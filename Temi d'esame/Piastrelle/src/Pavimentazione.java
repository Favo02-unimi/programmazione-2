import java.util.Map;
import java.util.HashMap;

public class Pavimentazione implements PavimentazioneInt {

  private Map<PavimentazioneInt, Integer> pavimentazioni;

  public Pavimentazione() {
    pavimentazioni = new HashMap<>();
  }

  public void addPavimentazione(PavimentazioneInt pavimentazione) {
    pavimentazioni.put(pavimentazione, pavimentazioni.get(pavimentazione) == null ? 1 : pavimentazioni.get(pavimentazione) + 1);
  }

  public void addPavimentazione(PavimentazioneInt pavimentazione, int qty) {
    pavimentazioni.put(pavimentazione, pavimentazioni.get(pavimentazione) == null ? qty : pavimentazioni.get(pavimentazione) + qty);
  }

  @Override
  public int getCosto() {
    int costoTot = 0;
    for (PavimentazioneInt p : pavimentazioni.keySet()) {
      costoTot += (p.getCosto() * pavimentazioni.get(p));
    }
    
    return costoTot;
  }

  @Override
  public int getSuperficie() {
    int supTot = 0;
    for (PavimentazioneInt p : pavimentazioni.keySet()) {
      supTot += (p.getSuperficie() * pavimentazioni.get(p));
    }
    
    return supTot;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("Pavimentazione:\n");
    for (PavimentazioneInt p : pavimentazioni.keySet()) {
      s.append("\t" + p.getClass() + ": costo " + p.getCosto() + " superficie " + p.getSuperficie());
      s.append("\n");
    }
    s.append("Totale: costo " + this.getCosto() + " superficie " + this.getSuperficie());
    return s.toString();
  }
  
  
}
