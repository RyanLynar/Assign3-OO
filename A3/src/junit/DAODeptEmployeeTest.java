package junit;

import static org.junit.Assert.*;
import java.sql.Date;
import java.util.ArrayList;

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
		obj.setEmpID(66666);
		obj.setfDate(date);
		obj.settDate(date);
	}

	@Test
	public void testAddItem() {
		//Cascading error :/
		assertTrue(dao.addItem(obj));
	}

	@Test
	public void testRemoveItem() {
		assertTrue(dao.removeItem(obj));
	}

	@Test
	public void testModifyItem() {
		obj.setEmpID(10002);
		obj.setDeptID("d007");
		obj.setfDate(Date.valueOf("1999-10-10"));
		assertTrue(dao.modifyItem(obj));
	}

	@Test
	public void testCreateList() {
		ArrayList<DeptEmployee> list = dao.createList(50);
		assertTrue(!list.isEmpty());
	}

	@Test
	public void testGetItemsByID() {

		ArrayList<DeptEmployee> list = dao.getItemsByID(10002);
		assertTrue(!list.isEmpty());
	}

  }
