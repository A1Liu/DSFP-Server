package server.terminal.commands.login;

import server.terminal.TerminalCommand;
import users.User;
import server.terminal.TerminalCommands;
import server.terminal.commands.selfRequests.Refresh;

public class LoginExistUser extends TerminalCommand {

	public LoginExistUser(TerminalCommands terminal) {
		super(terminal, "String", "String");
	}

	@Override
	public void execute(Object... elist) {
		User user = getObject().getServer().getLoginDAO().login((String) elist[0], (String) elist[1]);
		getObject().setUserID(user.getID());
		getObject().setUser(user);
		setOutput(new User(user));
		getObject().getCommands().getMove((String) null).execute();
		new Refresh(this.getObject().getCommands()).execute();
	}

}
