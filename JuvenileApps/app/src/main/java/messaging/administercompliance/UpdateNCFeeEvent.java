//Source file: C:\\views\\CommonSupervision\\app\\src\\messaging\\administerviolationreport\\UpdateNCResponseEvent.java

package messaging.administercompliance;

import mojo.km.messaging.RequestEvent;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UpdateNCFeeEvent extends RequestEvent
{
    private String payType;
    private String amountOrdered;
    private String paidToDate;
    private String amountDelinquent;
    private String ncFeeId;
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
	 * @return Returns the amountDelinquent.
	 */
	public String getAmountDelinquent() {
		return amountDelinquent;
	}
	/**
	 * @param amountDelinquent The amountDelinquent to set.
	 */
	public void setAmountDelinquent(String amountDelinquent) {
		this.amountDelinquent = amountDelinquent;
	}
	/**
	 * @return Returns the amountordered.
	 */
	public String getAmountOrdered() {
		return amountOrdered;
	}
	/**
	 * @param amountordered The amountordered to set.
	 */
	public void setAmountOrdered(String amountordered) {
		this.amountOrdered = amountordered;
	}
	/**
	 * @return Returns the ncFeeId.
	 */
	public String getNcFeeId() {
		return ncFeeId;
	}
	/**
	 * @param ncFeeId The ncFeeId to set.
	 */
	public void setNcFeeId(String ncFeeId) {
		this.ncFeeId = ncFeeId;
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
