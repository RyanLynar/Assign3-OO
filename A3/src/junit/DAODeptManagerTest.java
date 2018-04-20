package junit;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import databaseaccess.DAODeptManager;
import transferobj.DeptManager;

public class DAODeptManagerTest {
	DAODeptManager dao;
	DeptManager obj;
	Date date;
	Long now;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		now = new Long(1524188049353L);
		date = new Date(now);
		dao = new DAODeptManager();
	}

	@Test
	public void testAddItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testModifyItem() {
		fail("Not yet implemented");
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
