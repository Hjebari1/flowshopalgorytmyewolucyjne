package flowshop;

import flowshop.Interfejsy.iAlgorytm;
import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iOperatorKrzyżowania;
import flowshop.Interfejsy.iOsobnik;
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
        sr = new selekcjaRuletka(dane);
    }

    public void krzyzowanie() {
        Random r = new Random();
        if (r.nextBoolean()) {
            oper = new operatorOX(sr.wybranaPopulacja(sr.wybranaPopulacja(p)));
        } else {
            oper = new operatorPMX(sr.wybranaPopulacja(sr.wybranaPopulacja(p)));
        }
        pc = oper.wykonaj();

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
}
