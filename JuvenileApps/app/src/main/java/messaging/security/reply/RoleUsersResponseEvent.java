/*
 * Created on Apr 12, 2005
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package messaging.security.reply;

import java.util.Date;
import mojo.km.messaging.ResponseEvent;

/**
 * @author mchowdhury
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class RoleUsersResponseEvent extends ResponseEvent
{
	private String userId;
	private String email;
	private Date expirationDate;
	private String firstName;
	private String genericLogonInd;
	private Date lastLoginDate;
	private String lastName;
	private String middleName;
	private String name;
	private String phoneNum;
	private String phoneExt;
	private String logonId;
	private String userStatus;
	private String title;
	private String roleId;
	private String userLoginName;
	private String userType;
	private String userTypeId;
	


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
	public Date getExpirationDate()
	{
		return expirationDate;
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
	public String getGenericLogonInd()
	{
		return genericLogonInd;
	}

	/**
	 * @return
	 */
	public Date getLastLoginDate()
	{
		return lastLoginDate;
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
	public String getName()
	{
		return name;
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
	public String getRoleId()
	{
		return roleId;
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
	public String getUserStatus()
	{
		return userStatus;
	}

	/**
	 * @param string
	 */
	public void setEmail(String string)
	{
		email = string;
	}

	/**
	 * @param date
	 */
	public void setExpirationDate(Date date)
	{
		expirationDate = date;
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
	public void setGenericLogonInd(String string)
	{
		genericLogonInd = string;
	}

	/**
	 * @param date
	 */
	public void setLastLoginDate(Date date)
	{
		lastLoginDate = date;
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
	public void setName(String string)
	{
		name = string;
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
	public void setRoleId(String string)
	{
		roleId = string;
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
	public void setUserStatus(String string)
	{
		userStatus = string;
	}

	/**
	 * @return
	 */
	public String getUserLoginName()
	{
		return userLoginName;
	}

	/**
	 * @return
	 */
	public String getUserType()
	{
		return userType;
	}

	/**
	 * @param string
	 */
	public void setUserLoginName(String string)
	{
		userLoginName = string;
	}

	/**
	 * @param string
	 */
	public void setUserType(String string)
	{
		userType = string;
	}

	/**
	 * @param string
	 */
	public void setUserId(String string)
	{
		this.userId = string;
		
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
	public String getUserTypeId()
	{
		return userTypeId;
	}

	/**
	 * @param string
	 */
	public void setUserTypeId(String string)
	{
		userTypeId = string;
	}

}
