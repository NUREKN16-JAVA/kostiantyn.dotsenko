package ua.nure.kn.dotsenko.usermanagement;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestUser {

	private static final String FULL_NAME_ETALON = "Ivan, Ivanov";
	private static final String LAST_NAME = "Ivanov";
	private static final String FIRST_NAME = "Ivan";
	
	// Тест актуальный на дату 13.11.2018
	private static final int CURRENT_YEAR = 2018;
	
	private static final int YEAR_OF_BIRTH = 1999;
	private User user;
	
	// Тест Возраста 1 - для случая, где день рождения уже прошёл, но месяца совпадают

	private static final int ETALONE_AGE_1 = CURRENT_YEAR - YEAR_OF_BIRTH;
	private static final int DAY_OF_BIRTH_1 = 10;
	private static final int MONTH_OF_BIRTH_1 = 10;
	
	@Test
	public void testGetAge1() {
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_1, DAY_OF_BIRTH_1);
		user.setDateOfBirth(calendar.getTime());
		
		assertEquals(ETALONE_AGE_1, user.getAge());
	}
	
	// Тест Возраста 2 - для случая, когда день рождения совпадает с текущим днём

	private static final int ETALONE_AGE_2 = CURRENT_YEAR - YEAR_OF_BIRTH;
	private static final int DAY_OF_BIRTH_2 = 13;
	private static final int MONTH_OF_BIRTH_2 = 10;
		
	@Test
	public void testGetAge2() {
			
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_2, DAY_OF_BIRTH_2);
		user.setDateOfBirth(calendar.getTime());
			
		assertEquals(ETALONE_AGE_2, user.getAge());
	}
	
	// Тест Возраста 3 - для случая, когда день рождения завтра

	private static final int ETALONE_AGE_3 = CURRENT_YEAR - YEAR_OF_BIRTH - 1;
	private static final int DAY_OF_BIRTH_3 = 14;
	private static final int MONTH_OF_BIRTH_3 = 10;
				
	@Test
	public void testGetAge3() {
					
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_3, DAY_OF_BIRTH_3);
		user.setDateOfBirth(calendar.getTime());
					
		assertEquals(ETALONE_AGE_3, user.getAge());
	}
		
	// Тест Возраста 4 - для случая, когда день рождения 1-го января

	private static final int ETALONE_AGE_4 = CURRENT_YEAR - YEAR_OF_BIRTH;
	private static final int DAY_OF_BIRTH_4 = 1;
	private static final int MONTH_OF_BIRTH_4 = 0;
						
	@Test
	public void testGetAge4() {
							
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_4, DAY_OF_BIRTH_4);
		user.setDateOfBirth(calendar.getTime());
							
		assertEquals(ETALONE_AGE_4, user.getAge());
	}
		
	// Тест Возраста 5 - для случая, когда день рождения 31-го декабря

	private static final int ETALONE_AGE_5 = CURRENT_YEAR - YEAR_OF_BIRTH - 1;
	private static final int DAY_OF_BIRTH_5 = 31;
	private static final int MONTH_OF_BIRTH_5 = 11;
								
	@Test
	public void testGetAge5() {
									
		Calendar calendar = Calendar.getInstance();
		calendar.set(YEAR_OF_BIRTH, MONTH_OF_BIRTH_5, DAY_OF_BIRTH_5);
		user.setDateOfBirth(calendar.getTime());
									
		assertEquals(ETALONE_AGE_5, user.getAge());
	}
		
	
		
	@Before
	public void setUp() throws Exception {
		user = new User();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetFullName() {
	user.setFirstName(FIRST_NAME);
  	user.setLastName(LAST_NAME);
     assertEquals(FULL_NAME_ETALON, (String)user.getFullName());
	}

}
