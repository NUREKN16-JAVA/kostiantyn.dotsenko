package ua.nure.kn.dotsenko.usermanagement.web;

import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mockobjects.dynamic.Mock;
import com.mockrunner.servlet.BasicServletTestCaseAdapter;

import ua.nure.kn.dotsenko.usermanagement.db.DaoFactory;
import ua.nure.kn.dotsenko.usermanagement.db.MockDaoFactory;
import ua.nure.kn.dotsenko.usermanagement.db.MockUserDao;

public abstract class MockServletTestCase extends BasicServletTestCaseAdapter {

	Mock mockUserDao;

	public Mock getMockUserDao() {
		return mockUserDao;
	}

	public void setMockUserDao(Mock mockUserDao) {
		this.mockUserDao = mockUserDao;
	}
	
	@Before
	protected void setUp() throws Exception {
		super.setUp();
		Properties properties = new Properties();
		properties.setProperty("dao.factory", MockDaoFactory.class.getName());
        DaoFactory.init(properties);
        setMockUserDao(((MockDaoFactory) DaoFactory.getInstance()).getMockUserDao());
	}

	@After
	protected void tearDown() throws Exception {
		super.tearDown();
		getMockUserDao().verify();
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
