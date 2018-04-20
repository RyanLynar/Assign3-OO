package junit;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import transferobj.Departments;

public class TransferDepartmentTest {

	public Departments department;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		department = new Departments();
	}

	@Test
	public void getDeptNumberTest() {
		department.setDeptNumber("D100");
		assertTrue(department.getDeptNumber().equals("D100"));
	}

	@Test
	public void setDeptNumberTest() {
		department.setDeptNumber("5");
		Assert.assertEquals(department.getDeptNumber(), "5");
	}

	@Test
	public void getDeptNameTest() {
		department.setDeptName("Billy");
		assertTrue(department.getDeptName().equals("Billy"));
	}

	@Test
	public void setDeptNameTest() {
		department.setDeptName("Engineer");
		Assert.assertEquals(department.getDeptName(), "Engineer");
	}

	@Test
	public void getValueTest() {

		department.setDeptName("Engineering");
		department.setDeptNumber("5");

		String[] temp = new String[] { "5", "Engineering" };

		assertArrayEquals(department.getValues(), temp);

	}

}
