package Vetreria;

// OVERVIEW: modella un contenitore Sfera
public class Sfera extends Contenitore {

  private final double raggio;

  // MODIFIES: this
  // EFFECTS: inizializza nuovo contenitore Sfera
  public Sfera(String liquido, double quantita, double raggio) throws ExceededCapacityException {
    super(liquido, quantita, Math.PI * raggio * raggio * raggio * 4 / 3);

    if (raggio <= 0)
      throw new IllegalArgumentException("r <= 0");

    this.raggio = raggio;
  }

  @Override
  public String toString() {
    return "Sfera - raggio: " + this.raggio + "\n(" + super.toString() + ")";
  }

}
