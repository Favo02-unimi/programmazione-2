import java.util.Scanner;

public class Test {
  public static void main(String[] args) {
    SistemaAstronomico sistema = new SistemaAstronomico();

    Scanner s = new Scanner(System.in);
    while (s.hasNext()) {
      char pOrS = s.next().charAt(0);
      String name = s.next();
      int x = s.nextInt();
      int y = s.nextInt();
      int z = s.nextInt();
      if (pOrS == 'P') {
        sistema.aggiungi(new Pianeta(name, x, y, z));
      } else if (pOrS == 'S') {
        sistema.aggiungi(new StellaFissa(name, x, y, z));
      }
    }
    s.close();

    sistema.simula(1);
    System.out.println(sistema);
  }
}
