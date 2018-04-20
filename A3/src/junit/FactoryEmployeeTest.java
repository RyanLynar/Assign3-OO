package junit;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import databaseaccess.DAODeptManager;
import databaseaccess.DAOEmployee;
import databaseaccess.DatabaseAccess;
import factories.DeptManagerFactory;
import factories.EmployeeFactory;

public class FactoryEmployeeTest {
	EmployeeFactory factory;
	ResultSet r;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		factory = new EmployeeFactory();
		r = DatabaseAccess.getInstance().getConnection().prepareStatement("SELECT * FROM " + DAOEmployee.tName + ";")
				.executeQuery();
	}

	@Test
	public void testCreateListFromResultsResultSet() {
		ResultSet size = null;
		int x = 0;
		try {
			size = DatabaseAccess.getInstance().getConnection().prepareStatement("SELECT COUNT(*) FROM " + DAOEmployee.tName + ";")
					.executeQuery();
			size.first();
			x = size.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		assertEquals(factory.createListFromResults(r).size(), x);	
		}

}
