package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import databaseaccess.DAOEmployee;
import logicclasses.EmployeeLogic;
import logicclasses.SalariesLogic;

public class LogicEmployeeTest {
	EmployeeLogic s;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}


	@Before
	public void setUp() throws Exception {
		s = new EmployeeLogic();
	}

	@Test
	public void testLogicEmployee() {
		assertNotNull(s = new EmployeeLogic());
	}

	@Test
	public void testAdd() {
		assertTrue(s.add(new String[]{"100080","1992-03-03","test","test","M","1997-08-03"}));
		
	}

	@Test
	public void testRemove() {
		assertTrue(s.remove(new String[] {"10007", "1957-05-23", "Tzvetan", "Zielinski", "F", "1989-02-10"}));
	}

	@Test
	public void testModify() {
		DAOEmployee d = new DAOEmployee();
		assertTrue(s.modify(d.getItemsByID(10004).get(0).getValues()));
	}

	@Test
	public void testGetByID() {
		assertTrue(!s.getByID(10004).isEmpty());
	}

	@Test
	public void testGetObjects() {
		assertTrue(!s.getObjects(10).isEmpty());
	}

}
