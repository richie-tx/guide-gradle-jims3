/*
 * Created on Oct 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.family;

import mojo.km.messaging.RequestEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetJuvenileMemberAddressEvent extends RequestEvent
{

	private String memberAddressId;
	private String juvenileNumber;



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
	 * @return Returns the memberAddressId.
	 */
	public String getMemberAddressId() {
		return memberAddressId;
	}
	/**
	 * @param memberAddressId The memberAddressId to set.
	 */
	public void setMemberAddressId(String memberAddressId) {
		this.memberAddressId = memberAddressId;
	}
}
