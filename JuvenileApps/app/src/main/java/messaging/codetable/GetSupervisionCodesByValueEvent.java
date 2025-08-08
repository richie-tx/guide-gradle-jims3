/*
 * Created on march 23, 20069
 *
 */
package messaging.codetable;

import mojo.km.messaging.RequestEvent;

/**
 * @author mchowdhury
 *
 */
public class GetSupervisionCodesByValueEvent extends RequestEvent
{
	private String agencyId;
	private String codeTableName;
	private String description;
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
	public String getCodeTableName()
	{
		return codeTableName;
	}

	/**
	 * @return
	 */
	public String getDescription()
	{
		return description;
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
	public void setCodeTableName(String string)
	{
		codeTableName = string;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string)
	{
		description = string;
	}

}
