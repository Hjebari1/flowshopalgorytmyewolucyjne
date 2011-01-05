package flowshop.Interfejsy;

import flowshop.populacja;

/**
 * Inteface opisujący operatory mutacji.
 * @author Łukasz Synówka
 */
public interface iMutacja
{
    /**
     * Funcja, która dla zadanej populacji wybiera osobniki, na których przeprowadza mutację,
     * @param p Zadana populacja
     */
    public void wynonaj(populacja p);
}
