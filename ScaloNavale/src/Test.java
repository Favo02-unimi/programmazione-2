import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int numMoli = in.nextInt();
        Scalo scalo = new Scalo(numMoli);

        for (int i = 0; in.hasNextLine(); i++) {
            String line = in.nextLine();
            if (line.isBlank()) {
                i--;
                continue;
            }
            String[] tokens = line.split(" ");
            for (int j = 0; j < tokens.length; j+=2 ) {
                scalo.attracca(new NaveCargo(tokens[j], Integer.parseInt(tokens[j+1])), i);
            }
        }
        in.close();
        
        System.out.print(scalo);

        int qty = Integer.parseInt(args[0]);
        int from = Integer.parseInt(args[1]);
        int to = Integer.parseInt(args[2]);

        Rimorchiatore rim;
        switch (args[3]) {
        case "mini":
            rim = new RimorchiatoreMini("rim", scalo);
            break;    
        case "super":
            rim = new RimorchiatoreSuper("rimsuper", scalo);
            break;
        case "prudente":
            rim = new RimorchiatorePrudente("rimprudente", scalo, Integer.parseInt(args[4]));
            break;
        default:
            rim = new RimorchiatoreMini("temp", scalo);
            break;
        }
        int steps = rim.sposta(qty, from, to);

        System.out.println(steps);
        System.out.print(scalo);
    }
}
