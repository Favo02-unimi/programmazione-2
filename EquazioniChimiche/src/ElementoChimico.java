import java.util.Objects;

/*
 * Record che implementa un elemento chimico immutabile, caratterizzato da numero atomico, nome, simbolo e peso
 */
public record ElementoChimico(int numeroAtomico, String nome, String simbolo, double peso) {
    
    /**
     * Crea un nuovo elemento chimico, dato il suo numero atomico, il suo nome, il suo simbolo ed il suo peso
     * 
     * @param numeroAtomico numero atomico dell'elemento
     * @param nome dell'elemento
     * @param simbolo dell'elemento
     * @param peso dell'elemento
     * @throws IllegalArgumentException se numeroAtomico è < 1 o > 118 (numero di elementi scoperti)
     * @throws NullPointerException se nome è null
     * @throws IllegalArgumentException se nome è vuoto
     * @throws NullPointerException se simbolo è null
     * @throws IllegalArgumentException se simbolo non è valido (non è compreso tra gli esistenti)
     * @throws IllegalArgumentException se simbolo è vuoto
     * @throws IllegalArgumentException se peso è <= 0
     */
    public ElementoChimico(int numeroAtomico, String nome, String simbolo, double peso) {
        if (numeroAtomico < 1 || numeroAtomico > 118) { // non ha senso che vengano creati elementi con più di 118 come numero atomico, dato che non esistono (non sono ancora stati scoperti)
            throw new IllegalArgumentException("Numero atomico invalido");
        }

        Objects.requireNonNull(nome, "Nome non può essere null");
        if (nome.isEmpty()) {
            throw new IllegalArgumentException("Il nome non può essere vuoto");
        }

        Objects.requireNonNull(simbolo, "Il simbolo non può essere null");
        if (simbolo.isEmpty()) {
            throw new IllegalArgumentException("Il simbolo non può essere null");
        }
        if (!Helpers.elementoValido(simbolo)) {
            throw new IllegalArgumentException("Il simbolo non è valido");
        }

        if (peso <= 0) {
            throw new IllegalArgumentException("Il peso non può essere <= a 0");
        }

        this.numeroAtomico = numeroAtomico;
        this.nome = nome;
        this.simbolo = simbolo;
        this.peso = peso;
    }

    /*
     * RI:
     *  - 0 < numero atomico < 118
     *  - nome != null
     *  - nome non vuoto
     *  - simbolo != null
     *  - simbolo non vuoto
     *  - peso > 0
     */


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(simbolo);
        sb.append(" ");
        sb.append(nome);
        sb.append(" ");
        sb.append(peso);
        return sb.toString();
    }

}
