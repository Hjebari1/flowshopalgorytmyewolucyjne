package flowshop;

import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iFunkcjaCelu;
import flowshop.Interfejsy.iOsobnik;
import flowshop.Interfejsy.iSelekcja;
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

    public List<iOsobnik> wybranaPopulacja(populacja p) {
        List<iOsobnik> wybrPop = new LinkedList<iOsobnik>();
        Random los = new Random();
        double[] wsp = wyliczWsp(p);
        int i = 0;
        for (Iterator popIter = p.popIterator(); popIter.hasNext();) {
            if (los.nextDouble() < wsp[i++])
                wybrPop.add((iOsobnik) popIter.next());
            else
                popIter.next();
        }

        return wybrPop;
    }

    private double[] wyliczWsp(populacja daneWejsciowe) {
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
        sum -= min*daneWejsciowe.rozmiarPopulacji();
        double[] wsp = new double[daneWejsciowe.rozmiarPopulacji()];
        int i = 0;
        for (Iterator wartIter = wartosciOsobnikow.iterator(); wartIter.hasNext();) {
            wsp[i++] = ((Double) wartIter.next() - min) / sum;
        }
        return wsp;
    }
}
