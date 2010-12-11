/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop.Interfejsy;

/**
 *
 * @author Łukasz Synówka
 */
public interface iOperatorKrzyżowania
{
    void dodajOsobnika(iOsobnik o) throws Exception;
    void usunOsobnika(iOsobnik o) throws Exception;

    void wykonaj() throws Exception;
}
