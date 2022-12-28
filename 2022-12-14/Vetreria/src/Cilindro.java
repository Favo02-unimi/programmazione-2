package Vetreria;

// OVERVIEW: modella un contenitore Sfera
public class Cilindro extends Contenitore {

  private final double raggio;
  private final double altezza;

  // MODIFIES: this
  // EFFECTS: inizializza nuovo contenitore Sfera
  public Cilindro(String liquido, double quantita, double raggio, double altezza)
      throws ExceededCapacityException {
    super(liquido, quantita, Math.PI * raggio * raggio * altezza);

    if (raggio <= 0)
      throw new IllegalArgumentException("raggio <= 0");
    if (altezza <= 0)
      throw new IllegalArgumentException("altezza <= 0");

    this.raggio = raggio;
    this.altezza = altezza;
  }

  @Override
  public String toString() {
    return "Sfera - raggio: " + this.raggio + " altezza: " + this.altezza + "\n(" + super.toString() + ")";
  }

}
