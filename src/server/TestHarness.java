package server;

import misc.SimpleTester;

public class TestHarness {

	/**/public static void main(String[] args) {
		Server server = new Server(1100);
		server.start();
		server.goOnline();
		//server.stopServer();
	}/*/
	
	public static void main(String[] args) {
		new Tester().run();
	}/**/
}

class Tester extends SimpleTester {

	@Override
	public Object execute(String name) {
//		String regex = "[^\\-A-Za-z ]|(?<![a-zA-Z])[-]|[-](?![A-Za-z])";
		return name.split("[^\\-A-Za-z ]|(?<![a-zA-Z])[-]|[-](?![A-Za-z])").length > 1 || name.endsWith("-");
	}
	
}