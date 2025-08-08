/*
 * Created on Oct 5, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.family;

import messaging.family.*;
import mojo.km.messaging.RequestEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetJuvenileFamilyMembersEvent extends GetFamilyMembersEvent
{

	private String juvenileNumber;

	/**
	 * 
	 */
	public GetJuvenileFamilyMembersEvent()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return
	 */
	public String getJuvenileNumber()
	{
		return juvenileNumber;
	}

	/**
	 * @param string
	 */
	public void setJuvenileNumber(String string)
	{
		juvenileNumber = string;
	}	
}
