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
public class SelekcjaRandTest {
    populacja daneWejsciowe;
    public SelekcjaRandTest() {
        daneWejsciowe = new populacja();
        for (int i=0; i < 10; i++)
            daneWejsciowe.dodajOsobnika(new osobnikFlowShop(10));
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
     * Test of wybranaPopulacja method, of class SelekcjaRand.
     */
    @Test
    public void testWybranaPopulacja_populacja() {
        System.out.println("wybranaPopulacja");
        populacja p = null;
        SelekcjaRand instance = new SelekcjaRand();
        populacja expResult = null;
        populacja result = instance.wybranaPopulacja(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of wybranaPopulacja method, of class SelekcjaRand.
     */
    @Test
    public void testWybranaPopulacja_populacja_int() {
        System.out.println("wybranaPopulacja");
        int rozmiar = daneWejsciowe.rozmiarPopulacji()/2;
        SelekcjaRand instance = new SelekcjaRand();
        populacja result = instance.wybranaPopulacja(daneWejsciowe, rozmiar);
        System.out.println(result);
    }

}