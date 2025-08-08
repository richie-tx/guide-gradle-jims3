/*
 * Created on Jul 5, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package messaging.family;

import mojo.km.messaging.RequestEvent;

/**
 * @author jjose
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class GetConstMemberEvent extends RequestEvent {
	
	private String familyMemberId;

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
}
