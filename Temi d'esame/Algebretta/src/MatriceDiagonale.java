public class MatriceDiagonale extends MatriceAbstract {

  private int[] diagonale;

  /**
   * Crea una nuova matrice diagonale
   * 
   * @param diagonale array di valori da mettere sulla diagonale
   * @throws IllegalArgumentException se il vettore che rappresenta la diagonale
   *                                  ha dimensione negativa
   */
  public MatriceDiagonale(int[] diagonale) {
    if (diagonale.length < 0) {
      throw new IllegalArgumentException("La lunghezza della diagonale non può essere negativa");
    }

    this.diagonale = diagonale;
  }

  @Override
  public int dim() {
    return diagonale.length;
  }

  @Override
  public int val(int row, int i) {
    // controllo bounds row
    if (row < 0 || row >= this.dim()) {
      throw new IllegalArgumentException(
          "Riga non valida, fornita: " + row + " dimensione matrice: " + this.dim());
    }
    // controllo bounds i
    if (i < 0 || i >= this.dim()) {
      throw new IllegalArgumentException(
          "Posizione non valida, fornita: " + i + " dimensione riga matrice: " + this.dim());
    }

    if (row == i) {
      return diagonale[i];
    } else {
      return 0;
    }
  }

  @Override
  public MatriceInterface matricePerScalare(int alpha) {
    int[] newDiagonale = new int[this.dim()];
    for (int i = 0; i < this.dim(); i++) {
      newDiagonale[i] = this.val(i, i) * alpha;
    }

    return new MatriceDiagonale(newDiagonale);
  }

  /**
   * @throws UnsupportedOperationException se la matrice m è di tipo non
   *                                       supportato (diverso da Nulla, Identità,
   *                                       Diagonale, Densa)
   */
  @Override
  public MatriceInterface matricePiuMatrice(MatriceInterface m) {
    // controllo dimensione m
    if (m.dim() != this.dim()) {
      throw new IllegalArgumentException(
          "Dimensione matrice fornita diversa da matrice attuale, fornita: " + m.dim() + " (expected: " + this.dim() +
              ")");
    }

    if (m instanceof MatriceNulla) {
      return this;
    }

    if (m instanceof MatriceIdentita || m instanceof MatriceDiagonale) {
      int[] newDiagonale = new int[this.dim()];
      for (int i = 0; i < this.dim(); i++) {
        newDiagonale[i] = this.val(i, i) + m.val(i, i);
      }

      return new MatriceDiagonale(newDiagonale);
    }

    if (m instanceof MatriceDensa) {
      return m.matricePiuMatrice(this);
    }

    throw new UnsupportedOperationException("Matrice di tipo non supportato");
  }

  /**
   * @throws UnsupportedOperationException se la matrice m è di tipo non
   *                                       supportato (diverso da Nulla, Identità,
   *                                       Diagonale, Densa)
   */
  @Override
  public MatriceInterface matricePerMatrice(MatriceInterface m) {
    // controllo dimensione m
    if (m.dim() != this.dim()) {
      throw new IllegalArgumentException(
          "Dimensione matrice fornita diversa da matrice attuale, fornita: " + m.dim() + " (expected: " + this.dim() +
              ")");
    }

    if (m instanceof MatriceNulla) {
      return new MatriceNulla(this.dim());
    }

    if (m instanceof MatriceIdentita) {
      return this;
    }

    if (m instanceof MatriceDiagonale) {
      int[] newDiagonale = new int[this.dim()];
      for (int i = 0; i < this.dim(); i++) {
        newDiagonale[i] = this.val(i, i) * m.val(i, i);
      }

      return new MatriceDiagonale(newDiagonale);
    }

    if (m instanceof MatriceDensa) {
      return m.matricePerMatrice(this);
    }

    throw new UnsupportedOperationException("Matrice di tipo non supportato");
  }
}
