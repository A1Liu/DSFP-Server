package server.terminal.commands.selfRequests;

import server.terminal.TerminalCommand;
import server.terminal.TerminalCommands;
import users.User;

public class ChangeInfo extends TerminalCommand {

	public ChangeInfo(TerminalCommands terminal) {
		super(terminal);
	}

	@Override
	public void execute(Object... elist) {
		User user = (User) elist[0];
		user = new User(getObject().getUserID(),user);
		getObject().getServer().getUserdao().update(user);
		user.setID(getObject().getSessionID());
		setOutput(user);
		getObject().setUser(user);//Update Request handler after the server has finished handling database
	}

}
