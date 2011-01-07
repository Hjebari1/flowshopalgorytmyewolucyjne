package flowshop;

import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iFPopulacjiRozmiar;
import flowshop.Interfejsy.iFunkcjaCelu;
import flowshop.Interfejsy.iOsobnik;
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
public class selekcjaRuletka implements iFPopulacjiRozmiar {

    iDane dane;
    iFunkcjaCelu fCel;

    public selekcjaRuletka(iDane dane, iFunkcjaCelu fCel) {
        this.dane = dane;
        this.fCel = fCel;
    }

    public selekcjaRuletka(iDane dane, int zakresLos) {
        this.dane = dane;
    }

    public populacja wykonaj(populacja p, int rozmiar) {
        populacja wybrPop = new populacja();
        Random los = new Random();
        List<Para<Double, iOsobnik>> wspTab = wyliczWsp(p, fCel);

        int odpSize = 0;
        double sum = 0;
        double prwd = 1.0;
        Para<Double, iOsobnik> tmp = null;
        while (odpSize < rozmiar) {
            sum = 0;
            prwd = los.nextDouble();
            for (Iterator<Para<Double, iOsobnik>> i = wspTab.iterator(); i.hasNext() && sum < prwd;) {
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
    protected List<Para<Double, iOsobnik>> wyliczWsp(populacja daneWejsciowe, iFunkcjaCelu fCelu) { // docelowo private, do testów protected
        LinkedList<Double> wartosciOsobnikow = new LinkedList<Double>();
        double max = 0, tmp, sum = 0;
        // wyliczanie sumy, znajdywanie minumum, obliczanie wartości funkcji celu
        for (Iterator popIter = daneWejsciowe.popIterator(); popIter.hasNext();) {
            tmp = fCelu.wartoscFunkcji((iOsobnik) popIter.next(), dane);
            wartosciOsobnikow.add(tmp);
            if (tmp > max) {
                max = tmp;
            }
            sum += tmp;
        }

        // noralizacja współczynnika
        sum = max * daneWejsciowe.rozmiarPopulacji() - sum;
        ArrayList<Para<Double, iOsobnik>> wyliczoneWsp = new ArrayList<Para<Double, iOsobnik>>();

        double wspPr;
        // iterator przewijający osobniki równolegle do przewijanych współczynników
        Iterator popIter = daneWejsciowe.popIterator();
        if (sum == 0) {                                                 //wszystkie osobniki zwracają równy wynik
            for (; popIter.hasNext();) {
                wspPr = 1.0 / daneWejsciowe.rozmiarPopulacji();
                wyliczoneWsp.add(new Para(wspPr, popIter.next()));
            }
        } else {
            for (Iterator wartIter = wartosciOsobnikow.iterator(); wartIter.hasNext();) {
                wspPr = (max - (Double) wartIter.next()) / sum;
                wyliczoneWsp.add(new Para(wspPr, popIter.next()));
            }
        }
        return wyliczoneWsp;
    }

    public populacja wykonaj(populacja p) {
        return wykonaj(p, p.rozmiarPopulacji());
    }
}
