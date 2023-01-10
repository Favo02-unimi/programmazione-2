import java.util.Scanner;

public class Rychler  {
    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        long n = in.nextLong();
        printLychrelSequence(n);
        in.close();
    }

    // EFFECTS: restituisce la stringa ottenuta invertendo l'ordine dei caratteri in s
    public static String reversal(String s) {
        if (s.length() <= 1) return s;
        return s.charAt(s.length()-1) + reversal(s.substring(1, s.length()-1)) + s.charAt(0);
    }

    // EFFECTS: restituisce true se s è palindroma
    public static boolean isPalindrome(String s) {
        if (s.length() <= 1) return true;
        return s.charAt(0) == s.charAt(s.length()-1) && isPalindrome(s.substring(1, s.length()-1));
    }

    // EFFECTS: restituisce una stringa contentente la rappresentazione di n
    public static String numToString(long n) {
        return n + "";
    }

    // REQUIRES: s deve essere contenere solo da caratteri numerici o '-' (in testa)
    // EFFECTS: restituisce un long che rappresenta il numero s
    public static long StringToNum(String s) {
        long n = 0;
        for (int i = 0; i < s.length(); i++) {
            n *= 10;
            // sottraggo al numero che identifica il carattere trovato in posizione i
            // il numero del carattere 0, trovando il numero effettivo
            n += s.charAt(i) - '0';
        }
        return n;
    }

    // REQUIRES: n deve essere positivo e non deve essere un numero di Rychrel
    // MODIFIES: System.out
    // EFFECTS: stampa su System.out la sequenza di Lychrel a partire da n
    public static void printLychrelSequence(long n) {
        while(!isPalindrome(numToString(n))) {
            System.out.println(n);
            n += StringToNum(reversal(numToString(n)));
        }
        System.out.println(n);
    }

}
