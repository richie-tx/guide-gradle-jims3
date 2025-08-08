package messaging.juvenilecase.reply;

import java.util.List;

import messaging.identityaddress.domintf.IAddressable;
import mojo.km.messaging.ResponseEvent;

public class JuvenileCasefileEvent extends ResponseEvent implements IAddressable{
	private String juvenileNum;

	private String probationOfficeId;

	private String supervisionNum;

	private String sequenceNum;

	private String supervisionType;

	private String caseStatus;

	private String activationDate;
	
	private String jpoAssignmentDate;

	private String supervisionEndDate;
	
	private String daysLeft;
	
	private List referralNumCourtDates; 

	private boolean isTitleIVNeeded;

	private boolean isMAYSINeeded;

	private boolean isRiskNeeded;

	/**
	 * @return the juvenileNum
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param juvenileNum the juvenileNum to set
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return the probationOfficeId
	 */
	public String getProbationOfficeId() {
		return probationOfficeId;
	}

	/**
	 * @param probationOfficeId the probationOfficeId to set
	 */
	public void setProbationOfficeId(String probationOfficeId) {
		this.probationOfficeId = probationOfficeId;
	}

	/**
	 * @return the supervisionNum
	 */
	public String getSupervisionNum() {
		return supervisionNum;
	}

	/**
	 * @param supervisionNum the supervisionNum to set
	 */
	public void setSupervisionNum(String supervisionNum) {
		this.supervisionNum = supervisionNum;
	}

	/**
	 * @return the sequenceNum
	 */
	public String getSequenceNum() {
		return sequenceNum;
	}

	/**
	 * @param sequenceNum the sequenceNum to set
	 */
	public void setSequenceNum(String sequenceNum) {
		this.sequenceNum = sequenceNum;
	}

	/**
	 * @return the supervisionType
	 */
	public String getSupervisionType() {
		return supervisionType;
	}

	/**
	 * @param supervisionType the supervisionType to set
	 */
	public void setSupervisionType(String supervisionType) {
		this.supervisionType = supervisionType;
	}

	/**
	 * @return the caseStatus
	 */
	public String getCaseStatus() {
		return caseStatus;
	}

	/**
	 * @param caseStatus the caseStatus to set
	 */
	public void setCaseStatus(String caseStatus) {
		this.caseStatus = caseStatus;
	}

	/**
	 * @return the activationDate
	 */
	public String getActivationDate() {
		return activationDate;
	}

	/**
	 * @param activationDate the activationDate to set
	 */
	public void setActivationDate(String activationDate) {
		this.activationDate = activationDate;
	}

	/**
	 * @return the supervisionEndDate
	 */
	public String getSupervisionEndDate() {
		return supervisionEndDate;
	}

	/**
	 * @param supervisionEndDate the supervisionEndDate to set
	 */
	public void setSupervisionEndDate(String supervisionEndDate) {
		this.supervisionEndDate = supervisionEndDate;
	}

	/**
	 * @return the daysLeft
	 */
	public String getDaysLeft() {
		return daysLeft;
	}

	/**
	 * @param daysLeft the daysLeft to set
	 */
	public void setDaysLeft(String daysLeft) {
		this.daysLeft = daysLeft;
	}

	
	/**
	 * @return the referralNumCourtDates
	 */
	public List getReferralNumCourtDates() {
		return referralNumCourtDates;
	}

	/**
	 * @param referralNumCourtDates the referralNumCourtDates to set
	 */
	public void setReferralNumCourtDates(List referralNumCourtDates) {
		this.referralNumCourtDates = referralNumCourtDates;
	}

	/**
	 * @return the isMAYSINeeded
	 */
	public boolean isMAYSINeeded() {
		return isMAYSINeeded;
	}

	/**
	 * @param isMAYSINeeded the isMAYSINeeded to set
	 */
	public void setMAYSINeeded(boolean isMAYSINeeded) {
		this.isMAYSINeeded = isMAYSINeeded;
	}

	/**
	 * @return the isTitleIVNeeded
	 */
	public boolean isTitleIVNeeded() {
		return isTitleIVNeeded;
	}

	/**
	 * @param isTitleIVNeeded the isTitleIVNeeded to set
	 */
	public void setTitleIVNeeded(boolean isTitleIVNeeded) {
		this.isTitleIVNeeded = isTitleIVNeeded;
	}

	/**
	 * @return the isRiskNeeded
	 */
	public boolean isRiskNeeded() {
		return isRiskNeeded;
	}

	/**
	 * @param isRiskNeeded the isRiskNeeded to set
	 */
	public void setRiskNeeded(boolean isRiskNeeded) {
		this.isRiskNeeded = isRiskNeeded;
	}

	public String getJpoAssignmentDate() {
		return jpoAssignmentDate;
	}

	public void setJpoAssignmentDate(String jpoAssignmentDate) {
		this.jpoAssignmentDate = jpoAssignmentDate;
	}

}
