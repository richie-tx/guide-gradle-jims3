//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\messaging\\security\\GetRolesEvent.java

package messaging.security;

import mojo.km.messaging.RequestEvent;

public class GetRolesEvent extends RequestEvent 
{
	private String roleDescription;
	private String roleName;
	private String roleType;
	private String roleCreatorId;
   
   /**
	* @roseuid 42569D9B03D8
	*/
   public GetRolesEvent() 
   {
    
   }
   

	/**
	 * @return
	 */
	public String getRoleDescription()
	{
		return roleDescription;
	}


	/**
	 * @return
	 */
	public String getRoleName()
	{
		return roleName;
	}

	/**
	 * @return
	 */
	public String getRoleType()
	{
		return roleType;
	}

	/**
	 * @param string
	 */
	public void setRoleDescription(String string)
	{
		roleDescription = string;
	}


	/**
	 * @param string
	 */
	public void setRoleName(String string)
	{
		roleName = string;
	}

	/**
	 * @param string
	 */
	public void setRoleType(String string)
	{
		roleType = string;
	}

	/**
	 * @return
	 */
	public String getRoleCreatorId()
	{
		return roleCreatorId;
	}

	/**
	 * @param string
	 */
	public void setRoleCreatorId(String string)
	{
		roleCreatorId = string;
	}

}
