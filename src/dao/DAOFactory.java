package dao;

/**
 * The interface that specifies all the types of DAO's that need to be implemented in the DAOFactories
 * @author Alyer
 *
 */
public interface DAOFactory {

	/**
	 * UserDAO for handing simple User data
	 * @return a UserDAO
	 */
	UserDAO getUserDAO();
	
	/**
	 * PassDAO for handling user passwords and salts
	 * @return a PassDAO
	 */
	PassDAO getPassDAO();
	
	/**
	 * Session for handling user passwords and salts
	 * @return a SessionDAO
	 */
	SessionDAO getSessionDAO();
	
	/**
	 * FriendDAO for handling user friends/following
	 * @return a FriendDAO object
	 */
	//FriendDAO getFriendDAO();
	
	/**
	 * RatingDAO for handing MEOWMEOWBEENZ-specific rating data. Handles ratings table and ratings edge table
	 * @return a RatingDAO object
	 */
	RatingDAO getRatingDAO();
	
}
