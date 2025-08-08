// Source file:
// C:\\views\\MJCW\\app\\src\\messaging\\calendar\\SaveProgramReferralEvent.java

package messaging.programreferral;

import java.util.Date;
import java.util.List;

import mojo.km.messaging.RequestEvent;

public class SaveProgramReferralEvent extends RequestEvent
{

    private String referralId;

    private String casefileId;

    private String programId;

    private String assignedHours;

    private Date beginDate;

    private Date sentDate;

    private Date lastActionDate;

    private Date endDate;

    private Date acknowledementDate;

    private String comments;

    private String currentUserName;

    private boolean isCourtOrdered;

    private String programReferralId;

    private String referralStatusCd;

    private String referralSubStatusCd;

    private List attachedEvents;

    private String outComeCd;

    private String outComeSubcategoryCd;

    private String controllingReferralNum;
    
    private String eventId;
    
    private boolean sendEmailToContacts;

    /**
     * @roseuid 463BA4D10066
     */
    public SaveProgramReferralEvent()
    {

    }

    /**
     * @return Returns the assignedHours.
     */
    public String getAssignedHours()
    {
	return assignedHours;
    }

    /**
     * @return Returns the beginDate.
     */
    public Date getBeginDate()
    {
	return beginDate;
    }

    /**
     * @return Returns the comments.
     */
    public String getComments()
    {
	return comments;
    }

    /**
     * @return Returns the programReferralId.
     */
    public String getProgramReferralId()
    {
	return programReferralId;
    }

    /**
     * @return Returns the isCourtOrdered.
     */
    public boolean isCourtOrdered()
    {
	return isCourtOrdered;
    }

    /**
     * @param assignedHours
     *            The assignedHours to set.
     */
    public void setAssignedHours(String assignedHours)
    {
	this.assignedHours = assignedHours;
    }

    /**
     * @param beginDate
     *            The beginDate to set.
     */
    public void setBeginDate(Date beginDate)
    {
	this.beginDate = beginDate;
    }

    /**
     * @param comments
     *            The comments to set.
     */
    public void setComments(String comments)
    {
	this.comments = comments;
    }

    /**
     * @param isCourtOrdered
     *            The isCourtOrdered to set.
     */
    public void setCourtOrdered(boolean isCourtOrdered)
    {
	this.isCourtOrdered = isCourtOrdered;
    }

    /**
     * @param programReferralId
     *            The programReferralId to set.
     */
    public void setProgramReferralId(String programReferralId)
    {
	this.programReferralId = programReferralId;
    }

    /**
     * @return Returns the referralStatusCd.
     */
    public String getReferralStatusCd()
    {
	return referralStatusCd;
    }

    /**
     * @param referralStatusCd
     *            The referralStatusCd to set.
     */
    public void setReferralStatusCd(String referralStatusCd)
    {
	this.referralStatusCd = referralStatusCd;
    }

    /**
     * @return Returns the referralSubStatusCd.
     */
    public String getReferralSubStatusCd()
    {
	return referralSubStatusCd;
    }

    /**
     * @param referralSubStatusCd
     *            The referralSubStatusCd to set.
     */
    public void setReferralSubStatusCd(String referralSubStatusCd)
    {
	this.referralSubStatusCd = referralSubStatusCd;
    }

    /**
     * @return Returns the currentUser.
     */
    public String getCurrentUserName()
    {
	return currentUserName;
    }

    /**
     * @param currentUser
     *            The currentUser to set.
     */
    public void setCurrentUserName(String currentUserName)
    {
	this.currentUserName = currentUserName;
    }

    /**
     * @return Returns the referralId.
     */
    public String getReferralId()
    {
	return referralId;
    }

    /**
     * @param referralId
     *            The referralId to set.
     */
    public void setReferralId(String referralId)
    {
	this.referralId = referralId;
    }

    /**
     * @return Returns the attachedEvents.
     */
    public List getAttachedEvents()
    {
	return attachedEvents;
    }

    /**
     * @param attachedEvents
     *            The attachedEvents to set.
     */
    public void setAttachedEvents(List attachedEvents)
    {
	this.attachedEvents = attachedEvents;
    }

    /**
     * @return Returns the casefileId.
     */
    public String getCasefileId()
    {
	return casefileId;
    }

    /**
     * @param casefileId
     *            The casefileId to set.
     */
    public void setCasefileId(String casefileId)
    {
	this.casefileId = casefileId;
    }

    /**
     * @return Returns the programId.
     */
    public String getProgramId()
    {
	return programId;
    }

    /**
     * @param programId
     *            The programId to set.
     */
    public void setProgramId(String programId)
    {
	this.programId = programId;
    }

    /**
     * @return Returns the lastActionDate.
     */
    public Date getLastActionDate()
    {
	return lastActionDate;
    }

    /**
     * @param lastActionDate
     *            The lastActionDate to set.
     */
    public void setLastActionDate(Date lastActionDate)
    {
	this.lastActionDate = lastActionDate;
    }

    /**
     * @return Returns the sentDate.
     */
    public Date getSentDate()
    {
	return sentDate;
    }

    /**
     * @param sentDate
     *            The sentDate to set.
     */
    public void setSentDate(Date sentDate)
    {
	this.sentDate = sentDate;
    }

    /**
     * @return Returns the acknowledementDate.
     */
    public Date getAcknowledementDate()
    {
	return acknowledementDate;
    }

    /**
     * @param acknowledementDate
     *            The acknowledementDate to set.
     */
    public void setAcknowledementDate(Date acknowledementDate)
    {
	this.acknowledementDate = acknowledementDate;
    }

    /**
     * @return Returns the endDate.
     */
    public Date getEndDate()
    {
	return endDate;
    }

    /**
     * @param endDate
     *            The endDate to set.
     */
    public void setEndDate(Date endDate)
    {
	this.endDate = endDate;
    }

    /**
     * @return Returns the outComeCd.
     */
    public String getOutComeCd()
    {
	return outComeCd;
    }

    /**
     * @param outComeCd
     *            The outComeCd to set.
     */
    public void setOutComeCd(String outComeCd)
    {
	this.outComeCd = outComeCd;
    }

    /**
     * @return the outComeSubcategoryCd
     */
    public String getOutComeSubcategoryCd()
    {
	return outComeSubcategoryCd;
    }

    /**
     * @param outComeSubcategoryCd
     *            the outComeSubcategoryCd to set
     */
    public void setOutComeSubcategoryCd(String outComeSubcategoryCd)
    {
	this.outComeSubcategoryCd = outComeSubcategoryCd;
    }

    /**
     * @return the controllingReferralNum
     */
    public String getControllingReferralNum()
    {
	return controllingReferralNum;
    }

    /**
     * @param controllingReferralNum
     *            the controllingReferralNum to set
     */
    public void setControllingReferralNum(String controllingReferralNum)
    {
	this.controllingReferralNum = controllingReferralNum;
    }

    public String getEventId()
    {
	return eventId;
    }

    public void setEventId(String eventId)
    {
	this.eventId = eventId;
    }

    public boolean isSendEmailToContacts()
    {
        return sendEmailToContacts;
    }

    public void setSendEmailToContacts(boolean sendEmailToContacts)
    {
        this.sendEmailToContacts = sendEmailToContacts;
    }

}
