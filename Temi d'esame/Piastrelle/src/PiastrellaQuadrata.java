public class PiastrellaQuadrata extends PiastrellaAbs {

  private int superficie;

  public PiastrellaQuadrata(int lato, int costo) {
    super(costo);
  
    if (lato <= 0) throw new IllegalArgumentException("Il lato della piastrella quadrata non può essere negativo o nullo, fornito: " + lato);

    this.superficie = lato*lato;

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
