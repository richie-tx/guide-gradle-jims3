// Copyright (C) 1999 by Jason Hunter <jhunter@acm.org>.  All rights reserved.
// Use of this class is limited.  Please see the LICENSE for more information.

package mojo.km.service.servlet.multipart;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import javax.servlet.ServletInputStream;

/**
 * A <code>ParamPart</code> is an upload part which represents a normal 
 * <code>INPUT</code> (i.e. non <code>TYPE="file"</code>) form parameter.
 * 
 * @author Geoff Soutter
 * @version 1.0, 2000/10/27, initial revision
 * @modelguid {9F188AC5-E50C-4FA0-8D3A-222143508790}
 */
public class ParamPart extends Part {
  
  /** contents of the parameter 
   * @modelguid {13CE366D-F359-4451-AC9F-7FC931CCE01C}
   */
  private byte[] value;

  /**
   * Constructs a parameter part; this is called by the parser.
   * 
   * @param name the name of the parameter.
   * @param in the servlet input stream to read the parameter value from.
   * @param boundary the MIME boundary that delimits the end of parameter value.
   * @modelguid {B5B9AF1D-F25D-4288-B845-355583675480}
   */
  ParamPart(String name, ServletInputStream in, 
            String boundary) throws IOException {
    super(name);

    // Copy the part's contents into a byte array
    PartInputStream pis = new PartInputStream(in, boundary);
    ByteArrayOutputStream baos = new ByteArrayOutputStream(512);
    byte[] buf = new byte[128];
    int read;
    while ((read = pis.read(buf)) != -1) {
      baos.write(buf, 0, read);
    }
    pis.close();
    baos.close();
    
    // save it for later
    value = baos.toByteArray();
  }

  /** 
   * Returns the value of the parameter as an array of bytes or a zero length 
   * array if the user entered no value for this parameter.
   * 
   * @return value of parameter as a ISO-8559-1 string.
   * @modelguid {D4190453-ED21-408F-9768-11359855AEC3}
   */
  public byte [] getValue() {
    return value;
  }
  
  /** 
   * Returns the value of the parameter in the default ISO-8859-1 encoding
   * or empty string if the user entered no value for this parameter.
   * 
   * @return value of parameter as a ISO-8559-1 encoded string.
   * @modelguid {AD860A31-F793-47CF-AB28-308AEF94A1EC}
   */
  public String getStringValue() 
      throws UnsupportedEncodingException {
    return getStringValue( "ISO-8859-1" );
  }
  
  /** 
   * Returns the value of the parameter in the supplied encoding
   * or empty string if the user entered no value for this parameter.
   * 
   * @return value of parameter as a string.
   * @modelguid {7D0B5201-DFB9-4BE8-9D1E-E58AADAC6581}
   */
  public String getStringValue(String encoding) 
      throws UnsupportedEncodingException {
    return new String(value, encoding);
  }
  
  /**
   * Returns <code>true</code> to indicate this part is a parameter.
   * 
   * @return true.
   * @modelguid {C6EBC396-E288-4F14-A9EF-ED921C0EFE7B}
   */
  public boolean isParam() {
    return true;
  }
}
