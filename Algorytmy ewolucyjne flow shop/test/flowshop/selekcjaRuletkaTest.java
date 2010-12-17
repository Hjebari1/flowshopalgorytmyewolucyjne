package flowshop;

import flowshop.Interfejsy.iOsobnik;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Jakub Banaszewski
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
        populacja p = new populacja();
        int rozmiar = 0;
        selekcjaRuletka instance = new selekcjaRuletka(new Dane1());
        populacja expResult = new populacja();
        populacja result = instance.wybranaPopulacja(p, rozmiar);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of wyliczWsp method, of class selekcjaRuletka.
     */
    @Test
    public void testWyliczWsp() {
        System.out.println("wyliczWsp");
        populacja daneWejsciowe = new populacja();
        selekcjaRuletka instance = new selekcjaRuletka(new Dane1());
        List expResult = new ArrayList();
        List<Para<Double, iOsobnik>> result = instance.wyliczWsp(daneWejsciowe);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of wybranaPopulacja method, of class selekcjaRuletka.
     */
    @Test
    public void testWybranaPopulacja_populacja() {
        System.out.println("wybranaPopulacja");
        populacja p = new populacja();
        for (int i = 0;i<5; i++)
            p.dodajOsobnika(new osobnikFlowShop(5));
        selekcjaRuletka instance = new selekcjaRuletka(new Dane1());

        populacja expResult = null;
        populacja result = instance.wybranaPopulacja(p);
        System.out.println(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}