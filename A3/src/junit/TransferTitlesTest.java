package junit;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import transferobj.Titles;

public class TransferTitlesTest {
	public Titles title;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		title = new Titles();
	}

	@Test
	public void testGetEmpNo() {
		title.setEmpNo(10001);
		assertEquals(10001,title.getEmpNo());
	}

	@Test
	public void testSetEmpNo() {
		title.setEmpNo(10001);
		assertEquals(title.getEmpNo(),10001);
	}

	@Test
	public void testGetTitle() {
		title.setTitle("Manager");
		Assert.assertEquals(title.getTitle(),"Manager" );
	}

	@Test
	public void testSetTitle() {
		title.setTitle("Manager");
		Assert.assertEquals(title.getTitle(),"Manager" );
	}

	@Test
	public void testGettDate() {
		Long now = Calendar.getInstance().getTime().getTime();
        Date date = new Date(now);
        title.settDate(date);
        assertTrue(title.gettDate().getTime() == now);
	}

	@Test
	public void testSettDate() {
		Long now = Calendar.getInstance().getTime().getTime();
        Date date = new Date(now);
        title.settDate(date);
        assertTrue(title.gettDate().getTime() == now);
	}

	@Test
	public void testGetfDate() {
		Long now = Calendar.getInstance().getTime().getTime();
        Date date = new Date(now);
        title.setfDate(date);
        assertTrue(title.getfDate().getTime() == now);
	}

	@Test
	public void testSetfDate() {
		Long now = Calendar.getInstance().getTime().getTime();
        Date date = new Date(now);
        title.setfDate(date);
        assertTrue(title.getfDate().getTime() == now);
	}

	@Test
	public void testGetValues() {
		Long now = new Long(1524188049353L);
        Date date = new Date(now);
        title.setEmpNo(1000);
        title.setTitle("Manager");
        title.setfDate(date);
        title.settDate(date);
        
        String[] temp = new String[] {"1000","Manager","2018-04-19","2018-04-19"};
        
        assertArrayEquals(title.getValues(), temp);
	}

	@Test
	public void testPrintAll() {
		
	}

}
