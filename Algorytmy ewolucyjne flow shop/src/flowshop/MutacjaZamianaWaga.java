/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;

import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iFunkcjaPopulacji;
import flowshop.Interfejsy.iOsobnik;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Łukasz Synówka
 */
public class MutacjaZamianaWaga implements iFunkcjaPopulacji
{
    private double wsp = 0;
    private wagiPar wg ;

    public MutacjaZamianaWaga(double czestotliwosc, wagiPar wg) 
    {
        this.wg = wg;
        wsp = czestotliwosc;
    }

    public populacja wykonaj(populacja p) {
        Random r = new Random();
        osobnikFlowShop oc;
        Double d;
        Integer a1;
        Integer a2;
        Integer b1;
        Integer b2;
        double suma;
        int mult;
        for (Iterator i = p.popIterator(); i.hasNext();) {
            iOsobnik o = (iOsobnik) i.next();
            oc = (osobnikFlowShop) o.makeCopy();
            if (r.nextDouble() > wsp) {
                do 
                {
                    a1 = r.nextInt(o.dlugoscGenomu());
                    a2 = r.nextInt(o.dlugoscGenomu());
                    b1 = (Integer) o.wartoscOsobnika(a1);
                    b2 = (Integer) o.wartoscOsobnika(a2);
                    d = r.nextDouble();
                    d = d - d.intValue();
                    suma=0;
                    mult=0;
                    if ( a2 > 0 )
                    {
                        suma = wg.wartosc(a2-1,a2);
                        mult++;
                    }
                    if (a2 < o.dlugoscGenomu()-1)
                    {
                        suma = wg.wartosc(a2,a2+1);
                        mult++;
                    }
                    if ( a1 > 0 )
                    {
                        suma = wg.wartosc(a1-1,a1);
                        mult++;
                    }
                    if (a1 < o.dlugoscGenomu()-1)
                    {
                        suma = wg.wartosc(a1,a1+1);
                        mult++;
                    }
                } while (suma>(d*mult));
                o.modyfikujGen(a1, b2);
                o.modyfikujGen(a2, b1);
            }
            wg.wagaMutacjaZamiana(oc, (osobnikFlowShop) o);
        }
        return p;
    }
}
