/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop.Interfejsy;
/**
 *
 * @author Łukasz Synówka
 */
public interface iOsobnik
{
    void modyfikujGen(int pozycja,int wartosc);
    Object wartoscOsobnika(int pozycja);

    int dlugoscGenomu();
}
