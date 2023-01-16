import java.util.Iterator;
import java.util.Objects;

/**
 * Classe che implementa una shell che opera sul filesystem, attraverso una cwd (current working directory) e capace di effettuare operazioni sul filesystem
 */
public class Shell {

  private Filesystem filesystem;
  private Path cwd;

  /**
   * Crea una nuova shell, dato il filesystem su cui operare. Imposta la cwd di partenza alla root
   * 
   * @throws NullPointerException se filesystem è null
   */
  public Shell(Filesystem filesystem) throws NullPointerException {
    Objects.requireNonNull(filesystem, "Il filesystem su cui operare non può essere null");
    this.cwd = new Path(":"); // inizializza la cwd alla root
    this.filesystem = filesystem;
  }

  /**
   * Restisuice la rappresentazione testuale del contenuto della cwd
   * 
   * @return la rappresentazione testuale del contenuto della cwd
   */
  public String ls() {
    StringBuilder str = new StringBuilder();
    Iterator<Filesystem.Entry> iter = filesystem.getEntry(cwd).contenuto();
    while(iter.hasNext()) {
      str.append(iter.next().toString());
      str.append("\n");
    }
    return str.toString();
  }

  /**
   * Restisuice la rappresentazione testuale del contenuto della path (rappresentata come stringa) passata (assoluta o relativa)
   * 
   * @return la rappresentazione testuale del contenuto della path passata
   * @throws NullPointerException se path è null
   */
  public String ls(String path) {
    Objects.requireNonNull(path, "Il path non può essere null");

    Path p = new Path(path);
    if (!p.isAssoluta()) {
      p = cwd.append(path);
    }

    StringBuilder str = new StringBuilder();
    Iterator<Filesystem.Entry> iter = filesystem.getEntry(p).contenuto();
    while(iter.hasNext()) {
      str.append(iter.next().toString());
      str.append("\n");
    }
    return str.toString();
  }

  /**
   * Restituisce la rappresentazione testuale della grandezza della cwd
   * 
   * @return la rappresentazione testuale della grandezza della cwd
   */
  public String size() {
    return "" + filesystem.getEntry(cwd).dim();
  }

  /**
   * Restituisce la rappresentazione testuale della grandezza della path (rappresentata come stringa) passata (assoluta o relativa)
   * 
   * @return la rappresentazione testuale della grandezza della path passata
   * @throws NullPointerException se path è null
   */
  public String size(String path) {
    Objects.requireNonNull(path, "Il path non può essere null");

    Path p = new Path(path);
    if (!p.isAssoluta()) {
      p = cwd.append(path);
    }

    return "" + filesystem.getEntry(p).dim();
  }

  /**
   * Crea una nuova directory in posizione "path", dato il suo path (assoluto o relativo) 
   * 
   * @param path in cui creare la nuova directory
   */
  public void mkdir(String path) {
    Objects.requireNonNull(path, "Il path non può essere null");

    Path p = new Path(path);
    if (!p.isAssoluta()) {
      p = cwd.append(path);
    }

    filesystem.createDir(p);
  }

  /**
   * Crea un nuovo file in posizione "path", dato il suo path (asssoluto o relativo) e la sua dimensione
   * 
   * @param path in cui creare il nuovo file
   * @param size dimensione del file da creare
   */
  public void mkfile(String path, int size) {
    Objects.requireNonNull(path, "Il path non può essere null");

    if (size < 0) {
      throw new IllegalArgumentException("La dimensione del file non può essere minore di 0");
    }

    Path p = new Path(path);
    if (!p.isAssoluta()) {
      p = cwd.append(path);
    }

    filesystem.createFile(p, size);
  }

  /**
   * Cambia la cwd (current working directory), impostandola alla root
   */
  public void cd() {
    this.cwd = new Path(":");
  }

  /**
   * Cambia la cwd (current working directory), impostandola alla path (in formato di stringa) passata (assoluta o relativa)
   */
  public void cd(String path) {
    Path p = new Path(path);
    if (!p.isAssoluta()) {
      p = cwd.append(path);
    }

    this.cwd = p;
  }

  /**
   * Restitiusce la rappresentazione testuale della cwd (current working directory)
   * 
   * @return la rappresentazione testuale della cwd (current working directory)
   */
  public String cwd() {
    return cwd.toString();
  }

}
