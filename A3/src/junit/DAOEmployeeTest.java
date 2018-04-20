package junit;

import static org.junit.Assert.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import databaseaccess.DAOEmployee;
import databaseaccess.DatabaseAccess;
import transferobj.Employee;


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
	}

	@Test
	public void testAddItem() {
		boolean temp;
		temp = dao.addItem(obj);
		dao.removeItem(obj);
		
		assertTrue(temp);
	}

	@Test
	public void testRemoveItem() {
		boolean temp;
		int tempNum = 0;

		try {
			PreparedStatement s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("SELECT MAX(" 
							+ DAOEmployee.COLUMNS[0] 
									+ ") FROM " + DAOEmployee.tName 
									+ ";");
			ResultSet res = s.executeQuery();
			res.first();
			tempNum = res.getInt(1)+1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		obj.setEmpNumber(tempNum);
		dao.addItem(obj);
		temp = dao.removeItem(obj);

		assertTrue(temp);
	}

	@Test
	public void testModifyItem() {
		boolean temp;
		int tempNum = 0;

		try {
			PreparedStatement s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("SELECT MAX(" 
							+ DAOEmployee.COLUMNS[0] 
									+ ") FROM " + DAOEmployee.tName 
									+ ";");
			ResultSet res = s.executeQuery();
			res.first();
			tempNum = res.getInt(1)+1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

		obj.setEmpNumber(tempNum);
		dao.addItem(obj);
		temp = dao.modifyItem(obj);

		assertTrue(temp);
	}

	@Test
	public void testCreateList() {
		assertTrue(!dao.createList(20).isEmpty());
	}

	@Test
	public void testGetItemsByID() {
		assertTrue(!dao.getItemsByID(10001).isEmpty());
	}

}
