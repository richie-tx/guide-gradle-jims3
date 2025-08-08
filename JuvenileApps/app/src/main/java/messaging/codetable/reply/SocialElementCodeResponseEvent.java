/*
 * Created on Jun 22, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.codetable.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author athorat
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SocialElementCodeResponseEvent extends ResponseEvent implements Comparable
{

	private String inactiveInd;
	private String element;
	private String reportGroup;
	private String code;
	private String description;
	private String socialElementCodeId;
	private String elementDescription;
	
	/**
	 * 
	 */
	public SocialElementCodeResponseEvent()
	{
		super();
		// TODO Auto-generated constructor stub
	}




	/**
	 * @return
	 */
	public String getElement()
	{
		return element;
	}

	/**
	 * @return
	 */
	public String getElementDescription()
	{
		return elementDescription;
	}

	/**
	 * @return
	 */
	public String getInactiveInd()
	{
		return inactiveInd;
	}

	/**
	 * @return
	 */
	public String getReportGroup()
	{
		return reportGroup;
	}

	/**
	 * @return
	 */
	public String getSocialElementCodeId()
	{
		return socialElementCodeId;
	}




	/**
	 * @param string
	 */
	public void setElement(String string)
	{
		element = string;
	}

	/**
	 * @param string
	 */
	public void setElementDescription(String string)
	{
		elementDescription = string;
	}

	/**
	 * @param string
	 */
	public void setInactiveInd(String string)
	{
		inactiveInd = string;
	}

	/**
	 * @param string
	 */
	public void setReportGroup(String string)
	{
		reportGroup = string;
	}

	/**
	 * @param string
	 */
	public void setSocialElementCodeId(String string)
	{
		socialElementCodeId = string;
	}

	/**
	 * @return
	 */
	public String getCode()
	{
		return code;
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
	public void setCode(String string)
	{
		code = string;
	}

	/**
	 * @param string
	 */
	public void setDescription(String string)
	{
		description = string;
	}


	public String toString() {
		return description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object arg0)
	{
		SocialElementCodeResponseEvent evt = (SocialElementCodeResponseEvent)arg0;
		return description.compareTo(evt.getDescription());

	}

}
