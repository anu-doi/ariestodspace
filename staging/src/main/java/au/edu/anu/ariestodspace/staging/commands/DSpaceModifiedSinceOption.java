package au.edu.anu.ariestodspace.staging.commands;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TimeZone;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.jsoup.helper.StringUtil;
import org.kohsuke.args4j.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.ariestodspace.dspace.DSpaceMapping;
import au.edu.anu.ariestodspace.dspace.DSpacePersistenceManager;
import au.edu.anu.ariestodspace.dspace.data.Item;
import au.edu.anu.ariestodspace.dspace.data.ItemHandle;
import au.edu.anu.ariestodspace.staging.data.LastRun;
import au.edu.anu.ariestodspace.staging.data.StagingPersistenceManager;
import au.edu.anu.ariestodspace.staging.util.StagingProperties;
import au.edu.anu.ariestodspace.staging.util.Util;

import com.ibm.icu.text.MessageFormat;
import com.ibm.icu.util.Calendar;

public class DSpaceModifiedSinceOption extends StagingSubCommand {
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	private static final String QUOTATION = "\"";
	private static final String LAST_RUN_TYPE = "last_modified";
	
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());
	
	@Option(name="-h", aliases="--help",help=true, usage="Help")
	private boolean help = false;
	
//	@Option(name="-d", aliases="--date",usage="Date since last run",handler=DateOptionHandler.class)
//	private Date modifiedSinceDate;

	@Override
	public void execute() throws StagingCommandException {
		if (help) {
			CommandUtil.printUsage(CommandUtil.MIGRATE, this.getClass());
			return;
		}
		
		EntityManagerFactory stagingEmf = StagingPersistenceManager.getInstance().getEntityManagerFactory();
		
		EntityManager stagingEm = stagingEmf.createEntityManager();
		
//		EntityManagerFactory dspaceEm = DSpacePersistenceManager.getInstance().getEntityManagerFactory();
		
//		EntityManager em = dspaceEm.createEntityManager();
		
		try {
			LastRun lastRun = stagingEm.find(LastRun.class, LAST_RUN_TYPE);
			Date lastRunDate = new Date();
			if (lastRun != null) {
				LOGGER.info("Found last run");
				lastRunDate = lastRun.getRunDate();
			}
			else {
				LOGGER.info("Didn't find last run");
				Calendar cal = Calendar.getInstance();
				cal.set(1970, 1, 1, 0, 0);
				lastRunDate = cal.getTime();
			}
			
			String collections = StagingProperties.getProperty("modified.since.collections", "staging");
			String[] splitColls = collections.split(",");
			
			List<Integer> owningCollections = new ArrayList<Integer>();
			for (String coll : splitColls) {
				try {
					owningCollections.add(Integer.valueOf(coll));
				}
				catch (Exception e) {
					LOGGER.info("Unable to parse value: {}", coll);
				}
			}
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
			String lastRunDateStr = sdf.format(lastRunDate);
			
			
			LOGGER.info("Last run date: {}", lastRunDateStr);
			
////			Query query = em.createQuery("select new au.edu.anu.ariestodspace.staging.holder.ItemHolder(i.itemId, null, null, null) from Item i join i.handles h join i.metadataValues accessioned where accessioned.metadataFieldId = 11 and accessioned.textValue > :dateAccessioned");
//			Query query = em.createQuery("select distinct new au.edu.anu.ariestodspace.staging.holder.ItemHolder(i.itemId, h.handle.handle, ariesIdentifiers.textValue, title.textValue) from Item i join i.handles h join i.metadataValues accessioned with accessioned.metadataFieldId = 11 left join i.metadataValues ariesIdentifiers with ariesIdentifiers.metadataFieldId = 110 left join i.metadataValues title with title.metadataFieldId = 64 where accessioned.textValue > :dateAccessioned and i.owningCollection.collectionId in :owningCollection");
////			query.setParameter("dateAccessioned", "2015-04-01T00:00:00Z");
//			query.setParameter("dateAccessioned", lastRunDateStr);
//			query.setParameter("owningCollection", owningCollections);
			
//			List<ItemHolder> items = query.getResultList();
			List<Item> lastItems = getLastModifiedSinceItems(lastRunDateStr, owningCollections);
			if (lastRun == null) {
				lastRun = new LastRun();
				lastRun.setRunType(LAST_RUN_TYPE);
			}
			Date newRunDate = new Date();
			lastRun.setRunDate(newRunDate);
			stagingEm.getTransaction().begin();
			stagingEm.persist(lastRun);
			stagingEm.getTransaction().commit();
			
			if (lastItems == null || lastItems.size() == 0) {
				sendNoNewItems(lastRunDate, newRunDate);
				LOGGER.info("No new items to email to RSD");
				return;
			}
			
			String[][] itemsMatrix = processItems(lastItems);
			sendNewItems(itemsMatrix, lastRunDate, newRunDate);
		}
		finally {
			stagingEm.close();
			DSpacePersistenceManager.getInstance().closeEntityManagerFactory();
			StagingPersistenceManager.getInstance().closeEntityManagerFactory();
		}
	}
	
	private List<Item> getLastModifiedSinceItems(String lastRunDateStr, List<Integer> owningCollections) {
		EntityManager dspaceEm = DSpacePersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
		
		try {
			Query query = dspaceEm.createQuery("select distinct i from Item i join i.metadataValues accessioned with accessioned.metadataFieldId = 11 join fetch i.metadataValues metadataValues where accessioned.textValue > :dateAccessioned and i.owningCollection.collectionId in :owningCollection");
			query.setParameter("dateAccessioned", lastRunDateStr);
			query.setParameter("owningCollection", owningCollections);
			
			@SuppressWarnings("unchecked")
			List<Item> items = query.getResultList();
			return items;
		}
		finally {
			dspaceEm.close();
		}
	}
	
	private String[][] processItems(List<Item> items) {
		if (items.size() == 0) {
			return null;
		}
		
		DSpaceMapping mapping = new DSpaceMapping();
		
		Set<String> headings = new HashSet<String>();
		List<Map<String, List<String>>> records = new ArrayList<Map<String, List<String>>>();
		for (Item item : items) {
			try {
				Map<String, List<String>> values = mapping.getMap(item);
				Set<String> itemHeadings = values.keySet();
				headings.addAll(itemHeadings);
				values.put("itemId", Arrays.asList(item.getItemId().toString()));
				List<String> handles = new ArrayList<String>();
				for (ItemHandle handle : item.getHandles()) {
					String handleVal = "http://hdl.handle.net/" + handle.getHandle();
					handles.add(handleVal);
				}
				if (handles.size() > 0) {
					values.put("handle", handles);
				}
				records.add(values);
			}
			catch (IOException e) {
				
			}
		}

		List<String> excludeFields = getExcludeFieldTypes();
		headings.removeAll(excludeFields);
		
		LOGGER.info("Number of new records: {}, Number of Fields: {}", records.size(), headings.size());
		String[][] itemsMatrix = new String[records.size() + 1][headings.size() + 2];
		itemsMatrix[0][0] = "itemId";
		itemsMatrix[0][1] = "handle";
		Iterator<String> it = headings.iterator();
		int i = 2;
		while (it.hasNext()) {
			String heading = it.next();
			itemsMatrix[0][i] = heading;
			i++;
		}
		for (int j = 0; j < records.size(); j++) {
			Map<String, List<String>> record = records.get(j);
			for (int k = 0; k < itemsMatrix[0].length; k++) {
				String header = itemsMatrix[0][k];
				List<String> values = record.get(header);
				if (values != null && values.size() > 0) {
					String val = StringUtil.join(values, ";");
					itemsMatrix[j+1][k] = val;
				}
			}
		}
		return itemsMatrix;
	}
	
	private List<String> getExcludeFieldTypes() {
		String excludeProperty = StagingProperties.getProperty("modified.since.exclude", "staging");
		List<String> excludeFields = new ArrayList<String>();
		
		if (excludeProperty != null) {
			String[] excludes = excludeProperty.split(",");
			
			for (String fieldType : excludes) {
				excludeFields.add(fieldType.trim());
			}
		}
		
		return excludeFields;
	}
	
	private Writer generateCSV(String[][] values) {
		StringWriter writer = new StringWriter();
		for (int i = 0; i < values.length; i++) {
			for (int j = 0; j < values[i].length; j++) {
				if (j > 0) {
					writer.append(COMMA_DELIMITER);
				}
				if (values[i][j] != null) {
					writer.append(QUOTATION);
					writer.append(escapeCSVCharacters(values[i][j]));
					writer.append(QUOTATION);
				}
			}
			writer.append(NEW_LINE_SEPARATOR);
		}
		return writer;
	}
	
	private String escapeCSVCharacters(String value) {
		value = value.replaceAll("\"", "\"\"").replaceAll("\\r\\n|\\r|\\n", " ");
		
		return value;
	}
	
	private void sendNewItems(String[][] itemsMatrix, Date firstDate, Date lastDate) {
		Multipart multipart = new MimeMultipart();
		
		Writer writer = generateCSV(itemsMatrix);
		
		Session session = getSession();
		String from = getFromAddress();
		
		Set<String> recipients = getRecipients();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

		String firstDateStr = sdf.format(firstDate);
		String lastDateStr = sdf.format(lastDate);

		List<String> arguments = new ArrayList<String>();
		arguments.add(firstDateStr);
		arguments.add(lastDateStr);

		String messageText = "Please find attached the items that have been created in Digital Collections.";
		try {
			messageText = getMessageText("mail/newmail.txt", arguments.toArray(new String[0]));
		}
		catch (IOException e) {
			// Do nothing instead use default text;
		}
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			for (String to : recipients) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			}
			message.setSubject("New items in Digital Collections list");
			
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText(messageText);
			multipart.addBodyPart(messageBodyPart);
			
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setFileName("new_dspace_items.csv");
			messageBodyPart.setText(writer.toString());
			
			multipart.addBodyPart(messageBodyPart);
			
			message.setContent(multipart);
			Transport.send(message);
			LOGGER.info("Sent message successfully...");
		}
		catch (MessagingException e) {
			LOGGER.error("Error sending message", e);
		}
	}
	
	private void sendNoNewItems(Date firstDate, Date lastDate) {
		Session session = getSession();
		String from = getFromAddress();
		
		Set<String> recipients = getRecipients();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));

		String firstDateStr = sdf.format(firstDate);
		String lastDateStr = sdf.format(lastDate);
		

		List<String> arguments = new ArrayList<String>();
		arguments.add(firstDateStr);
		arguments.add(lastDateStr);
		
		String messageText = "No new records were created in Digital Collections";
		try {
			messageText = getMessageText("mail/nonewmail.txt", arguments.toArray(new String[0]));
		}
		catch (IOException e) {
			// Do nothing instead use default text;
		}
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			for (String to : recipients) {
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			}
			message.setSubject("New items in Digital Collections list");

			message.setText(messageText);
			
			Transport.send(message);
			LOGGER.info("Send message successfully...");
		}
		catch (MessagingException e) {
			LOGGER.error("Error sending message", e);
		}
	}
	
	private String getMessageText(String resourceName, String[] arguments) throws IOException {
		InputStream is = StagingProperties.class.getClassLoader().getResourceAsStream(resourceName);
		String msgText = Util.convertStreamToString(is);
		String text = MessageFormat.format(msgText, arguments);
		
		return text;
	}
	
	private Session getSession() {
		Properties properties = System.getProperties();
		
		String mailServer = StagingProperties.getProperty("mail.server", "staging");
		if (mailServer != null) {
			properties.setProperty("mail.smtp.host", mailServer);
		}
		
		String port = StagingProperties.getProperty("mail.server.port", "staging");
		if (port != null) {
			properties.setProperty("mail.smtp.port", port);
		}
		Session session = Session.getDefaultInstance(properties);
		return session;
	}
	
	private String getFromAddress() {
		String from = StagingProperties.getProperty("mail.from.address", "staging");
		return from;
	}
	
	private Set<String> getRecipients() {
		String to = StagingProperties.getProperty("mail.to.address", "staging");
		
		String[] addresses = to.split(",");
		Set<String> recipients = new HashSet<String>();
		for (String address : addresses) {
			recipients.add(address.trim());
		}
		
		return recipients;
	}
}
