package flowshop;

import flowshop.Interfejsy.VAlgorytm;
import flowshop.Interfejsy.iDane;

/**
 * Przykładowa implementacja abstrakcyjnej  klasy algorytmu
 * @author Jakub Banaszewski
 */
public class AlgorytmPrzyklad extends VAlgorytm {

    int iloscOsobnikow;
   /**
    * Kostruktor konkretnej implementacji abstrakcyjnej klasy VAlgorytm.
    * @param iloscOsobnikow rozmiar populacji
    * @param d dane wejsciowe
   */
    public AlgorytmPrzyklad(int iloscOsobnikow, iDane d) throws Exception {
        dane = d;
        f = new funkcjaCeluFlowShop();
        this.iloscOsobnikow = iloscOsobnikow;
        zbiorOsobnikow = new populacja();
        zastepowanie = new zastepowanieMinimalne(dane, f, iloscOsobnikow);
        mutacja = new MutacjaPrzesuniecie((float) 0.1);
        for (int i = 0; i < iloscOsobnikow; i++) {
            zbiorOsobnikow.dodajOsobnika(new osobnikFlowShop(d.iloscZadan()));
        }
    }

      
    @Override
    public String toString() {
        StringBuilder wynik = new StringBuilder("");
        wynik = wynik.append(getMin()).append("\n");
        return wynik.toString();
    }
}
