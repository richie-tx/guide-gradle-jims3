// Copyright (C) 1998 by Jason Hunter <jhunter@acm.org>.  All rights reserved.
// Use of this class is limited.  Please see the LICENSE for more information.

package mojo.km.service.servlet.multipart;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

import mojo.km.service.servlet.multipart.MultipartParser;
import mojo.km.service.servlet.multipart.Part;
import mojo.km.service.servlet.multipart.FilePart;
import mojo.km.service.servlet.multipart.ParamPart;

/** 
 * A utility class to handle <code>multipart/form-data</code> requests,
 * the kind of requests that support file uploads.  This class emulates the 
 * interface of <code>HttpServletRequest</code>, making it familiar to use. 
 * It uses a "push" model where any incoming files are read and saved directly
 * to disk in the constructor. If you wish to have more flexibility, e.g. 
 * write the files to a database, use the "pull" model 
 * <code>MultipartParser</code> instead.
 * <p>
 * This class can receive arbitrarily large files (up to an artificial limit 
 * you can set), and fairly efficiently too.  
 * It cannot handle nested data (multipart content within multipart content)
 * or internationalized content (such as non Latin-1 filenames).
 * <p>
 * See the included <a href="upload.war">upload.war</a> 
 * for an example of how to use this class.
 * <p>
 * The full file upload specification is contained in experimental RFC 1867,
 * available at <a href="http://www.ietf.org/rfc/rfc1867.txt">
 * http://www.ietf.org/rfc/rfc1867.txt</a>.
 *
 * @see MultipartParser
 * 
 * @author Jason Hunter
 * @author Geoff Soutter
 * @version 1.7, 01/02/07, made fields protected to increase user flexibility
 * @version 1.6, 00/07/21, redid internals to use MultipartParser,
 *                         thanks to Geoff Soutter
 * @version 1.5, 00/02/04, added auto MacBinary decoding for IE on Mac
 * @version 1.4, 00/01/05, added getParameterValues(),
 *                         WebSphere 2.x getContentType() workaround,
 *                         stopped writing empty "unknown" file
 * @version 1.3, 99/12/28, IE4 on Win98 lastIndexOf("boundary=") workaround
 * @version 1.2, 99/12/20, IE4 on Mac readNextPart() workaround
 * @version 1.1, 99/01/15, JSDK readLine() bug workaround
 * @version 1.0, 98/09/18
 * @modelguid {E36B7C69-CEDB-4E7A-922D-52ACCC75C84F}
 */
public class MultipartRequest {

	/** @modelguid {E6D71543-BB4C-4A7D-8DD9-0910A38F00AA} */
  private static final int DEFAULT_MAX_POST_SIZE = 1024 * 1024;  // 1 Meg

	/** @modelguid {396CADF3-EFB3-44B4-BD81-E90FCA56BCA3} */
  protected Hashtable parameters = new Hashtable();  // name - Vector of values
	/** @modelguid {B6606B39-4DC2-4F5D-9041-7C0859B1F1A0} */
  protected Hashtable files = new Hashtable();       // name - UploadedFile

  /**
   * Constructs a new MultipartRequest to handle the specified request, 
   * saving any uploaded files to the given directory, and limiting the 
   * upload size to 1 Megabyte.  If the content is too large, an
   * IOException is thrown.  This constructor actually parses the 
   * <tt>multipart/form-data</tt> and throws an IOException if there's any 
   * problem reading or parsing the request.
   *
   * @param request the servlet request.
   * @param saveDirectory the directory in which to save any uploaded files.
   * @exception IOException if the uploaded content is larger than 1 Megabyte
   * or there's a problem reading or parsing the request.
   * @modelguid {4DF03647-CACE-4F33-8452-FCFFD91C4635}
   */
  public MultipartRequest(HttpServletRequest request,
                          String saveDirectory) throws IOException {
    this(request, saveDirectory, DEFAULT_MAX_POST_SIZE);
  }

  /**
   * Constructs a new MultipartRequest to handle the specified request, 
   * saving any uploaded files to the given directory, and limiting the 
   * upload size to the specified length.  If the content is too large, an 
   * IOException is thrown.  This constructor actually parses the 
   * <tt>multipart/form-data</tt> and throws an IOException if there's any 
   * problem reading or parsing the request.
   *
   * @param request the servlet request.
   * @param saveDirectory the directory in which to save any uploaded files.
   * @param maxPostSize the maximum size of the POST content.
   * @exception IOException if the uploaded content is larger than 
   * <tt>maxPostSize</tt> or there's a problem reading or parsing the request.
   * @modelguid {26C9A6EC-776C-4856-8BFE-5EB7DC00D349}
   */
  public MultipartRequest(HttpServletRequest request,
                          String saveDirectory,
                          int maxPostSize) throws IOException {
    // Sanity check values
    if (request == null)
      throw new IllegalArgumentException("request cannot be null");
    if (saveDirectory == null)
      throw new IllegalArgumentException("saveDirectory cannot be null");
    if (maxPostSize <= 0) {
      throw new IllegalArgumentException("maxPostSize must be positive");
    }

    // Save the dir
    File dir = new File(saveDirectory);

    // Check saveDirectory is truly a directory
    if (!dir.isDirectory())
      throw new IllegalArgumentException("Not a directory: " + saveDirectory);

    // Check saveDirectory is writable
    if (!dir.canWrite())
      throw new IllegalArgumentException("Not writable: " + saveDirectory);

    // Parse the incoming multipart, storing files in the dir provided, 
    // and populate the meta objects which describe what we found
    MultipartParser parser = new MultipartParser(request, maxPostSize);

    Part part;
    while ((part = parser.readNextPart()) != null) {
      String name = part.getName();
      if (part.isParam()) {
        // It's a parameter part, add it to the vector of values
        ParamPart paramPart = (ParamPart) part;
        String value = paramPart.getStringValue();
        Vector existingValues = (Vector)parameters.get(name);
        if (existingValues == null) {
          existingValues = new Vector();
          parameters.put(name, existingValues);
        }
        existingValues.addElement(value);
      }
      else if (part.isFile()) {
        // It's a file part
        FilePart filePart = (FilePart) part;
        String fileName = filePart.getFileName();
        if (fileName != null) {
          // The part actually contained a file
          filePart.writeTo(dir);
          files.put(name, new UploadedFile(
                      dir.toString(), fileName, filePart.getContentType()));
        }
        else { 
          // The field did not contain a file
          files.put(name, new UploadedFile(null, null, null));
        }
      }
    }
  }

  /**
   * Constructor with an old signature, kept for backward compatibility.
   * Without this constructor, a servlet compiled against a previous version 
   * of this class (pre 1.4) would have to be recompiled to link with this 
   * version.  This constructor supports the linking via the old signature.
   * Callers must simply be careful to pass in an HttpServletRequest.
   * 
   * @modelguid {5D83D933-78C0-4D7D-B953-A0C4EC728E3A}
   */
  public MultipartRequest(ServletRequest request,
                          String saveDirectory) throws IOException {
    this((HttpServletRequest)request, saveDirectory);
  }

  /**
   * Constructor with an old signature, kept for backward compatibility.
   * Without this constructor, a servlet compiled against a previous version 
   * of this class (pre 1.4) would have to be recompiled to link with this 
   * version.  This constructor supports the linking via the old signature.
   * Callers must simply be careful to pass in an HttpServletRequest.
   * 
   * @modelguid {67CBBCA3-F305-4C7B-85B2-F060BB64F331}
   */
  public MultipartRequest(ServletRequest request,
                          String saveDirectory,
                          int maxPostSize) throws IOException {
    this((HttpServletRequest)request, saveDirectory, maxPostSize);
  }

  /**
   * Returns the names of all the parameters as an Enumeration of 
   * Strings.  It returns an empty Enumeration if there are no parameters.
   *
   * @return the names of all the parameters as an Enumeration of Strings.
   * @modelguid {40676CF7-AC2C-4037-B29A-DD3C82366BA3}
   */
  public Enumeration getParameterNames() {
    return parameters.keys();
  }

  /**
   * Returns the names of all the uploaded files as an Enumeration of 
   * Strings.  It returns an empty Enumeration if there are no uploaded 
   * files.  Each file name is the name specified by the form, not by 
   * the user.
   *
   * @return the names of all the uploaded files as an Enumeration of Strings.
   * @modelguid {191C00E0-3134-4827-9F71-B2167861C975}
   */
  public Enumeration getFileNames() {
    return files.keys();
  }

  /**
   * Returns the value of the named parameter as a String, or null if 
   * the parameter was not sent or was sent without a value.  The value 
   * is guaranteed to be in its normal, decoded form.  If the parameter 
   * has multiple values, only the last one is returned (for backward 
   * compatibility).  For parameters with multiple values, it's possible
   * the last "value" may be null.
   *
   * @param name the parameter name.
   * @return the parameter value.
   * @modelguid {DFA3A16C-E3C6-41EF-9936-4EBDAB474CE2}
   */
  public String getParameter(String name) {
    try {
      Vector values = (Vector)parameters.get(name);
      if (values == null || values.size() == 0) {
        return null;
      }
      String value = (String)values.elementAt(values.size() - 1);
      return value;
    }
    catch (Exception e) {
      return null;
    }
  }

  /**
   * Returns the values of the named parameter as a String array, or null if 
   * the parameter was not sent.  The array has one entry for each parameter 
   * field sent.  If any field was sent without a value that entry is stored 
   * in the array as a null.  The values are guaranteed to be in their 
   * normal, decoded form.  A single value is returned as a one-element array.
   *
   * @param name the parameter name.
   * @return the parameter values.
   * @modelguid {14384A68-D35D-4E75-9252-8D12D0E39910}
   */
  public String[] getParameterValues(String name) {
    try {
      Vector values = (Vector)parameters.get(name);
      if (values == null || values.size() == 0) {
        return null;
      }
      String[] valuesArray = new String[values.size()];
      values.copyInto(valuesArray);
      return valuesArray;
    }
    catch (Exception e) {
      return null;
    }
  }

  /**
   * Returns the filesystem name of the specified file, or null if the 
   * file was not included in the upload.  A filesystem name is the name 
   * specified by the user.  It is also the name under which the file is 
   * actually saved.
   *
   * @param name the file name.
   * @return the filesystem name of the file.
   * @modelguid {4E655ACE-962B-45A2-AD08-B239A4AFDACB}
   */
  public String getFilesystemName(String name) {
    try {
      UploadedFile file = (UploadedFile)files.get(name);
      return file.getFilesystemName();  // may be null
    }
    catch (Exception e) {
      return null;
    }
  }

  /**
   * Returns the content type of the specified file (as supplied by the 
   * client browser), or null if the file was not included in the upload.
   *
   * @param name the file name.
   * @return the content type of the file.
   * @modelguid {5717D5F5-41C5-4A10-8FDE-EA37C0686298}
   */
  public String getContentType(String name) {
    try {
      UploadedFile file = (UploadedFile)files.get(name);
      return file.getContentType();  // may be null
    }
    catch (Exception e) {
      return null;
    }
  }

  /**
   * Returns a File object for the specified file saved on the server's 
   * filesystem, or null if the file was not included in the upload.
   *
   * @param name the file name.
   * @return a File object for the named file.
   * @modelguid {27540707-1C15-4AB6-A722-3B75262EFBDE}
   */
  public File getFile(String name) {
    try {
      UploadedFile file = (UploadedFile)files.get(name);
      return file.getFile();  // may be null
    }
    catch (Exception e) {
      return null;
    }
  }
}


// A class to hold information about an uploaded file.
//
/** @modelguid {499DCA44-6E0C-49CA-9A5F-0481F655219F} */
class UploadedFile {

	/** @modelguid {FAE310F0-F72B-414D-B512-45DE5E64DD88} */
  private String dir;
	/** @modelguid {752D9ABC-15CD-4F7D-8D3B-A18C9BC7AF4E} */
  private String filename;
	/** @modelguid {CC638149-B3CC-492A-996B-8F260E969675} */
  private String type;

	/** @modelguid {CE712443-E5DB-48D0-B3A1-688C6D5D97F5} */
  UploadedFile(String dir, String filename, String type) {
    this.dir = dir;
    this.filename = filename;
    this.type = type;
  }

	/** @modelguid {E60F7D7B-0A39-4219-9535-0454AE9805F6} */
  public String getContentType() {
    return type;
  }

	/** @modelguid {017695B2-3076-4BF6-910A-A5CCB16371A8} */
  public String getFilesystemName() {
    return filename;
  }

	/** @modelguid {3E9B2616-519B-4290-82F3-ABA70BE0E3AA} */
  public File getFile() {
    if (dir == null || filename == null) {
      return null;
    }
    else {
      return new File(dir + File.separator + filename);
    }
  }
}

