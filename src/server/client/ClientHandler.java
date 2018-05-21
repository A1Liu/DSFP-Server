package server.client;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

import server.Server;


/**
 * Handler for socket connections to the server.
 * @author aliu
 *
 */
public class ClientHandler extends Thread {

	private ServerSocket serverSocket;
	private final Server server;
	private int port;
	private volatile boolean running;
	private ArrayList<RequestHandler> connections;
	
	public ClientHandler(Server server, int port) {
		this.server = server;
		this.port = port;
		running = false;
		this.setPriority(MIN_PRIORITY);
		connections = new ArrayList<RequestHandler>();
	}
	
	@Override
	public void start() {
		try {
			serverSocket = new ServerSocket( port );
			super.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	
	@Override
	public void run() {
		running = true;
		System.out.println("Listening for connections.");
		while (running) {
			try {
				RequestHandler requestHandler = new RequestHandler(server,serverSocket.accept());
				Thread requestThread = new Thread(requestHandler);
				requestThread.start();
				connections.add(requestHandler);
				System.out.println("Connection established!");
			} catch (IOException e) {
				if (running)
					System.out.println("Failed to connect. Retrying...");
			}
		}
	}
	
	/**
	 * Disconnects all of the requestHandlers.
	 */
	public void disconnectAll() {
		for (RHandler request : connections) {
			request.disconnect();
		}
		connections = new ArrayList<RequestHandler>();
	}
	
	/**
	 * Ends all of connections by force quitting them.
	 */
	public void endAll() {
		for (RequestHandler request : connections) {
			request.quit();
			//try{request.forceQuit();}catch (InterruptedException i) {}
		}
		connections = new ArrayList<RequestHandler>();
	}
	
	/**
	 * go offline.
	 */
	public void offline() {
		this.endAll();
		running = false;
		try {
			serverSocket.close();
		} catch (Exception e) {
			
		}
	}

}
