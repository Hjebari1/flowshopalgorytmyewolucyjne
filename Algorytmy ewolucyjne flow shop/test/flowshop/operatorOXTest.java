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
public class operatorOXTest {

    public operatorOXTest() {
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
     * Test of krzyzuj method, of class operatorOX.
     */
    @Test
    public void testKrzyzuj() throws Exception {
        System.out.println("krzyzuj");
        int[] genom1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] genom2 = {3, 2, 6, 7, 1, 5, 9, 4, 8};
        int[] genom3 = {4, 5, 6, 7, 1, 9, 8, 2, 3};
        int[] genom4 = {7, 1, 3, 4, 5, 8, 9, 2, 6};
        iOsobnik o1 = new osobnikFlowShop(9, genom1);
        iOsobnik o2 = new osobnikFlowShop(9, genom2);
        operatorOX instance = new operatorOX(null);
        iOsobnik wyn1 = new osobnikFlowShop(9, genom3);
        iOsobnik wyn2 = new osobnikFlowShop(9, genom4);
        Para expResult = new Para<iOsobnik, iOsobnik>(wyn1,wyn2);
        Para result = instance.krzyzuj(o1, o2, 2, 5);
        System.out.print(result);
        assertEquals(expResult, result);
    }
}
