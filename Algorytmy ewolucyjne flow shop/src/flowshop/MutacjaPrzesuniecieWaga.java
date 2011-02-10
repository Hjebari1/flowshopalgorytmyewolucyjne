package flowshop;

import flowshop.Interfejsy.iFPopulacjiRozmiar;
import flowshop.Interfejsy.iOsobnik;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Łukasz Synówka
 */
public class MutacjaPrzesuniecieWaga implements iFPopulacjiRozmiar {

    double wsp;
    wagiPar wg;

    /**
     * Konstruktor domyślny.
     * @param wsp Prawdopodobieństwo wystąpienia mutacji
     * @param wg obiekt zawierający wagi par
     */
    public MutacjaPrzesuniecieWaga(double czestotliwosc, wagiPar wg) {
        this.wg = wg;
        wsp = czestotliwosc;
    }

    public populacja wykonaj(populacja p) {
        return wykonaj(p, p.rozmiarPopulacji());
    }

    public populacja wykonaj(populacja p, int rozmiar) {
        populacja wynik = new populacja();
        Random r = new Random();
        iOsobnik oc = null;
        Double d;
        double suma;
        int mult;
        while (wynik.rozmiarPopulacji() < rozmiar) {
            for (Iterator<iOsobnik> j = p.popIterator(); j.hasNext() && wynik.rozmiarPopulacji() < rozmiar;) {
                try {
                    iOsobnik o = j.next().makeCopy();
                    oc = o.makeCopy();
                    Integer start;
                    Integer meta;
                    int tmp;
                    if (r.nextDouble() > wsp) {
                        do {
                            start = r.nextInt(o.dlugoscGenomu());
                            meta = r.nextInt(o.dlugoscGenomu());
                            if (start > meta) {
                                tmp = start;
                                start = meta;
                                meta = tmp;
                            }
                            d = r.nextDouble();
                            d = d - d.intValue();
                            suma = 0;
                            mult = 0;
                            if (meta > 0) {
                                suma = wg.wartosc((Integer) o.wartoscOsobnika(meta-1), (Integer) o.wartoscOsobnika(meta));
                                mult++;
                            }
                            if (meta < o.dlugoscGenomu() - 1) {
                                suma = wg.wartosc((Integer) o.wartoscOsobnika(meta), (Integer) o.wartoscOsobnika(meta+1));
                                mult++;
                            }
                            if (start > 0) {
                                suma = wg.wartosc((Integer) o.wartoscOsobnika(start-1), (Integer) o.wartoscOsobnika(start));
                                mult++;
                            }
                            if (start < o.dlugoscGenomu() - 1) {
                                suma = wg.wartosc((Integer) o.wartoscOsobnika(start), (Integer) o.wartoscOsobnika(start+1));
                                mult++;
                            }
                        } while (suma > (d * mult));
                        Object wart = o.wartoscOsobnika(start);
                        for (int i = start; i < meta; i++) {
                            o.modyfikujGen(i, o.wartoscOsobnika(i + 1));
                        }
                        o.modyfikujGen(meta, wart);
                    }
                    wg.wagaMutacjaZamiana((osobnikFlowShop) oc, (osobnikFlowShop) o);
                    wynik.dodajOsobnika(o);
                } catch (Exception ex) {
                    Logger.getLogger(MutacjaPrzesuniecieWaga.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return wynik;
    }
}