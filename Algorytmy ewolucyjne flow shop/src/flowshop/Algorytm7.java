package flowshop;

import flowshop.Interfejsy.iAlgorytm;
import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iMutacja;
import flowshop.Interfejsy.iOperatorKrzyżowania;
import flowshop.Interfejsy.iOsobnik;
import flowshop.Interfejsy.iZastepowanie;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ListIterator;
import java.util.Random;

/**
 *
 * @author Jakub Banaszewski
 */
public class Algorytm7 implements iAlgorytm {

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

    public Algorytm7(int iloscOsobnikow, iDane d) throws FileNotFoundException, IOException {
        dane = d;
        f = new funkcjaCeluFlowShop();
        this.iloscOsobnikow = iloscOsobnikow;
        pop = new populacja();
        zast = new zastepowanieMaksymalne(dane, f, iloscOsobnikow);
        mut = new MutacjaShift((float) 0.1);
        for (int i = 0; i < iloscOsobnikow; i++) {
            pop.dodajOsobnika(new osobnikFlowShop(d.iloscZadan()));
        }
    }

    public iAlgorytm createAlg(int iloscOsobnikow, iDane d) throws FileNotFoundException, IOException {
        return new Algorytm7(iloscOsobnikow, d);
    }

    public String nazwaAlg() {
        return "Algorytm7 - operator multi + mutacja shift (0.1),  multiselekcja selekcja + zastępowanieTurniej + ocalali";
    }

    public void wybor() {
        sr = new selekcjaRuletka(dane,f);
        popSelect = sr.wybranaPopulacja(pop,pop.rozmiarPopulacji() / 2);
    }

    public void krzyzowanie() {
        oper = new multiOperator();
        popOper = oper.wykonaj(popSelect);
        mut.wynonaj(popOper);
    }

    public void zastepowanie() {
        Random r = new Random();
        int ileOsob = 0;
        populacja ocalali = new populacja();
        int limit = pop.rozmiarPopulacji()/10;
        while (ileOsob < limit) {
            if (pop.rozmiarPopulacji() == 0) break;
            ocalali.dodajOsobnika(pop.usunOsobnika(r.nextInt(pop.rozmiarPopulacji())));
            ileOsob++;
        }
        pop = zast.wykonaj(pop, popOper);
        pop.polaczPopulacje(ocalali);
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
