/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;

import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iFunkcjaCelu;
import flowshop.Interfejsy.iOsobnik;
import flowshop.osobnikFlowShop;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ListIterator;


/**
 *
 * @author Łukasz Synówka
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        // TODO code application logic here

        Algorytm1 a = new Algorytm1(50,new Dane3("MyFile2.txt"));

        for (int i=0;i<1000;i++)
        {
            a.wybor();
            a.krzyzowanie();
            a.zastepowanie();
            System.out.println(a.toString());
        }
    System.out.println(a.toString());

    }

}
