import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe che implementa un BoolVectDenso, in cui il numero di valori veri è dello stesso ordine di quelli falsi
 */
public class BoolVectDense implements BoolVect, Iterable<Integer> {

  /**
   * Lista che contiene i valori booleani del BoolVect
   * L'indice nella lista corrisponde alla posizione
   */
  private final List<Boolean> values;
  
  /**
   * Crea un nuovo BoolVectDense vuoto
   */
  public BoolVectDense() {
    values = new LinkedList<>();
  }

  /**
   * Crea un nuovo BoolVectDense con i valori passati in vals
   * 
   * @param vals valori con cui inizializzare il BoolVect. Ogni carattere della stringa corrisponde ad un valore booleano, F = false, V = true
   * @throws IllegalArgumentException se vals contiene dei caratteri diversi da 'F' o 'V'
   */
  public BoolVectDense(String vals) {
    values = new LinkedList<>();
    for (int i = 0; i < vals.length(); i++) {
      if (vals.charAt(i) == 'V') {
        this.values.add(true);
      } else if (vals.charAt(i) == 'F') {
        this.values.add(false);
      } else {
        throw new IllegalArgumentException("Valore non riconosciuto: " + vals.charAt(i));
      }
    }
  }

  @Override
  public int dim() {
    for (int i = this.values.size()-1; i > 0; i--) {
      if (values.get(i)) {
        return i+1;
      }
    }
    return 0;
  }

  @Override
  public boolean get(int index) {
    if (index < 0) {
      throw new IllegalArgumentException("L'indice non può essere negativo, fornito: " + index);
    }
    if (index >= this.dim()) {
      return false;
    }
    return values.get(index);
  }

  @Override
  public void add(boolean val) {
    this.values.add(val);
  }

  @Override
  public void add(boolean val, int index) {
    if (index < 0) {
      throw new IllegalArgumentException("L'indice non può essere negativo, fornito: " + index);
    }
    if (index < this.values.size()) {
      this.values.set(index, val);
    } else {
      for (int i = this.values.size(); i < index; i++) {
        this.add(false);
      }
      this.add(val);
    }
  }

  @Override
  public BoolVect and(BoolVect val) {
    BoolVect res = new BoolVectDense();

    // dense
    for (int i = 0; i < Math.min(this.dim(), val.dim()); i++) {
      res.add(this.get(i) && val.get(i));
    }

    return res;
  }

  @Override
  public BoolVect or(BoolVect val) {
    BoolVect res = new BoolVectDense();

    // dense
    for (int i = 0; i < Math.max(this.dim(), val.dim()); i++) {
      res.add(this.get(i) || val.get(i));
    }

    return res;
  }

  @Override
  public BoolVect xor(BoolVect val) {
    BoolVect res = new BoolVectDense();

    // dense
    for (int i = 0; i < Math.max(this.dim(), val.dim()); i++) {
      res.add(this.get(i) ^ val.get(i));
    }

    return res;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();

    Iterator<Integer> iter = this.iterator();
    int index = 0;
    while (iter.hasNext()) {
      int nextTrue = iter.next();
      for (int i = index; i < nextTrue; i++) {
        s.append("F");
      }
      s.append("V");
    }
    return s.toString();
  }

  @Override
  public Iterator<Integer> iterator() {
    Iterator<Integer> iter = new Iterator<>() {

      private int currentIndex = 0;

      @Override
      public boolean hasNext() {
        return currentIndex < dim()-1;
      }

      @Override
      public Integer next() {
        for (int i = currentIndex; i < dim()-1; i++) {
          if (values.get(i)) {
            currentIndex = i+1;
            return i;
          }
        }
        currentIndex = dim()-1;
        return dim()-1;
      }
      
    };
    return iter;
  }
  
}
