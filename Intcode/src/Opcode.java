public enum Opcode {
  ADD(1, 3){
    @Override
    public Instruction toInstruction(Memory.Location[] parameters, Registers registers) {
      return new Add(parameters);
    }
  },
  //MUL,
  //...
  ;
  
  private final int code;
  final int nParams;

  private Opcode(int code, int nParams) {
    this.code = code;
    this.nParams = nParams;
  } 

  public static Opcode fromCode(int code) {
    for (Opcode o : values()) {
      if (o.code == code) return o;
    }
    throw new IllegalArgumentException("Invalid opcode: " + code);
  } 

  public abstract Instruction toInstruction(Memory.Location[] parameters, Registers registers);
}
