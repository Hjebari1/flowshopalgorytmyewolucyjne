package flowshop;

import flowshop.Interfejsy.iOperatorKrzyżowania;
import flowshop.Interfejsy.iOsobnik;
import java.util.Random;

/**
 *
 * @author Jakub Banaszewski
 * Operator krzyżowania osobników OX (Order Crossover)
 */
public class operatorOX extends iOperatorKrzyżowania {

    public operatorOX(populacja daneWejsciowe) {
        zbiorOsobnikow = daneWejsciowe;
    }

    public void wykonaj() {
        throw new UnsupportedOperationException("Not supported yet.");
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

        if (poz1 == poz2) {
            return new Para<iOsobnik, iOsobnik>(o1, o2);
        }
        if (poz1 > poz2) {
            int tmp = poz1;
            poz1 = poz2;
            poz2 = tmp;
        }
        // poz1 w przedziale, poz2 poza przedzialem!
        for (int i = poz1; i < poz2; i++) {
            wyn1.modyfikujGen(i, o2.wartoscOsobnika(i));
            wyn2.modyfikujGen(i, o1.wartoscOsobnika(i));
        }


        //faza PMX
        Object zmGen = null;
        int pozZmian = -1;
        for (int i = poz1; i < poz2; i++) {
            zmGen = wyn1.wartoscOsobnika(i);
            pozZmian = wyn1.znajdzPozGenuPoza(poz1, poz2, zmGen);
            wyn1.modyfikujGen(pozZmian, wyn2.wartoscOsobnika(i));
            zmGen = wyn2.wartoscOsobnika(i);
            pozZmian = wyn2.znajdzPozGenuPoza(poz1, poz2, zmGen);
            wyn2.modyfikujGen(pozZmian, wyn1.wartoscOsobnika(i));
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
}
