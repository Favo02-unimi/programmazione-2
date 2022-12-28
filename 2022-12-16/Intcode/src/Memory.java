import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Memory {
  private final List<Integer> memoryCells; // non è possibile cambiare (riferimento alla) lista, ma è possibile
                                           // modificare la lista

  Memory(List<Integer> program) {
    memoryCells = new ArrayList<>(program); // costruiamo memoria con dentro il programma iniziale
  }

  private void set(int address, int value) {
    if (address < 0)
      throw new IllegalArgumentException("Invalid memory address: " + address);

    int size = address - memoryCells.size() +1;
    if (size > 0)
      memoryCells.addAll(Collections.nCopies(size, 0));
    memoryCells.set(address, value);
  }

  int get(int address) {
    if (address < 0)
      throw new IllegalArgumentException("Invalid memory address: " + address);
    if (address < memoryCells.size()) 
      return memoryCells.get(address);
    return 0;
  }

  public Location[] prepareLocations(int nParams, int parameterModes, Registers registers) {
    Location[] parameters = new Location[nParams];

    for (int i = 0; i < nParams; i++, parameterModes /= 10)
      parameters[i] = new Location(registers.ip, Mode.fromCode(parameterModes % 10), registers.rbp);

    return parameters;
  }

  class Location {
    private final Mode mode;
    private final int rbp;
    private final int address;

    Location(int address, Mode mode, int relativeBasePointer) {
      this.address = address;
      this.mode = mode;
      this.rbp = relativeBasePointer;
    }

    void write(int value) {
      if (mode == Mode.IMMEDIATE)
        throw new IllegalStateException("Can't write in IMMEDIATE mode");
      int contents = get(address);
      if (mode == Mode.RELATIVE)
        set((contents + rbp), value);
      set(contents, value); // Mode.POSITION
    }

    public int read() {
      int contents = get(address);
      if (mode == Mode.IMMEDIATE)
        return contents;
      if (mode == Mode.RELATIVE)
        return get(contents + rbp);
      return get(contents); // Mode.POSITION
    }
  }

}
