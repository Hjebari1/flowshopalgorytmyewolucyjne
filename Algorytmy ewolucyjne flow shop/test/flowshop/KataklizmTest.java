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
public class KataklizmTest {

    public KataklizmTest() {
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
     * Test of reset method, of class Kataklizm.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        //Kataklizm instance = null;
        //instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of wykonaj method, of class Kataklizm.
     */
    @Test
    public void testWykonaj() {
        System.out.println("wykonaj");
        populacja p = new populacja();
        p.dodajOsobnika(new osobnikFlowShop(20));
        p.dodajOsobnika(new osobnikFlowShop(20));
        p.dodajOsobnika(new osobnikFlowShop(20));
        p.dodajOsobnika(new osobnikFlowShop(20));
        p.dodajOsobnika(new osobnikFlowShop(20));
        p.dodajOsobnika(new osobnikFlowShop(20));
        p.dodajOsobnika(new osobnikFlowShop(20));
        p.dodajOsobnika(new osobnikFlowShop(20));
        p.dodajOsobnika(new osobnikFlowShop(20));
        p.dodajOsobnika(new osobnikFlowShop(20));
        p.dodajOsobnika(new osobnikFlowShop(20));
        p.dodajOsobnika(new osobnikFlowShop(20));
        p.dodajOsobnika(new osobnikFlowShop(20));
        p.dodajOsobnika(new osobnikFlowShop(20));
        p.dodajOsobnika(new osobnikFlowShop(20));
        p.dodajOsobnika(new osobnikFlowShop(20));
        p.dodajOsobnika(new osobnikFlowShop(20));
        p.dodajOsobnika(new osobnikFlowShop(20));
        p.dodajOsobnika(new osobnikFlowShop(20));
        p.dodajOsobnika(new osobnikFlowShop(20));
        System.out.println(p);
        Kataklizm instance = new Kataklizm(1, p.rozmiarPopulacji(), p.rozmiarPopulacji()/10, p.rozmiarPopulacji()/10, new MutacjaPrzesuniecie(0.1));
        populacja expResult = p;
        populacja result = instance.wykonaj(p);
        assertFalse(expResult.equals(result));
        System.out.println(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}