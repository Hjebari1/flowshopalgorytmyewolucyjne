package flowshop;

import java.util.logging.Level;
import java.util.logging.Logger;
import operatory.multiOperator;
import flowshop.Interfejsy.iAlgorytm;
import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iFunkcjaPopulacji;
import flowshop.Interfejsy.iOsobnik;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Random;

/**
 * Klasa zostawiona jako przykład i pomost między starym a nowym porządkiem
 * programu.
 * kolejne algorytmy z literą L na końcu różnią się między sobą 
 * parametrami takimi jak ilość mutacji czy ilość osobników 
 * do krzyżowania
 *
 * @author Łukasz Synówka
 */
public class Algorytm4L implements iAlgorytm
{

    populacja p;
    populacja ps;
    populacja pc;
    iDane dane;
    selekcjaRuletka sr;
    iFunkcjaPopulacji oper;
    zastepowanieTurniej zt;
    int iloscOsobnikow;
    funkcjaCeluFlowShop f;
    iFunkcjaPopulacji m;

    public Algorytm4L(int iloscOsobnikow,iDane d) throws FileNotFoundException, Exception
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
        m= new MutacjaZamiana(0.2);
    }


    public void wybor()
    {
        try {
            ps = sr.wykonaj(p, iloscOsobnikow / 2);
            ps.dodajOsobnika(ps.min(f, dane));
        } catch (Exception ex) {
            Logger.getLogger(Algorytm4L.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void krzyzowanie()
    {
        Random r = new Random();
        oper = new multiOperator();
        pc = oper.wykonaj(p);
        for(int i=0;i<iloscOsobnikow/100;i++)
        {
            m.wykonaj(pc);
            m.wykonaj(p);
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
        Iterator<iOsobnik> iter = p.popIterator();

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
        try {
            return new Algorytm4L(iloscOsobnikow, d);
        } catch (Exception ex) {
            Logger.getLogger(Algorytm4L.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String nazwaAlg() {
        return "Algorytm4L";
    }

    public double getMin()
    {
        Iterator<iOsobnik> iter = p.popIterator();
        osobnikFlowShop o;
        double min = Double.MAX_VALUE;
        while (iter.hasNext()) {
            o = (osobnikFlowShop) iter.next();
            min = Math.min(f.wartoscFunkcji(o, dane), min);
        }
        return min;
    }

}

