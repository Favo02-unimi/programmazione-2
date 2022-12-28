import java.util.Scanner;

public class DisegnaScacchiera {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.print("grandezza ogni riga/colonna: ");
    int n = in.nextInt();
    in.close();
    disengaScacchiera(n);
  } 
  
  // REQUIRES: numero n positivo
  // MODIFIES: System.out
  // EFFECTS: stampa una riga di lunghezza n * 8 e altezza 1, con prime n parti bianche se isBianca è true
  public static void disegnaRiga(int n, boolean isBianca) {
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < n; j++) {
        if (isBianca) {
          System.out.print("-");
        }
        else {
          System.out.print("#");
        }
      }
      isBianca = !isBianca;
    }
  }

  // REQUIRES: numero n positivo
  // MODIFIES: System.out
  // EFFECTS: stampa una scacchiera da 8*8 caselle, di cui ogni casella composta da n*n parti
  public static void disengaScacchiera(int n) {
    boolean isBianca = true;
    for (int i = 0; i < 8; i++) {
      for (int j = 0; j < n; j++) {
        disegnaRiga(n, isBianca);
        System.out.println();
      }
      isBianca = !isBianca;
    }
  }  
}
