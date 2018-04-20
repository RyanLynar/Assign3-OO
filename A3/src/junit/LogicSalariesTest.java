package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import databaseaccess.DAOSalaries;
import logicclasses.SalariesLogic;

public class LogicSalariesTest {
	SalariesLogic s;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		s = new SalariesLogic();
	}

	@Test
	public void testSalariesLogic() {
		assertNotNull(s = new SalariesLogic());
	}

	@Test
	public void testAdd() {
		assertTrue(s.add(new String[]{"10000","10","1999-10-13","1999-10-13"}));
		
	}

	@Test
	public void testRemove() {
		assertTrue(s.remove(new String[] {"10002","65828","1996-08-03","1997-08-03"}));
	}

	@Test
	public void testModify() {
		DAOSalaries d = new DAOSalaries();
		d.getItemsByID(10004).get(0).setSalary(10);
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
