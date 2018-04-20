package junit;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import databaseaccess.DAODeptManager;
import logicclasses.DeptEmployeeLogic;
import logicclasses.DeptManagerLogic;

public class LogicDeptEmployeeTest {
	DeptEmployeeLogic s;
	@Before
	public void setUp() throws Exception {
		s = new DeptEmployeeLogic();
	}

	@Test
	public void testSalariesLogic() {
		assertNotNull(s = new DeptEmployeeLogic());
	}

	@Test
	public void testAdd() {
		assertTrue(s.add(new String[]{"10000","10","1999-10-13","1999-10-13"}));
		
	}

	@Test
	public void testRemove() {
		assertTrue(s.remove(new String[] {"10008", "d007", "1999-10-10", "2018-04-19"}));
	}

	@Test
	public void testModify() {
		DAODeptManager d = new DAODeptManager();
		d.getItemsByID(10003).get(0).setFromDate(Date.valueOf("1999-12-31"));
		assertTrue(s.modify(d.getItemsByID(10003).get(0).getValues()));
	}

	@Test
	public void testGetByID() {
		assertTrue(!s.getByID(10003).isEmpty());
	}

	@Test
	public void testGetObjects() {
		assertTrue(!s.getObjects(1).isEmpty());
	}

}
