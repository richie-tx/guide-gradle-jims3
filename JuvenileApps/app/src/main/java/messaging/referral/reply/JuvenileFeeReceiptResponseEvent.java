/*
 * Created on July 13, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.referral.reply;

import java.util.Date;

import messaging.contact.to.PhoneNumberBean;
import mojo.km.messaging.ResponseEvent;

/**
 * @author UGopinath
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JuvenileFeeReceiptResponseEvent extends ResponseEvent {
	
	private String feeType; 
	private String payor; 
	private String payorType; 
	private String payorAddress; 
	private PhoneNumberBean payorPhone;
	private String petetionNum;
	private String receiptNum;
	private Date paidDate;
	private String amountPaid;
	private String tansactionNum;
	private Date receivedDate;
	private String feeStatus;
	private String codeId;
	
	/**
	 * @return Returns the amountPaid.
	 */
	public String getAmountPaid() {
		return amountPaid;
	}
	/**
	 * @param amountPaid The amountPaid to set.
	 */
	public void setAmountPaid(String amountPaid) {
		this.amountPaid = amountPaid;
	}
	/**
	 * @return Returns the feeStatus.
	 */
	public String getFeeStatus() {
		return feeStatus;
	}
	/**
	 * @param feeStatus The feeStatus to set.
	 */
	public void setFeeStatus(String feeStatus) {
		this.feeStatus = feeStatus;
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
	 * @return Returns the payor.
	 */
	public String getPayor() {
		return payor;
	}
	/**
	 * @param payor The payor to set.
	 */
	public void setPayor(String payor) {
		this.payor = payor;
	}
	/**
	 * @return Returns the payorAddress.
	 */
	public String getPayorAddress() {
		return payorAddress;
	}
	/**
	 * @param payorAddress The payorAddress to set.
	 */
	public void setPayorAddress(String payorAddress) {
		this.payorAddress = payorAddress;
	}
	/**
	 * @return Returns the payorPhone.
	 */
	public PhoneNumberBean getPayorPhone() {
		return payorPhone;
	}
	/**
	 * @param payorPhone The payorPhone to set.
	 */
	public void setPayorPhone(PhoneNumberBean payorPhone) {
		this.payorPhone = payorPhone;
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
	 * @return Returns the petetionNum.
	 */
	public String getPetetionNum() {
		return petetionNum;
	}
	/**
	 * @param petetionNum The petetionNum to set.
	 */
	public void setPetetionNum(String petetionNum) {
		this.petetionNum = petetionNum;
	}
	/**
	 * @return Returns the receiptNum.
	 */
	public String getReceiptNum() {
		return receiptNum;
	}
	/**
	 * @param receiptNum The receiptNum to set.
	 */
	public void setReceiptNum(String receiptNum) {
		this.receiptNum = receiptNum;
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
	 * @return Returns the tansactionNum.
	 */
	public String getTansactionNum() {
		return tansactionNum;
	}
	/**
	 * @param tansactionNum The tansactionNum to set.
	 */
	public void setTansactionNum(String tansactionNum) {
		this.tansactionNum = tansactionNum;
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
}
