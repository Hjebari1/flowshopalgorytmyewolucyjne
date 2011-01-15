package flowshop;

import flowshop.Interfejsy.iFunkcjaPopulacji;
import flowshop.Interfejsy.iOsobnik;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Klasa reprezentująca funcję znajdującą unikalne osobniki i zwracającą ich zbiór.
 * @author Jakub Banaszewski
 */
public class Kataklizm implements iFunkcjaPopulacji{
    int iloscIteracji;
    int ileJeszcze;
    /**
     * Konstruntor z parametrem co ile iteracji nastąpi przerzedzenie populacji
     * @param ileIter Co ile iteracji uruchamiamy funcje wykonaj
     */
    public Kataklizm(int ileIter)
    {
        iloscIteracji = ileIter;
        ileJeszcze = ileIter;
    }
    public boolean czyTeraz()
    {
        ileJeszcze--;
        if (ileJeszcze == 0)
        {
            ileJeszcze = iloscIteracji;
            return true;
        }
        return false;
    }
    /**
     * Właściwa funkcja klasy.
     * @param p Populacja wejsciowa
     * @return Zbior unikalnych elementow wejsciowej populacji
     * TODO? Uzupełnienie populacji świerzymy osobnikami
     */
    public populacja wykonaj(populacja p) {
        iOsobnik o;
        HashSet<iOsobnik> zbior = new HashSet<iOsobnik>();
        for (Iterator<iOsobnik> i = p.popIterator();i.hasNext();) { //Trochę to metodą hałupniczą,ale trudno
            o = i.next();
            zbior.add(o);
            p.usunOsobnika(o);
        }
        p.dodajOsobniki(zbior);
        return p;
    }

}
