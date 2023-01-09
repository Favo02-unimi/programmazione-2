public class PiastrellaRomboidale extends PiastrellaAbs {

  private int superficie;

  /**
   * Crea una nuova PiastrellaRomboidale date le due diagonali ed il suo costo
   * 
   * @param diagonale1 prima diagonale della piastrella romboidale
   * @param diagonale2 seconda diagonale della piastrella romboidale
   * @param costo costo unitario della piastrella
   * @throws IllegalArgumentException se il costo è negativo o nullo
   * @throws IllegalArgumentException se la diagonale1 o la diagonale2 è negativa o nulla
   */
  public PiastrellaRomboidale(int diagonale1, int diagonale2, int costo) {
    super(costo);
  
    if (diagonale1 <= 0 || diagonale2 <= 0) throw new IllegalArgumentException("Le due diagonali della piastrella romboidale non possono essere negative o nulle, fornite: " + diagonale1 + " " + diagonale2);

    this.superficie = (diagonale1 * diagonale2) / 2;
  }

  @Override
  public int getSuperficie() {
    return this.superficie;
  }
  
}
