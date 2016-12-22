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
    public void testSetCriteria() {
    	checklist.setCriteria("veys");
        assertEquals("CAT",checklist.getCriteria(),"veys");
    }
	
    @Test
    public void testGetCriteria() {
        checklist.setCriteria("mevlut");
        assertEquals("CAT",checklist.getCriteria(),"mevlut");
    }

    @Test
    public void testGetCriteriaNull() {
        checklist.setCriteria(null);
        assertEquals("CAT",checklist.getCriteria(),null);
    }

    @Test
    public void testGetChecklistItem() {
        checklist.setChecklist_item("veys");
        assertEquals("CAT",checklist.getChecklist_item(),"veys");
    }

    @Test
    public void testgetFunction() {
    	String[] stringarray = {"a","b"};
    	checklist.setFunction(stringarray);
        assertArrayEquals(checklist.getFunction(),stringarray);
   }
    @Test
    public void testsetTest_methodNull() {
    	String[] stringarray = null;
  	checklist.setTest_method(stringarray);
        assertEquals(checklist.getTest_method(),null);
   }
    
    @Test
    public void testsetTest_method() {
    	String[] stringarray = {"a","b"};
    	checklist.setTest_method(stringarray);
        assertArrayEquals(checklist.getTest_method(),null);
   }
    
    
    
    @Test
    public void testsetStatusNull() {
    	String[] stringarray = null;
    	checklist.setStatus(stringarray);
    	assertArrayEquals(checklist.getStatus(),null);
    }
    
    @Test
    public void testsetStatus() {
    	String[] stringarray = {"mevlut"};
    	checklist.setStatus(stringarray);
    	assertArrayEquals(checklist.getStatus(),stringarray);
    }
    
 
    @Test
    public void testgetStatus() {
    	String[] stringarray = {"mevlut"};
    	checklist.setStatus(stringarray);
        assertEquals("CAT",checklist.getStatus(),stringarray);
    }
    
   
    @Test
    public void testsetFunctionNull() {
    	String[] stringarray = null;
  	checklist.setFunction(stringarray);
        assertEquals(checklist.getFunction(),null);
    }
    
    @Test
    public void testsetFunction() {
    	String[] stringarray = {"a","b"};
    	checklist.setFunction(stringarray);
        assertArrayEquals(checklist.getFunction(),stringarray);
    }
    
  

    
	@Test
	public void testObject() {
		assertNotNull(checklist);
	}

}