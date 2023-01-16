import java.util.Objects;

/**
 * Classe che implementa una bancarella, composta da un inventario e da un listino
 */
public class Bancarella {

  private Inventario inventario;
  private Listino listino;

  /**
   * Crea una nuova bancarella, creando un inventario vuoto e un listino vuoto
   */
  public Bancarella() {
    this.inventario = new Inventario();
    this.listino = new ListinoMoltiplicativo();  
  }

  /**
   * Aggiunge un nuovo giocattolo (non già contenuto nella bancarella) all'inventario e al listino della bancarella
   * 
   * @param giocattolo da aggiungere
   * @param quantita di giocattoli da aggiungere
   * @param prezzo del giocattolo
   * @throws NullPointerException se giocattolo è null
   * @throws IllegalArgumentException se quantità è minore di 1
   * @throws IllegalArgumentException se prezzo è minore di 0.01
   * @throws IllegalArgumentException se il giocattolo è già contenuto nella bancarella 
   */
  public void nuovoGiocattolo(Giocattolo giocattolo, int quantita, double prezzo) throws IllegalArgumentException {
    Objects.requireNonNull(giocattolo, "Giocattolo non può essere null");
    if (quantita < 1) {
      throw new IllegalArgumentException("La quantità non può essere minore di 1");
    }
    if (prezzo < 0.01) {
      throw new IllegalArgumentException("Il prezzo non può essere minore di 0.01");
    }
    if (inventario.getQuantita(giocattolo) != 0) {
      throw new IllegalArgumentException("Il giocattolo è già contenuto nella bancarella");
    }

    inventario.add(giocattolo, quantita);
    listino.setPrezzo(giocattolo, prezzo);

    assert repOk();
  }

  /**
   * Aggiunge un la quantità "quantita" al giocattolo "giocattolo" (già contenuto nella bancarella) all'inventario della bancarella
   * 
   * @param giocattolo da aggiungere
   * @param quantita di giocattoli da aggiungere
   * @throws NullPointerException se giocattolo è null
   * @throws IllegalArgumentException se quantità è minore di 1
   * @throws IllegalArgumentException se il giocattolo non è già contenuto nella bancarella 
   */
  public void aggiungiGiocattolo(Giocattolo giocattolo, int quantita) {
    Objects.requireNonNull(giocattolo, "Giocattolo non può essere null");
    if (quantita < 1) {
      throw new IllegalArgumentException("La quantità non può essere minore di 1");
    }
    if (inventario.getQuantita(giocattolo) == 0) {
      throw new IllegalArgumentException("Il giocattolo non è già contenuto nella bancarella");
    }

    inventario.add(giocattolo, quantita);

    assert repOk();
  }

  /**
   * Rimuove la quantità "quantita" al giocattolo "giocattolo" (già contenuto nella bancarella) dall'inventario della bancarella
   * 
   * @param giocattolo da rimuovere
   * @param quantita di giocattoli da rimuovere
   * @throws NullPointerException se giocattolo è null
   * @throws IllegalArgumentException se quantità è minore di 1
   * @throws IllegalArgumentException se il giocattolo non è contenuto nella bancarella 
   * @throws IllegalArgumentException se la quantità da rimuovere è maggiore di quella disponibile
   */
  public void rimuoviGiocattolo(Giocattolo giocattolo, int quantita) {
    Objects.requireNonNull(giocattolo, "Giocattolo non può essere null");
    if (quantita < 1) {
      throw new IllegalArgumentException("La quantità non può essere minore di 1");
    }
    if (inventario.getQuantita(giocattolo) == 0) {
      throw new IllegalArgumentException("Il giocattolo non è già contenuto nella bancarella");
    }
    int newQty = inventario.getQuantita(giocattolo) - quantita;
    if (newQty < 0) {
      throw new IllegalArgumentException("La quantità disponibile è minore della quantità da rimuovere");
    }
    
    inventario.remove(giocattolo, quantita);
    if (newQty == 0) {
      listino.removePrezzo(giocattolo);
    }

    assert repOk();
  }

  /**
   * RI:
   *  listino != null
   *  inventario != null
   */
  private boolean repOk() {
    if (listino == null) return false;
    if (inventario == null) return false;

    return true;
  }

} 
