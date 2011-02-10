package flowshop.Interfejsy;

import flowshop.identycznosc;
import flowshop.osobnikFlowShop;
import flowshop.populacja;
import java.util.Iterator;

/**
 * Klasa abstrakcyjna implementująca algorytm wykonujący obliczenia.
 * Zawiera ona podstawowe operatory i fukcję, które działają na populacji.
 * Klasy rozszerzające tylko przypisują tym właśnie operatorom i funkcjom
 * wybrane metody.
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
    /**
     * Metoda podmieniająca aktualną populację, na której pracuje algorytm
     * @param osobniki Nowa populacja, na której ma działać algorytm.
     */
    public void ustawPopulacje(populacja osobniki) {
        zbiorOsobnikow = osobniki;
    }
    
    public void wykonajIteracje() {
        wykonajIteracje(1);
    }
    /**
     * Funcja wykonująca zadaną ilość iteracji algorytmu.
     * @param iloscIteracji Ilość iteracji do wykonania przez algorytm.
     */
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
