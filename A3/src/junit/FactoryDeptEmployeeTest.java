package junit;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import databaseaccess.DAODepartments;
import databaseaccess.DAODeptEmployee;
import databaseaccess.DatabaseAccess;
import factories.DepartmentsFactory;
import factories.DeptEmployeeFactory;

public class FactoryDeptEmployeeTest {
	DeptEmployeeFactory factory;
	ResultSet r;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		factory = new DeptEmployeeFactory();
		r = DatabaseAccess.getInstance().getConnection().prepareStatement("SELECT * FROM " + DAODeptEmployee.tName + ";")
				.executeQuery();
	}

	@Test
	public void testCreateListFromResultsResultSet() {
		ResultSet size = null;
		int x = 0;
		try {
			size = DatabaseAccess.getInstance().getConnection().prepareStatement("SELECT COUNT(*) FROM " + DAODeptEmployee.tName + ";")
					.executeQuery();
			size.first();
			x = size.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		assertEquals(factory.createListFromResults(r).size(), x);
	}

}
