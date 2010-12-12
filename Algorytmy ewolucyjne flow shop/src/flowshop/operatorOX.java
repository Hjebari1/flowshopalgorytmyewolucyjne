package flowshop;

import flowshop.Interfejsy.iOperatorKrzyżowania;
import flowshop.Interfejsy.iOsobnik;
import java.util.Random;

/**
 *
 * @author Jakub Banaszewski
 * Operator krzyżowania osobników OX (Order Crossover)
 */
public class operatorOX implements iOperatorKrzyżowania {

    public void dodajOsobnika(iOsobnik o) throws Exception {
    }

    public void usunOsobnika(iOsobnik o) {
    }

    public void wykonaj() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private Para<iOsobnik, iOsobnik> krzyzuj(iOsobnik o1, iOsobnik o2) throws CloneNotSupportedException {
        int size = o1.dlugoscGenomu();
        iOsobnik wyn1 = (iOsobnik) o1.makeCopy();
        iOsobnik wyn2 = (iOsobnik) o2.makeCopy();
        Random ktorePola = new Random();
        int poz1 = ktorePola.nextInt(size);
        int poz2 = ktorePola.nextInt(size);
        while (poz1 == poz2) {
            poz2 = ktorePola.nextInt(size); //!! TODO:: to tak nie można
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
            pozZmian = wyn1.znajdzPozGenuPoza(poz1, poz2, zmGen);
            wyn1.modyfikujGen(pozZmian, wyn1.wartoscOsobnika(i));
        }
        //faza OX
        iOsobnik wyn4 = wyn1.makeCopy();
        iOsobnik wyn3 = wyn2.makeCopy();

        for (int i = 1; i < size; i++) {
            pozZmian = wyn2.znajdzPozGenu(0, size, wyn4.wartoscOsobnika(i));
            if (poz1 <= pozZmian && pozZmian < poz2) {
                wyn4.modyfikujGen(i,null);
            }
            pozZmian = wyn1.znajdzPozGenu(0, size, wyn3.wartoscOsobnika(i));
            if (poz1 <= pozZmian && pozZmian < poz2) {
                wyn3.modyfikujGen(i,null);
            }
            
            int j = 0;
            for (i = 0; i < size ; i ++)
            {
                if (wyn4.wartoscOsobnika((i + poz2) % size) == null) continue;
                else {
                    wyn1.modyfikujGen((poz2 + j) % size ,wyn4.wartoscOsobnika((i + poz2)% size));
                    j++;
                    }
            }
            for (i = 0; i < size ; i ++)
            {
                if (wyn3.wartoscOsobnika((i + poz2) % size) == null) continue;
                else {
                    wyn2.modyfikujGen((poz2 + j) % size ,wyn3.wartoscOsobnika((i + poz2)% size));
                    j++;
                    }
            }

        }
        return new Para<iOsobnik, iOsobnik>(wyn1, wyn2);
    }
}
