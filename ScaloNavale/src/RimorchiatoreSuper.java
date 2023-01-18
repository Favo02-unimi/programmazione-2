import java.util.Stack;

/**
 * Classe che implementa una versione del rimorchiatore in cui è possibile spostare potenzialmente infinite navi insieme
 */
public class RimorchiatoreSuper extends RimorchiatoreAbstract {

    /**
     * Crea un nuovo rimorchiatore super dal nome "nome"
     * 
     * @param nome del rimorchiatore
     * @throws NullPointerException se nome è null
     * @throws NullPointerException se scalo è null
     * @throws IllegalArgumentException se nome è vuoto
     */
    public RimorchiatoreSuper(String nome, Scalo scalo) throws NullPointerException, IllegalArgumentException {
        super(nome, scalo);
    }

    @Override
    public int sposta(int qty, int from, int to) throws IllegalArgumentException {
        controlli(qty, from, to);

        Stack<NaveCargo> temp = new Stack<>();

        for (int i = 0; i < qty; i++) {
            temp.push(getScalo().salpa(from));
        }
        for (int i = 0; i < qty; i++) {
            getScalo().attracca(temp.pop(), to);
        }

        return 1;
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
