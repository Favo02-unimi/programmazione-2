public class PiastrellaQuadrata extends PiastrellaAbs {

  private int superficie;

  /**
   * Crea una nuova PiastrellaQuadrata dato il suo lato ed il suo costo
   * 
   * @param lato lato della piastrella quadrata
   * @param costo costo unitario della piastrella
   * @throws IllegalArgumentException se il costo è negativo o nullo
   * @throws IllegalArgumentException se il lato è negativo o nullo
   */
  public PiastrellaQuadrata(int lato, int costo) {
    super(costo);
  
    if (lato <= 0) throw new IllegalArgumentException("Il lato della piastrella quadrata non può essere negativo o nullo, fornito: " + lato);

    this.superficie = lato*lato;
  }

  @Override
  public int getSuperficie() {
    return this.superficie;
  }
  
}
