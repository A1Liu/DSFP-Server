package server;

import misc.SimpleTester;
import sql.DAOFactory;
import sql.PassDAO;
import sql.RatingDAO;
import sql.UserDAO;
import users.User;

import static debug.Debug.*;

import java.sql.SQLException;

public class TestHarness {

//	/*/
	public static void main(String[] args) {
		Server server = new Server(1100);
		server.start();
		server.goOnline();
		//server.stopServer();
	}/**/
	/*/
	public static void main(String[] args) throws SQLException {
		DAOFactory dao = DAOFactory.getInstance("javabase.jdbc");
		RatingDAO ratings = dao.getRatingDAO();
		UserDAO users = dao.getUserDAO();
		PassDAO passwords = dao.getPassDAO();
		User first = new User((long) 1, null);
		User second = new User((long) 2, null);
//		ratings.createUser(user);
//		ratings.deleteUser(user);
//		ratings.getRating(user);
//		ratings.getRatingsFrom(user);
//		ratings.getRatingsTo(user);
//		ratings.rateUser(from, to, rating);
//		ratings.updateRating(from, to, newRating);
//		ratings.updateUser(user, newRating);
		sp(ratings.getRatingsTo(first));
		
		
	}/**/
	
	/*/
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