/*
 * Created on Sept 19, 2017
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.family;

import mojo.km.messaging.RequestEvent;

/**
 * @author ugopinath
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SaveFamilyMemSSNUserAccessEvent extends RequestEvent {

	private String juvenileNum;	
	private String ssn;	
	private String accessedBy;
	private String familyMemID;
	
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
	 * @return Returns the ssn.
	 */
	public String getSsn() {
		return ssn;
	}
	/**
	 * @param ssn The ssn to set.
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	public String getAccessedBy() {
		return accessedBy;
	}
	public void setAccessedBy(String accessedBy) {
		this.accessedBy = accessedBy;
	}
	public String getFamilyMemID() {
		return familyMemID;
	}
	public void setFamilyMemID(String familyMemID) {
		this.familyMemID = familyMemID;
	}
}// END CLASS
