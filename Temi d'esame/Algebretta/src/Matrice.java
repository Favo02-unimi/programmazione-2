public interface Matrice {

  /**
   * Restisuice la dimensione della matrice
   * 
   * @return la dimensione della matrice
   */
  public int dim();

  /**
   * Restituisce il valore in posizione i della righa row
   * 
   * @param row riga da cui estrarre il valore
   * @param i   posizione in cui estrarre il valore, nella righa row
   * @return il valore in posizione i della righa row
   * @throws IllegalArgumentException se row o i non sono valide (minore di zero o
   *                                  maggiore uguale alla dimensione della
   *                                  matrice corrente)
   */
  public int val(int row, int i);

  /**
   * Restituisce la matrice prodotto scalare tra la matrice corrente e la scalare
   * alpha
   * 
   * @param alpha scalare per cui moltiplicare la matrice corrente
   * @return la matrice prodotto scalare tra la matrice corrente e la scalare
   *         alpha
   */
  public Matrice prodottoScalare(int alpha);

  /**
   * Restituisce la matrice somma tra la matrice corrente e la matrice m, se le
   * matrici sono conformi
   * 
   * @param m matrice addendo da sommare alla matrice corrente
   * @return la matrice somma tra la matrice corrente e la matrice m
   * @throws IllegalArgumentException se la matrice m è di dimensione diversa
   *                                  dalla matrice corrente
   */
  public Matrice somma(Matrice m);

  /**
   * Restituisce la matrice proddo matriciale tra la matrice corrente e la matrice
   * m, se le matrici sono conformi
   * 
   * @param m matrice da moltiplicare alla matrice corrente
   * @return la matrice proddo matriciale tra la matrice corrente e la matrice m
   * @throws IllegalArgumentException se la matrice m è di dimensione diversa
   *                                  dalla matrice corrente
   */
  public Matrice prodottoMatriciale(Matrice m);
}
