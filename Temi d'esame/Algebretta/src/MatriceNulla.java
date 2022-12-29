public class MatriceNulla extends MatriceAbstract {

  private int size;

  /**
   * Crea una nuova matrice densa nulla
   * 
   * @param size grandezza matrice
   * @throws IllegalArgumentException se la size passata è negativa
   */
  public MatriceNulla(int size) {
    if (size < 0)
      throw new IllegalArgumentException("La dimensione della matrice nulla non può essere negativa, fornita: " + size);

    this.size = size;
  }

  @Override
  public int dim() {
    return this.size;
  }

  @Override
  public int val(int row, int i) {
    // controllo bounds row
    if (row < 0 || row >= this.size)
      throw new IllegalArgumentException("Riga non valida, fornita: " + row + " dimensione matrice: " + this.size);
    // controllo bounds i
    if (i < 0 || i >= this.size)
      throw new IllegalArgumentException(
          "Posizione non valida, fornita: " + i + " dimensione riga matrice: " + this.size);

    return 0;
  }

  @Override
  public MatriceInterface prodottoScalare(int alpha) {
    return this;
  }

  @Override
  public MatriceInterface somma(MatriceInterface m) {
    // controllo dimensione m
    if (m.dim() != this.dim())
      throw new IllegalArgumentException(
          "Dimensione matrice fornita diversa da matrice attuale, fornita: " + m.dim() + " (expected: " + this.dim() +
              ")");

    return m;
  }

  @Override
  public MatriceInterface prodottoMatriciale(MatriceInterface m) {
    // controllo dimensione m
    if (m.dim() != this.dim())
      throw new IllegalArgumentException(
          "Dimensione matrice fornita diversa da matrice attuale, fornita: " + m.dim() + " (expected: " + this.dim() +
              ")");

    return this;
  }

}
