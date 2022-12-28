import java.util.Scanner;

public class DisegnaV {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.print("Numero righe: ");
    int n = in.nextInt();
    in.close();
    disegnaV(n);
  }

  // REQUIRES: numero >= 0
  // MODIFIES: System.out
  // EFFECTS: stampa una V composta da 'righe' righe
  public static void disegnaV(int righe) {
    for (int i = 1; i <= righe; i++) {
      stampaSpazi(i);
      stampaAsterisco();
      if (i < righe) {
        stampaSpazi(((righe-i)*2) -1);
        stampaAsterisco();
      }
      stampaACapo();
    }
  }

  // REQUIRES: numero >= 0
  // MODIFIES: System.out
  // EFFECTS: stampa n spazi
  public static void stampaSpazi(int n) {
    for (int i = 0; i < n; i++) {
      System.out.print(" ");
    }
  }

  // REQUIRES: -
  // MODIFIES: System.out
  // EFFECTS: stampa un asterisco
  public static void stampaAsterisco() {
    System.out.print("*");
  }

  // REQUIRES: -
  // MODIFIES: System.out
  // EFFECTS: stampa un a capo
  public static void stampaACapo() {
    System.out.println();
  }
}
