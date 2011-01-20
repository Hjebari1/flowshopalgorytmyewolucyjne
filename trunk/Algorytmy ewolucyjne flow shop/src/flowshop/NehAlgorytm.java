package flowshop;

import flowshop.Interfejsy.iDane;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Jest to algorytm heurestyczny generujące
 * przybliżone optymalne rozwiązanie.
 * Wynik będzie dołączany do populacji początkowej,
 * aby usprawnić zbieżność.
 * @author Jakub Banaszewski
 */
public class NehAlgorytm {

    private class ParaComparator implements Comparator<Para<Double, Integer>> {

        public int compare(Para<Double, Integer> o1, Para<Double, Integer> o2) {
            return Double.compare(o1.getFirst(), o2.getFirst());
        }
    }
    iDane daneWejsciowe;
    List<Integer> porzadek;
    List<Para<Double, Integer>> wyniki;
    /**
     * Metoda, któa dla dwóch prac podaje, którą bardziej opłaca się wykonać
     * pierwszą.
     * @param p1 numer pierwszej pracy
     * @param p2 numer drugiej pracy
     * @return Numer pracy, którą się opłaca wykonać najpierw, oraz koszt wykonania obu prac.
     */
    private Para<Double,Integer> paraPrac(int p1, int p2) {
        double wyn1 = daneWejsciowe.czasZadania(0, p1);
        double wolnyCzas1 = 0;
        double wyn2 = daneWejsciowe.czasZadania(0, p2);
        double wolnyCzas2 = 0;
        for (int i = 0; i < daneWejsciowe.iloscMaszyn(); i++) {
            if (daneWejsciowe.czasZadania(i, p2) < daneWejsciowe.czasZadania(i + 1, p1)
                    - wolnyCzas1) {
                wyn1 += daneWejsciowe.czasZadania(i + 1, p1) - wolnyCzas1;
                wolnyCzas1 = 0;
            } else {
                wyn1 += daneWejsciowe.czasZadania(i, p2);
                wolnyCzas1 += daneWejsciowe.czasZadania(i, p2) - daneWejsciowe.czasZadania(i + 1, p1);

            }
            if (daneWejsciowe.czasZadania(i, p1) < daneWejsciowe.czasZadania(i + 1, p2)
                    - wolnyCzas2) {
                wyn2 += daneWejsciowe.czasZadania(i + 1, p2) - wolnyCzas2;
                wolnyCzas2 = 0;
            } else {
                wyn2 += daneWejsciowe.czasZadania(i, p1);
                wolnyCzas2 += daneWejsciowe.czasZadania(i, p1) - daneWejsciowe.czasZadania(i + 1, p2);
            }

        }
        if (wyn1 < wyn2) {
            return new Para<Double,Integer>(wyn1,p1);
        } else {
            return new Para<Double,Integer>(wyn2,p2);
        }
    }

    public NehAlgorytm(iDane dw) {
        daneWejsciowe = dw;
        porzadek = new ArrayList<Integer>();
        wyniki = new ArrayList<Para<Double, Integer>>();
    }

    public Collection<Integer> wyliczPorzadek() {
        double wynik;
        for (int i = 0; i < daneWejsciowe.iloscZadan(); i++) {
            wynik = 0.0;
            for (int j = 0; j < daneWejsciowe.iloscMaszyn(); j++) {
                wynik += daneWejsciowe.czasZadania(j, i);
            }
            wyniki.add(new Para<Double, Integer>(wynik, i));
        }
        Collections.sort(wyniki, new ParaComparator());

        return null;
    }
}
