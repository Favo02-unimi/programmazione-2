public class MatriceNulla extends MatriceAbstract {

  private int size;

  /**
   * Crea una nuova matrice densa nulla
   * 
   * @param size grandezza matrice
   * @throws IllegalArgumentException se la size passata è negativa
   */
  public MatriceNulla(int size) {
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

    return 0;
  }

  @Override
  public MatriceInterface matricePerScalare(int alpha) {
    return this;
  }

  @Override
  public MatriceInterface matricePiuMatrice(MatriceInterface m) {
    // controllo dimensione m
    if (m.dim() != this.dim()) {
      throw new IllegalArgumentException(
          "Dimensione matrice fornita diversa da matrice attuale, fornita: " + m.dim() + " (expected: " + this.dim() +
              ")");
    }

    return m;
  }

  @Override
  public MatriceInterface matricePerMatrice(MatriceInterface m) {
    // controllo dimensione m
    if (m.dim() != this.dim()) {
      throw new IllegalArgumentException(
          "Dimensione matrice fornita diversa da matrice attuale, fornita: " + m.dim() + " (expected: " + this.dim() +
              ")");
    }

    return this;
  }

}
