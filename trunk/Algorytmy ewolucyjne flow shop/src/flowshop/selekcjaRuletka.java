package flowshop;

import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iFunkcjaCelu;
import flowshop.Interfejsy.iOsobnik;
import flowshop.Interfejsy.iSelekcja;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * 
 * @author Jakub Banaszewski
 */
public class selekcjaRuletka implements iSelekcja {

    private static int zakresLosowania;
    List<Object> wartosciOsobnikow;
    iDane dane;

    public selekcjaRuletka(iDane dane) {
        wartosciOsobnikow = new LinkedList<Object>();
        this.dane = dane;
        zakresLosowania = 1000;
    }

    public selekcjaRuletka(iDane dane, int zakresLos) {
        wartosciOsobnikow = new LinkedList<Object>();
        this.dane = dane;
        zakresLosowania = zakresLos;
    }

    public List<iOsobnik> wybranaPopulacja(populacja p, int rozmiar) {
        List<iOsobnik> wybrPop = new LinkedList<iOsobnik>();
        Random los = new Random();
        HashMap<Double, List> wspTab = wyliczWsp(p);
        ArrayList klucze = new ArrayList(wspTab.keySet());
        Collections.sort(klucze);
        double wspPr;
        int odpSize = 0;
        double sum = 0;
        double prwd;
        while (odpSize < rozmiar) {
            sum = 0;
            prwd = los.nextDouble();
            for (Iterator kluczIter = klucze.iterator(); kluczIter.hasNext();) {
                if (odpSize >= rozmiar) break;
                wspPr = (Double) kluczIter.next();
                List wspList = wspTab.get(wspPr);
                if (wspPr * wspList.size() + sum > prwd) {
                    int poz = (int) Math.round(Math.ceil((prwd - sum) / wspPr)); //long to int!!
                    odpSize ++;
                    wybrPop.add((iOsobnik) wspList.get(poz));
                    while (wspPr * (wspList.size() - poz - 1) > prwd) {
                        if (odpSize >= rozmiar) break;
                        poz += (int) Math.round(Math.ceil((prwd / wspPr))); //long to int!!
                        if (poz < wspList.size()) {
                            odpSize++;
                            wybrPop.add((iOsobnik) wspList.get(poz));
                        }
                    }
                }
            }
        }
        return wybrPop;
    }

    private HashMap wyliczWsp(populacja daneWejsciowe) {
        iFunkcjaCelu f = new funkcjaCeluFlowShop();
        double min = 10000, tmp, sum = 0; // ustawić stałą w funkcjaCeluFlowShop, która jest duża
        for (Iterator popIter = daneWejsciowe.popIterator(); popIter.hasNext();) {
            tmp = f.wartoscFunkcji((iOsobnik) popIter.next(), dane);
            wartosciOsobnikow.add(tmp);
            if (tmp < min) {
                min = tmp;
            }
            sum += tmp;
        }
        sum -= min * daneWejsciowe.rozmiarPopulacji();
        HashMap<Double, List> wsp = new HashMap<Double, List>();
        int i = 0;
        double wspPr;
        Iterator popIter = daneWejsciowe.popIterator();
        for (Iterator wartIter = wartosciOsobnikow.iterator(); wartIter.hasNext();) {
            wspPr = ((Double) wartIter.next() - min) / sum;
            if (!wsp.get(wspPr).isEmpty()) {
                wsp.get(wspPr).add(popIter.next());
            } else {
                List list = new ArrayList<iOsobnik>();
                list.add(popIter.next());
                wsp.put(wspPr, list);
            }
        }
        return wsp;
    }

    public List<iOsobnik> wybranaPopulacja(populacja p) {
        return wybranaPopulacja(p, p.rozmiarPopulacji());
    }
}
