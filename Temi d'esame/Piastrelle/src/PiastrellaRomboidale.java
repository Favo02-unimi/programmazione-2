public class PiastrellaRomboidale extends PiastrellaAbs {

  private int superficie;

  public PiastrellaRomboidale(int diagonale1, int diagonale2, int costo) {
    super(costo);
  
    if (diagonale1 <= 0 || diagonale2 <= 0) throw new IllegalArgumentException("Le due diagonali della piastrella romboidale non possono essere negative o nulle, fornite: " + diagonale1 + " " + diagonale2);

    this.superficie = (diagonale1 * diagonale2) / 2;

    assert repOk();
  }

  @Override
  public int getSuperficie() {
    return this.superficie;
  }

  private boolean repOk() {
    return this.getSuperficie() > 0 && this.getCosto() > 0;
  }
  
}
