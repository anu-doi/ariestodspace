package au.edu.anu.ariestodspace.staging.commands;

import org.kohsuke.args4j.CmdLineParser;

/**
 * Utility class for the command line arguments
 * 
 * @author Genevieve Turner
 *
 */
public class CommandUtil {
	public static final String MAPPER = "mapper";
	public static final String LOAD_IDENTIFIER = "load-identifier";
	public static final String LOAD_NON_MATCHES = "load-non-match";
	public static final String FIND_SIMILAR = "find-similar";
	public static final String MIGRATE = "migrate";

	/**
	 * Print the usage command
	 * 
	 * @param command The command being executed
	 * @param clazz The class associated with the command
	 */
	public static void printUsage(String command, Class<?> clazz) {
		try {
			CmdLineParser parser = new CmdLineParser(clazz.newInstance());
			//System.out.print(CommandUtil.STORE + " " + CommandUtil.PUB);
			System.out.print(CommandUtil.MAPPER + " " + command);
			parser.printSingleLineUsage(System.out);
			System.out.println("");
			parser.printUsage(System.out);
		}
		catch (IllegalAccessException e) {
			System.err.println("Exception accessing class " + clazz.getName() + ". " + e.getMessage());
		}
		catch (InstantiationException e) {
			System.err.println("Exception instantiationg class " + clazz.getName() + ". " + e.getMessage());
		}
	}
}
