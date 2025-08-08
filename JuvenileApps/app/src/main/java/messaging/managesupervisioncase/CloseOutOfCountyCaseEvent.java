//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\managesupervisioncase\\GetOutOfCountyCaseEvent.java

package messaging.managesupervisioncase;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

public class CloseOutOfCountyCaseEvent extends RequestEvent
{
	private String caseNum;
	private String courtDivisionId;
	private String dispositionTypeId;
	private Date dispositionDate;
//	reasonForUpdateId = null(Close Case) and not null(Update Case Closure)
	private String reasonForUpdateId;
	private String previousDispositionTypeId;
	private boolean lastCaseForJurisdiction;
	

	/**
	 * @roseuid 4447D37C0108
	 */
	public CloseOutOfCountyCaseEvent()
	{

	}

	/**
	 * @param aCaseNum
	 * @roseuid 4447C367002D
	 */
	public void setCaseNum(String aCaseNum)
	{
		caseNum = aCaseNum;
	}

	/**
	 * @return String
	 * @roseuid 4447C3670036
	 */
	public String getCaseNum()
	{
		return caseNum;
	}

	/**
	 * @param aCourtDivisionId
	 * @roseuid 4447C3670038
	 */
	public void setCourtDivisionId(String aCourtDivisionId)
	{
		courtDivisionId = aCourtDivisionId;
	}

	/**
	 * @return String
	 * @roseuid 4447C3670041
	 */
	public String getCourtDivisionId()
	{
		return courtDivisionId;
	}

	/**
	 * @return the dispositionDate
	 */
	public Date getDispositionDate() {
		return dispositionDate;
	}

	/**
	 * @param dispositionDate the dispositionDate to set
	 */
	public void setDispositionDate(Date dispositionDate) {
		this.dispositionDate = dispositionDate;
	}

	/**
	 * @return the dispositionTypeId
	 */
	public String getDispositionTypeId() {
		return dispositionTypeId;
	}

	/**
	 * @param dispositionTypeId the dispositionTypeId to set
	 */
	public void setDispositionTypeId(String dispositionTypeId) {
		this.dispositionTypeId = dispositionTypeId;
	}

	/**
	 * @return the reasonForUpdateId
	 */
	public String getReasonForUpdateId() {
		return reasonForUpdateId;
	}

	/**
	 * @param reasonForUpdateId the reasonForUpdateId to set
	 */
	public void setReasonForUpdateId(String reasonForUpdateId) {
		this.reasonForUpdateId = reasonForUpdateId;
	}

	/**
	 * @return the previousDispositionTypeId
	 */
	public String getPreviousDispositionTypeId() {
		return previousDispositionTypeId;
	}

	/**
	 * @param previousDispositionTypeId the previousDispositionTypeId to set
	 */
	public void setPreviousDispositionTypeId(String previousDispositionTypeId) {
		this.previousDispositionTypeId = previousDispositionTypeId;
	}

	public void setLastCaseForJurisdiction(boolean isLastCase) {
		lastCaseForJurisdiction = isLastCase;
	}

	public boolean isLastCaseForJurisdiction() {
		return lastCaseForJurisdiction;
	}
	
}
