public abstract class ParametersBasedInstruction implements Instruction {
  Memory.Location[] parameters;

  public ParametersBasedInstruction(Memory.Location[] parameters) {
    this.parameters = parameters;
  }

  @Override
  public boolean isHalting() {
    return false;
  }

}
