package flowshop;

import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iFunkcjaCelu;
import flowshop.Interfejsy.iOsobnik;
import flowshop.Interfejsy.iSelekcja;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Klasa implementująca metodę ruletki na populacji.
 * Oblicza współczynniki przystosowania osobników, sortuje je, a potem losuje wartość
 * z przedziału [0,1]
 * Przeglądając osobniki sumujemy wpółczynniki przystosowania.
 * Jeśli suma przekroczy wysolowaną wartość dodajemy osobnika,
 * które współczynnik dodaliśmy ostatni.
 * @author Jakub Banaszewski
 */
public class selekcjaRuletka implements iSelekcja {

    iDane dane;

    public selekcjaRuletka(iDane dane) {
        this.dane = dane;
    }

    public selekcjaRuletka(iDane dane, int zakresLos) {
        this.dane = dane;
    }

    public populacja wybranaPopulacja(populacja p, int rozmiar) {
        populacja wybrPop = new populacja();
        Random los = new Random();
        List<Para<Double, iOsobnik>> wspTab = wyliczWsp(p);

        double wspPr;
        int odpSize = 0;
        double sum = 0;
        double prwd;
        Para<Double,iOsobnik> tmp = null;
        while (odpSize < rozmiar) {
            sum = 0;
            prwd = los.nextDouble();
            for (Iterator<Para<Double,iOsobnik>> i = wspTab.iterator(); i.hasNext() && sum < prwd;) {
                tmp = i.next();
                sum += tmp.getFirst();
            }
            wybrPop.dodajOsobnika(tmp.getSecond());
            odpSize++;
        }
        return wybrPop;
    }

    /**
     * Funkcja prywatna, która wylicza współczynniki przystosownia osobników
     * @param daneWejsciowe populacja wejsciowa
     * @return Para współczynnik, osobnik
     */
    protected List<Para<Double, iOsobnik>> wyliczWsp(populacja daneWejsciowe) { // docelowo private, do testów protected
        iFunkcjaCelu f = new funkcjaCeluFlowShop(); //funkcja celu nie powinna być parametrem ?
        LinkedList<Double> wartosciOsobnikow = new LinkedList<Double>();
        double min = Double.MAX_VALUE, tmp, sum = 0;
        // wyliczanie sumy, znajdywanie minumum, obliczanie wartości funkcji celu
        for (Iterator popIter = daneWejsciowe.popIterator(); popIter.hasNext();) {
            tmp = f.wartoscFunkcji((iOsobnik) popIter.next(), dane);
            wartosciOsobnikow.add(tmp);
            if (tmp < min) {
                min = tmp;
            }
            sum += tmp;
        }
        // noralizacja współczynnika
        sum -= min * daneWejsciowe.rozmiarPopulacji();

        ArrayList<Para<Double, iOsobnik>> wsp = new ArrayList<Para<Double, iOsobnik>>();

        double wspPr;
        // iterator przewijający osobniki równolegle do przewijanych współczynników
        Iterator popIter = daneWejsciowe.popIterator();

        for (Iterator wartIter = wartosciOsobnikow.iterator(); wartIter.hasNext();) {
            wspPr = ((Double) wartIter.next() - min) / sum;
            wsp.add(new Para(wspPr, popIter.next()));
        }
        return wsp;
    }

    public populacja wybranaPopulacja(populacja p) {
        return wybranaPopulacja(p, p.rozmiarPopulacji());
    }
}
