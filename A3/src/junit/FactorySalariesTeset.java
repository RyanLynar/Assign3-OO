package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


import factories.SalariesFactory;

public class FactorySalariesTeset {
	SalariesFactory factory;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		String[] temp = new String[] {"20","D001","2018-04-19","2018-04-19"};
	}

	@Test
	public void testSalariesFactory() {
		//r.first
		//r.getint
		//assertNotNull(factory.returnList(0), temp);
	}

	@Test
	public void testCreateFromResultsResultSet() {
		
	}

	@Test
	public void testCreateFromInputStringArray() {
		fail("Not yet implemented");
	}

	@Test
	public void testCreateListFromResultsResultSet() {
		fail("Not yet implemented");
	}

}
