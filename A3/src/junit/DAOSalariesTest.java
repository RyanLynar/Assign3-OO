package junit;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import databaseaccess.DAOSalaries;
import transferobj.Salaries;

public class DAOSalariesTest {
	DAOSalaries dao;
	Salaries obj;
	Date date;
	Long now;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		now = new Long(1524188049353L);
		date = new Date(now);
		dao = new DAOSalaries();
		
		obj = new Salaries();
		obj.setEmpNo(10003);
		obj.setfDate(date);
		obj.setSalary(1000);
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
		obj.setSalary(20000);
		assertTrue(dao.modifyItem(obj));
		obj.setSalary(1000);
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
