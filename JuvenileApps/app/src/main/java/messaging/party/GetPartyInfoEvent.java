//Source file: C:\\views\\dev\\app\\src\\messaging\\contact\\GetPartyInfoEvent.java

package messaging.party;

import mojo.km.messaging.RequestEvent;

public class GetPartyInfoEvent extends RequestEvent
{
	public String spn;
	public String currentNameInd;
	public String OID;
	/**
	 * @roseuid 416D2E3A016E
	 */
	public GetPartyInfoEvent()
	{
	}
	
	/**
	 * @return
	 */
	public String getSpn()
	{
		return spn;
	}

	/**
	 * @param spn
	 */
	public void setSpn(String spn)
	{
		this.spn = spn;
	}
	/**
	 * @return
	 */
	public String getCurrentNameInd()
	{
		return currentNameInd;
	}

	/**
	 * @return
	 */
	public String getOID()
	{
		return OID;
	}

	/**
	 * @param string
	 */
	public void setCurrentNameInd(String string)
	{
		currentNameInd = string;
	}

	/**
	 * @param string
	 */
	public void setOID(String string)
	{
		OID = string;
	}

}
