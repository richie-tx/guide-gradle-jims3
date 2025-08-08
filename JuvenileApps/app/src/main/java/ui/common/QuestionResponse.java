/*
 * Created on Dec 7, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package ui.common;

/**
 * @author jjose
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class QuestionResponse
{
	private String responseId="";
	private String responseDisplayText="";
	private String responseValue="";
	private boolean isDefault=false;
	private String codeTableName=null;
	
	/**
	 * @return
	 */
	public String getResponseDisplayText()
	{
		return responseDisplayText;
	}

	/**
	 * @return
	 */
	public String getResponseId()
	{
		return responseId;
	}

	/**
	 * @return
	 */
	public String getResponseValue()
	{
		return responseValue;
	}

	/**
	 * @return
	 */
	public boolean isDefault()
	{
		return isDefault;
	}

	/**
	 * @param string
	 */
	public void setResponseDisplayText(String string)
	{
		responseDisplayText = string;
	}

	/**
	 * @param string
	 */
	public void setResponseId(String string)
	{
		responseId = string;
	}

	/**
	 * @param string
	 */
	public void setResponseValue(String string)
	{
		responseValue = string;
	}

	/**
	 * @param b
	 */
	public void setDefault(boolean b)
	{
		isDefault = b;
	}

	/**
	 * @return Returns the codeTableName.
	 */
	public String getCodeTableName() {
		return codeTableName;
	}
	/**
	 * @param codeTableName The codeTableName to set.
	 */
	public void setCodeTableName(String codeTableName) {
		this.codeTableName = codeTableName;
	}
}// END CLASS
