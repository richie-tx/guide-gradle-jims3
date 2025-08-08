/*
 * Created on Aug 5, 2004
 *
 */
package messaging.codetable.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mlawles
 *
 */
public class CodeTableResponseEvent extends ResponseEvent
{
	private String codeTableName;
	/**
	* Properties for codes
	* @referencedType pd.codetable.Code
	*/
	private String codeTableId;

	/**
	 * @return
	 */
	public String getCodeTableId()
	{
		return codeTableId;
	}

	/**
	 * @return
	 */
	public String getCodeTableName()
	{
		return codeTableName;
	}

	/**
	 * @param string
	 */
	public void setCodeTableId(String string)
	{
		codeTableId = string;
	}

	/**
	 * @param string
	 */
	public void setCodeTableName(String string)
	{
		codeTableName = string;
	}

}
