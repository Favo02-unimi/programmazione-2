public class VettoreDenso implements Vettore {

  private int[] vettore;

  public VettoreDenso(int[] vettore) {
    this.vettore = vettore;
  }

  @Override
  public int dim() {
    return vettore.length;
  }

  @Override
  public int val(int i) {
    if (i < 0 || i >= vettore.length)
      throw new IllegalArgumentException("Posizione del valore da estrarre non esistente. Valore fornito: " + i);
    return vettore[i];
  }

  @Override
  public Vettore per(int alpha) {
    int[] newVettore = new int[vettore.length];

    for (int i = 0; i < vettore.length; i++) {
      newVettore[i] = vettore[i] * alpha;
    }

    return new VettoreDenso(newVettore);
  }

  @Override
  public Vettore piu(Vettore v) {
    if (v.dim() != this.dim())
      throw new IllegalArgumentException(
          "Vettori non conformi. Grandezza vettore corrente: " + this.dim() + ", vettore fornito: " + v.dim());

    int[] newVettore = new int[vettore.length];

    for (int i = 0; i < this.dim(); i++) {
      newVettore[i] = this.val(i) + v.val(i);
    }

    return new VettoreDenso(newVettore);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("(");
    for (int i = 0; i < dim(); i++) {
      sb.append(val(i) + (i < dim() - 1 ? ", " : ""));
    }
    sb.append(")");
    return sb.toString();
  }
}
