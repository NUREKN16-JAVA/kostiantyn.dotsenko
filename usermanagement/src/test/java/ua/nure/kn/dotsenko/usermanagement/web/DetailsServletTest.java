package ua.nure.kn.dotsenko.usermanagement.web;

import org.junit.Before;
import org.junit.Test;

public class DetailsServletTest extends MockServletTestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(DetailsServlet.class);
	}

	@Test
    public void testOkButton() {
        addRequestParameter("okButton", "Ok");
        doPost();
        assertNull(getWebMockObjectFactory().getMockRequest().getAttribute("error"));
    }

}
