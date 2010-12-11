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

    private iOsobnik krzyzuj(iOsobnik o1, iOsobnik o2, iOsobnik wynik) {
        int size = o1.dlugoscGenomu();
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
        for (int i=0;i<poz1;i++)
            wynik.modyfikujGen(i, o1.wartoscOsobnika(i));
        for (int i=poz1; i < poz2; i++)
            wynik.modyfikujGen(i, o2.wartoscOsobnika(i));
        for (int i=poz2; i < size; i++)
            wynik.modyfikujGen(i, o1.wartoscOsobnika(i));
        //faza PMX
        
        return wynik;
    }
}
