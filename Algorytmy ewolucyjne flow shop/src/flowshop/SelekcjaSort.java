package flowshop;

import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iFPopulacjiRozmiar;
import flowshop.Interfejsy.iFunkcjaCelu;
import flowshop.Interfejsy.iOsobnik;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Selekcja metodą ruletki obliczająca współczynniki według rankingu
 * @author Jakub Banaszewski
 */
public class SelekcjaSort implements iFPopulacjiRozmiar {

    iDane dane;
    iFunkcjaCelu fCel;

    /**
     * Konstruktor przyjmujące dane i funkcję celu dla których ma określić
     * wartości osobników.
     * @param dane
     * @param fCel
     */
    public SelekcjaSort(iDane dane, iFunkcjaCelu fCel) {
        this.dane = dane;
        this.fCel = fCel;
    }

    /**
     * Właściwa funkcja selekcji. Dla zadanej populacji wybiera unikalne elementy, sortuje je
     * i według ich kolejności określa ich współczynniki. Następnie korzystając z metody ruletki
     * wybiera odpowiednie osobniki do populacji wynikowej.
     * @param populacjaMacierzysta Populacja wejściowa
     * @param rozmiar Rozmiar populacji wynikowej
     * @return Populacja wybrana z zadanej populacji zgoda z rozmiarem
     */
    public populacja wykonaj(populacja populacjaMacierzysta, int rozmiar) {
        populacja wybrPop = new populacja();
        Random los = new Random();
        List <iOsobnik> osobniki = new ArrayList<iOsobnik>(populacjaMacierzysta.osobniki().keySet());
        Collections.sort(osobniki,fCel.porownaj(dane));
        ArrayList<Para<Double,iOsobnik>> wspTab = new ArrayList<Para<Double,iOsobnik>>();
        iOsobnik o=null;
        int k = osobniki.size();
        for (Iterator<iOsobnik> iter = osobniki.iterator();iter.hasNext();)
        {
            o = iter.next();
            wspTab.add(new Para<Double,iOsobnik>((2.0*k--)/(osobniki.size()*(osobniki.size()+1.0)),o));
        }
        int odpSize = 0;
        double sum = 0;
        double prwd = 1.0;
        Para<Double, iOsobnik> tmp = null;
        while (odpSize < rozmiar) {
            try {
                sum = 0;
                prwd = los.nextDouble();
                for (Iterator<Para<Double, iOsobnik>> i = wspTab.iterator(); i.hasNext() && sum < prwd;) {
                    tmp = i.next();
                    sum += tmp.getFirst();
                }
                wybrPop.dodajOsobnika(tmp.getSecond());
                odpSize++;
            } catch (Exception ex) {
                Logger.getLogger(SelekcjaSort.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return wybrPop;
    }

    public populacja wykonaj(populacja populacjaMacierzysta) {
        return wykonaj(populacjaMacierzysta, populacjaMacierzysta.rozmiarPopulacji());
    }
}
