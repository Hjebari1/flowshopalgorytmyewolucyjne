/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;

import flowshop.Interfejsy.iFunkcjaCelu;
import flowshop.Interfejsy.iOsobnik;


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
        populacja p1 = new populacja();
        populacja p2 = new populacja();
        p1.dodajOsobnika(new osobnikFlowShop(5));
        p1.dodajOsobnika(new osobnikFlowShop(5));
        p1.dodajOsobnika(new osobnikFlowShop(5));

        p2.dodajOsobnika(new osobnikFlowShop(5));
        p2.dodajOsobnika(new osobnikFlowShop(5));
        p2.dodajOsobnika(new osobnikFlowShop(5));

        p1.polaczPopulacje(p2);
        iOsobnik o;
        Dane1 d = new Dane1();
        iFunkcjaCelu f = new funkcjaCeluFlowShop();
        while(p1.rozmiarPopulacji()>0)
        {
            o = p1.usunOsobnika(0);
            for (int i=0;i<5;i++) System.out.print(o.wartoscOsobnika(i)+" ");
            System.out.println();
            System.out.print(f.wartoscFunkcji(o, d));
            System.out.println();
        }
        
        for(int i=0;i<d.iloscMaszyn();i++)
        {
            System.out.println();
            for(int j=0;j<d.iloscZadan();j++)
                System.out.print(d.czasZadania(i, j)+ " ");
        }
        System.out.println();

    }

}
