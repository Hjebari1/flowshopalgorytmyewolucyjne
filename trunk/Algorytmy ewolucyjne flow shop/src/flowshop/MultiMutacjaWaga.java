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
public class MultiMutacjaWaga implements iFPopulacjiRozmiar
{
/**
 * @author Łukasz Synówka
 */
    double wsp;
    wagiPar wg;
    iFPopulacjiRozmiar mutacja1;
    iFPopulacjiRozmiar mutacja2;

    public MultiMutacjaWaga(double czestotliwosc, wagiPar wg) {
        this.wg = wg;
        wsp = czestotliwosc;
        mutacja1 = new MutacjaPrzesuniecieWaga(czestotliwosc,wg);
        mutacja2 = (iFPopulacjiRozmiar) new MutacjaZamianaWaga(czestotliwosc,wg);
    }

    public populacja wykonaj(populacja p) {
        return wykonaj(p, p.rozmiarPopulacji());
    }

    public populacja wykonaj(populacja p, int rozmiar)
    {
        Random b = new Random();
        if (b.nextBoolean())
            return mutacja1.wykonaj(p, rozmiar);
        else
            return mutacja2.wykonaj(p, rozmiar);
    }
}

