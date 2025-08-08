/*
 * Created on May 17, 2007
 *
 */
package messaging.programreferral.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;


/**
 *
 */
public class ProgramAssociatedJuvenileEventReferralResponseEvent extends ResponseEvent implements Comparable{
	
	private String eventReferralId;
	
	private String serviceEventId;
	
	private String programReferralId;
	
	//added for Production Support
	private String createUserID;
	private Date createDate;
	private String updateUser;
	private Date updateDate;
	private String createJIMS2UserID;
	private String updateJIMS2UserID;
	
	
	
	/**
	 * @return Returns the programReferralId.
	 */
	public String getProgramReferralId() {
		return programReferralId;
	}
	/**
	 * @param programReferralId The programReferralId to set.
	 */
	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}

	/**
	 * @return the eventReferralId
	 */
	public String getEventReferralId() {
		return eventReferralId;
	}
	/**
	 * @param eventReferralId the eventReferralId to set
	 */
	public void setEventReferralId(String eventReferralId) {
		this.eventReferralId = eventReferralId;
	}
	/**
	 * @return the serviceEventId
	 */
	public String getServiceEventId() {
		return serviceEventId;
	}
	/**
	 * @param serviceEventId the serviceEventId to set
	 */
	public void setServiceEventId(String serviceEventId) {
		this.serviceEventId = serviceEventId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object obj) {
		if (obj==null || ! (obj instanceof ProgramAssociatedJuvenileEventReferralResponseEvent))
			return 0;		
		ProgramAssociatedJuvenileEventReferralResponseEvent resp = (ProgramAssociatedJuvenileEventReferralResponseEvent)obj;
		return ((this.getEventReferralId()==null || resp.getEventReferralId()==null) ? 0 : this.getEventReferralId().compareTo(resp.getEventReferralId()));
	}

	/**
	 * @return the createUserID
	 */
	public String getCreateUserID() {
		return createUserID;
	}

	/**
	 * @param createUserID the createUserID to set
	 */
	public void setCreateUserID(String createUserID) {
		this.createUserID = createUserID;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the updateUser
	 */
	public String getUpdateUser() {
		return updateUser;
	}

	/**
	 * @param updateUser the updateUser to set
	 */
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the createJIMS2UserID
	 */
	public String getCreateJIMS2UserID() {
		return createJIMS2UserID;
	}

	/**
	 * @param createJIMS2UserID the createJIMS2UserID to set
	 */
	public void setCreateJIMS2UserID(String createJIMS2UserID) {
		this.createJIMS2UserID = createJIMS2UserID;
	}

	/**
	 * @return the updateJIMS2UserID
	 */
	public String getUpdateJIMS2UserID() {
		return updateJIMS2UserID;
	}

	/**
	 * @param updateJIMS2UserID the updateJIMS2UserID to set
	 */
	public void setUpdateJIMS2UserID(String updateJIMS2UserID) {
		this.updateJIMS2UserID = updateJIMS2UserID;
	}
	
}
