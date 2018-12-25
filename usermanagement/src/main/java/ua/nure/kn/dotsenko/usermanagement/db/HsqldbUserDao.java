package ua.nure.kn.dotsenko.usermanagement.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.LinkedList;

import ua.nure.kn.dotsenko.usermanagement.User;

class HsqldbUserDao implements UserDAO {

	private static final String INSERT_QUERY = "INSERT INTO USERS(firstname, lastname, dateofbirth) VALUES(?, ?, ?)";
	private static final String SELECT_ALL_QUERY = "SELECT id, firstname, lastname, dateofbirth FROM users";
	private static final String SELECT_USER_QUERY = "SELECT * FROM users WHERE id = ?";
	private static final String UPDATE_QUERY = "UPDATE users SET firstname = ?, lastname = ?, dateofbirth = ? WHERE id = ?";
	private static final String DELETE_QUERY = "DELETE FROM users WHERE id = ?";
	private static final String SELECT_USER_BY_NAMES = "SELECT * FROM users WHERE firstName = ? AND lastName = ?";
	private ConnectionFactory connectionFactory;
	
	public ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}

	public void setConnectionFactory(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public HsqldbUserDao(ConnectionFactory connectionFactory) {
		this.connectionFactory = connectionFactory;
	}

	public HsqldbUserDao() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User create(final User user) throws DatabaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
			statement.setString(1, user.getFirstName());
			statement.setString(2, user.getLastName());
			statement.setDate(3, new Date(user.getDateOfBirth().getTime()));

			int n = statement.executeUpdate();
			if (n != 1) {
				throw new DatabaseException("Number of inserted rows: " + n);
			}

			CallableStatement callableStatement = connection.prepareCall("call IDENTITY()");
			ResultSet keys = callableStatement.executeQuery();
			if (keys.next()) {
				user.setId(keys.getLong(1));
			}
			keys.close();
			callableStatement.close();
			statement.close();
			connection.close();
			return user;
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	public User find(final Long id) throws DatabaseException {
		User user = null;
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_QUERY);
			preparedStatement.setLong(1, id.longValue());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				throw new DatabaseException("No users with id=" + id);
			}
			user = new User();
			user.setId(new Long(resultSet.getLong(1)));
			user.setFirstName(resultSet.getString(2));
			user.setLastName(resultSet.getString(3));
			user.setDateOfBirth(resultSet.getDate(4));
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		return user;
	}

	@Override
	public void update(final User user) throws DatabaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getLastName());
			preparedStatement.setDate(3, new Date(user.getDateOfBirth().getTime()));
			preparedStatement.setLong(4, user.getId().longValue());

			int n = preparedStatement.executeUpdate();
			if (n != 1) {
				throw new DatabaseException("Number of updated rows: " + n);
			}

			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	public void delete(final User user) throws DatabaseException {
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
			preparedStatement.setLong(1, user.getId().longValue());

			int n = preparedStatement.executeUpdate();
			if (n != 1) {
				throw new DatabaseException("Number of deleted rows: " + n);
			}

			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	public Collection<User> findAll() throws DatabaseException {
		Collection<User> result = new LinkedList<User>(); 
		
		try {
			Connection connection = connectionFactory.createConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_ALL_QUERY);
			while(resultSet.next()) {
				User user = new User();
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));
				result.add(user);
				statement.close();
				connection.close();
			}
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		
		return result;
	}

	@Override
	public Collection<User> find(String firstName, String lastName) throws DatabaseException {
    Collection<User> result = new LinkedList<User>(); 
		
		try {
			Connection connection = connectionFactory.createConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_NAMES);
			preparedStatement.setString(1, firstName);
			preparedStatement.setString(2, lastName);
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()) {
				User user = new User();
				user.setId(new Long(resultSet.getLong(1)));
				user.setFirstName(resultSet.getString(2));
				user.setLastName(resultSet.getString(3));
				user.setDateOfBirth(resultSet.getDate(4));
				result.add(user);
				preparedStatement.close();
				connection.close();
			}
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
		
		return result;
	}

}
