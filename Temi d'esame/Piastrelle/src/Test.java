public class Test {
  public static void main(String[] args) {
    Piastrella piastrella;
    Piastrelle piastrelle;

    piastrella = new PiastrellaQuadrata(2, 3);
    piastrelle = new Piastrelle();
    piastrelle.addPiastrelle(piastrella, 42);

    Pavimentazione pavCucina = new Pavimentazione();
    pavCucina.addPavimentazione(piastrelle);


    piastrella = new PiastrellaRomboidale(4, 2, 5);
    piastrelle = new Piastrelle();
    piastrelle.addPiastrelle(piastrella, 65);

    Pavimentazione pavBagno = new Pavimentazione();
    pavBagno.addPavimentazione(piastrelle);


    Pavimentazione pavCasa = new Pavimentazione();

    pavCasa.addPavimentazione(pavCucina);
    System.out.println("cucina " + pavCucina);
    System.out.println("casa " + pavCasa);
    System.out.println();

    pavCasa.addPavimentazione(pavBagno);
    System.out.println("bagno " + pavBagno);
    System.out.println("casa " + pavCasa);
    System.out.println();

    pavCasa.addPavimentazione(pavBagno);
    System.out.println("bagno " + pavBagno);
    System.out.println("casa " + pavCasa);
    System.out.println();
    
    System.out.println(pavCasa);
  }
}
