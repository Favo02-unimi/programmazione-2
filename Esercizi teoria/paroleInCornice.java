import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class paroleInCornice {

  public static void main(String[] args) {
    List<String> parole = new ArrayList<String>();

    String align = args[0];
    
    Scanner in = new Scanner(System.in);
    System.out.println("Inserire parole terminate da -1");
    int maxLength = 0;
    while (in.hasNext()) {
      String parola = in.next();
      if (parola.equals("-1")) {
        break;
      }
      if(parola.length() > maxLength) {
        maxLength = parola.length();
      }
      parole.add(parola);
    }
    in.close();

    // calculate "vertical" words if vertical alignment
    if (align.equals("V")) {
      List<String> paroleVert = new ArrayList<String>();
      int newMaxLenght = parole.size()*2;

      for (int i = 0; i < maxLength; i++) {
        String parolaOriz = "";
        for (String parola : parole) {

          if (parola.length() > i) {
            parolaOriz += parola.charAt(i) + " ";
          }
          else {
            parolaOriz += "  ";
          }

        }
        paroleVert.add(parolaOriz);
      }
      parole = paroleVert;
      maxLength = newMaxLenght;
    }

    for (int i = 0; i < maxLength+4; i++) {
      System.out.print("*");
    }
    System.out.println();
    for (String parola : parole) {
      System.out.print("* ");
      switch(align) {
        case "L":
          System.out.print(parola);
          for (int i = 0; i < (maxLength-parola.length()); i++) {
            System.out.print(" ");
          }
          break;
        case "R":
          for (int i = 0; i < (maxLength-parola.length()); i++) {
            System.out.print(" ");
          }
          System.out.print(parola);
          break;
        case "C":
          for (int i = 0; i < (maxLength-parola.length())/2; i++) {
            System.out.print(" ");
          }
          System.out.print(parola);
          for (int i = 0; i < (maxLength-parola.length())/2; i++) {
            System.out.print(" ");
          }
          break;
        case "V":
          System.out.print(parola);
          break;
      }
      System.out.println(" *");

    }
    for (int i = 0; i < maxLength+4; i++) {
      System.out.print("*");
    }
  }
}
