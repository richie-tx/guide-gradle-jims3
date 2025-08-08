package messaging.administercaseload;


import java.util.List;

import mojo.km.messaging.ResponseEvent;

public class CloseCaseResponseEvent extends ResponseEvent
{
	private String defendantId;
	private String criminalCaseId;
	private String result;
	private List failureReasonsList;
	
	
	
	/**
	 * @return the defendantId
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId the defendantId to set
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	/**
	 * @return the criminalCaseId
	 */
	public String getCriminalCaseId() {
		return criminalCaseId;
	}
	/**
	 * @param criminalCaseId the criminalCaseId to set
	 */
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return the failureReasonsList
	 */
	public List getFailureReasonsList() {
		return failureReasonsList;
	}
	/**
	 * @param failureReasonsList the failureReasonsList to set
	 */
	public void setFailureReasonsList(List failureReasonsList) {
		this.failureReasonsList = failureReasonsList;
	}
}
