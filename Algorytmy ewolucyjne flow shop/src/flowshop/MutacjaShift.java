/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package flowshop;

import flowshop.Interfejsy.iMutacja;
import flowshop.Interfejsy.iOsobnik;
import java.util.Random;

/**
 *
 * @author Jakub Banaszewski
 */
public class MutacjaShift implements iMutacja {
    double wspol;

    public MutacjaShift(double wsp) {
        wspol = wsp;
    }


    public void wynonaj(populacja p) {
        Random r = new Random();
        for (int iloscMutacji = (p.rozmiarPopulacji() * wspol > 0) ? r.nextInt(p.rozmiarPopulacji() / 4) : 1 ; iloscMutacji>0; iloscMutacji--) {
            int a = r.nextInt(p.rozmiarPopulacji());
            iOsobnik o = p.usunOsobnika(a);
            Integer start = r.nextInt(o.dlugoscGenomu());
            Integer meta = r.nextInt(o.dlugoscGenomu());
            Object wart = o.wartoscOsobnika(start);
            for (int i = start; i < meta; i++)
                o.modyfikujGen(i, o.wartoscOsobnika(i+1));
            o.modyfikujGen(meta,wart);
            p.dodajOsobnika(o);
        }
    }
}
