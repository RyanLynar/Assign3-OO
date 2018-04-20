package junit;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import databaseaccess.DAOEmployee;
import databaseaccess.DAOSalaries;
import databaseaccess.DatabaseAccess;
import factories.EmployeeFactory;
import factories.SalariesFactory;

public class FactorySalariesTeset {
	SalariesFactory factory;
	ResultSet r;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		factory = new SalariesFactory();
		r = DatabaseAccess.getInstance().getConnection().prepareStatement("SELECT * FROM " + DAOSalaries.tName + ";")
				.executeQuery();
	}

	@Test
	public void testCreateListFromResultsResultSet() {
		ResultSet size = null;
		int x = 0;
		try {
			size = DatabaseAccess.getInstance().getConnection().prepareStatement("SELECT COUNT(*) FROM " + DAOSalaries.tName + ";")
					.executeQuery();
			size.first();
			x = size.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		assertEquals(factory.createListFromResults(r).size(), x);	
	}

}
