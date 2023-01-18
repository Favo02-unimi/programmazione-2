import java.util.Objects;

/**
 * Classe astratta che aggiunge come stato del rimorchiatore il suo nome identificativo
 * Offre anche dei metodi di controllo di validità di parametri
 */
public abstract class RimorchiatoreAbstract implements Rimorchiatore {

    private final String nome;
    private final Scalo scalo;

    /**
     * Crea un nuovo rimorchiatore dal nome "nome"
     * 
     * @param nome del rimorchiatore
     * @throws NullPointerException se scalo è null
     * @throws NullPointerException se nome è null
     * @throws IllegalArgumentException se nome è vuoto
     */
    public RimorchiatoreAbstract(String nome, Scalo scalo) throws NullPointerException, IllegalArgumentException {
        controlliNome(nome);
        Objects.requireNonNull(scalo);

        this.nome = nome;
        this.scalo = scalo;
    }

    /**
     * Restituisce il nome del rimorchiatore
     * 
     * @return il nome del rimorchiatore
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce lo scalo del rimorchiatore
     * 
     * @return il nome del rimorchiatore
     */
    public Scalo getScalo() {
        return this.scalo;
    }


    /**
     * Controlla la validità del parametro nome
     * 
     * @param nome parametro nome da controllare
     * @throws NullPointerException se nome è null
     * @throws IllegalArgumentException nome è vuoto
     */
    protected void controlliNome(String nome) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(nome, "Il nome del rimorchiatore non può essere null");
        if (nome.isEmpty()) {
            throw new IllegalArgumentException("Il nome del rimorchiatore non può essere vuoto");
        }
    }

    /**
     * Controlla la validità dei parametri qty, from, to
     * 
     * @param qty parametro quantità da controllare
     * @param from parametro molo from da controllare
     * @param to parametro molo to da controllare
     * @throws IllegalArgumentException se from non esiste (< 0 o > numero moli)
     * @throws IllegalArgumentException se to non esiste (< 0 o > numero moli)
     * @throws IllegalArgumentException se qty è < 1
     */
    protected void controlli(int qty, int from, int to) throws IllegalArgumentException {
        if (qty < 1) {
            throw new IllegalArgumentException("Il numero di navi da spostare non può essere minore di 1");
        }
        if (qty < 1) {
            throw new IllegalArgumentException("Il numero di navi da spostare non può essere minore di 1");
        }
        if (scalo.moloExists(from) || scalo.moloExists(to)) {
            throw new IllegalArgumentException("I moli degli spostamenti non esistono");
        }
    }
    
    /**
     * RI:
     *  scalo != null
     *  nome != null
     *  nome non vuoto
     */
    private boolean repOk() {
        if (scalo == null) return false;
        if (nome == null) return false;
        if (nome.isEmpty()) return false;

        return true;
    }
}
