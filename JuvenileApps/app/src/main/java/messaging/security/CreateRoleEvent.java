//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\messaging\\security\\CreateRoleEvent.java

package messaging.security;

import java.util.Collection;
import mojo.km.messaging.Composite.CompositeRequest;

public class CreateRoleEvent extends CompositeRequest 
{
	private String roleName;
	private String roleId;
	private String roleCreatorFirstName;
	private String roleCreatorLastName;
	private String roleDescription;
	private Collection featuresList = null;
	private Collection agenciesList = null;
	private String roleType;
	private String roleCreatorId;
   
   /**
    * @roseuid 42568FDA00BB
    */
   public CreateRoleEvent() 
   {
    
   }
   
 
	/**
	 * @return
	 */

	/**
	 * @return
	 */
	public String getRoleCreatorFirstName()
	{
		return roleCreatorFirstName;
	}

	/**
	 * @return
	 */
	public String getRoleCreatorLastName()
	{
		return roleCreatorLastName;
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
	public String getRoleId()
	{
		return roleId;
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
	public void setRoleCreatorFirstName(String string)
	{
		roleCreatorFirstName = string;
	}

	/**
	 * @param string
	 */
	public void setRoleCreatorLastName(String string)
	{
		roleCreatorLastName = string;
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
	public void setRoleId(String string)
	{
		roleId = string;
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
	public Collection getAgenciesList()
	{
		return agenciesList;
	}

	/**
	 * @return
	 */
	public Collection getFeaturesList()
	{
		return featuresList;
	}

	/**
	 * @param collection
	 */
	public void setAgenciesList(Collection collection)
	{
		agenciesList = collection;
	}

	/**
	 * @param collection
	 */
	public void setFeaturesList(Collection collection)
	{
		featuresList = collection;
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
