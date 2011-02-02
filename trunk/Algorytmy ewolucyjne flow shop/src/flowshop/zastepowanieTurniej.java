package flowshop;

import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iFunkcjaCelu;
import flowshop.Interfejsy.iOsobnik;
import flowshop.Interfejsy.iZastepowanie;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

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

    /**
     * Główna metoda klasy. Parametrami są odpowiednio
     * populacja obecna i populacja po krzyżowaniu
     * */
    public populacja wykonaj(populacja p1, populacja p2)
    {
        try {
            iOsobnik o1;
            iOsobnik o2;
            p1.polaczPopulacje(p2);
            Random r = new Random();
            populacja wynik = new populacja();
            Iterator<iOsobnik> li = p1.osobnikiPop().iterator();
            int lm = Math.round((float) Math.log(iloscKoncowa));
            iOsobnik[] min = new osobnikFlowShop[Math.round((float) Math.log(iloscKoncowa))];
            for (int i = 0; i < lm; i++) {
                min[i] = li.next();
            }
            for (int i = 0; i < lm; i++) {
                for (int j = i; j < lm; j++) {
                    if (funkcja.wartoscFunkcji(min[i], dane) < funkcja.wartoscFunkcji(min[j], dane)) {
                        o1 = min[i];
                        min[i] = min[j];
                        min[j] = o1;
                    }
                }
            }
            while (li.hasNext()) {
                o2 = li.next();
                if (funkcja.wartoscFunkcji(min[0], dane) > funkcja.wartoscFunkcji(o2, dane)) {
                    min[0] = o2;
                    for (int j = 0; j < lm; j++) {
                        if (funkcja.wartoscFunkcji(min[0], dane) < funkcja.wartoscFunkcji(min[j], dane)) {
                            o1 = min[0];
                            min[0] = min[j];
                            min[j] = o1;
                        }
                    }
                }
            }
            ArrayList<iOsobnik> osobniki = new ArrayList(p1.osobnikiPop());
            for (int i = 0; i < lm; i++) {
                wynik.dodajOsobnika(min[i]);
            }
            while (wynik.rozmiarPopulacji() < iloscKoncowa) {
                o1 = osobniki.get(r.nextInt(osobniki.size())); //FE!
                o2 = osobniki.get(r.nextInt(osobniki.size()));
                if (funkcja.wartoscFunkcji(o1, dane) < funkcja.wartoscFunkcji(o2, dane)) {
                    wynik.dodajOsobnika(o1);
                    p1.dodajOsobnika(o2);
                } else {
                    wynik.dodajOsobnika(o2);
                    p1.dodajOsobnika(o1);
                }
            }
            return wynik;
        } catch (Exception ex) {
            Logger.getLogger(zastepowanieTurniej.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }


}
