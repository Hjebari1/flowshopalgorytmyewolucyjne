package flowshop;

import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iFunkcjaCelu;
import flowshop.Interfejsy.iOsobnik;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Zbiór osobników oparty na liście, który reprezentuje populacje.
 * @author Łukasz Synówka
 */
public class populacjaOld {

    private int iloscOsobnikow;
    private List<iOsobnik> osobniki;

    public populacjaOld() {
        this.iloscOsobnikow = 0;
        this.osobniki = new LinkedList<iOsobnik>();
    }

    public int rozmiarPopulacji() {
        return osobniki.size();
    }
    public int rozmiarOsobnika() {
        if (iloscOsobnikow > 0)
            return osobniki.get(0).dlugoscGenomu();
        else
            return 0;
    }
    /**
     * Funkcja dodaje <b>kopie<\b> osobnika do listy.
     * Ważne w przypadku późniejszych zmianach na tych osobnikach.
     * @param osobnik
     */
    public void dodajOsobnika(iOsobnik osobnik) {
        this.osobniki.add(osobnik.makeCopy());
        this.iloscOsobnikow++;
    }
    public void dodajOsobniki(Collection<iOsobnik> osobniki)
    {
        this.osobniki.addAll(osobniki);
        this.iloscOsobnikow = this.osobniki.size();
    }
    public iOsobnik usunOsobnika(int pozycja) {
        this.iloscOsobnikow--;
        return this.osobniki.remove(pozycja);
    }

    public void polaczPopulacje(populacjaOld p2) {
        this.iloscOsobnikow += p2.iloscOsobnikow;
        this.osobniki.addAll(p2.osobniki);
    }

    public boolean usunOsobnika(iOsobnik o) {
        // usuwa tylko jednego osobnika
        this.iloscOsobnikow--;
        return this.osobniki.remove(o);
    }

    public ListIterator<iOsobnik> popIterator() {
        return osobniki.listIterator();
    }

    public iOsobnik min(iFunkcjaCelu f,iDane d)
    {
        iOsobnik min = this.osobniki.get(0);

        for(int i=0;i<this.iloscOsobnikow;i++)
        {
            if (f.wartoscFunkcji(this.osobniki.get(i), d)<f.wartoscFunkcji(min, d))
                min = this.osobniki.get(i);
        }
        return min;
    }
    //TODO kopiowanie populacji do operatorów ??
     
    @Override
    public String toString() {
        return osobniki.toString();
    }


}
