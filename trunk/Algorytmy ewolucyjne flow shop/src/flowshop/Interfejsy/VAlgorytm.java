package flowshop.Interfejsy;

import flowshop.identycznosc;
import flowshop.osobnikFlowShop;
import flowshop.populacja;
import java.util.Iterator;
import java.util.ListIterator;

/**
 * Moja propozycja implementacji algorytmów. Warto pewnie jeszcze dodać funkcję
 * znajdywania mediany i max.
 * @author Jakub Banaszewski
 */
public abstract class VAlgorytm {

    protected iFunkcjaPopulacji mutacja = identycznosc.getInstance();
    protected iFunkcjaPopulacji operatorKrzyżowania = identycznosc.getInstance();
    protected iFunkcjaPopulacji selekcja = identycznosc.getInstance();
    protected iZastepowanie zastepowanie = null;
    protected iFunkcjaCelu f = null;
    protected iDane dane = null;
    protected populacja zbiorOsobnikow = null;

    /**
     * Metoda zwracająca wartość minimalną populacji
     * @return Wartość minimalna populacji
     */
    public double getMin() {
        Iterator<iOsobnik> iter = zbiorOsobnikow.popIterator();
        osobnikFlowShop o;
        double min = Double.MAX_VALUE;
        while (iter.hasNext()) {
            o = (osobnikFlowShop) iter.next();
            min = Math.min(f.wartoscFunkcji(o, dane), min);
        }
        return min;
    }
    public double getMax() {
        Iterator<iOsobnik> iter = zbiorOsobnikow.popIterator();
        osobnikFlowShop o;
        double max = 0;
        while (iter.hasNext()) {
            o = (osobnikFlowShop) iter.next();
            max = Math.max(f.wartoscFunkcji(o, dane), max);
        }
        return max;
    }
    public double getMed() {
        Iterator<iOsobnik> iter = zbiorOsobnikow.popIterator();
        osobnikFlowShop o;
        double med = 0;
        while (iter.hasNext()) {
            o = (osobnikFlowShop) iter.next();
            med += f.wartoscFunkcji(o, dane) / ((double) zbiorOsobnikow.szerokoscPopulacji());
        }
        return med;
    }
    public void wykonajIteracje() {
        wykonajIteracje(1);
    }
    public void wykonajIteracje(int iloscIteracji) {
        populacja nowaPopulacja = new populacja();
        for (int i=0; i < iloscIteracji; i++)
        {
            nowaPopulacja = operatorKrzyżowania.wykonaj(selekcja.wykonaj(zbiorOsobnikow));
            nowaPopulacja = mutacja.wykonaj(nowaPopulacja);
            zbiorOsobnikow = zastepowanie.wykonaj(zbiorOsobnikow, nowaPopulacja);
        }
    }
}
