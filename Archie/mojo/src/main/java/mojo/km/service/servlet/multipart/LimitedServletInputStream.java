// Copyright (C) 1999 by Jason Hunter <jhunter@acm.org>.  All rights reserved.
// Use of this class is limited.  Please see the LICENSE for more information.
 
package mojo.km.service.servlet.multipart;

import java.io.IOException;

import javax.servlet.ServletInputStream;

/**
 * A <code>LimitedServletInputStream</code> wraps another 
 * <code>ServletInputStream</code> in order to keep track of how many bytes 
 * have been read and detect when the Content-Length limit has been reached. 
 * This is necessary since some servlet containers are slow to notice the end 
 * of stream and cause the client code to hang if it tries to read past it.
 * 
 * @author Jason Hunter
 * @author Geoff Soutter
 * @version 1.0, 2000/10/27, initial revision
 * @modelguid {B7F98F01-802A-4CF5-AA4F-CD6728D039C1}
 */
public class LimitedServletInputStream extends ServletInputStream {
  
  /** input stream we are filtering 
   * @modelguid {62073DF5-9A9F-4272-8D6B-9574A9E97799}
   */
  private ServletInputStream in;
  
  /** number of bytes to read before giving up 
   * @modelguid {C252A366-1696-4572-BDE8-768187E30366}
   */
  private int totalExpected;
  
  /** number of bytes we have currently read 
   * @modelguid {4ED14829-0D53-4B0E-9844-647EF7D9373D}
   */
  private int totalRead = 0;
  
  /**
   * Creates a <code>LimitedServletInputStream</code> with the specified
   * length limit that wraps the provided <code>ServletInputStream</code>.
   * @modelguid {02F014FD-4B27-46F6-82AB-D9A9C00F52E3}
   */
  public LimitedServletInputStream(ServletInputStream in, int totalExpected) {
    this.in = in;
    this.totalExpected = totalExpected;
  }

  /**
   * Implement length limitation on top of the <code>readLine</code> method of
   * the wrapped <code>ServletInputStream</code>.
   *
   * @param b    an array of bytes into which data is read.
   * @param off  an integer specifying the character at which
   *        this method begins reading.
   * @param len  an integer specifying the maximum number of 
   *        bytes to read.
   * @return     an integer specifying the actual number of bytes 
   *        read, or -1 if the end of the stream is reached.
   * @exception  IOException  if an I/O error occurs.
   * @modelguid {CE807D6A-357A-4C76-B1F0-0F3BFE95AF03}
   */
  public int readLine(byte b[], int off, int len) throws IOException {
    int result, left = totalExpected - totalRead;
    if (left <= 0) {
      return -1;
    } else {
      result = ((ServletInputStream)in).readLine(b, off, Math.min(left, len));
    }
    if (result > 0) {
      totalRead += result;
    }
    return result;    
  }

  /**
   * Implement length limitation on top of the <code>read</code> method of 
   * the wrapped <code>ServletInputStream</code>.
   *
   * @return     the next byte of data, or <code>-1</code> if the end of the
   *             stream is reached.
   * @exception  IOException  if an I/O error occurs.
   * @modelguid {84476EC7-62C2-4222-BB46-78F89A9002C6}
   */
  public int read() throws IOException {
    if (totalRead >= totalExpected) {
      return -1;
    }
    return in.read();
  }
  
  /**
   * Implement length limitation on top of the <code>read</code> method of 
   * the wrapped <code>ServletInputStream</code>.
   *
   * @param      b     destination buffer.
   * @param      off   offset at which to start storing bytes.
   * @param      len   maximum number of bytes to read.
   * @return     the number of bytes read, or <code>-1</code> if the end of
   *             the stream has been reached.
   * @exception  IOException  if an I/O error occurs.
   * @modelguid {6404667A-3A5D-4FE9-AA9F-390A6EB542F4}
   */
  public int read( byte b[], int off, int len ) throws IOException {
    int result, left = totalExpected - totalRead;
    if (left <= 0) {
      return -1;
    } else {
      result = in.read(b, off, Math.min(left, len));
    }
    if (result > 0) {
      totalRead += result;
    }
    return result;    
  }
}
