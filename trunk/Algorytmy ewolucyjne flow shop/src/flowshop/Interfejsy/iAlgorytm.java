/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop.Interfejsy;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Łukasz Synówka
 */
public interface iAlgorytm
{
    public iAlgorytm createAlg(int iloscOsobnikow, iDane d) throws FileNotFoundException, IOException;
    public String nazwaAlg();
    void wybor();
    void krzyzowanie();
    void zastepowanie();
}
