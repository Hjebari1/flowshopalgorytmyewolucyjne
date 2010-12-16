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
 * Klasa implementująca metodę ruletki na populacji.
 * Oblicza współczynniki przystosowania osobników, sortuje je, a potem losuje wartość
 * z przedziału [0,1]
 * Przeglądając osobniki sumujemy wpółczynniki przystosowania.
 * Jeśli suma przekroczy wysolowaną wartość dodajemy osobnika,
 * które współczynnik dodaliśmy ostatni.
 * @author Jakub Banaszewski
 */
public class selekcjaRuletka implements iSelekcja {

    private static int zakresLosowania;
    iDane dane;

    public selekcjaRuletka(iDane dane) {
        this.dane = dane;
        zakresLosowania = 1000;
    }

    public selekcjaRuletka(iDane dane, int zakresLos) {
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
                if (odpSize >= rozmiar) {
                    break;
                }
                wspPr = (Double) kluczIter.next();
                List wspList = wspTab.get(wspPr);
                if (wspPr * wspList.size() + sum > prwd) {
                    int poz = (int) Math.round(Math.ceil((prwd - sum) / wspPr)); //long to int!!
                    odpSize++;
                    wybrPop.add((iOsobnik) wspList.get(poz));
                    while (wspPr * (wspList.size() - poz - 1) > prwd) {
                        if (odpSize >= rozmiar) {
                            break;
                        }
                        poz += (int) Math.round(Math.ceil((prwd / wspPr))); //long to int!!
                        if (poz < wspList.size()) {
                            odpSize++;
                            wybrPop.add((iOsobnik) wspList.get(poz));
                        }
                    }
                    sum = (wspList.size() > poz ) ? ((wspList.size() - poz)*wspPr) : 0;
                } else {
                    sum += wspPr * wspList.size();
                }
            }
        }
        return wybrPop;
    }

    /**
     * Funkcja prywatna, która wylicza współczynniki przystosownia osobników
     * Współczynnik jest kluczem do listy iOsobników, których przystosowanie odpowiada kluczowi.
     * Funkcja może być przekokszona (nadmiar użytych struktur).
     * @param daneWejsciowe populacja wejsciowa
     * @return współczynniki przystosowania jako klucze wraz z odpowiednio przyporządkowanymi im iOsobnikami
     */
    protected HashMap wyliczWsp(populacja daneWejsciowe) { // docelowo private, do testów protected
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

        HashMap<Double, List> wsp = new HashMap<Double, List>();
        double wspPr;
        // iterator przewijający osobniki równolegle do przewijanych współczynników
        Iterator popIter = daneWejsciowe.popIterator();

        for (Iterator wartIter = wartosciOsobnikow.iterator(); wartIter.hasNext();) {
            wspPr = ((Double) wartIter.next() - min) / sum;
            // sprawdzenie, czy lista na osobniki istnieje, jeśli nie tworzę nową
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
