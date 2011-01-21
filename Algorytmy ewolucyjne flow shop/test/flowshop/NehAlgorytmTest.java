/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;

import flowshop.Interfejsy.iDane;
import java.util.ArrayList;
import java.util.Collection;
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
public class NehAlgorytmTest {
    iDane dane1=null;
    public NehAlgorytmTest() {
        
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        dane1 = new iDane() {

            double czasyZadan[][] = {
                {5,9,9,4},
                {9,3,4,8},
                {8,10,5,8},
                {10,1,8,7},
                {1,8,6,2},
            };
            public int iloscMaszyn() {
                return 5;
            }

            public int iloscZadan() {
                return 4;
            }

            public double czasZadania(int iloscMaszyn, int iloscZadan) {
                return czasyZadan[iloscMaszyn][iloscZadan];
            }
        };
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of wyliczPorzadek method, of class NehAlgorytm.
     */
    @Test
    public void testWyliczPorzadek() {
        System.out.println("wyliczPorzadek");
        NehAlgorytm instance = new NehAlgorytm(dane1);
        Collection expResult = new ArrayList<Integer>();
        expResult.add(3);
        expResult.add(2);
        expResult.add(0);
        expResult.add(1);
        Collection result = instance.wyliczPorzadek();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        System.out.println(result);
    }

}