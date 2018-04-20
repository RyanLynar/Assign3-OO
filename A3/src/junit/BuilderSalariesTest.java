package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import builders.SalariesBuilder;

public class BuilderSalariesTest {
	SalariesBuilder salary;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testSalariesBuilder() {
		
	}

	@Test
	public void testBuildResultSet() {
		salary.returnList();
		assertNotNull(salary.returnList());
	}

	@Test
	public void testReturnList() {
		//assertNotNull(returnList());
	}

	@Test
	public void testBuildStringArray() {
		fail("Not yet implemented");
	}

}
