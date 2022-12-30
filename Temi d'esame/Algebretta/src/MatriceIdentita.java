public class MatriceIdentita extends MatriceAbstract {

  private int size;

  /**
   * Costruttore che crea una nuova matrice densa identità
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
    controlloIndici(row, i);

    if (row == i) {
      return 1;
    } else {
      return 0;
    }
  }

  @Override
  public Matrice matricePerScalare(int alpha) {
    if (alpha == 0) {
      return new MatriceNulla(this.dim());
    }

    int[] newDiagonale = new int[this.dim()];
    for (int i = 0; i < this.dim(); i++) {
      newDiagonale[i] = alpha;
    }

    return new MatriceDiagonale(newDiagonale);
  }

  @Override
  public Matrice matricePiuMatrice(Matrice m) {
    controlloNull(m);
    controlloDimensione(m);

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

  @Override
  public Matrice matricePerMatrice(Matrice m) {
    controlloNull(m);
    controlloDimensione(m);

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
