package flowshop.Interfejsy;

import flowshop.populacja;

/**
 *
 * @author Jakub Banaszewski
 */
public interface iFPopulacjiRozmiar extends iFunkcjaPopulacji{
    public populacja wykonaj(populacja p, int rozmiar);
}
