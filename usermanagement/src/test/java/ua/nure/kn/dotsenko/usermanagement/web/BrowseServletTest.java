package ua.nure.kn.dotsenko.usermanagement.web;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ua.nure.kn.dotsenko.usermanagement.User;

public class BrowseServletTest extends MockServletTestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(BrowseServlet.class);
	}

	@Test
	public void testBrowse() {
		User user = new User(1000L, "John", "Doe", new Date());
		List<User> list = Collections.singletonList(user);
		getMockUserDao().expectAndReturn("findAll", list);
		doGet();
		Collection<User> collection = (Collection<User>) getWebMockObjectFactory().getMockSession().getAttribute("users");
		assertNotNull("Could not find list of Users in session", collection);
		assertSame(list, collection);
	}

}
