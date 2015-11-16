package au.edu.anu.ariestodspace.staging.commands;

import static org.junit.Assert.fail;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Ignore;
import org.junit.Test;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.ariestodspace.aries.ARIESPersistenceManager;
import au.edu.anu.ariestodspace.dspace.DSpacePersistenceManager;
import au.edu.anu.ariestodspace.staging.data.StagingPersistenceManager;

public class MigrateDataOptionTest {
	private static Logger LOGGER = LoggerFactory.getLogger(MigrateDataOptionTest.class);
	
	@AfterClass
	public static void after() {
		StagingPersistenceManager.getInstance().closeEntityManagerFactory();
		DSpacePersistenceManager.getInstance().closeEntityManagerFactory();
		ARIESPersistenceManager.getInstance().closeEntityManagerFactory();
	}
	
	@Ignore
	@Test
	public void ariesIdentifierTest() {
		StagingCommand command = new StagingCommand();
		CmdLineParser parser = new CmdLineParser(command);
		try {
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","f5625xPUB10596","u4721027xPUB69");
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","u4005981xPUB54");
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","u4005981xPUB54","-r");
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","u4005981xPUB54","u9511635xPUB769");
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","f2965xPUB1147","f2965xPUB1141");
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","f2965xPUB1147");
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","u9204672xPUB471");
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","u9406909xPUB695");
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","u9910377xPUB286","u4002919xPUB473","u4756716xPUB278","u5036708xPUB23","u5141460xPUB60","u5665070xPUB4","u9910377xPUB297","u9910377xPUB298","U3594520xPUB464","u4039210xPUB202","u4279067xPUB1118","u5530201xPUB107","u5530201xPUB108","u5530201xPUB111","u5530201xPUB112");


//			parser.parseArgument(CommandUtil.MIGRATE,"-i","u9912193xPUB374");
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","u9912193xPUB46","u9912193xPUB94","u9912852xPUB1","u9912193xPUB79","u9912193xPUB395","u9912193xPUB390","u9912193xPUB374","u9912193xPUB311","u9912193xPUB245");
			
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","u9910377xPUB298");

			// some have degress in text
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","u9912193xPUB6","u9912193xPUB63","u9912193xPUB75","u9912193xPUB94","u9913207xPUB3","u9912193xPUB46","u9912193xPUB395");

//			parser.parseArgument(CommandUtil.MIGRATE,"-i","u9910377xPUB298", "u9912193xPUB75");
			// has degrees in chrApplicationDescription
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","u9912193xPUB75");
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","u5455391xPUB3");
			// desc with math characters
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","f2965xPUB1039");

//			parser.parseArgument(CommandUtil.MIGRATE,"-i","u4056230xPUB314");
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","u4053284xPUB2");
			
			//18F-florbetaben Aβ imaging in mild cognitive impairment
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","u4056230xPUB269");
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","f5625xPUB1113");

			parser.parseArgument(CommandUtil.MIGRATE,"-i","MigratedxPub17332");
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","u4326120xPUB620");
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","MigratedxPub13204");
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","u3923986xPUB228");
			
			// Testing filename with ']' character
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","a240288xPUB3");
			
			
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","a383154xPUB1090");
			
			
			//Special characters test
//			parser.parseArgument(CommandUtil.MIGRATE,"-i","u4056230xPUB269","u4005981xPUB690","f5625xPUB4560","MigratedxPub15844","u4155331xPUB33","MigratedxPub13204","u4085724xPUB67","u9406909xPUB572","u5455391xPUB3");
			
			if (command.getCmd() != null) {
				command.getCmd().execute();
			}
		}
		catch (CmdLineException | StagingCommandException e) {
			LOGGER.error("Error parsing arguments", e);
			fail("Error parsing arguments");
		}
	}

	@Ignore
	@Test
	public void fileTest() {
		StagingCommand command = new StagingCommand();
		CmdLineParser parser = new CmdLineParser(command);
		try {
			parser.parseArgument(CommandUtil.MIGRATE,"-f","C:/WorkSpace/Testing/dspace/ariestodspace/testariesids.txt","-c","Test Collection");
			if (command.getCmd() != null) {
				command.getCmd().execute();
			}
		}
		catch (CmdLineException | StagingCommandException e) {
			LOGGER.error("Error parsing arguments", e);
			fail("Error parsing arguments");
		}
	}
	
	@Ignore
	@Test
	public void allTest() {

		StagingCommand command = new StagingCommand();
		CmdLineParser parser = new CmdLineParser(command);
		try {
			parser.parseArgument(CommandUtil.MIGRATE);
			if (command.getCmd() != null) {
				command.getCmd().execute();
			}
		}
		catch (CmdLineException | StagingCommandException e) {
			LOGGER.error("Error parsing arguments", e);
			fail("Error parsing arguments");
		}
	}

	@Ignore
	@Test
	public void dateTimeTest() {
		StagingCommand command = new StagingCommand();
		CmdLineParser parser = new CmdLineParser(command);
		try {
			parser.parseArgument(CommandUtil.MIGRATE,"-l");
			if (command.getCmd() != null) {
				command.getCmd().execute();
			}
		}
		catch (CmdLineException | StagingCommandException e) {
			LOGGER.error("Error parsing arguments", e);
			fail("Error parsing arguments");
		}
	}
	
	@Ignore
	@Test
	public void counterTest() {
		StagingCommand command = new StagingCommand();
		CmdLineParser parser = new CmdLineParser(command);
		Date startDate = new Date();
		try {
			parser.parseArgument(CommandUtil.MIGRATE,"-s","198","-e","199");
			if (command.getCmd() != null) {
				command.getCmd().execute();
			}
		}
		catch (CmdLineException | StagingCommandException e) {
			LOGGER.error("Error parsing arguments", e);
			fail("Error parsing arguments");
		}
		Date endDate = new Date();
		long diff = endDate.getTime() - startDate.getTime();
		
	    long mins = TimeUnit.MILLISECONDS.toMinutes(diff);
	    long secs = TimeUnit.MILLISECONDS.toSeconds(diff) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(diff));
	    
	    LOGGER.info("Counter Test took {} mins and {} secs to run", mins, secs);
	}
}
