package flowshop;

import flowshop.Interfejsy.iDane;
import flowshop.Interfejsy.iFunkcjaCelu;
import flowshop.Interfejsy.iOsobnik;
import flowshop.Interfejsy.iSelekcja;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Jakub Banaszewski
 */
public class selekcjaRuletka implements iSelekcja {

    populacja daneWejsciowe;
    List<Object> wartosciOsobnikow;
    iDane dane;
    public selekcjaRuletka(populacja dW, iDane dane) {
        daneWejsciowe = dW;
        wartosciOsobnikow = new LinkedList<Object>();
        this.dane = dane;
    }
    public List<iOsobnik> wybranaPopulacja(populacja p) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void wyliczWart()
    {
        iFunkcjaCelu f = new funkcjaCeluFlowShop();
        int i;
        for (Iterator popIter = daneWejsciowe.popIterator();popIter.hasNext();)
        {
            wartosciOsobnikow.add(f.wartoscFunkcji((iOsobnik) popIter.next(),dane));
        }
    }
}
