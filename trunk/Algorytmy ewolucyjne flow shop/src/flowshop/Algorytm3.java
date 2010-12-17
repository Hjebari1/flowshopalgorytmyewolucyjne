package flowshop;

import flowshop.Interfejsy.iAlgorytm;
import flowshop.Interfejsy.iDane;
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
    iDane dane;
    selekcjaRuletka sr;
    iOperatorKrzyżowania oper;
    zastepowanieMaksymalne zm;
    int iloscOsobnikow;
    funkcjaCeluFlowShop f;

    public Algorytm3(int iloscOsobnikow, iDane d) throws FileNotFoundException, IOException {
        dane = d;
        f = new funkcjaCeluFlowShop();
        this.iloscOsobnikow = iloscOsobnikow;
        pop = new populacja();
        zm = new zastepowanieMaksymalne(dane, f, iloscOsobnikow);
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
    }

    public void zastepowanie() {
        pop = zm.wykonaj(pop, popOper);
    }
}
