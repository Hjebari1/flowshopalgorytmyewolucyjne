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
 * Klasa sortująca unikalne osobniki w populacji i wybierająca te
 * o najmniejszej wartości funkcji celu (bliższe optymalnemu rozwiązaniu).
 * @author Łukasz Synówka
 */
public class zastepowanieMinimalne implements iZastepowanie
{
    iDane dane;
    iFunkcjaCelu funkcja;
    int iloscKoncowa;
    double ilePozostawic;

    /**
     * Konstruktor definiujący dane i funkcję dla których będą liczone wartości
     * osobników. Ilość końcowa to rozmiar populacji wynikowej.
     * @param dane Dane do obliczeń
     * @param funkcja Funkcja celu korzystająca z danych
     * @param iloscKoncowa Rozmiar populacji po połączeniu
     */
    public zastepowanieMinimalne(iDane dane, iFunkcjaCelu funkcja, int iloscKoncowa)
    {
        this.dane = dane;
        this.funkcja = funkcja;
        this.iloscKoncowa = iloscKoncowa;
        ilePozostawic = 0.0;
    }
    /**
     * Konstruktor z dodatkowym parametrem definiującym ile losowych osobników
     * z orginalnie zadanej populacji przetrwa zastępowanie bez uczestniczenia w
     * wyborze najlepiej przystosowanych osobników.
     * @param ileZostanie Współczynnik, który określa jaka część pierwotnej populacji ma pozostać.
     */
    public zastepowanieMinimalne(iDane dane, iFunkcjaCelu funkcja, int iloscKoncowa, double ileZostanie)
    {
        this.dane = dane;
        this.funkcja = funkcja;
        this.iloscKoncowa = iloscKoncowa;
        ilePozostawic = ileZostanie;
    }
    /**
     * Właściwa funcja populacji.
     * @param p1 Populacja obecna
     * @param p2 Populacja wygenerowana
     * @return Połączenie populacji
     */
    public populacja wykonaj(populacja p1, populacja p2) 
    {
        populacja populacjaWynikowa = new populacja();
        Random r = new Random();
        Object[] przetrwaja = p1.osobniki().keySet().toArray();
        while(populacjaWynikowa.rozmiarPopulacji()<iloscKoncowa*ilePozostawic)
        {
            try {
                populacjaWynikowa.dodajOsobnika((iOsobnik) przetrwaja[r.nextInt(p1.szerokoscPopulacji())]);
            } catch (Exception ex) {
                Logger.getLogger(zastepowanieMinimalne.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        p1.polaczPopulacje(p2);
        p2=new populacja();
        
        List<iOsobnik> osobniki = p1.osobnikiPop();
        Collections.sort(osobniki,funkcja.porownaj(dane));
        ListIterator<iOsobnik> li = osobniki.listIterator();
        while(li.hasNext()&&(populacjaWynikowa.rozmiarPopulacji()<iloscKoncowa))
        {
            try {
                populacjaWynikowa.dodajOsobnika(li.next());
            } catch (Exception ex) {
                Logger.getLogger(zastepowanieMinimalne.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return populacjaWynikowa;
    }

}
