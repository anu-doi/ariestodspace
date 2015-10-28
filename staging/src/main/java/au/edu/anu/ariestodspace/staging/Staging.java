package au.edu.anu.ariestodspace.staging;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import au.edu.anu.ariestodspace.aries.ARIESPersistenceManager;
import au.edu.anu.ariestodspace.dspace.DSpacePersistenceManager;
import au.edu.anu.ariestodspace.staging.commands.CommandUtil;
import au.edu.anu.ariestodspace.staging.commands.StagingCommand;
import au.edu.anu.ariestodspace.staging.commands.StagingCommandException;
import au.edu.anu.ariestodspace.staging.data.StagingPersistenceManager;

/**
 * Launch class
 * 
 * @author Genevieve Turner
 *
 */
public class Staging {
	private static final Logger LOGGER = LoggerFactory.getLogger(Staging.class);
	/**
	 * Main method
	 * 
	 * @param args The arguments
	 */
	public static void main(String[] args) {
		StagingCommand command = new StagingCommand();
		
		CmdLineParser parser = new CmdLineParser(command);
		
		try {
			parser.parseArgument(args);
			if (command.getCmd() != null) {
				command.getCmd().execute();
			}
			else {
				parser.printSingleLineUsage(System.out);
				System.out.println("");
				parser.printUsage(System.out);
			}
		}
		catch (CmdLineException e) {
			System.err.println(e.getMessage());
			printCommandExecution(args);
			System.err.println("No command arguments found please use -h for options");
		}
		catch (StagingCommandException e) {
			printCommandExecution(args);
			System.err.println("No command arguments found please use -h for options");
		}
		finally {
			DSpacePersistenceManager.getInstance().closeEntityManagerFactory();
			StagingPersistenceManager.getInstance().closeEntityManagerFactory();
			ARIESPersistenceManager.getInstance().closeEntityManagerFactory();
		}
		LOGGER.info("Command Complete");
	}
	
	/**
	 * Print the command line execution
	 * 
	 * @param args The arguments
	 */
	public static void printCommandExecution(String[] args) {
		System.err.print("Executed Command: " + CommandUtil.MAPPER + " ");
		for (int i = 0; i < args.length; i++) {
			System.err.print(args[i] + " ");
		}
		System.err.println("");
	}
}
