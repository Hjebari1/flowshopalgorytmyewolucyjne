/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;

import flowshop.Interfejsy.iFunkcjaCelu;
import flowshop.Interfejsy.iOsobnik;
import flowshop.osobnikFlowShop;
import java.util.ListIterator;


/**
 *
 * @author Łukasz Synówka
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here

        Algorytm1 a = new Algorytm1(20,5);

        for (int i=0;i<100;i++)
        {
            a.wybor();
            a.krzyzowanie();
            a.zastepowanie();
            System.out.println(a.toString());
        }

    }

}
