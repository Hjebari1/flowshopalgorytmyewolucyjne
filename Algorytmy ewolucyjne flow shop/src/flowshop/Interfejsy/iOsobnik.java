package flowshop.Interfejsy;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Łukasz Synówka
 */
public abstract class iOsobnik implements Cloneable {

    abstract public void modyfikujGen(int pozycja, Object wartosc);

    abstract public Object wartoscOsobnika(int pozycja);

    abstract public int dlugoscGenomu();

    abstract public int znajdzPozGenu(int zakresOd,int zakresDo,Object wartosc);

    abstract public int znajdzPozGenuPoza(int zakresOd,int zakresDo,Object wartosc);

    @Override
    abstract public boolean equals(Object o);
    @Override
    abstract public int hashCode();

    public iOsobnik makeCopy() {
        try {
            return (iOsobnik) this.clone();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(iOsobnik.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
