import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Classe che implementa un MultiSet basato su una Lista
 */
public class ListMultiSet<E> implements MultiSet<E> {

  private List<E> elems;

  /**
   * Crea un nuovo ListMultiSet
   */
  public ListMultiSet() {
    elems = new ArrayList<>();
  }

  @Override
  public int add(E e) {
    if (e == null) {
      throw new NullPointerException("L'elemento da aggiungere non può essere null");
    }

    elems.add(e);
    return multiplicity(e);
  }

  @Override
  public int remove(Object o) {
    if (o == null) {
      throw new NullPointerException("L'elemento da rimuovere non può essere null");
    }

    int mult = multiplicity(o);
    elems.remove(o);
    return mult;
  }

  @Override
  public int multiplicity(Object o) {
    if (o == null) {
      throw new NullPointerException("L'elemento da cercare non può essere null");
    }

    int count = 0;
    for (E e : elems) {
      if (e.equals(o)) {
        count++;
      }
    }
    return count;
  }

  @Override
  public int size() {
    return elems.size();
  }

  @Override
  public MultiSet<E> union(MultiSet<? extends E> o) {
    if (o == null) {
      throw new NullPointerException("Il multiset da unire non può essere null");
    }

    MultiSet<E> union = new ListMultiSet<>();

    Iterator<? extends E> iter = this.iterator();
    while(iter.hasNext()) {
      E el = iter.next();
      int qty = this.multiplicity(el);

      if (o.multiplicity(el) > qty) {
        qty = o.multiplicity(el);
      }

      union.addQty(el, qty);
    }

    iter = o.iterator();
    while(iter.hasNext()) {
      E el = iter.next();
      int qty = o.multiplicity(el);

      if (!union.contains(el)) {
        if (this.multiplicity(el) > qty) {
          qty = this.multiplicity(el);
        }
        
        union.addQty(el, qty);
      }
    }

    return union;
  }

  @Override
  public MultiSet<E> intersection(MultiSet<? extends E> o) {
    if (o == null) {
      throw new NullPointerException("Il multiset da intersecare non può essere null");
    }
    
    MultiSet<E> intersection = new MapMultiSet<>();

    Iterator<E> thisIter = this.iterator();
    while(thisIter.hasNext()) {
      E el = thisIter.next();
      int qty = this.multiplicity(el);

      if (o.contains(el)) {
        if (o.multiplicity(el) < qty) {
          qty = o.multiplicity(el);
        }

        intersection.addQty(el, qty);
      }
    }    

    return intersection;
  }
  
  @Override
  public Iterator<E> iterator() {
    Set<E> supporto = new HashSet<>(elems);
    return Collections.unmodifiableCollection(supporto).iterator();
  }

  @Override
  public String toString() {
    return internalToString();
  }

}
