/*
 * Created on Jan 18, 2007
 *
 */
package messaging.notification.to;

import messaging.notification.domintf.IAttachment;

/**
 * @author Jim Fisher
 *  
 */
public class EmailAttachmentBean implements IAttachment
{
    private byte[] content;

    private String contentType;

    private String name;

    public byte[] getContent()
    {
        return this.content;
    }

    public String getContentType()
    {
        return this.contentType;
    }

    public String getName()
    {
        return this.name;
    }

    /**
     * @param content
     *            The content to set.
     */
    public void setContent(byte[] content)
    {
        this.content = content;
    }

    /**
     * @param contentType
     *            The contentType to set.
     */
    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }

    /**
     * @param name
     *            The name to set.
     */
    public void setName(String name)
    {
        this.name = name;
    }

}
