import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Classe che implementa un MultiSet basato su una mappa
 */
public class MapMultiSet<E> implements MultiSet<E> {

  private Map<E, Integer> elems;

  /**
   * Crea un nuovo MapMultiSet
   */
  public MapMultiSet() {
    elems = new HashMap<>();
  }

  @Override
  public int add(E e) {
    if (e == null) {
      throw new NullPointerException("L'elemento da aggiungere non può essere null");
    }

    if (!elems.containsKey(e)) {
      elems.put(e, 1);
    } else {
      elems.put(e, elems.get(e)+1);
    }
    return multiplicity(e);
  }

  @Override
  public int remove(Object o) {
    if (o == null) {
      throw new NullPointerException("L'elemento da rimuovere non può essere null");
    }

    if (!elems.containsKey(o)) {
      return 0;
    } else {
      int mult = multiplicity(o);

      if (mult == 1) {
        elems.remove(o);
      } else {
        elems.put((E)o, mult-1);
      }

      return mult;
    }
  }

  @Override
  public int multiplicity(Object o) {
    if (o == null) {
      throw new NullPointerException("L'elemento da cercare non può essere null");
    }

    if (!elems.containsKey(o)) {
      return 0;
    }
    return elems.get(o);
  }

  @Override
  public int size() {
    int sum = 0;
    for (Integer v : elems.values()) {
      sum += v;
    }
    return sum;
  }

  @Override
  public MultiSet<E> union(MultiSet<? extends E> o) {
    if (o == null) {
      throw new NullPointerException("Il multiset da unire non può essere null");
    }

    MultiSet<E> union = new MapMultiSet<>();

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
    return Collections.unmodifiableCollection(elems.keySet()).iterator();
  }

  @Override
  public String toString() {
    return internalToString();
  }

}
