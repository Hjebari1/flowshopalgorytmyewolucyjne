/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop.Interfejsy;

import flowshop.populacja;
import java.util.Random;

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
