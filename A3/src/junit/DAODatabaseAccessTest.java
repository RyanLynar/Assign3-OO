package junit;

import static org.junit.Assert.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import databaseaccess.DatabaseAccess;

public class DAODatabaseAccessTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetInstance() {
		assertNotNull(DatabaseAccess.getInstance());
	}

	@Test
	public void testCloseConnection() {
		try {
			PreparedStatement s = DatabaseAccess.getInstance().getConnection()
					.prepareStatement("SELECT * FROM employees;");
			DatabaseAccess.getInstance().closeConnection();
			s.executeQuery();
			fail("Database is still open");
		} catch (SQLException e) {

		}
	}

	@Test
	public void testGetConnection() {
		try {
			assertFalse(DatabaseAccess.getInstance().getConnection().isClosed());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
