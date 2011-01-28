package operatory;

import flowshop.Interfejsy.iFPopulacjiRozmiar;
import flowshop.Interfejsy.iFunkcjaPopulacji;
import flowshop.Interfejsy.iOsobnik;
import flowshop.Para;
import flowshop.populacja;
import java.util.Iterator;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Łukasz Synówka
 */
public class multiOperator implements iFPopulacjiRozmiar
{
    operatorCX cx;
    operatorOX ox;
    operatorPMX pmx;
    public multiOperator()
    {
        cx= new operatorCX();
        ox= new operatorOX();
        pmx= new operatorPMX();
    }

    @Override
    public populacja wykonaj(populacja popOsobnikow) {
        return wykonaj(popOsobnikow, popOsobnikow.rozmiarPopulacji());
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

    public populacja wykonaj(populacja p, int rozmiar) {
        Random losPoz = new Random();
        populacja pochodneOsobniki = new populacja();
        Object[] osobniki = p.osobniki().keySet().toArray();
        int size = p.osobniki().size();
        while (pochodneOsobniki.rozmiarPopulacji() < rozmiar) {
            try {
                iOsobnik o1 = (iOsobnik) osobniki[losPoz.nextInt(size)];
                iOsobnik o2 = (iOsobnik) osobniki[losPoz.nextInt(size)];
                Object wynik = krzyzuj(o1, o2);
                if (wynik instanceof Para) {

                    pochodneOsobniki.dodajOsobnika((iOsobnik) ((Para) wynik).getFirst());
                    pochodneOsobniki.dodajOsobnika((iOsobnik) ((Para) wynik).getSecond());
                } else {
                    pochodneOsobniki.dodajOsobnika((iOsobnik) wynik);
                }
            } catch (CloneNotSupportedException ex) {
                Logger.getLogger(operatorOX.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(operatorOX.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return pochodneOsobniki;
    }
}