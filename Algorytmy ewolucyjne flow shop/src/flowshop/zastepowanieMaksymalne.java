/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;

import flowshop.Interfejsy.*;
import flowshop.Interfejsy.iZastepowanie;
import java.util.Random;

/**
 *Funkcja się nie sprawdziła może będzie rozwijana później
 * @author Łukasz Synówka
 */
public class zastepowanieMaksymalne implements iZastepowanie
{
    iDane dane;
    iFunkcjaCelu funkcja;
    int iloscKoncowa;

    public zastepowanieMaksymalne(iDane dane, iFunkcjaCelu funkcja, int iloscKoncowa)
    {
        this.dane = dane;
        this.funkcja = funkcja;
        this.iloscKoncowa = iloscKoncowa;
    }
    public populacja wykonaj(populacja p1, populacja p2) 
    {
        p1.polaczPopulacje(p2);
        p2=new populacja();
        populacja p = new populacja();
        iOsobnik o;
        Random r = new Random();
        iOsobnik mediana = p1.usunOsobnika(r.nextInt(p1.rozmiarPopulacji()));
        while((p1.rozmiarPopulacji()>0)&&(p.rozmiarPopulacji()<iloscKoncowa))
        {
            o=p1.usunOsobnika(p1.rozmiarPopulacji()-1);
            if(funkcja.wartoscFunkcji(mediana, dane)>=funkcja.wartoscFunkcji(o, dane))
                p.dodajOsobnika(o);
            else
                p2.dodajOsobnika(o);
        }
        while(p.rozmiarPopulacji()<iloscKoncowa)
        {
            p.dodajOsobnika(p2.usunOsobnika(p2.rozmiarPopulacji()-1));
        }
        return p;
    }

}
