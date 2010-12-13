/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;

import flowshop.Interfejsy.iOsobnik;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Łukasz Synówka
 */
public class populacja
{
    int iloscOsobnikow;
    List<iOsobnik> osobniki;

    public populacja()
    {
        this.iloscOsobnikow = 0;
        this.osobniki = new LinkedList<iOsobnik>();
    }

    void dodajOsobnika(iOsobnik osobnik)
    {
        this.osobniki.add(osobnik);
        this.iloscOsobnikow++;
    }

    iOsobnik usunOsobnika(int pozycja)
    {
        this.iloscOsobnikow--;
        return this.osobniki.remove(pozycja);
    }

    void polaczPopulacje(populacja p2)
    {
        this.iloscOsobnikow+=p2.iloscOsobnikow;
        this.osobniki.addAll(p2.osobniki);
    }

    boolean usunOsobnika(iOsobnik o) {
        this.iloscOsobnikow--;
        return this.osobniki.remove(o);
    }
}
