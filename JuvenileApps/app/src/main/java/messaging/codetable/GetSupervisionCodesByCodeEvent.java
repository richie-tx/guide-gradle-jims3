/*
 * Created on Jan 23, 2006
 *
 */
package messaging.codetable;

import mojo.km.messaging.RequestEvent;

/**
 * @author dgibler
 *
 */
public class GetSupervisionCodesByCodeEvent extends RequestEvent
{
	private String agencyId;
	private String codeId;
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
	public String getCodeId()
	{
		return codeId;
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
	public void setCodeId(String string)
	{
		codeId = string;
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
