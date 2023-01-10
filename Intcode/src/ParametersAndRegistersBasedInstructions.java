public abstract class ParametersAndRegistersBasedInstructions extends ParametersBasedInstruction {
  
  Registers registers;

  public ParametersAndRegistersBasedInstructions(Memory.Location[] parameters, Registers registers) {
    super(parameters);
    this.registers = registers;
  }

}
