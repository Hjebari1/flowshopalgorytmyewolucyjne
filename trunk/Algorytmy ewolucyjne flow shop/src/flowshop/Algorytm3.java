package flowshop;

import flowshop.Interfejsy.iAlgorytm;
import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iMutacja;
import flowshop.Interfejsy.iOperatorKrzyżowania;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Jakub Banaszewski
 */
public class Algorytm3 implements iAlgorytm {

    populacja pop;
    populacja popSelect;
    populacja popOper;
    populacja popMut;
    iDane dane;
    selekcjaRuletka sr;
    iOperatorKrzyżowania oper;
    iMutacja mut;
    zastepowanieMaksymalne zm;
    int iloscOsobnikow;
    funkcjaCeluFlowShop f;

    public Algorytm3(int iloscOsobnikow, iDane d) throws FileNotFoundException, IOException {
        dane = d;
        f = new funkcjaCeluFlowShop();
        this.iloscOsobnikow = iloscOsobnikow;
        pop = new populacja();
        zm = new zastepowanieMaksymalne(dane, f, iloscOsobnikow);
        mut = new MutacjaK();
        for (int i = 0; i < iloscOsobnikow; i++) {
            pop.dodajOsobnika(new osobnikFlowShop(d.iloscZadan()));
        }
    }

    public iAlgorytm createAlg(int iloscOsobnikow, iDane d) throws FileNotFoundException, IOException {
        return new Algorytm1(iloscOsobnikow, d);
    }

    public String nazwaAlg() {
        return "Algorytm3 - operator OX, selekcja + zastępowanieMaksymalne";
    }

    public void wybor() {
        sr = new selekcjaRuletka(dane,f);
        popSelect = sr.wybranaPopulacja(pop);
    }

    public void krzyzowanie() {
        oper = new operatorOX();
        popOper = oper.wykonaj(popSelect);
        mut.wynonaj(popOper);
    }

    public void zastepowanie() {
        pop = zm.wykonaj(pop, popOper);
    }
}
