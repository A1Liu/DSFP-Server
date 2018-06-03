package sql;

import static sql.DAOUtil.prepareStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DAOException;
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
	
	private static final String SQL_CREATE_USER = 
			"INSERT INTO stats (userid, rating) VALUES (? , 1)";
	private static final String SQL_UPDATE_USER = 
			"UPDATE stats SET rating = ? WHERE userid = ?";
	private static final String SQL_DELETE_USER = 
			"DELETE FROM stats WHERE userid = ?";
	private static final String SQL_RATE_USER = 
			"INSERT INTO ratings (userid1, userid2, rating) VALUES (?, ?, ?)";
	private static final String SQL_UPDATE_RATING = 
			"UPDATE ratings SET rating=? WHERE userid1 = ? AND userid2 = ?";
	private static final String SQL_GET_USER_RATING = 
			"SELECT rating FROM stats WHERE userid = ?";
	private static final String SQL_RATINGS_FROM = 
			"SELECT userid2, rating FROM ratings WHERE userid1 = ?";
	private static final String SQL_RATINGS_TO = 
			"SELECT userid1, rating FROM ratings WHERE userid2 = ?";
	private static final String SQL_RATING_EXIST = //TODO: use this to clear up the rate user method
			"select * from ratings where userid1 = ? AND userid2 = ?";
	
	
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
	public synchronized void createUser(User user) {
		Object[] values = {
				user.getID()
			};
			
		executeVoid(SQL_CREATE_USER, "Creating user failed, no rows affected.", values);
	}

	@Override
	public synchronized void updateUser(User user, double newRating) {
		Object[] values = {
				newRating,
				user.getID()
			};
			
		executeVoid(SQL_UPDATE_USER, "Updating user failed, no rows affected.", values);
		
	}

	@Override
	public synchronized void deleteUser(User user) {
		Object[] values = {
				user.getID()
			};
			
		executeVoid(SQL_DELETE_USER, "Deleting user failed, no rows affected.", values);
	}

	@Override
	public synchronized void rateUser(User from, User to, int rating) {
		Object[] values = {
				from.getID(),
				to.getID(),
				rating
			};
		
		
		
		executeVoid(SQL_RATE_USER, "Creating rating failed, no rows affected.", values);
	}

	@Override
	public synchronized void updateRating(User from, User to, int newRating) {
		Object[] values = {
				newRating,
				from.getID(),
				to.getID()
			};
			
		executeVoid(SQL_UPDATE_RATING, "Updating rating failed, no rows affected.", values);
	}

	@Override
	public double getRating(User user) throws SQLException {
		Object[] values = {
				user.getID()	
			};
		
		try (
	            Connection connection = daoFactory.getConnection();
	            PreparedStatement statement = prepareStatement(connection, SQL_GET_USER_RATING, false, values);
        ) {
			ResultSet results = statement.executeQuery();
			
			if (results.next()) {
				return results.getDouble("rating");
			} else {
				return -1;
			}
				
        } catch (SQLException e) {
            throw new DAOException(e);
        }
//		ResultSet results = executeQuery(SQL_GET_USER_RATING, values);
//		return results.getDouble("rating");
	}

	@Override
	public List<RatedUser> getRatingsFrom(User user) throws SQLException {
		Object[] values = {
				user.getID()	
			};
		ArrayList<RatedUser> users = new ArrayList<RatedUser>();
		
		try (
	            Connection connection = daoFactory.getConnection();
	            PreparedStatement statement = prepareStatement(connection, SQL_RATINGS_FROM, false, values);
        ) {
			ResultSet results = statement.executeQuery();
			
			
			while (results.next()) {
				long id = results.getLong("userid2");
				int rating = results.getInt("rating");
				users.add(new RatedUser(id, rating));
			}
		} catch (SQLException e) {
            throw new DAOException(e);
        }
		return users;
	}

	@Override
	public List<RatedUser> getRatingsTo(User user) throws SQLException {
		
		Object[] values = {
				user.getID()	
			};
		ArrayList<RatedUser> users = new ArrayList<RatedUser>();
		
		try (
	            Connection connection = daoFactory.getConnection();
	            PreparedStatement statement = prepareStatement(connection, SQL_RATINGS_TO, false, values);
        ) {
			ResultSet results = statement.executeQuery();
			
			
			while (results.next()) {
				long id = results.getLong("userid1");
				int rating = results.getInt("rating");
				users.add(new RatedUser(id, rating));
			}
		} catch (SQLException e) {
            throw new DAOException(e);
        }
		return users;
	}
	
	/**
	 * execute sql statement that doesn't return anything 
	 * @param sql the statement to execute
	 * @param errorStatement statement to put in the DAO exception error statement
	 * @param values values to add to the prepared statement
	 * @throws DAOException if something goes wrong at the database level
	 */
	private void executeVoid(String sql, String errorStatement, Object... values) throws DAOException {
		  try (
		            Connection connection = daoFactory.getConnection();
		            PreparedStatement statement = prepareStatement(connection, sql, false, values);
		            
		        ) {
					int affectedRows = statement.executeUpdate();
			        if (affectedRows == 0) {
			       		throw new DAOException(errorStatement);
			        }
		        } catch (SQLException e) {
		            throw new DAOException(e);
		        }
	}

}
