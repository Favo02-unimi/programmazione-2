import java.util.Objects;

/*
 * Classe immutabile che rappresenta una nave cargo, caratterizzata da nome e peso
 */
public class NaveCargo {
    // questa classe potrebbe essere un record, ma la versione di java utilizzata non li implementa ancora

    private final String nome;
    private final int peso;

    /**
     * Crea una nuova nave cargo dato il suo nome e peso
     * 
     * @param nome della nave
     * @param peso della nave
     * @throws NullPointerException se nome è null
     * @throws IllegalArgumentException se nome è vuoto
     * @throws IllegalArgumentException se peso è <= 0
     */
    public NaveCargo(String nome, int peso) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(nome, "Il nome della nave non può essere null");
        if (nome.isEmpty()) {
            throw new IllegalArgumentException("Il nome della nave non può essere vuoto");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("Il peso della nave non può essere minore o uguale a 0");
        }

        this.nome = nome;
        this.peso = peso;
    }

    public String getNome() {
        return nome;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public int hashCode() {
        // due navi sono uguali se hanno lo stesso nome, a prescindere dal loro peso
        return Objects.hash(nome);
    }

    @Override
    public boolean equals(java.lang.Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof NaveCargo)) return false;
        
        NaveCargo other = (NaveCargo) obj;
        // due navi sono uguali se hanno lo stesso nome, a prescindere dal loro peso
        return other.nome.equals(this.nome);
    }

    @Override
    public String toString() {
        return nome + "[" + peso + "]";
    }

    /**
     * RI:
     *  nome != null
     *  nome non vuoto
     *  peso > 0
     */
    private boolean repOk() {
        if (nome == null) return false;
        if (nome.isEmpty()) return false;
        if (peso <= 0) return false;

        return true;
    }
    
}
