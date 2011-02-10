package nieudane;

import flowshop.Interfejsy.VAlgorytm;
import flowshop.Interfejsy.iFPopulacjiRozmiar;
import flowshop.Interfejsy.iFunkcjaPopulacji;
import flowshop.Interfejsy.iOsobnik;
import flowshop.Interfejsy.iZastepowanie;
import flowshop.osobnikFlowShop;
import flowshop.populacja;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa reprezentująca funcję znajdującą unikalne osobniki i zwracającą ich zbiór.
 * @author Jakub Banaszewski
 */
public class Rewolucja implements iFunkcjaPopulacji {

    int iloscIteracji;
    int ileJeszcze;
    int jakCzesto;
    int orgRozPopulacji;
    int ileZostaje;
    int ileMutuje;
    iFPopulacjiRozmiar mutacja;
    VAlgorytm jakaRewolucja;
    iZastepowanie polaczenie;

    /**
     * Konstruntor z parametrem co ile iteracji nastąpi przerzedzenie populacji
     * @param ileIter Co ile iteracji uruchamiamy funcje wykonaj
     */
    public Rewolucja(int ileIter, int coile, int rozmiarP, int ilezostaje, int ilemutuje,
            iFPopulacjiRozmiar jakmutowac, VAlgorytm warunki, iZastepowanie jakLaczyc) {
        iloscIteracji = ileIter;
        ileJeszcze = coile;
        jakCzesto = coile;
        orgRozPopulacji = rozmiarP;
        ileZostaje = ilezostaje;
        ileMutuje = ilemutuje;
        mutacja = jakmutowac;
        jakaRewolucja = warunki;
        polaczenie = jakLaczyc;
    }

    /**
     * Właściwa funkcja klasy.
     * @param p Populacja wejsciowa
     * @return Zbior unikalnych elementow wejsciowej populacji.
     */
    public populacja wykonaj(populacja p) {
        if (ileJeszcze == 0) {
            try {
                ileJeszcze = jakCzesto;
                System.out.println("# Idzie rewolucja!");
                populacja wynik = new populacja();
                Set<iOsobnik> zbior = p.osobniki().keySet();
                Iterator<iOsobnik> iter = zbior.iterator();
                int limit = ileZostaje;
                if (ileZostaje > zbior.size()) {
                    limit = zbior.size();
                }
                while (limit > 0) {
                    wynik.dodajOsobnika(iter.next());
                    limit--;
                }
                populacja mutanty = mutacja.wykonaj(wynik, ileMutuje);
                wynik.polaczPopulacje(mutanty);
                populacja rewolucjonisci = new populacja();
                rewolucjonisci.dodajOsobniki(wynik);
                for (int i = wynik.rozmiarPopulacji(); i < orgRozPopulacji; i++) {
                    wynik.dodajOsobnika(new osobnikFlowShop(wynik.rozmiarOsobnika()));
                }
                rewolucjonisci = mutacja.wykonaj(rewolucjonisci,orgRozPopulacji);
                jakaRewolucja.ustawPopulacje(rewolucjonisci);
                jakaRewolucja.wykonajIteracje(iloscIteracji);
                polaczenie.wykonaj(rewolucjonisci, wynik);
                return wynik;
            } catch (Exception ex) {
                Logger.getLogger(Rewolucja.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ileJeszcze--;
            return p;
        }
        return p;
    }
}
