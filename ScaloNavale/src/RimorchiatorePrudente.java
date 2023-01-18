import java.util.Stack;

/**
 * Classe che implementa una versione del rimorchiatore in cui è possibile spostare più navi contemporaneamente purchè la somma dei loro pesi sia sotto al carico massimo del rimorchiatore corrente
 */
public class RimorchiatorePrudente extends RimorchiatoreAbstract {

    private final int caricoMassimo;

    /**
     * Crea un nuovo rimorchiatore prudente con carico massimo passato
     * 
     * @param nome del rimorchiatore
     * @param caricoMassimo massimo carico spostabile dal rimorchiatore
     * @throws NullPointerException se scalo è null
     * @throws NullPointerException se nome è null
     * @throws IllegalArgumentException se nome è vuoto
     * @throws IllegalArgumentException se carico massimo è < 1
     */
    public RimorchiatorePrudente(String nome, Scalo scalo, int caricoMassimo) throws NullPointerException, IllegalArgumentException {
        super(nome, scalo);
        if (caricoMassimo < 1) {
            throw new IllegalArgumentException("Il carico massimo non può essere minore di 1");
        }

        this.caricoMassimo = caricoMassimo;
    }

    @Override
    public int sposta(int qty, int from, int to) throws IllegalArgumentException {
        controlli(qty, from, to);

        int curSpostate = 0;
        int viaggi = 0;
        while (curSpostate < qty) {
            curSpostate = spostaPesoMassimo(qty, curSpostate, from, to);
            viaggi++;
        }

        return viaggi;
    }

    /**
     * Sposta tante navi quante possibile dal carico massimo alla volta da from a to
     * 
     * @param qty numero di navi totale da spostare
     * @param curSpostate numero di navi spostate fin'ora
     * @param from molo dal quale spostare
     * @param to molo al quale spostare
     * @throws NullPointerException se from è null
     * @throws NullPointerException se to è null
     * @return numero di navi spostate
     */
    public int spostaPesoMassimo(int qty, int curSpostate, int from, int to) throws NullPointerException {
        Stack<NaveCargo> temp = new Stack<>();
        
        int curCarico = 0; // peso attaccato al rim

        // attaccare prima barca indipendentemente dal peso
        temp.push(getScalo().salpa(from));
        curCarico = temp.peek().getPeso(); // salvare suo peso
        curSpostate++;

        // finchè ci stanno navi, attacca
        while (curSpostate < qty && (curCarico + getScalo().pesoProssimaASalpare(from) <= caricoMassimo)) {
            temp.push(getScalo().salpa(from));
            curCarico += temp.peek().getPeso();
            curSpostate++;
        }

        // stacca le navi attaccate
        int rimorchiatoreLoad = temp.size(); // la size dello stack cambia durante l'esecuzione del loop
        for (int i = 0; i < rimorchiatoreLoad; i++) {
            getScalo().attracca(temp.pop(), to);
        }

        return curSpostate;
    }

    /**
     * RI:
     *  nome != null
     *  nome non vuoto
     *  pesoMassimo > 0
     */
    private boolean repOk() {
        if (getNome() == null) return false;
        if (getNome().isEmpty()) return false;
        if (caricoMassimo < 1) return false;

        return true;
    }

}
