package algorytmy;

import flowshop.Interfejsy.VAlgorytm;
import flowshop.Interfejsy.iDane;
import flowshop.Kataklizm;
import flowshop.MutacjaPrzesuniecie;
import flowshop.NehAlgorytm;
import flowshop.SelekcjaSort;
import flowshop.funkcjaCeluFlowShop;
import flowshop.osobnikFlowShop;
import flowshop.populacja;
import flowshop.selekcjaRuletka;
import flowshop.zastepowanieMinimalne;
import flowshop.zastepowanieTurniej;
import java.util.Iterator;
import java.util.List;
import operatory.multiOperator;
import operatory.operatorPMX;

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
        zastepowanie = new zastepowanieMinimalne(dane, f, iloscOsobnikow,0.1);
        mutacja = new MutacjaPrzesuniecie(0.02);
        oczyszczacz = new Kataklizm(2000,iloscOsobnikow, iloscOsobnikow/10,iloscOsobnikow/5,new MutacjaPrzesuniecie(0.1));
        selekcja = new SelekcjaSort(dane, f);
        operatorKrzyżowania = new multiOperator();
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
    public void wykonajIteracje(int iloscIteracji) {
        populacja nowaPopulacja = new populacja();
        for (int i = 0; i < iloscIteracji; i++) {
            nowaPopulacja = operatorKrzyżowania.wykonaj(selekcja.wykonaj(zbiorOsobnikow));
            nowaPopulacja = mutacja.wykonaj(nowaPopulacja);
            zbiorOsobnikow = zastepowanie.wykonaj(zbiorOsobnikow, nowaPopulacja);
            if (getMin() < min) {
                min = getMin();
                oczyszczacz.reset();
            } else {
                oczyszczacz.wykonaj(zbiorOsobnikow);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder wynik = new StringBuilder("");
        wynik = wynik.append(getMin()).append(" ").
                append(getMed()).append(" ").
                append(getMax()).append("\n"); // mam nadzieję, że to jest to samo co było
        return wynik.toString();
    }
}
