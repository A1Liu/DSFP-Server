package server;


import server.terminal.TerminalCommands;
import users.User;

/**
 * A basic RequestHandler to handle the communication between the client and server.
 * @author aliu
 *
 */
public abstract class BaseRequestHandler {

	private TerminalCommands terminal;
	private volatile boolean running;
	private final Server server;
	private User user;
	private User viewing;
	private boolean root;
	private Long sessionID;
	private Long userID;
	
	public BaseRequestHandler(Server server) {
		terminal = new TerminalCommands(this);
		this.server = server;
		root = false;
		viewing = null;
	}
	
	protected void setCommands(TerminalCommands commands) {
		this.terminal = commands;
	}
	
	public TerminalCommands getCommands() {
		return terminal;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Checks if currently logged in
	 * @return true if logged in
	 */
	public boolean loggedIn() {
		return !(user == null || user.getID() == null);
	}
	
	public void setRoot(boolean root) {//TODO: change this to use 'move to' terminal command
		this.root = root;
	}
	
	/**
	 * checks if currently a server admin or 'root user'
	 * @return true if an admin
	 */
	public boolean isRoot() {
		return root;
	}
	
	/**
	 * getter user
	 * @return
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * start the requestHandler
	 */
	public void startReqHandler() {
		running = true;
	}
	
	/**
	 * quit the requestHandler
	 */
	public void quit() {
		running = false;
	}
	
	/**
	 * start the server. Used by admins.
	 */
	protected void startServer() {
		server.goOnline();
	}
	
	/**
	 * stop the server. Used by admins.
	 */
	protected void stopServer() {
		server.goOffline();
	}
	
	/**
	 * gets the server reference.
	 * @return server
	 */
	public Server getServer() {
		return server;
	}
	
	/**
	 * checks whether this should still be running
	 * @return true if it's running
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * log out.
	 */
	public void logout() {
		user = null;
	}

	public Long getSessionID() {
		return sessionID;
	}

	public void setSessionID(Long sessionID) {
		this.sessionID = sessionID;
	}

	public Long getUserID() {
		return userID;
	}

	public void setUserID(Long userID) {
		this.userID = userID;
	}

	public User getViewing() {
		return viewing;
	}

	public void setViewing(User viewing) {
		this.viewing = viewing;
	}
}
