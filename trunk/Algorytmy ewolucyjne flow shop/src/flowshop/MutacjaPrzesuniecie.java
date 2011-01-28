package flowshop;

import flowshop.Interfejsy.iFPopulacjiRozmiar;
import flowshop.Interfejsy.iOsobnik;
import java.util.Iterator;
import java.util.Random;

/**
 * Mutacja, która przesuwa gen o losową wartość w kolejności.
 * Prawdopodobieństwo wystąpienia mutacji w populacji jest zadane
 * w konstruktorze klasy.
 * @author Jakub Banaszewski
 */
public class MutacjaPrzesuniecie implements iFPopulacjiRozmiar {

    double wsp;

    /**
     * Konstruktor domyślny.
     * @param wsp Prawdopodobieństwo wystąpienia mutacji
     */
    public MutacjaPrzesuniecie(double czestotliwosc) {
        wsp = czestotliwosc;
    }

    /**
     * Dla zadanej populacji funkcja z jednakowym prawdopodobieństwem
     * dla każdego elementu przeprowadza przesunięcie genu o kilka pozycji.
     * @param p zadana populacja
     */
    public populacja wykonaj(populacja p) {
        Random r = new Random();
        for (Iterator<iOsobnik> j = p.popIterator(); j.hasNext();) {
            iOsobnik o = j.next();
            if (r.nextDouble() > wsp) {
                Integer start = r.nextInt(o.dlugoscGenomu());
                Integer meta = r.nextInt(o.dlugoscGenomu());
                int tmp;
                if (start > meta) {
                    tmp = start;
                    start = meta;
                    meta = tmp;
                }
                Object wart = o.wartoscOsobnika(start);
                for (int i = start; i < meta; i++) {
                    o.modyfikujGen(i, o.wartoscOsobnika(i + 1));
                }
                o.modyfikujGen(meta, wart);
            }
        }
        return p;
    }

    public populacja wykonaj(populacja p, int rozmiar) {
        populacja wynik = new populacja();
        Random r = new Random();
        while (wynik.rozmiarPopulacji() < rozmiar) {
            for (Iterator<iOsobnik> j = p.popIterator(); j.hasNext() && wynik.rozmiarPopulacji() < rozmiar;) {
                iOsobnik o = j.next().makeCopy();
                if (r.nextDouble() > wsp) {
                    Integer start = r.nextInt(o.dlugoscGenomu());
                    Integer meta = r.nextInt(o.dlugoscGenomu());
                    int tmp;
                    if (start > meta) {
                        tmp = start;
                        start = meta;
                        meta = tmp;
                    }
                    Object wart = o.wartoscOsobnika(start);
                    for (int i = start; i < meta; i++) {
                        o.modyfikujGen(i, o.wartoscOsobnika(i + 1));
                    }
                    o.modyfikujGen(meta, wart);
                }
                wynik.dodajOsobnika(o);
            }
        }
        return wynik;
    }
}
