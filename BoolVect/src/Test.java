public class Test {
  public static void main(String[] args) {
    BoolVect b1 = new BoolVectDense("FFVF");
    BoolVect b2 = new BoolVectDense("VFFV");
    System.out.println(b1);

    b1.add(false);
    b1.add(true, 10);

    System.out.println(b1);
    System.out.println(b1.or(b2));
    System.out.println(b2.or(b1));

    System.out.println();

    BoolVect b3 = new BoolVectSparse();
    b3.add(true, 10);
    b3.add(true, 5);
    b3.add(true, 24);
    System.out.println(b3);

    BoolVect b4 = new BoolVectSparse();
    b4.add(true, 10);
    b4.add(true, 2);
    b4.add(true, 22);
    System.out.println(b4);

    System.out.println();
    System.out.println(b3.and(b4));
    System.out.println(b3.or(b4));
    System.out.println(b3.xor(b4));
    

  }
}
