public class PiastrellaTriangolare extends PiastrellaAbs {

  private int superficie;

  public PiastrellaTriangolare(int base, int altezza, int costo) {
    super(costo);
  
    if (base <= 0 || altezza <= 0) throw new IllegalArgumentException("La base o l'altezza della piastrella triangolare non possono essere negative o nulle, forniti: " + base + " " + altezza);

    this.superficie = base * altezza;

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
