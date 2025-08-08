/*
 * Created on Jan 18, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package mojo.es.mail.mailrequests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.activation.DataSource;

/**
 * @author jfisher
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ByteArrayDataSource implements DataSource
{
    private byte[] content;
    private String contentType;
    private String name;

    public ByteArrayDataSource(String aName, byte[] aContent, String aContentType)
    {
        this.name = aName;
        this.content = aContent;
        this.contentType = aContentType;
    }

    /* (non-Javadoc)
     * @see javax.activation.DataSource#getContentType()
     */
    public String getContentType()
    {
        // TODO Auto-generated method stub
        return this.contentType;
    }

    /* (non-Javadoc)
     * @see javax.activation.DataSource#getInputStream()
     */
    public InputStream getInputStream() throws IOException
    {
        InputStream is = null;
        if(this.content != null)
        {
            is = new ByteArrayInputStream(this.content);
        }
        return is;
    }

    /* (non-Javadoc)
     * @see javax.activation.DataSource#getName()
     */
    public String getName()
    {
        return this.name;
    }

    public OutputStream getOutputStream() throws IOException
    {
        ByteArrayOutputStream os = null;
        if(this.content != null)
        {
            os = new ByteArrayOutputStream();
            os.write(this.content);
        }
        return os;
    }

}
