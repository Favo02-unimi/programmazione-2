import java.util.Arrays;
import java.util.Scanner;

public class CostanteDiKaprekar2 {

  static final short KAPREKAR_CONSTANT = 6174;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.print("numero di partenza: ");
    int n = in.nextInt();
    in.close();
    printKaprekarSequence((short)n);
  }

  // REQUIRES: 0 <= n < 10^digits, digits < 6
  // MODIFIES: -
  // EFFECTS: restituisce il riferimento ad un array conetenente le cifre decimali di n, preservandole l'ordine
  public static byte[] numToArray(short n, byte digits) {
    byte[] a = new byte[digits];

    for (int i = 0; i < digits; i++) {
      a[i] = (byte)(n%10);
      n /= 10;
    }
    
    return a;
  }

  // REQUIRES: a != null, ciascun elemento di a deve essere compreso da 0 e 9, il numero rappresentato non deve ssere maggiore di 2^16-1
  // MODIFIES: -
  // EFFECTS: restituisce il numero ottenuto giastapponendo le cifre contenute in a
  public static short digitArrayToNum(byte[] a) {
    int n = 0;
    for (int i = a.length-1; i >= 0; i--) {
      if (i != a.length-1) {
        n *= 10;
      }
      n += a[i];
    }
    return (short)n;
  }

  // REQUIRES: a != null
  // MODIFIES: a
  // EFFECTS: esegue un inversione in-place dell'ordine degli elementi di a
  public static void reverseArray(byte[] a) {
    for (int i = 0; i < a.length/2; i++) {
      byte tmp = a[i];
      a[i] = a[a.length-1-i];
      a[a.length-1-i] = tmp;
    }
  }

  // REQUIRES: 0 < n < 10000
  // MODIFIES: -
  // EFFECTS: restituisce il numero successivo della sequenza di Kaprekar
  public static short stepKaprekar(short n) {
    byte[] a = numToArray(n, (byte)4);
    Arrays.sort(a);
    short desc = digitArrayToNum(a);
    reverseArray(a);
    short asc = digitArrayToNum(a);
    short res = (short)(desc - asc);
    return res;
  }

  // REQUIRES: n è composto da almeno 2 cifre diverse
  // MODIFIES: System.out
  // EFFECTS: stampa su standard output la sequenza di Kaprekar a partire da n, un elemento per riga
  //            solleva IllegalArgumentException se n < 1 o n > 999
  
  public static void printKaprekarSequence(short n) {
    if (n < 0 || n > 9999) throw new IllegalArgumentException("Numero inserito non valido. Numeri validi da 1 a 999, inserito " + n);
    if (stepKaprekar(n) == 0) throw new IllegalArgumentException("Numero inserito non valido. Sono validi i numeri con almeno 2 cifre diverse, inserito " + n);

    while (n != KAPREKAR_CONSTANT) {
      System.out.println(n);
      n = stepKaprekar(n);
    }
    System.out.println(n);
  }
}
