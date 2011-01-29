package operatory;

import flowshop.Interfejsy.iFPopulacjiRozmiar;
import flowshop.Interfejsy.iFunkcjaPopulacji;

import flowshop.Interfejsy.iOsobnik;
import flowshop.populacja;
import java.util.LinkedList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Operator krzyżowania CX. Krzyżowanie polegające na znadywaniu cyklu w jednym genomie
 * i przepisywaniu pozostałych pozycji z drugiego genomu.
 * @author Jakub Banaszewski
 */
public class operatorCX implements iFPopulacjiRozmiar {

    public operatorCX() {
    }

    public iOsobnik krzyzuj(iOsobnik o1, iOsobnik o2) throws CloneNotSupportedException, Exception {
        Random r = new Random();
        if (o1.dlugoscGenomu() != o2.dlugoscGenomu()) {
            throw new Exception("Nierówne genomy do krzyżowania!");
        }
        int size = o1.dlugoscGenomu();
        if (size == 0) {
            throw new Exception("Pusty genom do krzyżowania!");
        }
        iOsobnik wyn = o1.makeCopy();
        int i = 0;
        int start = r.nextInt(size);
        LinkedList<Integer> listaCyklu = new LinkedList<Integer>();
        if (o1.equals(o2)) {
            return o1;
        } else {
            while (o1.wartoscOsobnika(start) == o2.wartoscOsobnika(start)) {
                start = r.nextInt(size);
            }
        }
        listaCyklu.add(start);
        i = start;
        do {
            i = o1.znajdzPozGenu(0, size, o2.wartoscOsobnika(i));
            if (i == o1.dlugoscGenomu()) {
                throw new Exception("Nie znaleziono genu!!" + o1.toString());
            }
            listaCyklu.add(new Integer(i));
        } while (i != start);

        for (int j = 0; j < size; j++) {
            wyn.modyfikujGen(j, listaCyklu.contains(j) ? o1.wartoscOsobnika(j) : o2.wartoscOsobnika(j));
        }
        return wyn;
    }

    public populacja wykonaj(populacja zbiorOsobnikow) {
        return wykonaj(zbiorOsobnikow, zbiorOsobnikow.rozmiarPopulacji());
    }

    public populacja wykonaj(populacja p, int rozmiar) {
        Random losPoz = new Random();
        populacja pochodneOsobniki = new populacja();
        Object[] osobniki = (iOsobnik[]) p.osobniki().keySet().toArray();
        int size = p.osobniki().size();
        while (pochodneOsobniki.rozmiarPopulacji() < rozmiar) {
            try {
                iOsobnik o1 = (iOsobnik) osobniki[losPoz.nextInt(size)];
                iOsobnik o2 = (iOsobnik) osobniki[losPoz.nextInt(size)];
                iOsobnik wynik = krzyzuj(o1, o2);
                pochodneOsobniki.dodajOsobnika(wynik);
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(operatorOX.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(operatorOX.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pochodneOsobniki;
    }
}
