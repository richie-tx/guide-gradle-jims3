package messaging.agency;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 * description Holds agency data
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class UpdateAgencyEvent extends RequestEvent 
{
	private String agencyName;
	private String agencyTypeId;
	private String agencyId;
	private String jmcRep;
	
	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * @return
	 */
	public String getAgencyName()
	{
		return agencyName;
	}

	/**
	 * @return
	 */
	public String getAgencyTypeId()
	{
		return agencyTypeId;
	}

	/**
	 * @return
	 */
	public String getJmcRep()
	{
		return jmcRep;
	}

	/**
	 * @param string
	 */
	public void setAgencyId(String string)
	{
		agencyId = string;
	}

	/**
	 * @param string
	 */
	public void setAgencyName(String string)
	{
		agencyName = string;
	}

	/**
	 * @param string
	 */
	public void setAgencyTypeId(String string)
	{
		agencyTypeId = string;
	}

	/**
	 * @param string
	 */
	public void setJmcRep(String string)
	{
		jmcRep = string;
	}
}