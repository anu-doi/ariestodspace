package au.edu.anu.ariestodspace.staging.commands;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.ariestodspace.dspace.DSpacePersistenceManager;
import au.edu.anu.ariestodspace.staging.data.StagingPersistenceManager;

public class IdentifierMappingTest {
	private static Logger LOGGER = LoggerFactory.getLogger(IdentifierMappingTest.class);
	
	@BeforeClass
	public static void before() {
	}
	
	@AfterClass
	public static void after() {
		StagingPersistenceManager.getInstance().closeEntityManagerFactory();
		DSpacePersistenceManager.getInstance().closeEntityManagerFactory();
	}
	
//	@Ignore
	@Test
	public void test() {
		LoadIdentiferMappingsOption option = new LoadIdentiferMappingsOption();
		try {
			option.execute();
		}
		catch (Exception e) {
			LOGGER.error("Exception executing load identifier mappings", e);
			fail("Exception executing load identifier mappings option");
		}
	}
}
