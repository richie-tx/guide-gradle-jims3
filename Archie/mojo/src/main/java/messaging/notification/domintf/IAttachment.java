/*
 * Created on Jan 18, 2007
 *
 */
package messaging.notification.domintf;

/**
 * @author Jim Fisher
 *
 */
public interface IAttachment
{
    String getName();
    String getContentType();
    byte[] getContent();
    void setName(String aName);
    void setContent(byte[] aContent);
    void setContentType(String aContentType);    
}
