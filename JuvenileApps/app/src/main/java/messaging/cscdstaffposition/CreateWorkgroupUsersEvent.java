/*
 * Created on Jun 13, 2007
 */
package messaging.cscdstaffposition;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_rbhat
 *
 */
public class CreateWorkgroupUsersEvent extends RequestEvent {
	private String agencyId;
	private String userFirstName;
	private String userLastName;
	private String jobTitleId;
	private	String divisionId;
	private String programUnitId; 	
	private String positionTypeId;
		
	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		return agencyId;
	}
	/**
	 * @param agencyId The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}
	/**
	 * @return Returns the divisionId.
	 */
	public String getDivisionId() {
		return divisionId;
	}
	/**
	 * @param divisionId The divisionId to set.
	 */
	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	/**
	 * @return Returns the jobTitleId.
	 */
	public String getJobTitleId() {
		return jobTitleId;
	}
	/**
	 * @param jobTitleId The jobTitleId to set.
	 */
	public void setJobTitleId(String jobTitleId) {
		this.jobTitleId = jobTitleId;
	}
	/**
	 * @return Returns the positionTypeId.
	 */
	public String getPositionTypeId() {
		return positionTypeId;
	}
	/**
	 * @param positionTypeId The positionTypeId to set.
	 */
	public void setPositionTypeId(String positionTypeId) {
		this.positionTypeId = positionTypeId;
	}
	/**
	 * @return Returns the programUnitId.
	 */
	public String getProgramUnitId() {
		return programUnitId;
	}
	/**
	 * @param programUnitId The programUnitId to set.
	 */
	public void setProgramUnitId(String programUnitId) {
		this.programUnitId = programUnitId;
	}
	/**
	 * @return Returns the userFirstName.
	 */
	public String getUserFirstName() {
		return userFirstName;
	}
	/**
	 * @param userFirstName The userFirstName to set.
	 */
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	/**
	 * @return Returns the userLastName.
	 */
	public String getUserLastName() {
		return userLastName;
	}
	/**
	 * @param userLastName The userLastName to set.
	 */
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
}
