/**
 * Classe statica che si occupa della conversione tra secondi e stringhe di durata
 */
public class Durata {
  
  /**
   * Restituisce il numero di secondi data una stringa di durata nei formati HH:MM:SS, MM:SS, SS
   * 
   * @param stringa da convertire a secondi
   * @return il numero di secondi convertito dalla stringa passata
   */
  public static int fromString(String stringa) {
    int secondi = 0;
    
    String[] tokens = stringa.split(":");
    int mult = 1;
    for (int i = tokens.length-1; i > 0; i--) {
      secondi += Integer.parseInt(tokens[i]) * mult;
      mult *= 60;
    }

    return secondi;
  }

  /**
   * Restituisce una stringa nel formato più piccolo tra HH:MM:SS, MM:SS
   * 
   * @param secondi da convertire a stringa
   * @return la stringa risultato della conversione dei secondi passati
   */
  public static String toString(int secondi) {
    int h = 0;
    int m = 0;

    h = secondi / 3600;
    secondi -= h * 3600;

    m = secondi / 60;
    secondi -= m * 60;

    if (h > 0) {
      return (String.format("%02d", h) + ":" + String.format("%02d", m) + ":" + String.format("%02d", secondi));
    }
    return (":" + String.format("%02d", m) + ":" + String.format("%02d", secondi));
  }

}
