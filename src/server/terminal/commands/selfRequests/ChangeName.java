package server.terminal.commands.selfRequests;

import server.terminal.TerminalCommand;
import users.User;
import server.terminal.TerminalCommands;

public class ChangeName extends TerminalCommand {

	public ChangeName(TerminalCommands terminal) {
		super(terminal, "String");
	}

	@Override
	public void execute(Object... elist) {
		String name = (String) elist[0];
		if (name.split("[^\\-A-Za-z ]|(?<![a-zA-Z])[-]|[-](?![A-Za-z])").length > 1 || name.endsWith("-"))//Removes some badly formatted names, like those with numbers or special characters. Allows for hyphenated names.
			throw new IllegalArgumentException("Must somewhat resemble a name.");
		User user = new User(getObject().getUserID(),getObject().getUser());
		user.setName(name);
		getObject().getServer().getUserdao().update(user);
		user.setID(getObject().getSessionID());
		setOutput(user);
		getObject().getUser().setName(name);//Update Request handler after the server has finished handling database
	}

}
