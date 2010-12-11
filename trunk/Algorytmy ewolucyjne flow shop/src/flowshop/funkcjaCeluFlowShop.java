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
        int index = 0;
        double q[][] = new double[dane.iloscMaszyn()][dane.iloscZadan()];


        index = (Integer)osobnik.wartoscOsobnika(0);
        q[0][0] = dane.czasZadania(0,index);

        for (int i=1;i<dane.iloscZadan();i++)
        {

            index = (Integer)osobnik.wartoscOsobnika(i);
            q[0][i] = q[0][i-1] + dane.czasZadania(0,index);

            for (int j=1;j<dane.iloscMaszyn();j++)
            {
                index = (Integer)osobnik.wartoscOsobnika(i);
                q[j][i] = Math.max(q[j-1][i],q[j][i-1]) + dane.czasZadania(j,index);
            }
        }
        return q[dane.iloscMaszyn()-1][dane.iloscZadan()-1];
    }


}
