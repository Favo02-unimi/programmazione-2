public class MatriceIdentita extends MatriceAbstract {

  private int size;

  /**
   * Crea una nuova matrice densa identità
   * 
   * @param size dimensione della matrice
   * @throws IllegalArgumentException se il vettore che rappresenta la diagonale
   *                                  ha dimensione negativa
   */
  public MatriceIdentita(int size) {
    if (size < 0) {
      throw new IllegalArgumentException("La dimensione della matrice nulla non può essere negativa, fornita: " + size);
    }

    this.size = size;
  }

  @Override
  public int dim() {
    return this.size;
  }

  @Override
  public int val(int row, int i) {
    // controllo bounds row
    if (row < 0 || row >= this.dim()) {
      throw new IllegalArgumentException("Riga non valida, fornita: " + row + " dimensione matrice: " + this.dim());
    }
    // controllo bounds i
    if (i < 0 || i >= this.dim()) {
      throw new IllegalArgumentException(
          "Posizione non valida, fornita: " + i + " dimensione riga matrice: " + this.dim());
    }

    if (row == i) {
      return 1;
    } else {
      return 0;
    }
  }

  @Override
  public MatriceInterface matricePerScalare(int alpha) {
    if (alpha == 0) {
      return new MatriceNulla(this.dim());
    }

    int[] newDiagonale = new int[this.dim()];
    for (int i = 0; i < this.dim(); i++) {
      newDiagonale[i] = alpha;
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
      return new MatriceNulla(this.dim());
    }

    if (m instanceof MatriceIdentita || m instanceof MatriceDiagonale) {
      int[] newDiagonale = new int[this.dim()];
      for (int i = 0; i < this.dim(); i++) {
        newDiagonale[i] = 1 + m.val(i, i);
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

    if (m instanceof MatriceIdentita || m instanceof MatriceDiagonale) {
      return m;
    }

    if (m instanceof MatriceDensa) {
      return m.matricePerMatrice(this);
    }

    throw new UnsupportedOperationException("Matrice di tipo non supportato");
  }
}
