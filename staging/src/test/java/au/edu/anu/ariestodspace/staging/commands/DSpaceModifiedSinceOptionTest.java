package au.edu.anu.ariestodspace.staging.commands;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.ariestodspace.aries.ARIESPersistenceManager;
import au.edu.anu.ariestodspace.dspace.DSpacePersistenceManager;
import au.edu.anu.ariestodspace.staging.data.StagingPersistenceManager;

public class DSpaceModifiedSinceOptionTest {
	private static Logger LOGGER = LoggerFactory.getLogger(DSpaceModifiedSinceOptionTest.class);

	@AfterClass
	public static void after() {
		StagingPersistenceManager.getInstance().closeEntityManagerFactory();
		DSpacePersistenceManager.getInstance().closeEntityManagerFactory();
		ARIESPersistenceManager.getInstance().closeEntityManagerFactory();
	}
	
	@Test
	public void test() {
		DSpaceModifiedSinceOption option = new DSpaceModifiedSinceOption();
		try {
			option.execute();
		}
		catch (StagingCommandException e) {
			LOGGER.error("Exception executing command", e);
			fail("Exception executing command");
		}
	}
}
