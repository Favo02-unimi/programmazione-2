/**
 * Interfaccia per vettori di valori booleani
 */
public interface BoolVect {
  
  /**
   * Restituisce la dimensione del vettore: la posizione dell'ultimo valore vero + 1
   * 
   * @return la dimensione del vettore: la posizione dell'ultimo valore vero + 1
   */
  public int dim();

  /**
   * Restituisce il valore in posizione index. Se è oltre la dimensione del vettore restituisce false
   * 
   * @param index indice del valore da restituire
   * @return il valore all'indice index
   * @throws IllegalArgumentException se l'indice è negativo
   */
  public boolean get(int index);

  /**
   * Aggiunge un valore in coda al vettore booleano corrente
   * 
   * @param val valore booleano da aggiungere
   */
  public void add(boolean val);

  /**
   * Aggiunge o modifica il valore in posizione index al vettore booleano corrente
   * 
   * @param val valore booleano da aggiungere
   * @param index posizione in cui aggiungere/modificare il valore
   * @throws IllegalArgumentException se l'indice è negativo
   */
  public void add(boolean val, int index);

  /**
   * Restituisce un nuovo BoolVect risultato dell'and logico tra ogni valore del vettore corrente e val
   * 
   * @param val vettore da mettere in and con il vettore corrente
   * @return un nuovo BoolVect risultato dell'and logico tra ogni valore del vettore corrente e val
   */
  public BoolVect and(BoolVect val);

  /**
   * Restituisce un nuovo BoolVect risultato dell'or logico tra ogni valore del vettore corrente e val
   * 
   * @param val vettore da mettere in or con il vettore corrente
   * @return un nuovo BoolVect risultato dell'or logico tra ogni valore del vettore corrente e val
   */
  public BoolVect or(BoolVect val);

  /**
   * Restituisce un nuovo BoolVect risultato dello xor logico tra ogni valore del vettore corrente e val
   * 
   * @param val vettore da mettere in xor con il vettore corrente
   * @return un nuovo BoolVect risultato dello xor logico tra ogni valore del vettore corrente e val
   */
  public BoolVect xor(BoolVect val);

}
