package algorytmy;

import flowshop.Interfejsy.iAlgorytm;
import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iMutacja;
import flowshop.Interfejsy.iOperatorKrzyżowania;
import flowshop.Interfejsy.iOsobnik;
import flowshop.Interfejsy.iZastepowanie;
import flowshop.MutacjaZamiana;
import flowshop.funkcjaCeluFlowShop;
import flowshop.operatorPMX;
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
public class Algorytm4 implements iAlgorytm {

    populacja pop;
    populacja popSelect;
    populacja popOper;
    populacja popMut;
    iDane dane;
    selekcjaRuletka sr;
    iOperatorKrzyżowania oper;
    iMutacja mut;
    iZastepowanie zast;
    int iloscOsobnikow;
    funkcjaCeluFlowShop f;

    public Algorytm4(int iloscOsobnikow, iDane d) throws FileNotFoundException, IOException {
        dane = d;
        f = new funkcjaCeluFlowShop();
        this.iloscOsobnikow = iloscOsobnikow;
        pop = new populacja();
        zast = new zastepowanieTurniej(dane, f, iloscOsobnikow);
        mut = new MutacjaZamiana();
        for (int i = 0; i < iloscOsobnikow; i++) {
            pop.dodajOsobnika(new osobnikFlowShop(d.iloscZadan()));
        }
    }

    public iAlgorytm createAlg(int iloscOsobnikow, iDane d) throws FileNotFoundException, IOException {
        return new Algorytm4(iloscOsobnikow, d);
    }

    public String nazwaAlg() {
        return "Algorytm4 - operator PMX + mocna mutacja, selekcja + zastępowanieTurniej";
    }

    public void wybor() {
        sr = new selekcjaRuletka(dane,f);
        popSelect = sr.wybranaPopulacja(pop);
    }

    public void krzyzowanie() {
        oper = new operatorPMX();
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
