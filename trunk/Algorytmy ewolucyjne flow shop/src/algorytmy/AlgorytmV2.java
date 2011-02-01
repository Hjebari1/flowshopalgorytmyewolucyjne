/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package algorytmy;

import flowshop.Interfejsy.*;
import flowshop.*;
import java.util.Iterator;
import java.util.List;
import operatory.*;


/**
 *
 * @author Łukasz Synówka
 */
public class AlgorytmV2 extends VAlgorytm {

    int iloscOsobnikow;
    Kataklizm oczyszczacz;
    double min;
    wagiPar wg;


    /**
     * Kostruktor konkretnej implementacji abstrakcyjnej klasy VAlgorytm.
     * TODO parametry przekazywane w konstruktorze czy wpisywane na sztywno ?
     * @param iloscOsobnikow rozmiar populacji
     * @param d dane wejsciowe
     */
    public AlgorytmV2(int iloscOsobnikow, iDane d) {
        dane = d;
        f = new funkcjaCeluFlowShop();
        this.iloscOsobnikow = iloscOsobnikow;
        zbiorOsobnikow = new populacja();
        wg = new wagiPar((funkcjaCeluFlowShop) f,d,0.001);
        zastepowanie = new zastepowanieTurniej(dane, f, iloscOsobnikow);
        mutacja = new MultiMutacjaWaga(0.001,wg); 
        //= new MutacjaZamianaWaga(0.001,wg);
        //mutacja = new MutacjaZamiana(0.5);
        oczyszczacz = new Kataklizm(500,iloscOsobnikow, iloscOsobnikow/10,iloscOsobnikow/5,new MutacjaPrzesuniecieWaga(0.1,wg));
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

