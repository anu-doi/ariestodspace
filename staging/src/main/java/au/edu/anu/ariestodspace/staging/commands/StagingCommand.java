package au.edu.anu.ariestodspace.staging.commands;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.Option;
import org.kohsuke.args4j.spi.SubCommand;
import org.kohsuke.args4j.spi.SubCommandHandler;
import org.kohsuke.args4j.spi.SubCommands;

/**
 * Base command used in the launcher
 * 
 * @author Genevieve Turner
 */
public class StagingCommand {
	
	@Argument(handler=SubCommandHandler.class, metaVar="<command> [options]", usage="Current commands are load-identifier, load-dspace, load-non-match, find-similar. Please use 'mapper <command> -h' for further information.")
	@SubCommands({
		@SubCommand(name=CommandUtil.LOAD_IDENTIFIER, impl=LoadIdentiferMappingsOption.class),
		@SubCommand(name=CommandUtil.MIGRATE, impl=MigrateDataOption.class)
	})
	StagingSubCommand cmd;
	
	@Option(name="-h", aliases="--help", usage="Displays this")
	private boolean help = false;

	/**
	 * Get the sub command
	 * 
	 * @return The sub command
	 */
	public StagingSubCommand getCmd() {
		return cmd;
	}

	/**
	 * Set the sub command
	 * 
	 * @param cmd The sub command
	 */
	public void setCmd(StagingSubCommand cmd) {
		this.cmd = cmd;
	}

	/**
	 * Indicate if help should be shown
	 * 
	 * @return true or false
	 */
	public boolean isHelp() {
		return help;
	}

	/**
	 * Set whether to show the help or not
	 * 
	 * @param help true or false
	 */
	public void setHelp(boolean help) {
		this.help = help;
	}
}
