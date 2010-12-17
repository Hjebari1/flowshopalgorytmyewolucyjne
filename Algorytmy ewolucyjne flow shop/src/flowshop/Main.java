/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 *
 * @author Łukasz Synówka
 */
public class Main {

    /**
     * @param args the command line arguments
     * Dopisać parametryzacje
     */
    public static void main(String[] args) throws FileNotFoundException, IOException
    {
        // TODO code application logic here

        Algorytm2K a = new Algorytm2K(50,new Dane3("MyFile2.txt"));

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
