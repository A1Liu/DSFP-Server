package server.terminal.commands.selfRequests;

import server.terminal.TerminalCommand;
import server.terminal.TerminalCommands;

public class ChangePass extends TerminalCommand {

	public ChangePass(TerminalCommands terminal) {
		super(terminal, "String", "String");
	}

	@Override
	public void execute(Object... elist) {
		String oldPass = (String) elist[0];
		String newPass = (String) elist[1];
		Long id = getObject().getUserID();
		if (getObject().getServer().getPassdao().checkPass(id, oldPass)) {
			getObject().getServer().getPassdao().changePass(id, newPass);
			setOutput("Success: password changed.");
		} else {
			setOutput("Failed: old password was incorrect.");
		}
	}

}
