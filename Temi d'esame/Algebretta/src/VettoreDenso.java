public class VettoreDenso extends VettoreAbstract {

  private int[] vettore;

  /**
   * Costruttore che crea un nuovo vettore denso
   * 
   * @param vettore elementi del vettore
   * @throws IllegalArgumentException se l'array di elementi fornito è di dimensione nulla o negativa
   */
  public VettoreDenso(int[] vettore) {
    if (vettore.length <= 0) {
      throw new IllegalArgumentException("Dimensione del vettore fornito non valida, fornita: " + vettore.length);
    }

    this.vettore = vettore;
  }

  @Override
  public int dim() {
    return vettore.length;
  }

  @Override
  public int val(int i) {
    controlloIndici(i);

    return vettore[i];
  }

  @Override
  public Vettore vettorePerScalare(int alpha) {
    int[] newVettore = new int[this.dim()];

    for (int i = 0; i < this.dim(); i++) {
      newVettore[i] = this.val(i) * alpha;
    }

    return new VettoreDenso(newVettore);
  }

  @Override
  public Vettore vettorePiuVettore(Vettore v) {
    controlloNull(v);
    controlloDimensione(v);

    int[] newVettore = new int[this.dim()];

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
