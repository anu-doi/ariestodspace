package au.edu.anu.ariestodspace.dspace.annotation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.ariestodspace.dspace.DSpaceMapping;
import au.edu.anu.ariestodspace.dspace.DSpacePersistenceManager;
import au.edu.anu.ariestodspace.dspace.data.Item;

public class DSpaceMappingTest {
	private static Logger LOGGER = LoggerFactory.getLogger(DSpaceObjectParserTest.class);
	
	private static EntityManagerFactory emf;
	
	@BeforeClass
	public static void before() {
		emf = DSpacePersistenceManager.getInstance().getEntityManagerFactory();
		if (emf == null) {
			LOGGER.info("Entity manager factory is null");
		}
	}
	
	@AfterClass
	public static void after() {
		DSpacePersistenceManager.getInstance().closeEntityManagerFactory();
	}

	@Test
	public void test() {
		EntityManager em = emf.createEntityManager();
		
		Item item = em.find(Item.class, 1);
		item.getMetadataValues().size();
		
		em.close();
		
		DSpaceMapping mapping = new DSpaceMapping();
		try {
			Map<String, List<String>> dspaceMap = mapping.getMap(item);
			
			List<String> abstracts = dspaceMap.get("dc.description.abstract");
			assertNotNull("No abstract value found", abstracts);
			assertEquals("Unexpected number of abstracts", 1, abstracts.size());
			assertEquals("Unexpected abstract value","Journal abstract example", abstracts.get(0));
			
			List<String> sources = dspaceMap.get("dc.source");
			assertNotNull("No sources found", sources);
			assertEquals("Unexpected number of sources", 1, sources.size());
			assertEquals("Unexpected source value", "Journal of all", sources.get(0));
			
			List<String> publishers = dspaceMap.get("dc.publisher");
			assertNotNull("No publishers found", publishers);
			assertEquals("Unexpected number of publishers", 1, publishers.size());
			assertEquals("Unexpected publisher value", "Elsevier", publishers.get(0));
			
			List<String> lastpages = dspaceMap.get("local.bibliographicCitation.lastpage");
			assertNotNull("No last pages found", lastpages);
			assertEquals("Unexpect4ed number of last pages", 1, lastpages.size());
			assertEquals("Unexpected last page", "59", lastpages.get(0));
			
			List<String> startpages = dspaceMap.get("local.bibliographicCitation.startpage");
			assertNotNull("No start pages found", startpages);
			assertEquals("Unexpected number of start pages", 1, startpages.size());
			assertEquals("Unexpected start page", "57", startpages.get(0));
			
			List<String> objectives = dspaceMap.get("local.identifier.absseo");
			assertNotNull("No socio-economic objects found", objectives);
			assertEquals("Unexpected number of socio-economic objectives", 2, objectives.size());
			assertEquals("Unexpected socio-economic objective", "861503", objectives.get(0));
			assertEquals("Unexpected socio-economic objective", "970102", objectives.get(1));
			
			List<String> authors = dspaceMap.get("dc.contributor.author");
			assertNotNull("No authors found", authors);
			assertEquals("Unexpected nubmer of authors", 4, authors.size());
			assertEquals("Unexpected author value", "Body, Some", authors.get(0));
			assertEquals("Unexpected author value", "Turner, Genevieve", authors.get(1));
			assertEquals("Unexpected author value", "Person, Random", authors.get(2));
			assertEquals("Unexpected author value", "Khanna, Rahul", authors.get(3));
			
			List<String> types = dspaceMap.get("dc.type");
			assertNotNull("No types found", types);
			assertEquals("Unexpected number of types", 1, types.size());
			assertEquals("Unexpected type value", "Journal Article", types.get(0));
			
			List<String> fields = dspaceMap.get("local.identifier.absfor");
			assertNotNull("No fields of research found", fields);
			assertEquals("Unexpected number of fields of research", 3, fields.size());
			assertEquals("Unexpected field of research", "020102", fields.get(0));
			assertEquals("Unexpected field of research", "030303", fields.get(1));
			assertEquals("Unexpected field of research", "040106", fields.get(2));
			
			List<String> uids = dspaceMap.get("local.contributor.authoruid");
			assertNotNull("No author uids found", uids);
			assertEquals("Unexpected number of author uids", 4, uids.size());
			assertEquals("Unexpected author uid", "E1111", uids.get(0));
			assertEquals("Unexpected author uid", "u1111111", uids.get(1));
			assertEquals("Unexpected author uid", "E1112", uids.get(2));
			assertEquals("Unexpected author uid", "u1111112", uids.get(3));
			
			List<String> issues = dspaceMap.get("local.bibliographicCitation.issue");
			assertNotNull("No issues found", issues);
			assertEquals("Unexpected number of issue numbers", 1, issues.size());
			assertEquals("Unexpected issue number value", "21", issues.get(0));
			
			List<String> titles = dspaceMap.get("dc.title");
			assertNotNull("No titles found", titles);
			assertEquals("Unexpected number of titles found", 1, titles.size());
			assertEquals("Unexpected title value", "Test Journal Title", titles.get(0));
			
			List<String> ariesPublications = dspaceMap.get("local.identifier.ariespublication");
			assertNotNull("No aries publications found", ariesPublications);
			assertEquals("Unexpected number of aries publications found", 1, ariesPublications.size());
			assertEquals("Unexpected aires publication value", "u1111111xPUB2", ariesPublications.get(0));
			
//			for (Entry<String, List<String>> entry : dspaceMap.entrySet()) {
//				LOGGER.info("Key: {}, Value: {}", entry.getKey(), entry.getValue());
//			}
		}
		catch (Exception e) {
			LOGGER.error("Exception retrieving map", e);
			fail("Exception retrieving map");
		}
	}
}
