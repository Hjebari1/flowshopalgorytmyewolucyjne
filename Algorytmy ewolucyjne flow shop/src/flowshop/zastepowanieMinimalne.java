package flowshop;

import flowshop.Interfejsy.*;
import flowshop.Interfejsy.iZastepowanie;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Funkcja się nie sprawdziła może będzie rozwijana później
 * @author Łukasz Synówka
 */
public class zastepowanieMinimalne implements iZastepowanie
{
    iDane dane;
    iFunkcjaCelu funkcja;
    int iloscKoncowa;
    double ilePozostawic;

    public zastepowanieMinimalne(iDane dane, iFunkcjaCelu funkcja, int iloscKoncowa)
    {
        this.dane = dane;
        this.funkcja = funkcja;
        this.iloscKoncowa = iloscKoncowa;
        ilePozostawic = 0.0;
    }
    public zastepowanieMinimalne(iDane dane, iFunkcjaCelu funkcja, int iloscKoncowa, double ileZostanie)
    {
        this.dane = dane;
        this.funkcja = funkcja;
        this.iloscKoncowa = iloscKoncowa;
        ilePozostawic = ileZostanie;
    }
    /**
     *
     * @param p1 Populacja obecna
     * @param p2 Populacja wygenerowana
     * @return Połączenie populacji
     */
    public populacja wykonaj(populacja p1, populacja p2) 
    {
        populacja p = new populacja();
        Random r = new Random();
        Object[] przetrwaja = p1.osobniki().keySet().toArray();
        while(p.rozmiarPopulacji()<iloscKoncowa*ilePozostawic)
        {
            try {
                p.dodajOsobnika((iOsobnik) przetrwaja[r.nextInt(p1.szerokoscPopulacji())]);
            } catch (Exception ex) {
                Logger.getLogger(zastepowanieMinimalne.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        p1.polaczPopulacje(p2);
        p2=new populacja();
        
        List<iOsobnik> osobniki = p1.osobnikiPop();
        Collections.sort(osobniki,funkcja.porownaj(dane));
        ListIterator<iOsobnik> li = osobniki.listIterator();
        while(li.hasNext()&&(p.rozmiarPopulacji()<iloscKoncowa))
        {
            try {
                p.dodajOsobnika(li.next());
            } catch (Exception ex) {
                Logger.getLogger(zastepowanieMinimalne.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return p;
    }

}
