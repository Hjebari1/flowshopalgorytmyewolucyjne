/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;

import flowshop.Interfejsy.*;
import java.util.Random;

/**
 *
 * @author Łukasz Synówka
 */
public class MultiMutacjaWaga implements iFunkcjaPopulacji
{
/**
 * @author Łukasz Synówka
 */
    double wsp;
    wagiPar wg;
    iFPopulacjiRozmiar mutacja1;
    MutacjaZamianaWaga mutacja2;

    public MultiMutacjaWaga(double czestotliwosc, wagiPar wg) {
        this.wg = wg;
        wsp = czestotliwosc;
        mutacja1 = new MutacjaPrzesuniecieWaga(czestotliwosc,wg);
        mutacja2 = new MutacjaZamianaWaga(czestotliwosc,wg);
    }

    public populacja wykonaj(populacja p)
    {
        Random b = new Random();
        if (b.nextBoolean())
            return mutacja1.wykonaj(p);
        else
            return mutacja2.wykonaj(p);
    }

}

