package flowshop.Interfejsy;

import flowshop.identycznosc;
import flowshop.osobnikFlowShop;
import flowshop.populacja;
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
        ListIterator<iOsobnik> iter = zbiorOsobnikow.popIterator();
        osobnikFlowShop o;
        double min = Double.MAX_VALUE;
        while (iter.hasNext()) {
            o = (osobnikFlowShop) iter.next();
            min = Math.min(f.wartoscFunkcji(o, dane), min);
        }
        return min;
    }
    public double getMax() {
        ListIterator<iOsobnik> iter = zbiorOsobnikow.popIterator();
        osobnikFlowShop o;
        double max = 0;
        while (iter.hasNext()) {
            o = (osobnikFlowShop) iter.next();
            max = Math.max(f.wartoscFunkcji(o, dane), max);
        }
        return max;
    }
    public double getMed() {
        ListIterator<iOsobnik> iter = zbiorOsobnikow.popIterator();
        osobnikFlowShop o;
        double med = 0;
        while (iter.hasNext()) {
            o = (osobnikFlowShop) iter.next();
            med += f.wartoscFunkcji(o, dane) / ((double) zbiorOsobnikow.rozmiarPopulacji());
        }
        return med;
    }
    public double[] wykonajIteracje() {
        wykonajIteracje(1);
        double[] wyn = new double[3];
        wyn[0] = getMax();
        wyn[1] = getMed();
        wyn[2] = getMin();
        return wyn;
    }
    public double[] wykonajIteracje(int iloscIteracji) {
        populacja nowaPopulacja = new populacja();
        for (int i=0; i < iloscIteracji; i++)
        {
            nowaPopulacja = operatorKrzyżowania.wykonaj(selekcja.wykonaj(zbiorOsobnikow));
            zbiorOsobnikow = zastepowanie.wykonaj(zbiorOsobnikow, nowaPopulacja);
        }
        double[] wyn = new double[3];
        wyn[0] = getMax();
        wyn[1] = getMed();
        wyn[2] = getMin();
        return wyn;
    }
}
