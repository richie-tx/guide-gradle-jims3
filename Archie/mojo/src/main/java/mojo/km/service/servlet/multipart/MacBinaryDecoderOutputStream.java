// Copyright (C) 1999 by Jason Hunter <jhunter@acm.org>.  All rights reserved.
// Use of this class is limited.  Please see the LICENSE for more information.

package mojo.km.service.servlet.multipart;

import java.io.OutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;

/**
 * A <code>MacBinaryDecoderOutput</code> filters MacBinary files to normal 
 * files on the fly; optimized for speed more than readability.
 * 
 * @author Jason Hunter
 * @modelguid {5D5D5FB7-2E85-4892-8727-D241CF93F253}
 */
public class MacBinaryDecoderOutputStream extends FilterOutputStream {
	/** @modelguid {B9512030-D73E-4EA0-BF77-19291671F1B8} */
  private int bytesFiltered = 0;
	/** @modelguid {B054937E-345A-4686-A00B-13B5172E1C45} */
  private int dataForkLength = 0;

	/** @modelguid {953A18A0-EC2E-4E39-AF0D-97721AD0A9E5} */
  public MacBinaryDecoderOutputStream(OutputStream out) {
    super(out);
  }

	/** @modelguid {B1D03A6F-688F-401E-9A01-3847C2A71601} */
  public void write(int b) throws IOException {
    // Bytes 83 through 86 are a long representing the data fork length
    // Check <= 86 first to short circuit early in the common case
    if (bytesFiltered <= 86 && bytesFiltered >= 83) {
      int leftShift = (86 - bytesFiltered) * 8;
      dataForkLength = dataForkLength | (b & 0xff) << leftShift;
    }

    // Bytes 128 up to (128 + dataForkLength - 1) are the data fork
    else if (bytesFiltered < (128 + dataForkLength) && bytesFiltered >= 128) {
      out.write(b);
    }

    bytesFiltered++;
  }

	/** @modelguid {0B0D420D-793B-4F78-BC20-D2E704228DA9} */
  public void write(byte b[]) throws IOException {
    write(b, 0, b.length);
  }

	/** @modelguid {81E20B1A-A932-43CD-89B1-49AB79E3BF68} */
  public void write(byte b[], int off, int len) throws IOException {
    // If the write is for content past the end of the data fork, ignore
    if (bytesFiltered >= (128 + dataForkLength)) {
      bytesFiltered += len;
    }
    // If the write is entirely within the data fork, write it directly
    else if (bytesFiltered >= 128 && 
             (bytesFiltered + len) <= (128 + dataForkLength)) {
      out.write(b, off, len);
      bytesFiltered += len;
    }
    // Otherwise, do the write a byte at a time to get the logic above
    else {
      for (int i = 0 ; i < len ; i++) {
        write(b[off + i]);
      }
    }
  }
}
