import java.util.Iterator;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Classe che implementa un album ascoltabile, una raccolta di brani ascoltabili
 */
public class Album implements Ascoltabile, Iterable<Brano> {

  private final String nome;
  private final BranoAlbum[] brani;

  /**
   * Crea un nuovo album di nome "nome" contentente i brani passati in forma testuale
   * 
   * @param nome dell'album
   * @param brani contenuto nell'album in formato testuale
   * @throws NullPointerException se nome è null
   * @throws NullPointerException se brani è null
   * @throws NullPointerException se un elemento di brani è null
   */
  public Album(String nome, String[] brani) {
    if (nome == null) {
      throw new NullPointerException("Il nome dell'album non può essere null");
    }

    this.nome = nome;

    if (brani == null) {
      throw new NullPointerException("I brani non possono essere null");
    }

    final SortedSet<BranoAlbum> tempBrani = new TreeSet<>();
    for (int i = 0; i < brani.length; i++) {
      if (brani[i] == null) {
        throw new NullPointerException("Nessun brano può essere null");
      }

      String[] tokens = brani[i].split(" - ");
      int durataBrano = 0;
      String nomeBrano = tokens[1];

      tempBrani.add(new BranoAlbum(nomeBrano, durataBrano));
    }

    this.brani = (BranoAlbum[])tempBrani.toArray();
  }

  @Override
  public String nome() {
    return nome;
  }

  @Override
  public int durata() {
    int durata = 0;
    for (BranoAlbum b : brani) {
      durata += b.durata();
    }
    return durata;
  }

  /**
   * Restituisce il brano di titolo "titolo" se contenuto nell'album, null altrimenti
   * 
   * @param titolo del brano da cercare
   * @throws NullPointerException se titolo è null
   * @return il brano di titolo "titolo" se contenuto nell'album, null altrimenti
   */
  public Brano getBrano(String titolo) {
    if (titolo == null) {
      throw new NullPointerException("Il titolo del brano da cercare non può essere null");
    }

    for (int i = 0; i < brani.length; i++) {    
      if (brani[i].nome().equals(titolo)) {
        return new Brano(this, i);
      }
    }
    return null;
  }

  /**
   * Restituisce il brano in posizione "posizione" contenuto nell'album
   * 
   * @param posizione del brano da cercare
   * @throws IndexOutOfBoundsException se la posizione è invalida (< 0, >= length)
   * @return il brano in posizione "posizione" contenuto nell'album
   */
  public Brano getBrano(int posizione) {
    if (posizione < 0 || posizione >= brani.length) {
      throw new IndexOutOfBoundsException("La posizione non può essere invalida (<0, >= length)");
    }

    return new Brano(this, posizione);
  } 

  /**
   * Restituisce la posizione del brano di titolo "titolo" se contenuto nell'album, -1 altrimenti
   * 
   * @param titolo del brano da cercare
   * @throws NullPointerException se titolo è null
   * @return la posizione del brano di titolo "titolo" se contenuto nell'album, -1 altrimenti
   */
  public int getPosizione(String titolo) {
    if (titolo == null) {
      throw new NullPointerException("Il titolo del brano da cercare non può essere null");
    }

    for (int i = 0; i < brani.length; i++) {
      if (brani[i].nome().equals(titolo)) {
        return i;
      }
    }

    return -1;
  }

  /**
   * Restituisce true se l'album contiene il brano di titolo "titolo", false altrimenti
   * 
   * @param brano da cercare nella playlist
   * @throws NullPointerException se titolo è null
   * @return true se la playlist contiene il brano di titolo "titolo", false altrimenti
   */
  public boolean contains(String titolo) {
    if (titolo == null) {
      throw new NullPointerException("Il titolo del brano da cercare non può essere null");
    }

    for (BranoAlbum b : brani) {
      if (b.nome().equals(titolo)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Restituisce il nome del brano in posizione "posizione"
   * 
   * @param posizione del brano di cui restituisce il nome
   * @throws IndexOutOfBoundsException se la posizione è invalida (< 0, >= length)
   * @return il nome del brano in posizione "posizione"
   */
  public String getNomeBrano(int posizione) {
    if (posizione < 0 || posizione >= brani.length) {
      throw new IndexOutOfBoundsException("La posizione non può essere invalida (<0, >= length)");
    }

    return brani[posizione].nome;
  }

  /**
   * Restituisce la durata del brano in posizione "posizione"
   * 
   * @param posizione del brano di cui restituisce la durata
   * @throws IndexOutOfBoundsException se la posizione è invalida (< 0, >= length)
   * @return la durata del brano in posizione "posizione"
   */
  public int getDurataBrano(int posizione) {
    if (posizione < 0 || posizione >= brani.length) {
      throw new IndexOutOfBoundsException("La posizione non può essere invalida (<0, >= length)");
    }

    return brani[posizione].durata;
  }

  @Override
  public Iterator<Brano> iterator() {
    return new Iterator<>() {

      int curIndex = 0;

      @Override
      public boolean hasNext() {
        return curIndex < brani.length;
      }

      @Override
      public Brano next() {
        return new Brano(Album.this, curIndex++);
      }

      @Override
      public void remove() {
        throw new UnsupportedOperationException("Non è possibile rimuovere da un album");
      }
    };
  }

  /**
   * RI:
   *  nome != null
   *  brani != null
   *  ogni brano in brani != null
   *  durata > 0
   *  no brani ripetuti
   */
  private boolean repOk() {
    if (nome == null) return false;
    if (brani == null) return false;
    for (BranoAlbum b : brani) {
      if (b == null) return false;
    }
    if (durata() <= 0) return false;
    if (brani.length != Set.of(brani).size()) return false;

    return true;
  }


  /**
   * Record che implementa un BranoAlbum, un brano senza album (che non esiste fuori da album) con nome e durata
   */
  private record BranoAlbum(String nome, int durata) implements Ascoltabile {}
  
}
