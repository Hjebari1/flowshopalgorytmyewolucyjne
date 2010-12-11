/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;

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
        p1.dodajOsobnika(new osobnikFlowShop(10));
        p1.dodajOsobnika(new osobnikFlowShop(10));
        p1.dodajOsobnika(new osobnikFlowShop(10));

        p2.dodajOsobnika(new osobnikFlowShop(10));
        p2.dodajOsobnika(new osobnikFlowShop(10));
        p2.dodajOsobnika(new osobnikFlowShop(10));

        p1.polaczPopulacje(p2);
        iOsobnik o;

        while(p1.iloscOsobnikow>0)
        {
            o = p1.usunOsobnika(0);
            for (int i=0;i<10;i++) System.out.print(o.wartoscOsobnika(i)+" ");
            System.out.println();
        }
        Dane1 d = new Dane1();
        for(int i=0;i<d.iloscMaszyn();i++)
            for(int j=0;j<d.iloscZadan();j++)
                System.out.println(d.czasZadania(i, j));
    }

}
