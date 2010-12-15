/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;

import flowshop.Interfejsy.iAlgorytm;
import flowshop.Interfejsy.iOperatorKrzyżowania;
import flowshop.Interfejsy.iOsobnik;
import java.util.ListIterator;
import java.util.Random;

/**
 *
 * @author Łukasz Synówka
 */
public class Algorytm1 implements iAlgorytm
{

    populacja p;
    populacja ps;
    populacja pc;
    Dane1 dane;
    selekcjaRuletka sr;
    iOperatorKrzyżowania oper;
    zastepowanieTurniej zt;
    int iloscOsobnikow;
    funkcjaCeluFlowShop f;

    public Algorytm1(int iloscOsobnikow,int dlugoscGenu)
    {
        f = new funkcjaCeluFlowShop();
        this.iloscOsobnikow=iloscOsobnikow;
        p = new populacja();
        dane = new Dane1();
        zt = new zastepowanieTurniej(dane,f,iloscOsobnikow);
        for(int i=0;i<iloscOsobnikow;i++)
        {
            p.dodajOsobnika(new osobnikFlowShop(dlugoscGenu));
        }
    }


    public void wybor()
    {
        sr = new selekcjaRuletka(p,dane);
    }

    public void krzyzowanie()
    {
        Random r = new Random();
        if (r.nextBoolean())
            oper = new operatorOX(p);
        else
            oper = new operatorCX(p);
        pc = oper.wykonaj();
        
    }

    public void zastepowanie()
    {
        p=zt.wykonaj(p, pc);
    }

    @Override
    public String toString()
    {
        String wynik = "";
        ListIterator<iOsobnik> iter = p.popIterator();

        osobnikFlowShop o;
        while(iter.hasNext())
        {
            o=(osobnikFlowShop) iter.next();
            wynik=wynik.concat(f.wartoscFunkcji(o, dane)+" ");
        }
        wynik=wynik.concat("\n");

        return wynik;

    }


}
