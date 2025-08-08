//Source file: C:\\views\\dev\\app\\src\\messaging\\codetable\\GetCodeTableRecordsEvent.java

package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class SearchCodetableNonMaRecordsEvent extends RequestEvent
{
	private String codeTableName;

	private String codeTableDescription;

	private String codeTableType;
	
	private String codeTableAgencyId;	

	
	/**
	 * @roseuid 4164282900A6
	 */
	public SearchCodetableNonMaRecordsEvent()
	{

	}

	/**
	 * @param codeTableName
	 * @roseuid 416427920146
	 */
	public void setCodeTableName(String aCodeTableName)
	{
		this.codeTableName = aCodeTableName;
	}

	/**
	 * @return String
	 * @roseuid 416427920153
	 */
	public String getCodeTableName()
	{
		return this.codeTableName;
	}

	public void setCodeTableDescription(String acodeTableDescription)
	{
		this.codeTableDescription = acodeTableDescription;
	}

	public String getCodeTableDescription()
	{
		return this.codeTableDescription;
	}

	public void setCodeTableType(String acodeTableType)
	{
		this.codeTableType = acodeTableType;
	}

	public String getCodeTableType()
	{
		return this.codeTableType;
	}
	
	public void setCodeTableAgencyId(String agencyId)
	{
		this.codeTableAgencyId = agencyId;
	}

	public String getCodeTableAgencyId()
	{
		return codeTableAgencyId;
	}

}
