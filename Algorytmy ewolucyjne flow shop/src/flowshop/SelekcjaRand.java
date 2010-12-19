package flowshop;

import flowshop.Interfejsy.iSelekcja;
import java.util.Random;

/**
 *
 * @author bejnan
 */
public class SelekcjaRand implements iSelekcja {

    public populacja wybranaPopulacja(populacja p) {
        return p;
    }

    public populacja wybranaPopulacja(populacja p, int rozmiar) {
        populacja wyn = new populacja();
        Random r = new Random();
        int ileOsob = 0;
        while (ileOsob < rozmiar) {
            wyn.dodajOsobnika(p.usunOsobnika(r.nextInt(p.rozmiarPopulacji())));
            ileOsob++;
        }
        p.polaczPopulacje(wyn);
        return wyn;
    }
}
