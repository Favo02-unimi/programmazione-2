import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe che implementa un BoolVectSparse, in cui la posizione dei valori veri è dell'ordine di Int.MAX_VALUE
 */
public class BoolVectSparse implements BoolVect, Iterable<Integer> {

  /**
   * Lista che contiene le posizioni (indici) dei valori veri
   */
  public final List<Integer> indexes;
  
  /**
   * Crea un nuovo BoolVectSparse vuoto
   */
  public BoolVectSparse() {
    indexes = new LinkedList<>();
  }

  @Override
  public int dim() {
    if (this.indexes.size() == 0) {
      return 0;
    }
    return this.indexes.get(this.indexes.size()-1)+1;
  }

  @Override
  public boolean get(int index) {
    if (index < 0) {
      throw new IllegalArgumentException("L'indice non può essere negativo, fornito: " + index);
    }
    if (indexes.contains(index)) {
      return true;
    }
    return false;
  }

  @Override
  public void add(boolean val) {
    if (val) {
      this.indexes.add(this.dim());
    }
  }

  @Override
  public void add(boolean val, int index) {
    if (index < 0) {
      throw new IllegalArgumentException("L'indice non può essere negativo, fornito: " + index);
    }
    if (val) {
      this.indexes.add(index);
      Collections.sort(this.indexes);
    }
  }

  @Override
  public BoolVect and(BoolVect val) {
    BoolVect res = new BoolVectSparse();

    // sparse
    Iterator<Integer> iter = this.iterator();
    while(iter.hasNext()) {
      int index = iter.next();
      if (val.get(index)) {
        res.add(true, index);
      }
    }

    return res;
  }

  @Override
  public BoolVect or(BoolVect val) {
    BoolVect res = new BoolVectSparse();

    // sparse
    Iterator<Integer> iter = this.iterator();
    while(iter.hasNext()) {
      res.add(true, iter.next());
    }
    iter = val.iterator();
    while(iter.hasNext()) {
      int index = iter.next();
      if (!res.get(index))
      res.add(true, index);
    }

    return res;
  }

  @Override
  public BoolVect xor(BoolVect val) {
    BoolVect res = new BoolVectSparse();

    // sparse
    Iterator<Integer> iter = this.iterator();
    while(iter.hasNext()) {
      int index = iter.next();
      if (!val.get(index)) {
        res.add(true, index);
      }
    }
    iter = val.iterator();
    while(iter.hasNext()) {
      int index = iter.next();
      if (!this.get(index)) {
        res.add(true, index);
      }
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
      index = nextTrue +1;
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
        return currentIndex < indexes.size();
      }

      @Override
      public Integer next() {
        return indexes.get(currentIndex++);       
      }

    };
    return iter;
  }
  
}
