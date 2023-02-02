import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {

        List<ElementoChimico> elementi = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        int numero = 1;
        while(in.hasNextLine()) {
            String line = in.nextLine();
            String[] tokens = line.split("\t");
            Double peso = Double.parseDouble(tokens[2]);
            ElementoChimico e = new ElementoChimico(numero, tokens[0], tokens[1], peso);
            elementi.add(e);
            numero++;
        }
        in.close();

        TavolaPeriodica tavola = new TavolaPeriodica(elementi);

        String[] formula = Helpers.parseFormula(args[0]);

        Molecola molecola;

        if (formula.length > 2) { // molecola composta
            String[] elems = new String[formula.length/2];
            int[] qtys = new int[formula.length/2];
            
            int arraysIndex = 0;
            for (int i = 0; i < formula.length; i+=2) {
                elems[arraysIndex] = formula[i]; 
                qtys[arraysIndex] = Integer.parseInt(formula[i+1]); 
                arraysIndex++;
            }

            molecola = new MolecolaComposta(tavola, elems, qtys);
        } else { // molecola semplice
            int qty = Integer.parseInt(formula[1]);
            molecola = new MolecolaSemplice(tavola, formula[0], qty);
        }

        System.out.println(molecola);

        Iterator<ElementoChimico> it = molecola.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }

    }
}