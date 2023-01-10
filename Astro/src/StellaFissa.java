/**
 * Classe che implementa un corpo celeste che non si può muovere: una stella fissa
 */
public class StellaFissa extends CorpoCeleste {

  private final Punto posizione;
  private final Punto velocita;

  /**
   * Crea una nuova stella fissa
   * 
   * @param nome della stella fissa
   * @param posizione della stella fissa
   * @throws NullPointerException se nome è null
   */
  public StellaFissa(String nome, int x, int y, int z) {
    super(nome);

    this.posizione = new Punto(x, y, z);
    this.velocita = new Punto(0,0,0);
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
    assert repOk();
  }

  @Override
  public String toString() {
    return "Stella fissa, " + nome + ": " + posizione;
  }

  @Override
  public void modificaPosizione() {
    assert repOk();
  }
  
  private boolean repOk() {
    if (posizione == null) {
      return false;
    }
    if (velocita == null) {
      return false;
    }
    if (!velocita.equals(new Punto(0,0,0))) {
      return false;
    }
    return true;
  }

}
