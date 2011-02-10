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
public class multiZastepowanie implements iZastepowanie
{
    iDane dane;
    iFunkcjaCelu funkcja;
    int iloscKoncowa;
    double ilePozostawic;
    double wsp;
    iZastepowanie zm;
    iZastepowanie zt;

    public multiZastepowanie(iDane dane, iFunkcjaCelu funkcja, int iloscKoncowa,double wsp)
    {
        this.dane = dane;
        this.funkcja = funkcja;
        this.wsp = wsp;
        this.iloscKoncowa = iloscKoncowa;
        ilePozostawic = 0.0;
        zm = new zastepowanieMinimalne(dane,funkcja,iloscKoncowa);
        zt = new zastepowanieTurniej(dane,funkcja,iloscKoncowa);
    }
    public multiZastepowanie(iDane dane, iFunkcjaCelu funkcja, int iloscKoncowa, double ileZostanie, double wsp)
    {
        this.dane = dane;
        this.wsp = wsp;
        this.funkcja = funkcja;
        this.iloscKoncowa = iloscKoncowa;
        ilePozostawic = ileZostanie;
        zm = new zastepowanieMinimalne(dane,funkcja,iloscKoncowa,ileZostanie);
        zt = new zastepowanieTurniej(dane,funkcja,iloscKoncowa);
    }
    public populacja wykonaj(populacja p1, populacja p2)
    {
        Random d = new Random();
        if (d.nextDouble()%2<wsp)
        {
            return zt.wykonaj(p1, p2);
        }
        else
        {
            return zm.wykonaj(p1, p2);
        }
    }

}
