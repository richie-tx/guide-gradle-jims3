//Source file: C:\\views\\archproduction\\app\\src\\messaging\\security\\GetRolesByConstarintsEvent.java

package messaging.security;

import mojo.km.messaging.RequestEvent;

/**
 * Class GetRolesByConstarintsEvent.
 * @author  Anand Thorat
 * @version  1.0.0
 */
public class GetRolesByConstraintsEvent extends RequestEvent
{

	/**
	 * ------------------------------------------------------------------------
	 * --- fields                                                           ---
	 * ------------------------------------------------------------------------
	 */
	private String roleDescription;
	private String roleName;
	private String agencyName;
	private String agencyId;
	private String creatorFirstName;
	private String creatorLastName;
	private String creatorId;
	private String roleType;
	private String agencyTypeId;

	/**
	 * ------------------------------------------------------------------------
	 * --- constructor                                                      ---
	 * ------------------------------------------------------------------------
	 * @roseuid 4256F0C50242
	 */
	public GetRolesByConstraintsEvent()
	{

	} //end of messaging.security.GetRolesByConstarintsEvent.GetRolesByConstarintsEvent

	/**
	 * 
	 * ------------------------------------------------------------------------
	 * --- methods                                                          ---
	 * ------------------------------------------------------------------------
	 * @param roleDescription
	 * @roseuid 4256EB8703AF
	 */
	public void setRoleDescription(String roleDescription)
	{
		this.roleDescription = roleDescription;

	} //end of messaging.security.GetRolesByConstarintsEvent.setRoleDescription

	/**
	 * 
	 * @return  String
	 * @roseuid 4256EB8703B1
	 */
	public String getRoleDescription()
	{
		return this.roleDescription;
	} //end of messaging.security.GetRolesByConstarintsEvent.getRoleDescription

	/**
	 * 
	 * @param roleName
	 * @roseuid 4256EB8703BD
	 */
	public void setRoleName(String roleName)
	{
		this.roleName = roleName;

	} //end of messaging.security.GetRolesByConstarintsEvent.setRoleName

	/**
	 * 
	 * @return  The role name.
	 * @return  The role name.
	 * @roseuid 4256EB8703BF
	 */
	public String getRoleName()
	{
		return this.roleName;
	} //end of messaging.security.GetRolesByConstarintsEvent.getRoleName

	/**
	 * 
	 * @param agencyName
	 * @roseuid 4256EB8703C1
	 */
	public void setAgencyName(String agencyName)
	{
		this.agencyName = agencyName;

	} //end of messaging.security.GetRolesByConstarintsEvent.setAgencyName

	/**
	 * 
	 * @return  String
	 * @roseuid 4256EB8703C3
	 */
	public String getAgencyName()
	{
		return this.agencyName;
	} //end of messaging.security.GetRolesByConstarintsEvent.getAgencyName

	/**
	 * 
	 * @return  The agency id.
	 * @roseuid 42725319006E
	 */
	public String getAgencyId()
	{
		return this.agencyId;
	} //end of messaging.security.GetRolesByConstarintsEvent.getAgencyId

	/**
	 * 
	 * @return  The creator first name.
	 * @roseuid 42725319006F
	 */
	public String getCreatorFirstName()
	{
		return this.creatorFirstName;
	} //end of messaging.security.GetRolesByConstarintsEvent.getCreatorFirstName

	/**
	 * 
	 * @return  The creator last name.
	 * @roseuid 42725319007D
	 */
	public String getCreatorLastName()
	{
		return this.creatorLastName;
	} //end of messaging.security.GetRolesByConstarintsEvent.getCreatorLastName

	/**
	 * 
	 * @param string The agency id.
	 * @roseuid 42725319007E
	 */
	public void setAgencyId(String string)
	{
		this.agencyId = string;

	} //end of messaging.security.GetRolesByConstarintsEvent.setAgencyId

	/**
	 * 
	 * @param string The creator first name.
	 * @roseuid 42725319008D
	 */
	public void setCreatorFirstName(String string)
	{
		this.creatorFirstName = string;

	} //end of messaging.security.GetRolesByConstarintsEvent.setCreatorFirstName

	/**
	 * 
	 * @param string The creator last name.
	 * @roseuid 42725319008F
	 */
	public void setCreatorLastName(String string)
	{
		this.creatorLastName = string;

	} //end of messaging.security.GetRolesByConstarintsEvent.setCreatorLastName

	/**
	 * 
	 * @return  The creator id.
	 * @roseuid 42725319009D
	 */
	public String getCreatorId()
	{
		return this.creatorId;
	} //end of messaging.security.GetRolesByConstarintsEvent.getCreatorId

	/**
	 * 
	 * @param string The creator id.
	 * @roseuid 42725319009E
	 */
	public void setCreatorId(String string)
	{
		this.creatorId = string;

	} //end of messaging.security.GetRolesByConstarintsEvent.setCreatorId

	/**
	 * 
	 * @return  The role type.
	 * @roseuid 4272531900AC
	 */
	public String getRoleType()
	{
		return this.roleType;
	} //end of messaging.security.GetRolesByConstarintsEvent.getRoleType

	/**
	 * 
	 * @param string The role type.
	 * @roseuid 4272531900AD
	 */
	public void setRoleType(String string)
	{
		this.roleType = string;

	}

	/**
	 * @return
	 */
	public String getAgencyTypeId()
	{
		return agencyTypeId;
	}

	/**
	 * @param string
	 */
	public void setAgencyTypeId(String string)
	{
		agencyTypeId = string;
	}

}
