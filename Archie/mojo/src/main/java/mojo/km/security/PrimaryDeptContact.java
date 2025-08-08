/*
 * 86318
 * Created on Mar 2, 2006
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package mojo.km.security;
import mojo.km.persistence.*;
/**
 * @author Rcooper
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class PrimaryDeptContact extends PersistentObject
{
	/*private String faxNum;
	private String homePhoneNum;
	private String workPhoneNum;
	private String middleName;
	private String pager;
	private String contactId;
	private String email;
	private String faxLocation;
	private String phoneExt;
	private String cellPhone;
	private String title;
	private String firstName;
	private String lastName;
	private String phoneNum;

	*//**
	 * @return
	 *//*
	public String getCellPhone()
	{
		return cellPhone;
	}

	*//**
	 * @return
	 *//*
	public String getContactId()
	{
		return contactId;
	}

	*//**
	 * @return
	 *//*
	public String getEmail()
	{
		return email;
	}

	*//**
	 * @return
	 *//*
	public String getFaxLocation()
	{
		return faxLocation;
	}

	*//**
	 * @return
	 *//*
	public String getFaxNum()
	{
		return faxNum;
	}

	*//**
	 * @return
	 *//*
	public String getFirstName()
	{
		return firstName;
	}

	*//**
	 * @return
	 *//*
	public String getHomePhoneNum()
	{
		return homePhoneNum;
	}

	*//**
	 * @return
	 *//*
	public String getLastName()
	{
		return lastName;
	}

	*//**
	 * @return
	 *//*
	public String getMiddleName()
	{
		return middleName;
	}

	*//**
	 * @return
	 *//*
	public String getPager()
	{
		return pager;
	}

	*//**
	 * @return
	 *//*
	public String getPhoneExt()
	{
		return phoneExt;
	}

	*//**
	 * @return
	 *//*
	public String getPhoneNum()
	{
		return phoneNum;
	}
	public String getFormattedPhoneNum()
	{
		String phoneNumber = this.phoneNum;
		System.out.println("phone = " + phoneNumber);
		{
			String areaCode = null;
			String prefix = null;
			String last4Digit = null;
			StringBuffer phone = new StringBuffer();
			if ((phoneNumber != null) && phoneNumber.length() > 9)
			{

				String formatedValue = phoneNumber.substring(3, 8);
				if (formatedValue.startsWith("-") && formatedValue.endsWith("-"))
				{
					areaCode = phoneNumber.substring(0, 3);
					prefix = phoneNumber.substring(4, 7);
					last4Digit = phoneNumber.substring(8, 12);
				}
				else
				{
					areaCode = phoneNumber.substring(0, 3);
					prefix = phoneNumber.substring(3, 6);
					last4Digit = phoneNumber.substring(6, 10);
				}
				phone = phone.append(areaCode).append("-").append(prefix).append("-").append(last4Digit);
				return phone.toString();
			}
			return "";
		}
	}

	*//**
	 * @return
	 *//*
	public String getTitle()
	{
		return title;
	}

	*//**
	 * @return
	 *//*
	public String getWorkPhoneNum()
	{
		return workPhoneNum;
	}

	*//**
	 * @param string
	 *//*
	public void setCellPhone(String string)
	{
		cellPhone = string;
	}

	*//**
	 * @param string
	 *//*
	public void setContactId(String string)
	{
		contactId = string;
	}

	*//**
	 * @param string
	 *//*
	public void setEmail(String string)
	{
		email = string;
	}

	*//**
	 * @param string
	 *//*
	public void setFaxLocation(String string)
	{
		faxLocation = string;
	}

	*//**
	 * @param string
	 *//*
	public void setFaxNum(String string)
	{
		faxNum = string;
	}

	*//**
	 * @param string
	 *//*
	public void setFirstName(String string)
	{
		firstName = string;
	}

	*//**
	 * @param string
	 *//*
	public void setHomePhoneNum(String string)
	{
		homePhoneNum = string;
	}

	*//**
	 * @param string
	 *//*
	public void setLastName(String string)
	{
		lastName = string;
	}

	*//**
	 * @param string
	 *//*
	public void setMiddleName(String string)
	{
		middleName = string;
	}

	*//**
	 * @param string
	 *//*
	public void setPager(String string)
	{
		pager = string;
	}

	*//**
	 * @param string
	 *//*
	public void setPhoneExt(String string)
	{
		phoneExt = string;
	}

	*//**
	 * @param string
	 *//*
	public void setPhoneNum(String string)
	{
		phoneNum = string;
	}

	*//**
	 * @param string
	 *//*
	public void setTitle(String string)
	{
		title = string;
	}

	*//**
	 * @param string
	 *//*
	public void setWorkPhoneNum(String string)
	{
		workPhoneNum = string;
	}
	public String getFormattedName()
		{   System.out.println("name = " + this.firstName + " " + this.lastName);
			return	this.firstName + " " + this.lastName;
		}*/
}
