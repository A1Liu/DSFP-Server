package server;

import java.io.InputStreamReader;
import java.sql.SQLException;

import server.client.ClientHandler;
import server.daoWrapper.LoginDAO;
import server.terminal.Terminal;
import sql.DAOFactory;
import sql.PassDAO;
import sql.UserDAO;

/**
 * Server object. Acts as the 'hub' thread; Connects clients to database, manages the other threads, etc.
 * @author aliu
 *
 */
public class Server extends Thread {

	private DAOFactory database;
	private LoginDAO logindao;
	private UserDAO userdao;
	private PassDAO passdao;
	private Terminal terminal;
	private ClientHandler clientHandler;
	
	public Server(int port) {
		clientHandler = new ClientHandler(this, port);
		database = DAOFactory.getInstance("javabase.jdbc");
		userdao = database.getUserDAO();
		passdao = database.getPassDAO();
		logindao = new LoginDAO(userdao, passdao);
		
	}
	
	/**
	 * starts the server!
	 */
	public synchronized void start() {
		try {database.getConnection();} catch (SQLException e) {System.out.println("Couldn't connect to database!");}
		this.run();
	}

	@Override
	public void run() {
		terminal = new Terminal(this, new InputStreamReader(System.in));
		Thread terminalThread = new Thread(terminal);
		terminal.setRoot(true);
		terminalThread.start();
	}
	
	protected Server(ClientHandler threadHandler) {
		this.clientHandler = threadHandler;
	}
    
    public synchronized final void goOnline() {
    	clientHandler.start();
    }
    
    public synchronized final void goOffline() {
    	clientHandler.endAll();
    	try {
    	clientHandler.offline();
    	} catch (Exception e) {
    		
    	}
    }
	
	public synchronized void stopServer() {
		terminal.quit();
		goOffline();
	}
	
	public LoginDAO getLoginDAO() {
		return logindao;
	}

	public UserDAO getUserdao() {
		return userdao;
	}

	public void setUserdao(UserDAO userdao) {
		this.userdao = userdao;
	}

	public PassDAO getPassdao() {
		return passdao;
	}

	public void setPassdao(PassDAO passdao) {
		this.passdao = passdao;
	}
	
}
