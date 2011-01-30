package flowshop;

import flowshop.Interfejsy.iFPopulacjiRozmiar;
import flowshop.Interfejsy.iFunkcjaPopulacji;
import flowshop.Interfejsy.iOsobnik;
import java.util.Iterator;
import java.util.Set;

/**
 * Klasa reprezentująca funcję znajdującą unikalne osobniki i zwracającą ich zbiór.
 * @author Jakub Banaszewski
 */
public class Kataklizm implements iFunkcjaPopulacji {

    int iloscIteracji;
    int ileJeszcze;
    int orgRozPopulacji;
    int ileZostaje;
    int ileMutuje;
    iFPopulacjiRozmiar mutacja;

    /**
     * Konstruntor z parametrem co ile iteracji nastąpi przerzedzenie populacji
     * @param ileIter Co ile iteracji uruchamiamy funcje wykonaj
     */
    public Kataklizm(int ileIter,int rozmiarP,int ilezostaje,int ilemutuje, iFPopulacjiRozmiar jakMutowac) {
        iloscIteracji = ileIter;
        ileJeszcze = ileIter;
        orgRozPopulacji = rozmiarP;
        ileZostaje = ilezostaje;
        ileMutuje = ilemutuje;
        mutacja = jakMutowac;
    }

    private boolean czyTeraz() {
        ileJeszcze--;
        if (ileJeszcze == 0) {
            ileJeszcze = iloscIteracji;
            return true;
        }
        return false;
    }

    public void reset() {
        ileJeszcze = iloscIteracji;
    }

    /**
     * Właściwa funkcja klasy.
     * @param p Populacja wejsciowa
     * @return Zbior unikalnych elementow wejsciowej populacji.
     */
    public populacja wykonaj(populacja p) {
        if (czyTeraz()) {
            System.out.println("# Idzie kataklizm!");
            populacja wynik = new populacja();
            Set<iOsobnik> zbior = p.osobniki().keySet();
            Iterator<iOsobnik> iter = zbior.iterator();
            int limit = ileZostaje;
            if (ileZostaje > zbior.size())
            {
                limit = zbior.size();
            }
            while (limit > 0)
            {
                wynik.dodajOsobnika(iter.next());
                limit --;
            }
            populacja mutanty = mutacja.wykonaj(wynik, ileMutuje);
            wynik.polaczPopulacje(mutanty);
            for (int i = wynik.rozmiarPopulacji(); i < orgRozPopulacji; i ++)
                wynik.dodajOsobnika(new osobnikFlowShop(wynik.rozmiarOsobnika()));
            return wynik;
        }
        return p;
    }
}
