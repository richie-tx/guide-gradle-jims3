//Source file: C:\\views\\dev\\app\\src\\messaging\\juvenilewarrant\\GetJuvenileAssociateAddressessEvent.java

package messaging.juvenilewarrant;

import mojo.km.messaging.RequestEvent;

public class GetJuvenileAssociateAddressesEvent extends RequestEvent
{
	public String associateNum;

	/**
	 * @roseuid 420A63BC02DE
	 */
	public GetJuvenileAssociateAddressesEvent()
	{

	}

	/**
	 * @return
	 */
	public String getAssociateNum()
	{
		return associateNum;
	}

	/**
	 * @param associateNum
	 */
	public void setAssociateNum(String associateNum)
	{
		this.associateNum = associateNum;
	}
}
