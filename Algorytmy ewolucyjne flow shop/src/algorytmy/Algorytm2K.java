package algorytmy;

import flowshop.Interfejsy.iAlgorytm;
import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iOperatorKrzyżowania;
import flowshop.Interfejsy.iOsobnik;
import flowshop.funkcjaCeluFlowShop;
import flowshop.operatorOX;
import flowshop.operatorPMX;
import flowshop.osobnikFlowShop;
import flowshop.populacja;
import flowshop.selekcjaRuletka;
import flowshop.zastepowanieTurniej;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ListIterator;
import java.util.Random;

/**
 *
 * @author Łukasz Synówka
 */
public class Algorytm2K implements iAlgorytm {

    populacja p;
    populacja ps;
    populacja pc;
    iDane dane;
    selekcjaRuletka sr;
    iOperatorKrzyżowania oper;
    zastepowanieTurniej zt;
    int iloscOsobnikow;
    funkcjaCeluFlowShop f;

    public Algorytm2K(int iloscOsobnikow, iDane d) throws FileNotFoundException, IOException {
        dane = d;
        f = new funkcjaCeluFlowShop();
        this.iloscOsobnikow = iloscOsobnikow;
        p = new populacja();
        zt = new zastepowanieTurniej(dane, f, iloscOsobnikow);
        for (int i = 0; i < iloscOsobnikow; i++) {
            p.dodajOsobnika(new osobnikFlowShop(d.iloscZadan()));
        }
    }

    public void wybor() {
        sr = new selekcjaRuletka(dane,f);
        ps = sr.wybranaPopulacja(p);
    }

    public void krzyzowanie() {
        Random r = new Random();
        if (r.nextBoolean()) {
            oper = new operatorOX();
        } else {
            oper = new operatorPMX();
        }
        pc = oper.wykonaj(ps);

    }

    public void zastepowanie() {
        p = zt.wykonaj(p, pc);
    }

    @Override
    public String toString() {
        String wynik = "";
        ListIterator<iOsobnik> iter = p.popIterator();

        double min = Double.MAX_VALUE;

        osobnikFlowShop o;
        while (iter.hasNext()) {
            o = (osobnikFlowShop) iter.next();
            min = Math.min(f.wartoscFunkcji(o, dane), min);
            //wynik=wynik.concat(f.wartoscFunkcji(o, dane)+" ");
        }
        wynik = wynik.concat(min + "\n");

        return wynik;

    }

    public iAlgorytm createAlg(int iloscOsobnikow, iDane d) throws FileNotFoundException, IOException {
        return new Algorytm2K(iloscOsobnikow, d);
    }

    public String nazwaAlg() {
        return "Algorytm 2K - tak jak Algorytm2, ale jeszcze z selekcją ruletkową";
    }
    public double getMin()
    {
        ListIterator<iOsobnik> iter = p.popIterator();
        osobnikFlowShop o;
        double min = Double.MAX_VALUE;
        while (iter.hasNext()) {
            o = (osobnikFlowShop) iter.next();
            min = Math.min(f.wartoscFunkcji(o, dane), min);
        }
        return min;
    }
}
