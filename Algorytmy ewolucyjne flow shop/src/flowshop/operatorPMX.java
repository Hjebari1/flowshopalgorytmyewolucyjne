package flowshop;

import flowshop.Interfejsy.iOperatorKrzyżowania;
import flowshop.Interfejsy.iOsobnik;
import java.util.Random;

/**
 *
 * @author Jakub Banaszewski
 * Operator krzyżowania osobników PMX (Order Crossover)
 */
public class operatorPMX implements iOperatorKrzyżowania {

    populacja zbiorOsobnikow = null;

    public operatorPMX(populacja daneWejsciowe) {
        zbiorOsobnikow = daneWejsciowe;
    }

    public void dodajOsobnika(iOsobnik o) throws Exception {
        zbiorOsobnikow.dodajOsobnika(o);
    }

    public void usunOsobnika(iOsobnik o) {
        zbiorOsobnikow.usunOsobnika(o);
    }

    public void wykonaj() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Para<iOsobnik, iOsobnik> krzyzuj(iOsobnik o1, iOsobnik o2) throws CloneNotSupportedException, Exception { //domyślnie private, dla testów public
         if (o1.dlugoscGenomu() != o2.dlugoscGenomu()) throw new Exception("Nierówne genomy do krzyżowania!");
        int size = o1.dlugoscGenomu();
        if (size == 0) throw new Exception("Pusty genom do krzyżowania!");
        iOsobnik wyn1 = o1.makeCopy();
        iOsobnik wyn2 = o2.makeCopy();
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

        return new Para<iOsobnik, iOsobnik>(wyn1, wyn2);
    }
}
