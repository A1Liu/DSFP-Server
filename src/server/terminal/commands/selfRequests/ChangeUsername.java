package server.terminal.commands.selfRequests;

import server.terminal.TerminalCommand;
import users.User;
import server.terminal.TerminalCommands;

public class ChangeUsername extends TerminalCommand {

	public ChangeUsername(TerminalCommands terminal) {
		super(terminal, "String");
	}

	@Override
	public void execute(Object... elist) {
		
		String username = (String) elist[0];
		if (((String) elist[0]).contains("@"))
			throw new IllegalArgumentException("Username can't be an email!");
		User user = new User(getObject().getUserID(),getObject().getUser());
		
		user.setUsername(username);
		getObject().getServer().getUserdao().update(user);
		user.setID(getObject().getSessionID());
		setOutput(user);
		getObject().getUser().setUsername(username);//Update Request handler after the server has finished handling database
		
	}

}
