package messaging.administercaseload;

import mojo.km.messaging.RequestEvent;

public class GetCaseloadSummaryEvent extends RequestEvent {
	private String supervisorPositionId;

	private String officerPositionId;

	/**
	 * @roseuid 46435FB300F8
	 */
	public GetCaseloadSummaryEvent() {

	}

	/**
	 * @return Returns the supervisorPositionId.
	 */
	public String getSupervisorPositionId() {
		return supervisorPositionId;
	}

	/**
	 * @param supervisor_position_id
	 */
	public void setSupervisorPositionId(String aSupervisorPositionId) {
		this.supervisorPositionId = aSupervisorPositionId;
	}

	/**
	 * @return Returns the officerPositionId.
	 */
	public String getOfficerPositionId() {
		return officerPositionId;
	}

	/**
	 * @param officerPositionId
	 *            The officerPositionId to set.
	 */
	public void setOfficerPositionId(String officerPositionId) {
		this.officerPositionId = officerPositionId;
	}
}
