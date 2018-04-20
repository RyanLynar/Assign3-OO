package junit;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import databaseaccess.DAOTitles;
import transferobj.Salaries;
import transferobj.Titles;

public class DAOTitlesTest {
	DAOTitles dao;
	Titles obj;
	Date date;
	Long now;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		now = new Long(1524188049353L);
		date = new Date(now);
		dao = new DAOTitles();
		
		obj = new Titles();
		obj.setEmpNo(2000);
		obj.setfDate(date);
		obj.settDate(date);
		obj.setTitle("SomeTitle");
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
		obj.setTitle("SomeTitle2");
		assertTrue(dao.modifyItem(obj));
		obj.setTitle("SomeTitle");
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
