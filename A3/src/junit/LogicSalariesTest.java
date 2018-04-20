package junit;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import logicclasses.SalariesLogic;
import transferobj.Salaries;

public class LogicSalariesTest {
	SalariesLogic salaryL;
    Salaries obj;
    
    
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		//String []
	}

	@Test
	public void testSalariesLogic() {
		fail("Not yet implemented");
	}

	@Test
	public void testAdd() {
		assertTrue(salaryL.add());
	}

	@Test
	public void testRemove() {
		fail("Not yet implemented");
	}

	@Test
	public void testModify() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetByID() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetObjects() {
		fail("Not yet implemented");
	}

}
