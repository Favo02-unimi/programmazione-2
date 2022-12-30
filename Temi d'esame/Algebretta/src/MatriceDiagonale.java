public class MatriceDiagonale extends MatriceAbstract {

  private int[] diagonale;

  /*
   * AF: La diagonale della matrice è rappresentata dall'array "diagonale", tutti
   * gli altri elementi della matrice sono 0
   */

  /*
   * RI: diagonale != null
   */

  /**
   * Costruttore che crea una nuova matrice diagonale
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
    controlloIndici(row, i);

    if (row == i) {
      return diagonale[i];
    } else {
      return 0;
    }
  }

  @Override
  public Matrice matricePerScalare(int alpha) {
    int[] newDiagonale = new int[this.dim()];
    for (int i = 0; i < this.dim(); i++) {
      newDiagonale[i] = this.val(i, i) * alpha;
    }

    return new MatriceDiagonale(newDiagonale);
  }

  @Override
  public Matrice matricePiuMatrice(Matrice m) {
    controlloNull(m);
    controlloDimensione(m);

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

  @Override
  public Matrice matricePerMatrice(Matrice m) {
    controlloNull(m);
    controlloDimensione(m);

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
