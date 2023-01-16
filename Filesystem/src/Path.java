import java.util.Objects;

/**
 * Classe immutabile che implementa un path, attraverso una stringa sempre nel formato valido
 */
public class Path {

  private String path;

  /**
   * Crea una nuova path che punta alla root
   */
  public Path() {
    this.path = ":";
  }

  /**
   * Crea una nuova path data la path in formato di stringa
   * 
   * @param path in formato di stringa da creare
   * @throws NullPointerException se path è null
   */
  public Path(String path) throws NullPointerException {
    Objects.requireNonNull(path, "La path non può essere null");
    if (path.isEmpty()) {
      throw new IllegalArgumentException("La path non può essere vuota");
    }

    if (path.contains("::")) {
      throw new IllegalArgumentException("Path non valida");
    }

    this.path = path;
  }

  /**
   * Restituisce il nome dell'entry a cui punta la path
   * 
   * @return il nome dell'entry a cui punta la path
   */
  public String getNome() {
    if (path.lastIndexOf(":") != -1) {
      return path.substring(path.lastIndexOf(":"));
    }
    return path;
  }

  /**
   * Restituisce true se la path è assoluta, false altrimenti
   * 
   * @return true se la path è assoluta, false altrimenti
   */
  public boolean isAssoluta() {
    return path.charAt(0) == ':';
  }

  /**
   * Restituisce una nuova path ottenuta aggiungendo alla path corrente la path "p" passata
   * 
   * @param p path da aggiungere alla path corrente
   * @return una nuova path ottenuta aggiungendo alla path corrente la path "p" passata
   */
  public Path append(String p) {
    return new Path(path + ":" + p);
  }

  @Override
  public boolean equals(Object o) {
    if (o instanceof Path) {
      final Path p = (Path) o;
      return p.path.equals(this.path);
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hash(path);
  }

  @Override
  public String toString() {
    return path;
  }

}
