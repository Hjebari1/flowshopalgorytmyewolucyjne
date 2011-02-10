/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;

import nieudane.MutacjaOdwrotna;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class MutacjaOdwrotnaTest {

    public MutacjaOdwrotnaTest() {
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
     * Test of wykonaj method, of class MutacjaOdwrotna.
     */
    @Test
    public void testWykonaj_populacja() {
        try {
            System.out.println("wykonaj");
            populacja p = new populacja();
            MutacjaOdwrotna instance = new MutacjaOdwrotna(1.0);
            p.dodajOsobnika(new osobnikFlowShop(5));
            p.dodajOsobnika(new osobnikFlowShop(5));
            populacja result = instance.wykonaj(p);
            System.out.println(p);
            System.out.println(result);
            //assertEquals(expResult, result);
            //fail("The test case is a prototype.");
            //fail("The test case is a prototype.");
        } catch (Exception ex) {
            Logger.getLogger(MutacjaOdwrotnaTest.class.getName()).log(Level.SEVERE, null, ex);
            fail("Wyjatek");
        }
    }

}