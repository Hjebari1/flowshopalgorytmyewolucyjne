package flowshop;

import flowshop.Interfejsy.iDane;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
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
            return Double.compare(o2.getFirst(),o1.getFirst());
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
    private double  paraPrac(int p1, int p2) {
        double wyn1 = daneWejsciowe.czasZadania(0, p1);
        double wolnyCzas1 = 0;
        for (int i = 0; i < daneWejsciowe.iloscMaszyn()-1; i++) {
            if (daneWejsciowe.czasZadania(i, p2) < daneWejsciowe.czasZadania(i + 1, p1)
                    - wolnyCzas1) {
                wyn1 += daneWejsciowe.czasZadania(i + 1, p1) - wolnyCzas1;
                wolnyCzas1 = 0;
            } else {
                wyn1 += daneWejsciowe.czasZadania(i, p2);
                wolnyCzas1 += daneWejsciowe.czasZadania(i, p2) - daneWejsciowe.czasZadania(i + 1, p1);
            }
        }
        return wyn1+daneWejsciowe.czasZadania(daneWejsciowe.iloscMaszyn()-1, p2);
    }
    public NehAlgorytm(iDane dw) {
        daneWejsciowe = dw;
        porzadek = new ArrayList<Integer>();
        wyniki = new ArrayList<Para<Double, Integer>>();
    }
     /**
     * Funkcja, która dla konkretnej permutacji prac oblicza
     * jego funcję celu.
     * @param kolejnosc Badany element
     * @return wartość liczbowa będąca wynikiem funkcji celu
     */
    private double wartoscFunkcji(List kolejnosc)
    {
        int index = 0;
        double q[][] = new double[daneWejsciowe.iloscMaszyn()][kolejnosc.size()];


        index = (Integer)kolejnosc.get(0);
        q[0][0] = daneWejsciowe.czasZadania(0,index);

        for (int i=1;i<daneWejsciowe.iloscMaszyn();i++)
            q[i][0] = daneWejsciowe.czasZadania(i,index)+q[i-1][0];


        for (int i=1;i<kolejnosc.size();i++)
        {

            index = (Integer)kolejnosc.get(i);
            q[0][i] = q[0][i-1] + daneWejsciowe.czasZadania(0,index);

            for (int j=1;j<daneWejsciowe.iloscMaszyn();j++)
            {
                index = (Integer)kolejnosc.get(i);
                q[j][i] = Math.max(q[j-1][i],q[j][i-1]) + daneWejsciowe.czasZadania(j,index);
            }
        }
        return q[daneWejsciowe.iloscMaszyn()-1][kolejnosc.size()-1];
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
        if (wyniki.size() == 1)
        {
            porzadek.add(0);
            return porzadek;
        }
        Para<Double,Integer> w1,w2,w3;
        w1 = wyniki.remove(0);
        w2 = wyniki.remove(0);
        if (paraPrac(w1.getSecond(), w2.getSecond()) > paraPrac(w2.getSecond(),w1.getSecond()))
        {
            porzadek.add(w2.getSecond());
            porzadek.add(w1.getSecond());
        }
        else
        {
            porzadek.add(w1.getSecond());
            porzadek.add(w2.getSecond());
        }
        double min,test;
        int pozMin;
        Integer workNum;
        for (Iterator<Para<Double,Integer>> itWyn = wyniki.iterator();itWyn.hasNext();)
        {
            min = Double.MAX_VALUE;
            pozMin = porzadek.size();
            workNum = itWyn.next().getSecond();
            for (int i = 0;i <= porzadek.size(); i++)
            {
                porzadek.add(i, workNum);
                test = wartoscFunkcji(porzadek);
                if (test < min)
                {
                    min = test;
                    pozMin = i;
                }
                porzadek.remove(i);
            }
            porzadek.add(pozMin,workNum);
        }
        return porzadek;
    }

    
}
