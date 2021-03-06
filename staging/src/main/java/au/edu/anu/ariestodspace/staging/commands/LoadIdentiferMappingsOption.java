package au.edu.anu.ariestodspace.staging.commands;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.kohsuke.args4j.Option;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.ariestodspace.dspace.DSpacePersistenceManager;
import au.edu.anu.ariestodspace.staging.data.IdentifierMapping;
import au.edu.anu.ariestodspace.staging.data.StagingPersistenceManager;

/**
 * Command line option to retrieve the identifier mappings from DSpace
 * 
 * @author Genevieve Turner
 *
 */
public class LoadIdentiferMappingsOption extends StagingSubCommand {
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Option(name="-h", aliases="--help",help=true)
	private boolean help = false;
	
	@Override
	public void execute() throws StagingCommandException {
		if (help) {
			CommandUtil.printUsage(CommandUtil.LOAD_IDENTIFIER, this.getClass());
		}
		
		EntityManager dspaceEm = DSpacePersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
		try {
			StringBuilder queryString = new StringBuilder();
			queryString.append("SELECT new au.edu.anu.ariestodspace.staging.data.IdentifierMapping(mv.textValue, i.itemId, itemHandle.handle, collectionHandle.handle) ");
			queryString.append("FROM Item i left join i.handles itemHandle left join i.owningCollection owningColl left join owningColl.handle collectionHandle join i.metadataValues mv ");
			queryString.append("WHERE mv.metadataFieldId = 110 ");
			queryString.append("AND i.withdrawn = false ");
			
			Query query = dspaceEm.createQuery(queryString.toString());
			@SuppressWarnings("unchecked")
			List<IdentifierMapping> mappings = query.getResultList();
			persistMappings(mappings);
		}
		finally {
			dspaceEm.close();
		}
	}
	
	/**
	 * Insert/Update the identifier mappings
	 * 
	 * @param mappings The list of identifier mappings
	 */
	private void persistMappings(List<IdentifierMapping> mappings) {
		EntityManager stagingEm = StagingPersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
		try {
			stagingEm.getTransaction().begin();
			for (IdentifierMapping mapping : mappings) {
				Query identifierQuery = stagingEm.createQuery("SELECT im FROM IdentifierMapping im WHERE im.ariesIdentifier = :ariesIdentifier AND im.itemId = :itemId");
				identifierQuery.setParameter("ariesIdentifier", mapping.getAriesIdentifier());
				identifierQuery.setParameter("itemId", mapping.getItemId());

				@SuppressWarnings("unchecked")
				List<IdentifierMapping> existingMappings = identifierQuery.getResultList();
				
				if (existingMappings != null && existingMappings.size() > 0) {
					for (IdentifierMapping existingMapping : existingMappings) {
						boolean hasChanged = false;
						if (mapping.getHandle() != null && !mapping.getHandle().equals(existingMapping.getHandle())) {
							existingMapping.setHandle(mapping.getHandle());
							hasChanged = true;
						}
						if (mapping.getCollectionHandle() != null && !mapping.getCollectionHandle().equals(existingMapping.getCollectionHandle())) {
							existingMapping.setCollectionHandle(mapping.getCollectionHandle());
							hasChanged = true;
						}
						if (hasChanged) {
							stagingEm.persist(existingMapping);
						}
					}
				}
				else {
					stagingEm.persist(mapping);
				}
			}
			stagingEm.getTransaction().commit();
		}
		finally {
			stagingEm.close();
		}
	}
}
