public class Halt implements Instruction {

  @Override
  public void execute() { }

  @Override
  public boolean isHalting() {
    return true;
  }
  
}
