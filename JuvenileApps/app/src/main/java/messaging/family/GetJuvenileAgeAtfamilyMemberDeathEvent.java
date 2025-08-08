/*
 * Created on Dec 10, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.family;

import mojo.km.messaging.RequestEvent;

/**
 * @author cc_vnarsingoju
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetJuvenileAgeAtfamilyMemberDeathEvent  extends RequestEvent{

	private String familyMemberId;
	private String juvenileNum;
	private String juvenileAge;
	private String action;
	
	/**
	 * @return Returns the familyMemberId.
	 */
	public String getFamilyMemberId() {
		return familyMemberId;
	}
	/**
	 * @param familyMemberId The familyMemberId to set.
	 */
	public void setFamilyMemberId(String familyMemberId) {
		this.familyMemberId = familyMemberId;
	}
	/**
	 * @return Returns the juvenileAge.
	 */
	public String getJuvenileAge() {
		return juvenileAge;
	}
	/**
	 * @param juvenileAge The juvenileAge to set.
	 */
	public void setJuvenileAge(String juvenileAge) {
		this.juvenileAge = juvenileAge;
	}
	/**
	 * @return Returns the juvenileNum.
	 */
	public String getJuvenileNum() {
		return juvenileNum;
	}
	/**
	 * @param juvenileNum The juvenileNum to set.
	 */
	public void setJuvenileNum(String juvenileNum) {
		this.juvenileNum = juvenileNum;
	}
	/**
	 * @return Returns the action.
	 */
	public String getAction() {
		return action;
	}
	/**
	 * @param action The action to set.
	 */
	public void setAction(String action) {
		this.action = action;
	}
}
