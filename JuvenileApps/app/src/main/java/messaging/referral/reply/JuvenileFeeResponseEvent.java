/*
 * Created on July 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.referral.reply;

import java.util.Date;

import mojo.km.messaging.ResponseEvent;

/**
 * @author UGopinath
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JuvenileFeeResponseEvent extends ResponseEvent {
	
	private String feeType; 
	private Date dueDate; 
	private Double totalDue; 
	private String status; 
	private Double currentBalance;
	private Double totalPaid;
	private Date receivedDate;
	private String payorType;
	private String transactionNum;
	private String caseNum;
	private String payorId;
	private Date paidDate;
	private String codeId;
	
	
	/**
	 * @return Returns the dueDate.
	 */
	public Date getDueDate() {
		return dueDate;
	}
	/**
	 * @param dueDate The dueDate to set.
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	/**
	 * @return Returns the feeType.
	 */
	public String getFeeType() {
		return feeType;
	}
	/**
	 * @param feeType The feeType to set.
	 */
	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}
	/**
	 * @return Returns the receivedDate.
	 */
	public Date getReceivedDate() {
		return receivedDate;
	}
	/**
	 * @param receivedDate The receivedDate to set.
	 */
	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}
	/**
	 * @return Returns the status.
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status The status to set.
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	/**
	 * @return Returns the payorType.
	 */
	public String getPayorType() {
		return payorType;
	}
	/**
	 * @param payorType The payorType to set.
	 */
	public void setPayorType(String payorType) {
		this.payorType = payorType;
	}
	/**
	 * @return Returns the transactionNum.
	 */
	public String getTransactionNum() {
		return transactionNum;
	}
	/**
	 * @param transactionNum The transactionNum to set.
	 */
	public void setTransactionNum(String transactionNum) {
		this.transactionNum = transactionNum;
	}
	/**
	 * @return Returns the payorId.
	 */
	public String getPayorId() {
		return payorId;
	}
	/**
	 * @param payorId The payorId to set.
	 */
	public void setPayorId(String payorId) {
		this.payorId = payorId;
	}
	/**
	 * @return Returns the caseNum.
	 */
	public String getCaseNum() {
		return caseNum;
	}
	/**
	 * @param caseNum The caseNum to set.
	 */
	public void setCaseNum(String caseNum) {
		this.caseNum = caseNum;
	}
	
	/**
	 * @return Returns the paidDate.
	 */
	public Date getPaidDate() {
		return paidDate;
	}
	/**
	 * @param paidDate The paidDate to set.
	 */
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	/**
	 * @return Returns the codeId.
	 */
	public String getCodeId() {
		return codeId;
	}
	/**
	 * @param codeId The codeId to set.
	 */
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	/**
	 * @return Returns the currentBalance.
	 */
	public Double getCurrentBalance() {
		return currentBalance;
	}
	/**
	 * @param currentBalance The currentBalance to set.
	 */
	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}
	/**
	 * @return Returns the totalDue.
	 */
	public Double getTotalDue() {
		return totalDue;
	}
	/**
	 * @param totalDue The totalDue to set.
	 */
	public void setTotalDue(Double totalDue) {
		this.totalDue = totalDue;
	}
	/**
	 * @return Returns the totalPaid.
	 */
	public Double getTotalPaid() {
		return totalPaid;
	}
	/**
	 * @param totalPaid The totalPaid to set.
	 */
	public void setTotalPaid(Double totalPaid) {
		this.totalPaid = totalPaid;
	}
}
