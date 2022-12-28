import java.util.ArrayList;

public class IntSet {

    // OVERVIEW: le istanze di questa classe rappresentano degli insiemi di interi

    
    // CAMPI
    private ArrayList<Integer> elems;
    

    // AF:
    //  ogni elemento di elems corrisponde ad un elemento dell'insieme
    //  in elems sono presenti tutti e solo gli elementi dell'insieme
    
    // IR:
    //  elems non deve essere null
    //  elems contiene solo int (boxed come Integer)
    //  elems non contiene duplicati


    // COSTRUTTORI

    // EFFECTS: crea un insieme vuoto
    public IntSet() {
        elems = new ArrayList<Integer>();
        assert repOk();
    }


    // METODI

    // EFFECTS: aggiunge x all'insieme. x viene ignorato se è già presente nell'insieme
    // MODIFIES: elems
    public void add(int x) {
        if (elems.contains(x)) {
            assert repOk();
            return;
        }
        elems.add(x);
        assert repOk();
    }

    // EFFECTS: rimuove x dall'insieme. x viene ignorato se non è presente nell'insieme
    // MODIFIES: elems
    public void remove(int x) {
        elems.remove(x);
        assert repOk();
    }

    // EFFECTS: restituisce un elemento arbitrario dall'insieme (non casuale!)
    //          solleva una EmptyException se l'insieme è vuoto
    public int choose() {
        if (elems.size() == 0) {
            throw new EmptyException("Non è possibile scegliere un elemento dall'insieme: l'insieme è vuoto");
        }
        return elems.get(0);
    }

    // EFFECTS: restituisce la cardinalità dell'insieme
    public int size() {
        return elems.size();
    }

    // EFFECTS: restituisce true se x è presente nell'insieme
    //          restituisce false altrimenti
    public boolean contains(int x) {
        return elems.contains(x);
    }

    // EFFECTS: restituisce una stringa formata dai valori degli elementi, racchiusi tra parentesi graffe e separati da virgola
    @Override
    public String toString() {
        if (elems.size() == 0) return "{}";

        String str = "{" + elems.get(0);
        for (int i = 1; i < elems.size(); i++) {
            str += "," + elems.get(i);
        }
        return str + "}";
    }

    // IR
    private boolean repOk() {
        if (elems == null) {
            return false;
        }
        for (Object el : elems) {
            if (!(el instanceof Integer)) {
                return false;
            }
        }
        for (int i = 0; i < elems.size(); i++) {
            for (int j = 0; j < elems.size(); j++) {
                if (i == j) continue;
                if (elems.get(i) == elems.get(j)) return false;
            }
        }
        return true;
    }

}
