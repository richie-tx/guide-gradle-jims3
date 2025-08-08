// Copyright (C) 1998 by Jason Hunter <jhunter@acm.org>.  All rights reserved.
// Use of this class is limited.  Please see the LICENSE for more information.

package mojo.km.service.servlet.multipart;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.ServletInputStream;

/**
 * A utility class to handle <code>multipart/form-data</code> requests, the kind of requests that
 * support file uploads. This class uses a "pull" model where the reading of incoming files and
 * parameters is controlled by the client code, which allows incoming files to be stored into any
 * <code>OutputStream</code>. If you wish to use an API which resembles
 * <code>HttpServletRequest</code>, use the "push" model <code>MultipartRequest</code> instead.
 * It's an easy-to-use wrapper around this class.
 * <p>
 * This class can receive arbitrarily large files (up to an artificial limit you can set), and
 * fairly efficiently too. It cannot handle nested data (multipart content within multipart content)
 * or internationalized content (such as non Latin-1 filenames).
 * <p>
 * It also optionally includes enhanced buffering and Content-Length limitation. Buffering is only
 * required if your servlet container is poorly implemented (many are, including Tomcat 3.2), but it
 * is generally recommended because it will make a slow servlet container a lot faster, and will
 * only make a fast servlet container a little slower. Content-Length limiting is usually only
 * required if you find that your servlet is hanging trying to read the input stram from the POST,
 * and it is similarly recommended because it only has a minimal impact on performance.
 * <p>
 * See the included <a href="upload.war">upload.war </a> for an example of how to use this class.
 * <p>
 * The full file upload specification is contained in experimental RFC 1867, available at <a
 * href="http://www.ietf.org/rfc/rfc1867.txt"> http://www.ietf.org/rfc/rfc1867.txt </a>.
 * 
 * @see com.oreilly.servlet.MultipartRequest
 * 
 * @author Jason Hunter
 * @author Geoff Soutter
 * @version 1.3, 2001/03/23, added IE5 bug workaround supporting \n as line ending, thanks to
 *          Michael Alyn Miller
 * @version 1.3, 2001/01/22, added support for boundaries surrounded by quotes and
 *          content-disposition after content-type, thanks to Scott Stark
 * @version 1.2, 2001/01/22, getFilePath() support thanks to Stefan Eissing
 * @version 1.1, 2000/10/29, integrating old WebSphere fix
 * @version 1.0, 2000/10/27, initial revision
 * @modelguid {0F922B77-A4ED-46BE-9D9E-F879143DBEA4}
 */
public class MultipartParser
{

    /**
     * input stream to read parts from
     * 
     * @modelguid {DF39F589-5969-4E6C-B36C-E79636C7DC56}
     */
    private ServletInputStream in;

    /**
     * MIME boundary that delimits parts
     * 
     * @modelguid {C00BD6C5-5BC1-414C-9711-52AB32570231}
     */
    private String boundary;

    /**
     * reference to the last file part we returned
     * 
     * @modelguid {6FCB7D3B-9F0F-47B1-9739-77214C0063EC}
     */
    private FilePart lastFilePart;

    /**
     * buffer for readLine method
     * 
     * @modelguid {A75C6D25-E5AA-4280-8C25-33FA37D83032}
     */
    private byte[] buf = new byte[8 * 1024];

    /**
     * Creates a <code>MultipartParser</code> from the specified request, which limits the upload
     * size to the specified length, buffers for performance and prevent attempts to read past the
     * amount specified by the Content-Length.
     * 
     * @param req
     *            the servlet request.
     * @param maxSize
     *            the maximum size of the POST content.
     * @modelguid {13DA626B-88C5-43D7-A1EE-B0D0D59E2236}
     */
    public MultipartParser(HttpServletRequest req, int maxSize) throws IOException
    {
        this(req, maxSize, true, true);
    }

    /**
     * Creates a <code>MultipartParser</code> from the specified request, which limits the upload
     * size to the specified length, and optionally buffers for performance and prevents attempts to
     * read past the amount specified by the Content-Length.
     * 
     * @param req
     *            the servlet request.
     * @param maxSize
     *            the maximum size of the POST content.
     * @param limitLength
     *            boolean flag to indicate if we need to filter the request's input stream to
     *            prevent trying to read past the end of the stream.
     * @modelguid {267B69F4-91AD-440A-A933-70C8AF113E6A}
     */
    public MultipartParser(HttpServletRequest req, int maxSize, boolean buffer, boolean limitLength) throws IOException
    {
        // Check the content type to make sure it's "multipart/form-data"
        // Access header two ways to work around WebSphere oddities
        String type = null;
        String type1 = req.getHeader("Content-Type");
        String type2 = req.getContentType();
        // If one value is null, choose the other value
        if (type1 == null && type2 != null)
        {
            type = type2;
        }
        else if (type2 == null && type1 != null)
        {
            type = type1;
        }
        // If neither value is null, choose the longer value
        else if (type1 != null && type2 != null)
        {
            type = (type1.length() > type2.length() ? type1 : type2);
        }

        if (type == null || !type.toLowerCase().startsWith("multipart/form-data"))
        {
            throw new IOException("Posted content type isn't multipart/form-data");
        }

        // Check the content length to prevent denial of service attacks
        int length = req.getContentLength();
        if (length > maxSize)
        {
            throw new IOException("Posted content length of " + length + " exceeds limit of " + maxSize);
        }

        // Get the boundary string; it's included in the content type.
        // Should look something like "------------------------12012133613061"
        String boundary = extractBoundary(type);
        if (boundary == null)
        {
            throw new IOException("Separation boundary was not specified");
        }

        ServletInputStream in = req.getInputStream();

        // if required, wrap the real input stream with classes that
        // "enhance" its behaviour for performance and stability
        if (buffer)
        {
            in = new BufferedServletInputStream(in);
        }
        if (limitLength)
        {
            in = new LimitedServletInputStream(in, length);
        }

        // Save our values for later
        this.in = in;
        this.boundary = boundary;

        // Read the first line, should be the first boundary
        String line = readLine();
        if (line == null)
        {
            throw new IOException("Corrupt form data: premature ending");
        }

        // Verify that the line is the boundary
        if (!line.startsWith(boundary))
        {
            throw new IOException("Corrupt form data: no leading boundary: " + line + " != " + boundary);
        }
    }

    /**
     * Read the next part arriving in the stream. Will be either a <code>FilePart</code> or a
     * <code>ParamPart</code>, or <code>null</code> to indicate there are no more parts to
     * read. The order of arrival corresponds to the order of the form elements in the submitted
     * form.
     * 
     * @return either a <code>FilePart</code>, a <code>ParamPart</code> or <code>null</code>
     *         if there are no more parts to read.
     * @exception IOException
     *                if an input or output exception has occurred.
     * 
     * @see FilePart
     * @see ParamPart
     * @modelguid {34AE8752-8DCA-410A-96DD-7BB0002CBDA3}
     */
    public Part readNextPart() throws IOException
    {
        // Make sure the last file was entirely read from the input
        if (lastFilePart != null)
        {
            lastFilePart.getInputStream().close();
            lastFilePart = null;
        }

        // Read the headers; they look like this (not all may be present):
        // Content-Disposition: form-data; name="field1"; filename="file1.txt"
        // Content-Type: type/subtype
        // Content-Transfer-Encoding: binary
        Vector headers = new Vector();

        String line = readLine();
        if (line == null)
        {
            // No parts left, we're done
            return null;
        }
        else if (line.length() == 0)
        {
            // IE4 on Mac sends an empty line at the end; treat that as the end.
            // Thanks to Daniel Lemire and Henri Tourigny for this fix.
            return null;
        }
        headers.addElement(line);

        // Read the following header lines we hit an empty line
        while ((line = readLine()) != null && (line.length() > 0))
        {
            headers.addElement(line);
        }

        // If we got a null above, it's the end
        if (line == null)
        {
            return null;
        }

        String name = null;
        String filename = null;
        String origname = null;
        String contentType = "text/plain"; // rfc1867 says this is the default

        Enumeration en = headers.elements();
        while (en.hasMoreElements())
        {
            String headerline = (String) en.nextElement();
            if (headerline.toLowerCase().startsWith("content-disposition:"))
            {
                // Parse the content-disposition line
                String[] dispInfo = extractDispositionInfo(headerline);
                // String disposition = dispInfo[0]; // not currently used
                name = dispInfo[1];
                filename = dispInfo[2];
                origname = dispInfo[3];
            }
            else if (headerline.toLowerCase().startsWith("content-type:"))
            {
                // Get the content type, or null if none specified
                String type = extractContentType(headerline);
                if (type != null)
                {
                    contentType = type;
                }
            }
        }

        // Now, finally, we read the content (end after reading the boundary)
        if (filename == null)
        {
            // This is a parameter, add it to the vector of values
            return new ParamPart(name, in, boundary);
        }
        else
        {
            // This is a file
            if (filename.equals(""))
            {
                filename = null; // empty filename, probably an "empty" file param
            }
            lastFilePart = new FilePart(name, in, boundary, contentType, filename, origname);
            return lastFilePart;
        }
    }

    /**
     * Extracts and returns the boundary token from a line.
     * 
     * @return the boundary token.
     * @modelguid {62641921-6FF9-41B1-A1A3-2920C66B4A32}
     */
    private String extractBoundary(String line)
    {
        // Use lastIndexOf() because IE 4.01 on Win98 has been known to send the
        // "boundary=" string multiple times. Thanks to David Wall for this fix.
        int index = line.lastIndexOf("boundary=");
        if (index == -1)
        {
            return null;
        }
        String boundary = line.substring(index + 9); // 9 for "boundary="
        if (boundary.charAt(0) == '"')
        {
            // The boundary is enclosed in quotes, strip them
            index = boundary.lastIndexOf('"');
            boundary = boundary.substring(1, index);
        }

        // The real boundary is always preceeded by an extra "--"
        boundary = "--" + boundary;

        return boundary;
    }

    /**
     * Extracts and returns disposition info from a line, as a <code>String<code>
     * array with elements: disposition, name, filename.
     * 
     * @return String[] of elements: disposition, name, filename.
     * @exception  IOException if the line is malformatted.
     * @modelguid {E250B4B5-73C4-490B-B4A5-1E818E918A8D}
     */
    private String[] extractDispositionInfo(String line) throws IOException
    {
        // Return the line's data as an array: disposition, name, filename
        String[] retval = new String[4];

        // Convert the line to a lowercase string without the ending \r\n
        // Keep the original line for error messages and for variable names.
        String origline = line;
        line = origline.toLowerCase();

        // Get the content disposition, should be "form-data"
        int start = line.indexOf("content-disposition: ");
        int end = line.indexOf(";");
        if (start == -1 || end == -1)
        {
            throw new IOException("Content disposition corrupt: " + origline);
        }
        String disposition = line.substring(start + 21, end);
        if (!disposition.equals("form-data"))
        {
            throw new IOException("Invalid content disposition: " + disposition);
        }

        // Get the field name
        start = line.indexOf("name=\"", end); // start at last semicolon
        end = line.indexOf("\"", start + 7); // skip name=\"
        if (start == -1 || end == -1)
        {
            throw new IOException("Content disposition corrupt: " + origline);
        }
        String name = origline.substring(start + 6, end);

        // Get the filename, if given
        String filename = null;
        String origname = null;
        start = line.indexOf("filename=\"", end + 2); // start after name
        end = line.indexOf("\"", start + 10); // skip filename=\"
        if (start != -1 && end != -1)
        { // note the !=
            filename = origline.substring(start + 10, end);
            origname = filename;
            // The filename may contain a full path. Cut to just the filename.
            int slash = Math.max(filename.lastIndexOf('/'), filename.lastIndexOf('\\'));
            if (slash > -1)
            {
                filename = filename.substring(slash + 1); // past last slash
            }
        }

        // Return a String array: disposition, name, filename
        // empty filename denotes no file posted!
        retval[0] = disposition;
        retval[1] = name;
        retval[2] = filename;
        retval[3] = origname;
        return retval;
    }

    /**
     * Extracts and returns the content type from a line, or null if the line was empty.
     * 
     * @return content type, or null if line was empty.
     * @exception IOException
     *                if the line is malformatted.
     * @modelguid {A203E394-2F00-49D6-9953-238CE25C4C69}
     */
    private String extractContentType(String line) throws IOException
    {
        String contentType = null;

        // Convert the line to a lowercase string
        String origline = line;
        line = origline.toLowerCase();

        // Get the content type, if any
        if (line.startsWith("content-type"))
        {
            int start = line.indexOf(" ");
            if (start == -1)
            {
                throw new IOException("Content type corrupt: " + origline);
            }
            contentType = line.substring(start + 1);
        }
        else if (line.length() != 0)
        { // no content type, so should be empty
            throw new IOException("Malformed line after disposition: " + origline);
        }

        return contentType;
    }

    /**
     * Read the next line of input.
     * 
     * @return a String containing the next line of input from the stream, or null to indicate the
     *         end of the stream.
     * @exception IOException
     *                if an input or output exception has occurred.
     * @modelguid {E8362305-34FC-40B3-9387-8B7F6AF0FCEB}
     */
    private String readLine() throws IOException
    {
        StringBuffer sbuf = new StringBuffer();
        int result;
        String line;

        do
        {
            result = in.readLine(buf, 0, buf.length); // does +=
            if (result != -1)
            {
                sbuf.append(new String(buf, 0, result, "ISO-8859-1"));
            }
        } while (result == buf.length); // loop only if the buffer was filled

        if (sbuf.length() == 0)
        {
            return null; // nothing read, must be at the end of stream
        }

        // Cut off the trailing \n or \r\n
        // It should always be \r\n but IE5 sometimes does just \n
        int len = sbuf.length();
        if (sbuf.charAt(len - 2) == '\r')
        {
            sbuf.setLength(len - 2); // cut \r\n
        }
        else
        {
            sbuf.setLength(len - 1); // cut \n
        }
        return sbuf.toString();
    }
}
