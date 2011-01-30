/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;

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
public class SelekcjaSortTest {

    public SelekcjaSortTest() {
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
     * Test of wykonaj method, of class SelekcjaSort.
     */
    @Test
    public void testWykonaj_populacja_int() {
        System.out.println("wykonaj");
        populacja p = null;
        int rozmiar = 0;
        SelekcjaSort instance = null;
        populacja expResult = null;
        populacja result = instance.wykonaj(p, rozmiar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of wykonaj method, of class SelekcjaSort.
     */
    @Test
    public void testWykonaj_populacja() {
        System.out.println("wykonaj");
        populacja p = null;
        SelekcjaSort instance = null;
        populacja expResult = null;
       //populacja result = instance.wykonaj(p);
       // assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}