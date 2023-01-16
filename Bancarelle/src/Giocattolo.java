import java.util.Objects;

/**
 * Record che rappresenta un giocattolo dato il suo nome e materiale
 */
public record Giocattolo(String nome, String materiale) {
  
  /**
   * Crea un nuovo giocattolo dato nome e materiale
   * 
   * @param nome del giocattolo
   * @param materiale del giocattolo
   * @throws NullPointerException se nome è null
   * @throws NullPointerException se materiale è null
   */
  public Giocattolo(String nome, String materiale) {
    this.nome = Objects.requireNonNull(nome);
    this.materiale = Objects.requireNonNull(materiale);
  }

  /**
   * RI:
   *  nome != null
   *  materiale != null
   */
  private boolean repOk() {
    if (nome == null) return false;
    if (materiale == null) return false;

    return true;
  }

}
