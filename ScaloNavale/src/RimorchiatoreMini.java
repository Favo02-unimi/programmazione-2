import java.util.Objects;

/**
 * Classe che implementa una versione del rimorchiatore in cui è possibile spostare una sola nave alla volta
 */
public class RimorchiatoreMini extends RimorchiatoreAbstract {

    /**
     * Crea un nuovo rimorchiatore mini dal nome "nome"
     * 
     * @param nome del rimorchiatore
     * @throws NullPointerException se scalo è null
     * @throws NullPointerException se nome è null
     * @throws NullPointerException se scalo è null
     * @throws IllegalArgumentException se nome è vuoto
     */
    public RimorchiatoreMini(String nome, Scalo scalo) throws NullPointerException, IllegalArgumentException {
        super(nome, scalo);
    }
    
    @Override
    public int sposta(int qty, int from, int to) throws NullPointerException, IllegalArgumentException {
        controlli(qty, from, to);
        
        for (int i = 0; i < qty; i++) {
            spostaUnitario(from, to);
        }

        return qty;
    }
    
    /**
     * Sposta una nave alla volta dal molo from al molo to
     * 
     * @param scalo molo dal quale spostare
     * @param from molo dal quale spostare
     * @param to molo al quale spostare
     * @throws NullPointerException se from è null
     * @throws NullPointerException se to è null
     */
    private void spostaUnitario(int from, int to) throws NullPointerException {
        Objects.requireNonNull(to, "Il molo dal quale spostare non può essere null");
        Objects.requireNonNull(to, "Il molo a cui spostare non può essere null");
        getScalo().attracca(getScalo().salpa(from), to);
    }

    /**
     * RI:
     *  nome != null
     *  nome non vuoto
     */
    private boolean repOk() {
        if (getNome() == null) return false;
        if (getNome().isEmpty()) return false;

        return true;
    }

}
