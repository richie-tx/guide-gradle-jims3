/*
 * Created on Mar 2, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilewarrant;

import messaging.address.AddressRequestEvent;



/**
 * @author ryoung
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class JuvenileAssociateAddressRequestEvent extends AddressRequestEvent
{
	private String associateNum;
	private boolean isBadAddress;
	

	/**
	 * @return
	 */
	public String getAssociateNum()
	{
		return associateNum;
	}

	/**
	 * @return
	 */
	public boolean isBadAddress()
	{
		return isBadAddress;
	}

	/**
	 * @param string
	 */
	public void setAssociateNum(String associateNum)
	{
		this.associateNum = associateNum;
	}

	/**
	 * @param b
	 */
	public void setBadAddress(boolean b)
	{
		isBadAddress = b;
	}

}
