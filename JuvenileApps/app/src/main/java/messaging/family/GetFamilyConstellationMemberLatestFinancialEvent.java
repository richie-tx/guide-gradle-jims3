/*
 * Created on Nov 1, 2005
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
public class GetFamilyConstellationMemberLatestFinancialEvent extends RequestEvent
{

	private String constelltionMemberId;
	/**
	 * 
	 */
	public GetFamilyConstellationMemberLatestFinancialEvent()
	{
		super();
		// TODO Auto-generated constructor stub
	}

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
