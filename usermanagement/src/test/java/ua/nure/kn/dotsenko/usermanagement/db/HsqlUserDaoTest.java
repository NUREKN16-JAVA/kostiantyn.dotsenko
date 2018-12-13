package ua.nure.kn.dotsenko.usermanagement.db;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.junit.Before;
import org.junit.Test;

import ua.nure.kn.dotsenko.usermanagement.User;

public class HsqlUserDaoTest extends DatabaseTestCase {
	
	private static final Long USER_ID = 1000L;
	private static final String USER_FIRSTNAME = "Bill";
	private static final String USER_LASTNAME = "Gates";
	private static final String UPDATED_USER_FIRSTNAME = "Gabe";
	private static final String UPDATED_USER_LASTNAME = "Newell";
	private static final String UPDATED_DATE_OF_BIRTH = "1962-11-03";
	private HsqldbUserDao dao = new HsqldbUserDao();
	private ConnectionFactory connectionFactory;

	@Before
	public void setUp() throws Exception {
		super.setUp();
		dao.setConnectionFactory(connectionFactory);
	}

	@Test
	public void testCreate() throws DatabaseException {
		User user = new User();
		user.setFirstName("Ivan");
		user.setLastName("Ivanov");
		user.setDateOfBirth(new Date());
		assertNull(user.getId());
		
		User userCreated = dao.create(user);
		assertNotNull(userCreated);
		assertNotNull(userCreated.getId());
		assertEquals(user.getFirstName(), userCreated.getFirstName());
		assertEquals(user.getLastName(), userCreated.getLastName());
	}
	
	@Test
	public void testFindAll() throws DatabaseException {
		try {
			Collection<User> collection = dao.findAll();
			assertNotNull("Collection is null", collection);
			assertEquals("Collection size.", 2, collection.size());
		} catch (DatabaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail(e.toString());
		}
	}
	
	@Test
	public void testFind() throws DatabaseException {
		User user = dao.find(USER_ID);
		assertNotNull("User variable equals to null.", user);
		assertEquals("User firstnames are not equal.", USER_FIRSTNAME, user.getFirstName());
		assertEquals("User lastnames are not equal.", USER_LASTNAME, user.getLastName());
	}
	
	@Test
	public void testUpdate() throws DatabaseException {
		User user = dao.find(USER_ID);
		user.setFirstName(UPDATED_USER_FIRSTNAME);
		user.setLastName(UPDATED_USER_LASTNAME);
		
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
		Date newDateOfBirth = new Date();
		try {
			newDateOfBirth=ft.parse(UPDATED_DATE_OF_BIRTH);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 
		user.setDateOfBirth(newDateOfBirth);
		
		dao.update(user);

		User updatedUser = dao.find(USER_ID);
		assertEquals(UPDATED_USER_FIRSTNAME, updatedUser.getFirstName());
		assertEquals(UPDATED_USER_LASTNAME, updatedUser.getLastName());
		assertEquals(newDateOfBirth, updatedUser.getDateOfBirth());
	}
	
	@Test
	public void testDelete() {
		User user = new User();
		user.setId(USER_ID);
		try {
			dao.delete(user);
			dao.find(USER_ID);
			fail("No exception is thrown");
		} catch (DatabaseException e) {
			assertEquals("No users with id=" + USER_ID, e.getMessage());
		}
	}

	@Override
	protected IDatabaseConnection getConnection() throws Exception {
		connectionFactory = DaoFactory.getInstance().getConnectionFactory();
		return new DatabaseConnection(connectionFactory.createConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
        IDataSet dataSet = new XmlDataSet(getClass().getClassLoader()
        		.getResourceAsStream("usersDataSet.xml"));
		return dataSet;
	}

}
