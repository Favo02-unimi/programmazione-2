import java.util.LinkedList;
import java.util.List;

public class SparsePoly {

    // OVERVIEW: Le istanze di questa classe rappresentano polinomi sparsi a una variabile con grado non negativo. Un polimio tipoco è: c0x^d0 + ... + cn*x^dn

    private record Monomio(int degree, int coeff) {
        
        // inizializza automaticamente i campi
        public Monomio {
            if (degree < 0) {
                throw new NegativeExponentException("Il grado di un monimio non può essere negativo");
            }
        }

        @Override
        public String toString() {
            return coeff + "x^" + degree;
        }

    }

    // CAMPI
    List<Monomio> terms;


    // AF(terms) =  terms[0].coeff * x ^ terms[0].degree + ... + terms[n].coeff * x ^ terms[n].degree
    //              if n == 0 => 0
    
    // RI(terms) =  terms[i].degree >= 0
    //              && terms dev'essere ordinata per grado
    //              && tutti gli elementi di terms devono essere Monomi


    // COSTRUTTORI

    // EFFECTS: costruisce il polinomio zero
    public SparsePoly() {
        terms = new LinkedList<>();
    }

    // EFFECTS: costruisce il polinomio coeff*x^degree
    //          solleva una NegativeExponentException se dregree < 0
    public SparsePoly(int coeff, int degree) {
        this(); // chiama il costruttore di questa class con zero argomenti (il costruttore del polinomio zero)
        if (coeff != 0) terms.add(new Monomio(degree, coeff));
    }


    // METODI

    // EFFECTS: restituisce il grado del polinomio
    public int degree() {
        if (terms.size() == 0) return -1;
        return terms.get(terms.size() - 1).degree;
    }

    // EFFECTS: restituisce l'indice del monomio il cui grado è degree
    //          restituisce -1 se terms è vuoto o non contiene degree
    private int findByDegree(int degree) {
        int i = 0;
        for (Monomio monomio : terms) {
            if (monomio.degree == degree) {
                return i;
            }
            i++;
        }
        return -1;
    }

    // REQUIRES: degree >= 0
    // EFFECTS: restituisce il coefficiente relativo a x^degree
    public int coeffByDegree(int degree) {
        int pos = findByDegree(degree); 
        if (pos < 0) {
            return 0;
        }
        return terms.get(pos).coeff;
    }

    // REQUIRES: other non null
    // EFFECTS: restituisce il polinomio risultante dalla somma di this + other
    public SparsePoly sum(SparsePoly other) {
        if (this.terms.size() == 0) {
            return other;
        }
        if (other.terms.size() == 0) {
            return this;
        }

        SparsePoly res = new SparsePoly();

        for (Monomio monomio : this.terms) {
            if (other.findByDegree(monomio.degree) != -1) {
                res.terms.add(new Monomio(monomio.degree, monomio.coeff + other.coeffByDegree(monomio.degree)));
            }
            else {
                res.terms.add(new Monomio(monomio.degree, monomio.coeff));
            }
        }

        for (Monomio monomio : other.terms) {
            if (this.findByDegree(monomio.degree) == -1) {
                res.terms.add(new Monomio(monomio.degree, monomio.coeff));
            }
        }

        return res;
    } 

    // REQUIRES: other non null
    // EFFECTS: restituisce il polinomio risultante dalla moltiplicazione di this * other
    public SparsePoly mul(SparsePoly other) {
        SparsePoly res = new SparsePoly();

        for (Monomio thisM : this.terms) {
            for (Monomio otherM : other.terms) {
                int coeffProd = thisM.coeff * otherM.coeff;
                int expProd = thisM.degree + otherM.degree;
                res = res.sum(new SparsePoly(coeffProd, expProd));
                System.out.println(res);
            }
        }

        return res;
    } 

    // EFFECTS: restituisce -this
    public SparsePoly minus() {
        SparsePoly res = new SparsePoly();
        for (Monomio monomio : terms) {
            res = res.sum(new SparsePoly(-(monomio.coeff), monomio.degree));
        }
        return res;
    }

    @Override
    public String toString() {
        if (this.terms.size() == 0) {
            return "";
        }
        String res = this.terms.get(0).coeff + "x^" + this.terms.get(0).degree;
        for (int i = 1; i < this.terms.size(); i++) {
            res += " + " + this.terms.get(i).coeff + "x^" + this.terms.get(i).degree;
        }
        return res;
    }

    public static void main(String[] args) {
        SparsePoly a = new SparsePoly(2, 1);
        SparsePoly b = new SparsePoly(3, 3);
        b = b.sum(new SparsePoly(-2, 2));
        b = b.sum(new SparsePoly(1, 1));
        b = b.sum(new SparsePoly(-4, 0));
        System.out.println(a);
        System.out.println(b);

        System.out.println("=========");
        SparsePoly ab = a.mul(b);
        System.out.println("=========");
        System.out.println(ab);
    }

}
