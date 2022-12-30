public abstract class VettoreAbstract implements Vettore {

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
   * Controlla che l'indice i sia valido per il vettore corrente
   * 
   * @param i indice fornito
   * @throws IllegalArgumentException se i è fuori dai bounds del vettore corrente
   *                                  corrente
   */
  void controlloIndici(int i) {
    if (i < 0 || i >= this.dim()) {
      throw new IllegalArgumentException("Indice non valido, fornito: " + i + " dimensione vettore: " + this.dim());
    }
  }
}
