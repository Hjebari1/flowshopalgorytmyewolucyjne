package flowshop.Interfejsy;

import flowshop.populacja;
/**
 *
 * @author Łukasz Synówka
 */
public abstract class iOperatorKrzyżowania
{
    protected populacja zbiorOsobnikow = null;
    public void dodajOsobnika(iOsobnik o) throws Exception {
        zbiorOsobnikow.dodajOsobnika(o);
    }

    public void usunOsobnika(iOsobnik o) {
        zbiorOsobnikow.usunOsobnika(o);
    }
    abstract public populacja wykonaj(populacja p);
}
