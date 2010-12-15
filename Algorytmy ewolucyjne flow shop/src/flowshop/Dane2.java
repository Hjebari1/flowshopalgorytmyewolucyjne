/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package flowshop;

import flowshop.Interfejsy.iDane;

/**
 *
 * @author Łukasz Synówka
 */
public class Dane2 implements iDane
{

    double[][] T =
    {{54, 83, 15, 71, 77, 36, 53, 38, 27, 87, 76, 91, 14, 29, 12, 77, 32, 87, 68, 94},
     {79,  3, 11, 99, 56, 70, 99, 60,  5, 56,  3, 61, 73, 75, 47, 14, 21, 86,  5, 77},
     {16, 89, 49, 15, 89, 45, 60, 23, 57, 64,  7,  1, 63, 41, 63, 47, 26, 75, 77, 40},
     {66, 58, 31, 68, 78, 91, 13, 59, 49, 85, 85,  9, 39, 41, 56, 40, 54, 77, 51, 31},
     {58, 56, 20, 85, 53, 35, 53, 41, 69, 13, 86, 72,  8, 49, 47, 87, 58, 18, 68, 28}};


    public int iloscMaszyn()
    {
        return 5;
    }

    public int iloscZadan() {
        return 20;
    }

    public double czasZadania(int iloscMaszyn, int iloscZadan)
    {
        return T[iloscMaszyn][iloscZadan];
    }


}
