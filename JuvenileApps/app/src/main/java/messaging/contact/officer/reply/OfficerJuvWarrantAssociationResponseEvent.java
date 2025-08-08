/*
 * Created on March 21, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.contact.officer.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class OfficerJuvWarrantAssociationResponseEvent extends ResponseEvent
{
	private String warrantId;
	private String juvNumber;
	private String juvFirstName;
	private String juvLastName;
	
	/**
	 * @return
	 */
	public String getJuvFirstName()
	{
		return juvFirstName;
	}

	/**
	 * @return
	 */
	public String getJuvLastName()
	{
		return juvLastName;
	}

	/**
	 * @return
	 */
	public String getJuvNumber()
	{
		return juvNumber;
	}

	/**
	 * @return
	 */
	public String getWarrantId()
	{
		return warrantId;
	}

	/**
	 * @param string
	 */
	public void setJuvFirstName(String string)
	{
		juvFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setJuvLastName(String string)
	{
		juvLastName = string;
	}

	/**
	 * @param string
	 */
	public void setJuvNumber(String string)
	{
		juvNumber = string;
	}

	/**
	 * @param string
	 */
	public void setWarrantId(String string)
	{
		warrantId = string;
	}

}
