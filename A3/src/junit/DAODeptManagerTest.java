package junit;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import databaseaccess.DAODeptManager;
import factories.DeptManagerFactory;
import factories.TransferFactoryCreator;
import transferobj.DeptManager;

public class DAODeptManagerTest {
	DAODeptManager dao;
	DeptManager obj;
	DeptManagerFactory df;
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
		df = (DeptManagerFactory) TransferFactoryCreator.createBuilder(DeptManager.class);
		
		obj = df.createFromInput(new String[] {"199991","d005",date.toString(),date.toString()});
	}

	@Test
	public void testAddItem() {
		assertTrue(dao.addItem(obj));
	}

	@Test
	public void testRemoveItem() {
		assertTrue(dao.removeItem(dao.getItemsByID("d006").get(0)));
	}

	@Test
	public void testModifyItem() {
		obj = dao.getItemsByID(110085).get(0);
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
