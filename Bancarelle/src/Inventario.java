import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/**
 * Classe mutabile che rappresenta un inventario, una raccolta di giocattoli e la relativa quantità
 */
public class Inventario {
  
  private final Map<Giocattolo, Integer> quantita;

  /**
   * Crea un nuovo inventario vuoto
   */
  public Inventario() {
    this.quantita = new HashMap<>();
  }

  /**
   * Aggiunge la quantità "quantita" al giocattolo "giocattolo"
   * 
   * @param giocattolo a cui aggiungere la quantita passata
   * @param quantita di giocattoli da aggiungere
   * @throws NullPointerException se giocattolo è null
   * @throws IllegalArgumentException se quantità è minore di 1
   */
  public void add(Giocattolo giocattolo, int quantita) throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(giocattolo);
    if (quantita < 1) {
      throw new IllegalArgumentException("La quantità da aggiungere non può essere minore di 1");
    }
    
    if (this.quantita.containsKey(giocattolo)) {
      this.quantita.put(giocattolo, this.quantita.get(giocattolo) + quantita);
    } else {
      this.quantita.put(giocattolo, quantita);
    }

    assert repOk();
  }

  /**
   * Rimuove la quantità "quantita" al giocattolo "giocattolo"
   * 
   * @param giocattolo a cui rimuovere la quantita passata
   * @param quantita di giocattoli da rimuovere
   * @throws NullPointerException se giocattolo è null
   * @throws IllegalArgumentException se il giocattolo non è presente nell'inventario
   * @throws IllegalArgumentException se quantità è minore di 1
   * @throws IllegalArgumentException se la quantità da rimuovere è maggiore di quella disponibile
   */
  public void remove(Giocattolo giocattolo, int quantita) throws NullPointerException, IllegalArgumentException {
    if (!this.quantita.containsKey(Objects.requireNonNull(giocattolo))) {
      throw new IllegalArgumentException("Il giocattolo non è presente nell'invetario");
    }
    if (quantita < 1) {
      throw new IllegalArgumentException("La quantità da rimuovere non può essere minore di 1");
    }

    final int newQty = this.quantita.get(giocattolo) - quantita;
    if (newQty < 0) {
      throw new IllegalArgumentException("La quantità da rimuovere è maggiore della quantità disponibile");
    }
    if (newQty == 0) {
      this.quantita.remove(giocattolo);
    } else {
      this.quantita.put(giocattolo, newQty);
    }

    assert repOk();
  }

  /**
   * Restituisce un iteratore per scorrere i giocatolli disponibili in questo inventario
   * 
   * @return un iteratore per scorrere i giocatolli disponibili in questo inventario
   */
  public Iterator<Giocattolo> getGiocattoli() {
    return Collections.unmodifiableCollection(quantita.keySet()).iterator();
  }

  /**
   * Restituisce la quantità di giocattoli disponibili del giocattolo "giocattolo"
   * 
   * @param giocattolo di cui richiedere la quantità
   * @return la quantità di giocattoli disponibili del giocattolo "giocattolo"
   * @throws NullPointerException se giocattolo è null
   */
  public int getQuantita(Giocattolo giocattolo) throws NullPointerException {
    if (!this.quantita.containsKey(Objects.requireNonNull(giocattolo))) {
      return 0;
    }
    return this.quantita.get(giocattolo);
  }

  /**
   * RI:
   *  quantita != null
   *  valori di quantita != null
   *  valori di quantita > 0
   */
  private boolean repOk() {
    if (quantita == null) return false;
    for (Integer q : quantita.values()) {
      if (q == null) return false;
      if (!(q > 0)) return false;
    }

    return true;
  }

}
