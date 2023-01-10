public enum Mode {
  POSITION(0),
  IMMEDIATE(1),
  RELATIVE(2);

  private final int code;

  private Mode(int code) {
    this.code = code;
  }

  public static Mode fromCode(int mode) {
    for (Mode m : values())
      if (m.code == mode)
        return m;

    throw new IllegalArgumentException("Invalid access mode: " + mode);
  }
}
