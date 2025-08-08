package ui.supervision.administercaseload.form;

import java.util.ArrayList;
import java.util.List;

public class SuperviseeTransferCasesInfo {
	/**
	 * Case selected by user for updating transfer history
	 */
	private String caseNumSelectedForTransferUpdate;
	/**
	 * List of supervisee cases to be transfered in/out from Harris county.
	 */
	private List casesToTransfer;
	/**
	 * List of out of county tranfer records which includes current and historical records
	 */	
	private List courtesyCases;
	/**
	 * List of harris county tranfer records which includes current and historical records
	 */
	private List harrisCountyCases;

	public SuperviseeTransferCasesInfo(SuperviseeTransferCasesInfo oldObj) {
		super();
		this.otherCaseNum = oldObj.getOtherCaseNum();
		this.personId = oldObj.getPersonId();
		this.rejected = oldObj.isRejected();
		this.rejectedAsStr = oldObj.getRejectedAsStr();
		this.transferInDate = oldObj.getTransferInDate();
		this.transferOutDate = oldObj.getTransferOutDate();
		this.transferStateId = oldObj.getTransferStateId();
		this.transferStateName = oldObj.getTransferStateName();
		this.transferTxCountyId = oldObj.getTransferTxCountyId();
		this.transferTxCountyName = oldObj.getTransferTxCountyName();
	}

	private String otherCaseNum;
	/**
	 * Supervisee identifier in other county's/state's system. Used during transfer in.
	 */
	private String personId;
	/**
	 * Flag to indicate if a Harris county transfer case was rejected by other county/state.
	 */
	private boolean rejected;
	private String rejectedAsStr;

	public String getRejectedAsStr() {
		return rejectedAsStr;
	}
	public void setRejectedAsStr(String rejectedAsStr) {
		this.rejectedAsStr = rejectedAsStr;
	}

	private String transferInClassificationSeqNum;

	/**
	 * Date on which a Harris county case is trasfered back from other county/state to 
	 * Harris county.
	 */
	private String transferInDate;
	private String transferInSubclassificationSeqNum;
	private String transferOutClassificationSeqNum;
	/**
	 * Date on which a Harris county case is transfer to another county or state.
	 */
	private String transferOutDate;

	private String transferOutSubclassificationSeqNum;
	/**
	 * Id of the other state to which Harris county case is being transfered.
	 */
	private String transferStateId;
	/**
	 * Name of the other state to which Harris county case is being transfered.
	 */
	private String transferStateName;
	/**
	 * Id of the other Texas county to which Harris county case is being transfered. 
	 */
	private String transferTxCountyId;
	/**
	 * Name of the other Texas county to which Harris county case is being transfered. 
	 */
	private String transferTxCountyName;

	/**
	 * Type of transfer i.e. transfer in or transfer out
	 */
	private String transferType;

	public SuperviseeTransferCasesInfo() {		
		clear();
	}
	public void clear() {
		transferOutDate = "";
		transferTxCountyName = "";
		transferStateName = "";
		transferTxCountyId = "";
		transferStateId = "";
		transferInDate = "";
		rejected = false;
		rejectedAsStr = "No";
		personId = "";	
		otherCaseNum = "";
		transferType = "";		
		casesToTransfer = new ArrayList();
		caseNumSelectedForTransferUpdate = "";
		harrisCountyCases = new ArrayList();
		courtesyCases = new ArrayList();
	}
	/**
	 * @return the caseNumSelectedForTransferUpdate
	 */
	public String getCaseNumSelectedForTransferUpdate() {
		return caseNumSelectedForTransferUpdate;
	}

	/**
	 * @return the casesToTransfer
	 */
	public List getCasesToTransfer() {
		return casesToTransfer;
	}
	/**
	 * @return the courtseyCases
	 */
	public List getCourtesyCases() {
		return courtesyCases;
	}
	/**
	 * @return the harrisCountyCases
	 */
	public List getHarrisCountyCases() {
		return harrisCountyCases;
	}

	public String getOtherCaseNum() {
		return otherCaseNum;
	}
	/**
	 * @return the personId
	 */
	public String getPersonId() {
		return personId;
	}
	public String getTransferInClassificationSeqNum() {
		return transferInClassificationSeqNum;
	}	
	/**
	 * @return the transferInDate
	 */
	public String getTransferInDate() {
		return transferInDate;
	}	
	public String getTransferInSubclassificationSeqNum() {
		return transferInSubclassificationSeqNum;
	}	
	public String getTransferOutClassificationSeqNum() {
		return transferOutClassificationSeqNum;
	}
	/**
	 * @return the transferOutDate
	 */
	public String getTransferOutDate() {
		return transferOutDate;
	}
	public String getTransferOutSubclassificationSeqNum() {
		return transferOutSubclassificationSeqNum;
	}
	
	/**
	 * @return the transferOutStateId
	 */
	public String getTransferStateId() {
		return transferStateId;
	}
	
	/**
	 * @return the transferOutStateName
	 */
	public String getTransferStateName() {
		return transferStateName;
	}

	/**
	 * @return the transferOutTxCountyId
	 */
	public String getTransferTxCountyId() {
		return transferTxCountyId;
	}

	/**
	 * @return the transferOutTxCountyName
	 */
	public String getTransferTxCountyName() {
		return transferTxCountyName;
	}

	/**
	 * @return the transferType
	 */
	public String getTransferType() {
		return transferType;
	}

	/**
	 * @return the isRejected
	 */
	public boolean isRejected() {
		return rejected;
	}

	/**
	 * @param caseNumSelectedForTransferUpdate the caseNumSelectedForTransferUpdate to set
	 */
	public void setCaseNumSelectedForTransferUpdate(
			String caseNumSelectedForTransferUpdate) {
		this.caseNumSelectedForTransferUpdate = caseNumSelectedForTransferUpdate;
	}

	/**
	 * @param casesToTransfer the casesToTransfer to set
	 */
	public void setCasesToTransfer(List casesToTransfer) {
		this.casesToTransfer = casesToTransfer;
	}

	/**
	 * @param courtseyCases the courtseyCases to set
	 */
	public void setCourtesyCases(List courtesyCases) {
		this.courtesyCases = courtesyCases;
	}

	/**
	 * @param harrisCountyCases the harrisCountyCases to set
	 */
	public void setHarrisCountyCases(List harrisCountyCases) {
		this.harrisCountyCases = harrisCountyCases;
	}

	public void setOtherCaseNum(String otherCaseNum) {
		this.otherCaseNum = otherCaseNum;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(String personId) {
		this.personId = personId;
	}

	/**
	 * @param isRejected the isRejected to set
	 */
	public void setRejected(boolean isRejected) {
		this.rejected = isRejected;
	}

	public void setTransferInClassificationSeqNum(String seqNum) {
		this.transferInClassificationSeqNum = seqNum;
	}

	/**
	 * @param transferInDate the transferInDate to set
	 */
	public void setTransferInDate(String transferInDate) {
		this.transferInDate = transferInDate;
	}

	public void setTransferInSubclassificationSeqNum(
			String transferInSubclassificationSeqNum) {
		this.transferInSubclassificationSeqNum = transferInSubclassificationSeqNum;
	}

	public void setTransferOutClassificationSeqNum(String transferOutSeqNum) {
		this.transferOutClassificationSeqNum = transferOutSeqNum;
	}

	/**
	 * @param transferOutDate the transferOutDate to set
	 */
	public void setTransferOutDate(String transferOutDate) {
		this.transferOutDate = transferOutDate;
	}

	public void setTransferOutSubclassificationSeqNum(
			String transferOutSubclassificationSeqNum) {
		this.transferOutSubclassificationSeqNum = transferOutSubclassificationSeqNum;
	}

	/**
	 * @param transferOutStateId the transferOutStateId to set
	 */
	public void setTransferStateId(String transferOutStateId) {
		this.transferStateId = transferOutStateId;
	}

	/**
	 * @param transferOutStateName the transferOutStateName to set
	 */
	public void setTransferStateName(String transferOutStateName) {
		this.transferStateName = transferOutStateName;
	}

	/**
	 * @param transferOutTxCountyId the transferOutTxCountyId to set
	 */
	public void setTransferTxCountyId(String transferOutTxCountyId) {
		this.transferTxCountyId = transferOutTxCountyId;
	}

	/**
	 * @param transferOutTxCountyName the transferOutTxCountyName to set
	 */
	public void setTransferTxCountyName(String transferOutTxCountyName) {
		this.transferTxCountyName = transferOutTxCountyName;
	}

	/**
	 * @param transferType the transferType to set
	 */
	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

}
