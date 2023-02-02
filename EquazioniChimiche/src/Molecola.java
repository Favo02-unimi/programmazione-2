import java.util.Iterator;

/**
 * Interfaccia che rappresenta una molecola, formata da più elementi chimici che possono essere dello stesso tipo o diversi
 */
public interface Molecola {
    
    /**
     * Restituisce la formula della molecola corrente
     * 
     * @return la formula della molecola corrente
     */
    public String getFormula();

    /**
     * Restituisce il peso della molecola corrente
     * 
     * @return il peso della molecola corrente
     */
    public double getPeso();

    /**
     * Restituisce la quantità di elementi di simbolo "simbolo", se l'elemento è presente nella molecola, altrimenti 0
     * 
     * @param simbolo di cui cercare la quantita
     * @return la quantità di elementi di simbolo "simbolo", se l'elemento è presente nella molecola, altrimenti 0
     * @throws NullPointerException se simbolo è null
     */
    public int quantita(String simbolo) throws NullPointerException;

    /**
     * Restituisce un iteratore che itera sugli elementi chimici che compongono la molecola. La quantità non viene restituita
     * 
     * @return un iteratore che itera sugli elementi chimici che compongono la molecola. La quantità non viene restituita
     * @throws UnsupportedOperationException se viene invocato il metodo remove() dell'iteratore
     */
    public Iterator<ElementoChimico> iterator() throws UnsupportedOperationException;

}
