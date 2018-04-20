package junit;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import databaseaccess.DAODeptManager;
import databaseaccess.DAOSalaries;
import logicclasses.DeptManagerLogic;
import logicclasses.SalariesLogic;

public class LogicDeptManagerTest {
	DeptManagerLogic s;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		s = new DeptManagerLogic();
	}

	@Test
	public void testSalariesLogic() {
		assertNotNull(s = new DeptManagerLogic());
	}

	@Test
	public void testAdd() {
		assertTrue(s.add(new String[]{"10000","10","1999-10-13","1999-10-13"}));
		
	}

	@Test
	public void testRemove() {
		assertTrue(s.remove(new String[] {"110085", "d002", "1985-01-01", "1989-12-17"}));
	}

	@Test
	public void testModify() {
		DAODeptManager d = new DAODeptManager();
		d.getItemsByID(110183).get(0).setFromDate(Date.valueOf("1999-12-31"));
		assertTrue(s.modify(d.getItemsByID(110183).get(0).getValues()));
	}

	@Test
	public void testGetByID() {
		assertTrue(!s.getByID(110183).isEmpty());
	}

	@Test
	public void testGetObjects() {
		assertTrue(!s.getObjects(1).isEmpty());
	}

}
