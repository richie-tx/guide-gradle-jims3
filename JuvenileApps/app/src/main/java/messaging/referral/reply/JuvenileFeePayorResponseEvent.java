/*
 * Created on July 19, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.referral.reply;

import messaging.contact.to.PhoneNumberBean;
import mojo.km.messaging.ResponseEvent;

/**
 * @author UGopinath
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JuvenileFeePayorResponseEvent extends ResponseEvent {
	
	private String payor;
	private String payorAddress;
	private String payorId;
	private PhoneNumberBean phone;
	private String barNum;
	
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
	 * @return Returns the phone.
	 */
	public PhoneNumberBean getPhone() {
		return phone;
	}
	/**
	 * @param phone The phone to set.
	 */
	public void setPhone(PhoneNumberBean phone) {
		this.phone = phone;
	}
	/**
	 * @return Returns the barNum.
	 */
	public String getBarNum() {
		return barNum;
	}
	/**
	 * @param barNum The barNum to set.
	 */
	public void setBarNum(String barNum) {
		this.barNum = barNum;
	}
}
