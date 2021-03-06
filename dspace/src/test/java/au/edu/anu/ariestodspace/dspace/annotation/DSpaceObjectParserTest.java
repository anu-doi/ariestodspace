package au.edu.anu.ariestodspace.dspace.annotation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.ariestodspace.dspace.DSpaceObject;
import au.edu.anu.ariestodspace.dspace.DSpacePersistenceManager;
import au.edu.anu.ariestodspace.dspace.data.CollectionHandle;
import au.edu.anu.ariestodspace.dspace.data.Item;
import au.edu.anu.ariestodspace.dspace.data.ItemHandle;

public class DSpaceObjectParserTest {
	private static Logger LOGGER = LoggerFactory.getLogger(DSpaceObjectParserTest.class);
	
//	private static EntityManagerFactory emf;
	
	@AfterClass
	public static void after() {
		DSpacePersistenceManager.getInstance().closeEntityManagerFactory();
	}
	
	@Ignore
	@Test
	public void test() {
		EntityManager em = DSpacePersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
		
//		em.createQuery("");
		Item item = null;

		try {
			item = em.find(Item.class, 1);
			assertNotNull("Item is null", item);
			assertNotNull("Item has no metadata values", item.getMetadataValues());
			
			assertEquals("Unexpected number of metadata values", 30, item.getMetadataValues().size());
		}
		finally {
			em.close();
		}
		assertNotNull("Item unexpectedly null", item);
		
		assertNotNull("Owning collection is null",item.getOwningCollection());
		try {
			DSpaceObjectParser parser = new DSpaceObjectParser();
			DSpaceObject dso = parser.getDSpaceObject(item);
			assertNotNull("No DSpaceObject created", dso);
			
			List<String> abstracts = dso.getAbstracts();
			assertNotNull("No abstracts found", abstracts);
			assertEquals("Unexpected number of abstracts", 1, abstracts.size());
			assertEquals("Unexpected abstract value", "Journal abstract example", abstracts.get(0));
			
			List<String> authors = dso.getAuthors();
			assertNotNull("No authors found", authors);
			assertEquals("Unexpected number of authors", 4, authors.size());
			assertEquals("Unexpected author", "Body, Some", authors.get(0));
			assertEquals("Unexpected author", "Turner, Genevieve", authors.get(1));
			assertEquals("Unexpected author", "Person, Random", authors.get(2));
			assertEquals("Unexpected author", "Khanna, Rahul", authors.get(3));
			
			List<String> affiliations = dso.getAffiliation();
			assertNotNull("No affiliations found", affiliations);
			assertEquals("Unexpected number of affiliations", 4, affiliations.size());
			assertEquals("Unexpected affiliation","Body, Some, UNSW", affiliations.get(0));
			assertEquals("Unexpected affiliation","Turner, Genevieve, ITS, ANU", affiliations.get(1));
			assertEquals("Unexpected affiliation","Person, Random, UTAS", affiliations.get(2));
			assertEquals("Unexpected affiliation","Khanna, Rahul, ITS, ANU", affiliations.get(3));
			
			List<String> ariesIds = dso.getAriesIds();
			assertNotNull("No aries identifier found", ariesIds);
			assertEquals("Unexpected number of aries ids", 1, ariesIds.size());
			assertEquals("Unexpected aries id","u1111111xPUB2", ariesIds.get(0));
			
			Date dateIssued = dso.getDateIssued();
			assertNotNull("No date issued found", dateIssued);
			Calendar cal = Calendar.getInstance();
			cal.set(2015, 8, 21, 0, 0, 0);
			cal.set(Calendar.MILLISECOND, 0);
			assertEquals("Unexpected date value",cal.getTime().getTime(),dateIssued.getTime());
			
			List<String> dois = dso.getDois();
			assertNotNull("No DOI's found");
			assertEquals("unexpected number of DOI's", 1, dois.size());
			assertEquals("Unexpected DOI value", "10.12345/6789", dois.get(0));
			
			
			List<String> editors = dso.getEditors();
			assertNotNull("No editors found", editors);
			assertEquals("Unexpected number of editors", 0,editors.size());
			
			List<String> handles = dso.getHandles();
			assertNotNull("No handles found", handles);
			assertEquals("Unexpected number of handles", 1, handles.size());
			assertEquals("Unexpected handle value", "1885/3", handles.get(0));
			
//			dso.getIsbns();
			
			List<String> issns = dso.getIssns();
			assertNotNull("No issns found", issns);
			assertEquals("Unexpected number of issns", 1, issns.size());
			assertEquals("Unexpected issn value", "1234-5678", issns.get(0));
			
			List<String> sourceUris = dso.getSourceUris();
			assertNotNull("No source uris found", sourceUris);
			assertEquals("Unexpected number of source uris", 1, sourceUris.size());
			assertEquals("Unexpected source uri values", "http://google.com.au", sourceUris.get(0));
			
			String title = dso.getTitle();
			assertNotNull("No title found", title);
			assertEquals("Unexpected title value", "Test Journal Title", title);
		}
		catch (Exception e) {
			LOGGER.error("Exception parsing item", e);
		}
		
	}
	
//	@Ignore
	@Test
	public void getItemHandleTest() {
		EntityManager em = DSpacePersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
		
		ItemHandle handle = em.find(ItemHandle.class, 2);

		em.close();
		
		assertNotNull("No handle", handle);
		assertEquals("Incorrect handle id",new Integer(2),handle.getId());
		assertEquals("Incorrect handle","1885/3",handle.getHandle());
		assertNotNull("No item associated with handle", handle.getItem());
		assertEquals("Incorrect item id",new Integer(1),handle.getItem().getItemId());
	}
	
	@Ignore
	@Test
	public void getCollectionHandleTest() {
		EntityManager em = DSpacePersistenceManager.getInstance().getEntityManagerFactory().createEntityManager();
		
		CollectionHandle handle = em.find(CollectionHandle.class, 3);

		em.close();

		assertNotNull("No handle", handle);
		assertEquals("Incorrect handle id",new Integer(2),handle.getId());
		assertEquals("Incorrect handle","1885/3",handle.getHandle());
		assertNotNull("No item associated with handle", handle.getCollection());
		assertEquals("Incorrect collection id",new Integer(1),handle.getCollection().getCollectionId());
		
		
//		assertEquals("Incorrect collection name","Open Access Digital Theses",handle.getCollection().getName());
	}
}
