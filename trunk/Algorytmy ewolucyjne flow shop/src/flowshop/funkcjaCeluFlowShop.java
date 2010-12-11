/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;

import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iFunkcjaCelu;
import flowshop.Interfejsy.iOsobnik;

/**
 *
 * @author Łukasz Synówka
 */
public class funkcjaCeluFlowShop implements iFunkcjaCelu
{

    public double wartoscFunkcji(iOsobnik osobnik, iDane dane)
    {
        double calkowityCzas=0;
        double max = 0;
        int index = 0;
        double q[][] = new double[dane.iloscMaszyn()][dane.iloscZadan()];
        index = (Integer)osobnik.wartoscOsobnika(0);
        q[0][0] = dane.czasZadania(0,index);
        index = (Integer)osobnik.wartoscOsobnika(1);
        q[1][1] = dane.czasZadania(0,index);
        return 0.0;
    }


}
