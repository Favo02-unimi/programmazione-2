package Vetreria;

// OVERVIEW: modella un COntenitore di liquido con capienza e quantità inserita
public abstract class Contenitore implements Comparable<Contenitore> {

  private String liquido;
  private double quantita;
  private final double volume;

  // MODIFIES: this
  // EFFECTS: inizializza this
  Contenitore(String liquido, double quantita, double volume) throws ExceededCapacityException {
    if (liquido == null)
      throw new NullPointerException("liquido è null");
    if (quantita < 0)
      throw new IllegalArgumentException("qta < 0");
    if (volume <= 0)
      throw new IllegalArgumentException("vol <= 0");
    if (quantita > volume)
      throw new ExceededCapacityException("qta > vol");

    this.liquido = liquido;
    this.quantita = quantita;
    this.volume = volume;

    assert repOk();
  }

  public String getLiquido() {
    return liquido;
  }

  // MODIFIES: this, c
  // EFFECTS: versa liquido da this a c fino alla massima capienza di c solo se i
  // liquidi sono compatibili (stesso liquido o no liquido in c)
  public void versa(Contenitore c) throws IncompatibleLiquidsException {
    if (c == null)
      throw new NullPointerException("c è null");
    if (!(c.liquido.equals(this.liquido)) && !(c.liquido.equals("")))
      throw new IncompatibleLiquidsException("liquidi incompatibili");
    if (this.quantita == 0 || c.quantita == c.volume)
      return;

    c.liquido = this.liquido;

    if (this.quantita + c.quantita <= c.volume) {
      c.quantita += this.quantita;
      this.quantita = 0;
      this.liquido = "";
    } else {
      this.quantita -= (c.volume - c.quantita);
      c.quantita = c.volume;
    }

    assert repOk();
    assert c.repOk();
  }

  @Override
  public int compareTo(Contenitore o) {
    if (this.volume > o.volume)
      return 1;
    if (this.volume < o.volume)
      return -1;
    return 0;
  }

  @Override
  public String toString() {
    return "Capienza: " + this.volume + " Liquido: " + this.liquido + " Qta: " + this.quantita;
  }

  public boolean repOk() {
    if (liquido == null)
      return false;
    if (quantita < 0)
      return false;
    if (volume <= 0)
      return false;
    if (quantita > volume)
      return false;
    return true;
  }
}
