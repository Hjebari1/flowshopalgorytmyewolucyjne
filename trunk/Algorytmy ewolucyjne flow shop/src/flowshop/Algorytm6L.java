/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;

import flowshop.Interfejsy.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ListIterator;
import java.util.Random;

/**
 *
 * @author Łukasz Synówka
 */
public class Algorytm6L implements iAlgorytm
{

    populacja p;
    populacja ps;
    populacja pc;
    iDane dane;
    selekcjaRuletka sr;
    iOperatorKrzyżowania oper;
    zastepowanieTurniej zt;
    int iloscOsobnikow;
    funkcjaCeluFlowShop f;
    iMutacja m;

    public Algorytm6L(int iloscOsobnikow,iDane d) throws FileNotFoundException
    {
        dane = d;
        f = new funkcjaCeluFlowShop();
        this.iloscOsobnikow=iloscOsobnikow;
        p = new populacja();
        zt = new zastepowanieTurniej(dane,f,iloscOsobnikow);
        sr = new selekcjaRuletka(dane,f);
        for(int i=0;i<iloscOsobnikow;i++)
        {
            p.dodajOsobnika(new osobnikFlowShop(d.iloscZadan()));
        }
        m= new Mutacja1();
    }


    public void wybor()
    {
        ps = sr.wybranaPopulacja(p, iloscOsobnikow/2);
        ps.dodajOsobnika(ps.min(f, dane));
    }

    public void krzyzowanie()
    {
        Random r = new Random();
        oper = new multiOperator();
        pc = oper.wykonaj(ps);
        for (int i=0;i<iloscOsobnikow/5;i++)
        {
            m.wynonaj(pc);
            m.wynonaj(p);
        }

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

        double min=Double.MAX_VALUE;

        osobnikFlowShop o;
        while(iter.hasNext())
        {
            o=(osobnikFlowShop) iter.next();
            min=Math.min(f.wartoscFunkcji(o, dane),min);
            //wynik=wynik.concat(f.wartoscFunkcji(o, dane)+" ");
        }
        wynik=wynik.concat(min+"\n");

        return wynik;

    }

    public iAlgorytm createAlg(int iloscOsobnikow, iDane d) throws FileNotFoundException, IOException {
        return new Algorytm2(iloscOsobnikow, d);
    }

    public String nazwaAlg() {
        return "Algorytm2 - multiOperator, zastępowanieTurniejowe";
    }

    public double getMin() {
        throw new UnsupportedOperationException("Not supported yet.");
    }


}
