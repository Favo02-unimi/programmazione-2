/**
 * Classe che implementa un corpo celeste che si muove: un pianeta
 */
public class Pianeta extends CorpoCeleste {

  private Punto posizione;
  private Punto velocita;

  /**
   * Crea un nuovo piante
   * 
   * @param nome del pianeta
   * @param posizione del pianeta
   * @throws NullPointerException se nome è null
   */
  public Pianeta(String nome, int x, int y, int z) {
    super(nome);

    this.posizione = new Punto(x, y, z);
    this.velocita = new Punto(0, 0, 0);
  }

  @Override
  public Punto posizione() {
    return posizione;
  }

  @Override
  public Punto velocita() {
    return velocita;
  }

  @Override
  public void modificaVelocita(CorpoCeleste c) {
    if (c == null) {
      throw new NullPointerException("Il corpo celeste che influenza la velocità non può essere null");
    }

    Punto delta = posizione.sottrai(c.posizione());
    velocita = velocita.sottrai(new Punto(delta.x() > 0 ? +1 : -1, delta.y() > 0 ? +1 : -1, delta.z() > 0 ? +1 : -1));

    assert repOk();
  }

  @Override
  public void modificaPosizione() {
    posizione = posizione.somma(velocita);

    assert repOk();
  }

  @Override
  public String toString() {
    return "Pianeta, " + nome + ": pos." + posizione + " vel. " + velocita;
  }
  
  private boolean repOk() {
    if (posizione == null) {
      return false;
    }
    if (velocita == null) {
      return false;
    }
    return true;
  }
  
}
