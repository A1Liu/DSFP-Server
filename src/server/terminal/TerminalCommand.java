package server.terminal;

import commands.ObjParamC;
import server.BaseRequestHandler;

/**
 * Command to be used in the TerminalCommands class. All commands used by TerminalCommands should be subclasses of this.
 * @author aliu
 *
 */
public abstract class TerminalCommand extends ObjParamC<BaseRequestHandler> {

	public TerminalCommand(TerminalCommands terminal, String... strings) {
		super(terminal.getTerminal(), strings);
	}
}
