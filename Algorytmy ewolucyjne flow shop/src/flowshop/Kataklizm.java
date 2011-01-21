package flowshop;

import flowshop.Interfejsy.iFunkcjaPopulacji;
import flowshop.Interfejsy.iOsobnik;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Klasa reprezentująca funcję znajdującą unikalne osobniki i zwracającą ich zbiór.
 * @author Jakub Banaszewski
 */
public class Kataklizm implements iFunkcjaPopulacji {

    int iloscIteracji;
    int ileJeszcze;
    int orgRozPopulacji;

    /**
     * Konstruntor z parametrem co ile iteracji nastąpi przerzedzenie populacji
     * @param ileIter Co ile iteracji uruchamiamy funcje wykonaj
     */
    public Kataklizm(int ileIter,int rozmiarP) {
        iloscIteracji = ileIter;
        ileJeszcze = ileIter;
        orgRozPopulacji = rozmiarP;
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
     * @return Zbior unikalnych elementow wejsciowej populacji
     * TODO? Uzupełnienie populacji świerzymy osobnikami
     */
    public populacja wykonaj(populacja p) {
        if (czyTeraz()) {
            iOsobnik o;
            HashSet<iOsobnik> zbior = new HashSet<iOsobnik>();
            while (p.rozmiarPopulacji() > 0) { //Trochę to metodą hałupniczą,ale trudno
                o = p.usunOsobnika(0);
                zbior.add(o);
                p.usunOsobnika(o);
            }
            p.dodajOsobniki(zbior);
        }
        for (int i = p.rozmiarPopulacji(); i < orgRozPopulacji; i ++)
            p.dodajOsobnika(new osobnikFlowShop(p.rozmiarOsobnika()));
        return p;
    }
}
