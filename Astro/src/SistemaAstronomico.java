import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Classe che rappresenta una collezione di corpi celesti: un sistema astronomico
 */
public class SistemaAstronomico {
  private Set<CorpoCeleste> corpiCelesti;
  
  /**
   * Crea un nuovo sistema astronomico
   */
  public SistemaAstronomico() {
    corpiCelesti = new TreeSet<>(new Comparator<>() {

      @Override
      public int compare(CorpoCeleste p, CorpoCeleste q) {
        return p.nome.compareTo(q.nome);
      }

    });
  }

  /**
   * Aggiunge un corpo celeste al sistema astronomico corrente
   * 
   * @param c corpo celeste da aggiungere
   * @throws NullPointerException se c è null
   */
  public void aggiungi(CorpoCeleste c) {
    if (c == null) {
      throw new NullPointerException("Il corpo celeste da aggiungere non può essere null");
    }

    corpiCelesti.add(c);
  }

  /**
   * Simula dei passi di interazione tra i corpi celesti presenti nel sistema astronomico
   * 
   * @param passi numero di passi da effettuare
   * @throws IllegalArgumentException se passi è negativo
   */
  public void simula(int passi) {
    if (passi < 0) {
      throw new IllegalArgumentException("Passi non può essere negativo");
    }

    for (int i = 0; i < passi; i++) {
      for (CorpoCeleste p : corpiCelesti) {
        for (CorpoCeleste q : corpiCelesti) {
          if (q == p) continue;

          p.modificaVelocita(q);
        }
      }
      for (CorpoCeleste p : corpiCelesti) {
        p.modificaPosizione();
      }
    }
  }

  /**
   * Restituisce l'energia totale del sistema astronomico
   * 
   * @return l'energia totale del sistema astronomico
   */
  public int energia() {
    int energia = 0;
    for (CorpoCeleste c : corpiCelesti) {
      energia += c.energia();
    }
    return energia;
  }

  @Override
  public String toString() {
    StringBuilder res = new StringBuilder();
    for (CorpoCeleste c : corpiCelesti) {
      res.append(c.toString());
      res.append("\n");
    }
    return res.toString();
  }
}
