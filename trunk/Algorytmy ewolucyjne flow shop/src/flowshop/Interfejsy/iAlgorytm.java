package flowshop.Interfejsy;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Łukasz Synówka
 */
public interface iAlgorytm
{
    /**
     * Tworzy kopię swojego odpowiednika o zadanych parametrach
     * @param iloscOsobnikow - Ilość osobników populacji
     * @param d - źródło danych
     * @return Instacja algorytmu
     * @throws FileNotFoundException nie znaleziono pliku na zadanej ścieżce
     * @throws IOException błąd otworzenia pliku z danymi
     */
    public iAlgorytm createAlg(int iloscOsobnikow, iDane d) throws FileNotFoundException, IOException;
    /**
     * Metoda "przedstawiająca algorytm na liście możliwych wyborów metody
     * @return String krótko opisujący metodę
     */
    public String nazwaAlg();
    /**
     * Metoda zwracająca wartość minimalną populacji
     * @return Wartość minimalna populacji
     */
    public double getMin();
    /**
     * Metoda wybierania osobników do krzyżowania (selekcja)
     */
    void wybor();
    /**
     * Metoda krzyżowania osobników. Tutaj też dokonuje się mutacja
     */
    void krzyzowanie();
    /**
     * Metoda zastępowania osobników
     */
    void zastepowanie();
}
