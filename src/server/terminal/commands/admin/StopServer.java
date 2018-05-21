package server.terminal.commands.admin;

import server.terminal.TerminalCommands;
import server.terminal.TerminalCommand;

public class StopServer extends TerminalCommand {

	public StopServer(TerminalCommands terminal) {
		super(terminal);
	}

	@Override
	public void execute(Object... elist) {
		getObject().getServer().goOffline();	
		setOutput("Server Stopped.");
	}
}

