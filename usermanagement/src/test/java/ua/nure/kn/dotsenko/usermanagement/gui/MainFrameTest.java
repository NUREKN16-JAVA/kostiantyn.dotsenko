package ua.nure.kn.dotsenko.usermanagement.gui;

import java.awt.Component;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mockobjects.dynamic.Mock;

import junit.extensions.jfcunit.JFCTestCase;
import junit.extensions.jfcunit.JFCTestHelper;
import junit.extensions.jfcunit.TestHelper;
import junit.extensions.jfcunit.eventdata.MouseEventData;
import junit.extensions.jfcunit.finder.NamedComponentFinder;
import ua.nure.kn.dotsenko.usermanagement.User;
import ua.nure.kn.dotsenko.usermanagement.db.DaoFactory;

public class MainFrameTest extends JFCTestCase {

	private Mock mockUserDao;
	private List<User> users;
	private MainFrame mainFrame; 

	@Before
	protected void setUp() throws Exception {
		super.setUp();
		//Properties properties = new Properties();
		//properties.setProperty("dao.factory", MockDaoFactory.class.getName());
		//DaoFactory.init(properties);
		//mockUserDao = ((MockDaoFactory) DaoFactory.getInstance()).getUserDAO();
		
		//User expectedUser = new User(1000L, "George", "Bush", new Date());
		//users = Collections.singletonList(expectedUser);
		//mockUserDao.expectAndReturn("findAll", users);
		setHelper(new JFCTestHelper());
		mainFrame = new MainFrame();
		mainFrame.setVisible(true);
	}
	
	@After
	protected void tearDown() throws Exception {
		//mockUserDao.verify();
		mainFrame.setVisible(false);
		getHelper();
		TestHelper.cleanUp(this);
		super.tearDown();
	}
	
	private Component find(Class<?> componentClass, String name) {
		NamedComponentFinder finder;
		finder = new NamedComponentFinder(componentClass, name);
		finder.setWait(0);
		Component component = finder.find(mainFrame, 0);
		assertNotNull("Could not find component '" + name + "'", component);
		return component;		
	}
	
	@Test
	public void testBrowseControls() {
		find(JPanel.class, "browsePanel");
		JTable table = (JTable) find(JTable.class, "userTable");
		
		//assertEquals(3, table.getColumnCount());
		//assertEquals("id", table.getColumnName(0)); //localize
		//assertEquals("First name", table.getColumnName(1));
		//assertEquals("Last name", table.getColumnName(2));
		
		find(JButton.class, "addButton");
		find(JButton.class, "editButton");
		find(JButton.class, "deleteButton");
		find(JButton.class, "detailsButton");
	}
	
	@Test
	public void testAddUser() {
		JButton addButton = (JButton) find(JButton.class, "addButton");
		getHelper().enterClickAndLeave(new MouseEventData(this, addButton));
		
		find(JPanel.class, "addPanel");
		find(JTextField.class, "firstNameField");
		find(JTextField.class, "lastNameField");
		find(JTextField.class, "dateOfBirthField");
		JButton okButton = (JButton) find(JButton.class, "okButton");
		find(JButton.class, "cancelButton");
		/*getHelper().enterClickAndLeave(new MouseEventData(this, okButton));
		find(JPanel.class, "browsePanel");*/
	}
}
