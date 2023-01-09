import java.util.HashMap;

public class Piastrelle implements PavimentazioneInt {
  
  private HashMap<Piastrella, Integer> piastrelle;

  public Piastrelle() {
    piastrelle = new HashMap<>();
  }

  public void addPiastrelle(Piastrella piastrella, int quantity) {
    if (quantity <= 0) throw new IllegalArgumentException("Non è possibile aggiungere una quantità negativa, quantità fornita: " + quantity);

    piastrelle.put(piastrella, piastrelle.get(piastrella) + quantity);
  }

  @Override
  public int getCostoTot() {
    int costoTot = 0;
    for (Piastrella p : piastrelle.keySet()) {
      costoTot += p.getCosto() * piastrelle.get(p);
    }

    return costoTot;
  }

  @Override
  public int getSuperficieTot() {
    int supTot = 0;
    for (Piastrella p : piastrelle.keySet()) {
      supTot += p.getSuperficie() * piastrelle.get(p);
    }

    return supTot;
  }

}
