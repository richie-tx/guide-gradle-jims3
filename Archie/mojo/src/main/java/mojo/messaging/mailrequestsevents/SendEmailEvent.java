package mojo.messaging.mailrequestsevents;

import messaging.identityaddress.domintf.IAddressable;
import messaging.notification.domintf.IAttachment;
import mojo.km.messaging.RequestEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Responsible for housing data that will be sent to control command SendEmailCommand
 * 
 * @author Jim Fisher
 */
public class SendEmailEvent extends RequestEvent implements IAddressable
{
    public SendEmailEvent()
    {
        this.toAddresses = new ArrayList<String>();
        this.ccAddresses = new ArrayList<String>();
        this.bccAddresses = new ArrayList<String>();
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

    private IAttachment attachment;

    private List<String> bccAddresses;

    private List<String> ccAddresses;

    private String fromAddress;

    private String message;

    private String messageId;

    private String subject;
    
    private String contentType;  //changes added for #13331

    private List<String> toAddresses;

    public void addBccAddress(String value)
    {
        this.bccAddresses.add(value);
    }

    public void addCcAddress(String value)
    {
        this.ccAddresses.add(value);
    }

    public void addToAddress(String value)
    {
        this.toAddresses.add(value);
    }

    /**
     * @return
     */
    public List<String> getBccAddresses()
    {
        return bccAddresses;
    }

    /**
     * @return
     */
    public List<String> getCcAddresses()
    {
        return ccAddresses;
    }

    public String getFromAddress()
    {
        return fromAddress;
    }

    public String getMessage()
    {
        return message;
    }

    /**
     * @return
     */
    public String getMessageId()
    {
        return messageId;
    }

    public String getSubject()
    {
        return subject;
    }

    public List<String> getToAddresses()
    {
        return toAddresses;
    }
    
    public String getToAddressString()
    {
    	StringBuilder buffer = new StringBuilder();
    	int len = toAddresses.size();
    	for(int i=0;i<len;i++)
    	{
    		buffer.append(toAddresses.get(i));
    		if(i<len-1)
    		{
    			buffer.append(";");
    		}
    	}
    	return buffer.toString();
    }

    public void setFromAddress(String fromAddress)
    {
        this.fromAddress = fromAddress;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    /**
     * @param string
     */
    public void setMessageId(String string)
    {
        messageId = string;
    }

    public void setSubject(String subject)
    {
        this.subject = subject;
    }

	/**
	 * @return the aContentType
	 */
	public String getContentType() {
		return contentType;
	}

	/**
	 * @param aContentType the aContentType to set
	 */
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

}
