package flowshop;

import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iFunkcjaCelu;
import flowshop.Interfejsy.iOsobnik;
import java.util.Comparator;

/**
 * Klasa obliczająca wartość funkcji celu zadanej w problemie
 * @author Łukasz Synówka
 */
public class funkcjaCeluFlowShop implements iFunkcjaCelu {

    /**
     * Funkcja, która dla konkretnego osobnika i zestawu danych oblicza
     * jego funcję celu, którą implementuje klasa.
     * @param osobnik Badany element
     * @param dane Zestaw danych na postawie którego wyliczany jest wyniki
     * @return wartość liczbowa będąca wynikiem funkcji celu
     */
    public double wartoscFunkcji(iOsobnik osobnik, iDane dane) {
        int index = 0;
        double q[][] = new double[dane.iloscMaszyn()][dane.iloscZadan()];


        index = (Integer) osobnik.wartoscOsobnika(0);
        q[0][0] = dane.czasZadania(0, index);

        for (int i = 1; i < dane.iloscMaszyn(); i++) {
            q[i][0] = dane.czasZadania(i, index) + q[i - 1][0];
        }


        for (int i = 1; i < dane.iloscZadan(); i++) {

            index = (Integer) osobnik.wartoscOsobnika(i);
            q[0][i] = q[0][i - 1] + dane.czasZadania(0, index);

            for (int j = 1; j < dane.iloscMaszyn(); j++) {
                index = (Integer) osobnik.wartoscOsobnika(i);
                q[j][i] = Math.max(q[j - 1][i], q[j][i - 1]) + dane.czasZadania(j, index);
            }
        }
        return q[dane.iloscMaszyn() - 1][dane.iloscZadan() - 1];
    }

    public Comparator<iOsobnik> porownaj(final iDane dane) {
        return new Comparator<iOsobnik>() {

            public int compare(iOsobnik o1, iOsobnik o2) {
                double wyn = wartoscFunkcji(o1, dane) - wartoscFunkcji(o2, dane);
                if (wyn > 0) {
                    return 1;
                }
                if (wyn == 0)
                    return 0;
                else
                    return -1;
            }
        };
    }
}
