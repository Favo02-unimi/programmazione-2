package Vetreria;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    Vetreria vetreria = new Vetreria();

    // insieme per memorizzare i vari tipi di liquidi (no duplicati)
    HashSet<String> liquidi = new HashSet<>(); 

    while (scanner.hasNext()) {
      String liquido = scanner.next();
      liquidi.add(liquido);
      double quantita = scanner.nextDouble();

      Contenitore contenitore = null;
      String tipo = scanner.next();

      try {
        switch (tipo) {
          case "Sfera":
            contenitore = new Sfera(liquido, quantita, scanner.nextDouble());
            break;
          case "Cuboide":
            contenitore = new Cuboide(liquido, quantita, scanner.nextDouble(), scanner.nextDouble(), scanner.nextDouble());
            break;
          case "Cilindro":
            contenitore = new Cilindro(liquido, quantita, scanner.nextDouble(), scanner.nextDouble());
            break;
        }
      } catch (ExceededCapacityException e) {
        System.out.println(e.getMessage());
        System.exit(1);
      }

      vetreria.add(contenitore);
    }

    scanner.close();

    System.out.println(vetreria);

    ArrayList<Vetreria> vetrerie = new ArrayList<>();

    for (String liq : liquidi)
      vetrerie.add(vetreria.estrai(liq));

    for (Vetreria v : vetrerie) {
      v.ottimizza(); // ottimizza la sorta anche
      System.out.println(v);
    }

  }
}
