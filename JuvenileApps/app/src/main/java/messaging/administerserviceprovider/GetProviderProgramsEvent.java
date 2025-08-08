//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerserviceprovider\\GetProviderProgramsEvent.java

package messaging.administerserviceprovider;

import mojo.km.messaging.RequestEvent;
import java.util.Date;

public class GetProviderProgramsEvent extends RequestEvent {
	public String programName;
	public Date endDateFrom;
	public Date endDateTo;
	public String statusId;
	public String targetInterventionId;
	private String programScheduleTypeId; //added for U.S #11099
	public String stateProgramCode;
	private String serviceProviderId;
	private String serviveId;
	private String agencyId;
	private String programId;

	/**
	 * @roseuid 450ACF5A0085
	 */
	public GetProviderProgramsEvent() {

	}

	/**
	 * @return Returns the endDateFrom.
	 */
	public Date getEndDateFrom() {
		return endDateFrom;
	}

	/**
	 * @param endDateFrom
	 *            The endDateFrom to set.
	 */
	public void setEndDateFrom(Date endDateFrom) {
		this.endDateFrom = endDateFrom;
	}

	/**
	 * @return Returns the endDateTo.
	 */
	public Date getEndDateTo() {
		return endDateTo;
	}

	/**
	 * @param endDateTo
	 *            The endDateTo to set.
	 */
	public void setEndDateTo(Date endDateTo) {
		this.endDateTo = endDateTo;
	}

	/**
	 * @return Returns the programName.
	 */
	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
	public String getProgramId() {
		return this.programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}
	

	/**
	 * @return Returns the stateProgramCode.
	 */
	public String getStateProgramCode() {
		return stateProgramCode;
	}

	/**
	 * @param stateProgramCode
	 *            The stateProgramCode to set.
	 */
	public void setStateProgramCode(String stateProgramCode) {
		this.stateProgramCode = stateProgramCode;
	}

	/**
	 * @return Returns the statusId.
	 */
	public String getStatusId() {
		return statusId;
	}

	/**
	 * @param statusId
	 *            The statusId to set.
	 */
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}

	/**
	 * @return Returns the targetInterventionId.
	 */
	public String getTargetInterventionId() {
		return targetInterventionId;
	}

	/**
	 * @param targetInterventionId
	 *            The targetInterventionId to set.
	 */
	public void setTargetInterventionId(String targetInterventionId) {
		this.targetInterventionId = targetInterventionId;
	}

	/**
	 * @return Returns the serviceProviderId.
	 */
	public String getServiceProviderId() {
		return serviceProviderId;
	}

	/**
	 * @param serviceProviderId
	 *            The serviceProviderId to set.
	 */
	public void setServiceProviderId(String serviceProviderId) {
		this.serviceProviderId = serviceProviderId;
	}

	/**
	 * @return Returns the serviveId.
	 */
	public String getServiveId() {
		return serviveId;
	}

	/**
	 * @param serviveId
	 *            The serviveId to set.
	 */
	public void setServiveId(String serviveId) {
		this.serviveId = serviveId;
	}

	/**
	 * @return Returns the agencyId.
	 */
	public String getAgencyId() {
		return agencyId;
	}

	/**
	 * @param agencyId
	 *            The agencyId to set.
	 */
	public void setAgencyId(String agencyId) {
		this.agencyId = agencyId;
	}

	/**
	 * @return the programScheduleTypeId
	 */
	public String getProgramScheduleTypeId() {
		return programScheduleTypeId;
	}

	/**
	 * @param programScheduleTypeId
	 *            the programScheduleTypeId to set
	 */
	public void setProgramScheduleTypeId(String programScheduleTypeId) {
		this.programScheduleTypeId = programScheduleTypeId;
	}
}
