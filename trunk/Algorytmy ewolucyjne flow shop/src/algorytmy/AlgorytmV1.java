package algorytmy;

import flowshop.Interfejsy.VAlgorytm;
import flowshop.Interfejsy.iDane;
import flowshop.Kataklizm;
import flowshop.MutacjaPrzesuniecie;
import flowshop.NehAlgorytm;
import flowshop.funkcjaCeluFlowShop;
import flowshop.osobnikFlowShop;
import flowshop.populacja;
import flowshop.selekcjaRuletka;
import flowshop.zastepowanieMaksymalne;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jakub Banaszewski
 */
public class AlgorytmV1 extends VAlgorytm {

    int iloscOsobnikow;
    Kataklizm oczyszczacz;
    double min;

    /**
     * Kostruktor konkretnej implementacji abstrakcyjnej klasy VAlgorytm.
     * TODO parametry przekazywane w konstruktorze czy wpisywane na sztywno ?
     * @param iloscOsobnikow rozmiar populacji
     * @param d dane wejsciowe
     */
    public AlgorytmV1(int iloscOsobnikow, iDane d) {
        dane = d;
        f = new funkcjaCeluFlowShop();
        this.iloscOsobnikow = iloscOsobnikow;
        zbiorOsobnikow = new populacja();
        zastepowanie = new zastepowanieMaksymalne(dane, f, iloscOsobnikow);
        mutacja = new MutacjaPrzesuniecie((float) 0.1);
        oczyszczacz = new Kataklizm(500, iloscOsobnikow);
        selekcja = new selekcjaRuletka(dane, f);
        NehAlgorytm nehAlg = new NehAlgorytm(d);
        List<Integer> startowyOsobnik = nehAlg.wyliczPorzadek();
        int[] genom = new int[d.iloscZadan()];
        int licznik = 0;
        for (Iterator<Integer> i = startowyOsobnik.iterator(); i.hasNext();) {
            genom[licznik++] = i.next();
        }
        zbiorOsobnikow.dodajOsobnika(new osobnikFlowShop(d.iloscZadan(), genom));
        for (int i = 1; i < iloscOsobnikow; i++) {
            zbiorOsobnikow.dodajOsobnika(new osobnikFlowShop(d.iloscZadan()));
        }
    }

    @Override
    public double[] wykonajIteracje(int iloscIteracji) {
        populacja nowaPopulacja = new populacja();
        for (int i = 0; i < iloscIteracji; i++) {
            nowaPopulacja = operatorKrzyżowania.wykonaj(selekcja.wykonaj(zbiorOsobnikow));
            zbiorOsobnikow = zastepowanie.wykonaj(zbiorOsobnikow, nowaPopulacja);
            if (getMin() < min) {
                min = getMin();
                oczyszczacz.reset();
            } else {
                oczyszczacz.wykonaj(zbiorOsobnikow);
            }
        }
        double[] wyn = new double[3]; //TODO przepisac to do toString'a;
        wyn[0] = getMax();
        wyn[1] = getMed();
        wyn[2] = getMin();
        return wyn;
    }

    @Override
    public String toString() {
        StringBuilder wynik = new StringBuilder("");
        wynik = wynik.append(getMin()).append("\n"); // mam nadzieję, że to jest to samo co było
        return wynik.toString();
    }
}
