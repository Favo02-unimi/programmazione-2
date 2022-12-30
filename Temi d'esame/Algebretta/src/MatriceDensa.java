public class MatriceDensa extends MatriceAbstract {

  private Vettore[] matrice;

  /*
   * AF: Ogni riga della matrice è rappresentata da un Vettore. L'array di questi
   * vettori costituisce la matrice completa.
   */

  /*
   * RI: matrice != null, ogni vettore di matrice != null, ogni vettore.dim() ==
   * matrice.length
   */

  /**
   * Costrutture che crea una nuova matrice densa quadrata
   * 
   * @param matrice array di Vettori
   * @throws IllegalArgumentException se almeno uno tra gli array di valori
   *                                  passati ha dimensione nulla o negativa
   * @throws IllegalArgumentException se il numero di array è diverso dal numero
   *                                  di elementi di ogni array
   */
  public MatriceDensa(int[][] matrice) {
    if (matrice.length <= 0) {
      throw new IllegalArgumentException(
          "Dimensione dell'array di array fornito non valida, fornita: " + matrice.length);
    }

    for (int i = 0; i < matrice.length; i++) {
      if (matrice[i].length != matrice.length) {
        throw new IllegalArgumentException("La matrice non è quadrata, il vettore in riga " + i + " ha dimensione "
            + matrice[i].length + " (expected: " + matrice.length + ")");
      }
    }

    // creazione vettori
    Vettore[] vettori = new Vettore[matrice.length];
    for (int i = 0; i < matrice.length; i++) {
      vettori[i] = new VettoreDenso(matrice[i]);
    }

    this.matrice = vettori;
  }

  @Override
  public int dim() {
    return matrice.length;
  }

  @Override
  public int val(int row, int i) {
    controlloIndici(row, i);

    return matrice[row].val(i);
  }

  @Override
  public Matrice matricePerScalare(int alpha) {
    int[][] newMatrice = new int[this.dim()][this.dim()];
    for (int row = 0; row < this.dim(); row++) {
      for (int i = 0; i < this.dim(); i++) {
        newMatrice[row][i] = this.val(row, i) * alpha;
      }
    }

    return new MatriceDensa(newMatrice);
  }

  @Override
  public Matrice matricePiuMatrice(Matrice m) {
    controlloNull(m);
    controlloDimensione(m);

    if (m instanceof MatriceNulla) {
      return new MatriceNulla(this.dim());
    }

    if (m instanceof MatriceDiagonale || m instanceof MatriceIdentita || m instanceof MatriceDensa) {
      int[][] newMatrice = new int[this.dim()][this.dim()];

      for (int row = 0; row < this.dim(); row++) {
        for (int i = 0; i < this.dim(); i++) {
          newMatrice[row][i] = this.val(row, i) + m.val(row, i);
        }
      }

      return new MatriceDensa(newMatrice);
    }

    throw new UnsupportedOperationException("Tipo di matrice non supportato");
  }

  @Override
  public Matrice matricePerMatrice(Matrice m) {
    controlloNull(m);
    controlloDimensione(m);

    if (m instanceof MatriceNulla) {
      return new MatriceNulla(this.dim());
    }

    if (m instanceof MatriceDiagonale || m instanceof MatriceIdentita) {
      int[] diagonale = new int[this.dim()];
      for (int i = 0; i < this.dim(); i++) {
        diagonale[i] = this.val(i, i) * m.val(i, i);
      }

      return new MatriceDiagonale(diagonale);
    }

    if (m instanceof MatriceDensa) {
      int[][] newMatrice = new int[this.dim()][this.dim()];
      for (int row = 0; row < this.dim(); row++) {
        for (int i = 0; i < this.dim(); i++) {
          for (int j1 = 0, j2 = 0; j1 < this.dim(); j1++, j2++) {
            newMatrice[row][i] += (this.val(row, j1) * m.val(j2, i));
          }
        }
      }

      return new MatriceDensa(newMatrice);
    }

    throw new UnsupportedOperationException("Tipo di matrice non supportato");
  }
}
