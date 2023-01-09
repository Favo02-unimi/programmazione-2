import java.util.Map;
import java.util.HashMap;

public class Pavimentazione implements Piastrellabile {

  private Map<Piastrellabile, Integer> pavimentazioni;

  public Pavimentazione() {
    pavimentazioni = new HashMap<>();
  }

  public void addPavimentazione(Piastrellabile pavimentazione) {
    pavimentazioni.put(pavimentazione, pavimentazioni.get(pavimentazione) == null ? 1 : pavimentazioni.get(pavimentazione) + 1);
  }

  public void addPavimentazione(Piastrellabile pavimentazione, int qty) {
    pavimentazioni.put(pavimentazione, pavimentazioni.get(pavimentazione) == null ? qty : pavimentazioni.get(pavimentazione) + qty);
  }

  @Override
  public int getCosto() {
    int costoTot = 0;
    for (Piastrellabile p : pavimentazioni.keySet()) {
      costoTot += (p.getCosto() * pavimentazioni.get(p));
    }
    
    return costoTot;
  }

  @Override
  public int getSuperficie() {
    int supTot = 0;
    for (Piastrellabile p : pavimentazioni.keySet()) {
      supTot += (p.getSuperficie() * pavimentazioni.get(p));
    }
    
    return supTot;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("Pavimentazione:\n");
    for (Piastrellabile p : pavimentazioni.keySet()) {
      s.append("\t" + p.getClass() + ": costo " + p.getCosto() + " superficie " + p.getSuperficie() + " quantità " + pavimentazioni.get(p));
      s.append("\n");
    }
    s.append("Totale: costo " + this.getCosto() + " superficie " + this.getSuperficie());
    return s.toString();
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((pavimentazioni == null) ? 0 : pavimentazioni.hashCode());
    return result;
  }  
  
}
