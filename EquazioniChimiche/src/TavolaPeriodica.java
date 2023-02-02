import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.List;

/**
 * Class immutabile che implementa una tavola periodica, la quale contiene un numero finito di elementi chimici
 */
public class TavolaPeriodica {
    
    // l'elemento in posizione i ha numero atomico i+1
    private ElementoChimico[] elementi;

    /**
     * Crea una nuova tavola periodica contenente gli elementi chimici passati
     * 
     * @param elementi lista degli elementi chimici da inserire nella tavola
     * @throws NullPointerException se elementi è null
     * @throws NullPointerException se un elemento tra gli elementi chimici passati è null.
     *          Nessun elemento sarà inserito nella tavola periodica in caso venga alzata questa eccezione
     * @throws IllegalArgumentException se due elementi hanno lo stesso simbolo.
     *          Nessun elemento sarà inserito nella tavola periodica in caso venga alzata questa eccezione
     * @throws IllegalArgumentException se almeno un elemento non è nella posizione della lista uguale al suo numero periodico -1.
     *          Nessun elemento sarà inserito nella tavola periodica in caso venga alzata questa eccezione
     */
    public TavolaPeriodica(List<ElementoChimico> elementi) throws NullPointerException, IllegalArgumentException {
        Set<String> simboliInseriti = new HashSet<>(); // controllo solo alla creazione che non siano inseriti due elementi con stesso simbolo

        Objects.requireNonNull(elementi, "La lista di elementi non può essere null");
        this.elementi = new ElementoChimico[elementi.size()];
        for (int i = 0; i < elementi.size(); i++) {
            if (elementi.get(i) == null) {
                this.elementi = null; // distruggo l'array di elementi già aggiunti, per non lasciare una tavola periodica non completa
                throw new NullPointerException("Nessun elemento passato alla tavola periodica deve essere null");
            }
            if (simboliInseriti.contains(elementi.get(i).simbolo())) {
                this.elementi = null; // distruggo l'array di elementi già aggiunti, per non lasciare una tavola periodica non completa
                throw new IllegalArgumentException("Non possono esistere due elementi con lo stesso simbolo");
            }
            if (elementi.get(i).numeroAtomico() != i+1) {
                this.elementi = null; // distruggo l'array di elementi già aggiunti, per non lasciare una tavola periodica non completa
                throw new IllegalArgumentException("La lista di elementi fornita non è contigua");
            }
            simboliInseriti.add(elementi.get(i).simbolo());
            this.elementi[i] = elementi.get(i);
        }
    }

    /**
     * Restituisce l'elemento di peso atomico "numeroAtomico" se presente nella tavola, null altrimenti
     * 
     * @param numeroAtomico dell'elemento da restituire
     * @return l'elemento di peso atomico "numeroAtomico" se presente nella tavola, null altrimenti
     */
    public ElementoChimico getElemento(int numeroAtomico) {
        numeroAtomico--; // gli elementi partono da 1, l'array da 0
        if (numeroAtomico < 0 || numeroAtomico > elementi.length) {
            return null;
        }
        return elementi[numeroAtomico];
    }

    /**
     * Restituisce l'elemento di simbolo "simbolo" se presente nella tavola, null altrimenti
     * 
     * @param simbolo dell'elemento da resituire
     * @return l'elemento di simbolo "simbolo" se presente nella tavola, null altrimenti
     * @throws NullPointerException se simbolo è null
     */
    public ElementoChimico getElemento(String simbolo) throws NullPointerException {
        Objects.requireNonNull(simbolo, "Il simbolo non può essere null");
        // in caso l'utente chieda un simbolo vuoto viene semplicmenete restituito null, non ho bisogno di controllare
        for (ElementoChimico e : elementi) {
            if (e.simbolo().equals(simbolo)) {
                return e;
            }
        }
        return null;
    }

    /**
     * RI:
     *  - elementi != null
     *  - ogni elemento di elementi != null
     *  - non ci possono essere due elementi con lo stesso simbolo
     *  - non ci possono essere due elemtni con lo stesso numero atomico
     *  - non ci possono essere "buchi", la tavola deve essere contigua
     */

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ElementoChimico e : elementi) {
            sb.append(e.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
