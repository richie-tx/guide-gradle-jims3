package messaging.administerassessments;

import mojo.km.messaging.RequestEvent;


public class GetAssessmentDetailsEvent extends RequestEvent 
{
	private String defendantId;
	
	private String assessmentTypeId;
	private boolean isInitial;
	private String masterAssessmentId;
	private String assessmentId; //OID
	private boolean isAssessmentCreate;
	private boolean isAssessmentUpdate;
	private boolean isAssessmentDetails;
	private boolean isRetrievePriorVersion;	
	
	
	/**
	 * @return Returns the assessmentId.
	 */
	public String getAssessmentId() {
		return assessmentId;
	}
	/**
	 * @param assessmentId The assessmentId to set.
	 */
	public void setAssessmentId(String assessmentId) {
		this.assessmentId = assessmentId;
	}
	/**
	 * @return Returns the assessmentTypeId.
	 */
	public String getAssessmentTypeId() {
		return assessmentTypeId;
	}
	/**
	 * @param assessmentTypeId The assessmentTypeId to set.
	 */
	public void setAssessmentTypeId(String assessmentTypeId) {
		this.assessmentTypeId = assessmentTypeId;
	}
	/**
	 * @return Returns the defendantId.
	 */
	public String getDefendantId() {
		return defendantId;
	}
	/**
	 * @param defendantId The defendantId to set.
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	/**
	 * @return Returns the isAssessmentCreate.
	 */
	public boolean isAssessmentCreate() {
		return isAssessmentCreate;
	}
	/**
	 * @param isAssessmentCreate The isAssessmentCreate to set.
	 */
	public void setAssessmentCreate(boolean isAssessmentCreate) {
		this.isAssessmentCreate = isAssessmentCreate;
	}
	/**
	 * @return Returns the isAssessmentDetails.
	 */
	public boolean isAssessmentDetails() {
		return isAssessmentDetails;
	}
	/**
	 * @param isAssessmentDetails The isAssessmentDetails to set.
	 */
	public void setAssessmentDetails(boolean isAssessmentDetails) {
		this.isAssessmentDetails = isAssessmentDetails;
	}
	/**
	 * @return Returns the isAssessmentUpdate.
	 */
	public boolean isAssessmentUpdate() {
		return isAssessmentUpdate;
	}
	/**
	 * @param isAssessmentUpdate The isAssessmentUpdate to set.
	 */
	public void setAssessmentUpdate(boolean isAssessmentUpdate) {
		this.isAssessmentUpdate = isAssessmentUpdate;
	}
	/**
	 * @return Returns the isInitial.
	 */
	public boolean isInitial() {
		return isInitial;
	}
	/**
	 * @param isInitial The isInitial to set.
	 */
	public void setInitial(boolean isInitial) {
		this.isInitial = isInitial;
	}
	/**
	 * @return Returns the isRetrievePriorVersion.
	 */
	public boolean isRetrievePriorVersion() {
		return isRetrievePriorVersion;
	}
	/**
	 * @param isRetrievePriorVersion The isRetrievePriorVersion to set.
	 */
	public void setRetrievePriorVersion(boolean isRetrievePriorVersion) {
		this.isRetrievePriorVersion = isRetrievePriorVersion;
	}
	/**
	 * @return Returns the masterAssessmentId.
	 */
	public String getMasterAssessmentId() {
		return masterAssessmentId;
	}
	/**
	 * @param masterAssessmentId The masterAssessmentId to set.
	 */
	public void setMasterAssessmentId(String masterAssessmentId) {
		this.masterAssessmentId = masterAssessmentId;
	}
}
