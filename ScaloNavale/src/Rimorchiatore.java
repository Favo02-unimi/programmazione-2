/**
 * Interfaccia che descrive il comportamento di un rimorchiatore
 */
public interface Rimorchiatore {
    
    /**
     * Restituisce il numero di viaggi necessari dal rimorchiatore per spostare il numero "qty" di navi dal molo numero "from" al molo numero "to"
     * 
     * @param Scalo scalo nel quale spostare le navi
     * @param qty numero di navi da spostare
     * @param from molo da cui spostare le navi
     * @param to molo al quale spostare le navi
     * @throws IllegalArgumentException se from non esiste (< 0 o > numero moli)
     * @throws IllegalArgumentException se to non esiste (< 0 o > numero moli)
     * @throws IllegalArgumentException se qty è < 1
     * @return il numero di viaggi necessari dal rimorchiatore per spostare le navi
     */
    public int sposta(int qty, int from, int to) throws IllegalArgumentException;

    /**
     * Restituisce il nome del rimorchiatore
     * 
     * @return il nome del rimorchiatore
     */
    public String getNome();

}
