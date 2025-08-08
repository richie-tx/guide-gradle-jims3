//Source file: C:\\views\\dev\\app\\src\\messaging\\codetable\\GetCodeTableEvent.java

package messaging.codetable;

import mojo.km.messaging.RequestEvent;

public class GetCodeTableEvent extends RequestEvent
{
	public String codeTableId;
	public String codeTableName;
	public String contextKey;

	/**
	 * @roseuid 416428290039
	 */
	public GetCodeTableEvent()
	{

	}

	/**
	 * @param codeTableId
	 * @roseuid 416427E302EC
	 */
	public void setCodeTableId(String aCodeTableId)
	{
		this.codeTableId = aCodeTableId;
	}

	/**
	 * @return String
	 * @roseuid 416427E302EE
	 */
	public String getCodeTableId()
	{
		return this.codeTableId;
	}

	/**
	 * @param codeTableName
	 * @roseuid 416427E302F9
	 */
	public void setCodeTableName(String aCodeTableName)
	{
		this.codeTableName = aCodeTableName;
	}

	/**
	 * @return String
	 * @roseuid 416427E302FB
	 */
	public String getCodeTableName()
	{
		return this.codeTableName;
	}
	/**
	 * @return
	 */
	public String getContextKey()
	{
		return contextKey;
	}

	/**
	 * @param string
	 */
	public void setContextKey(String string)
	{
		contextKey = string;
	}

}
