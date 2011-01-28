package flowshop;

import flowshop.Interfejsy.iFPopulacjiRozmiar;
import flowshop.Interfejsy.iOsobnik;
import flowshop.Interfejsy.iOsobnik;
import java.util.List;
import java.util.Random;

/**
 * Seleckja elementów w populacji w sposób losowy.
 * Wyznaczone elementy są ciągle elementami starej populacji
 * @author Jakub Banaszewski
 */
public class SelekcjaRand implements iFPopulacjiRozmiar {

    public populacja wykonaj(populacja p) {
        return p;
    }

    public populacja wykonaj(populacja p, int rozmiar) {
        populacja wyn = new populacja();
        Random r = new Random();
        int ileOsob = 0;
        List<iOsobnik> osobniki = wyn.osobnikiPop();
        while (ileOsob < rozmiar) {
            wyn.dodajOsobnika(osobniki.remove(r.nextInt(osobniki.size())));
            ileOsob++;
        }
        p.polaczPopulacje(wyn);
        return wyn;
    }
}
