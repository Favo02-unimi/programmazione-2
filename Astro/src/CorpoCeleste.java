/**
 * Classe astratta che rappresenta un corpo celeste
 */
public abstract class CorpoCeleste {
  
  public String nome;

  /**
   * Crea un nuovo corpo celeste dato il suo nome
   * 
   * @param nome del corpo celeste
   * @throws NullPointerException se nome è null
   */
  public CorpoCeleste(String nome) {
    if (nome == null) {
      throw new NullPointerException("Il nome non può essere null");
    }
    this.nome = nome;
  }

  /**
   * Restituisce la posizione del corpo celeste
   * 
   * @return la posizione del corpo celeste
   */
  public abstract Punto posizione();

  /**
   * Restituisce la velocità del corpo celeste
   * 
   * @return la velocità del corpo celeste
   */
  public abstract Punto velocita();

  /**
   * Restituisce l'energia del corpo celeste
   * 
   * @return l'energia del corpo celeste
   */
  public int energia() {
    return posizione().norma() * velocita().norma();
  }

  /**
   * Modifica la velocità del corpo celeste corrente in alla vicinanza ad un corpo celeste
   * 
   * @param c corpo celeste che influenza la velocità del corpo celeste corrente. c non viene modificato
   * @throws NullPointerException se c è null
   */
  public abstract void modificaVelocita(final CorpoCeleste c);

  /**
   * Modifica la posizione del corpo celeste corrente in base alla velocità corrente
   */
  public abstract void modificaPosizione();
}
