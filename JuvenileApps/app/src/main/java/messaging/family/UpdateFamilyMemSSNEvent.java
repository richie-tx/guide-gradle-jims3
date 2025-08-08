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
public class UpdateFamilyMemSSNEvent extends RequestEvent {

	private String ssn;	
	private String familyMemID;
	
	
	
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

	public String getFamilyMemID() {
		return familyMemID;
	}
	public void setFamilyMemID(String familyMemID) {
		this.familyMemID = familyMemID;
	}
}// END CLASS
