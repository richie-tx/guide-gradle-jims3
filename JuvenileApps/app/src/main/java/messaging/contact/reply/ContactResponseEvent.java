/*
 * Created on Oct 21, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.contact.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dgibler
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ContactResponseEvent extends ResponseEvent
{
	private String faxNum;
	private String homePhoneNum;
	private String workPhoneNum;
	private String middleName;
	private String pager;
	private String email;
	private String faxLocation;
	private String phoneExt;
	private String cellPhone;
	private String title;
	private String firstName;
	private String lastName;
	private String phoneNum;
	private String logonId;
	private String liaisonTrainingInd;
	private String primaryContact;
		
	/**
	 * @return
	 */
	public String getCellPhone()
	{
		return cellPhone;
	}

	/**
	 * @return
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @return
	 */
	public String getFaxLocation()
	{
		return faxLocation;
	}

	/**
	 * @return
	 */
	public String getFaxNum()
	{
		return faxNum;
	}

	/**
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/**
	 * @return
	 */
	public String getHomePhoneNum()
	{
		return homePhoneNum;
	}

	/**
	 * @return
	 */
	public String getLastName()
	{
		return lastName;
	}

	/**
	 * @return
	 */
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 * @return
	 */
	public String getMiddleName()
	{
		return middleName;
	}

	/**
	 * @return
	 */
	public String getPager()
	{
		return pager;
	}

	/**
	 * @return
	 */
	public String getPhoneExt()
	{
		return phoneExt;
	}

	/**
	 * @return
	 */
	public String getPhoneNum()
	{
		return phoneNum;
	}

	/**
	 * @return
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @return
	 */
	public String getWorkPhoneNum()
	{
		return workPhoneNum;
	}

	/**
	 * @param string
	 */
	public void setCellPhone(String string)
	{
		cellPhone = string;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string)
	{
		email = string;
	}

	/**
	 * @param string
	 */
	public void setFaxLocation(String string)
	{
		faxLocation = string;
	}

	/**
	 * @param string
	 */
	public void setFaxNum(String string)
	{
		faxNum = string;
	}

	/**
	 * @param string
	 */
	public void setFirstName(String string)
	{
		firstName = string;
	}

	/**
	 * @param string
	 */
	public void setHomePhoneNum(String string)
	{
		homePhoneNum = string;
	}

	/**
	 * @param string
	 */
	public void setLastName(String string)
	{
		lastName = string;
	}

	/**
	 * @param string
	 */
	public void setLogonId(String string)
	{
		logonId = string;
	}

	/**
	 * @param string
	 */
	public void setMiddleName(String string)
	{
		middleName = string;
	}

	/**
	 * @param string
	 */
	public void setPager(String string)
	{
		pager = string;
	}

	/**
	 * @param string
	 */
	public void setPhoneExt(String string)
	{
		phoneExt = string;
	}

	/**
	 * @param string
	 */
	public void setPhoneNum(String string)
	{
		phoneNum = string;
	}

	/**
	 * @param string
	 */
	public void setTitle(String string)
	{
		title = string;
	}

	/**
	 * @param string
	 */
	public void setWorkPhoneNum(String string)
	{
		workPhoneNum = string;
	}

	/**
	 * @return
	 */
	public String getLiaisonTrainingInd()
	{
		return liaisonTrainingInd;
	}

	/**
	 * @return
	 */
	public String getPrimaryContact()
	{
		return primaryContact;
	}

	/**
	 * @param string
	 */
	public void setLiaisonTrainingInd(String string)
	{
		liaisonTrainingInd = string;
	}

	/**
	 * @param string
	 */
	public void setPrimaryContact(String string)
	{
		primaryContact = string;
	}

}
