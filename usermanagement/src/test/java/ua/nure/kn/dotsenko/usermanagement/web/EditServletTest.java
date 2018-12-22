package ua.nure.kn.dotsenko.usermanagement.web;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import ua.nure.kn.dotsenko.usermanagement.User;

public class EditServletTest extends MockServletTestCase {

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		createServlet(EditServlet.class);
	}

	@Test
	public void testEdit() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		
		String dateFormatted = sdf.format(date);
		
		try {
			date = sdf.parse(dateFormatted);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		User user = new User(1000L, "John", "Doe", date);
		getMockUserDao().expect("update", user);
		
		addRequestParameter("id", "1000");
		addRequestParameter("firstName", "John");
		addRequestParameter("lastName", "Doe");
		addRequestParameter("date", DateFormat.getDateInstance().format(date));
		addRequestParameter("okButton", "Ok");
		doPost();
	}

}
