public class PiastrellaTriangolare extends PiastrellaAbs {

  private int superficie;

  /**
   * Crea una nuova PiastrellaTriangolare date la base, l'altezza ed il costo
   * 
   * @param base base della piastrella triangolare
   * @param altezza altezza della piastrella triangolare
   * @param costo costo unitario della piastrella
   * @throws IllegalArgumentException se il costo è negativo o nullo
   * @throws IllegalArgumentException se la base o l'altezza è negativa o nulla
   */
  public PiastrellaTriangolare(int base, int altezza, int costo) {
    super(costo);
  
    if (base <= 0 || altezza <= 0) throw new IllegalArgumentException("La base o l'altezza della piastrella triangolare non possono essere negative o nulle, forniti: " + base + " " + altezza);

    this.superficie = base * altezza;
  }

  @Override
  public int getSuperficie() {
    return this.superficie;
  }
  
}
