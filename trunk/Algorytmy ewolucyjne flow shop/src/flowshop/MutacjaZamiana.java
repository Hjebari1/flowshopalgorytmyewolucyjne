package flowshop;

import flowshop.Interfejsy.iFunkcjaPopulacji;
import flowshop.Interfejsy.iOsobnik;
import java.util.Iterator;
import java.util.Random;

/**
 * @author Jakub Banaszewski
 */
public class MutacjaZamiana implements iFunkcjaPopulacji {

    private double wsp = 0;

    public MutacjaZamiana(double czestotliwosc) {
        wsp = czestotliwosc;
    }

    public populacja wykonaj(populacja p) {
        Random r = new Random();
        for (Iterator i = p.popIterator(); i.hasNext();) {
            iOsobnik o = (iOsobnik) i.next();
            if (r.nextDouble() > wsp) {
                Integer a1 = r.nextInt(o.dlugoscGenomu());
                Integer a2 = r.nextInt(o.dlugoscGenomu());
                Integer b1 = (Integer) o.wartoscOsobnika(a1);
                Integer b2 = (Integer) o.wartoscOsobnika(a2);
                o.modyfikujGen(a1, b2);
                o.modyfikujGen(a2, b1);
            }
        }
        return p;
    }

    public populacja wykonaj(populacja p, int rozmiar) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
