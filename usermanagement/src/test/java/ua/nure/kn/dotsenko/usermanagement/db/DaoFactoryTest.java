package ua.nure.kn.dotsenko.usermanagement.db;

import static org.junit.Assert.*;

import org.junit.Test;

public class DaoFactoryTest {

	@Test
	public void testGetUserDAO() {
		try {
			DaoFactory daoFactory = DaoFactory.getInstance();
			assertNotNull("DaoFactory instance is null", daoFactory);
			UserDAO userDAO = daoFactory.getUserDAO();
			assertNotNull("UserDAO instance is null", userDAO);
		} catch (RuntimeException e) {
			e.printStackTrace();
			fail(e.toString());
		}
	}

}
