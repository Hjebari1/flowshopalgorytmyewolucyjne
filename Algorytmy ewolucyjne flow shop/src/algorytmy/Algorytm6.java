package algorytmy;

import flowshop.Interfejsy.iAlgorytm;
import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iMutacja;
import flowshop.Interfejsy.iOperatorKrzyżowania;
import flowshop.Interfejsy.iOsobnik;
import flowshop.Interfejsy.iZastepowanie;
import flowshop.MutacjaPrzesuniecie;
import flowshop.SelekcjaRand;
import flowshop.funkcjaCeluFlowShop;
import flowshop.multiOperator;
import flowshop.osobnikFlowShop;
import flowshop.populacja;
import flowshop.selekcjaRuletka;
import flowshop.zastepowanieTurniej;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ListIterator;

/**
 *
 * @author Jakub Banaszewski
 */
public class Algorytm6 implements iAlgorytm {

    populacja pop;
    populacja popSelect;
    populacja popOper;
    populacja popMut;
    iDane dane;
    selekcjaRuletka sr;
    SelekcjaRand sr2;
    iOperatorKrzyżowania oper;
    iMutacja mut;
    iZastepowanie zast;
    int iloscOsobnikow;
    funkcjaCeluFlowShop f;

    public Algorytm6(int iloscOsobnikow, iDane d) throws FileNotFoundException, IOException {
        dane = d;
        f = new funkcjaCeluFlowShop();
        this.iloscOsobnikow = iloscOsobnikow;
        pop = new populacja();
        zast = new zastepowanieTurniej(dane, f, iloscOsobnikow);
        mut = new MutacjaPrzesuniecie((float) 0.25);
        for (int i = 0; i < iloscOsobnikow; i++) {
            pop.dodajOsobnika(new osobnikFlowShop(d.iloscZadan()));
        }
    }

    public iAlgorytm createAlg(int iloscOsobnikow, iDane d) throws FileNotFoundException, IOException {
        return new Algorytm6(iloscOsobnikow, d);
    }

    public String nazwaAlg() {
        return "Algorytm6 - operator multi + mutacja shift,  multiselekcja selekcja + zastępowanieTurniej";
    }

    public void wybor() {
        sr = new selekcjaRuletka(dane,f);
        sr2 = new SelekcjaRand();
        popSelect = sr.wybranaPopulacja(pop,pop.rozmiarPopulacji() / 2);
        popSelect.polaczPopulacje(sr2.wybranaPopulacja(pop, (pop.rozmiarPopulacji()/2)));
    }

    public void krzyzowanie() {
        oper = new multiOperator();
        popOper = oper.wykonaj(popSelect);
        mut.wynonaj(popOper);
    }

    public void zastepowanie() {
        pop = zast.wykonaj(pop, popOper);
    }

    public double getMin()
    {
        ListIterator<iOsobnik> iter = pop.popIterator();
        osobnikFlowShop o;
        double min = Double.MAX_VALUE;
        while (iter.hasNext()) {
            o = (osobnikFlowShop) iter.next();
            min = Math.min(f.wartoscFunkcji(o, dane), min);
        }
        return min;
    }
    @Override
    public String toString() {
        String wynik = "";
        wynik = wynik.concat(getMin() + "\n");
        return wynik;
    }
}
