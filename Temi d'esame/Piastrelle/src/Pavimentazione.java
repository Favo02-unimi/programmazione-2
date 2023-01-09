import java.util.ArrayList;
import java.util.List;


public class Pavimentazione implements PavimentazioneInt {

  private List<PavimentazioneInt> pavimentazioni;

  public Pavimentazione() {
    pavimentazioni = new ArrayList<>();
  }

  public void addPavimentazione(PavimentazioneInt pavimentazione) {
    pavimentazioni.add(pavimentazione);
  }

  @Override
  public int getCostoTot() {
    int costoTot = 0;
    for (PavimentazioneInt p : pavimentazioni) {
      costoTot += p.getCostoTot();
    }
    
    return costoTot;
  }

  @Override
  public int getSuperficieTot() {
    int supTot = 0;
    for (PavimentazioneInt p : pavimentazioni) {
      supTot += p.getSuperficieTot();
    }
    
    return supTot;
  }

  @Override
  public String toString() {
    StringBuilder s = new StringBuilder();
    s.append("Pavimentazione:\n");
    for (PavimentazioneInt p : pavimentazioni) {
      s.append("\t" + p.getClass() + ": costo " + p.getCostoTot() + " superficie " + p.getSuperficieTot());
      s.append("\n");
    }
    s.append("Totale: costo " + this.getCostoTot() + " superficie " + this.getSuperficieTot());
    return s.toString();
  }
  
  
}
