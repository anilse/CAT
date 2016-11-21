package tests;

import static org.junit.Assert.*;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import Model.Checklist;

public class ChecklistTest extends TestCase{

	Checklist checklist;
	
	@Before
	public void setUp() throws Exception {
		String fname = "dummyChecklist";
		checklist = new Checklist(fname, null, null, fname, null, null);
	}
	
	@Test
	public void testObject() {
		assertNotNull(checklist);
	}

}
