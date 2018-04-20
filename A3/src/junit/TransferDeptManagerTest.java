package junit;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import transferobj.DeptManager;

public class TransferDeptManagerTest {
	DeptManager manager;
	Date date;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		manager = new DeptManager();
	}

	@Test
	public void testGetEmployeeNumber() {
		manager.setEmployeeNumber(2000);
		assertTrue(manager.getEmployeeNumber() == 2000);
	}

	@Test
	public void testSetEmployeeNumber() {
		manager.setEmployeeNumber(2000);
		assertEquals(2000, manager.getEmployeeNumber());
	}

	@Test
	public void testGetDeptNumber() {
		manager.setDeptNumber("T222");
		assertTrue(manager.getDeptNumber().equals("T222"));
	}

	@Test
	public void testSetDeptNumber() {
		manager.setDeptNumber("H1H1H1");
		assertEquals("H1H1H1", manager.getDeptNumber());
	}

	@Test
	public void testGetFromDate() {
		Long now = new Long(1524188049353L);
		System.out.println(now);
		date = new Date(now);
		manager.setFromDate(date);
		assertTrue(manager.getFromDate().getTime() == now);
	}

	@Test
	public void testSetFromDate() {
		Long now = new Long(1524188049353L);
		System.out.println(now);
		date = new Date(now);
		manager.setFromDate(date);
		assertEquals(1524188049353L, manager.getFromDate().getTime());
	}

	@Test
	public void testGetToDate() {
		Long now = new Long(1524188049353L);
		System.out.println(now);
		date = new Date(now);
		manager.setToDate(date);
		assertTrue(manager.getToDate().getTime() == now);
	}

	@Test
	public void testSetToDate() {
		Long now = new Long(1524188049353L);
		date = new Date(now);
		manager.setToDate(date);
		assertEquals(1524188049353L, manager.getToDate().getTime());
	}

	@Test
	public void testGetValues() {
		Long now = new Long(1524188049353L);
		date = new Date(now);
		manager.setDeptNumber("T100");
		manager.setEmployeeNumber(50);
		manager.setFromDate(date);
		manager.setToDate(date);
		
		String[] temp = new String[] {"50","T100","2018-04-19","2018-04-19"};
		
		assertArrayEquals(manager.getValues(), temp);
	}
}
