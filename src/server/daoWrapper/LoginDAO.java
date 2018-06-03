package server.daoWrapper;

import dao.DAOFactory;
import dao.PassDAO;
import dao.UserDAO;
import users.User;

/**
 * Wrapper to make it easier to write log in command in terminal
 * @author aliu
 *
 */
public class LoginDAO {

	private UserDAO userdao;
	private PassDAO passdao;
	
	
	public LoginDAO(UserDAO userdao, PassDAO passdao) {
		this.userdao = userdao;
		this.passdao = passdao;
	}
	
	/**
	 * Login to server with new account. Adds user and password to the database
	 * @param user user to add
	 * @param password user's password
	 * @return Created user if successful. user.getID() returns the session id.
	 * @throws IllegalArgumentException if username or email is already taken
	 */
	public synchronized User newAcct(User user, String password) throws IllegalArgumentException {
		if (userdao.existEmail(user.getEmail()))  {
			throw new IllegalArgumentException("Email already taken.");
		}else if(userdao.existUsername(user.getUsername())) {
			throw new IllegalArgumentException("Username already taken.");
		} else {
			user = userdao.create(user);
			passdao.createPass(user.getID(), password);
		}
		return user;
	}
	
	public User login(Long SessionID) {
		return null;
	}
	
	/**
	 * Logs into server with username/email and password
	 * @param name username or email
	 * @param password password of the user
	 * @return complete user object if successful. user.getID() returns the sessionid.
	 * @throws IllegalArgumentException if the username or email or password are incorrect.
	 */
	public User login(String name, String password) throws IllegalArgumentException {
		User user = userdao.find(name);
		if (user == null)
			throw new IllegalArgumentException(name.indexOf('@') == -1 ? "Incorrect Username!" : "Incorrect Email!");
		if (passdao.checkPass(user.getID(), password)) {
			return user;
		} else {
			throw new IllegalArgumentException("Incorrect Password!");
		}
		
	}
	
	public UserDAO getUserDAO() {
		return userdao;
	}
	
}
