/*
 * Created on Apr 12, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.juvenilewarrant.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class InvalidWarrantStageErrorEvent extends ResponseEvent
{
	private String warrantStatus = "";
	private String warrantActivationStatus = "";
	private String warrantSignatureStatus = "";
	private String warrantAcknowledgeStatus = ""; 
	private String warrantNum	=	"";
	

	/**
	 * @return
	 */
	public String getWarrantAcknowledgeStatus()
	{
		return warrantAcknowledgeStatus;
	}

	/**
	 * @return
	 */
	public String getWarrantActivationStatus()
	{
		return warrantActivationStatus;
	}

	/**
	 * @return
	 */
	public String getWarrantSignatureStatus()
	{
		return warrantSignatureStatus;
	}

	/**
	 * @return
	 */
	public String getWarrantStatus()
	{
		return warrantStatus;
	}

	/**
	 * @param string
	 */
	public void setWarrantAcknowledgeStatus(String string)
	{
		warrantAcknowledgeStatus = string;
	}

	/**
	 * @param string
	 */
	public void setWarrantActivationStatus(String string)
	{
		warrantActivationStatus = string;
	}

	/**
	 * @param string
	 */
	public void setWarrantSignatureStatus(String string)
	{
		warrantSignatureStatus = string;
	}

	/**
	 * @param string
	 */
	public void setWarrantStatus(String string)
	{
		warrantStatus = string;
	}

	/**
	 * @return
	 */
	public String getWarrantNum()
	{
		return warrantNum;
	}

	/**
	 * @param string
	 */
	public void setWarrantNum(String string)
	{
		warrantNum = string;
	}

}
