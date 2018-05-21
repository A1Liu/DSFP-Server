package server.terminal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

import commands.CommandException;
import server.BaseRequestHandler;
import server.Server;

/**
 * Terminal class. Essentially a wrapper around the terminal commands, allowing for easy continuous use by calling its run method.
 * @author aliu
 *
 */
public class Terminal extends BaseRequestHandler implements Runnable {

	private final BufferedReader cmdLine;
	
	public Terminal(Server server, Reader input) {
		super(server);
		cmdLine = new BufferedReader(input);
		setCommands(new TerminalCommands(this));
	}
	
	public void run() {
		startReqHandler();
		while (isRunning()) {
			try {
				Object print = getCommands().input(cmdLine.readLine());
				if (print != null) {
					System.out.println(print.toString());
				}
			} catch (IOException e) {
				quit();
				e.printStackTrace();
			} catch (CommandException e) {
				System.out.println(e.toString());
			}
		}
	}
	
}
