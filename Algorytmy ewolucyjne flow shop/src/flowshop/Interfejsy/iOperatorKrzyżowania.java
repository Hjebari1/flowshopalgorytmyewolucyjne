package flowshop.Interfejsy;

import flowshop.populacja;
/**
 * Operatory krzyżowania, które tworzą nowe osobniki na bazie 2 źródłowych.
 * @author Łukasz Synówka
 */
public abstract class iOperatorKrzyżowania
{
    protected populacja zbiorOsobnikow = null;
    /**
     * Chyba niepotrzebne ...
     * @param o
     * @throws Exception
     */
    public void dodajOsobnika(iOsobnik o) throws Exception {
        zbiorOsobnikow.dodajOsobnika(o);
    }
    /**
     * j.w...
     * @param o
     */
    public void usunOsobnika(iOsobnik o) {
        zbiorOsobnikow.usunOsobnika(o);
    }
    /**
     * Funkcja, która dla zadanej populacji generuje nowe osobniki uprzednio je parując.
     * @param p Populacja wstępna
     * @return populacja wynikowa
     */
    abstract public populacja wykonaj(populacja p);
}
