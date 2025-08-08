/*
 * Created on Mar 9, 2006
 *
 */
package messaging.notification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import messaging.identityaddress.domintf.IAddressable;
import messaging.notification.domintf.IAttachment;
import messaging.notification.domintf.ICreateNotification;
import mojo.km.messaging.RequestEvent;

/**
 * @author Jim Fisher
 *  
 */
public class CreateNotificationEvent extends RequestEvent implements ICreateNotification
{
    private String subject;

    private Map identities;

    private String notificationTopic;

    private List contentBeans;

    private IAttachment attachment;

    public CreateNotificationEvent()
    {
        super();
        this.contentBeans = new ArrayList();
        this.identities = new HashMap();
    }

    public void addContentBean(Serializable bean)
    {
        this.contentBeans.add(bean);
    }

    public List getContentBeans()
    {
        return this.contentBeans;
    }

    public void addIdentity(String context, IAddressable addressable)
    {
        this.identities.put(context, addressable);
    }

    public Map getIdentities()
    {
        return this.identities;
    }

    /**
     * @return
     */
    public String getNotificationTopic()
    {
        return notificationTopic;
    }

    /**
     * @param string
     */
    public void setNotificationTopic(String string)
    {
        notificationTopic = string;
    }

    /**
     * @return
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * @param string
     */
    public void setSubject(String string)
    {
        subject = string;
    }

    /**
     * @return Returns the attachment.
     */
    public IAttachment getAttachment()
    {
        return attachment;
    }

    /**
     * @param attachment
     *            The attachment to set.
     */
    public void setAttachment(IAttachment attachment)
    {
        this.attachment = attachment;
    }
}
