/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;

import flowshop.Interfejsy.iDane;

/**
 * klasa zawierająca informacjie czy położenie sąsiednich genów jest pozytywne
 * "w[i][j]" zawiera ifnormacje czy warto postawić obok siebie element 'i' i 'j'
 * im wyższa wartość tym lepiej
 * @author Łukasz Synówka
 */
public class wagiPar
{
    double w[][];
    funkcjaCeluFlowShop f;
    iDane d;
    int n;
    double wsp;

    public wagiPar(funkcjaCeluFlowShop f,iDane d,double wsp)
    {
        this.wsp = wsp;
        this.f = f;
        this.d = d;
        n=d.iloscZadan();
        w = new double[n][n];
        for (int i=0;i<n;i++)
        {
            for (int j=0;j<n;j++)
            {
                w[i][j]=0.5;
            }
        }
    }
    
    public void wagaMutacjaZamiana(osobnikFlowShop o1, osobnikFlowShop o2)
    {
        int n1=o1.dlugoscGenomu();
        int n2=o2.dlugoscGenomu();

        int mnoznik = 0;

        if (f.wartoscFunkcji(o1,d)<f.wartoscFunkcji(o2, d))
            mnoznik=-1;
        else
            if (f.wartoscFunkcji(o1,d)==f.wartoscFunkcji(o2, d))
                mnoznik=0;
            else
                mnoznik=1;

        int tab[][] = new int[n1][n2];
        for (int i=0;i<n1;i++)
        {
            for (int j=0;j<n2;j++)
            {
                tab[i][j]=0;
            }
        }

        for (int i=0;i<n1-1;i++)
        {
            tab[(Integer)(o1.wartoscOsobnika(i))][(Integer)(o1.wartoscOsobnika(i+1))]=1;
        }


        for (int i=0;i<n1-1;i++)
        {

            if (tab[(Integer)(o2.wartoscOsobnika(i))][(Integer)(o2.wartoscOsobnika(i+1))]!=0)
                tab[(Integer)(o2.wartoscOsobnika(i))][(Integer)(o2.wartoscOsobnika(i+1))]=2;
        }
        for (int i=0;i<n1;i++)
        {
            for (int j=0;j<n2;j++)
            {
                if (tab[i][j]==2)
                {
                    w[i][j]=w[i][j]+mnoznik*w[i][j]*wsp;
                    if (w[i][j]>1) w[i][j]=1;
                    if (w[i][j]<0) w[i][j]=0;
                }
            }
        }

    }

    @Override
    public String toString()
    {
        String result ="";

        for (int i=0;i<n;i++)
        {
            for (int j=0;j<n;j++)
            {
                result +=w[i][j];
                result += " ";
            }
            result +="\n";
        }
        return result;
    }

    public double wartosc(int i,int j)
    {
        return w[i][j];
    }
    
}
