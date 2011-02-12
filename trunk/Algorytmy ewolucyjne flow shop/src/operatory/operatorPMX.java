package operatory;

import flowshop.Interfejsy.iFPopulacjiRozmiar;
import flowshop.Interfejsy.iOsobnik;
import flowshop.Para;
import flowshop.populacja;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Operator krzyżowania osobników PMX (Order Crossover).
 * Dobierany jest przedział, w którym następuje wymiana genów między
 * osobnikami potomnymi.
 * Następnie powstarzające geny w genomie są uzupełniane
 * ich odpowiednikami w drugim genomie.
 * @author Jakub Banaszewski
 */
public class operatorPMX implements iFPopulacjiRozmiar {

    public operatorPMX() {
    }

    /**
     * Funkcja generująca parę potomną na podstawie dwóch osobników rodzicielskich.
     * @param o1 pierwszy rodzic
     * @param o2 drugi rodzic
     * @param poz1 początek przedziału wymiany
     * @param poz2 koniec przedziału wymiany
     * @return Para osobników potomnych
     * @throws CloneNotSupportedException
     * @throws Exception
     */
    public Para<iOsobnik, iOsobnik> krzyzuj(iOsobnik o1, iOsobnik o2, int poz1, int poz2) throws CloneNotSupportedException, Exception {
        if (o1.dlugoscGenomu() != o2.dlugoscGenomu()) {
            throw new Exception("Nierówne genomy do krzyżowania!");
        }
        int size = o1.dlugoscGenomu();
        if (size == 0) {
            throw new Exception("Pusty genom do krzyżowania!");
        }
        iOsobnik wyn1 = o1.makeCopy();
        iOsobnik wyn2 = o2.makeCopy();

        if (Math.abs(poz1 - poz2) > o1.dlugoscGenomu()-Math.abs(poz1 - poz2)) {
            int tmp = poz1;
            poz1 = poz2;
            poz2 = tmp;
        }
        HashMap genyDoZmiany = new HashMap();
        Object tmp, tmp2;
        // poz1 w przedziale, poz2 poza przedzialem!
        for (int i = poz1; i % o1.dlugoscGenomu() != poz2; i++) {
            wyn1.modyfikujGen(i % o1.dlugoscGenomu(), o2.wartoscOsobnika(i % o1.dlugoscGenomu()));
            wyn2.modyfikujGen(i % o1.dlugoscGenomu(), o1.wartoscOsobnika(i % o1.dlugoscGenomu()));
        }
        //sprawdzenie, czy nie przenosimy powtarzajacych sie genów
        for (int i  = poz1; i % o1.dlugoscGenomu() != poz2; i++) {
            if (o1.wartoscOsobnika(i % o1.dlugoscGenomu()) == o2.wartoscOsobnika(i % o1.dlugoscGenomu())) continue;
            if (!genyDoZmiany.containsKey(o1.wartoscOsobnika(i % o1.dlugoscGenomu()))) {
                if (!genyDoZmiany.containsKey(o2.wartoscOsobnika(i % o1.dlugoscGenomu()))) {
                    genyDoZmiany.put(o1.wartoscOsobnika(i % o1.dlugoscGenomu()), o2.wartoscOsobnika(i % o1.dlugoscGenomu()));
                    genyDoZmiany.put(o2.wartoscOsobnika(i % o1.dlugoscGenomu()), o1.wartoscOsobnika(i % o1.dlugoscGenomu()));
                } else {
                    tmp = genyDoZmiany.get(o2.wartoscOsobnika(i % o1.dlugoscGenomu()));
                    genyDoZmiany.put(tmp, o1.wartoscOsobnika(i % o1.dlugoscGenomu()));
                    genyDoZmiany.put(o1.wartoscOsobnika(i % o1.dlugoscGenomu()), tmp);
                    genyDoZmiany.remove(o2.wartoscOsobnika(i % o1.dlugoscGenomu()));
                }
            } else {
                if (!genyDoZmiany.containsKey(o2.wartoscOsobnika(i % o1.dlugoscGenomu()))) {
                    tmp = genyDoZmiany.get(o1.wartoscOsobnika(i % o1.dlugoscGenomu()));
                    genyDoZmiany.put(o2.wartoscOsobnika(i % o1.dlugoscGenomu()), tmp);
                    genyDoZmiany.put(tmp, o2.wartoscOsobnika(i % o1.dlugoscGenomu()));
                    genyDoZmiany.remove(o1.wartoscOsobnika(i % o1.dlugoscGenomu()));
                } else {
                    tmp = genyDoZmiany.get(o1.wartoscOsobnika(i % o1.dlugoscGenomu()));
                    tmp2 = genyDoZmiany.get(o2.wartoscOsobnika(i % o1.dlugoscGenomu()));
                    genyDoZmiany.put(tmp, tmp2);
                    genyDoZmiany.put(tmp2, tmp);
                    genyDoZmiany.remove(o1.wartoscOsobnika(i % o1.dlugoscGenomu()));
                    genyDoZmiany.remove(o2.wartoscOsobnika(i % o1.dlugoscGenomu()));
                }
            }

        }
        //faza PMX
        Object zmGen = null;
        int pozZmian = -1;
        for (int i = poz1; i % o1.dlugoscGenomu() != poz2; i++) {
            zmGen = wyn1.wartoscOsobnika(i % o1.dlugoscGenomu());
            if (genyDoZmiany.containsKey(zmGen)) {
                pozZmian = wyn1.znajdzPozGenuPoza(poz1, poz2, zmGen);
                wyn1.modyfikujGen(pozZmian, genyDoZmiany.get(zmGen));
            }
            zmGen = wyn2.wartoscOsobnika(i % o1.dlugoscGenomu());
            if (genyDoZmiany.containsKey(zmGen)) {
                pozZmian = wyn2.znajdzPozGenuPoza(poz1, poz2, zmGen);
                wyn2.modyfikujGen(pozZmian, genyDoZmiany.get(zmGen));
            }
        }
        return new Para<iOsobnik, iOsobnik>(wyn1, wyn2);
    }

    public populacja wykonaj(populacja zbiorOsobnikow) {
        return wykonaj(zbiorOsobnikow, zbiorOsobnikow.rozmiarPopulacji());

    }

    public populacja wykonaj(populacja p, int rozmiar) {

        Random losPoz = new Random();
        populacja pochodneOsobniki = new populacja();
        Object[] osobniki = p.osobniki().keySet().toArray();
        int size = p.osobniki().size();
        while (pochodneOsobniki.rozmiarPopulacji() < rozmiar) {
            try {
                iOsobnik o1 = (iOsobnik) osobniki[losPoz.nextInt(size)];
                iOsobnik o2 = (iOsobnik) osobniki[losPoz.nextInt(size)];
                int poz1 = losPoz.nextInt(o1.dlugoscGenomu());
                int poz2 = losPoz.nextInt(o2.dlugoscGenomu()); //!!kontrola pozycji ?
                if (poz1 > poz2) {
                    int tmp = poz1;
                    poz1 = poz2;
                    poz2 = tmp;
                }
                Para<iOsobnik, iOsobnik> wynik = krzyzuj(o1, o2, poz1, poz2);
                pochodneOsobniki.dodajOsobnika(wynik.getFirst());
                pochodneOsobniki.dodajOsobnika(wynik.getSecond());
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(operatorOX.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(operatorOX.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pochodneOsobniki;
    }
}
