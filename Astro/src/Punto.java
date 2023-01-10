/**
 * Class che rappresenta un punto in 3 dimensioni
 */
public record Punto(int x, int y, int z) {
  
  /**
   * Restituisce il punto somma tra il punto corrente e il punto q
   * 
   * @param q punto da sommare al punto corrente
   * @return il punto somma tra il punto corrente e il punto q
   * @throws NullPointerException se q è null
   */
  public Punto somma(Punto q) {
    if (q == null) {
      throw new NullPointerException("Il punto da sommare non può essere null");
    }
    return new Punto(x + q.x, y + q.y, z + q.z);
  }

  /**
   * Restituisce il punto differenza tra il punto corrente e il punto q
   * 
   * @param q punto da sottrarre al punto corrente
   * @return il punto differenza tra il punto corrente e il punto q
   * @throws NullPointerException se q è null
   */
  public Punto sottrai(Punto q) {
    if (q == null) {
      throw new NullPointerException("Il punto da sottrarre non può essere null");
    }
    return new Punto(x - q.x, y - q.y, z - q.z);
  }

  /**
   * Restituisce la norma delle coordinate del punto corrente
   * 
   * @return la norma delle coordinate del punto corrente
   */
  public int norma() {
    return Math.abs(x) + Math.abs(y) + Math.abs(z);
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ", " + z + ")";
  }

}
