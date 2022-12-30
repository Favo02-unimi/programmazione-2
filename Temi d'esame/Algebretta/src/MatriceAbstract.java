public abstract class MatriceAbstract implements Matrice {

  void controlloNull(Matrice m) {
    if (m == null) {
      throw new IllegalArgumentException("Matrice fornita null");
    }
  }

  void controlloNull(Vettore v) {
    if (v == null) {
      throw new IllegalArgumentException("Vettore fornito null");
    }
  }

  void controlloDimensione(Matrice m) {
    if (m.dim() != this.dim()) {
      throw new IllegalArgumentException(
          "Dimensione matrice fornita diversa da matrice attuale, fornita: " + m.dim() + " (expected: " + this.dim() +
              ")");
    }
  }

  void controlloDimensione(Vettore v) {
    if (v.dim() != this.dim()) {
      throw new IllegalArgumentException(
          "Dimensione vettore fornito diversa da matrice attuale, fornita: " + v.dim() + " (expected: " + this.dim() +
              ")");
    }
  }

  @Override
  public Vettore matricePerVettore(Vettore v) {
    controlloNull(v);
    controlloDimensione(v);

    int[] newArray = new int[this.dim()];
    for (int i = 0; i < newArray.length; i++) {
      int val = 0;
      for (int r = 0; r < newArray.length; r++) {
        val += (this.val(i, r) * v.val(r));
      }
      newArray[i] = val;
    }

    return new VettoreDenso(newArray);
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < dim(); i++) {
      for (int j = 0; j < dim(); j++) {
        sb.append(val(i, j) + (j < dim() - 1 ? ", " : ""));
      }
      if (i < dim() - 1) {
        sb.append("; ");
      }
    }
    sb.append("]");
    return sb.toString();
  }

}
