package flowshop;

import flowshop.Interfejsy.iOperatorKrzyżowania;
import flowshop.Interfejsy.iOsobnik;
import java.util.HashMap;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jakub Banaszewski
 * Operator krzyżowania osobników OX (Order Crossover)
 */
public class operatorOX extends iOperatorKrzyżowania {

    public operatorOX() {
    }
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

        if (poz1 == poz2) {
            return new Para<iOsobnik, iOsobnik>(o1, o2);
        }
        if (poz1 > poz2) {
            int tmp = poz1;
            poz1 = poz2;
            poz2 = tmp;
        }
        // poz1 w przedziale, poz2 poza przedzialem!
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
        //faza OX
        iOsobnik wyn4 = wyn1.makeCopy(); //trudna numeracja, nieintuicjyjna (jeśli jakaś intuicyjna istnieje)
        iOsobnik wyn3 = wyn2.makeCopy();

        for (int i = 0; i < size; i++) {
            pozZmian = wyn2.znajdzPozGenu(0, size, wyn4.wartoscOsobnika(i));
            if (poz1 <= pozZmian && pozZmian < poz2) {
                wyn4.modyfikujGen(i, iOsobnik.pusto);
            }
            pozZmian = wyn1.znajdzPozGenu(0, size, wyn3.wartoscOsobnika(i));
            if (poz1 <= pozZmian && pozZmian < poz2) {
                wyn3.modyfikujGen(i, iOsobnik.pusto);
            }
        }
        int j = poz2;
        for (int i = 0; i < size; i++) {
            if (wyn4.wartoscOsobnika((i + poz2) % size) == iOsobnik.pusto) {
                continue;
            } else {
                wyn2.modyfikujGen(j % size, wyn4.wartoscOsobnika((i + poz2) % size));
                j++;
            }
        }
        j = poz2;
        for (int i = 0; i < size; i++) {
            if (wyn3.wartoscOsobnika((i + poz2) % size) == iOsobnik.pusto) {
                continue;
            } else {
                wyn1.modyfikujGen(j % size, wyn3.wartoscOsobnika((i + poz2) % size));
                j++;
            }
        }
        return new Para<iOsobnik, iOsobnik>(wyn1, wyn2);
    }

    public populacja wykonaj(populacja zbiorOsobnikow) {
        Random losPoz = new Random();
        populacja pochodneOsobniki = new populacja();
        populacja rodzice = new populacja();
        rodzice.polaczPopulacje(zbiorOsobnikow);

        while (rodzice.rozmiarPopulacji() > 0) {
            try {
                iOsobnik o1 = rodzice.usunOsobnika(losPoz.nextInt(rodzice.rozmiarPopulacji()));
                iOsobnik o2 = rodzice.usunOsobnika(losPoz.nextInt(rodzice.rozmiarPopulacji()));
                int poz1 = losPoz.nextInt(o1.dlugoscGenomu());
                int poz2 = losPoz.nextInt(o2.dlugoscGenomu());
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
