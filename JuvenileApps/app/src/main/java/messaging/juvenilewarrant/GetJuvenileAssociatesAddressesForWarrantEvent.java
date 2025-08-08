/*
 * Created on Mar 3, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

/**
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class GetJuvenileAssociatesAddressesForWarrantEvent extends RequestEvent
{
	private String warrantNum;

	/**
	 * @return
	 */
	public String getWarrantNum()
	{
		return warrantNum;
	}

	/**
	 * @param string
	 */
	public void setWarrantNum(String warrantNum)
	{
		this.warrantNum = warrantNum;
	}

}
