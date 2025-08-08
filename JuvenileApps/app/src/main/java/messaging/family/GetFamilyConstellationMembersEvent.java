/*
 * Created on Oct 4, 2005
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
public class GetFamilyConstellationMembersEvent extends RequestEvent
{
	private String conetllaionId;


	/**
	 * 
	 */
	public GetFamilyConstellationMembersEvent()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return
	 */
	public String getConetllaionId()
	{
		return conetllaionId;
	}

	/**
	 * @param string
	 */
	public void setConetllaionId(String string)
	{
		conetllaionId = string;
	}

}
