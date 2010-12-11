/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop.Interfejsy;

/**
 *
 * @author Łukasz Synówka
 */
public interface iDane
{
    int iloscMaszyn();
    int iloscZadan();
    
    double czasZadania(int iloscMaszyn, int iloscZadan);
}
