package messaging.districtCourtHearings.reply;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.ResponseEvent;

/**
 * Notification Event For District Court.
 * 
 * @author sthyagarajan
 */
public class JuvenileDistrictCourtNotificationResponseEvent extends ResponseEvent implements IAddressable
{

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    public String juvenileNum;

    public String supervisionNumber;

    public String subject;

    public String identity;

    public String notificationMessage;

    /**
     * @return the juvenileNum
     */
    public String getJuvenileNum()
    {
	return juvenileNum;
    }

    /**
     * @param juvenileNum
     *            the juvenileNum to set
     */
    public void setJuvenileNum(String juvenileNum)
    {
	this.juvenileNum = juvenileNum;
    }

    /**
     * @return the supervisionNumber
     */
    public String getSupervisionNumber()
    {
	return supervisionNumber;
    }

    /**
     * @param supervisionNumber
     *            the supervisionNumber to set
     */
    public void setSupervisionNumber(String supervisionNumber)
    {
	this.supervisionNumber = supervisionNumber;
    }

    /**
     * @return the subject
     */
    public String getSubject()
    {
	return subject;
    }

    /**
     * @param subject
     *            the subject to set
     */
    public void setSubject(String subject)
    {
	this.subject = subject;
    }

    /**
     * @return the identity
     */
    public String getIdentity()
    {
	return identity;
    }

    /**
     * @param identity
     *            the identity to set
     */
    public void setIdentity(String identity)
    {
	this.identity = identity;
    }

    /**
     * @return the notificationMessage
     */
    public String getNotificationMessage()
    {
	return notificationMessage;
    }

    /**
     * @param notificationMessage
     *            the notificationMessage to set
     */
    public void setNotificationMessage(String notificationMessage)
    {
	this.notificationMessage = notificationMessage;
    }

    /**
     * @return the serialversionuid
     */
    public static long getSerialversionuid()
    {
	return serialVersionUID;
    }

}
