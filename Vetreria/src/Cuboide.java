package Vetreria;

// OVERVIEW: modella un contenitore Sfera
public class Cuboide extends Contenitore {

  private final double a;
  private final double b;
  private final double c;

  // MODIFIES: this
  // EFFECTS: inizializza nuovo contenitore Sfera
  public Cuboide(String liquido, double quantita, double a, double b, double c)
      throws ExceededCapacityException {
    super(liquido, quantita, a * b * c);

    if (a <= 0)
      throw new IllegalArgumentException("a <= 0");
    if (b <= 0)
      throw new IllegalArgumentException("b <= 0");
    if (c <= 0)
      throw new IllegalArgumentException("c <= 0");

    this.a = a;
    this.b = b;
    this.c = c;
  }

  @Override
  public String toString() {
    return "Sfera - lati: " + this.a + " " + this.b + " " + this.c + "\n(" + super.toString() + ")";
  }

}
