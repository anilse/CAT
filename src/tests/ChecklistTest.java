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
		checklist = new Checklist();
	}
	
	@Test
	public void testObject() {
		assertNotNull(checklist);
	}

}
