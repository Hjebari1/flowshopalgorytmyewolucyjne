package flowshop;

import flowshop.Interfejsy.iOperatorKrzyżowania;
import flowshop.Interfejsy.iOsobnik;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jakub Banaszewski
 * Operator krzyżowania osobników PMX (Order Crossover)
 */
public class operatorPMX extends iOperatorKrzyżowania {

    public operatorPMX(populacja daneWejsciowe) {
        zbiorOsobnikow = daneWejsciowe;
    }

    public Para<iOsobnik, iOsobnik> krzyzuj(iOsobnik o1, iOsobnik o2, int poz1, int poz2) throws CloneNotSupportedException, Exception { //domyślnie private, dla testów public
        if (o1.dlugoscGenomu() != o2.dlugoscGenomu()) {
            throw new Exception("Nierówne genomy do krzyżowania!");
        }
        int size = o1.dlugoscGenomu();
        if (size == 0) {
            throw new Exception("Pusty genom do krzyżowania!");
        }
        iOsobnik wyn1 = o1.makeCopy();
        iOsobnik wyn2 = o2.makeCopy();

        if (poz1 > poz2) {
            int tmp = poz1;
            poz1 = poz2;
            poz2 = tmp;
        }
        HashMap genyDoZmiany = new HashMap();
        Object tmp, tmp2;
        // poz1 w przedziale, poz2 poza przedzialem!
        for (int i = poz1; i < poz2; i++) {
            wyn1.modyfikujGen(i, o2.wartoscOsobnika(i));
            wyn2.modyfikujGen(i, o1.wartoscOsobnika(i));
        }
        //sprawdzenie, czy nie przenosimy powtarzajacych sie genów
        for (int i = poz1; i < poz2; i++) {
            if (o1.wartoscOsobnika(i) == o2.wartoscOsobnika(i)) continue;
            if (!genyDoZmiany.containsKey(o1.wartoscOsobnika(i))) {
                if (!genyDoZmiany.containsKey(o2.wartoscOsobnika(i))) {
                    genyDoZmiany.put(o1.wartoscOsobnika(i), o2.wartoscOsobnika(i));
                    genyDoZmiany.put(o2.wartoscOsobnika(i), o1.wartoscOsobnika(i));
                } else {
                    tmp = genyDoZmiany.get(o2.wartoscOsobnika(i));
                    genyDoZmiany.put(tmp, o1.wartoscOsobnika(i));
                    genyDoZmiany.put(o1.wartoscOsobnika(i), tmp);
                    genyDoZmiany.remove(o2.wartoscOsobnika(i));
                }
            } else {
                if (!genyDoZmiany.containsKey(o2.wartoscOsobnika(i))) {
                    tmp = genyDoZmiany.get(o1.wartoscOsobnika(i));
                    genyDoZmiany.put(o2.wartoscOsobnika(i), tmp);
                    genyDoZmiany.put(tmp, o2.wartoscOsobnika(i));
                    genyDoZmiany.remove(o1.wartoscOsobnika(i));
                } else {
                    tmp = genyDoZmiany.get(o1.wartoscOsobnika(i));
                    tmp2 = genyDoZmiany.get(o2.wartoscOsobnika(i));
                    genyDoZmiany.put(tmp, tmp2);
                    genyDoZmiany.put(tmp2, tmp);
                    genyDoZmiany.remove(o1.wartoscOsobnika(i));
                    genyDoZmiany.remove(o2.wartoscOsobnika(i));
                }
            }

        }
        //faza PMX
        Object zmGen = null;
        int pozZmian = -1;
        for (int i = poz1; i < poz2; i++) {
            zmGen = wyn1.wartoscOsobnika(i);
            if (genyDoZmiany.containsKey(zmGen)) {
                pozZmian = wyn1.znajdzPozGenuPoza(poz1, poz2, zmGen);
                wyn1.modyfikujGen(pozZmian, genyDoZmiany.get(zmGen));
            }
            zmGen = wyn2.wartoscOsobnika(i);
            if (genyDoZmiany.containsKey(zmGen)) {
                pozZmian = wyn2.znajdzPozGenuPoza(poz1, poz2, zmGen);
                wyn2.modyfikujGen(pozZmian, genyDoZmiany.get(zmGen));
            }
        }
        return new Para<iOsobnik, iOsobnik>(wyn1, wyn2);
    }

    public populacja wykonaj() {
        Random losPoz = new Random();
        populacja pochodneOsobniki = new populacja();
        populacja rodzice = new populacja();
        rodzice.polaczPopulacje(zbiorOsobnikow);
        while (rodzice.rozmiarPopulacji() > 0) {
            try {
                iOsobnik o1 = rodzice.usunOsobnika(losPoz.nextInt(rodzice.rozmiarPopulacji()));
                iOsobnik o2 = rodzice.usunOsobnika(losPoz.nextInt(rodzice.rozmiarPopulacji()));
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
                for (int j = 1; j
                        <= 9; j++) {
                    if (wynik.getFirst().znajdzPozGenu(0, 9, j) == 9) {
                        System.out.println(wynik.getFirst());
                        System.out.println(wynik.getSecond());
                        System.out.println(o1);
                        System.out.println(o2);
                        System.out.println(poz1);
                        System.out.println(poz2);
                    }
                    if (wynik.getSecond().znajdzPozGenu(0, 9, j) == 9) {
                        System.out.println(wynik.getSecond());
                        System.out.println(o1);
                        System.out.println(o2);
                        System.out.println(poz1);
                        System.out.println(poz2);
                    }

                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(operatorOX.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(operatorOX.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pochodneOsobniki;

    }
}