/*
 * Project: JIMS2
 * Class:   messaging.security.GetUsersForUserAdministrationEvent
 * Version: 1.0.0
 *
 * Date:    2005-04-28
 *
 * Author:  Anand Thorat
 * Email:   athorat@jims.hctx.net
 */

package messaging.security;

public class GetUsersForUserAdministrationEvent extends mojo.km.messaging.RequestEvent
{
	private String agencyId;
	private String agencyName;
	private String agencyType;
	private String departmentId;
	private String departmentName;
	private String firstName;
	private String lastName;
	private String logonId;
	private String userTypeId;

	/**
	 *  
	 * @return  String
	 * @roseuid 42712ADE00B4
	 */
	public String getAgencyId()
	{
		return this.agencyId;
	} 

	/**
	 *  
	 * @return  String
	 * @roseuid 42712ADE00B0
	 */
	public String getAgencyName()
	{
		return this.agencyName;
	} 

	/**
	 *  
	 * @return  String
	 * @roseuid 42712ADE00BC
	 */
	public String getAgencyType()
	{
		return this.agencyType;
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
	 * @return  String
	 * @roseuid 42712ADE00C0
	 */
	public String getFirstName()
	{
		return this.firstName;
	} 

	/**
	 *  
	 * @return  String
	 * @roseuid 42712ADE00C4
	 */
	public String getLastName()
	{
		return this.lastName;
	} 

	/**
	 *  
	 * @return  String
	 * @roseuid 42712ADE00C8
	 */
	public String getLogonId()
	{
		return this.logonId;
	} 

	/**
	 *  
	 * @return  String
	 * @roseuid 42712ADE00CD
	 */
	public String getUserTypeId()
	{
		return this.userTypeId;
	} 

	/**
	 *  
	 * @param agencyId @roseuid 42712ADE00B2
	 */
	public void setAgencyId(String agencyId)
	{
		this.agencyId = agencyId;
	} 

	/**
	 *  
	 * @param agencyName @roseuid 42712ADE00AE
	 */
	public void setAgencyName(String agencyName)
	{
		this.agencyName = agencyName;
	} 

	/**
	 *  
	 * @param agencyType @roseuid 42712ADE00B6
	 */
	public void setAgencyType(String agencyType)
	{
		this.agencyType = agencyType;
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
	 * @param firstName @roseuid 42712ADE00BE
	 */
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;

	} 

	/**
	 *  
	 * @param lastName @roseuid 42712ADE00C2
	 */
	public void setLastName(String lastName)
	{
		this.lastName = lastName;

	} 

	/**
	 *  
	 * @param logonId @roseuid 42712ADE00C6
	 */
	public void setLogonId(String logonId)
	{
		this.logonId = logonId;

	} 

	/**
	 *  
	 * @param userTypeId @roseuid 42712ADE00CB
	 */
	public void setUserTypeId(String userType)
	{
		this.userTypeId = userType;
	} 
} 