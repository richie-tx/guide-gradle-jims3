/*
 * Created on May 25, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.contact.user.reply;

import mojo.km.messaging.ResponseEvent;

/**
 * @author dnikolis
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class SecurityUserResponseEvent extends ResponseEvent implements Comparable
{
	private String agencyId;
	private String agencyName;
	private String departmentId;
	private String departmentName;
	private String firstName;
	private String lastName;
	private String logonId; 
	private String middleName;
	private String ssn;
	private String userTypeId;
	private String userId;
	private String homePhoneNum;
	private String phoneExt;
	private String phoneNum;
	private String cellPhone;
	private String pager;
	private String faxLocation;
	private String email;
	private String workPhoneNum;
	private String faxNum;
	private String formattedName;
	
	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 * @return
	 */
	public String getAgencyName()
	{
		return agencyName;
	}

	/**
	 * @return
	 */
	public String getDepartmentId()
	{
		return departmentId;
	}

	/**
	 * @return
	 */
	public String getDepartmentName()
	{
		return departmentName;
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
	public String getUserTypeId()
	{
		return userTypeId;
	}

	/**
	 * @param agencyId
	 */
	public void setAgencyId(String agencyId)
	{
		this.agencyId = agencyId;
	}

	/**
	 * @param agencyName
	 */
	public void setAgencyName(String agencyName)
	{
		this.agencyName = agencyName;
	}

	/**
	 * @param departmentId
	 */
	public void setDepartmentId(String departmentId)
	{
		this.departmentId = departmentId;
	}

	/**
	 * @param departmentName
	 */
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}

	/**
	 * @param firstName
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}

	/**
	 * @param lastName
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;
	}

	/**
	 * @param logonId
	 */
	public void setLogonId(String logonId)
	{
		this.logonId = logonId;
	}

	/**
	 * @param middleName
	 */
	public void setMiddleName(String middleName)
	{
		this.middleName = middleName;
	}

	/**
	 * @param userTypeId
	 */
	public void setUserTypeId(String userTypeId)
	{
		this.userTypeId = userTypeId;
	}

	/**
	 * @return
	 */
	public String getSsn()
	{
		return ssn;
	}


	/**
	 * @param string
	 */
	public void setSsn(String string)
	{
		ssn = string;
	}

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
	public String getHomePhoneNum()
	{
		return homePhoneNum;
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
	public String getUserId()
	{
		return userId;
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
	public void setHomePhoneNum(String string)
	{
		homePhoneNum = string;
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
	public void setUserId(String string)
	{
		userId = string;
	}

	/**
	 * @param string
	 */
	public void setWorkPhoneNum(String string)
	{
		workPhoneNum = string;
	}

	/**
	 * @return Returns the formattedName.
	 */
	public String getFormattedName() {
		return formattedName;
	}
	/**
	 * @param formattedName The formattedName to set.
	 */
	public void setFormattedName(String formattedName) {
		this.formattedName = formattedName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(Object o) {
		if (o == null){
			return -1;
		}
		SecurityUserResponseEvent c = (SecurityUserResponseEvent)o;
		if (c.getFormattedName() == null){
			return -1;
		}		
		if (this.getFormattedName() == null){
			return 1;
		}
		return this.getFormattedName().compareToIgnoreCase(c.getFormattedName());
	}	
}