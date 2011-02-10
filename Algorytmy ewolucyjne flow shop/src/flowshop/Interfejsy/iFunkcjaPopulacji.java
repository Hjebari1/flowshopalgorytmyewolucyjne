package flowshop.Interfejsy;

import flowshop.populacja;

/**
 * Interface określający rodzinę funkcji działających na populacji takich
 * jak mutacja, selekcja, czy operatory krzyżowania.
 * @author Jakub Banaszewski
 */
public interface iFunkcjaPopulacji {
        public populacja wykonaj(populacja p);
}
