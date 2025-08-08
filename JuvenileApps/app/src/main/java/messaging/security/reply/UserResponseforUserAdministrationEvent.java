/*
 * Project: JIMS2
 * Class:   messaging.security.reply.UserResponseforUserAdministrationEvent
 * Version: 1.0.0
 *
 * Date:    2005-04-28
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package messaging.security.reply;

import naming.PDSecurityConstants;

/**
 * Class UserResponseforUserAdministrationEvent.
 *  
 * @author  Anand Thorat
 * @version  1.0.0
 */
public class UserResponseforUserAdministrationEvent extends mojo.km.messaging.ResponseEvent
{
	private String agencyId;
	private String agencyName;
	private String departmentId;
	private String departmentName;
	private boolean isAgencyHasSA;
	private String logonId;
	private String userFirstName;
	private String userLastName;
	private String userMiddleName;
	private String userType;
	private String userTypeId;
	private String jims2LogonId;
	private String serviceProviderName;

	/**
	 * @return
	 */
	public String getAgencyId()
	{
		return agencyId;
	}

	/**
	 *  
	 * @return  The agency name.
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
	 *  
	 * @return  The user id.
	 */
	public String getLogonId()
	{
		return logonId;
	}

	/**
	 *  
	 * @return  The user first name.
	 */
	public String getUserFirstName()
	{
		return userFirstName;
	}

	/**
	 *  
	 * @return  The user last name.
	 */
	public String getUserLastName()
	{
		return userLastName;
	}

	/**
	 *  
	 * @return  The user type.
	 */
	public String getUserType()
	{
		return userType;
	}
	/**
	 * @return
	 */
	public String getUserTypeId()
	{
		return userTypeId;
	}

	/**
	 *  
	 * @return  The boolean.
	 */
	public boolean isAgencyHasSA()
	{
		return isAgencyHasSA;
	}

	/**
	 *  
	 * @param b The agency has s a.
	 */
	public void setAgencyHasSA(boolean b)
	{
		isAgencyHasSA = b;
	}

	/**
	 * @param string
	 */
	public void setAgencyId(String agencyId)
	{
		this.agencyId = agencyId;
	}

	/**
	 *  
	 * @param string The agency name.
	 */
	public void setAgencyName(String string)
	{
		agencyName = string;
	}

	/**
	 * @param string
	 */
	public void setDepartmentId(String departmentId)
	{
		this.departmentId = departmentId;
	}

	/**
	 * @param string
	 */
	public void setDepartmentName(String departmentName)
	{
		this.departmentName = departmentName;
	}

	/**
	 *  
	 * @param string The user id.
	 */
	public void setLogonId(String string)
	{
		logonId = string;
	}

	/**
	 *  
	 * @param string The user first name.
	 */
	public void setUserFirstName(String string)
	{
		userFirstName = string;
	}

	/**
	 *  
	 * @param string The user last name.
	 */
	public void setUserLastName(String string)
	{
		userLastName = string;
	}

	/**
	 *  
	 * @param string The user type.
	 */
	public void setUserType(String string)
	{
		userType = string;
	}

	/**
	 * @param string
	 */
	public void setUserTypeId(String userTypeId)
	{
		this.userTypeId = userTypeId;
	}

	/**
	 * @return
	 */
	public boolean getIsASA()
	{
		boolean result = false;
		if(PDSecurityConstants.USER_TYPE_ASA.equals(userTypeId)){
			result = true;
		}
		return result;
	}

	/**
	 * @return
	 */
	public boolean getIsLiason()
	{
		boolean result = false;
		if(PDSecurityConstants.USER_TYPE_LIASON.equals(userTypeId)){
			result = true;
		}
		return result;
	}

	/**
	 * @return
	 */
	public boolean getIsMA()
	{
		boolean result = false;
		if(PDSecurityConstants.USER_TYPE_MA.equals(userTypeId)){
			result = true;
		}
		return result;
	}

	/**
	 * @return
	 */
	public boolean getIsSA()
	{
		boolean result = false;
		if(PDSecurityConstants.USER_TYPE_SA.equals(userTypeId)){
			result = true;
		}
		return result;
	}
	
	public boolean getUserTypeExists(){
		boolean result = false;
		if(userTypeId != null && !userTypeId.equals("")){
			result = true;
		}
		return result;
	}
	
	/**
	 * @return Returns the userMiddleName.
	 */
	public String getUserMiddleName() {
		return userMiddleName;
	}
	/**
	 * @param userMiddleName The userMiddleName to set.
	 */
	public void setUserMiddleName(String userMiddleName) {
		this.userMiddleName = userMiddleName;
	}
	/**
	 * @return Returns the jims2LogonId.
	 */
	public String getJims2LogonId() {
		return jims2LogonId;
	}
	/**
	 * @param jims2LogonId The jims2LogonId to set.
	 */
	public void setJims2LogonId(String jims2LogonId) {
		this.jims2LogonId = jims2LogonId;
	}
	/**
	 * @return Returns the serviceProviderName.
	 */
	public String getServiceProviderName() {
		return serviceProviderName;
	}
	/**
	 * @param serviceProviderName The serviceProviderName to set.
	 */
	public void setServiceProviderName(String serviceProviderName) {
		this.serviceProviderName = serviceProviderName;
	}
}