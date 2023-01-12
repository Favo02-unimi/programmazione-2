import java.util.Scanner;

public class Test {
  public static void main(String[] args) {
    
    MultiSet<String> a = new MapMultiSet<>();
    MultiSet<String> b = new ListMultiSet<>();

    Scanner in = new Scanner(System.in);
    
    for (int lineIndex = 0; in.hasNextLine(); lineIndex++) {
      String line = in.nextLine();
      String[] elems = line.split(" ");
      for (String el : elems) {
        if (lineIndex % 2 == 0) {
          a.add(el);
        } else {
          b.add(el);
        }
      }
    }
    in.close();

    System.out.println(a);
    System.out.println(b);
    System.out.println(a.union(b));
    System.out.println(a.intersection(b));

  }
}
