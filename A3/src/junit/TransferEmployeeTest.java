package junit;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import transferobj.Employee;

public class TransferEmployeeTest {
	Employee emp;
	Date date;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		emp = new Employee();
	}

	@Test
	public void testGetEmpNumber() {
		emp.setEmpNumber(200);
		assertEquals(200, emp.getEmpNumber());
	}

	@Test
	public void testGetEmpFName() {
		emp.setEmpFName("Fred");
		assertTrue(emp.getEmpFName().equals("Fred"));
	}

	@Test
	public void testGetEmpLName() {
		emp.setEmpLName("Andrews");
		assertTrue(emp.getEmpLName().equals("Andrews"));
	}

	@Test
	public void testGetEmpName() {
		emp.setEmpFName("aFirstName");
		emp.setEmpLName("aLastName");
		assertTrue(emp.getEmpName().equals("aFirstName aLastName"));
	}

	@Test
	public void testSetEmpNumber() {
		emp.setEmpNumber(9);
		assertTrue(emp.getEmpNumber() == 9);
	}

	@Test
	public void testSetEmpFName() {
		emp.setEmpFName("firstName");
		assertEquals("firstName", emp.getEmpFName());
	}

	@Test
	public void testSetEmpLName() {
		emp.setEmpLName("LastName");
		assertEquals("LastName", emp.getEmpLName());
	}

	@Test
	public void testSetEmpGender() {
		emp.setEmpGender("F");
		assertEquals("F", emp.getEmpGender());
	}

	@Test
	public void testSetEmpBDate() {
		Long now = new Long(1524188049353L);
		date = new Date(now);
		emp.setEmpBDate(date);
		assertTrue(emp.getEmpBDate().getTime() == now);
	}

	@Test
	public void testSetEmpHDate() {
		Long now = new Long(1524188049353L);
		date = new Date(now);
		emp.setEmpHDate(date);
		assertTrue(emp.getEmpHDate().getTime() == now);
	}

	@Test
	public void testGetEmpGender() {
		emp.setEmpGender("M");
		assertTrue(emp.getEmpGender().equals("M"));
	}

	@Test
	public void testGetEmpBDate() {
		Long now = new Long(1524188049353L);
		date = new Date(now);
		emp.setEmpBDate(date);
		assertTrue(emp.getEmpBDate().getTime() == now);
	}

	@Test
	public void testGetEmpHDate() {
		Long now = new Long(1524188049353L);
		date = new Date(now);
		emp.setEmpHDate(date);
		assertTrue(emp.getEmpHDate().getTime() == now);
	}

	@Test
	public void testGetValues() {
		Long now = new Long(1524188049353L);
		date = new Date(now);
		emp.setEmpBDate(date);
		emp.setEmpFName("Hello");
		emp.setEmpGender("Hello");
		emp.setEmpHDate(date);
		emp.setEmpLName("Hello");
		emp.setEmpNumber(1001);
		
		String[] temp = new String[] {"1001", "2018-04-19", "Hello", "Hello", "Hello", "2018-04-19"};
		
		assertArrayEquals(emp.getValues(), temp);
	}

}
