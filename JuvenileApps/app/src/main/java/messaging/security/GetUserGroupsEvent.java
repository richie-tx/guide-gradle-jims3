//Source file: C:\\views\\archproduction\\app\\src\\messaging\\security\\GetUserGroupsEvent.java

package messaging.security;

import mojo.km.messaging.RequestEvent;

public class GetUserGroupsEvent extends RequestEvent
{
    //87191
	//private String agencyId;
	//private String agencyName;
	private String userGroupDescription;
	private String userGroupName;
//	private String statusId;
//	private String userGroupTypeId;

	/**
	 * @roseuid 429720C0012C
	 */
	public GetUserGroupsEvent()
	{

	}

	/**
	 * Access method for the agencyId property.
	 * 
	 * @return   the current value of the agencyId property
	 *//*
	public java.lang.String getAgencyId()
	{
		return agencyId;
	}

	*//**
	 * Access method for the agencyName property.
	 * 
	 * @return   the current value of the agencyName property
	 *//*
	public java.lang.String getAgencyName()
	{
		return agencyName;
	}
	
	*//**
	 * Access method for the statusId property.
	 * 
	 * @return   the current value of the statusId property
	 *//*
	public java.lang.String getStatusId()
	{
		return statusId;
	}	*/

	/**
	 * Access method for the userGroupDescription property.
	 * 
	 * @return   the current value of the userGroupDescription property
	 */
	public String getUserGroupDescription()
	{
		return userGroupDescription;
	}

	/**
	 * Access method for the userGroupName property.
	 * 
	 * @return   the current value of the userGroupName property
	 */
	public String getUserGroupName()
	{
		return userGroupName;
	}

	/**
	 * Sets the value of the agencyId property.
	 * 
	 * @param aAgencyId the new value of the agencyId property
	 */
	/*public void setAgencyId(java.lang.String aAgencyId)
	{
		agencyId = aAgencyId;
	}

	*//**
	 * Sets the value of the agencyName property.
	 * 
	 * @param aAgencyName the new value of the agencyName property
	 *//*
	public void setAgencyName(java.lang.String aAgencyName)
	{
		agencyName = aAgencyName;
	}

	*//**
	 * Sets the value of the statusId property.
	 * 
	 * @param aStatusId the new value of the statusId property
	 *//*
	public void setStatusId(java.lang.String aStatusId)
	{
		statusId = aStatusId;
	}
*/
	/**
	 * Sets the value of the userGroupDescription property.
	 * 
	 * @param aUserGroupDescription the new value of the userGroupDescription property
	 */
	public void setUserGroupDescription(String aUserGroupDescription)
	{
		userGroupDescription = aUserGroupDescription;
	}

	/**
	 * Sets the value of the userGroupName property.
	 * 
	 * @param aUserGroupName the new value of the userGroupName property
	 */
	public void setUserGroupName(String aUserGroupName)
	{
		userGroupName = aUserGroupName;
	}
	/**
	 * @return
	 *//*
	public String getUserGroupTypeId()
	{
		return userGroupTypeId;
	}

	*//**
	 * @param string
	 *//*
	public void setUserGroupTypeId(String string)
	{
		userGroupTypeId = string;
	}*/

}