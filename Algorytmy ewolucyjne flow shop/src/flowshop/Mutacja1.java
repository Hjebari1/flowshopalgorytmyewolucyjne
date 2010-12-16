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
 * @author Łukasz Synówka
 */
public class Mutacja1 implements iMutacja
{

    public void wynonaj(populacja p)
    {
        Random r = new Random();
        iOsobnik o = p.usunOsobnika(r.nextInt(p.rozmiarPopulacji()));
        Integer a1 = r.nextInt(o.dlugoscGenomu());
        Integer a2 = r.nextInt(o.dlugoscGenomu());
        Integer b1 = (Integer)o.wartoscOsobnika(a1);
        Integer b2 = (Integer)o.wartoscOsobnika(a2);
        o.modyfikujGen(a1, b2);
        o.modyfikujGen(a2, b1);
        p.dodajOsobnika(o);
    }

}
