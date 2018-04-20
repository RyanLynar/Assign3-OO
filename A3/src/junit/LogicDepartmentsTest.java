package junit;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import databaseaccess.DAODepartments;
import databaseaccess.DAODeptManager;
import logicclasses.DepartmentsLogic;
import logicclasses.DeptEmployeeLogic;

public class LogicDepartmentsTest {
	DepartmentsLogic s;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		s = new DepartmentsLogic();
	}

	@Test
	public void testSalariesLogic() {
		assertNotNull(s = new DepartmentsLogic());
	}

	@Test
	public void testAdd() {
		assertTrue(s.add(new String[]{"d0001","Test"}));
		
	}

	@Test
	public void testRemove() {
		assertTrue(s.remove(new String[] {"d005", "Development"}));
	}

	@Test
	public void testModify() {
		DAODepartments d = new DAODepartments();
		d.getItemsByID("d004").get(0).setDeptName("testResult");
		assertTrue(s.modify(d.getItemsByID("d004").get(0).getValues()));
	}

	@Test
	public void testGetByID() {
		assertTrue(!s.getByID("d004").isEmpty());
	}

	@Test
	public void testGetObjects() {
		assertTrue(!s.getObjects(1).isEmpty());
	}
}
