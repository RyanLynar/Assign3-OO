package junit;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

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
		salary.setEmpNo(10001);
		assertEquals(10001,salary.getEmpNo());
		
	}

	@Test
	public void testSetEmpNo() {
		salary.setEmpNo(10001);
		assertEquals(salary.getEmpNo(),10001);
	}

	@Test
	public void testGetSalary() {
		salary.setSalary(53000);
		assertEquals(salary.getSalary(),(53000));
	}

	@Test
	public void testSetSalary() {
		salary.setSalary(53000);
		assertEquals(salary.getSalary(),(53000));
	}

	@Test
	public void testGetfDate() {
		Long now = Calendar.getInstance().getTime().getTime();
        Date date = new Date(now);
        salary.setfDate(date);
        assertTrue(salary.getfDate().getTime() == now);
	}

	@Test
	public void testSetfDate() {
		Long now = Calendar.getInstance().getTime().getTime();
        Date date = new Date(now);
        salary.setfDate(date);
        assertTrue(salary.getfDate().getTime() == now);
	}

	@Test
	public void testGettDate() {
		Long now = Calendar.getInstance().getTime().getTime();
        Date date = new Date(now);
        salary.settDate(date);
        assertTrue(salary.gettDate().getTime() == now);
	}

	@Test
	public void testSettDate() {
		Long now = Calendar.getInstance().getTime().getTime();
        Date date = new Date(now);
        salary.settDate(date);
        assertTrue(salary.gettDate().getTime() == now);
	}

	@Test
	public void testGetValues() {
		Long now = new Long(1524188049353L);
        Date date = new Date(now);
        salary.setEmpNo(1000);
        salary.setSalary(53000);
        salary.setfDate(date);
        salary.settDate(date);
        
        String[] temp = new String[] {"1000","53000","2018-04-19","2018-04-19"};
        
        assertArrayEquals(salary.getValues(), temp);
	}

	@Test
	public void testPrintAll() {
	}

}
