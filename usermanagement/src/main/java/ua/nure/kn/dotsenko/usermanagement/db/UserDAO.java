package ua.nure.kn.dotsenko.usermanagement.db;

import java.util.Collection;

import ua.nure.kn.dotsenko.usermanagement.User;

public interface UserDAO {

	/**
	 * Add user to database
	 * @param user with null id
	 * @return user with auto generated id
	 *         throw DatabaseException if any error occurs with DB 
	 */
	
	User create(final User user) throws DatabaseException;
	
	User find(final Long id) throws DatabaseException;
		
	Collection<User> findAll() throws DatabaseException;
	
	Collection<User> find(String firstName, String lastName) throws DatabaseException;
	
    void update(final User user) throws DatabaseException; 
    
    void delete(final User user) throws DatabaseException;
    
    void setConnectionFactory(ConnectionFactory cf);
}
