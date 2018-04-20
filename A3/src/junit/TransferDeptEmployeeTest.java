package junit;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import transferobj.DeptEmployee;

public class TransferDeptEmployeeTest {
	public DeptEmployee deptEmployee;
	Date date;
	int result;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		deptEmployee = new DeptEmployee();
	}
	
	@After
	public void tearDown() throws Exception {
		System.out.println("TransferDeptEmployeeTest Complete");
	}


	@Test
	public void testGetEmpID() {
		deptEmployee.setEmpID(10);
		result = deptEmployee.getEmpID();
		assertEquals(10, result);
	}

	@Test
	public void testSetEmpID() {
		deptEmployee.setEmpID(24444);
		result = deptEmployee.getEmpID();
		assertEquals(24444, result);
	}

	@Test
	public void testGetDeptID() {
		deptEmployee.setDeptID("D222");
		assertTrue(deptEmployee.getDeptID().equals("D222"));
	}

	@Test
	public void testSetDeptID() {
		deptEmployee.setDeptID("D919");
		assertTrue(deptEmployee.getDeptID().equals("D919"));
	}

	@Test
	public void testGetfDate() {
		Long now = new Long(1524188049353L);
		date = new Date(now);
		deptEmployee.setfDate(date);
		assertTrue(deptEmployee.getfDate().getTime() == now);
	}

	@Test
	public void testSetfDate() {
		Long now = new Long(1524188049353L);
		date = new Date(now);
		deptEmployee.setfDate(date);
		assertTrue(deptEmployee.getfDate().getTime() == now);
	}

	@Test
	public void testGettDate() {
		Long now = new Long(1524188049353L);
		date = new Date(now);
		deptEmployee.settDate(date);
		assertTrue(deptEmployee.gettDate().getTime() == now);
	}

	@Test
	public void testSettDate() {
		Long now = new Long(1524188049353L);
		date = new Date(now);
		deptEmployee.settDate(date);
		assertTrue(deptEmployee.gettDate().getTime() == now);
	}

	@Test
	public void testGetValues() {
		Long now = new Long(1524188049353L);
		date = new Date(now);
		deptEmployee.setDeptID("D001");
		deptEmployee.setEmpID(20);
		deptEmployee.setfDate(date);
		deptEmployee.settDate(date);
		
		String[] temp = new String[] {"20","D001","2018-04-19","2018-04-19"};
		
		assertArrayEquals(deptEmployee.getValues(), temp);
	}
}
