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
   * Restituisce la durata totale dell'audio ascoltabile
   * 
   * @return la durata totale dell'audio ascoltabile
   */
  public int durata();

}
