package flowshop;

import algorytmy.AlgorytmV1;
import algorytmy.AlgorytmV2;
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
            int ileIter = 1000;
            if (args.length > 0) {
                ileIter = Integer.parseInt(args[0]);
            }
            for (int i = 0; i < ileIter / 20; i++) {
                algorytm.wykonajIteracje(20);
                System.out.println(i * 20 + " " + algorytm.toString());
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
