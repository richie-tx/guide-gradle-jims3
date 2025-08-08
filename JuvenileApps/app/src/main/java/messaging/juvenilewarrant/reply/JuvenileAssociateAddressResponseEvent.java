/*
 * Created on Feb 1, 2005
 */
package messaging.juvenilewarrant.reply;

import messaging.address.reply.AddressResponseEvent;

/**
 * @author asrvastava
 *
 */
public class JuvenileAssociateAddressResponseEvent extends AddressResponseEvent
{
	private boolean isBadAddress;
	private String relationshipToJuvenile;

	/**
	 * @return
	 */
	public boolean isBadAddress()
	{
		return isBadAddress;
	}

	/**
	 * @param b
	 */
	public void setBadAddress(boolean b)
	{
		isBadAddress = b;
	}

	public String getRelationshipToJuvenile()
	{
	    return relationshipToJuvenile;
	}

	public void setRelationshipToJuvenile(String relationshipTo)
	{
	    this.relationshipToJuvenile = relationshipTo;
	}

	public boolean isBlank()
	{
		if ((getStreetNum() == null || getStreetNum().trim().equals(""))
			&& (getStreetName() == null || getStreetName().trim().equals(""))
			&& (getAptNum() == null || getAptNum().trim().equals(""))
			&& (getCity() == null || getCity().trim().equals("")))
		{
			return true;
		}
		return false;
	}
}