import java.util.List;

public class IntCodeVM {
  Registers registers;
  Memory memory;

  public IntCodeVM(List<Integer> program) {
    memory = new Memory(program);
    registers = new Registers();
  }

  void run() {
    boolean running;
    do {
      int rawInstruction = fetch(registers.ip);
      Instruction instruction = decode(rawInstruction);
      running = !execute(instruction);
    } while (running);
  }

  private int fetch(int ip) {
    return memory.get(ip);
  }

  private Instruction decode(int rawInstruction) {
    Opcode op = Opcode.fromCode(rawInstruction%100);
    Memory.Location[] parameters = memory.prepareLocations(op.nParams, rawInstruction / 100, registers);
    registers.ip += 1 + op.nParams;
    return op.toInstruction(parameters, registers);
  }

  private boolean execute(Instruction instruction) {
    instruction.execute();
    return instruction.isHalting();
  }
}
