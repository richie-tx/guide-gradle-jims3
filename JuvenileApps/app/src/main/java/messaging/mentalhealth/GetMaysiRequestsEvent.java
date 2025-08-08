/*
 * Created on Jul 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.mentalhealth;

import java.util.Date;

import mojo.km.messaging.RequestEvent;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetMaysiRequestsEvent extends RequestEvent {
	private String juvenileNumber;
	private String referralNumber;
	private Date dateForward; // this date is the date after which if any maysi's exist will be returned
	/**
	 * @return Returns the dateForward.
	 */
	public Date getDateForward() {
		return dateForward;
	}
	/**
	 * @param dateForward The dateForward to set.
	 */
	public void setDateForward(Date dateForward) {
		this.dateForward = dateForward;
	}
	/**
	 * @return Returns the juvenileNumber.
	 */
	public String getJuvenileNumber() {
		return juvenileNumber;
	}
	/**
	 * @param juvenileNumber The juvenileNumber to set.
	 */
	public void setJuvenileNumber(String juvenileNumber) {
		this.juvenileNumber = juvenileNumber;
	}
	/**
	 * @return Returns the referralNumber.
	 */
	public String getReferralNumber() {
		return referralNumber;
	}
	/**
	 * @param referralNumber The referralNumber to set.
	 */
	public void setReferralNumber(String referralNumber) {
		this.referralNumber = referralNumber;
	}
}
