package messaging.administerprogramreferrals;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class ReferProgramReferralEvent extends RequestEvent
{
	private String programReferralId;
	
	private String programId;
	
	private String locationId;
	
	private Date scheduleDateTime;

	private String submitComments;
	
	
	
	/**
	 * @return the programReferralId
	 */
	public String getProgramReferralId() {
		return programReferralId;
	}

	/**
	 * @param programReferralId the programReferralId to set
	 */
	public void setProgramReferralId(String programReferralId) {
		this.programReferralId = programReferralId;
	}


	/**
	 * @return the programId
	 */
	public String getProgramId() {
		return programId;
	}

	/**
	 * @param programId the programId to set
	 */
	public void setProgramId(String programId) {
		this.programId = programId;
	}

	/**
	 * @return the locationId
	 */
	public String getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	/**
	 * @return the scheduleDateTime
	 */
	public Date getScheduleDateTime() {
		return scheduleDateTime;
	}

	/**
	 * @param scheduleDateTime the scheduleDateTime to set
	 */
	public void setScheduleDateTime(Date scheduleDateTime) {
		this.scheduleDateTime = scheduleDateTime;
	}

	/**
	 * @return the submitComments
	 */
	public String getSubmitComments() {
		return submitComments;
	}

	/**
	 * @param submitComments the submitComments to set
	 */
	public void setSubmitComments(String submitComments) {
		this.submitComments = submitComments;
	}
	
}
