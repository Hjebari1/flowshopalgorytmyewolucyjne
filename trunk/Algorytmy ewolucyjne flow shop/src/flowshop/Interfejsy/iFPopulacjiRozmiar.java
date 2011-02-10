package flowshop.Interfejsy;

import flowshop.populacja;

/**
 * Iterface określający funcje działające na populacji i zwracające nową populacje,
 * która jest określonego rozmiaru (możliwe, że innego od populacji zadanej na wejściu)
 * @author Jakub Banaszewski
 */
public interface iFPopulacjiRozmiar extends iFunkcjaPopulacji{
    /**
     * Funkcja realizująca funkjonalność klasy
     * @param p Populacja wejściowa
     * @param rozmiar Rozmiar populacji wyjściowej
     * @return Populacja wyjściowa
     */
    public populacja wykonaj(populacja p, int rozmiar);
}
