/*
 * Created on Apr 25, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.administercompliance.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NCFeeResponseEvent extends ResponseEvent{
	private String payType;
	private String amountOrdered;
	private String paidToDate;
	private String delinquentAmount;
	private String ncResponseId;
	private String feeId;
	private boolean manualAdded;

	
	/**
	 * @return the manualAdded
	 */
	public boolean isManualAdded() {
		return manualAdded;
	}
	/**
	 * @param manualAdded the manualAdded to set
	 */
	public void setManualAdded(boolean manualAdded) {
		this.manualAdded = manualAdded;
	}
	/**
	 * @return Returns the amountOrdered.
	 */
	public String getAmountOrdered() {
		return amountOrdered;
	}
	/**
	 * @param amountOrdered The amountOrdered to set.
	 */
	public void setAmountOrdered(String amountOrdered) {
		this.amountOrdered = amountOrdered;
	}
	/**
	 * @return Returns the delinquentAmount.
	 */
	public String getDelinquentAmount() {
		return delinquentAmount;
	}
	/**
	 * @param delinquentAmount The delinquentAmount to set.
	 */
	public void setDelinquentAmount(String delinquentAmount) {
		this.delinquentAmount = delinquentAmount;
	}
	/**
	 * @return Returns the feeId.
	 */
	public String getFeeId() {
		return feeId;
	}
	/**
	 * @param feeId The feeId to set.
	 */
	public void setFeeId(String feeId) {
		this.feeId = feeId;
	}
	/**
	 * @return Returns the ncResponseId.
	 */
	public String getNcResponseId() {
		return ncResponseId;
	}
	/**
	 * @param ncResponseId The ncResponseId to set.
	 */
	public void setNcResponseId(String ncResponseId) {
		this.ncResponseId = ncResponseId;
	}
	/**
	 * @return Returns the paidToDate.
	 */
	public String getPaidToDate() {
		return paidToDate;
	}
	/**
	 * @param paidToDate The paidToDate to set.
	 */
	public void setPaidToDate(String paidToDate) {
		this.paidToDate = paidToDate;
	}
	/**
	 * @return Returns the payType.
	 */
	public String getPayType() {
		return payType;
	}
	/**
	 * @param payType The payType to set.
	 */
	public void setPayType(String payType) {
		this.payType = payType;
	}
}
