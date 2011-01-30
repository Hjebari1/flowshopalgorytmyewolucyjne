package flowshop;

import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iFunkcjaCelu;
import flowshop.Interfejsy.iOsobnik;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Zbiór osobników oparty na liście, który reprezentuje populacje.
 * @author Łukasz Synówka
 */
public class populacja {

    private int iloscOsobnikow;
    private HashMap<iOsobnik, Integer> osobniki;

    public populacja() {
        this.iloscOsobnikow = 0;
        this.osobniki = new HashMap<iOsobnik, Integer>();
    }

    /**
     * Ilość różnych osobników populacji
     * @return
     */
    public int szerokoscPopulacji() {
        return osobniki.size();
    }

    /**
     * Ilość wszystkich osobników populacji
     * @return
     */
    public int rozmiarPopulacji() {
        return iloscOsobnikow;
    }

    public int rozmiarOsobnika() {
        return 0;
    }
    
    public HashMap<iOsobnik,Integer> osobniki()
    {
        return osobniki;
    }

    /**
     * Funkcja dodaje <b>kopie<\b> osobnika do listy.
     * Ważne w przypadku późniejszych zmianach na tych osobnikach.
     * @param osobnik
     */
    public void dodajOsobnika(iOsobnik osobnik) {
        int val = 0;
        if (this.osobniki.containsKey(osobnik)) {
            val = this.osobniki.get(osobnik);
        }
        this.osobniki.put(osobnik.makeCopy(), val + 1);
        this.iloscOsobnikow++;
    }

    public void dodajOsobniki(populacja popOsob) {
        this.osobniki.putAll(popOsob.osobniki);
        this.iloscOsobnikow += popOsob.rozmiarPopulacji();
    }

    public void polaczPopulacje(populacja p2) {
        this.iloscOsobnikow += p2.iloscOsobnikow;
        this.osobniki.putAll(p2.osobniki());
    }

    public boolean usunOsobnika(iOsobnik osobnik) {
        int val = 0;
        if (this.osobniki.containsKey(osobnik)) {
            val = this.osobniki.get(osobnik);
        }
        if (val > 1) {
            val--;
            osobniki.put(osobnik, val);
        }
        else
            return (null != osobniki.remove(osobnik));
        this.iloscOsobnikow--;
        return true;
    }
    /**
     * Iterator po różnorodnych osobnikach populacji
     * @return
     */
    public Iterator<iOsobnik> popIterator() {
        return osobniki.keySet().iterator();
    }

    public iOsobnik min(iFunkcjaCelu f, iDane d) {
        iOsobnik min = null;
        iOsobnik o = null;
        for (Iterator<iOsobnik> i = popIterator(); i.hasNext();) {
            o = i.next();
            if (min == null)
                min = o;
            else if (f.wartoscFunkcji(o, d) < f.wartoscFunkcji(min, d)) {
                min = o;
            }
        }
        return min;
    }
    /**
     * Funkcja, która przetwarza formę słownikowego trzymania populacji
     * w formę listową. Listę odpowiadającą stanowi populacji zwraca w wyniki.
     * @return Stan populacji w formie listy.
     */
    public List<iOsobnik> osobnikiPop()
    {
        iOsobnik o=null;
        ArrayList<iOsobnik> wyn = new ArrayList<iOsobnik>();
        for (Iterator<iOsobnik> i = popIterator(); i.hasNext();)
        {
            o = i.next();
            for (int j = osobniki.get(o);j>0;j--)
                wyn.add(o);
       }
        return wyn;
    }
    //TODO kopiowanie populacji do operatorów ??

    @Override
    public String toString() {
        return osobniki.toString();
    }
}
