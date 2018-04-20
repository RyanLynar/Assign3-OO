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
		obj = dao.getItemsByID("d005").get(0);
		assertTrue(dao.modifyItem(obj));
	}

	@Test
	public void testCreateList() {
		assertTrue(!dao.createList(20).isEmpty());
	}

	@Test
	public void testGetItemsByID() {
		assertTrue(!dao.getItemsByID("d005").isEmpty());
	}

  }
