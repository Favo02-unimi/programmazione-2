import java.util.Scanner;

public class DisegnaRombo {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.print("grandezza rombo (diagonale = 2*n +1): ");
    int n = in.nextInt();
    in.close();
    stampaRombo(n);
  }

  // REQUIRES: numero n positivo
  // MODIFIES: System.out
  // EFFECTS: stampa n spazi
  public static void stampaSpazi(int n) {
    for (int i = 0; i < n; i++) {
      System.out.print(" ");
    }
  }

  // REQUIRES: numero n positivo
  // MODIFIES: System.out
  // EFFECTS: stampa n asterischi
  public static void stampaAsterischi(int n) {
    for (int i = 0; i < n; i++) {
      System.out.print("*");
    }
  }

  // REQUIRES: numero n positivo
  // MODIFIES: System.out
  // EFFECTS: stampa un rombo di diagonale 2*n +1
  public static void stampaRombo(int n) {
    for (int i = 0; i < n; i++) {
      stampaSpazi(n-i);
      stampaAsterischi(i*2+1);
      System.out.println();
    }
    for (int i = n; i >= 0; i--) {
      stampaSpazi(n-i);
      stampaAsterischi(i*2+1);
      System.out.println();
    }
  }
}
