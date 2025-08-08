/*
 * Created on Aug 30, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.contact.agency.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DepartmentContactResponseEvent extends ResponseEvent
{
	private String lastName;
	private String firstName;
	private String middleName;
	private String title;
	private String userId;
	private String phone;
	private String phoneExt;
	private String liasonTrainingInd;
	private String liasonTraining;
	private String email;
	private String departmentId;	
	private String contactId;
	private String contactTypeId;
	private String primaryContact;
	
	
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
	public String getFirstName()
	{
		return firstName;
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
	public String getLiasonTraining()
	{
		return liasonTraining;
	}

	/**
	 * @return
	 */
	public String getLiasonTrainingInd()
	{
		return liasonTrainingInd;
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
	public String getPhone()
	{
		return phone;
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
	public String getTitle()
	{
		return title;
	}

	/**
	 * @return
	 */
	public String getUserId()
	{
		return userId;
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
	public void setFirstName(String string)
	{
		firstName = string;
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
	public void setLiasonTraining(String string)
	{
		liasonTraining = string;
	}

	/**
	 * @param string
	 */
	public void setLiasonTrainingInd(String string)
	{
		liasonTrainingInd = string;
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
	public void setPhone(String string)
	{
		phone = string;
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
	public void setTitle(String string)
	{
		title = string;
	}

	/**
	 * @param string
	 */
	public void setUserId(String string)
	{
		userId = string;
	}

	/**
	 * @return
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}

	/**
	 * @param string
	 */
	public void setDepartmentId(String string)
	{
		departmentId = string;
	}

	/**
	 * @return
	 */
	public String getContactId()
	{
		return contactId;
	}

	/**
	 * @param string
	 */
	public void setContactId(String string)
	{
		contactId = string;
	}

	/**
	 * @return
	 */
	public String getContactTypeId()
	{
		return contactTypeId;
	}

	/**
	 * @param string
	 */
	public void setContactTypeId(String string)
	{
		contactTypeId = string;
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
	public void setPrimaryContact(String string)
	{
		primaryContact = string;
	}

}