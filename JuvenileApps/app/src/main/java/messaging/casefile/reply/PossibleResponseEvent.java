/*
 * Created on July 26, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.casefile.reply; 

import java.util.Collection;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 * Class holding data for each individual jsp.
 */
 
public class PossibleResponseEvent extends ResponseEvent{
	private String displayText;
	private String id;
	private String responseValue;
	private boolean isDefault = false;
	private String codeTableName=null;
	
	/**
	 * Consturctor
	 */
	public PossibleResponseEvent(){
	}
	
	/**
	 * @return
	 */
	public String getDisplayText()
	{
		return displayText;
	}

	/**
	 * @return
	 */
	public String getId()
	{
		return id;
	}

	/**
	 * @return
	 */
	public boolean isDefault()
	{
		return isDefault;
	}

	/**
	 * @return
	 */
	public String getResponseValue()
	{
		return responseValue;
	}

	/**
	 * @param string
	 */
	public void setDisplayText(String string)
	{
		displayText = string;
	}

	/**
	 * @param string
	 */
	public void setId(String string)
	{
		id = string;
	}

	/**
	 * @param b
	 */
	public void setDefault(boolean b)
	{
		isDefault = b;
	}

	/**
	 * @param string
	 */
	public void setResponseValue(String string)
	{
		responseValue = string;
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
}
