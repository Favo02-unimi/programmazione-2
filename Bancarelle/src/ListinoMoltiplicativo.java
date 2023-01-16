import java.util.Objects;

public class ListinoMoltiplicativo extends ListinoAbstract {

  @Override
  public double getPrezzoTotale(Giocattolo giocattolo, int quantita) throws NullPointerException, IllegalArgumentException {
    if (!(this.contains(Objects.requireNonNull(giocattolo)))) {
      throw new IllegalArgumentException("Questo listino non contiene questo giocattolo");
    }

    return getPrezzoUnitario(giocattolo) * quantita;
  }


}
