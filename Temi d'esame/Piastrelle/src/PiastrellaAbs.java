public abstract class PiastrellaAbs implements Piastrellabile {
  
  private int costo;

  /**
   * Crea una nuova piastrella dato il suo costo
   * 
   * @param costo costo unitario della piastrella
   * @throws IllegalArgumentException se il costo è negativo o nullo
   */
  public PiastrellaAbs(int costo) {
    if (costo <= 0) throw new IllegalArgumentException("Il costo della piastrella non può essere negativo o nullo, fornito: " + costo);

    this.costo = costo;
  }

  @Override
  public int getCosto() {
    return costo;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("Piastrella: " + this.getClass() + ":");
    s.append("\tCosto " + this.getCosto() + " superficie " + this.getSuperficie());
    return s.toString();
  }

}
