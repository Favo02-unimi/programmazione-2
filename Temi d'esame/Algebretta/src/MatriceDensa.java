public class MatriceDensa extends MatriceAbstract {

  private Vettore[] matrice;

  /**
   * Crea una nuova matrice densa quadrata
   * 
   * @param matrice array di Vettori
   * @throws IllegalArgumentException se non tutti i Vettori che vengono passati
   *                                  sono della stessa dimensione della matrice
   */
  public MatriceDensa(int[][] matrice) {
    // controllo matrice quadrata
    for (int i = 0; i < matrice.length; i++) {
      if (matrice[i].length != matrice.length)
        throw new IllegalArgumentException("La matrice non è quadrata, il vettore in riga " + i + " ha dimensione "
            + matrice[i].length + " (expected: " + matrice.length + ")");
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
    // controllo bounds row
    if (row < 0 || row >= matrice.length)
      throw new IllegalArgumentException("Riga non valida, fornita: " + row + " dimensione matrice: " + matrice.length);
    // controllo bounds i
    if (i < 0 || i >= matrice.length)
      throw new IllegalArgumentException(
          "Posizione non valida, fornita: " + i + " dimensione riga matrice: " + matrice.length);

    return matrice[row].val(i);
  }

  @Override
  public MatriceInterface prodottoScalare(int alpha) {
    int[][] newMatrice = new int[matrice.length][matrice.length];
    for (int row = 0; row < matrice.length; row++) {
      for (int i = 0; i < newMatrice.length; i++) {
        newMatrice[i][row] = matrice[row].val(i) * alpha;
      }
    }

    return new MatriceDensa(newMatrice);
  }

  /**
   * @throws UnsupportedOperationException se la matrice m è di tipo non
   *                                       supportato (diverso da Nulla,
   *                                       Diagonale, Identità, Densa)
   */
  @Override
  public MatriceInterface somma(MatriceInterface m) {
    // controllo dimensione m
    if (m.dim() != this.dim())
      throw new IllegalArgumentException(
          "Dimensione matrice fornita diversa da matrice attuale, fornita: " + m.dim() + " (expected: " + this.dim() +
              ")");

    if (m instanceof MatriceNulla)
      return new MatriceNulla(this.dim());

    if (m instanceof MatriceDiagonale || m instanceof MatriceIdentita || m instanceof MatriceDensa) {
      int[][] newMatrice = new int[matrice.length][matrice.length];

      for (int row = 0; row < matrice.length; row++) {
        for (int i = 0; i < newMatrice.length; i++) {
          newMatrice[i][row] = matrice[row].val(i) + m.val(row, i);
        }
      }

      return new MatriceDensa(newMatrice);
    }

    throw new UnsupportedOperationException("Tipo di matrice non supportato");
  }

  /**
   * @throws UnsupportedOperationException se la matrice m è di tipo non
   *                                       supportato (diverso da Nulla,
   *                                       Diagonale, Identità, Densa)
   */
  @Override
  public MatriceInterface prodottoMatriciale(MatriceInterface m) {
    // controllo dimensione m
    if (m.dim() != this.dim())
      throw new IllegalArgumentException(
          "Dimensione matrice fornita diversa da matrice attuale, fornita: " + m.dim() + " (expected: " + this.dim() +
              ")");

    if (m instanceof MatriceNulla)
      return new MatriceNulla(this.dim());

    if (m instanceof MatriceDiagonale || m instanceof MatriceIdentita) {
      int[] diagonale = new int[this.dim()];
      for (int i = 0; i < diagonale.length; i++) {
        diagonale[i] = this.matrice[i].val(i) * m.val(i, i);
      }

      return new MatriceDiagonale(diagonale);
    }

    if (m instanceof MatriceDensa) {
      int[][] newMatrice = new int[matrice.length][matrice.length];
      for (int row = 0; row < newMatrice.length; row++) {
        for (int i = 0; i < newMatrice.length; i++) {
          for (int j1 = 0, j2 = 0; j1 < newMatrice.length; j1++, j2++) {
            newMatrice[i][row] += (this.val(row, j1) * m.val(j2, i));
          }
        }
      }

      return new MatriceDensa(newMatrice);
    }

    throw new UnsupportedOperationException("Tipo di matrice non supportato");
  }
}
