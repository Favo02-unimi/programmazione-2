public record Brano(Album album, int posizione) implements Ascoltabile {

  /**
   * Crea un nuovo Brano estratto da un album in base alla sua posizione
   * 
   * @param album da cui estrarre il brano
   * @param posizione del brano da estrarre (rispetto all'album)
   * @throws NullPointerException se album è null
   * @throws IndexOutOfBoundsException se la posizione è invalida rispetto all'album
   */
  public Brano(Album album, int posizione) {
    if (album == null) {
      throw new NullPointerException("L'album del brano non può essere null");
    }

    try {
      album.getBrano(posizione);
    } catch (IndexOutOfBoundsException e) {
      throw new IndexOutOfBoundsException("La posizione del brano deve essere valida all'interno dell'album");
    }

    this.album = album;
    this.posizione = posizione;
  }

  @Override
  public String nome() {
    return album.getNomeBrano(posizione);
  }

  @Override
  public int durata() {
    return album.getDurataBrano(posizione);
  }

  /**
   * RI:
   *  album != null
   *  posizione valida in album
   */
  private boolean repOk() {
    if (album == null) return false;
    try {
      album.getBrano(posizione);
    } catch (IndexOutOfBoundsException e) {
      return false;
    }

    return true;
  }
  
}
