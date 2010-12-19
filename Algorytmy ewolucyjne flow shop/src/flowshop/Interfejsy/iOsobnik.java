package flowshop.Interfejsy;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Łukasz Synówka
 */
public abstract class iOsobnik implements Cloneable {
    public static Object pusto=null;

    /**
     * Modyfikuje wartość genu na zadanej pozycji
     * @param pozycja Pozycja w genomie
     * @param wartosc Nowa wartość genu
     */
    abstract public void modyfikujGen(int pozycja, Object wartosc);

    /**
     * Zwraca gen osobnika na wskazanej pozycji
     * @param pozycja Pozycja w genomie
     * @return wartość genu
     */
    abstract public Object wartoscOsobnika(int pozycja);

    /**
     * Funkcja zwraca długość genomu
     * @return Długość genumu
     */
    abstract public int dlugoscGenomu();

    /**
     * Funkcja wyszukująca genu, którego wartość odpowiada parametrowi w wyznaczonym
     * przedziale. Gdy nie znajdzie genu zwraca wartość końca zadanego przedziału.
     * @param zakresOd Od tego pola włącznie następuje wyszukiwanie
     * @param zakresDo Do tego pola z jego wyłączeniem następuje wyszukiwanie
     * @param wartosc Poszukiwany gen
     * @return Pozycja wyszukanego genu lub wartość końca przedziału
     */
    abstract public int znajdzPozGenu(int zakresOd,int zakresDo,Object wartosc);
    /**
    * Funkcja wyszukująca genu, którego wartość odpowiada parametrowi  poza wyznaczonym
     * przedziale. Gdy nie znajdzie genu zwraca wartość długości genomu.
     * @param zakresOd Od tego pola jest przerwa w wyszukaniu.
     * @param zakresDo Do tego pola z jego wyłączeniem jest przerwa w wyszukiwaniu.
     * @param wartosc Poszukiwany gen
     * @return Pozycja wyszukanego genu lub wartość końca przedziału
     */
    abstract public int znajdzPozGenuPoza(int zakresOd,int zakresDo,Object wartosc);

    @Override
    abstract public boolean equals(Object o);
    @Override
    abstract public int hashCode();
    /**
     * Klonuje osobnika i tworzy jego identyczną kopię
     * @return Kopia osobnika
     */
    public iOsobnik makeCopy() {
        try {
            return (iOsobnik) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(iOsobnik.class.getName()).log(Level.SEVERE, null, ex); //??TODO:coś sensownego
        }
        return null;
    }
}
