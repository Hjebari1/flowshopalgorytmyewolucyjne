package flowshop;

import operatory.operatorPMX;
import flowshop.Interfejsy.iOsobnik;
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
public class operatorPMXTest {

    public operatorPMXTest() {
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
     * Test of krzyzuj method, of class operatorPMX.
     */
    @Test
    public void testKrzyzuj() throws Exception {
        System.out.println("krzyzuj");
        int[] genom1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] genom2 = {3, 2, 6, 7, 1, 5, 9, 4, 8};
        int[] genom3 = {5, 2, 6, 7, 1, 3, 4, 8, 9};
        int[] genom4 = {6, 2, 3, 4, 5, 1, 9, 7, 8};
        iOsobnik o1 = new osobnikFlowShop(9, genom1);
        iOsobnik o2 = new osobnikFlowShop(9, genom2);
        operatorPMX instance = new operatorPMX();
        iOsobnik wyn1 = new osobnikFlowShop(9, genom3);
        iOsobnik wyn2 = new osobnikFlowShop(9, genom4);
        Para expResult = new Para<iOsobnik, iOsobnik>(wyn1, wyn2);
        Para result = instance.krzyzuj(o1, o2, 2, 5);
        System.out.print(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of krzyzuj method, of class operatorPMX.
     */
    @Test
    public void testKrzyzuj2() throws Exception {
        System.out.println("krzyzuj2");
        int[] genom1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] genom2 = {3, 2, 6, 7, 1, 5, 9, 4, 8};
        int[] genom3 = {3, 2, 6, 4, 5, 1, 7, 8, 9};
        int[] genom4 = {1, 2, 3, 7, 6, 5, 9, 4, 8};
        iOsobnik o1 = new osobnikFlowShop(9, genom1);
        iOsobnik o2 = new osobnikFlowShop(9, genom2);
        operatorPMX instance = new operatorPMX();
        iOsobnik wyn1 = new osobnikFlowShop(9, genom3);
        iOsobnik wyn2 = new osobnikFlowShop(9, genom4);
        Para expResult = new Para<iOsobnik, iOsobnik>(wyn1, wyn2);
        Para result = instance.krzyzuj(o1, o2, 0, 3);
        System.out.print(result);
        assertEquals(expResult, result);
    }

    @Test
    public void testWykonaj() {
        System.out.println("wykonaj");
        populacja testRodzice = new populacja();
        int[] genom1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] genom2 = {3, 2, 6, 7, 1, 5, 9, 4, 8};
        int[] genom3 = {4, 5, 6, 7, 1, 9, 8, 2, 3};
        int[] genom4 = {7, 1, 3, 4, 5, 8, 9, 2, 6};
        testRodzice.dodajOsobnika(new osobnikFlowShop(9, genom1));
        testRodzice.dodajOsobnika(new osobnikFlowShop(9, genom2));
        testRodzice.dodajOsobnika(new osobnikFlowShop(9, genom3));
        testRodzice.dodajOsobnika(new osobnikFlowShop(9, genom4));
        operatorPMX instance = new operatorPMX();
        populacja dzieci = instance.wykonaj(testRodzice);
        assertEquals(dzieci.rozmiarPopulacji(), 4);
        iOsobnik testOs = null;
        while (dzieci.rozmiarPopulacji() > 0) {
            testOs = dzieci.usunOsobnika(0);
            for (int j = 1; j <= 9; j++) {
                if (testOs.znajdzPozGenu(0, 9, j) == 9) {
                    System.out.println(testOs);
                    fail("Nie znaleziono prawidlowego genu");
                }
            }
        }
        assertEquals(dzieci.rozmiarPopulacji(), 0);
    }
    @Test
    public void testWykonaj3() {
        System.out.println("wykonaj3");
        populacja testRodzice = new populacja();
        testRodzice.dodajOsobnika(new osobnikFlowShop(20));
        testRodzice.dodajOsobnika(new osobnikFlowShop(20));
        testRodzice.dodajOsobnika(new osobnikFlowShop(20));
        testRodzice.dodajOsobnika(new osobnikFlowShop(20));
        operatorPMX instance = new operatorPMX();
        populacja dzieci = null;
        for (int i = 0; i < 1000; i ++)
        {
            dzieci = instance.wykonaj(testRodzice);
            testRodzice = dzieci;
        }
        assertEquals(dzieci.rozmiarPopulacji(), 4);
        iOsobnik testOs = null;
        while (dzieci.rozmiarPopulacji() > 0) {
            testOs = dzieci.usunOsobnika(0);
            for (int j = 0; j < 20; j++) {
                if (testOs.znajdzPozGenu(0, 20, j) == 20) {
                    System.out.println(testOs);
                    fail("Nie znaleziono prawidlowego genu");
                }
            }
        }
        assertEquals(dzieci.rozmiarPopulacji(), 0);
    }
}
