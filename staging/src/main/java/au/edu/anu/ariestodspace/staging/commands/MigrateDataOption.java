package au.edu.anu.ariestodspace.staging.commands;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.StringArrayOptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.swordapp.client.ClientConfiguration;
import org.swordapp.client.SWORDClient;

import au.edu.anu.ariestodspace.aries.ARIESPersistenceManager;
import au.edu.anu.ariestodspace.aries.InternalAuthor;
import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;
import au.edu.anu.ariestodspace.aries.ResearchOutputsDataAuthors;
import au.edu.anu.ariestodspace.aries.ResearchOutputsDataDocuments;
import au.edu.anu.ariestodspace.aries.annotation.ARIESParser;
import au.edu.anu.ariestodspace.aries.outputs.IgnoreOutput;
import au.edu.anu.ariestodspace.dspace.DSpaceMapping;
import au.edu.anu.ariestodspace.dspace.DSpacePersistenceManager;
import au.edu.anu.ariestodspace.dspace.data.Item;
import au.edu.anu.ariestodspace.staging.data.CurrentANUPeople;
import au.edu.anu.ariestodspace.staging.data.DuplicateRecords;
import au.edu.anu.ariestodspace.staging.data.IdentifierMapping;
import au.edu.anu.ariestodspace.staging.data.LastRun;
import au.edu.anu.ariestodspace.staging.data.StagingPersistenceManager;
import au.edu.anu.ariestodspace.staging.exception.StagingException;
import au.edu.anu.ariestodspace.staging.sword.SwordProcessor;
import au.edu.anu.ariestodspace.staging.sword.SwordServerInfo;
import au.edu.anu.ariestodspace.staging.sword.data.BitstreamInfo;
import au.edu.anu.ariestodspace.staging.sword.data.StagingSwordRequestData;
import au.edu.anu.ariestodspace.staging.sword.data.SwordRequestData;
import au.edu.anu.ariestodspace.staging.sword.data.SwordRequestDataProvider;
import au.edu.anu.ariestodspace.staging.sword.exception.WorkflowException;
import au.edu.anu.ariestodspace.staging.util.StagingProperties;

/**
 * Command line option to migrate data.
 * 
 * @author Genevieve Turner
 *
 */
public class MigrateDataOption extends StagingSubCommand {
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	private static final String ARIES_IMPORT = "Imported from ARIES";
	private static final String LAST_IMPORT_TYPE = "last_import";
	private static final List<Integer> VALID_DOCUMENT_TYPES = Collections.unmodifiableList(
			new ArrayList<Integer>() {
				private static final long serialVersionUID = 1L;
			{
				add(new Integer(0));
				add(new Integer(1));
				add(new Integer(7));
				add(new Integer(11));
			}}
	);
	private static final Pattern FILE_EXT_PATTERN = Pattern.compile("pdf|jpg");
	private static final Pattern DOCUMENT_NAME_PATTERN = Pattern.compile("front|email|letter|info|content|referee|proceedi|peer|program|intro|Affil|author|Evidence|toc");
	private static final String ARIES_DIRECTORY = StagingProperties.getProperty("aries.directory", "staging");
	
	@Option(name="-h", aliases="--help",help=true, usage="Help")
	private boolean help = false;
	
	@Option(name="-i",aliases="--aries-identifier",required=false,usage="ARIES Identifier",metaVar="aries identifier",handler=StringArrayOptionHandler.class)
	private List<String> ariesIds;
	
	@Option(name="-o",aliases="--only-matched",required=false,usage="Only migrate matched records")
	private boolean onlyMatched;
	
	@Option(name="-r", aliases="--replace-existing",required=false,usage="Replace existing values in DSpace with values from ARIES")
	private boolean replaceExisting;
	
	@Option(name="-c", aliases="--collection-name",required=false,usage="For new items submit to a collection other than the default")
	private String collectionName;
	
	@Option(name="-f", aliases="--filename",required=false,usage="A list of aries identifiers to migrate")
	private String filename;
	
	@Option(name="-l", aliases="--use-last-run",required=false,usage="Indicates to execute only from the last run date")
	private boolean useLastRun;
	
	@Option(name="-s", aliases="--counter-start", required=false,usage="Start of the ARIES Identifier Counter")
	private Integer counterStart;
	
	@Option(name="-e", aliases="--counter-end", required=false, usage="End of the ARIES Identifier Counter")
	private Integer counterEnd;
	
	@Override
	public void execute() throws StagingCommandException {
		if (help) {
			CommandUtil.printUsage(CommandUtil.MIGRATE, this.getClass());
			return;
		}
		try {
			if (ariesIds != null) {
				processIds();
			}
			else if (filename != null) {
				processFile();
			}
			else if (counterStart != null) {
				processByCounter();
			}
			else if (counterEnd == null) {
				processAll();
			}
		}
		finally {
			ARIESPersistenceManager.getInstance().closeEntityManagerFactory();
			DSpacePersistenceManager.getInstance().closeEntityManagerFactory();
			StagingPersistenceManager.getInstance().closeEntityManagerFactory();
		}
	}
	
	/**
	 * Process a list of identifiers
	 */
	@SuppressWarnings("unchecked")
	private void processIds() {
		LOGGER.info("Process by ids");
		EntityManager ariesEm = ARIESPersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
		//filter out verified records
		Query query = ariesEm.createQuery("SELECT data1 FROM ResearchOutputsData1 data1 WHERE chrOutput6Code in :ariesIds and chrCalculatePoints = 'Yes'");
		List<ResearchOutputsData1> results = null;
		List<ResearchOutputsData1> recordsToAdd = new ArrayList<ResearchOutputsData1>();
		int numberOfRecords = 10;
		try {
			for (int i = 0; i < ariesIds.size(); i=i+numberOfRecords) {
				int toIndex = i + numberOfRecords;
				if (ariesIds.size() < toIndex) {
					toIndex = ariesIds.size();
				}
				List<String> queryIds = ariesIds.subList(i, toIndex);
				query.setParameter("ariesIds", queryIds);
				results = query.getResultList();
				recordsToAdd.addAll(results);
			}
		}
		finally {
			ariesEm.close();
		}
		processSword(recordsToAdd);
	}
	
	/**
	 * Process all the appropriate records
	 */
	@SuppressWarnings("unchecked")
	private void processAll() {
		LOGGER.info("Process all");
		EntityManager stagingEm = StagingPersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
		EntityManager ariesEm = ARIESPersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
		List<ResearchOutputsData1> results = new ArrayList<ResearchOutputsData1>();
		try {
			Query query = null;
			if (useLastRun) {
				LastRun lastRun = stagingEm.find(LastRun.class, LAST_IMPORT_TYPE);
				LOGGER.info("Last run date: {}", lastRun.getRunDate());

				//get all the records from the given date/time and filter out verified records
				query = ariesEm.createQuery("SELECT data1 FROM ResearchOutputsData1 data1 WHERE chrCalculatePoints = 'Yes' and (chrAmendedByDateTime > :lastRunDate or chrCreatedByDateTime > :lastRunDate)");
				query.setParameter("lastRunDate", lastRun.getRunDate());
				
				Date newDate = new Date();
				lastRun.setRunDate(newDate);
				stagingEm.getTransaction().begin();
				stagingEm.persist(lastRun);
				stagingEm.getTransaction().commit();
			}
			else {
				//filter out verified records
				query = ariesEm.createQuery("SELECT data1 FROM ResearchOutputsData1 data1 WHERE chrCalculatePoints = 'Yes'");
			}
			if (query != null) {
				results = query.getResultList();
			}
		}
		finally {
			ariesEm.close();
			stagingEm.close();
		}
		processSword(results);
	}
	
	/**
	 * Process a list of ids contained within a file
	 */
	@SuppressWarnings("unchecked")
	private void processFile() {
		LOGGER.info("Process by file");
		try {
			FileReader fr = new FileReader(filename);
			List<String> ariesIds = new ArrayList<String>();
			
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) {
				if (!ariesIds.contains(line)) {
					ariesIds.add(line);
				}
			}
			br.close();
			fr.close();
			
			EntityManager ariesEm = ARIESPersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
			//filter out verified records
			Query query = ariesEm.createQuery("SELECT data1 FROM ResearchOutputsData1 data1 WHERE chrOutput6Code in :ariesIds and chrCalculatePoints = 'Yes'");
			List<ResearchOutputsData1> results = null;
			List<ResearchOutputsData1> recordsToAdd = new ArrayList<ResearchOutputsData1>();
			int numberOfRecords = 10;
			try {
				for (int i = 0; i < ariesIds.size(); i=i+numberOfRecords) {
					int toIndex = i + numberOfRecords;
					if (ariesIds.size() < toIndex) {
						toIndex = ariesIds.size();
					}
					List<String> queryIds = ariesIds.subList(i, toIndex);
					query.setParameter("ariesIds", queryIds);
					results = query.getResultList();
					recordsToAdd.addAll(results);
				}
			}
			finally {
				ariesEm.close();
			}
			processSword(recordsToAdd);
		}
		catch (IOException e) {
			LOGGER.error("Error reading file");
		}
	}
	
	/**
	 * process records by the counter
	 */
	@SuppressWarnings("unchecked")
	private void processByCounter() {
		LOGGER.info("Process by counter.  The records with a chrOutput6CodeCounter between {} and {} will be processed", counterStart, counterEnd);
		List<ResearchOutputsData1> results = new ArrayList<ResearchOutputsData1>();
		EntityManager em = ARIESPersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
		try {
			Query query = em.createQuery("SELECT data1 FROM ResearchOutputsData1 data1 WHERE data1.chrCalculatePoints = 'Yes' and data1.chrOutput6CodeCounter between :startCounter and :endCounter order by data1.chrOutput6CodeCounter asc, data1.chrCreatedByCode asc");
			query.setParameter("startCounter", counterStart);
			if (counterEnd == null) {
				query.setParameter("endCounter", counterStart);
			}
			else {
				query.setParameter("endCounter", counterEnd);
			}
			results = query.getResultList();
		}
		finally {
			em.close();
		}
		processSword(results);
	}
	
	/**
	 * Process the ARIES records and send them to DSpave via Sword
	 * 
	 * @param results The ARIES records
	 */
	private void processSword(List<ResearchOutputsData1> results) {
		if (collectionName == null) {
			collectionName = StagingProperties.getProperty("sword.default.collection", "staging");
		}
		
		SwordRequestDataProvider dataProvider = new StagingSwordRequestData();
		
		for (ResearchOutputsData1 data1 : results) {
			try {
				boolean sendToDSpace = true;
				if (data1 instanceof IgnoreOutput) {
					sendToDSpace = false;
				}
				if (sendToDSpace && data1.getExternalCategories().contains(data1.getChrOutput2Code())) {
					sendToDSpace = checkIfHasCurrentANUUser(data1);
					LOGGER.debug("Send external category to DSpace for {}: {}", data1.getChrOutput6Code(), sendToDSpace);
				}
				boolean isNotPrimaryDuplicate = checkIfShouldProceedIfDuplicate(data1.getChrOutput6Code());
				
				if (sendToDSpace && isNotPrimaryDuplicate) {
					processSendRecord(dataProvider, data1);
				}
			}
			catch (StagingException e) {
				LOGGER.error("Exception retrieving data", e);
			}
			catch (IllegalAccessException | InvocationTargetException | IOException e) {
				LOGGER.error("Error parsing aries data", e);
			}
		}
		
		String serverUrl = StagingProperties.getProperty("sword.server", "staging");
		String username = StagingProperties.getProperty("sword.username", "staging");
		String password = StagingProperties.getProperty("sword.password", "staging");
		
		String serviceDocumentUrl = serverUrl + "/servicedocument";
		
		ClientConfiguration config = new ClientConfiguration();
		SWORDClient swordClient = new SWORDClient(config);
		SwordServerInfo info = new SwordServerInfo(serviceDocumentUrl, username, password);
		SwordProcessor processor = new SwordProcessor(swordClient, info, dataProvider);
		try {
			processor.process();
		}
		catch (WorkflowException e) {
			LOGGER.error("Exception procesing sword records", e);
		}
		postProcessSword(dataProvider);
	}
	
	/**
	 * Process the records and send them to DSpace
	 * 
	 * @param dataProvider The sword data provider
	 * @param data1 The aries record
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws IOException
	 */
	private void processSendRecord(SwordRequestDataProvider dataProvider, ResearchOutputsData1 data1) 
			throws IllegalAccessException, InvocationTargetException, IOException, StagingException {
		ARIESParser parser = new ARIESParser();
		Set<String> duplicates = getDuplicateAriesIdentifiers(data1.getChrOutput6Code());
		boolean sendToDSpace = false;

		Map<String, Set<String>> mapToSend = new HashMap<String, Set<String>>();
		Map<String, List<String>> ariesMap = parser.getDSpaceValues(data1);
		
		// Make sure all the ARIES ids are in the item
		if (duplicates != null) {
			List<String> ariesIds = ariesMap.get("local.identifier.ariespublication");
			
			if (duplicates != null && ariesIds.size() != duplicates.size() ) {
				for (String duplicate : duplicates) {
					if (!ariesIds.contains(duplicate)) {
						ariesIds.add(duplicate);
					}
				}
			}
		}
		
		Item item = getItem(data1, duplicates);
		if (item != null) {
			DSpaceMapping dspaceMapping = new DSpaceMapping();
	
			Map<String, List<String>> itemMap = dspaceMapping.getMap(item);
			
			for (Entry<String, List<String>> entry : itemMap.entrySet()) {
				String mappingKey = StagingProperties.getProperty(entry.getKey(), "fieldmappings");
				if (mappingKey != null) {
					Set<String> values = new LinkedHashSet<String>(entry.getValue());
					mapToSend.put(mappingKey, values);
				}
			}

			for (Entry<String, List<String>> entry : ariesMap.entrySet()) {
				String key = entry.getKey();
				
				List<String> ariesValues = entry.getValue();
				List<String> dspaceValues = itemMap.get(key);
				
				if (dspaceValues == null || dspaceValues.size() == 0) {
					String mappingKey = StagingProperties.getProperty(entry.getKey(), "fieldmappings");
					if (mappingKey != null) {
						LinkedHashSet<String> setData = new LinkedHashSet<String>();
						if ("local.identifier.authoremail".equals(entry.getKey())) {
							setFirstAuthorEmail(data1, ariesValues);
						}
						addData(setData, ariesValues);
						mapToSend.put(mappingKey, setData);
						LOGGER.debug("Differences in value {} for item {}", mappingKey, data1.getChrOutput6Code());
						sendToDSpace = true;
					}
				}
			}
		}
		else {
			sendToDSpace = true;
			addAllToSetMap(mapToSend, ariesMap, true);

			String notesKey = StagingProperties.getProperty("local.description.notes", "fieldmappings");
			
			//Indicate that the record was imported from ARIES
			Set<String> notes = mapToSend.get(notesKey);
			if (notes != null) {
				notes.add(ARIES_IMPORT);
			}
			else {
				notes = new LinkedHashSet<String>();
				notes.add(ARIES_IMPORT);
				mapToSend.put(notesKey, notes);
			}
			
			// Set an embargo date for newly created records
			Set<String> embargoes = new LinkedHashSet<String>();
			embargoes.add("2037-12-31");
			String embargoKey = StagingProperties.getProperty("local.description.embargo", "fieldmappings");
			mapToSend.put(embargoKey, embargoes);
			
			// Add default email to send
			String emailKey = StagingProperties.getProperty("local.contributor.authoremail", "fieldmappings");
			Set<String> emails = mapToSend.get(emailKey);
			List<String> emailList = new ArrayList<String>();
			if (emails != null && emails.size() > 0) {
				emailList.addAll(emails);
			}
			setFirstAuthorEmail(data1, emailList);
			emails = new LinkedHashSet<String>(emailList);
			mapToSend.put(emailKey, emails);
//			mapToSend.put(emailKey, value)
		}
		if (sendToDSpace && mapToSend.size() > 0) {
			if (item != null) {
				SwordRequestData data = new SwordRequestData(item.getItemId(), mapToSend, null, true);
				dataProvider.getSwordRequests().add(data);
			}
			else {
				Set<BitstreamInfo> bitstreams = getBitstreams(data1);
				SwordRequestData data = new SwordRequestData(collectionName, mapToSend, bitstreams, false);
				dataProvider.getSwordRequests().add(data);
			}
		}
	}
	
	/**
	 * Process the records after they have been sent to DSpace via SWoRD. (i.e. save the newly created ids to a file)
	 * 
	 * @param dataProvider The data provider that was used to submit items via sword
	 */
	private void postProcessSword(SwordRequestDataProvider dataProvider) {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileLocation = StagingProperties.getProperty("id.file.directory", "staging");
		String filename = fileLocation + "new-id-list-" + sdf.format(date) + ".txt";

		EntityManager stagingEm = StagingPersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
		BufferedWriter writer = null;
		try {
			try {
				stagingEm.getTransaction().begin();
				for (SwordRequestData request : dataProvider.getSwordRequests()) {
					Map<String, Set<String>> metadata = request.getMetadata();
					Set<String> ariesIdentifiers = metadata.get("ariespublication");
					if (request.getCollectionName() != null && request.getEditLink() != null) {
						String id = request.getEditLink().substring(request.getEditLink().lastIndexOf("/") + 1);
						try {
							if (writer == null) {
								writer = new BufferedWriter(new FileWriter(filename));
								LOGGER.info("Created file at {}", filename);
							}
							writer.write(id);
							writer.write("\n");
						}
						catch (IOException e) {
							LOGGER.error("Error writing id to file", id);
						}
						Integer itemId = Integer.valueOf(id);
						for (String ariesId : ariesIdentifiers) {
							if (itemId != null) {
								IdentifierMapping identifierMapping = new IdentifierMapping(ariesId, itemId);
								stagingEm.persist(identifierMapping);
							}
							else {
								LOGGER.error("Item id integer value for {} and aries identifier {} is null", id, ariesId);
							}
						}
					}
				}
				stagingEm.getTransaction().commit();
			}
			finally {
				stagingEm.close();
				if (writer != null) {
					writer.close();
				}
			}
		}
		catch (IOException e) {
			LOGGER.error("Error closing writer");
		}
	}
	
	/**
	 * Add a list of values to the data set
	 * 
	 * @param data The data set
	 * @param values The values to put in the set
	 */
	private void addData(Set<String> data, List<String> values) {
		for (String value : values) {
			addData(data, value);
		}
	}
	
	/**
	 * Add a single value to the data set.  Thsi will also strip the html tags.
	 * 
	 * @param data The data set
	 * @param value The value to put in the set
	 */
	private void addData(Set<String> data, String value) {
		if (value != null) {
			value = value.trim();
			if (!"".equals(value)) {
				value = value.replaceAll("<sub>|</sub>|<sup>|</sup>|<i>|</i>|<br>|</br>|<p>|</p>|<em>|</em>|<i>|</i>|<super>|</super>", " ");
				data.add(value);
			}
		}
	}
	
	/**
	 * Get the item associated with the aries record
	 * 
	 * 
	 * @param data1 The aries record
	 * @param duplicates The a list of duplicate records
	 * @return The item
	 */
	@SuppressWarnings("unchecked")
	private Item getItem(ResearchOutputsData1 data1, Set<String> duplicates) throws StagingException {
		EntityManager stagingEm = StagingPersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
		
		List<IdentifierMapping> mappings = null;
		try {
			Query identifierQuery = stagingEm.createQuery("SELECT im FROM IdentifierMapping im WHERE ariesIdentifier in :ariesIdentifier", IdentifierMapping.class);
			if (duplicates != null && duplicates.size() > 0) {
				identifierQuery.setParameter("ariesIdentifier", duplicates);
			}
			else {
				identifierQuery.setParameter("ariesIdentifier", data1.getChrOutput6Code());
			}
			mappings = identifierQuery.getResultList();
		}
		finally {
			stagingEm.close();
		}
		if (mappings != null && mappings.size() > 0) {
			EntityManager dspaceEm = DSpacePersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
			try {
				Integer itemId = mappings.get(0).getItemId();
				Item item = dspaceEm.find(Item.class, itemId);
				if (item != null) {
					item.getMetadataValues().size();
					return item;
				}
				else {
					throw new StagingException("Unable to find item with the id '"+itemId+"' for the aries record '"+data1.getChrOutput6Code()+"'");
				}
			}
			finally {
				dspaceEm.close();
			}
		}
		else {
			String handleUri = null;
			List<ResearchOutputsDataDocuments> documents = data1.getDocuments();
			for (ResearchOutputsDataDocuments doc : documents) {
				if ("ANU Repository Link".equals(doc.getChrDocumentName())) {
					handleUri = doc.getChrURL();
					break;
				}
			}
			if (handleUri != null) {
				int index = handleUri.lastIndexOf("/");
				if (index > 0) {
					index = handleUri.lastIndexOf("/", index);
					String handle = handleUri.substring(index + 1);
					EntityManager dspaceEm = DSpacePersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
					try {
						Query query = dspaceEm.createQuery("SELECT i FROM Item i where i.handles.handle = :handle");
						query.setParameter("handle", handle);
						
						Item item = (Item) query.getSingleResult();
						return item;
					}
					catch (NoResultException e) {
						LOGGER.error("Unable to find item record for the handle: {}", handle, e);
					}
					finally {
						dspaceEm.close();
					}
				}
			}
		}
		
		return null;
	}
	
	/**
	 * Add all the items to a map that is to be send to Sword
	 * 
	 * @param mapToSend The map to add values to
	 * @param sourceMap The map that contains the values
	 */
	private void addAllToSetMap(Map<String, Set<String>> mapToSend, Map<String, List<String>> sourceMap, boolean decodeLatin) {
		for (Entry<String, List<String>> entry : sourceMap.entrySet()) {
			if (entry.getValue() != null && entry.getValue().size() > 0) {
				String mappingKey = StagingProperties.getProperty(entry.getKey(), "fieldmappings");
				Set<String> set = new LinkedHashSet<String>();
				addData(set, entry.getValue());
				mapToSend.put(mappingKey, set);
			}
		}
	}
	
	/**
	 * Set the first email
	 * 
	 * @param data1 The aries record
	 * @param authorEmails The list of author emails
	 */
	private void setFirstAuthorEmail(ResearchOutputsData1 data1, List<String> authorEmails) {
		List<ResearchOutputsDataAuthors> authors = data1.getAuthors();
		
		List<String> authorUids = new ArrayList<String>();
		for (ResearchOutputsDataAuthors author : authors) {
			if (author.getUid() != null) {
				authorUids.add(author.getUid().toLowerCase());
			}
		}
		String firstEmail = "repository.admin@anu.edu.au";
		if (authorUids.size() > 0) {
			EntityManager em = StagingPersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
			try {
				Query query = em.createQuery("SELECT p FROM CurrentANUPeople p WHERE lower(p.universityId) in :uids");
				query.setParameter("uids", authorUids);
				
				@SuppressWarnings("unchecked")
				List<CurrentANUPeople> people = query.getResultList();
				
				if (people != null && people.size() > 0) {
					List<String> currentPeopleUids = new ArrayList<String>();
					for (CurrentANUPeople person : people) {
						currentPeopleUids.add(person.getUniversityId().toLowerCase());
					}
				
					for (String uid : authorUids) {
						if (currentPeopleUids.contains(uid)) {
							firstEmail = uid + "@anu.edu.au";
							break;
						}
					}
				}
			}
			finally {
				em.close();
			}
		}
		authorEmails.add(0, firstEmail);
	}
	
	/**
	 * Check if there is a current ANU user associated with an ARIES record
	 * 
	 * @param data1 The aries record
	 * @return True if there is a current ANU User
	 */
	private boolean checkIfHasCurrentANUUser(ResearchOutputsData1 data1) {
		List<ResearchOutputsDataAuthors> authors = data1.getAuthors();
		
		List<String> uidsToCheck = new ArrayList<String>();
		
		for (ResearchOutputsDataAuthors author : authors) {
			if (author instanceof InternalAuthor) {
				String uid = ((InternalAuthor) author).getUser().getChrStaffNumber();
				uidsToCheck.add(uid.toLowerCase());
			}
		}
		
		if (uidsToCheck.size() > 0) {
			EntityManager em = StagingPersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
			try {
				Query query = em.createQuery("select cu from CurrentANUPeople cu where universityId in :uids");
				query.setParameter("uids", uidsToCheck);
				List<?> results = query.getResultList();
				if (results != null && results.size() > 0) {
					return true;
				}
			}
			finally {
				em.close();
			}
		}
		
		return false;
	}
	
	/**
	 * Check if the identifier is associated with the primary record
	 * 
	 * @param ariesIdentifier The aries identifier
	 * @return Indicates if it should proceed
	 */
	private boolean checkIfShouldProceedIfDuplicate(String ariesIdentifier) {
		EntityManager em = StagingPersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
		try {
			Query query = em.createQuery("SELECT dr from DuplicateRecords dr WHERE ariesIdentifier = :ariesIdentifier");
			try {
				query.setParameter("ariesIdentifier", ariesIdentifier);
				try {
					DuplicateRecords record = (DuplicateRecords) query.getSingleResult();
					if (record != null && record.getSequenceNumber().intValue() > 1) {
						return false;
					}
				}
				catch (NoResultException e) {
					//do nothing if no result was found
				}
			}
			catch (Exception e) {
				//DO nothing
				LOGGER.error("Exception getting record", e);
			}
		}
		finally {
			em.close();
		}
		
		return true;
	}
	
	/**
	 * Get the list of duplicate identifiers
	 * 
	 * @param ariesIdentifier THe aries identifier to find duplicates for
	 * @return The list of duplicates
	 */
	private Set<String> getDuplicateAriesIdentifiers(String ariesIdentifier) {
		EntityManager em = StagingPersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
		try {
			Query query = em.createQuery("SELECT dr1 FROM DuplicateRecords dr1 WHERE EXISTS (SELECT 1 FROM DuplicateRecords dr2 WHERE dr1.duplicateId = dr2.duplicateId and dr2.ariesIdentifier = :ariesIdentifier)");
			query.setParameter("ariesIdentifier", ariesIdentifier);
			@SuppressWarnings("unchecked")
			List<DuplicateRecords> duplicates = query.getResultList();
			if (duplicates != null && duplicates.size() > 0) {
				Collections.sort(duplicates, new Comparator<DuplicateRecords>() {
					@Override
					public int compare(DuplicateRecords duplicate1, DuplicateRecords duplicate2) {
						return duplicate1.getDuplicateId().compareTo(duplicate2.getDuplicateId());
					}
				});
				
				Set<String> identifiers = new LinkedHashSet<String>();
				for (DuplicateRecords dupe : duplicates) {
					identifiers.add(dupe.getAriesIdentifier());
				}
				return identifiers;
			}
		}
		finally {
			em.close();
		}
		return null;
	}
	
	/**
	 * Get the files to send associated with the record
	 * 
	 * @param data1 The aries record
	 * @return The list of bitstreams
	 */
	private Set<BitstreamInfo> getBitstreams(ResearchOutputsData1 data1) {
		Set<BitstreamInfo> bitstreams = new LinkedHashSet<BitstreamInfo>();
		
		Matcher fileExtMatcher = null;
		Matcher nameMatcher = null;
		for (ResearchOutputsDataDocuments doc : data1.getDocuments()) {
			if (VALID_DOCUMENT_TYPES.contains(doc.getIntDocumentType())) {
				fileExtMatcher = FILE_EXT_PATTERN.matcher(doc.getChrFileExtention());
				if (fileExtMatcher.matches()) {
					nameMatcher = DOCUMENT_NAME_PATTERN.matcher(doc.getChrDocumentName());
					if (!nameMatcher.matches()) {
						String filename = ARIES_DIRECTORY + doc.getChrOutputFileName();
						try {
							LOGGER.info("File name: {}", filename);
							BitstreamInfo bitstream = new BitstreamInfo(filename);
							bitstreams.add(bitstream);
						}
						catch (FileNotFoundException e) {
							LOGGER.error("Exception creating bitstream file", e);
						}
					}
				}
			}
		}
		if (bitstreams.size() > 0) {
			return bitstreams;
		}
		
		return null;
	}
}
