/*
 * Created on Dec 12, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.juvenilecase.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author cc_vnarsingoju
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class JuvenileAgeResponseEvent extends ResponseEvent {

	private String juvenileAgeAtDeath;

	private String juvenileNum;

	private String memberId;

	/**
	 * @return Returns the juvenileAgeAtDeath.
	 */
	public String getJuvenileAgeAtDeath() {
		return juvenileAgeAtDeath;
	}

	/**
	 * @param juvenileAgeAtDeath
	 *            The juvenileAgeAtDeath to set.
	 */
	public void setJuvenileAgeAtDeath(String juvenileAgeAtDeath) {
		this.juvenileAgeAtDeath = juvenileAgeAtDeath;
	}

	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}

	/**
	 * @param juvenileNum
	 *            The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}

	/**
	 * @return Returns the memberId.
	 */
	public String getMemberId() {
		return memberId;
	}

	/**
	 * @param memberId
	 *            The memberId to set.
	 */
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
}
