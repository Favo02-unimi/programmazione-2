import java.util.ArrayList;

public class Poly {
    
    // OVERVIEW: le istanze di questa classe rappresentano dei polinomi in una sola variabile a coefficienti interi, con grado non negativo

    
    // CAMPI
    private ArrayList<Integer> coeffs;
    

    // AF: coeffs[i] rappresenta il coefficiente di un polinomio, elevato ad x^i.
    //     il polinomio è formato sommando tutti i coffienti correttamente elevati

    // IR: non ci possono essere coefficienti null

    
    // COSTRUTTORI

    // MODIFIES: coeffs
    // EFFECTS: crea il polinomio zero
    public Poly() {
        coeffs = new ArrayList<>();
    }

    // MODIFIES: coeffs
    // EFFECTS: crea il polinomio cx^n
    //          solleva NegativeExponentException se n è negativo
    public Poly(int c, int n) {
        coeffs = new ArrayList<>();
        if (n < 0) {
            throw new NegativeExponentException("l'esponente non può essere negativo, esponente fornito: " + n);
        }
        for (int i = 0; i < n; i++) {
            coeffs.add(0);
        }
        coeffs.add(c);
        assert repOk();
    }

    // REQUIRES: q non null
    // EFFECTS: restituisce il polinomio somma tra this e il polinomio q
    public Poly add(Poly q) {
        Poly sum = new Poly();
        for (int i = 0; i < Math.max(this.degree(), q.degree()); i++) {
            int thisCoeff;
            int qCoeff;
            if (i >= this.degree()) {
                thisCoeff = 0;
            }
            else {
                thisCoeff = this.coeffs.get(i);
            }
            if (i >= q.degree()) {
                qCoeff = 0;
            }
            else {
                qCoeff = q.coeffs.get(i);
            }
            sum.coeffs.add(thisCoeff + qCoeff);
        }
        return sum;
    }

    // REQUIRES: q non null
    // EFFECTS: restituisce il polinomio differenza tra this e il polinomio q
    public Poly sub(Poly q) {
        Poly dif = new Poly();
        for (int i = 0; i < Math.max(this.degree(), q.degree()); i++) {
            int thisCoeff;
            int qCoeff;
            if (i >= this.degree()) {
                thisCoeff = 0;
            }
            else {
                thisCoeff = this.coeffs.get(i);
            }
            if (i >= q.degree()) {
                qCoeff = 0;
            }
            else {
                qCoeff = q.coeffs.get(i);
            }
            dif.coeffs.add(thisCoeff - qCoeff);
        }
        return dif;
    }

    // REQUIRES: q non null
    // EFFECTS: restituisce il polinomio prodotto tra this e il polinomio q
    public Poly mul(Poly q) {
        Poly prod = new Poly();
        for (int i = 0; i < degree(); i++) {
            for (int j = 0; j < q.degree(); j++) {
                int coeffProd = this.coeffs.get(i) * q.coeffs.get(j);
                int expProd = i+j;
                Poly subProd = new Poly(coeffProd, expProd);
                prod = prod.add(subProd);
            }    
        }
        return prod;
    }

    // EFFECTS: restituisce il polinomio opposto a this
    public Poly minus() {
        Poly minus = new Poly();
        for (int i = 0; i < this.degree(); i++) {
            int revCoeff = -(this.coeffs.get(i));
            int exp = i;
            minus = minus.add(new Poly(revCoeff, exp));
        }
        return minus;
    }

    // EFFECTS: restituisce il grado del polinomio
    public int degree() {
        if (coeffs.size() == 0) return 0;
        return coeffs.size();
    }
    
    // EFFECTS: restituisce il coefficiente del termine di grado d
    public int coeff(int d) {
        return coeffs.get(d);
    }

    // EFFECTS: restituisce una stringa che descrive il polinomio this
    //          in formato c1x^e1 + ... + cnx^en con c coefficiente ed e esponente
    @Override
    public String toString() {
        if (coeffs.size() == 0) {
            return "";
        }
        String str = "";
        if (coeffs.get(0) != 0) {
            str += coeffs.get(0) + "";
        }
        for (int e = 1; e < degree(); e++) {
            if (!str.equals("")) str += " + ";

            int c = coeffs.get(e);
            if (e == 1) {
                if (c == 0) continue;
                if (c == 1) str += "x";
                else str += c + "x";
            }
            else {
                if (c == 0) continue;
                if (c == 1) str += "x^" + e;
                else str += c + "x^" + e;
            }
        }
        return str;
    }

    private boolean repOk() {
        for (Object coeff : coeffs) {
            if (coeff == null) {
                return false;
            }
        }
        return true;
    }
}
