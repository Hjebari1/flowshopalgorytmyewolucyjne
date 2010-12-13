package flowshop;

import flowshop.Interfejsy.iOperatorKrzyżowania;
import flowshop.Interfejsy.iOsobnik;
import java.util.LinkedList;

/**
 *
 * @author Jakub Banaszewski
 */
public class operatorCX extends iOperatorKrzyżowania {

    public operatorCX(populacja daneWejsciowe) {
        zbiorOsobnikow = daneWejsciowe;
    }

    public void wykonaj() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public iOsobnik krzyzuj(iOsobnik o1, iOsobnik o2) throws CloneNotSupportedException, Exception { //domyślnie private, dla testów public
        // mozna dodac losowanie pozycji startowej do szukania cyklu
        if (o1.dlugoscGenomu() != o2.dlugoscGenomu()) throw new Exception("Nierówne genomy do krzyżowania!");
        int size = o1.dlugoscGenomu();
        if (size == 0) throw new Exception("Pusty genom do krzyżowania!");
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
