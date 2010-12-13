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
        operatorCX instance = new operatorCX(null);
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
        operatorCX instance = new operatorCX(null);
        iOsobnik expResult = new osobnikFlowShop(9,genomwyn);
        iOsobnik result = instance.krzyzuj(o1, o2);
        System.out.print(result);
        assertEquals(expResult, result);
    }
}