/*
 * Created on Sept 18 2018
 */
package messaging.detentionCourtHearings.reply;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.ResponseEvent;

/**
 * @author ryoung
 *
 */
public class JuvenileDetentionNotificationResponseEvent extends ResponseEvent implements IAddressable {
	
    	private static final long serialVersionUID = 1L;
    	private String juvenileNum;
	private String bookingReferral;
	private String assignedCourt;
	private String juvenileName;
	private String hearingDate;
	private String detainedFacilityDesc;
	private String emailTo;
	private boolean isCLM;
	private String facility;
	private String supervisionType;


	private String supervisionNumber;
	
	//used for notification message to SP/JPO
	private String subject;
	private String identity;
	private String notificationMessage;
     /**
     * 
     */
   
	public String getBookingReferral()
	{
	    return bookingReferral;
	}

	public void setBookingReferral(String bookingReferral)
	{
	    this.bookingReferral = bookingReferral;
	}

	public String getAssignedCourt()
	{
	    return assignedCourt;
	}

	public void setAssignedCourt(String assignedCourt)
	{
	    this.assignedCourt = assignedCourt;
	}

	public String getJuvenileName()
	{
	    return juvenileName;
	}

	public void setJuvenileName(String juvenileName)
	{
	    this.juvenileName = juvenileName;
	}

	public String getHearingDate()
	{
	    return hearingDate;
	}

	public void setHearingDate(String hearingDate)
	{
	    this.hearingDate = hearingDate;
	}
	
	

	/**
	 * @roseuid 46C1B357025B
	 */
	public JuvenileDetentionNotificationResponseEvent() {

	}

	/**
	 * Access method for the juvenileNum property.
	 * 
	 * @return the current value of the juvenileNum property
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * Sets the value of the juvenileNum property.
	 * 
	 * @param aJuvenileNum
	 *            the new value of the juvenileNum property
	 */
	public void setJuvenileNum(String aJuvenileNum) {
		juvenileNum = aJuvenileNum;
	}

	/**
	 * Access method for the supervisionNumber property.
	 * 
	 * @return the current value of the supervisionNumber property
	 */
	public String getSupervisionNumber() {
		return supervisionNumber;
	}

	/**
	 * Sets the value of the supervisionNumber property.
	 * 
	 * @param aSupervisionNumber
	 *            the new value of the supervisionNumber property
	 */
	public void setSupervisionNumber(String aSupervisionNumber) {
		supervisionNumber = aSupervisionNumber;
	}

	/**
	 * @return Returns the identity.
	 */
	public String getIdentity() {
		return identity;
	}
	/**
	 * @param identity The identity to set.
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}
	/**
	 * @return Returns the notificationMessage.
	 */
	public String getNotificationMessage() {
		return notificationMessage;
	}
	/**
	 * @param notificationMessage The notificationMessage to set.
	 */
	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}
	/**
	 * @return Returns the subject.
	 */
	public String getSubject() {
		return subject;
	}
	/**
	 * @param subject The subject to set.
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	/**
	 * @return Returns the referralId.
	 */

	public String getDetainedFacilityDesc()
	{
	    return detainedFacilityDesc;
	}

	public void setDetainedFacilityDesc(String detainedFacilityDesc)
	{
	    this.detainedFacilityDesc = detainedFacilityDesc;
	}

	public String getEmailTo()
	{
	    return emailTo;
	}

	public void setEmailTo(String emailTo)
	{
	    this.emailTo = emailTo;
	}

	public boolean isCLM()
	{
	    return isCLM;
	}

	public void setCLM(boolean isCLM)
	{
	    this.isCLM = isCLM;
	}

	public String getFacility()
	{
	    return facility;
	}

	public void setFacility(String facility)
	{
	    this.facility = facility;
	}

	public String getSupervisionType()
	{
	    return supervisionType;
	}

	public void setSupervisionType(String supervisionType)
	{
	    this.supervisionType = supervisionType;
	}
	
}
