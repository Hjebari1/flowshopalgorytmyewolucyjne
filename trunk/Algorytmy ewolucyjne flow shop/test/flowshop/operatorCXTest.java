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
 * @author Jakub Banaszewski
 */
public class operatorCXTest {

    public operatorCXTest() {
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
     * Test of krzyzuj method, of class operatorCX.
     */
    @Test
    public void testKrzyzuj() throws Exception {
        System.out.println("krzyzuj");
        int[] genom1 = {1,2,3,4,5,6,7,8,9};
        int[] genom2 = {3,2,6,7,1,5,9,4,8};
        int[] genomwyn = { 1, 2, 3, 7, 5, 6, 9, 4, 8};
        iOsobnik o1 = new osobnikFlowShop(9, genom1);
        iOsobnik o2 = new osobnikFlowShop(9, genom2);
        operatorCX instance = new operatorCX();
        iOsobnik expResult = new osobnikFlowShop(9,genomwyn);
        iOsobnik result = instance.krzyzuj(o1, o2);
        System.out.print(result);
        assertEquals(expResult, result);
    }
    @Test
    public void testKrzyzuj2() throws Exception {
        System.out.println("krzyzuj2");
        int[] genom1 = {4,5,6,7,1,9,8,2,3};
        int[] genom2 = {7,4,3,1,5,8,9,2,6};
        int[] genomwyn = {4,5,3,7,1,8,9,2,6};
        iOsobnik o1 = new osobnikFlowShop(9, genom1);
        iOsobnik o2 = new osobnikFlowShop(9, genom2);
        operatorCX instance = new operatorCX();
        iOsobnik expResult = new osobnikFlowShop(9,genomwyn);
        iOsobnik result = instance.krzyzuj(o1, o2);
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
        operatorCX instance = new operatorCX();
        populacja dzieci = instance.wykonaj(testRodzice);
        assertEquals(dzieci.rozmiarPopulacji(), 2);
        iOsobnik testOs = null;
        while(dzieci.rozmiarPopulacji() > 0) {
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
}