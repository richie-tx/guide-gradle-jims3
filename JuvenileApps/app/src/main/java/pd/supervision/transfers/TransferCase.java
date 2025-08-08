package pd.supervision.transfers;

import mojo.km.persistence.PersistentObject;

public class TransferCase extends PersistentObject {
	/**
	 * Legacy database action type
	 */
	private String actionType;
	/**
	 * Court division indicator of criminal case
	 */
	private String cdi;
	/**
	 * Legacy database record sequence number
	 */
	private String cicsRecordClassSeqNumber;
	private String cicsRecordSubclassSeqNumber;
	/**
	 * Criminal case number in Harris county
	 */
	private String criminalCaseId;
	
	private String operatorId;
	/**
	 * Id of defendant in the originating county
	 */
	private String originationCountyPersonId;
	/**
	 * Criminal case number in other county.
	 */
	private String otherCountyCaseId;
	/**
	 * Receiving county or state code
	 */
	private String receivingCountyCode;
	/**
	 * Case rejection flag
	 */
	private String rejected;
	/**
	 * Id of defendant in the supervising county
	 */
	private String supervisingCountyPersonId;
	/**
	 * Date on which a case was transfered-out or transfered-in
	 */
	private String transactionDate;
	/**
	 * Transferring county or state code
	 */
	private String transferringCountyCode;
	/**
	 * Transfer type. 1 = Transfer Out, 4 = Transfer In
	 */
	private String transferType;
	
	/**
	 * @return the actionType
	 */
	public String getActionType() {
        fetch();
		return actionType;
	}
	/**
	 * @return the cdi
	 */
	public String getCdi() {
        fetch();
		return cdi;
	}
	/**
	 * @return the cicsRecordSeqNumber
	 */
	public String getCicsRecordClassSeqNumber() {
        fetch();
		return cicsRecordClassSeqNumber;
	}
	public String getCicsRecordSubclassSeqNumber() {
		fetch();
		return cicsRecordSubclassSeqNumber;
	}
	/**
	 * @return the criminalCaseId
	 */
	public String getCriminalCaseId() {
        fetch();
		return criminalCaseId;
	}
	/**
	 * @return the originationCountyPersonId
	 */
	public String getOriginationCountyPersonId() {
        fetch();
		return originationCountyPersonId;
	}
	/**
	 * @return the otherCountyCaseId
	 */
	public String getOtherCountyCaseId() {
        fetch();
		return otherCountyCaseId;
	}
	/**
	 * @return the receivingCountyCode
	 */
	public String getReceivingCountyCode() {
        fetch();
		return receivingCountyCode;
	}
	/**
	 * @return the rejected
	 */
	public String getRejected() {
        fetch();
		return rejected;
	}
	/**
	 * @return the supervisingCountyPersonId
	 */
	public String getSupervisingCountyPersonId() {
        fetch();
		return supervisingCountyPersonId;
	}
	/**
	 * @return the transactionDate
	 */
	public String getTransactionDate() {
        fetch();
		return transactionDate;
	}
	/**
	 * @return the transferringCountCode
	 */
	public String getTransferringCountyCode() {
        fetch();
		return transferringCountyCode;
	}
	/**
	 * @return the transferType
	 */
	public String getTransferType() {
        fetch();
		return transferType;
	}
	/**
	 * @param actionType the actionType to set
	 */
	public void setActionType(String actionType) {
        if (this.actionType == null || !this.actionType.equals(actionType)) {
            markModified();
        }		
		this.actionType = actionType;
	}
	/**
	 * @param cdi the cdi to set
	 */
	public void setCdi(String cdi) {
        if (this.cdi == null || !this.cdi.equals(cdi)) {
            markModified();
        }		
		this.cdi = cdi;
	}
	/**
	 * @param cicsRecordSeqNumber the cicsRecordSeqNumber to set
	 */
	public void setCicsRecordClassSeqNumber(String cicsRecordClassSeqNumber) {
        if (this.cicsRecordClassSeqNumber == null || !this.cicsRecordClassSeqNumber.equals(cicsRecordClassSeqNumber)) {
            markModified();
        }		
		this.cicsRecordClassSeqNumber = cicsRecordClassSeqNumber;
	}
	public void setCicsRecordSubclassSeqNumber(String cicsRecordSubclassSeqNumber) {
        if (this.cicsRecordSubclassSeqNumber == null || !this.cicsRecordSubclassSeqNumber.equals(cicsRecordSubclassSeqNumber)) {
            markModified();
        }		
		this.cicsRecordSubclassSeqNumber = cicsRecordSubclassSeqNumber;
	}
	/**
	 * @param criminalCaseId the criminalCaseId to set
	 */
	public void setCriminalCaseId(String criminalCaseId) {
        if (this.criminalCaseId == null || !this.criminalCaseId.equals(criminalCaseId)) {
            markModified();
        }		
		this.criminalCaseId = criminalCaseId;
	}
	/**
	 * @param originationCountyPersonId the originationCountyPersonId to set
	 */
	public void setOriginationCountyPersonId(String originationCountyPersonId) {
        if (this.originationCountyPersonId == null || !this.originationCountyPersonId.equals(originationCountyPersonId)) {
            markModified();
        }		
		this.originationCountyPersonId = originationCountyPersonId;
	}
	/**
	 * @param otherCountyCaseId the otherCountyCaseId to set
	 */
	public void setOtherCountyCaseId(String otherCountyCaseId) {
        if (this.otherCountyCaseId == null || !this.otherCountyCaseId.equals(otherCountyCaseId)) {
            markModified();
        }		
		this.otherCountyCaseId = otherCountyCaseId;
	}
	/**
	 * @param receivingCountyCode the receivingCountyCode to set
	 */
	public void setReceivingCountyCode(String receivingCountyCode) {
        if (this.receivingCountyCode == null || !this.receivingCountyCode.equals(receivingCountyCode)) {
            markModified();
        }		
		this.receivingCountyCode = receivingCountyCode;
	}
	/**
	 * @param rejected the rejected to set
	 */
	public void setRejected(String rejected) {
        if (this.rejected == null || !this.rejected.equals(rejected)) {
            markModified();
        }		
		this.rejected = rejected;
	}
	/**
	 * @param supervisingCountyPersonId the supervisingCountyPersonId to set
	 */
	public void setSupervisingCountyPersonId(String supervisingCountyPersonId) {
        if (this.supervisingCountyPersonId == null || !this.supervisingCountyPersonId.equals(supervisingCountyPersonId)) {
            markModified();
        }		
		this.supervisingCountyPersonId = supervisingCountyPersonId;
	}
	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(String transactionDate) {
        if (this.transactionDate == null || !this.transactionDate.equals(transactionDate)) {
            markModified();
        }		
		this.transactionDate = transactionDate;
	}
	/**
	 * @param transferringCountCode the transferringCountCode to set
	 */
	public void setTransferringCountyCode(String transferringCountyCode) {
        if (this.transferringCountyCode == null || !this.transferringCountyCode.equals(transferringCountyCode)) {
            markModified();
        }		
		this.transferringCountyCode = transferringCountyCode;
	}
	/**
	 * @param transferType the transferType to set
	 */
	public void setTransferType(String transferType) {
        if (this.transferType == null || !this.transferType.equals(transferType)) {
            markModified();
        }		
		this.transferType = transferType;
	}
	/**
	 * 
	 * @return
	 */
	public String getOperatorId() {
		
		fetch();
		return operatorId;
	}
	
	/**
	 * 
	 * @param operatorId
	 */
	public void setOperatorId(String operatorId) {
		
		if (this.operatorId == null || !this.operatorId.equals(operatorId)) {
            markModified();
        }	
		
		this.operatorId = operatorId;
	}
	
}
