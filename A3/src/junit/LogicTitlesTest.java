package junit;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import logicclasses.TitlesLogic;

public class LogicTitlesTest {
	TitlesLogic t = new TitlesLogic();
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testTitlesLogic() {
		assertNotNull(t = new TitlesLogic());
	}

	@Test
	public void testAdd() {
		t.add(new String[] {"1000","Test","1992-03-24","2017-03-24"});
	}

	@Test
	public void testRemove() {
		assertTrue(t.remove(t.getByID(10001).get(0).getValues()));
	}

	@Test
	public void testModify() {
		t.getByID(10002).get(0).settDate(Date.valueOf("1992-03-24"));
		assertTrue(t.modify(t.getByID(10002).get(0).getValues()));
		
	}

	@Test
	public void testGetByID() {
		assertTrue(!t.getByID(10002).isEmpty());
	}

	@Test
	public void testGetObjects() {
		assertTrue(!t.getObjects(50).isEmpty());
	}

}
