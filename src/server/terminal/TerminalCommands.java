package server.terminal;

import commands.Command;
import commands.Commands;
import server.BaseRequestHandler;
import server.terminal.commands.*;
import server.terminal.commands.admin.*;
import server.terminal.commands.login.*;
import server.terminal.commands.selfRequests.*;

import static util.DataUtil.prepend;
import static util.IOUtil.readLines;

import java.io.IOException;

/**
 * Commands for the Terminal. Since the commands are in a hashtable separate from the tree, I can use this class as server logic for any client, socket or terminal based.
 * @author aliu
 *
 */
public class TerminalCommands extends Commands {
	
	private final static String[] GRAPH;
	private final BaseRequestHandler terminal;
	
	static {
		String[] a = new String[1];
		try {
			a = readLines("Properties/commands.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		GRAPH = a;
	}
	
	/**
	 * Constructor for terminal clients. Creates tree.
	 * @param terminal terminal used to access this object.
	 */
	public TerminalCommands(Terminal terminal) {
		addGraph(GRAPH);
		this.terminal = terminal;
		setup();
	}
	
	/**
	 * Constructor for general clients. Doesn't create a command tree.
	 * @param baseRequestHandler the request handler that uses this object.
	 */
	public TerminalCommands(BaseRequestHandler baseRequestHandler) {
		this.terminal = baseRequestHandler;
		setup();
	}
	
	/**
	 * Getter for requestHandler
	 * @return the requestHandler
	 */
	public BaseRequestHandler getTerminal() {
		return terminal;
	}

	/**
	 * Input handler for a logged-in user.
	 * @param input input array
	 * @return output of command
	 */
	public Object userInput(String[] input) {
		if (terminal.loggedIn()) {
			return this.input(prepend(input,null));
		} else {
			return this.input(input);
		}
	}
	
//	/**
//	 * Input handler for root-access user
//	 * @param input input array
//	 * @return output of command
//	 */
//	private Object rootInput(String[] input) {
//		if (terminal.isRoot()) {
//			return this.input(prepend(prepend(input,null),null));
//		} else {
//			return this.input(input);
//		}
//	}

//	public Object cmdLine(String input) {
//		try {
//		if (terminal.isRoot()) {
//			return rootInput(convertString(input)
//					);
//		} else {
//			return userInput(convertString(input));
//		} } catch (Exception e) {
//			
//			return e.getMessage();
//		}
//	}
	
	/**
	 * Gets a command object. Used by general clients.
	 * @param identifier id of command
	 * @return command
	 */
	public Command getCommand(int identifier) {
		return super.getCommand(identifier);
	}
	
	/**
	 * Checks to see if command is a TerminalCommand first before adding it to the list.
	 */
	@Override
	public void addCommand(Integer label, Command command) {
		if (command == null)
			throw new IllegalArgumentException("Command cannot be null!");
		
		if (command instanceof TerminalCommand) {
			super.addCommand(label, command);
		} else throw new IllegalArgumentException("TCommands only takes subclasses of TerminalC as commands!");
	}
	
	/**
	 * setup to add all commands.
	 */
	private void setup() {
		
		//basics
		addCommand(0,new LoginExistUser(this));//login with username and password
		addCommand(1,new UserLogout(this));//log out
		addCommand(2, new TerminalCommand(this){@Override public void execute(Object... elist) {getObject().quit();}});
		addCommand(3,new LoginNewUser(this));//new user
		
		//interact with own account
		addCommand(10,new Refresh(this));//refresh user information
		addCommand(11,new ChangeUsername(this));//change username
		addCommand(12,new ChangeEmail(this));//change email
		addCommand(13, new ChangeName(this)); //change name
		addCommand(14,new ChangePass(this));//change password
		addCommand(19, new ChangeInfo(this));//change information. used by non-terminal clients only.
		
		//interact with other users
		//addCommand(20,null);//search
		//addCommand(21,null);//get info on specified user
		//addCommand(22,null);//rate another user. takes arguments <username> <integer>. If currently viewing a user, then <username> field is optional
		//addCommand(23,null);//friend another user. takes arguments <username>. If currently viewing a user, then <username> field is optional
		
		//admin commands: 100-199
		
		//Login and basics
		addCommand(100,new AdminLogin(this));//root: login as root user
		addCommand(101,new StartServer(this));//start: start server
		addCommand(102,new StopServer(this));//stop: stop server
		//addCommand(103,null);//new: new terminal instance
		//addCommand(103,null);//
		//addCommand(100,null);//
		//addCommand(100,null);//
		//addCommand(100,null);//
		///addCommand(100,null);//
		//addCommand(100,null);//
		//addCommand(101,null);//
		//addCommand(90,null);
		//addCommand(90,null);
		addCommand(199,new AdminLogout(this));
	}
	
}
