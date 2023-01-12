import java.util.Iterator;

/**
 * Interfaccia che rappresenta un multiset, un insieme di numeri che possono essere ripetuti in ordine sparso
 */
public interface MultiSet<E> extends Iterable<E> {
  
  /**
   * Aggiunge un oggetto di tipo E al multiset, restituendo la nuova molteplicità
   * 
   * @param e elemento da aggiungere
   * @throws NullPointerException se "e" è null
   * @return la nuova molteplicità dell'elemento aggiunto
   */
  public int add(E e);

  /**
   * Aggiunge "qty" volte l'elemento "e" al MultiSet corrente
   * 
   * @param e elemento da aggiungere "qty" volte
   * @param qty numero di volte di aggiungere l'elemento "e"
   */
  public default void addQty(E e, int qty) {
    for (int i = 0; i < qty; i++) {
      add(e);
    }
  }

  /**
   * Rimuove un oggetto dal multiset, restituendo la vecchia (prima della rimozione) molteplicità
   * 
   * @param o elemento da rimuovere
   * @throws NullPointerException se "o" è null
   * @return la vecchia (prima della rimozione) molteplicità
   */
  public int remove(Object o);

  /**
   * Restituisce true se l'oggetto "o" è contenuto dentro il multiset corrente, false altrimenti
   * 
   * @param o oggetto da cercare
   * @throws NullPointerException se "o" è null
   * @return true se l'oggetto "o" è contenuto dentro il multiset corrente, false altrimenti
   */
  public default boolean contains(Object o) {
    return multiplicity(o) != 0;
  }

  /**
   * Restituice la molteplicità dell'oggetto "o" passato
   * 
   * @param o oggetto di cui calcolare la molteplicità
   * @throws NullPointerException se "o" è null
   * @return la molteplicità dell'oggetto "o" passato
   */
  public int multiplicity(Object o);

  /**
   * Restituisce la cardinalità (somma delle molteplicità degli elementi) del multiset corrente
   * 
   * @return la cardinalità (somma delle molteplicità degli elementi) del multiset corrente
   */
  public int size();

  /**
   * Restituisce un nuovo multiset risultato dell'unione tra il multiset corrente e il multiset "o" passato
   * 
   * @param o multiset con cui calcolare l'unione col multiset corrente
   * @throws NullPointerException se "o" è null
   * @return un nuovo multiset risultato dell'unione tra il multiset corrente e il multiset "o" passato
   */
  public MultiSet<E> union(MultiSet<? extends E> o);

  /**
   * Restituisce un nuovo multiset risultato dall'intersezione tra il multiset corrente e il multiset "o" passato
   * 
   * @param o multiset con cui calcolare l'intersezione col multiset corrente
   * @throws NullPointerException se "o" è null
   * @return un nuovo multiset risultato dall'intersezione tra il multiset corrente e il multiset "o" passato
   */
  public MultiSet<E> intersection(MultiSet<? extends E> o);

  /**
   * Restituisce una rappresentazione testuale del multiset
   * 
   * @return la stringa che rappresenta testualmente il multiset
   */
  public default String internalToString() {
    if (size() == 0) {
      return "{}";
    }

    StringBuilder res = new StringBuilder();
    Iterator<E> iter = iterator();
    res.append("{");
    E elem = iter.next();
    res.append(elem + ":" + multiplicity(elem));
    
    while (iter.hasNext()) {
      elem = iter.next();
      res.append(", ");
      res.append(elem + ":" + multiplicity(elem));
    }
    res.append("}");

    return res.toString();
  } 

}
