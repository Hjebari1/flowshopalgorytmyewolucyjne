package flowshop.Interfejsy;

/**
 * Interface reprezentujący zestaw danych problemu.
 * @author Łukasz Synówka
 */
public interface iDane
{
    /**
     * Funkcja zwracająca ilość maszyn w problemie FlowShop
     * @return ilość maszyn dla konkretnego zestawu danych
     */
    public int iloscMaszyn();

    /**
     * Funkcja zwracająca ilość zadań w problemie FlowShop
     * @return ilość zadań dla konkretnego zestawu danych
     */

    public int iloscZadan();

    /**
     * Funkcja zwracająca czas wykonania się pojedyńczej operacji w problemie FlowShop
     * dla konkretnej maszyny i konkretnego zadania.
     * @return wartość czasu jaki jest potrzebny dla operacji.
     */
    public double czasZadania(int iloscMaszyn, int iloscZadan);
}
