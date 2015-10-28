package au.edu.anu.ariestodspace.staging.commands;

/**
 * Abstract class for the sub commands
 * 
 * @author Genevieve Turner
 *
 */
public abstract class StagingSubCommand {
	/**
	 * Execute the option command
	 * 
	 * @throws StagingCommandException
	 */
	abstract public void execute() throws StagingCommandException;
}
