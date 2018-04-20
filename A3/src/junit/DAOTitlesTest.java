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
import databaseaccess.DAOTitles;
import databaseaccess.DatabaseAccess;
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
	public void setUp() {
		now = new Long(1524188049353L);
		date = new Date(now);
		dao = new DAOTitles();
		
		obj = new Titles();
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
		int tempNum = 0;
		
		try {
			PreparedStatement s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("SELECT MAX(" 
							+ DAOTitles.COLUMNS[0] 
									+ ") FROM " + DAOTitles.tName 
									+ ";");
			ResultSet res = s.executeQuery();
			res.first();
			tempNum = res.getInt(1)+1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		obj.setEmpNo(tempNum);
		dao.addItem(obj);
		assertTrue(dao.removeItem(obj));
	}

	@Test
	public void testModifyItem() {
		boolean temp;
		int tempNum = 0;

		try {
			PreparedStatement s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("SELECT MAX(" 
							+ DAOTitles.COLUMNS[0] 
									+ ") FROM " + DAOTitles.tName 
									+ ";");
			ResultSet res = s.executeQuery();
			res.first();
			tempNum = res.getInt(1)+1;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		obj.setEmpNo(tempNum);
		dao.addItem(obj);
		temp = dao.modifyItem(obj);

		assertTrue(temp);
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
