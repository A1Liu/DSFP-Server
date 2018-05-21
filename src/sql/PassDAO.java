package sql;

import static sql.DAOUtil.prepareStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DAOException;

public class PassDAO implements dao.PassDAO {//use MD5 (?) for password sql statements

	private static final String  SQL_EXIST_ID = 
			"SELECT id FROM Passwords WHERE password = MD5(?)";
	private static final String  SQL_GET_PASSWORD_HASH = 
			"SELECT password FROM Passwords WHERE id = ?";
	private static final String  SQL_GET_SALT =
			"SELECT salt FROM Passwords WHERE id = ?";
	private static final String  SQL_INSERT = 
			"INSERT INTO Passwords (id, salt, password) VALUES (?, ?, MD5(?))";
	private static final String  SQL_DELETE = 
			"DELETE FROM Passwords WHERE id = ?";
	private static final String  SQL_UPDATE = 
			"UPDATE Passwords SET password = MD5(?) WHERE id = ?";
	private static final String SQL_HASH = 
			"SELECT MD5(?)";
	
    private DAOFactory daoFactory;
    
    /**
     * Construct a Password DAO for the given DAOFactory. Package private so that it can be constructed
     * inside the DAO package only.
     * @param daoFactory The DAOFactory to construct this Password DAO for.
     */
    PassDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

	@Override
	public void createPass(Long id, String pass) throws IllegalArgumentException, DAOException {
		if (id == null) throw new IllegalArgumentException("ID can't be null.");
		int salt = (int) (Math.random()*1000);
		Object[] values = {
			id,
			salt,
			(salt + pass)
		};
		executeVoid(SQL_INSERT, "Creating password failed, no rows affected.", values);
	}

	@Override
	public void deletePass(Long id) throws IllegalArgumentException, DAOException {
		if (id == null) throw new IllegalArgumentException("ID can't be null.");
		Object[] values = {id};
		executeVoid(SQL_DELETE, "Deleting password failed, no rows affected.", values);
	}

	@Override
	public int getSalt(Long id) throws DAOException {
		Object[] values = {
				id
		};
		
		try (
		            Connection connection = daoFactory.getConnection();
		            PreparedStatement statement = prepareStatement(connection, SQL_GET_SALT, false, values);
		            ResultSet resultSet = statement.executeQuery();
		        ) {
			  		if (resultSet.next()) {
			  			return resultSet.getInt("salt");
			  		} else return -1;
		        } catch (SQLException e) {
		            throw new DAOException(e);
		        }
	}
	
	@Override
	public void changePass(Long id, String newPass) throws IllegalArgumentException, DAOException {	
		
		int salt = this.getSalt(id);
		
		Object[] values = {
			(salt+newPass),
			id
		};
		
		executeVoid(SQL_UPDATE, "Updating password failed, no rows affected.", values);
	}

	@Override
	public boolean existID(Long id) throws DAOException {
		Object[] values = {id};
		return executeBool(SQL_EXIST_ID, values);
	}

	@Override
	public String getPassHash(Long id) throws DAOException {
		Object[] values = {
				id
		};
		
		try (
		            Connection connection = daoFactory.getConnection();
		            PreparedStatement statement = prepareStatement(connection, SQL_GET_PASSWORD_HASH, false, values);
		            ResultSet resultSet = statement.executeQuery();
		        ) {
			  		if (resultSet.next()) {
			  			return resultSet.getString("password");
			  		} else return null;
		        } catch (SQLException e) {
		            throw new DAOException(e);
		        }
	}
	
	@Override
	public String getHash(String pass) throws DAOException {
		Object[] values = {
				pass
		};
		
		try (
		            Connection connection = daoFactory.getConnection();
		            PreparedStatement statement = prepareStatement(connection, SQL_HASH, false, values);
		            ResultSet resultSet = statement.executeQuery();
		        ) {
			  		if (resultSet.next()) {
			  			return resultSet.getString(1);
			  		} else return null;
		        } catch (SQLException e) {
		            throw new DAOException(e);
		        }
	}
	
	@Override
	public boolean checkPass(Long id, String pass) throws DAOException {
		int salt = getSalt(id);
		return getPassHash(id).equals(getHash(salt+pass));
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
	
	/**
	 * executes an SQL statement that returns a boolean value
	 * @param sql the sql statement to execute
	 * @param values the values to add to the prepared statement
	 * @return the appropriate boolean for the sql statement
	 * @throws DAOException if something is wrong at the database level
	 */
	private boolean executeBool(String sql, Object... values) throws DAOException {
		
		boolean bool = false;
		
		try (
		            Connection connection = daoFactory.getConnection();
		            PreparedStatement statement = prepareStatement(connection, sql, false, values);
		            ResultSet resultSet = statement.executeQuery();
		        ) {
			  		bool = resultSet.next();
		        } catch (SQLException e) {
		            throw new DAOException(e);
		        }
		return bool;
	}

}
