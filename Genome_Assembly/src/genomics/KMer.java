package genomics;


import java.util.Arrays;
import debruijn.Splittable;


public class KMer implements Splittable<KMer> {
  private byte[] buffer;
  private int length;


  public KMer(int k) {
    this.buffer = new byte[k / 4 + 1];
    this.length = 0;
  }


  public static KMer from(String string) {
    KMer kmer = new KMer(string.length());
    string.chars().forEach(c -> {
      if (c == 'A') kmer.append((byte) 0x00);
      if (c == 'C') kmer.append((byte) 0x01);
      if (c == 'T') kmer.append((byte) 0x02);
      if (c == 'G') kmer.append((byte) 0x03);
    });

    return kmer;
  }


  public String toString() {
    String string = new String();

    for (int i = 0; i < length; i += 1) {
      string += Gene.toString(get(i));
    }

    return string;
  }


  public void append(byte gene) {
    byte segment = buffer[length / 4];
    byte shift = (byte) (length % 4 * 2);

    buffer[length / 4] = (byte) (segment | gene << shift);
    length += 1;
  }


  public byte get(int offset) {
    if (offset < length) {
      byte segment = buffer[offset / 4];
      byte shift = (byte) (offset % 4 * 2);

      return (byte) ((segment & 0x03 << shift) >> shift);
    }
    else {
      throw new IndexOutOfBoundsException();
    }
  }


  public int hashCode() {
    return Arrays.hashCode(buffer) ^ length;
  }


  public boolean equals(Object obj) {
    return hashCode() == obj.hashCode();
  }


  public KMer getPrefix() {
    KMer prefix = new KMer(length - 1);

    for (int i = 0; i < length - 1; i += 1) {
      prefix.append(get(i));
    }

    return prefix;
  }


  public KMer getSuffix() {
    KMer suffix = new KMer(length  -1);

    for (int i = 1; i < length; i += 1) {
      suffix.append(get(i));
    }

    return suffix;
  }


  public int size() {
    return length;
  }
}