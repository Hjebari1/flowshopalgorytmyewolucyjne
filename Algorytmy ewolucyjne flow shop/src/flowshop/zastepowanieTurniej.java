/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;

import flowshop.Interfejsy.iFunkcjaCelu;
import flowshop.Interfejsy.iOsobnik;
import flowshop.Interfejsy.iZastepowanie;
import java.util.Random;

/**
 *
 * @author Łukasz Synówka
 */
public class zastepowanieTurniej implements iZastepowanie
{

    public populacja wykonaj(populacja p1, populacja p2,iFunkcjaCelu funkcja, int iloscKoncowa)
    {
        iOsobnik o1;
        iOsobnik o2;

        p1.polaczPopulacje(p2);
        Random r = new Random();
        populacja wynik = new populacja();
        while (p1.iloscOsobnikow>iloscKoncowa)
        {
            o1 = p1.usunOsobnika(r.nextInt(p1.iloscOsobnikow));
            o2 = p1.usunOsobnika(r.nextInt(p1.iloscOsobnikow));
            if (funkcja.wartoscFunkcji(o1)<funkcja.wartoscFunkcji(o2))
            {
                wynik.dodajOsobnika(o1);
                p1.dodajOsobnika(o2);
            }
            else
            {
                wynik.dodajOsobnika(o2);
                p1.dodajOsobnika(o1);
            }
        }
        return wynik;
    }


}
