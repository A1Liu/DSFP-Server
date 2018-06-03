package server.terminal.commands.otherUserRequests;

import server.terminal.TerminalCommand;
import server.terminal.TerminalCommands;

public class Search extends TerminalCommand {

	public Search(TerminalCommands terminal, String... strings) {
		super(terminal, strings);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute(Object... elist) {
		String searchKey = (String) elist[0];
		setOutput(this.getObject().getServer().getUserdao().search(searchKey));
	}

}
