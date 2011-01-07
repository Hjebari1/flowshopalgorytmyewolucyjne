/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flowshop;

import flowshop.Interfejsy.iOsobnik;
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
public class MutacjaShiftTest {

    public MutacjaShiftTest() {
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
     * Test of wynonaj method, of class MutacjaShift.
     */
    @Test
    public void testWynonaj() {
        System.out.println("wynonaj");
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
        MutacjaPrzesuniecie instance = new MutacjaPrzesuniecie((float) 0.5);
        instance.wykonaj(p);
        iOsobnik testOs = null;
        while (p.rozmiarPopulacji() > 0) {
            testOs = p.usunOsobnika(0);
            for (int j = 0; j < 20; j++) {
                if (testOs.znajdzPozGenu(0, 20, j) == 20) {
                    System.out.println(testOs);
                    fail("Nie znaleziono prawidlowego genu");
                }
            }
        }
        assertEquals(p.rozmiarPopulacji(), 0);
        System.out.println(testOs);
    }
}
