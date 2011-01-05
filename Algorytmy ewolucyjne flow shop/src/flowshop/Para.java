package flowshop;

/**
 * Klasa trzymająca parę obiektów dowolnego typu
 * @author Jakub Banaszewski
 */
public class Para<A, B> {

    private A pierwszy;
    private B drugi;

    Para(A y, B x) {
        pierwszy = y;
        drugi = x;
    }

    public A getFirst() {
        return pierwszy;
    }

    public B getSecond() {
        return drugi;
    }

    @Override
    public String toString() {
        return "Para : \n pierszy : " + pierwszy.toString() + "\n drugi : " + drugi.toString() + "\n";
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Para))
            throw new ClassCastException("Nieprawidłowe porównanie Pary");
        Para p = (Para) o;
        return pierwszy.equals(p.getFirst()) && drugi.equals(p.getSecond());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + (this.pierwszy != null ? this.pierwszy.hashCode() : 0);
        hash = 79 * hash + (this.drugi != null ? this.drugi.hashCode() : 0);
        return hash;
    }
}
