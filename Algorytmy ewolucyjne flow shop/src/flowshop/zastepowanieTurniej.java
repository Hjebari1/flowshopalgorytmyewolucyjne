package flowshop;

import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iFunkcjaCelu;
import flowshop.Interfejsy.iOsobnik;
import flowshop.Interfejsy.iZastepowanie;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Zastępowanie truniejowe służy do scalenia populacji bierzącej z populacją
 * wygenerowaną przez krzyżowanie.
 * @author Łukasz Synówka
 */
public class zastepowanieTurniej implements iZastepowanie
{

    iDane dane;
    iFunkcjaCelu funkcja;
    // ilosc końcowa określa ile osobników ma pozostac
    int iloscKoncowa;

    public zastepowanieTurniej(iDane dane, iFunkcjaCelu funkcja, int iloscKoncowa)
    {
        this.dane = dane;
        this.funkcja = funkcja;
        this.iloscKoncowa = iloscKoncowa;
    }

    // główna metoda klasy parametrami są odpowiednio
    // populacja populacja obecna i populacja po krzyżowaniu
    public populacja wykonaj(populacja p1, populacja p2)
    {
        iOsobnik o1;
        iOsobnik o2;

        p1.polaczPopulacje(p2);
        Random r = new Random();
        populacja wynik = new populacja();
        Iterator<iOsobnik> li = p1.popIterator();
        int lm = Math.round((float) Math.log(iloscKoncowa));
        iOsobnik[] max = new osobnikFlowShop[Math.round((float) Math.log(iloscKoncowa))];
        for (int i=0;i<lm;i++) max[i]=li.next();
        for (int i=0;i<lm;i++)
            for (int j=i;j<lm;j++)
            {
                if (funkcja.wartoscFunkcji(max[i], dane)<funkcja.wartoscFunkcji(max[j], dane))
                {
                    o1=max[i];
                    max[i]=max[j];
                    max[j]=o1;
                }
            }
        while(li.hasNext())
        {
            o2=li.next();
            if (funkcja.wartoscFunkcji(max[0], dane)>funkcja.wartoscFunkcji(o2, dane))
            {
                max[0]=o2;
                for (int j=0;j<lm;j++)
                {
                    if (funkcja.wartoscFunkcji(max[0], dane)<funkcja.wartoscFunkcji(max[j], dane))
                    {
                        o1=max[0];
                        max[0]=max[j];
                        max[j]=o1;
                    }
                }
            }

        }
        ArrayList<iOsobnik> osobniki = new ArrayList(p1.osobnikiPop());
        for (int i=0;i<lm;i++) wynik.dodajOsobnika(max[i]);
        while (wynik.rozmiarPopulacji()<iloscKoncowa)
        {
            o1 = osobniki.get(r.nextInt(osobniki.size())); //FE!
            o2 = osobniki.get(r.nextInt(osobniki.size()));
            if (funkcja.wartoscFunkcji(o1,dane)<funkcja.wartoscFunkcji(o2,dane))
            {
                wynik.dodajOsobnika(o1);
                p1.dodajOsobnika(o2);
            }
            else
            {
                wynik.dodajOsobnika(o2);
                p1.dodajOsobnika(o1);
            }
        }
        return wynik;
    }


}
