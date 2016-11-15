/**
 *
 */
package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FilenameUtils;
import org.junit.Before;
import org.junit.Test;

import Controller.Control;

import junit.framework.TestCase;

import jxl.read.biff.BiffException;


/**
 * @author anilse
 */
public class ControlTest extends TestCase {
    Control control;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        control = new Control();
    }

    @Test
    public void testControllerObject() {
        assertNotNull(control);
    }

    // getAndCheckFileName tests-start
    @Test
    public void testGetAndCheckFileNameXls() {
        File dummyXlsFile = new File("deneme.xls");
        assertTrue(control.getAndCheckFileName(dummyXlsFile));
    }

    @Test
    public void testGetAndCheckFileNameNull() {
        assertFalse(control.getAndCheckFileName(null));
    }

    @Test
    public void testGetAndCheckFileNameWithNoExtension() {
        File dummyFile = new File("file");
        assertFalse(control.getAndCheckFileName(dummyFile));
    }

    @Test
    public void testGetAndCheckFileNameXml() {
        File dummyXmlFile = new File("example.xml");
        assertFalse(control.getAndCheckFileName(dummyXmlFile));
    }
    // getAndCheckFileName tests-end
    // parseExcel tests-start

    @Test
    public void testparseExcelNull() throws BiffException, IOException {
        File dummyXmlFile = new File("example.xml");
        assertNotNull(control.parseExcel(dummyXmlFile));
    }

}
