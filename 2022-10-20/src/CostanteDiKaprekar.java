import java.util.Scanner;

public class CostanteDiKaprekar {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.print("inserire numero di 4 cifre di partenza: ");
    int n = in.nextInt();
    in.close();
    stampaSequenzaKaprekar(n);
  }

  // REQUIRES: n positivo di 4 cifre, con almeno 2 cifre diverse
  // MODIFIES: System.out
  // EFFECTS: stampa la sequenza di trasformazioni di Kaprekar da n fino ad arrivare alla costante di Kaprekar
  public static void stampaSequenzaKaprekar(int n) {
    while (n != 6174) {
      System.out.println(n);
      n = stepKaprekar(n);
    }
    System.out.println(n);
  }

  // REQUIRES: n positivo con almeno 2 cifre diverse
  // MODIFIES: -
  // EFFECTS: trasforma un numero n in un altro numero sottraendo le sue cifre ordinate dalla più grande alla più piccola con le sue cifre ordinate dalla più piccola alla più grande
  public static int stepKaprekar(int n) {
    return ordinaCifreNumero(n, true) - ordinaCifreNumero(n, false);
  }

  // REQUIRES: n positivo di 4 cifre
  // MODIFIES: -
  // EFFECTS: restituisce un numero composto dalle cifre di n ordinate in modo crescente se crescente = true e in modo descrescente se crescente = false
  public static int ordinaCifreNumero(int n, boolean decrescente) {
    int c1, c2, c3, c4;
    c1 = (n/1000)%10;
    c2 = (n/100)%10;
    c3 = (n/10)%10;
    c4 = n%10;

    int m1, m2, m3, m4;

    if (decrescente) {
      m1 = Math.max(c1, c2);
      m2 = Math.min(c1, c2);
    }
    else {
      m1 = Math.min(c1, c2);
      m2 = Math.max(c1, c2);
    }

    c1 = m1;
    c2 = m2;

    if (decrescente) {
      m3 = Math.max(c3, c4);
      m4 = Math.min(c3, c4);
    }
    else {
      m3 = Math.min(c3, c4);
      m4 = Math.max(c3, c4);
    }

    c3 = m3;
    c4 = m4;

    if (decrescente) {
      m1 = Math.max(c1, c3);
      m3 = Math.min(c1, c3);
    }
    else {
      m1 = Math.min(c1, c3);
      m3 = Math.max(c1, c3);
    }

    c1 = m1;
    c3 = m3;

    if (decrescente) {
      m2 = Math.max(c2, c4);
      m4 = Math.min(c2, c4);
    }
    else {
      m2 = Math.min(c2, c4);
      m4 = Math.max(c2, c4);
    }

    c2 = m2;
    c4 = m4;

    if (decrescente) {
      m2 = Math.max(c2, c3);
      m3 = Math.min(c2, c3);
    }
    else {
      m2 = Math.min(c2, c3);
      m3 = Math.max(c2, c3);
    }

    c2 = m2;
    c3 = m3;

    return c1 * 1000 + c2 * 100 + c3 * 10 + c4;
  }

}
