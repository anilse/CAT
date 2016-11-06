package tests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import Controller.Control;
import View.View;
import junit.framework.TestCase;


public class ViewTest extends TestCase{
	
	View view;
	Control control;

	@Before
	public void setUp() throws Exception {
		control = new Control();
		view = new View(control);
	}

	@Test
	public void testObject() {
		assertNotNull(view);
	}

}
