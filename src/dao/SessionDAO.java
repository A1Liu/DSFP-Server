package dao;

import users.User;

public interface SessionDAO {
	
	public long createSession(User user);//create a sessionID for a user
	
	public long createSession(long id);//create a sessionID for a user w/ SQL key id
	
	public long updateSession(long id);
	
	public void deleteSession(long id);
	
	public void deleteUser(long id);
	
	public void deleteUser(User user);
}
