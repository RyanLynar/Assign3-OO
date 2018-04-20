package junit;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import transferobj.Salaries;

public class TransferSalariesTest {
	public Salaries salary;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		salary = new Salaries();
	}

	@Test
	public void testGetEmpNo() {
		
	}

	@Test
	public void testSetEmpNo() {
		salary.setEmpNo(10001);
		assertEquals(salary.getEmpNo(), "1000");
	}

	@Test
	public void testGetSalary() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetSalary() {
		salary.setSalary(53000);
		assertEquals(salary.getSalary(),(53000));
	}

	@Test
	public void testGetfDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetfDate() {
		
		salary.setfDate(10/8/2013);
		assertEquals(salary.getfDate().equals(10/8/2013));
	}

	@Test
	public void testGettDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testSettDate() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetValues() {
		fail("Not yet implemented");
	}

	@Test
	public void testPrintAll() {
		fail("Not yet implemented");
	}

}
