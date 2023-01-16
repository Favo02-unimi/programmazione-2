/**
 * Interfaccia di un listino,  
 */
public interface Listino {

  /**
   * Restituisce true se il listino contiene il prezzo del giocattolo "giocattolo"
   * 
   * @param giocattolo di cui sapere se è contenuto nel listino
   * @return true se il listino contiene il prezzo del giocattolo "giocattolo"
   * @throws NullPointerException se giocattolo è null
   */
  public boolean contains(Giocattolo giocattolo) throws NullPointerException;

  /**
   * Restituisce il costo unitario del giocattolo "giocattolo"
   * 
   * @param giocattolo di cui chiedere il prezzo
   * @return il costo unitario del giocattolo "giocattolo"
   * @throws NullPointerException se giocattolo è null
   * @throws IllegalArgumentException se il giocattolo non è presente nel listino
   */
  public double getPrezzoUnitario(Giocattolo giocattolo) throws NullPointerException, IllegalArgumentException;

  /**
   * Restituisce il costo totale della quantità "quantita" del giocattolo "giocattolo"
   * 
   * @param giocattolo di cui chiedere il prezzo
   * @param quantita di giocattoli da richiedere
   * @return il costo totale della quantità "quantita" del giocattolo "giocattolo"
   * @throws NullPointerException se giocattolo è null
   * @throws IllegalArgumentException se il giocattolo non è presente nel listino
   */
  public double getPrezzoTotale(Giocattolo giocattolo, int quantita) throws NullPointerException, IllegalArgumentException;

  /**
   * Imposta il prezzo del giocattolo "giocattolo" al prezzo "prezzo"
   * 
   * @param giocattolo di cui impostare il prezzo
   * @param prezzo da impostare
   * @throws NullPointerException se giocattolo è null
   * @throws IllegalArgumentException se il prezzo non è maggiore di 0.01
   */
  public void setPrezzo(Giocattolo giocattolo, double prezzo) throws NullPointerException, IllegalArgumentException;

  /**
   * Rimuove il prezzo del giocattolo dal listino
   * 
   * @param giocattolo di cui rimuovere il prezzo
   * @throws NullPointerException se giocattolo è null
   * @throws IllegalArgumentException se il giocattolo non è presente nel listino
   */
  public void removePrezzo(Giocattolo giocattolo) throws NullPointerException;
  
}
