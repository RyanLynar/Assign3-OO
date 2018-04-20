package junit;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import databaseaccess.DAODepartments;
import factories.DepartmentsFactory;
import factories.TransferFactoryCreator;
import transferobj.Departments;

public class DAODepartmentsTest {
	DAODepartments dao;
	Departments obj;
	DepartmentsFactory df;
	String key;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		dao= new DAODepartments();
		df= (DepartmentsFactory) TransferFactoryCreator.createBuilder(Departments.class);
		int firstKey;
		obj = df.createFromInput(new String[]{"d100","t"});
	}

	@Test
	public void testAddItem() {		
		assertTrue(dao.addItem(obj));
	}

	@Test
	public void testRemoveItem() {
		assertTrue(dao.removeItem(dao.getItemsByID("d001").get(0)));
	}

	@Test
	public void testModifyItem() {
		obj = dao.getItemsByID("d002").get(0);
		obj.setDeptName("Test2");
		assertTrue(dao.modifyItem(obj));
	}
}
