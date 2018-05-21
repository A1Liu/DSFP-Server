package server.terminal.commands.login;

import server.terminal.TerminalCommands;
import server.terminal.commands.selfRequests.Refresh;
import server.terminal.TerminalCommand;
import users.User;

public class LoginNewUser extends TerminalCommand {

	public LoginNewUser(TerminalCommands terminal) {
		super(terminal, "String", "String", "String", "String");//username, email, name, password
	}
	
	@Override
	public void execute(Object... elist) {
		if (!elist[0].toString().contains("@") && elist[1].toString().contains("@") && !elist[2].toString().contains("@")) {
			User user = getObject().getServer().getLoginDAO().newAcct(new User((String) elist[0],(String) elist[1],(String) elist[2]), (String) elist[3]);
			getObject().setUserID(user.getID());
			getObject().setUser(user);
			setOutput(new User(user));
			getObject().getCommands().getMove((String) null).execute();
			new Refresh(this.getObject().getCommands()).execute();
		} else setOutput("Incorrect input formatting!");
		
	}
	
	
}
