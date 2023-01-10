import java.util.Map;
import java.util.HashMap;

/**
 * Classe mutabile che rappresenta un insieme di piastrelle e pavimentazioni
 */
public class Pavimentazione implements Piastrellabile {

  /**
   * Ogni piastrella o pavimentazione è presente in una certa quantità nella pavimentazione
   */
  private Map<Piastrellabile, Integer> pavimentazioni;

  /**
   * Crea una nuova pavimentazione
   */
  public Pavimentazione() {
    pavimentazioni = new HashMap<>();
  }

  /**
   * Aggiunge un istanza Piastrellabile in quantità 1 alla pavimentazione
   * 
   * @param piastrellabile istanza da aggiungere a pavimentazione
   */
  public void addPiastrellabile(Piastrellabile piastrellabile) {
    pavimentazioni.put(piastrellabile, pavimentazioni.get(piastrellabile) == null ? 1 : pavimentazioni.get(piastrellabile) + 1);
  }

  /**
   * Aggiunge delle istanze Piastrellabili in quantità qty alla pavimentazione
   * 
   * @param piastrellabile istanza da aggiungere a pavimentazione
   * @param qty quantità di istanze piastrellabile da aggiungere a pavimentazione
   */
  public void addPiastrellabile(Piastrellabile piastrellabile, int qty) {
    pavimentazioni.put(piastrellabile, pavimentazioni.get(piastrellabile) == null ? qty : pavimentazioni.get(piastrellabile) + qty);
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
