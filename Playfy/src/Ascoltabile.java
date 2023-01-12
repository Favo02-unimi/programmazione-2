/**
 * Interfaccia di un audio ascoltabile
 */
public interface Ascoltabile {
  
  /**
   * Restituisce il nome dell'audio ascoltabile
   * 
   * @return il nome dell'audio ascoltabile
   */
  public String nome();

  /**
   * Restituisce la stringa che rappresenta la durata totale dell'audio ascoltabile, in formato HH:MM:SS o MM:SS (il più piccolo)
   * 
   * @return la durata totale dell'audio ascoltabile
   */
  public String durata();

}
