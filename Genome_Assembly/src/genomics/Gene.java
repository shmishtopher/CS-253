package genomics;


public class Gene {
  private byte value;

  public Gene(byte value) {
    this.value = value;
  }

  public String toString() {
    if (value == 0x00) return "A";
    if (value == 0x01) return "C";
    if (value == 0x02) return "T";
    if (value == 0x03) return "G";
    else return "N";
  }

  public static String toString(byte value) {
    if (value == 0x00) return "A";
    if (value == 0x01) return "C";
    if (value == 0x02) return "T";
    if (value == 0x03) return "G";
    else return "N";
  }
}