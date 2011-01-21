package flowshop;

import algorytmy.AlgorytmV1;
import dane.Dane3;
import flowshop.Interfejsy.VAlgorytm;
import flowshop.Interfejsy.iDane;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jakub Banaszewski
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        iDane wejscie;
        try {
            wejscie = new Dane3("MyFile5.txt");
            VAlgorytm algorytm = new AlgorytmV1(1000, wejscie);
            double[] wyn;
            for (int i = 0; i < 1000;i++)
            {
                wyn = algorytm.wykonajIteracje(10);
                System.out.println(i + " " + wyn[0] + " " + wyn[1] + " " + wyn[2]);
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
