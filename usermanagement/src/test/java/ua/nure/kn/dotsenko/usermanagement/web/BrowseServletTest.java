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
	
	@Test
	public void testEdit() {
		User user = new User(1000L, "John", "Doe", new Date());
		getMockUserDao().expectAndReturn("find", new Long(1000), user);
		addRequestParameter("editButton", "Edit");
		addRequestParameter("id", "1000");
		doPost();
		User userInSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
		assertNotNull("Could not find the user in session", userInSession);
		assertSame(user, userInSession);
	}

    @Test
    public void testEditWithNoOneSelected() {
        addRequestParameter("editButton", "Edit");
        doPost();
        assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("error"));
    }
    
    @Test
    public void testDelete() {
        User user = new User(1000L, "Gabe", "Newell", new Date());
        getMockUserDao().expectAndReturn("find", new Long(1000), user);
        getMockUserDao().expect("delete", user);
        getMockUserDao().expect("findAll");
        addRequestParameter("deleteButton", "Delete");
        addRequestParameter("id", "1000");
        doPost();
        assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("message"));
    }
    
    @Test
    public void testDeleteWithNoOneSelected() {
        addRequestParameter("deleteButton", "Delete");
        doPost();
        assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("error"));
    }
    
    @Test
    public void testDetails() {
		User user = new User(1000L, "Alexander", "Veselov", new Date());
		getMockUserDao().expectAndReturn("find", 1000L, user);
		addRequestParameter("detailsButton", "Details");
		addRequestParameter("id", "1000");
		doPost();
		User userInSession = (User) getWebMockObjectFactory().getMockSession().getAttribute("user");
		assertNotNull("Could not find the user in session", userInSession);
		assertSame(user, userInSession);
    }
    
    @Test
    public void testDetailsWithNoOneSelected() {
        addRequestParameter("detailsButton", "Details");
        doPost();
        assertNotNull(getWebMockObjectFactory().getMockRequest().getAttribute("error"));
    }

}
