import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;

/**
 * Classe che implementa una molecola semplice, formata da un solo elemento ripetuto più volte
 */
public class MolecolaSemplice implements Molecola, Iterable<ElementoChimico> {
    
    private final ElementoChimico elemento;
    private final int quantita;

    /**
     * Crea una nuova molecola semplice, formato da un elemento estratto da una tavola periodica ripetuto "quantita" volte
     * 
     * @param tavola da cui estrarre l'elemento
     * @param simbolo dell'elemento da estrarre
     * @param quantita di volte l'elemento è ripetuto per formare la molecola
     */
    public MolecolaSemplice(TavolaPeriodica tavola, String simbolo, int quantita) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(tavola, "La tavola non può essere null");
        Objects.requireNonNull(simbolo, "Il simbolo non può essere null");
        if (simbolo.isEmpty() || tavola.getElemento(simbolo) == null) {
            throw new IllegalArgumentException("Il simbolo non esiste nella tavola passata");
        }
        if (quantita <= 1) {
            throw new IllegalArgumentException("Il numero di elementi non può essere minore o uguale a 1");
        }

        this.elemento = tavola.getElemento(simbolo);
        this.quantita = quantita;
    }

    @Override
    public String getFormula() {
        return elemento.simbolo() + quantita;
    }

    @Override
    public double getPeso() {
        return elemento.peso() * quantita;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFormula());
        sb.append(", ");
        sb.append(getPeso());
        sb.append(" Da, semplice");
        return sb.toString();
    }

    @Override
    public int quantita(String simbolo) throws NullPointerException {
        Objects.requireNonNull(simbolo, "il simbolo da cercare non può essere null");
        if (elemento.simbolo().equals(simbolo)) {
            return quantita;
        }
        return 0;
    }

    @Override
    public Iterator<ElementoChimico> iterator() {
        return Collections.singleton(elemento).iterator();
    }

    /**
     * RI:
     *  - elemento != null
     *  - quantita > 1
     */

}
