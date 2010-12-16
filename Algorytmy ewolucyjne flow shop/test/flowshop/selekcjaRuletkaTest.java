/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;

import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author bejnan
 */
public class selekcjaRuletkaTest {

    public selekcjaRuletkaTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of wybranaPopulacja method, of class selekcjaRuletka.
     */
    @Test
    public void testWybranaPopulacja_populacja_int() {
        System.out.println("wybranaPopulacja");
        populacja p = null;
        int rozmiar = 0;
        selekcjaRuletka instance = null;
        List expResult = null;
        List result = instance.wybranaPopulacja(p, rozmiar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of wybranaPopulacja method, of class selekcjaRuletka.
     */
    @Test
    public void testWybranaPopulacja_populacja() {
        System.out.println("wybranaPopulacja");
        populacja p = null;
        selekcjaRuletka instance = null;
        List expResult = null;
        List result = instance.wybranaPopulacja(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}