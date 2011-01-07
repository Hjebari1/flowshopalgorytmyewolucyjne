package flowshop;

import dane.Dane1;
import flowshop.Interfejsy.iDane;
import java.util.Iterator;
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
    populacja daneWejsciowe;
    iDane daneWsp;
    public selekcjaRuletkaTest() {
       daneWejsciowe = new populacja();
        for (int i=0; i < 5; i++)
            daneWejsciowe.dodajOsobnika(new osobnikFlowShop(5));
       daneWsp = new Dane1();
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
        selekcjaRuletka instance = new selekcjaRuletka(daneWsp,new funkcjaCeluFlowShop());
        populacja expResult = new populacja();
        populacja result = instance.wybranaPopulacja(p, rozmiar);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of wyliczWsp method, of class selekcjaRuletka.
     */
    @Test
    public void testWyliczWsp() {
        System.out.println("wyliczWsp");
        selekcjaRuletka instance = new selekcjaRuletka(daneWsp,new funkcjaCeluFlowShop());
        List expResult = new ArrayList();
        List<Para<Double, iOsobnik>> result = instance.wyliczWsp(daneWejsciowe,new funkcjaCeluFlowShop());
        System.out.println(result);
        iOsobnik badany=null;
        Para<Double,iOsobnik> badana=null;
        double sum = 0;
        for (Iterator<Para<Double,iOsobnik>> i = result.iterator(); i.hasNext();)
        {
            badana = i.next();
            sum += badana.getFirst();
            badany = badana.getSecond();
            for (int j = 0;j < badany.dlugoscGenomu(); j++)
                if (badany.znajdzPozGenu(0, badany.dlugoscGenomu(), j) == badany.dlugoscGenomu())
                    fail("Nie znaleziono prawidlowego genu");
        }
        if (sum != 1)
            fail("Błędna suma");
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of wybranaPopulacja method, of class selekcjaRuletka.
     */
    @Test
    public void testWybranaPopulacja_populacja() {
        System.out.println("wybranaPopulacja");
        selekcjaRuletka instance = new selekcjaRuletka(daneWsp,new funkcjaCeluFlowShop());
        System.out.println(instance.wyliczWsp(daneWejsciowe,new funkcjaCeluFlowShop()));
        //populacja expResult = null;
        populacja result = instance.wybranaPopulacja(daneWejsciowe);
        System.out.println(result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}