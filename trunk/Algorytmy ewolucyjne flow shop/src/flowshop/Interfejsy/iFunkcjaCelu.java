package flowshop.Interfejsy;

import java.util.Comparator;

/**
 * Interface opisujący funcję celu.
 * @author Łukasz Synówka
 */
public interface iFunkcjaCelu
{
    /**
     * Metoda, która oblicza wartość funcji celu.
     * @param osobnik Osobnik, dla którego dokonuje się obliczeń
     * @param dane Zestaw danych, z którego ma korzystać funcja
     * @return Wartość funkcji dla zadanych parametrów
     */
    double wartoscFunkcji(iOsobnik osobnik,iDane dane);
    Comparator<iOsobnik> porownaj(iDane dane);
}
