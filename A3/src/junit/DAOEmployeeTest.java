package junit;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import databaseaccess.DAOEmployee;
import databaseaccess.DAOSalaries;
import transferobj.Employee;
import transferobj.Salaries;

public class DAOEmployeeTest {
	DAOEmployee dao;
	Employee obj;
	Date date;
	Long now;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		now = new Long(1524188049353L);
		date = new Date(now);
		dao = new DAOEmployee();
		
		obj = new Employee();
		obj.setEmpBDate(date);
		obj.setEmpFName("first");
		obj.setEmpGender("M");
		obj.setEmpHDate(date);
		obj.setEmpLName("last");
		obj.setEmpNumber(10001);
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
		obj.setEmpLName("modify");
		assertTrue(dao.modifyItem(obj));
		obj.setEmpLName("last");
	}

//	@Test
//	public void testCreateList() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetItemsByID() {
//		fail("Not yet implemented");
//	}

}
