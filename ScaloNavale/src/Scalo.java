import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

/**
 * Classe che implementa uno scalo, rappresentato dalla lista di moli e da un rimorchiatore per fare le operazioni di movimento
 */
public class Scalo {
    
    private final List<Molo> moli;
    private Rimorchiatore rimorchiatore;
    // l'intero scalo controlla che non possano arrivare ed attraccare ai suoi moli due navi uguali (con lo stesso nome), attraverso il salvataggio di un codice per ogni nave (il suo hashcode)
    private Set<Integer> codiceNavi;

    /**
     * Crea un nuovo scalo con "numeroMoli" numero di moli vuoti e con insieme di navi vuoto
     * 
     * @param numeroMoli numero di moli vuoti da creare
     * @throws IllegalArgumentException se numeroMoli è < 1
     */
    public Scalo(int numeroMoli) throws IllegalArgumentException {
        if (numeroMoli < 1) {
            throw new IllegalArgumentException("Il numero di moli non può essere minore di 1");
        }
        
        this.moli = new ArrayList<>();
        for (int i = 0; i < numeroMoli; i++) {
            this.moli.add(new Molo());
        }

        this.codiceNavi = new HashSet<>();
        // il rimorchiatore verrà aggiunto dopo, per poter essere scambiato
    }

    /**
     * Imposta il rimorchiatore a rim
     * 
     * @param rim rimorchiatore da impostare
     * @throws NullPointerException se rim è null
     */
    public void setRimorchiatore(Rimorchiatore rim) throws NullPointerException {
        Objects.requireNonNull(rim);
        this.rimorchiatore = rim;
    }

    /**
     * Fa attraccare la "nave" nave al molo di numero "numeroMolo"
     * 
     * @param nave da far attraccare
     * @param numeroMolo numero di molo a cui far attraccare la nave
     * @throws NullPointerException se nave è null
     * @throws IllegalArgumentException se nave è già presente nel molo
     * @throws IllegalArgumentException numeroMolo è invalido (< 0 o >= numero moli)
     */
    public void attracca(NaveCargo nave, int numeroMolo) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(nave);
        if (codiceNavi.contains(nave.hashCode())) {
            throw new IllegalArgumentException("La nave è già presente nel molo");
        }
        if (numeroMolo < 0 || numeroMolo >= moli.size()) {
            throw new IllegalArgumentException("Il numero di molo non esiste");
        }

        moli.get(numeroMolo).attracca(nave);
        codiceNavi.add(nave.hashCode());
    }

    /**
     * Restituisce la nave che salpa dal molo "numeroMolo", lasciando lo scalo
     * 
     * @param numeroMolo numero di molo da cui far salpare la nave
     * @return la nave che salpa dal molo "numeroMolo", lasciando lo scalo
     * @throws IllegalArgumentException numeroMolo è invalido (< 0 o >= numero moli)
     */
    public NaveCargo salpa(int numeroMolo) throws IllegalArgumentException {
        if (numeroMolo < 0 || numeroMolo >= moli.size()) {
            throw new IllegalArgumentException("Il numero di molo non esiste");
        }
        if (moli.get(numeroMolo).isEmpty()) {
            throw new IllegalArgumentException("Il molo è vuoto");
        }

        NaveCargo salpa = moli.get(numeroMolo).salpa();
        codiceNavi.remove(salpa.hashCode());
        return salpa;
    }    

    /**
     * Restisuice true se il molo "numeroMolo" è vuoto, false altrimenti
     * 
     * @param numeroMolo numero di molo da controllare se sia vuoto
     * @return true se il molo "numeroMolo" è vuoto, false altrimenti
     * @throws IllegalArgumentException numeroMolo è invalido (< 0 o >= numero moli)
     */
    public boolean isMoloEmpty(int numeroMolo) throws IllegalArgumentException {
        if (numeroMolo < 0 || numeroMolo >= moli.size()) {
            throw new IllegalArgumentException("Il numero di molo non esiste");
        }

        return moli.get(numeroMolo).isEmpty();
    }

    /**
     * Restituisce il numero di viaggi necessario dal rimorchiatore per spostare "quantita" numero di navi dal molo di numero "fromMolo" al molo di numero "toMolo"
     * 
     * @param quantita numero di navi da spostare
     * @param fromMolo numero del molo da cui spostare le navi
     * @param toMolo numero del molo in cui spostare le navi
     * @throws IllegalArgumentException se quantita < 1
     * @throws IllegalArgumentException fromMolo è invalido (< 0 o >= numero moli)
     * @throws IllegalArgumentException toMolo è invalido (< 0 o >= numero moli)
     * @throws IllegalArgumentException fromMolo == toMolo
     * @return il numero di viaggi necessari dal rimorchiatore per spostare la nave
     */
    public int sposta(int quantita, int fromMolo, int toMolo) throws IllegalArgumentException {
        if (quantita < 1) {
            throw new IllegalArgumentException("Il numero di navi da spostarenon può essere minore di 1");
        }
        if (fromMolo == toMolo) {
            throw new IllegalArgumentException("I numeri di modo da cui spostare e al quale spostare non possono essere uguali");
        }
        if (fromMolo < 0 || fromMolo >= moli.size()) {
            throw new IllegalArgumentException("Il numero di molo da cui spostare non esiste");
        }
        if (toMolo < 0 || toMolo >= moli.size()) {
            throw new IllegalArgumentException("Il numero di molo al quale spostare non esiste");
        }
        Objects.requireNonNull(rimorchiatore, "Non è presente alcun rimorchiatore nello scalo");

        return rimorchiatore.sposta(quantita, fromMolo, toMolo);
    }

    /**
     * Restituisce true se il molo di numero "numeroMolo" esiste, false altrimenti
     * 
     * @param numeroMolo numero di molo da cercare
     * @return true se il molo di numero "numeroMolo" esiste, false altrimenti
     */
    public boolean moloExists(int numeroMolo) {
        return !(numeroMolo < 0 || numeroMolo >= moli.size()); 
    }

    /**
     * Restituisce il peso della prossima nave a salpare dal molo numeroMolo
     * 
     * @return il peso della prossima nave a salpare
     * @throws IllegalArgumentException se il molo non esiste
     * @throws UnsupportedOperationException se il molo è vuoto
     */
    public int pesoProssimaASalpare(int numeroMolo) {
        if (!moloExists(numeroMolo)) {
            throw new IllegalArgumentException("Il molo non esiste");
        }
        return moli.get(numeroMolo).pesoProssimaASalpare();
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < moli.size(); i++) {
            str.append(i);
            str.append(": ");
            str.append(moli.get(i).toString());
            str.append("\n");
        }

        return str.toString();
    }


    /**
     * Classe che implementa un molo, una fila navi cargo dalla quale l'ultima che entra è la prima che salpa (pila)
     */
    private class Molo {
        // un molo non ha senso di esistere senza uno scalo, quindi la ho inserita come inner class di Scalo, in modo che solo scalo stesso possa chiamare le operazioni dirette sui moli, mentre dall'esterno è possibile interfacciarsi solo con scalo
        // in questo modo posso evitare di gestire due navi uguali nello stesso molo, dato che solo lo scalo è in grado di far attrccare delle navi ad un molo e lo scalo controlla che non esistanodue navi uguali in tutto lo scalo
        
        // la fila è rappresentata da uno stack di navi, l'ultima che entra è la prima che esce
        private Stack<NaveCargo> navi;

        /**
         * Crea un nuovo molo vuoto
         */
        private Molo() {
            // il molo che viene creato è sempre vuoto, arrivano poi delle navi che vengono attraccate
            this.navi = new Stack<>();
        }

        /**
         * Attracca al molo una nuova nave cargo al molo corrente
         * 
         * @param nave da attraccare
         * @throws NullPointerException se nave è null
         */
        private void attracca(NaveCargo nave) throws NullPointerException {
            // la gestione che non ci siano navi duplicate è gestita dall'intero scalo, non dal singolo molo
            Objects.requireNonNull(nave, "La nave non può essere null");
            navi.push(nave);
        }

        /**
         * Restituisce l'ultima nave attraccata al molo
         * 
         * @throws UnsupportedOperationException se il molo è vuoto
         * @return l'ultima nave attraccata al molo
         */
        private NaveCargo salpa() throws UnsupportedOperationException {
            if (navi.isEmpty()) {
                throw new UnsupportedOperationException("Il molo è vuoto");
            }

            return navi.pop();
        }

        /**
         * Restituisce il peso della prossima nave a salpare
         * 
         * @return il peso della prossima nave a salpare
         * @throws UnsupportedOperationException se il molo è vuoto
         */
        public int pesoProssimaASalpare() throws UnsupportedOperationException {
            if (navi.isEmpty()) {
                throw new UnsupportedOperationException("Il molo è vuoto");
            }

            return navi.peek().getPeso();
        }

        /**
         * Restituisce true se il molo è vuoto, false altrimenti
         * 
         * @return restituisce true se il molo è vuoto, false altrimenti
         */
        public boolean isEmpty() {
            return navi.isEmpty();
        }

        @Override
        public String toString() {
            if (navi.isEmpty()) {
                return "< #";
            }
            StringBuilder str = new StringBuilder();
            Object[] tempStack = navi.toArray(); // mi serve mantenere lo stack intatto, quindi non è possibile fare delle pop
            str.append("< ");
            str.append(tempStack[tempStack.length-1].toString());
            for (int i = tempStack.length-2; i >= 0; i--) {
                str.append(", ");
                str.append(tempStack[i].toString());
            }
            str.append(" #");
            return str.toString();
        }

        /**
         * RI:
         *  navi != null
         */
        private boolean repOk() {
            if (navi == null) return false;

            // la gestione che non ci siano navi duplicate è gestita dall'intero scalo, non dal singolo molo dato che non avrebbe senso che due moli diversi abbiano una nave uguale (con lo stesso nome)
            // di conseguenza non è necessario gestire il casoin cui due navi uguali attraccano allo stesso molo, dato che devono essere stare prima validate dallo scalo
            return true;
        }

    }
    
}
