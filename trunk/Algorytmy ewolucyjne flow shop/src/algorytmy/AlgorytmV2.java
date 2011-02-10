package algorytmy;

import flowshop.Interfejsy.*;
import flowshop.*;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import operatory.*;


/**
 *
 * @author Łukasz Synówka
 * Alternatywna wersja algorytmu AlgorytmV1
 * 
 */
public class AlgorytmV2 extends VAlgorytm {

    int iloscOsobnikow;
    Kataklizm oczyszczacz;
    double min;
    wagiPar wg;


    /**
     * @param iloscOsobnikow rozmiar populacji
     * @param d dane wejsciowe
     */
    public AlgorytmV2(int iloscOsobnikow, iDane d) throws Exception {
        dane = d;
        f = new funkcjaCeluFlowShop();
        this.iloscOsobnikow = iloscOsobnikow;
        zbiorOsobnikow = new populacja();
        wg = new wagiPar((funkcjaCeluFlowShop) f,d,0.0001);
        //zastepowanie = new zastepowanieMinimalne(dane, f, iloscOsobnikow);
        zastepowanie = new multiZastepowanie(dane,f,iloscOsobnikow,0.00001);
        mutacja = new MultiMutacjaWaga(0.001,wg);
        //= new MutacjaZamianaWaga(0.001,wg);
        //mutacja = new MutacjaZamiana(0.5);
        oczyszczacz = new Kataklizm(400,iloscOsobnikow, iloscOsobnikow/10,iloscOsobnikow/2,new MutacjaPrzesuniecieWaga(0.1,wg));
        //selekcja = new SelekcjaSort(dane, f);
        selekcja =  new multiSelekcja(dane,f,0.5);
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
        this.min = Double.MAX_VALUE;
    }

    @Override
    public void wykonajIteracje(int iloscIteracji) {
        populacja nowaPopulacja = new populacja();
        for (int i = 0; i < iloscIteracji; i++) {
            nowaPopulacja = operatorKrzyżowania.wykonaj(selekcja.wykonaj(zbiorOsobnikow));
            nowaPopulacja = mutacja.wykonaj(nowaPopulacja);
            zbiorOsobnikow = zastepowanie.wykonaj(zbiorOsobnikow, nowaPopulacja);
            if ((getMin() < min) ) {
                min = getMin();
                oczyszczacz.reset();
            } else {
                    iOsobnik o1 = zbiorOsobnikow.min(f, dane);
                    zbiorOsobnikow = oczyszczacz.wykonaj(zbiorOsobnikow);
                    iOsobnik o2 = zbiorOsobnikow.min(f, dane);
                    if (f.wartoscFunkcji(o1, dane)<f.wartoscFunkcji(o2, dane)) zbiorOsobnikow.usunOsobnika(o2);
                    try {
                        zbiorOsobnikow.dodajOsobnika(o1);
                    } catch (Exception ex) {
                        Logger.getLogger(AlgorytmV2.class.getName()).log(Level.SEVERE, null, ex);
                    }

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

