/*
 * Created on Oct 13, 2005
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
public class GetFamilyMemberInsuranceEvent extends RequestEvent
{
	private String familyMemberId;

	/**
	 * @return
	 */
	public String getFamilyMemberId()
	{
		return familyMemberId;
	}

	/**
	 * @param string
	 */
	public void setFamilyMemberId(String string)
	{
		familyMemberId = string;
	}

}
