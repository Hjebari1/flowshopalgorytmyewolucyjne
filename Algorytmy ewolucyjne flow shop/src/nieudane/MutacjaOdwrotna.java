package nieudane;

import flowshop.Interfejsy.iFPopulacjiRozmiar;
import flowshop.Interfejsy.iOsobnik;
import flowshop.MutacjaPrzesuniecie;
import flowshop.populacja;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Tworzy permutację odwrotną do tej jaka jest w osobniku
 *  względem permutacji identycznościowej.
 * @author Jakub Banaszewski
 */
public class MutacjaOdwrotna implements iFPopulacjiRozmiar {
    double wsp;
    public MutacjaOdwrotna(double wspC) {
        wsp = wspC;
    }


    public populacja wykonaj(populacja p, int rozmiar) {
        populacja wynik = new populacja();
        Random r = new Random();
        while (wynik.rozmiarPopulacji() < rozmiar) {
            for (Iterator<iOsobnik> j = p.popIterator(); j.hasNext() && wynik.rozmiarPopulacji() < rozmiar;) {
                try {
                    iOsobnik zrodlo = j.next();
                    iOsobnik o = zrodlo.makeCopy();
                    if (r.nextDouble() < wsp) {
                        for (int i = 0; i < o.dlugoscGenomu() ; i++)
                        {
                            Integer gen = (Integer) zrodlo.wartoscOsobnika(i);
                            o.modyfikujGen(gen, i);
                        }
                        wynik.dodajOsobnika(o);
                    }                    
                } catch (Exception ex) {
                    Logger.getLogger(MutacjaPrzesuniecie.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return wynik;
    }
    
    public populacja wykonaj(populacja p) {
        return wykonaj(p,p.rozmiarPopulacji());
    }

}
