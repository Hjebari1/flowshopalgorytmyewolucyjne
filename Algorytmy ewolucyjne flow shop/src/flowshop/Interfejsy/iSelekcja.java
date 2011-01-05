package flowshop.Interfejsy;

import flowshop.*;

/**
 * Interface opisujący selekcję na populacji.
 * @author Łukasz Synówka
 */
public interface iSelekcja 
{
    /**
     * Dla zadanej populacji metoda zwraca wybraną jej część.
     * @param p Populacja, na której przeprowadza się selekcję
     * @return Populacja, która pozytywnie przeszła selekcję
     */
    public populacja wybranaPopulacja(populacja p);
}
