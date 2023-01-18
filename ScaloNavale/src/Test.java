public class Test {
    public static void main(String[] args) {
        Scalo scalo = new Scalo(5);
        Rimorchiatore rim = new RimorchiatoreMini("rim", scalo);
        scalo.setRimorchiatore(rim);
        //Scalo scalo = new Scalo(5, new RimorchiatoreSuper());
        
        scalo.attracca(new NaveCargo("a", 10), 1);
        
        scalo.attracca(new NaveCargo("b", 100), 1);
    
        scalo.attracca(new NaveCargo("c", 100), 1);

        scalo.sposta(3, 1, 2);

        System.out.println(scalo);

    }
}