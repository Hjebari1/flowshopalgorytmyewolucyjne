package flowshop;

import flowshop.Interfejsy.iFPopulacjiRozmiar;
import flowshop.Interfejsy.iFunkcjaPopulacji;
import flowshop.Interfejsy.iOsobnik;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Klasa reprezentująca funcję znajdującą unikalne osobniki i zwracającą ich zbiór.
 * @author Jakub Banaszewski
 */
public class Kataklizm implements iFunkcjaPopulacji {

    int iloscIteracji;
    int ileJeszcze;
    int orgRozPopulacji;
    int ileZostaje;
    int ileMutuje;
    iFPopulacjiRozmiar mutacja;
    iFPopulacjiRozmiar selekcja;
    /**
     * Konstruktor, który decyduje o tym ile osobników pozostaje z poprzednej
     * populacji i ile jest generowanych nowych osobników.
     * Dodatkowymi narzędziami jest selekcja i mutacja.
     * @param ileIter Określa co ile iteracji następuje kataklizm
     * @param rozmiarP Definiuje orginalny rozmiar populacji
     * @param ilezostaje Określa ile osobników z poprzedniej populacji przetrwa
     * @param ilemutuje Liczba osobników starej populacji, którzy przechodzą mutację i są dołączani do nowej populacji
     * @param jakaSelekcja Jak wybierane są osobniki, które mają przetrwać
     */
    public Kataklizm(int ileIter,int rozmiarP,int ilezostaje,int ilemutuje, iFPopulacjiRozmiar jakaSelekcja) {
        iloscIteracji = ileIter;
        ileJeszcze = ileIter;
        orgRozPopulacji = rozmiarP;
        ileZostaje = ilezostaje;
        ileMutuje = ilemutuje;
        mutacja = new MutacjaPrzesuniecie(0.1);
        selekcja = jakaSelekcja;
    }

    private boolean czyTeraz() {
        ileJeszcze--;
        if (ileJeszcze == 0) {
            ileJeszcze = iloscIteracji;
            return true;
        }
        return false;
    }

    /**
     * Ustawienie licznika iteracji ponownie na wartość początkową.
     */
    public void reset() {
        ileJeszcze = iloscIteracji;
    }

    /**
     * Fukcja wykonująca zadanie klasy
     * @param p Populacja wejsciowa
     * @return Nowa populacja z fragmentem starej.
     */
    public populacja wykonaj(populacja p) {
        if (czyTeraz()) {
            try {
                System.out.println("# Idzie kataklizm!");
                populacja wynik = new populacja();
                Set<iOsobnik> zbior = p.osobniki().keySet();
                int limit = ileZostaje;
                if (ileZostaje > zbior.size()) {
                    limit = zbior.size();
                }
                wynik = selekcja.wykonaj(p, limit);
                populacja mutanty = mutacja.wykonaj(p, ileMutuje);
                wynik.polaczPopulacje(mutanty);
                for (int i = wynik.rozmiarPopulacji(); i < orgRozPopulacji; i++) {
                    wynik.dodajOsobnika(new osobnikFlowShop(wynik.rozmiarOsobnika()));
                }
                return wynik;
            } catch (Exception ex) {
                Logger.getLogger(Kataklizm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return p;
    }
}
