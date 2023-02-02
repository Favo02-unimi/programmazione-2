import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Classe che implementa una molecola semplice
 */
public class MolecolaComposta implements Molecola, Iterable<ElementoChimico> {

    private Map<ElementoChimico, Integer> elementiQuantita;

    /**
     * Crea una nuova molecola composta, data una tavola da cui estrarre gli elementi, una lista di elementi e una lista con le quantità di ogni elementi
     * 
     * @param tavola da cui estrarre gli elementi
     * @param elementi di cui è composta la molecola
     * @param quantita in cui è presente ogni elemento
     * @throws NullPointerException se tavole è null
     * @throws IllegalArgumentException se la lunghzza di elementi è diversa dalla linghezza di quantita
     * @throws IllegalArgumentException se il numero di elementi è minore di 2
     * @throws NullPointerException se almeno un elemnto di "elementi" è null.
     *          Non verrà aggiunto nessun elemento alla molecola in caso venga alzata questa eccezione.
     * @throws IllegalArgumentException se almeno un simbolo passato non è presente nella tavola.
     *          Non verrà aggiunto nessun elemento alla molecola in caso venga alzata questa eccezione.
     * @throws IllegalArgumentException se almeno una quantità è minore di 0.
     *          Non verrà aggiunto nessun elemento alla molecola in caso venga alzata questa eccezione.
     */
    public MolecolaComposta(TavolaPeriodica tavola, String[] elementi, int[] quantita) throws IllegalArgumentException, NullPointerException {
        Objects.requireNonNull(tavola, "La tavola non può essere null");
        if (elementi.length != quantita.length) {
            throw new IllegalArgumentException("Il numero elementi e quantita deve essere uguali, ad ogni elemento corrisponde una quantita");
        }
        if (elementi.length < 2) {
            throw new IllegalArgumentException("Una molecola composta deve essere formata da almeno 2 elementi");
        }
        this.elementiQuantita = new HashMap<>();
        for (int i = 0; i < elementi.length; i++) {
            if (elementi[i] == null) {
                this.elementiQuantita = null; // non lascio molecole costruite a metà
                throw new NullPointerException("Ogni elemento non deve essere null");
            }
            if (elementi[i].isEmpty() || tavola.getElemento(elementi[i]) == null) {
                this.elementiQuantita = null; // non lascio molecole costruite a metà
                throw new IllegalArgumentException("Il simbolo " + elementi[i] + "non esiste nella tavola passata");
            }
            if (quantita[i] < 1) {
                this.elementiQuantita = null; // non lascio molecole costruite a metà
                throw new IllegalArgumentException("La quantità del simbolo " + elementi[i] + " non deve essere minore di 1");
            }

            final ElementoChimico elem = tavola.getElemento(elementi[i]);
            // caso incui è presente più volte lo stesso elemento nella formula
            if (elementiQuantita.containsKey(elem)) {
                elementiQuantita.put(elem, elementiQuantita.get(elem) + quantita[i]);   
            } else {
                elementiQuantita.put(elem, quantita[i]);   
            }
        }
    }

    @Override
    public String getFormula() {

        List<ElementoChimico> special = new ArrayList<>();
        List<ElementoChimico> toSort = new ArrayList<>();
        String[] s = {"C", "H", "N", "O"};
        for (ElementoChimico e : elementiQuantita.keySet()) {
            if (Arrays.asList(s).contains(e.simbolo())) {
                special.add(e);
            } else {
                toSort.add(e);
            }
        }

        Comparator<ElementoChimico> comp = new Comparator<>() {
            @Override
            public int compare(ElementoChimico o1, ElementoChimico o2) {
                return o1.simbolo().compareTo(o2.simbolo());
            }
        };

        Collections.sort(special, comp);
        Collections.sort(toSort, comp);

        special.addAll(toSort);

        StringBuilder sb = new StringBuilder();
        for (ElementoChimico e : special) {
            sb.append(e.simbolo());
            int qty = elementiQuantita.get(e);
            if (qty > 1) {
                sb.append(qty);
            }
        }
        return sb.toString();
    }

    @Override
    public double getPeso() {
        double pesoTot = 0;
        for (ElementoChimico e : elementiQuantita.keySet()) {
            pesoTot += (e.peso() * elementiQuantita.get(e));
        }
        return pesoTot;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getFormula());
        sb.append(", ");
        sb.append(getPeso());
        sb.append(" Da, composta");
        return sb.toString();
    }

    @Override
    public int quantita(String simbolo) throws NullPointerException {
        Objects.requireNonNull(simbolo, "il simbolo da cercare non può essere null");
        for (ElementoChimico e : elementiQuantita.keySet()) {
            if (e.simbolo().equals(simbolo)) {
                return elementiQuantita.get(e);
            }
        }
        return 0;
    }

    @Override
    public Iterator<ElementoChimico> iterator() {
        return Collections.unmodifiableCollection(elementiQuantita.keySet()).iterator();
    }


    /**
     * RI:
     *  - elementiQuantita != null
     *  - ogni chiave di elementiQuantita != null
     *  - ogni valore di elementiQuantita > 0
     */

}
