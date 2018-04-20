package junit;

import static org.junit.Assert.*;
import java.sql.Date;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import databaseaccess.DAODeptEmployee;
import transferobj.DeptEmployee;

public class DAODeptEmployeeTest {
	DAODeptEmployee dao;
	DeptEmployee obj;
	Date date;
	Long now;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		now = new Long(1524188049353L);
		date = new Date(now);
		dao = new DAODeptEmployee();
		
		obj = new DeptEmployee();
		obj.setDeptID("d005");
		obj.setEmpID(10001);
		obj.setfDate(date);
		obj.settDate(date);
		
	}

	@Test
	public void testAddItem() {
		assertTrue(dao.addItem(obj));
	}

	@Test
	public void testRemoveItem() {
		assertTrue(dao.removeItem(obj));
	}

	@Test
	public void testModifyItem() {
		obj.setEmpID(10002);
		assertTrue(dao.modifyItem(obj));
		obj.setEmpID(10001);
	}

	@Test
	public void testCreateList() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetItemsByID() {
		fail("Not yet implemented");
	}

  }
