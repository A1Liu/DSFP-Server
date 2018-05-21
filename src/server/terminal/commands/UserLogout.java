package server.terminal.commands;

import server.terminal.*;
import server.terminal.TerminalCommand;

public class UserLogout extends TerminalCommand {

	public UserLogout(TerminalCommands terminal) {
		super(terminal);
	}

	@Override
	public void execute(Object... elist) {
		if (getObject().loggedIn()) {
			String username = getObject().getUser().getUsername();
			getObject().logout();
			getObject().getCommands().getMove().execute();;
			setOutput("User '" + username + "' logged out.");
		} else
			setOutput("Not logged in!");
	}

}
