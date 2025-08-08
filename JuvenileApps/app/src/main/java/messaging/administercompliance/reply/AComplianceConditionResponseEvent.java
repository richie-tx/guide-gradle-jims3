/*
 * Created on Dec 03, 2007
 */
package messaging.administercompliance.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 */
public class AComplianceConditionResponseEvent extends ResponseEvent 
{
    private boolean isCompliant;
    private int ncCount;
    private String orderConditionName;
    private String orderConditionDescription;
    private String sprOrderConditionId;
    private String caseNumber;
    private String courtId;
    private String defendantId;
    private String displayCaseNum;
    private String complianceReasonId;
    private String complianceReasonDescription;
	private String groupId;   
    private String sprOrderId;
    private String conditionId;
    private String offenseIndicator;
    private int orderChainNumber;
    
	/**
	 * @return Returns the conditionId.
	 */
	public String getConditionId() {
		return conditionId;
	}
	/**
	 * @param conditionId The conditionId to set.
	 */
	public void setConditionId(String conditionId) {
		this.conditionId = conditionId;
	}
	/**
	 * @return Returns the caseNumber.
	 */
	public String getCaseNumber() {
		return caseNumber;
	}
	/**
	 * @param caseNumber The caseNumber to set.
	 */
	public void setCaseNumber(String caseNumber) {
		this.caseNumber = caseNumber;
	}
	/**
	 * @return Returns the courtId.
	 */
	public String getCourtId() {
		return courtId;
	}
	/**
	 * @param courtId The courtId to set.
	 */
	public void setCourtId(String courtId) {
		this.courtId = courtId;
	}

	/**
	 * @return Returns the isCompliant.
	 */
	public boolean isCompliant() {
		return isCompliant;
	}
	/**
	 * @param isCompliant The isCompliant to set.
	 */
	public void setCompliant(boolean isCompliant) {
		this.isCompliant = isCompliant;
	}
	/**
	 * @return Returns the ncCount.
	 */
	public int getNcCount() {
		return this.ncCount;
	}
	/**
	 * @param ncCount The ncCount to set.
	 */
	public void setNcCount(int ncCount) {
		this.ncCount = ncCount;
	}
	/**
	 * @return Returns the orderConditionDescription.
	 */
	public String getOrderConditionDescription() {
		return orderConditionDescription;
	}
	/**
	 * @param orderConditionDescription The orderConditionDescription to set.
	 */
	public void setOrderConditionDescription(String orderConditionDescription) {
		this.orderConditionDescription = orderConditionDescription;
	}
	/**
	 * @return Returns the orderConditionName.
	 */
	public String getOrderConditionName() {
		return orderConditionName;
	}
	/**
	 * @param orderConditionName The orderConditionName to set.
	 */
	public void setOrderConditionName(String orderConditionName) {
		this.orderConditionName = orderConditionName;
	}
	/**
	 * @return Returns the sprOrderConditionId.
	 */
	public String getSprOrderConditionId() {
		return sprOrderConditionId;
	}
	/**
	 * @param sprOrderConditionId The sprOrderConditionId to set.
	 */
	public void setSprOrderConditionId(String sprOrderConditionId) {
		this.sprOrderConditionId = sprOrderConditionId;
	}
	/**
	 * @return Returns the complianceReasonId.
	 */
	public String getComplianceReasonId() {
		return complianceReasonId;
	}
	/**
	 * @param complianceReasonId The complianceReasonId to set.
	 */
	public void setComplianceReasonId(String complianceReasonId) {
		this.complianceReasonId = complianceReasonId;
	}
	/**
	 * @return Returns the complianceReasonDescription.
	 */
	public String getComplianceReasonDescription() {
		return complianceReasonDescription;
	}
	/**
	 * @param complianceReasonDescription The complianceReasonDescription to set.
	 */
	public void setComplianceReasonDescription(String complianceReasonDescription) {
		this.complianceReasonDescription = complianceReasonDescription;
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
	 * @return the displayCaseNum
	 */
	public String getDisplayCaseNum() {
		return displayCaseNum;
	}
	/**
	 * @param displayCaseNum the displayCaseNum to set
	 */
	public void setDisplayCaseNum(String displayCaseNum) {
		this.displayCaseNum = displayCaseNum;
	}
	/**
	 * @return Returns the groupId.
	 */
	public String getGroupId() {
		return groupId;
	}
	/**
	 * @param groupId The groupId to set.
	 */
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	/**
	 * @return Returns the sprOrderId.
	 */
	public String getSprOrderId() {
		return sprOrderId;
	}
	/**
	 * @param sprOrderId The sprOrderId to set.
	 */
	public void setSprOrderId(String sprOrderId) {
		this.sprOrderId = sprOrderId;
	}
	/**
	 * @return Returns the offenseIndicator.
	 */
	public String getOffenseIndicator() {
		return offenseIndicator;
	}
	/**
	 * @param offenseIndicator The offenseIndicator to set.
	 */
	public void setOffenseIndicator(String offenseIndicator) {
		this.offenseIndicator = offenseIndicator;
	}
	/**
	 * @return Returns the orderChainNumber.
	 */
	public int getOrderChainNumber() {
		return orderChainNumber;
	}
	/**
	 * @param orderChainNumber The orderChainNumber to set.
	 */
	public void setOrderChainNumber(int orderChainNumber) {
		this.orderChainNumber = orderChainNumber;
	}
}
