package com.companies.lyft;

/*
  You have a function that reads 4KB chunks from a bus on a device.
  Using 'read4K', write a read function that can read arbitrary amount
  of data from the bus
 */
public class Read4KBChunks {

  private static final int FOUR_KB = 4096;

  public static int read4K(byte[] out) {
    return 0;
  }

  public static int read(byte[] out, int numByte) {
    byte[] buffer = new byte[FOUR_KB];

    int pages = (numByte / FOUR_KB);
    int readBytes = 0;

    for (int cur = 0; cur < pages; cur++) {
      int size = read4K(buffer);
      if (size > 0) {
        readBytes += size;
        copy(buffer, out, 0, cur * FOUR_KB, FOUR_KB);
      } else {
        return readBytes;
      }
    }

    if (readBytes < numByte) {
      int size = read4K(buffer);
      if (size > 0) {
        copy(buffer, out, 0, readBytes, numByte - readBytes);
      } else {
        return readBytes;
      }
    }

    return numByte;
  }

  private static void copy(byte[] src, byte[] dest, int srcStart, int destStart, int numBytes) {
    // copy numBytes from src to dest
  }
}
