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
 *
 * Pozwala wykożystywać dwie metody zastępowania w jednym algorytmie
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
    /**
     *
     * @param dane dane z zadania
     * @param funkcja funkcja celu
     * @param iloscKoncowa ilość końcow jaka ma zostać po zastępowaniu
     * @param wsp współcznynnik określający z jaką częśtotliwością wykonuje się określone Zastępowanie
     */
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
