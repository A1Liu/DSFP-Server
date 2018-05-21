package dao;

/**
 * all the methods that need to be implemented by a PassDAO
 * @author Alyer
 *
 */
public interface PassDAO {
    
    /**
     * Create a password entry for the given id and password in the database. The user ID must not be null, otherwise it will throw
     * IllegalArgumentException.
     * @param user The user to be created in the database.
     * @throws IllegalArgumentException If the ID is null.
     * @throws DAOException If something fails at database level.
     */
    public void createPass(Long id, String pass) throws IllegalArgumentException, DAOException;
    
    /**
     * delete the given user entry in the database. The user ID must not be null, otherwise it will throw
     * IllegalArgumentException.
     * @param user The user to be created in the database.
     * @throws IllegalArgumentException If the user ID is null.
     * @throws DAOException If something fails at database level.
     */
    public void deletePass(Long id) throws IllegalArgumentException, DAOException;
    
    /**
     * Returns the salt of the given id
     * @param id id of the user whose salt is being retrieved
     * @return the salt of the user
     * @throws DAOException If something fails at the database level.
     */
    public int getSalt(Long id) throws DAOException;
    
    /**
     * Update the password of a user. After deleting, the DAO will set the ID of the given
     * user to null.
     * @param id The id to be deleted from the database.
     * @throws DAOException If something fails at database level.
     * @throws IllegalArgumentException If newPass is null or empty or if id is null
     */
    public void changePass(Long id, String newPass) throws IllegalArgumentException, DAOException;

    /**
     * Returns true if the given ID has an associated password
     * @param id The id which is to be checked in the database.
     * @return True if the given id exists in the database.
     * @throws DAOException If something fails at database level.
     */
    public boolean existID(Long id) throws DAOException;
    
    /**
     * returns the password of the given ID
     * @param id The id whose password is being checked
     * @return The password of the id
     * @throws DAOException if something fails at the database level.
     */
    public String getPassHash(Long id) throws DAOException;
    
    /**
     * Returns the MD5 Hash of a password
     * @param pass
     * @return a String version of the hexadecimal hash of a string
     * @throws DAOException If something fails at the database level.
     */
    public String getHash(String pass) throws DAOException;
    
    /**
     * checks to see if pass is the password of user with ID id
     * @param id the id of the user we're checking
     * @param pass the password we're guessing
     * @return True if the passwords match
     * @throws DAOException if something fails at the database level
     */
    public boolean checkPass(Long id, String pass) throws DAOException;
}
