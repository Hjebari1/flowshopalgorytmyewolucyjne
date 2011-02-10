/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;

import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iFPopulacjiRozmiar;
import flowshop.Interfejsy.iFunkcjaCelu;
import java.util.Random;

/**
 *
 * @author Łukasz Synówka
 */
public class multiSelekcja implements iFPopulacjiRozmiar
{
    iDane dane;
    iFunkcjaCelu fCel;
    iFPopulacjiRozmiar selekcjaRand;
    iFPopulacjiRozmiar selekcjaSort;
    double wsp;

    public multiSelekcja(iDane dane, iFunkcjaCelu fCel, double wsp) {
        this.dane = dane;
        this.fCel = fCel;
        this.wsp=wsp;
        selekcjaRand = new SelekcjaRand();
        selekcjaSort = new SelekcjaSort(dane,fCel);
    }

    public multiSelekcja(iDane dane, double wsp) {
        this.dane = dane;
        this.wsp=wsp;
        selekcjaRand = new SelekcjaRand();
        selekcjaSort = new SelekcjaSort(dane,fCel);
    }

    public populacja wykonaj(populacja p, int rozmiar)
    {
        Random d = new Random();
        if (d.nextDouble()%1<wsp)
        {
            return selekcjaRand.wykonaj(p, rozmiar);
        }
        else
        {
            return selekcjaSort.wykonaj(p, rozmiar);
        }
    }

    public populacja wykonaj(populacja p)
    {
        Random d = new Random();
        if (d.nextDouble()%2<wsp)
        {
            return selekcjaRand.wykonaj(p);
        }
        else
        {
            return selekcjaSort.wykonaj(p);
        }
    }

}
