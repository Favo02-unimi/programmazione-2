public abstract class MatriceAbstract implements Matrice {

  /**
   * Controlla che la matrice fornita non sia null
   * 
   * @param m matrice fornita
   * @throws IllegalArgumentException se la matrice m è null
   */
  void controlloNull(Matrice m) {
    if (m == null) {
      throw new IllegalArgumentException("Matrice fornita null");
    }
  }

  /**
   * Controlla che il vettore fornito non sia null
   * 
   * @param v vettore fornito
   * @throws IllegalArgumentException se il vettore v è null
   */
  void controlloNull(Vettore v) {
    if (v == null) {
      throw new IllegalArgumentException("Vettore fornito null");
    }
  }

  /**
   * Controlla che la dimensioni della matrice corrente siano uguali a quelle
   * della matrice fornita m
   * 
   * @param m matrice fornita
   * @throws IllegalArgumentException se le dimensioni sono diverse
   */
  void controlloDimensione(Matrice m) {
    if (m.dim() != this.dim()) {
      throw new IllegalArgumentException(
          "Dimensione matrice fornita diversa da matrice attuale, fornita: " + m.dim() + " (expected: " + this.dim() +
              ")");
    }
  }

  /**
   * Controlla che la dimensioni della matrice corrente siano uguali a quelle
   * del vettore fornito v
   * 
   * @param v vettore fornito
   * @throws IllegalArgumentException se le dimensioni sono diverse
   */
  void controlloDimensione(Vettore v) {
    if (v.dim() != this.dim()) {
      throw new IllegalArgumentException(
          "Dimensione vettore fornito diversa da matrice attuale, fornita: " + v.dim() + " (expected: " + this.dim() +
              ")");
    }
  }

  /**
   * Controlla che gli indici i, j siano validi per la matrice correntes
   * 
   * @param i indice delle righe fornito
   * @param j indice della posizione (colonna) fornito
   * @throws IllegalArgumentException se i è fuori dai bounds della matrice
   *                                  corrente
   * @throws IllegalArgumentException se j è fuori dai bounds della matrice
   *                                  corrente
   */
  void controlloIndici(int i, int j) {
    if (i < 0 || i >= this.dim()) {
      throw new IllegalArgumentException("Riga non valida, fornita: " + i + " dimensione matrice: " + this.dim());
    }
    if (j < 0 || j >= this.dim()) {
      throw new IllegalArgumentException(
          "Posizione non valida, fornita: " + j + " dimensione riga matrice: " + this.dim());
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
