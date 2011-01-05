package flowshop;

import flowshop.Interfejsy.iMutacja;
import flowshop.Interfejsy.iOsobnik;
import java.util.Random;

/**
 * Mutacja, która przesuwa gen o losową wartość w kolejności.
 * Prawdopodobieństwo wystąpienia mutacji w populacji jest zadane
 * w konstruktorze klasy.
 * @author Jakub Banaszewski
 */
public class MutacjaShift implements iMutacja {

    float wspol;

    /**
     * Konstruktor domyślny.
     * @param wsp Prawdopodobieństwo wystąpienia mutacji
     */
    public MutacjaShift(float wsp) {
        wspol = wsp;
    }
    /**
     * Dla zadanej populacji funkcja z jednakowym prawdopodobieństwem
     * dla każdego elementu przeprowadza przesunięcie genu o kilka pozycji.
     * @param p zadana populacja
     */
    public void wynonaj(populacja p) {
        Random r = new Random();
        for (int iloscMutacji = (p.rozmiarPopulacji() * wspol > 0) ? r.nextInt(Math.round(p.rozmiarPopulacji() * wspol)) : 1; iloscMutacji > 0; iloscMutacji--) {
            int a = r.nextInt(p.rozmiarPopulacji());
            iOsobnik o = p.usunOsobnika(a);
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
            p.dodajOsobnika(o);
        }
    }
}
