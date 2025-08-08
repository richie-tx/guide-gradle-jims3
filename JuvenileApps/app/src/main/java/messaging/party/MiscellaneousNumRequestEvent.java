/*
 * Created on Dec 15, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.party;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MiscellaneousNumRequestEvent extends RequestEvent
{
	private String miscellaneousNumId;
	private String miscellaneousNum;
	private String miscellaneousNumType;
	private String stateAgency;
	/**
	 * @return
	 */
	public String getMiscellaneousNum()
	{
		return miscellaneousNum;
	}

	/**
	 * @return
	 */
	public String getMiscellaneousNumType()
	{
		return miscellaneousNumType;
	}

	/**
	 * @param string
	 */
	public void setMiscellaneousNum(String string)
	{
		miscellaneousNum = string;
	}

	/**
	 * @param string
	 */
	public void setMiscellaneousNumType(String string)
	{
		miscellaneousNumType = string;
	}

	/**
	 * @return
	 */
	public String getMiscellaneousNumId()
	{
		return miscellaneousNumId;
	}

	/**
	 * @param string
	 */
	public void setMiscellaneousNumId(String string)
	{
		miscellaneousNumId = string;
	}

	/**
	 * @return
	 */
	public String getStateAgency()
	{
		return stateAgency;
	}

	/**
	 * @param string
	 */
	public void setStateAgency(String string)
	{
		stateAgency = string;
	}

}
