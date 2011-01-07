package flowshop;
;
import flowshop.Interfejsy.iFPopulacjiRozmiar;

/**
 *
 * @author Jakub Banaszewski
 */
public class identycznosc implements iFPopulacjiRozmiar{
    static identycznosc instance;
    static { instance = new identycznosc();}
    private identycznosc() {};
    public static identycznosc getInstance()
    {
        return instance;
    }
    public populacja wykonaj(populacja p) {
        return p;
    }

    public populacja wykonaj(populacja p, int rozmiar) {
        return p;
    }

}
