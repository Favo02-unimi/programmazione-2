public record Brano(Album album, int posizione) implements Ascoltabile {

  @Override
  public String nome() {
    return album.getBrano(posizione).nome();
  }

  @Override
  public int durata() {
    return album.getBrano(posizione).durata();
  }
  
}
