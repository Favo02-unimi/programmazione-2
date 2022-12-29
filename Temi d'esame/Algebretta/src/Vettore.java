public interface Vettore {

  /**
   * Restituisce la dimensione del vettore
   * 
   * @returns la dimensione del vettore
   */
  public int dim();

  /**
   * Restituisce il valore in posizione i
   * 
   * @param i posizione del valore da restituire
   * @return il valore in posizione i
   * @throws IllegalArgumentException se la posizione i non esiste (minore di zero
   *                                  o maggiore uguale dimensione vettore)
   */
  public int val(final int i);

  /**
   * Restituisce il vettore prodotto del vettore corrente per la scalare alpha
   * 
   * @param alpha scalare per cui moltiplicare il vettore
   * @return il vettore prodotto del vettore corrente per la scalare alpha
   */
  public Vettore vettorePerVettore(final int alpha);

  /**
   * Restituisce il vettore somma tra il vettore corrente e il vettore v, se i due
   * vettori sono conformi
   * 
   * @param v vettore addendo da sommare al vettore corrente
   * @return il vettore somma tra il vettore corrente e il vettore v
   * @throws IllegalArgumentException se la dimensione del vettore v è diversa da
   *                                  quella del vettore corrente
   */
  public Vettore vettorePiuVettore(final Vettore v);

}
