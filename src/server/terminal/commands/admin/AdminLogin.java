package server.terminal.commands.admin;

import server.terminal.TerminalCommand;
import server.terminal.TerminalCommands;

public class AdminLogin extends TerminalCommand {

	public AdminLogin(TerminalCommands terminal, String... strings) {
		super(terminal, strings);
		// 
	}

	@Override
	public void execute(Object... elist) {
		getObject().getCommands().getMove(null,null).execute();
		setOutput("Logged in as server admin.");
	}

}
