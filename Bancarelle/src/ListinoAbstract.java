import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class ListinoAbstract implements Listino {
  
  private Map<Giocattolo, Double> prezzi;

  public ListinoAbstract() {
    this.prezzi = new HashMap<>();
  }

  @Override
  public boolean contains(Giocattolo giocattolo) throws NullPointerException {
    Objects.requireNonNull(giocattolo, "Il giocattolo non può essere null");

    return prezzi.containsKey(giocattolo);
  }

  @Override
  public double getPrezzoUnitario(Giocattolo giocattolo) throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(giocattolo, "Il giocattolo non può essere null");
    if (!(this.contains(giocattolo))) {
      throw new IllegalArgumentException("Questo listino non contiene questo giocattolo");
    }

    return prezzi.get(giocattolo);
  }

  @Override
  public void setPrezzo(Giocattolo giocattolo, double prezzo) throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(giocattolo, "Il giocattolo non può essere null");
    if (!(prezzo > 0.01)) {
      throw new IllegalArgumentException("Il prezzo non può essere minore di 0.01");
    }
    this.prezzi.put(giocattolo, prezzo);
  }

  @Override
  public void removePrezzo(Giocattolo giocattolo) {
    Objects.requireNonNull(giocattolo, "Il giocattolo non può essere null");
    if (!(this.contains(giocattolo))) {
      throw new IllegalArgumentException("Questo listino non contiene questo giocattolo");
    }
    
    prezzi.remove(giocattolo);
  }

}
