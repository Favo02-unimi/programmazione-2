import java.util.Scanner;

public class ProvaDel9 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("Inserire numero n:");
    int n = in.nextInt();
    trovaNumeri(n);
    in.close();
  }

  // REQUIRES: -
  // MODIFIES: -
  // EFFECTS: restituisce la somma delle cifre di n
  public static int sommaCifre(int n) {
    int res = 0;
    while (n/10 > 0) {
      res += n%10;
      n /= 10;
    }
    res += n%10;
    return res;
  }

  // REQUIRES: -
  // MODIFIES: -
  // EFFECTS: restituisce il valore della prova del nove calcolato su un singolo numero
  public static int provaDel9(int n) {
    while (n/10 > 0) {
      n = sommaCifre(n);
    }
    return n;
  }

  // REQUIRES: numero n positivo
  // MODIFIES: System.out
  // EFFECTS: stampa tutte le terne (a, b, c) di numeri con:
  //            a, b, c < n
  //            a * b != c
  //            prova del nove risulta corretta
  public static void trovaNumeri(int n) {
    for (int a = 1; a < n; a++) {
      for (int b = 1; b < n; b++) {
        for (int c = 1; c < n; c++) {
          if (a * b == c) {
            continue;
          }
          if (provaDel9(provaDel9(a) * provaDel9(b)) == provaDel9(c)) {
            System.out.println(a + " " + b + " " + c);
          }
        }
      }
    }
  }


}
