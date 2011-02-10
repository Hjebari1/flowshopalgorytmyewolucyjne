package algorytmy;

import flowshop.Interfejsy.VAlgorytm;
import flowshop.Interfejsy.iDane;
import flowshop.Kataklizm;
import flowshop.MutacjaPrzesuniecie;
import flowshop.NehAlgorytm;
import flowshop.Rewolucja;
import flowshop.SelekcjaSort;
import flowshop.funkcjaCeluFlowShop;
import flowshop.osobnikFlowShop;
import flowshop.populacja;
import flowshop.zastepowanieMinimalne;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import operatory.multiOperator;

/**
 *
 * @author Jakub Banaszewski
 */
public class AlgorytmV1 extends VAlgorytm {

    int iloscOsobnikow;
    Kataklizm oczyszczacz;
    Rewolucja mieszacz;
    double min;

    /**
     * Kostruktor konkretnej implementacji abstrakcyjnej klasy VAlgorytm.
     * @param iloscOsobnikow rozmiar populacji
     * @param d dane wejsciowe
     */
    public AlgorytmV1(int iloscOsobnikow, iDane d) {
        try {
            dane = d;
            min = Double.MAX_VALUE;
            f = new funkcjaCeluFlowShop();
            this.iloscOsobnikow = iloscOsobnikow;
            zbiorOsobnikow = new populacja();
            zastepowanie = new zastepowanieMinimalne(dane, f, iloscOsobnikow, 0.5);
            mutacja = new MutacjaPrzesuniecie(0.5);
            oczyszczacz = new Kataklizm(2000, iloscOsobnikow, 4, 100, new MutacjaPrzesuniecie(0.1));
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
        } catch (Exception ex) {
            Logger.getLogger(AlgorytmV1.class.getName()).log(Level.SEVERE, null, ex);
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
                append(getMax()).append("\n");
        return wynik.toString();
    }
}
