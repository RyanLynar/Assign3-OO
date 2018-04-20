package junit;

import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import databaseaccess.DAOSalaries;
import databaseaccess.DAOTitles;
import databaseaccess.DatabaseAccess;
import factories.SalariesFactory;
import factories.TitlesFactory;

public class FactoryTitlesTest {
	TitlesFactory factory;
	ResultSet r;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		factory = new TitlesFactory();
		r = DatabaseAccess.getInstance().getConnection().prepareStatement("SELECT * FROM " + DAOTitles.tName + ";")
				.executeQuery();
	}

	@Test
	public void testCreateListFromResultsResultSet() {
		ResultSet size = null;
		int x = 0;
		try {
			size = DatabaseAccess.getInstance().getConnection().prepareStatement("SELECT COUNT(*) FROM " + DAOTitles.tName + ";")
					.executeQuery();
			size.first();
			x = size.getInt(1);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		assertEquals(factory.createListFromResults(r).size(), x);	
	}

}
