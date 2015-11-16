package au.edu.anu.ariestodspace.aries.annotation;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.ariestodspace.aries.ARIESPersistenceManager;
import au.edu.anu.ariestodspace.aries.ResearchOutputsData1;

public class SomeTest {
	Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private static EntityManagerFactory emf;

	@BeforeClass
	public static void before() {
		emf = ARIESPersistenceManager.getInstance().getEntityManagerFactory();
	}
	
	@AfterClass
	public static void after() {
		ARIESPersistenceManager.getInstance().closeEntityManagerFactory();
	}

	@Test
	public void testResearchOutput() {

		EntityManager em = emf.createEntityManager();

//		Query query = em.createQuery("from ResearchOutputsData1 WHERE chrOutput6Code = 'U3488905xPUB3766'");
//		Query query = em.createQuery("from ResearchOutputsData1 WHERE chrOutput6Code = 'u5202662xPUB101'");
//		Query query = em.createQuery("from ResearchOutputsData1 WHERE chrOutput6Code = 'U4217927xPUB760'"); // does what expected with characters
		
		Query query = em.createQuery("from ResearchOutputsData1 WHERE chrOutput6Code = 'u9204672xPUB137'");
		ResearchOutputsData1 data1 = (ResearchOutputsData1)query.getSingleResult();

		ARIESParser parser = new ARIESParser();
		try {
			Map<String, List<String>> values = parser.getDSpaceValues(data1);
			for (Entry<String, List<String>> entry : values.entrySet()) {
				LOGGER.info("Key: {}, Values: {}", entry.getKey(), entry.getValue());
			}
		}
		catch (IllegalAccessException | InvocationTargetException e) {
			LOGGER.error("Exception parsing values", e);
		}
		
	}
}
