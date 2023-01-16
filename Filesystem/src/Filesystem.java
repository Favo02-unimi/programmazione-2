import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * Classe filesystem che implementa una memorizzazione di file ed entry, fornendo i servizi base necessari al funzionamento della shell
 */
public class Filesystem {

  private Map<Path, Entry> memory;

  /**
   * Crea un nuovo filesystem vuoto
   */
  public Filesystem() {
    this.memory = new HashMap<>();
    memory.put(new Path(), new Directory(":"));
  }

  /**
   * Restituisce una entry dato il suo path assoluto
   *  
   * @param path assoluto in cui reperire la entry
   * @return una entry dato il suo path assoluto
   * @throws NullPointerException se path è null
   * @throws IllegalArgumentException se la path non è presente nel filesystem
   */
  public Entry getEntry(Path path) throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(path, "La path non può essere null");
    if (!memory.containsKey(path)) {
      throw new IllegalArgumentException("Il filesystem non contiene questa path");
    }
    return memory.get(path);
  }

  /**
   * Crea e aggiunge al filesystem un nuovo file, dato il suo path assoluto e la sua dimensione
   * 
   * @param path assoluto in cui posizionare il file
   * @param dim dimensione del file da creare
   * @throws NullPointerException se path è null
   * @throws IllegalArgumentException se dim è < 0
   */
  public void createFile(Path path, int dim) throws NullPointerException, IllegalArgumentException {
    Objects.requireNonNull(path, "Il path non può essere null");
    if (dim < 0) {
      throw new IllegalArgumentException("La dimensione non può essere minore di 0");
    }
    
    // il nome del file è contenuto nel path
    memory.put(path, new File(path.getNome(), dim));
    
    assert repOk();
  }

  /**
   * Crea e aggiunge al filesystem una nuova directory vuota, dato il suo path assoluto
   * 
   * @param path assoluto in cui posizionare la directory
   * @throws NullPointerException se path è null
   */
  public void createDir(Path path) throws NullPointerException {
    Objects.requireNonNull(path, "Il path non può essere null");

    // il nome della directory è contenuto nel path
    memory.put(path, new Directory(path.getNome()));

    assert repOk();
  }

  /**
   * RI:
   *  memory != null
   *  ogni chiave di memory != null
   *  ogni valore di memory != null
   */
  private boolean repOk() {
    if (memory == null) return false;
    for (Path p : memory.keySet()) {
      if (p == null) return false;
    }
    for (Entry e : memory.values()) {
      if (e == null) return false;
    }

    return true;
  }

  /**
   * Classe astratta che rappresenta una entry, un elemento del filesystem, che ha un nome
   */
  public abstract class Entry {

    private final String nome;

    /**
     * Crea una nuova entry dato il suo nome
     * 
     * @param nome della entry
     * @throws NullPointerException se nome è null
     * @throws IllegalArgumentException se nome è vuoto
     */
    private Entry(String nome) throws NullPointerException, IllegalArgumentException {
      // il costruttore è privato dato che solo il filesystem può creare delle entry
      Objects.requireNonNull(nome, "Il nome non può essere null");
      if (nome.isEmpty()) {
        throw new IllegalArgumentException("Il nome non può essere vuoto");
      }
      this.nome = nome;
    }

    /**
     * Restituisce il nome della entry
     * 
     * @return il nome della entry
     */
    public String nome() {
      return this.nome;
    }

    /**
     * Restituisce la dimensione della entry
     * 
     * @return la dimensione della entry
     */
    public abstract int dim();

    /**
     * Restisuice un iteratore per scorrere il contenuto della entry
     * 
     * @return un iteratore per scorrere il contenuto della entry
     */
    public abstract Iterator<Entry> contenuto();
    // è possibile scorrere ogni tipo di entry (anche i file, che restituiranno solo sè stessi) (come su linux)

    @Override
    public boolean equals(Object o) {
      // due entry sono uguali solo se il loro nome è uguale
      // nella stessa cartella non possono coesistere due entry (anche un file ed una directory) con lo stesso nome
      if (o instanceof Entry) {
        final Entry e = (Entry) o;
        return e.nome().equals(this.nome());
      }
      return false;
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(nome());
    }

    /**
     * RI:
     *  nome != null
     *  nome non vuoto
     *  dimensione non negativa
     */
    private boolean repOk() {
      if (nome == null) return false;
      if (nome.isEmpty()) return false;
      if (dim() < 0) return false;

      return true;
    }

  }

  /**
   * Classe che estende Entry, che implementa un file, un elemento del filesystem con una dimensione
   */
  public class File extends Entry {

    private final int dim;

    /**
     * Crea un nuovo file dato il suo nome e la dimensione
     * 
     * @param nome del file
     * @param dim dimensione del file
     * @throws NullPointerException se nome è null
     * @throws IllegalArgumentException se nome è vuoto
     * @throws IllegalArgumentException se dim è < 0
     */
    private File(String nome, int dim) throws NullPointerException, IllegalArgumentException {
      // il costruttore è privato dato che solo il filesystem può creare dei file
      super(nome);
      if (dim < 0) { // il file può avere dimensione 0, un file vuoto
        throw new IllegalArgumentException("la dimensione del file non può essere < 0");
      }
      this.dim = dim;
    }

    @Override
    public int dim() {
      return dim;
    }

    @Override
    public Iterator<Entry> contenuto() {
      final List<Entry> list = new ArrayList<>();
      list.add(this);
      return Collections.unmodifiableCollection(list).iterator();
    }

    /**
     * RI:
     *  nome != null
     *  nome non vuoto
     *  dimensione non negativa
     */
    private boolean repOk() {
      if (nome() == null) return false;
      if (nome().isEmpty()) return false;
      if (dim < 0) return false;

      return true;
    }

  }

  /**
   * Classe che estende Entry, che implementa una directory, un elemento del filesystem che può contenere altre entry
   */
  public class Directory extends Entry {

    private final Set<Entry> contenuto;

    /**
     * Crea una nuova directory vuota dato il suo nome
     * 
     * @throws NullPointerException se nome è null
     * @throws IllegalArgumentException se nome è vuoto
     */
    private Directory(String nome) throws NullPointerException, IllegalArgumentException {
      // il costruttore è privato dato che solo il filesystem può creare delle directory
      super(nome);
      this.contenuto = new HashSet<>();
    }

    @Override
    public int dim() {
      int sum = 0;
      for (Entry e : contenuto) {
        sum += e.dim();
      }
      return sum;
    }

    /**
     * Aggiunge una entry al contenuto di questa directory
     * 
     * @param entry da aggiungere al contenuto di questa directory
     * @throws NullPointerException se entry è null
     * @throws IllegalArgumentException se entry è già contenuta nella directory
     */
    private void add(Entry entry) throws NullPointerException, IllegalArgumentException {
      Objects.requireNonNull(entry, "La entry da aggiungere non può essere null");
      if (contenuto.contains(entry)) {
        throw new IllegalArgumentException("La entry è già presente dentro questa directory");
      }
      contenuto.add(entry);
    }

    @Override
    public Iterator<Entry> contenuto() {
      return Collections.unmodifiableCollection(contenuto).iterator();
    }

    /**
     * RI:
     *  nome != null
     *  nome non vuoto
     *  contenuto != null
     *  ogni elemento di contenuto != null
     *  dimensione non negativa
     */
    private boolean repOk() {
      if (nome() == null) return false;
      if (nome().isEmpty()) return false;
      if (contenuto == null) return false;
      for (Entry entry : contenuto) {
        if (entry == null) return false;
      }
      if (dim() < 0) return false;

      return true;
    }

  }
}
