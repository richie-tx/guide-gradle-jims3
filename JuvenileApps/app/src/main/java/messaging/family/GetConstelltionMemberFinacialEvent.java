/*
 * Created on Oct 25, 2005
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
public class GetConstelltionMemberFinacialEvent extends RequestEvent
{
	private String constelltionMemberId;

	/**
	 * @return
	 */
	public String getConstelltionMemberId()
	{
		return constelltionMemberId;
	}

	/**
	 * @param string
	 */
	public void setConstelltionMemberId(String string)
	{
		constelltionMemberId = string;
	}

}
