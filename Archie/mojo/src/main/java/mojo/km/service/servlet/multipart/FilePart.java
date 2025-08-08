// Copyright (C) 1999 by Jason Hunter <jhunter@acm.org>.  All rights reserved.
// Use of this class is limited.  Please see the LICENSE for more information.

package mojo.km.service.servlet.multipart;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.servlet.ServletInputStream;

/**
 * A <code>FilePart</code> is an upload part which represents a 
 * <code>INPUT TYPE="file"</code> form parameter.
 * 
 * @author Geoff Soutter
 * @version 1.2, 2001/01/22, getFilePath() addition thanks to Stefan Eissing
 * @version 1.1, 2000/11/26, writeTo() bug fix thanks to Mike Shivas
 * @version 1.0, 2000/10/27, initial revision
 * @modelguid {29EDFD8B-87E2-4242-83FF-640E48010316}
 */
public class FilePart extends Part {
  
  /** "file system" name of the file  
   * @modelguid {735D442B-E63B-426B-A222-4E98B880B4A4}
   */
  private String fileName;     
  
  /** path of the file as sent in the request, if given 
   * @modelguid {E24510AF-6D32-462B-A28C-B5982BD9FC15}
   */
  private String filePath;

  /** content type of the file 
   * @modelguid {BA69F9C6-A262-4599-92F6-6B0A8489A095}
   */
  private String contentType;   
  
  /** input stream containing file data 
   * @modelguid {73200B49-BF62-4BC0-A12B-325C75DEB3E5}
   */
  private PartInputStream partInput;  
    
  /**
   * Construct a file part; this is called by the parser.
   * 
   * @param name the name of the parameter.
   * @param in the servlet input stream to read the file from.
   * @param boundary the MIME boundary that delimits the end of file.
   * @param contentType the content type of the file provided in the 
   * MIME header.
   * @param fileName the file system name of the file provided in the 
   * MIME header.
   * @param filePath the file system path of the file provided in the
   * MIME header (as specified in disposition info).
   * 
   * @exception IOException	if an input or output exception has occurred.
   * @modelguid {26D43FF9-834A-4212-A218-D710A505ECFB}
   */
  FilePart(String name, ServletInputStream in, String boundary,
           String contentType, String fileName, String filePath)
                                                   throws IOException {
    super(name);
    this.fileName = fileName;
    this.filePath = filePath;
    this.contentType = contentType;
    partInput = new PartInputStream(in, boundary);
  }
  
  /**
   * Returns the name that the file was stored with on the remote system, 
   * or <code>null</code> if the user didn't enter a file to be uploaded. 
   * Note: this is not the same as the name of the form parameter used to 
   * transmit the file; that is available from the <code>getName</code>
   * method.
   * 
   * @return name of file uploaded or <code>null</code>.
   * 
   * @see Part#getName()
   * @modelguid {C5FE1AE5-5798-4349-8742-FD077D52EAB3}
   */
  public String getFileName() {
    return fileName;
  }

  /**
   * Returns the full path and name of the file on the remote system,
   * or <code>null</code> if the user didn't enter a file to be uploaded.
   * If path information was not supplied by the remote system, this method
   * will return the same as <code>getFileName()</code>.
   *
   * @return path of file uploaded or <code>null</code>.
   *
   * @see Part#getName()
   * @modelguid {0585AFEB-0536-4A1F-A53A-BF71395A3AA7}
   */
  public String getFilePath() {
    return filePath;
  }

  /** 
   * Returns the content type of the file data contained within.
   * 
   * @return content type of the file data.
   * @modelguid {7CFE7098-01CE-4758-A1DE-C865435C3EA9}
   */
  public String getContentType() {
    return contentType;
  }
  
  /**
   * Returns an input stream which contains the contents of the
   * file supplied. If the user didn't enter a file to upload
   * there will be <code>0</code> bytes in the input stream.
   * It's important to read the contents of the InputStream 
   * immediately and in full before proceeding to process the 
   * next part.  The contents will otherwise be lost on moving
   * to the next part.
   * 
   * @return an input stream containing contents of file.
   * @modelguid {8487CD8C-3192-4394-BD4D-B1E7E80196F0}
   */
  public InputStream getInputStream() {
    return partInput;
  }

  /**
   * Write this file part to a file or directory. If the user 
   * supplied a file, we write it to that file, and if they supplied
   * a directory, we write it to that directory with the filename
   * that accompanied it. If this part doesn't contain a file this
   * method does nothing.
   *
   * @return number of bytes written
   * @exception IOException	if an input or output exception has occurred.
   * @modelguid {7F375A26-46BD-4E78-988B-EA7E3D6BEB65}
   */
  public long writeTo(File fileOrDirectory) throws IOException {
    long written = 0;
    
    OutputStream fileOut = null;
    try {
      // Only do something if this part contains a file
      if (fileName != null) {
        // Check if user supplied directory
        File file;
        if (fileOrDirectory.isDirectory()) {
          // Write it to that dir the user supplied, 
          // with the filename it arrived with
          file = new File(fileOrDirectory, fileName);
        }
        else {
          // Write it to the file the user supplied,
          // ignoring the filename it arrived with
          file = fileOrDirectory;
        }
        fileOut = new BufferedOutputStream(new FileOutputStream(file));
        written = write(fileOut);
      }
    }
    finally {
      if (fileOut != null) fileOut.close();
    }
    return written;
  }

  /**
   * Write this file part to the given output stream. If this part doesn't 
   * contain a file this method does nothing.
   *
   * @return number of bytes written.
   * @exception IOException	if an input or output exception has occurred.
   * @modelguid {8F566151-722B-4741-9BD2-6AD0BC8C7D00}
   */
  public long writeTo(OutputStream out) throws IOException {
    long size=0;
    // Only do something if this part contains a file
    if (fileName != null) {
      // Write it out
      size = write( out );
    }
    return size;
  }

  /**
   * Internal method to write this file part; doesn't check to see
   * if it has contents first.
   *
   * @return number of bytes written.
   * @exception IOException	if an input or output exception has occurred.
   * @modelguid {D1E05906-964A-455D-B38A-3B07BC377099}
   */
  long write(OutputStream out) throws IOException {
    // decode macbinary if this was sent
    if (contentType.equals("application/x-macbinary")) {
      out = new MacBinaryDecoderOutputStream(out);
    }
    long size=0;
    int read;
    byte[] buf = new byte[8 * 1024];
    while((read = partInput.read(buf)) != -1) {
      out.write(buf, 0, read);
      size += read;
    }
    return size;
  }
  
  /**
   * Returns <code>true</code> to indicate this part is a file.
   * 
   * @return true.
   * @modelguid {36055C8E-8D34-4630-BBFC-FF32EC43E2D1}
   */
  public boolean isFile() {
    return true;
  }
}
