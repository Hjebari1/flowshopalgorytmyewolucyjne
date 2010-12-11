/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;


import flowshop.Interfejsy.iOsobnik;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Łukasz Synówka
 */
public class osobnikFlowShop implements iOsobnik
{
    int[] permutacja;
    int dlugoscPermutacji;

    public osobnikFlowShop()
    {
        this.dlugoscPermutacji = 100;
        permutacja = new int[100];
        List <Integer>pula = new LinkedList<Integer>();

        for (int i=0;i<this.dlugoscPermutacji;i++)
        {
            pula.add(i);
        }
        Random r = new Random();
        int a;
        for (int i=this.dlugoscPermutacji;i>0;i--)
        {
            a = r.nextInt(i);
            this.permutacja[i-1] = pula.remove(a);
        }
    }

    public osobnikFlowShop(int dl)
    {
        permutacja = new int[dl];
        this.dlugoscPermutacji=dl;
        List<Integer> pula = new LinkedList<Integer>();

        for (int i=0;i<this.dlugoscPermutacji;i++)
        {
            pula.add(i);
        }
        Random r = new Random();
        int a;
        for (int i=this.dlugoscPermutacji;i>0;i--)
        {
            a = r.nextInt(i);
            this.permutacja[i-1] = pula.remove(a);
        }
    }

    public osobnikFlowShop(int dl, int[] gen)
    {
        this.dlugoscPermutacji = dl;
        this.permutacja = gen;
    }


    public void modyfikujGen(int pozycja, Object wartosc)
    {
        int wartInt = (Integer) (wartosc);
        if (pozycja<this.dlugoscPermutacji) this.permutacja[pozycja] = wartInt;
    }

    public Object wartoscOsobnika(int pozycja)
    {
        return this.permutacja[pozycja];
    }

    public int dlugoscGenomu()
    {
        return this.dlugoscPermutacji;
    }




}
