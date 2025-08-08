/**
 * 
 */
package messaging.transfers.to;

import java.io.Serializable;
import java.util.Date;

/**
 * @author cc_rbhat
 * 
 */
public class TransferCaseBean implements Serializable {
	/**
	 * Database action type. Values,
	 * A = Add,
	 * C = Change,
	 * D = Delete
	 */
	private String actionType;
	/**
	 * Court division indicator of defendant case
	 */
	private String cdi;
	/**
	 * Sequence number in CICS database
	 */
	private String cicsClassificationSequenceNo;
	private String cicsSubclassificationSequenceNo;
	/**
	 * Criminal case id of defendant
	 */
	private String criminalCaseId;	
	/**
	 * Defendant Id
	 */
	private String defendantId;
	private String otherCountyCaseNum;
	private String otherCountyPersonId;
	/**
	 * Id of the defendant in other county or state.
	 */
	private String personId;
	/**
	 * Id of the county or state receiving the case.
	 */
	private String receivingCountyCode;
	/**
	 * Transfer case rejected by receiving county supervisor. Values,
	 * Y = Yes
	 * N = No 
	 */
	private boolean rejected;
	/**
	 * Date on which a case was transfered in or transfered out.
	 */
	private Date transferDate;
	/**
	 * Id of the county or state transferring the case.
	 */
	private String transferringCountyCode;
	/**
	 * Identifies transfer type i.e. transfer in or transfer out. Values,
	 * 1 = Transfer out
	 * 4 = Transfer in
	 */
	private String transferType;
	/**
	 * @return the actionType
	 */
	public String getActionType() {
		return actionType;
	}
	/**
	 * @return the cdi
	 */
	public String getCdi() {
		return cdi;
	}
	/**
	 * @return the cicsSequenceNo
	 */
	public String getCicsClassificationSequenceNo() {
		return cicsClassificationSequenceNo;
	}
	public String getCicsSubclassificationSequenceNo() {
		return cicsSubclassificationSequenceNo;
	}
	
	/**
	 * @return the criminalCaseId
	 */
	public String getCriminalCaseId() {
		return criminalCaseId;
	}
	/**
	 * @return the defendantId
	 */
	public String getDefendantId() {
		return defendantId;
	}
	public String getOtherCountyCaseNum() {
		return otherCountyCaseNum;
	}
	public String getOtherCountyPersonId() {
		return otherCountyPersonId;
	}
	/**
	 * @return the personId
	 */
	public String getPersonId() {
		return personId;
	}
	/**
	 * @return the receivingCountyId
	 */
	public String getReceivingCountyCode() {
		return receivingCountyCode;
	}
	/**
	 * @return the transactionDate
	 */
	public Date getTransferDate() {
		return transferDate;
	}
	/**
	 * @return the transferingCountyId
	 */
	public String getTransferringCountyCode() {
		return transferringCountyCode;
	}
	/**
	 * @return the transferType
	 */
	public String getTransferType() {
		return transferType;
	}
	/**
	 * @return the rejected
	 */
	public boolean isRejected() {
		return rejected;
	}
	/**
	 * @param actionType the actionType to set
	 */
	public void setActionType(String actionType) {
		this.actionType = actionType;
	}
	/**
	 * @param cdi the cdi to set
	 */
	public void setCdi(String cdi) {
		this.cdi = cdi;
	}
	/**
	 * @param cicsSequenceNo the cicsSequenceNo to set
	 */
	public void setCicsClassificationSequenceNo(String cicsSequenceNo) {
		this.cicsClassificationSequenceNo = cicsSequenceNo;
	}
	public void setCicsSubclassificationSequenceNo(
			String cicsSubclassificationSequenceNo) {
		this.cicsSubclassificationSequenceNo = cicsSubclassificationSequenceNo;
	}
	/**
	 * @param criminalCaseId the criminalCaseId to set
	 */
	public void setCriminalCaseId(String criminalCaseId) {
		this.criminalCaseId = criminalCaseId;
	}
	/**
	 * @param defendantId the defendantId to set
	 */
	public void setDefendantId(String defendantId) {
		this.defendantId = defendantId;
	}
	public void setOtherCountyCaseNum(String otherCountyCaseNum) {
		this.otherCountyCaseNum = otherCountyCaseNum;
	}
	public void setOtherCountyPersonId(String otherCountyPersonId) {
		this.otherCountyPersonId = otherCountyPersonId;
	}
	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	/**
	 * @param receivingCountyId the receivingCountyId to set
	 */
	public void setReceivingCountyCode(String receivingCountyCode) {
		this.receivingCountyCode = receivingCountyCode;
	}
	/**
	 * @param rejected the rejected to set
	 */
	public void setRejected(boolean rejected) {
		this.rejected = rejected;
	}
	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransferDate(Date transferDate) {
		this.transferDate = transferDate;
	}
	/**
	 * @param transferingCountyId the transferingCountyId to set
	 */
	public void setTransferringCountyCode(String transferringCountyCode) {
		this.transferringCountyCode = transferringCountyCode;
	}
	/**
	 * @param transferType the transferType to set
	 */
	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}
}