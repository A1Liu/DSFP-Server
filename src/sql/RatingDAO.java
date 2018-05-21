package sql;

import java.util.List;

import users.RatedUser;
import users.User;

/**
 * TODO: start and finish this
 * 
 * @author aliu
 *
 */
public class RatingDAO implements dao.RatingDAO {

	private DAOFactory daoFactory;
	
	public RatingDAO(DAOFactory daoFactory) {
		this.setDaoFactory(daoFactory);
	}

	public DAOFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DAOFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	@Override
	public void createUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user, double newRating) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rateUser(User from, User to, int rating) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateRating(User from, User to, int newRating) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getRating(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<RatedUser> getRatingsFrom(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RatedUser> getRatingsTo(User user) {
		// TODO Auto-generated method stub
		return null;
	}

}
