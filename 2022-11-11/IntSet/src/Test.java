import java.util.Scanner;

public class Test {
  public static void main(String[] args) {
    IntSet intset = new IntSet();
    
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      intset.add(in.nextInt());
    }
    in.close();

    System.out.println("size: " + intset.size());
  }
}
