/*
 * Created on Dec 16, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.contact.party.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class MiscellaneousIdResponseEvent extends ResponseEvent
{
	private String idNum;
	private String idNumType;
	private String idNumTypeId;
	private String miscellaneousIdId;
	private String partyId;
	private String stateAgency;
	private String stateAgencyId;

	/**
	 * @return
	 */
	public String getIdNum()
	{
		return idNum;
	}

	/**
	 * @return
	 */
	public String getIdNumType()
	{
		return idNumType;
	}

	/**
	 * @return
	 */
	public String getIdNumTypeId()
	{
		return idNumTypeId;
	}

	/**
	 * @return
	 */
	public String getMiscellaneousIdId()
	{
		return miscellaneousIdId;
	}

	/**
	 * @return
	 */
	public String getPartyId()
	{
		return partyId;
	}

	/**
	 * @param string
	 */
	public void setIdNum(String string)
	{
		idNum = string;
	}

	/**
	 * @param string
	 */
	public void setIdNumType(String string)
	{
		idNumType = string;
	}

	/**
	 * @param string
	 */
	public void setIdNumTypeId(String string)
	{
		idNumTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setMiscellaneousIdId(String string)
	{
		miscellaneousIdId = string;
	}

	/**
	 * @param string
	 */
	public void setPartyId(String string)
	{
		partyId = string;
	}

	/**
	 * @return
	 */
	public String getStateAgency()
	{
		return stateAgency;
	}

	/**
	 * @return
	 */
	public String getStateAgencyId()
	{
		return stateAgencyId;
	}

	/**
	 * @param string
	 */
	public void setStateAgency(String string)
	{
		stateAgency = string;
	}

	/**
	 * @param string
	 */
	public void setStateAgencyId(String string)
	{
		stateAgencyId = string;
	}

}
