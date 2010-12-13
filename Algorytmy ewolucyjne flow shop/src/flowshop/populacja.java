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

    public void dodajOsobnika(iOsobnik osobnik)
    {
        this.osobniki.add(osobnik);
        this.iloscOsobnikow++;
    }

    public iOsobnik usunOsobnika(int pozycja)
    {
        this.iloscOsobnikow--;
        return this.osobniki.remove(pozycja);
    }

    public void polaczPopulacje(populacja p2)
    {
        this.iloscOsobnikow+=p2.iloscOsobnikow;
        this.osobniki.addAll(p2.osobniki);
    }

    public boolean usunOsobnika(iOsobnik o) {
        // usuwa tylko jednego osobnika
        this.iloscOsobnikow--;
        return this.osobniki.remove(o);
    }
}
