/*
 * Created on Oct 19, 2005
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
public class GetFamilyConstellationMemberFinancialEvent extends RequestEvent
{
	private int constellationMemberId;
	private int memberId;
	 

	/**
	 * @return
	 */
	public int getConstellationMemberId()
	{
		return constellationMemberId;
	}

	/**
	 * @return
	 */
	public int getMemberId()
	{
		return memberId;
	}

	/**
	 * @param i
	 */
	public void setConstellationMemberId(int i)
	{
		constellationMemberId = i;
	}

	/**
	 * @param i
	 */
	public void setMemberId(int i)
	{
		memberId = i;
	}

}
