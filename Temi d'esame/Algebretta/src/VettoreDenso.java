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
    return vettore.length;
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
    if (v.dim() < 0 || v.dim() >= vettore.length)
      throw new IllegalArgumentException(
          "Vettori non conformi. Grandezza vettore corrente: " + dim() + ", vettore fornito: " + v.dim());

    int[] newVettore = new int[vettore.length];

    for (int i = 0; i < vettore.length; i++) {
      newVettore[i] = vettore[i] + v.val(i);
    }

    return new VettoreDenso(newVettore);
  }

}
