public record Brano(Album album, int posizione) implements Ascoltabile {

  @Override
  public String nome() {
    return album.getNomeBrano(posizione);
  }

  @Override
  public int durata() {
    return album.getDurataBrano(posizione);
  }
  
}
