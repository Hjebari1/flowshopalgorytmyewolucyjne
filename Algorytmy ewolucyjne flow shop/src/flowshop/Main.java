package flowshop;

import flowshop.Interfejsy.iAlgorytm;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Łukasz Synówka
 */
public class Main {

    static public String powitanie = "Lista komend : \n"
            + "lista - wypisuje mozliwe algorytmy\n"
            + "wykonaj <numer_algorytmu> <ilosc_osobnikow> <sciezka danych> \n";
    static LinkedList<iAlgorytm> listaAlgorytmow = new LinkedList<iAlgorytm>();

    static {
        try {
            listaAlgorytmow.add(new Algorytm1(0, null));
            listaAlgorytmow.add(new Algorytm2(0, null));
            listaAlgorytmow.add(new Algorytm2K(0, null));
            listaAlgorytmow.add(new Algorytm3(0, null));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * @param args the command line arguments
     * Dopisać parametryzacje
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {

        iAlgorytm wykonajAlg = null;
        if (args.length > 0) {
            if (args[0].trim().equalsIgnoreCase("lista")) {
                int licz = 1;
                for (Iterator<iAlgorytm> i = listaAlgorytmow.iterator(); i.hasNext();) {
                    System.out.println(licz++ + " - " + i.next().nazwaAlg());
                }
            } else if (args[0].trim().equalsIgnoreCase("wykonaj")) {
                if (args.length < 4) {
                    System.out.println("Nie podano odpowiednich parametrów");
                }
                int ktory_alg = Integer.parseInt(args[1].trim());
                wykonajAlg = listaAlgorytmow.get(ktory_alg - 1);
                int ile_osob = Integer.parseInt(args[2].trim());
                wykonajAlg = wykonajAlg.createAlg(ile_osob, new Dane3(args[3].trim()));
                if (wykonajAlg == null) System.out.println("Dupa!");
            }
            if (wykonajAlg != null) {
                for (int i = 0; i < 1000; i++) {
                    wykonajAlg.wybor();
                    wykonajAlg.krzyzowanie();
                    wykonajAlg.zastepowanie();
                    System.out.println(wykonajAlg.toString());
                }
                System.out.println(wykonajAlg.toString());
            }
        } else {
            System.out.println(powitanie);
        }
    }
}
