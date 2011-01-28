package flowshop;

import flowshop.Interfejsy.*;
import flowshop.Interfejsy.iZastepowanie;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 *Funkcja się nie sprawdziła może będzie rozwijana później
 * @author Łukasz Synówka
 */
public class zastepowanieMaksymalne implements iZastepowanie
{
    iDane dane;
    iFunkcjaCelu funkcja;
    int iloscKoncowa;

    public zastepowanieMaksymalne(iDane dane, iFunkcjaCelu funkcja, int iloscKoncowa)
    {
        this.dane = dane;
        this.funkcja = funkcja;
        this.iloscKoncowa = iloscKoncowa;
    }
    public populacja wykonaj(populacja p1, populacja p2) 
    {
        p1.polaczPopulacje(p2);
        p2=new populacja();
        populacja p = new populacja();
        iOsobnik o;
        Random r = new Random();
        List<iOsobnik> osobniki = p1.osobnikiPop();
        Collections.sort(osobniki,funkcja.porownaj(dane));
        ListIterator<iOsobnik> li = osobniki.listIterator();
        while(li.hasNext()&&(p.rozmiarPopulacji()<iloscKoncowa))
        {
                p.dodajOsobnika(li.next());
        }
        return p;
    }

}
