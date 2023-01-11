import java.util.Iterator;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Class che implementa una playlist, un ascoltabile che contiene dei Brani
 */
public class Playlist implements Ascoltabile, Iterable<Brano> {
  
  private String nome;
  private SortedSet<Brano> brani;

  /**
   * Aggiunge il brano "brano" alla playlist, non fa nulla se il brano è già contenuto
   * 
   * @param brano da aggiungere alla playlist
   * @throws NullPointerException se brano è null
   */
  public void add(Brano brano) {
    if (brano == null) {
      throw new NullPointerException("Il brano da aggiungere alla playlist non può essere null");
    }

    brani.add(brano);
  }

  /**
   * Rimuove il brano "brano" dalla playlist, non fa nulla se il brano non è contenuto
   * 
   * @param brano da rimuovere dalla playlist
   * @throws NullPointerException se brano è null
   */
  public void remove(Brano brano) {
    if (brano == null) {
      throw new NullPointerException("Il brano da rimuovere dalla playlist non può essere null");
    }

    brani.remove(brano);
  }  

  /**
   * Restituisce true se la playlist contiene il brano "brano", false altrimenti
   * 
   * @param brano da cercare nella playlist
   * @throws NullPointerException se brano è null
   * @return true se la playlist contiene il brano "brano", false altrimenti
   */
  public boolean contains(Brano brano) {
    if (brano == null) {
      throw new NullPointerException("Il brano da cercare nella playlist non può essere null");
    }

    return brani.contains(brano);
  }

  /**
   * Restituisce la posizione del brano "brano" se è contenuto nella playlist, -1 altrimenti
   * 
   * @param brano da cercare nella playlist
   * @throws NullPointerException se brano è null
   * @return la posizione del brano "brano" se è contenuto nella playlist, -1 altrimenti
   */
  public int posizione(Brano brano) {
    if (brano == null) {
      throw new NullPointerException("Il brano da cercare nella playlist non può essere null");
    }

    Iterator<Brano> iter = brani.iterator();
    for (int i = 0; iter.hasNext(); i++) {
      if (brano.equals(iter.next())) {
        return i;
      }
    }

    return -1;
  }

  @Override
  public String nome() {
    return nome;
  }

  @Override
  public int durata() {
    int durata = 0;
    for (Brano b : brani) {
      durata += b.durata();
    }
    return durata;
  }

  @Override
  public Iterator<Brano> iterator() {
    return brani.iterator();
  }

  /**
   * Restituisce un iteratore che scorre i brani presenti nella playlist contenuti nell'album "album"
   * 
   * @param album dei brani da cercare
   * @throws NullPointerException se album è null
   * @return un iteratore che scorre i brani presenti nella playlist contenuti nell'album "album"
   */
  public Iterator<Brano> iterator(Album album) {
    if (album == null) {
      throw new IllegalArgumentException("L'album dei brani da cercare non può essere null");
    }

    SortedSet<Brano> braniAlbum = new TreeSet<>();
    Iterator<Brano> iter = brani.iterator();
    while (iter.hasNext()) {
      Brano branoAlbum = iter.next();
      if (album.contains(branoAlbum.nome())) {
        braniAlbum.add(branoAlbum);
      }
    }

    return braniAlbum.iterator();
  }

  /**
   * Restituisce un iteratore che scorre gli album dei brani contenuti nell playlist
   * 
   * @return un iteratore che scorre gli album dei brani contenuti nell playlist
   */
  public Iterator<Album> albumIterator() {

    SortedSet<Album> albums = new TreeSet<>();
    Iterator<Brano> iter = brani.iterator();
    while (iter.hasNext()) {
      albums.add(iter.next().album());
    }

    return albums.iterator();
  }

}
