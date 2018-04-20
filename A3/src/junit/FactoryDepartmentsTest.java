package junit;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import databaseaccess.DAODepartments;
import databaseaccess.DatabaseAccess;
import factories.DepartmentsFactory;

public class FactoryDepartmentsTest {
	DepartmentsFactory factory;
	ResultSet r;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		factory = new DepartmentsFactory();
		r = DatabaseAccess.getInstance().getConnection().prepareStatement("SELECT * FROM " + DAODepartments.tName + ";")
				.executeQuery();
	}

	@Test
	public void testCreateListFromResultsResultSet() {
		
		ResultSet size = null;
		int x = 0;
		try {
			size = DatabaseAccess.getInstance().getConnection().prepareStatement("SELECT COUNT(*) FROM " + DAODepartments.tName + ";")
					.executeQuery();
			size.first();
			x = size.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		assertEquals(factory.createListFromResults(r).size(), x);
	}

}
