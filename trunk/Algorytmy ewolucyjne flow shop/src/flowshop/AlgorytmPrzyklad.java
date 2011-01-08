package flowshop;

import flowshop.Interfejsy.VAlgorytm;
import flowshop.Interfejsy.iAlgorytm;
import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iOsobnik;
import flowshop.Interfejsy.iZastepowanie;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ListIterator;
import java.util.Random;

/**
 *
 * @author Jakub Banaszewski
 */
public class AlgorytmPrzyklad extends VAlgorytm {

    int iloscOsobnikow;
   /**
    * Kostruktor konkretnej implementacji abstrakcyjnej klasy VAlgorytm.
    * TODO parametry przekazywane w konstruktorze czy wpisywane na sztywno ?
    * @param iloscOsobnikow rozmiar populacji
    * @param d dane wejsciowe
   */
    public AlgorytmPrzyklad(int iloscOsobnikow, iDane d) {
        dane = d;
        f = new funkcjaCeluFlowShop();
        this.iloscOsobnikow = iloscOsobnikow;
        zbiorOsobnikow = new populacja();
        zastepowanie = new zastepowanieMaksymalne(dane, f, iloscOsobnikow);
        mutacja = new MutacjaPrzesuniecie((float) 0.1);
        for (int i = 0; i < iloscOsobnikow; i++) {
            zbiorOsobnikow.dodajOsobnika(new osobnikFlowShop(d.iloscZadan()));
        }
    }

      
    @Override
    public String toString() {
        StringBuilder wynik = new StringBuilder("");
        wynik = wynik.append(getMin()).append("\n"); // mam nadzieję, że to jest to samo co było
        return wynik.toString();
    }
}
