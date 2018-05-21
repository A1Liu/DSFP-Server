package server.terminal.commands.admin;

import server.terminal.TerminalCommands;
import server.terminal.TerminalCommand;

public class StartServer extends TerminalCommand {

	public StartServer(TerminalCommands terminal) {
		super(terminal);
		// 
	}

	@Override
	public void execute(Object... elist) {
		getObject().getServer().goOnline();
		setOutput("Server Started!");
	}
}
