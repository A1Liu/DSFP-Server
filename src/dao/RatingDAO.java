package dao;

import java.util.List;

import users.RatedUser;
import users.User;

/**
 * TODO: implement ratings table
 * @author aliu
 *
 */
public interface RatingDAO {

	/**
	 * Creates a user in the stats database
	 * @param user user to create
	 */
	public void createUser(User user);
	
	/**
	 * Sets a given user's rating
	 * @param user user to set
	 * @param newRating new rating of that user
	 */
	public void updateUser(User user, double newRating);
	
	/**
	 * deletes a user from the ratings table and stats table
	 * @param user user to be deleted
	 */
	public void deleteUser(User user);
	
	/**
	 * Creates a rating edge from one user to another, with a specified rating
	 * @param from
	 * @param to
	 * @param rating
	 */
	public void rateUser(User from, User to, int rating);
	
	/**
	 * Updates an existing rating edge from one user to another, with a specified rating
	 * @param from
	 * @param to
	 * @param newRating
	 */
	public void updateRating(User from, User to, int newRating);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public double getRating(User user);
	
	/**
	 * 
	 * @param user
	 * @return
	 */
	public List<RatedUser> getRatingsFrom(User user);
	
	/**
	 * Returns a list of ratings to a specified user
	 * @param user
	 * @return
	 */
	public List<RatedUser> getRatingsTo(User user);
	
	
	
}
