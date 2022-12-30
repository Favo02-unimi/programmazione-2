public class MatriceNulla extends MatriceAbstract {

  private int size;

  /*
   * AF: La matrice di dimenensione "size" contiene solo elementi 0.
   */

  /*
   * RI: size > 0
   */

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
    controlloIndici(row, i);

    return 0;
  }

  @Override
  public Matrice matricePerScalare(int alpha) {
    return this;
  }

  @Override
  public Matrice matricePiuMatrice(Matrice m) {
    controlloNull(m);
    controlloDimensione(m);

    return m;
  }

  @Override
  public Matrice matricePerMatrice(Matrice m) {
    controlloNull(m);
    controlloDimensione(m);

    return this;
  }

}
