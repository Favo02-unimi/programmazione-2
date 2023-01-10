package Vetreria;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Vetreria implements Iterable<Contenitore> {

  private ArrayList<Contenitore> lista;

  // MODIFIES: this
  // EFFECTS: inizializza una vetreria vuota
  Vetreria() {
    this.lista = new ArrayList<>();
  }

  // MODIFIES: this
  // EFFECTS: aggiunge c a this
  public void add(Contenitore contenitore) {
    if (contenitore == null)
      throw new NullPointerException("contenitore è null");
    lista.add(contenitore);
  }

  @Override
  public Iterator<Contenitore> iterator() {

    // non restituiamo direttamente l'iteratore di ArrayList dato implementa anche
    // altri metodi (come il remove), che non vogliamo che sia possibile utilizzare
    // su Vetreria, quindi ne costruiamo per delega, utilizzando solo le
    // funzionalità necessarie
    return new Iterator<Contenitore>() {

      Iterator<Contenitore> iteratore = lista.iterator();

      @Override
      public boolean hasNext() {
        return iteratore.hasNext();
      }

      @Override
      public Contenitore next() {
        return iteratore.next();
      }

    };
  }

  // MODIFIES: this
  // EFFECTS: riordina this dal contenitore più grande al più piccolo
  public void sort() {
    Collections.sort(this.lista);
    Collections.reverse(this.lista);

    // si potrebbe anche utilizzare il sort di arraylist e non quello di collection

    // this.lista.sort(null);
    // Collections.reverse(this.lista);
  }

  // MODIFIES: this
  // EFFECTS: rimuove contenitori di tipo "liquido" da this e li agginge in una
  // nuova vetreria e restituisce la nuova vetreria
  public Vetreria estrai(String liquido) {
    Vetreria newVetreria = new Vetreria();

    Iterator<Contenitore> iteratore = lista.iterator();

    while (iteratore.hasNext()) {
      Contenitore contenitore = iteratore.next();
      if (contenitore.getLiquido().equals(liquido)) {
        newVetreria.add(contenitore);
        iteratore.remove();
      }
    }

    // sarebbe anche possibile fare un "normale" foreach e poi, per rimuovere dalla
    // vetreria corrente, iterare sui contenitor della nuova vetreria (ma fuori dal
    // foreach, non si può rimuovere durante un iterazione senza metodo remove())

    // for (Contenitore contenitore : lista)
    // if (contenitore.getLiquido().equals(liquido))
    // newVetreria.add(contenitore);

    // this.lista.removeAll(newVetreria.lista);

    return newVetreria;
  }

  // MODIFIES: this
  // EFFECTS: riordina la vetreria e ottimizza riempiendo dai contenitori più
  // piccoli in quelli più grandi
  public void ottimizza() {
    this.sort();

    Contenitore ultimo = null;

    for (Contenitore contenitore : this.lista) {
      if (ultimo != null)
        contenitore.versa(ultimo);
      ultimo = contenitore;
    }
  }

  @Override
  public String toString() {
    String res = "Vetreria: ";
    for (Contenitore contenitore : this.lista)
      res += contenitore + "\n";

    return res;
  }

  public boolean repOk() {
    for (Contenitore contenitore : this.lista)
      if (contenitore == null || !(contenitore.repOk()))
        return false;
    return false;
  }

}
