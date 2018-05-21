package server.terminal.commands.selfRequests;

import server.terminal.TerminalCommand;
import users.User;
import server.terminal.TerminalCommands;

/**
 * Returns the updated user object if successful.
 * @author aliu
 *
 */
public class ChangeEmail extends TerminalCommand {

	public ChangeEmail(TerminalCommands terminal) {
		super(terminal, "String");
	}

	@Override
	public void execute(Object... elist) {
		String email = (String) elist[0];
		if (!((String) elist[0]).contains("@"))
			throw new IllegalArgumentException("Must be a valid email!");
		
		User user = new User(getObject().getUserID(),getObject().getUser());
		user.setEmail(email);
		getObject().getServer().getUserdao().update(user);
		user.setID(getObject().getSessionID());
		setOutput(user);
		getObject().getUser().setEmail(email);//Update Request handler after the server has finished handling database
	}

}
