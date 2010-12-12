package flowshop;

import flowshop.Interfejsy.iOperatorKrzyżowania;
import flowshop.Interfejsy.iOsobnik;
import java.util.LinkedList;

/**
 *
 * @author Jakub Banaszewski
 */
public class OperatorCX implements iOperatorKrzyżowania {

    public void dodajOsobnika(iOsobnik o) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void usunOsobnika(iOsobnik o) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void wykonaj() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public iOsobnik krzyzuj(iOsobnik o1, iOsobnik o2) throws CloneNotSupportedException { //domyślnie private, dla testów public
        int size = o1.dlugoscGenomu();
        iOsobnik wyn = o1.makeCopy();
        int i = 0;
        LinkedList<Integer> listaCyklu = new LinkedList<Integer>();
        listaCyklu.add(0);
        do {
            i = o1.znajdzPozGenu(0, size, o2.wartoscOsobnika(i));
            listaCyklu.add(new Integer(i));
        } while (i != 0);

        for (int j = 0; j < size; j++) {
            wyn.modyfikujGen(j, listaCyklu.contains(j) ? o1.wartoscOsobnika(j) : o2.wartoscOsobnika(j));
        }
        return wyn;
    }
}
