package flowshop.Interfejsy;

import flowshop.*;

/**
 * Interface, którego implementacje realizują zastępowanie.
 * @author Łukasz Synówka
 */
public interface iZastepowanie
{
    /**
     * Algorytm z dwóch populacji tworzy jedną o rozmiarze pierwszej z zadanych populacji.
     * @param p1 Populacja wejściowa definiująca rozmiar.
     * @param p2 Populacja wejściowa dodatkowa (najczęściej osobników potomnych).
     * @return Populacja wynikowa o rozmiarze populacji p1.
     */
    populacja wykonaj(populacja p1,populacja p2);
}
