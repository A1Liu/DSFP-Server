package server.client;

import java.io.IOException;
import java.net.Socket;
import connection.Packet;
import connection.serverPackets.ServerPacket;
import server.Server;
import server.terminal.TerminalCommand;

/**
 * This holds a lot of the server communication logic.
 * @author aliu
 *
 */
public class RequestHandler extends RHandler {

	private boolean isAdmin;
	
	protected RequestHandler(Server server, Socket socket) {
		super(server, socket);
		isAdmin = false;
	}

	@Override
	public void execute() throws InterruptedException, Exception {
		this.startReqHandler();
		while(isRunning()) {
			Object input = getInputStream().readObject();
			if (input instanceof Packet) {
				System.out.println("Received Packet");
				Packet packet = (Packet) input;
				if (packet.getTag() > 99) {//They're trying to use an admin command
					if (isAdmin) {
						
					} else {
						sendOutput(-1, "Client doesn't have sufficient permissions to use that command.");
						//send error message: not authorized to use that command
					}
				} else {//not trying to use admin command. Add more if statements to check for stuff like "are they logged in, are they allowed to use that command, etc."
					if (packet.getTag() < 10) {
						executeCommand(packet.getTag(),packet.getData());
					} else if (this.getUser() != null) {
						executeCommand(packet.getTag(),packet.getData());
					} else {
						sendOutput(-1, "Client doesn't have sufficient permissions to use that command.");
					}
				}
			} else {//Not talking to a client that we recognize
				disconnect();
				quit();
			}
		}
	}
	
	/**
	 * This method assumes that permissions have been met, and simply executes the command.
	 * @param tag
	 * @param data
	 * @throws IOException
	 */
	private void executeCommand(int tag, Object... data) throws IOException {
		if (tag == -1) {//-1 is the tag for error messages from the server
			
		}
		TerminalCommand command = (TerminalCommand) getCommands().getCommand(tag);
		if (command.checkParams(data)) {
			try {
				command.execute(data);
				sendOutput(tag,command.getOutput());
			} catch (Exception e) {
				sendOutput(-1,e.getMessage());
			}
		} else {
			sendOutput(-1,"Client Request was incorrectly formatted.");
		}
	}
	
	private void sendOutput(int tag, Object... o) throws IOException {
		getOutputStream().writeObject(new ServerPacket(tag,o));
		getOutputStream().flush();
	}

}
