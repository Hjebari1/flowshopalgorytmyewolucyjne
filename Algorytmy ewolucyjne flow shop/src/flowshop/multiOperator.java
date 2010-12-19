package flowshop;

import flowshop.Interfejsy.iOperatorKrzyżowania;
import flowshop.Interfejsy.iOsobnik;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Łukasz Synówka
 */
public class multiOperator extends iOperatorKrzyżowania
{
    operatorCX cx;
    operatorOX ox;
    operatorPMX pmx;
    populacja popOsobnikow;
    public multiOperator()
    {
        cx= new operatorCX();
        ox= new operatorOX();
        pmx= new operatorPMX();
    }

    @Override
    public populacja wykonaj(populacja popOsobnikow)
    {
        Random losPoz = new Random();
        populacja pochodneOsobniki = new populacja();
        populacja rodzice = new populacja();
        rodzice.polaczPopulacje(popOsobnikow);

        while (rodzice.rozmiarPopulacji() > 1) {
            try {
                iOsobnik o1 = rodzice.usunOsobnika(losPoz.nextInt(rodzice.rozmiarPopulacji()));
                iOsobnik o2 = rodzice.usunOsobnika(losPoz.nextInt(rodzice.rozmiarPopulacji()));
                Object wynik = krzyzuj(o1, o2);
                if ( wynik instanceof Para)
                {

                    pochodneOsobniki.dodajOsobnika((iOsobnik)((Para)wynik).getFirst());
                    pochodneOsobniki.dodajOsobnika((iOsobnik)((Para)wynik).getSecond());
                }
                else
                    pochodneOsobniki.dodajOsobnika((iOsobnik)wynik);
            } catch (CloneNotSupportedException ex)
            {
                Logger.getLogger(operatorOX.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(operatorOX.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pochodneOsobniki;
}

    private Object krzyzuj(iOsobnik o1, iOsobnik o2) throws CloneNotSupportedException, Exception
    {
        Random r = new Random();
        int a=r.nextInt(3);
        if (a==0)
            return cx.krzyzuj(o1, o2);
        if (a==1)
        {
            int poz1 = r.nextInt(o1.dlugoscGenomu());
            int poz2 = r.nextInt(o2.dlugoscGenomu());
            if (poz1 > poz2)
            {
                int tmp = poz1;
                poz1 = poz2;
                poz2 = tmp;
            }
            return ox.krzyzuj(o1, o2,poz1,poz2);
        }
        int poz1 = r.nextInt(o1.dlugoscGenomu());
        int poz2 = r.nextInt(o2.dlugoscGenomu());
        if (poz1 > poz2)
        {
            int tmp = poz1;
            poz1 = poz2;
            poz2 = tmp;
        }
        return pmx.krzyzuj(o1, o2,poz1,poz2);

    }
}