package flowshop;

import flowshop.Interfejsy.iDane;
import java.util.Random;

/**
 *
 * @author Łukasz Synówka
 */
public class Dane1 implements iDane
{

    int iloscMaszyn=5;
    int iloscZadan=5;
    double[][] T = new double[iloscMaszyn][iloscZadan];
    public Dane1()
    {

        Random r = new Random();
        for(int i=0;i<iloscMaszyn;i++)
            for(int j=0;j<iloscZadan;j++)
                T[i][j] = r.nextInt(10)+1;
    }

    public int iloscMaszyn()
    {
        return iloscMaszyn;
    }

    public int iloscZadan()
    {
        return iloscZadan;
    }

    public double czasZadania(int iloscMa, int iloscZa)
    {
        return T[iloscMa][iloscZa];
    }

}
