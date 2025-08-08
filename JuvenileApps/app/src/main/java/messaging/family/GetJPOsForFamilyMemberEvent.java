/*
 * Created on May 17, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.family;

import mojo.km.messaging.RequestEvent;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetJPOsForFamilyMemberEvent extends RequestEvent
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
