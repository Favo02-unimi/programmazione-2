public abstract class PiastrellaAbs implements Piastrellabile {
  
  private int costo;

  public PiastrellaAbs(int costo) {
    if (costo <= 0) throw new IllegalArgumentException("Il costo della piastrella non può essere negativo o nullo, fornito: " + costo);

    this.costo = costo;

    assert repOk();
  }

  @Override
  public int getCosto() {
    return costo;
  }

  private boolean repOk() {
    return this.getCosto() > 0;
  }
  
}
