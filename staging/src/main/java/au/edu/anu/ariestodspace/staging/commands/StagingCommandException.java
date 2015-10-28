package au.edu.anu.ariestodspace.staging.commands;

/**
 * Staging Commands Exception class
 * 
 * @author Genevieve Turner
 *
 */
public class StagingCommandException extends Exception {
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * 
	 * @param message The message
	 */
	public StagingCommandException(String message) {
		super(message);
	}
}
