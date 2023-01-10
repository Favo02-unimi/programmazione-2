import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    List<Piastrellabile> piastrellature = new ArrayList<>();

    while (in.hasNext()) {
      switch (in.next()) {
      case "Q":
        piastrellature.add(new PiastrellaQuadrata(in.nextInt(), in.nextInt()));
        break;
      case "R":
        piastrellature.add(new PiastrellaRomboidale(in.nextInt(), in.nextInt(), in.nextInt()));
        break;
      case "T":
        piastrellature.add(new PiastrellaTriangolare(in.nextInt(), in.nextInt(), in.nextInt()));
        break;
      case "P":
        Pavimentazione p = new Pavimentazione();
        while (in.hasNextInt()) {
          int qty = in.nextInt();
          int index = in.nextInt();
          p.addPiastrellabile(piastrellature.get(index), qty);
        }
        piastrellature.add(p);
        break;
      }
    }
    in.close();
    
    for (Piastrellabile p : piastrellature) {
      if (p instanceof Pavimentazione) {
        System.out.println(p.getSuperficie() + "\t" + p.getCosto());
      }
    }

  }

}
