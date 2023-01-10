import java.util.Scanner;

public class Test {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    int p = in.nextInt();
    int q = in.nextInt();

    int[] vals = new int[(p+q)*2]; 
    for (int i = 0; i < vals.length; i++) {
      vals[i] = in.nextInt();
    }

    int index = 0;

    Poly firstPoly = new Poly();
    for (int i = 0; i < p; i++) {
      int c = vals[index];
      index++;
      int e = vals[index];
      index++;
      //System.out.println("poly of " + c + " " + e + " --> " + new Poly(c, e));
      firstPoly = firstPoly.add(new Poly(c,e));
    }

    Poly secondPoly = new Poly();
    for (int i = 0; i < q; i++) {
      int c = vals[index];
      index++;
      int e = vals[index];
      index++;
      //System.out.println("poly of " + c + " " + e + " --> " + new Poly(c, e));
      secondPoly = secondPoly.add(new Poly(c, e));
    }

    in.close();

    System.out.println("---");
    System.out.println("1st poly: " + firstPoly);
    System.out.println("2st poly: " + secondPoly);
    System.out.println("---");

    System.out.println("Sum: " + firstPoly.add(secondPoly));
    System.out.println("Sub: " + firstPoly.sub(secondPoly));
    System.out.println("Mul: " + firstPoly.mul(secondPoly));
    System.out.println("Inv1: " + firstPoly.minus());
    System.out.println("Inv2: " + secondPoly.minus());
  }
}
