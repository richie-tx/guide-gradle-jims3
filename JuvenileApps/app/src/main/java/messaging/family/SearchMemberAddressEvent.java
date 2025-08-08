/*
 * Created on Oct 24, 2005
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
public class SearchMemberAddressEvent extends RequestEvent
{
	private String constelltionId;

	/**
	 * @return
	 */
	public String getConstelltionId()
	{
		return constelltionId;
	}

	/**
	 * @param string
	 */
	public void setConstelltionId(String string)
	{
		constelltionId = string;
	}

}
