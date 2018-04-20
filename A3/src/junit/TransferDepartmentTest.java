package junit;

import static org.junit.Assert.*;

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
	Assert.assertEquals(department.getDeptNumber(),"5" );
	}
	
	@Test
	public void getDeptNameTest() {
		
	}
	
	@Test
	public void setDeptNameTest(){
		department.setDeptName("Engineer");
		Assert.assertEquals(department.getDeptName(),"Engineer" );
	}
	
	@Test
	public void getValueTest(){
		
	}
	
	@Test
	public void printAllTest(){
		department.setDeptName("Engineer");
		department.setDeptNumber("5");
		//assertEquals()
		
	}
	

}
