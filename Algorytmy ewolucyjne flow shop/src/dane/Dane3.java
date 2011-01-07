/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dane;

import flowshop.Interfejsy.iDane;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Dzieki obiektom tej kalsy mozna wyciągać dane z pliów tekstowych
 * W pierwszej linijce pliku powinna się znajdować informacjia o rozmiarze danych
 * a w kolejnyc czas oddzielone pojedynczymi odstępami
 * każdy wiersz odpowiada jednej maszynie.
 *
 * @author Łukasz Synówka
 */


public class Dane3 implements iDane
{

    int iloscM;
    int iloscZ;
    double T[][];

    public Dane3(String s) throws FileNotFoundException, IOException
    {

        if (s==null) s = new String("MyFile.txt");
        RandomAccessFile raf = new RandomAccessFile(s,"r");

        String[] temp;
        temp = raf.readLine().split(" ");
        this.iloscZ=Integer.parseInt(temp[0]);
        this.iloscM=Integer.parseInt(temp[1]);


        this.T = new double[this.iloscM][this.iloscZ];
        for(int i=0;i<this.iloscM;i++)
        {
            temp = raf.readLine().replaceAll("  ", " ").split(" ");
            for(int j=0;j<this.iloscZ;j++)
            {
                T[i][j]=Double.parseDouble(temp[j]);
            }
        }
    }

    public int iloscMaszyn()
    {
        return this.iloscM;
    }

    public int iloscZadan()
    {
        return this.iloscZ;
    }

    public double czasZadania(int iloscMaszyn, int iloscZadan)
    {
        return T[iloscMaszyn][iloscZadan];
    }
}
