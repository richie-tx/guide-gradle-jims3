//Source file: C:\\views\\archproduction\\framework\\mojo-jims2\\mojo.java\\src\\messaging\\security\\UpdateRoleEvent.java

package messaging.security;

import java.util.Collection;
import mojo.km.messaging.RequestEvent;

public class UpdateRoleEvent extends RequestEvent 
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
   private boolean updateFeatureFlag;
   private boolean updateAgencyFlag;
   /**
    * @roseuid 4256F0C5031C
    */
   public UpdateRoleEvent() 
   {
    
   }
   
   /**
    * @param roleName
    * @roseuid 4256EB880080
    */
   public void setRoleName(String roleName) 
   {
      this.roleName = roleName;
   }
   
   /**
    * @roseuid 4256EB880082
    */
   public String getRoleName() 
   {
      return this.roleName; 
   }
   
   /**
    * @param roleCreatorFirstName
    * @roseuid 4256EB88008C
    */
   public void setRoleCreatorFirstName(String roleCreatorFirstName) 
   {
      this.roleCreatorFirstName = roleCreatorFirstName;
   }
   
   /**
    * @return String
    * @roseuid 4256EB88008E
    */
   public String getRoleCreatorFirstName() 
   {
      return this.roleCreatorFirstName;
   }
   
   /**
    * @param roleCreatorLastName
    * @roseuid 4256EB880090
    */
   public void setRoleCreatorLastName(String roleCreatorLastName) 
   {
	  this.roleCreatorLastName = roleCreatorLastName;
   }
   
   /**
    * @return String
    * @roseuid 4256EB880092
    */
   public String getRoleCreatorLastName() 
   {
     return this.roleCreatorLastName;
   }
   
   /**
    * @param roleDescription
    * @roseuid 4256EB880094
    */
   public void setRoleDescription(String roleDescription) 
   {
      this.roleDescription = roleDescription; 
   }
   
   /**
    * @return String
    * @roseuid 4256EB880096
    */
   public String getRoleDescription() 
   {
      return this.roleDescription;
   }

   /**
    * @return
    */
    public String getRoleId()
    {
	   return this.roleId;
    }

    /**
     * @param string
     */
     public void setRoleId(String roleId)
     {
	    this.roleId = roleId;
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
	public void setRoleType(String string)
	{
		roleType = string;
	}
	

	/**
	 * @return
	 */
	public boolean isUpdateAgencyFlag()
	{
		return updateAgencyFlag;
	}
	
	/**
	 * @return
	 */
	public boolean isUpdateFeatureFlag()
	{
		return updateFeatureFlag;
	}
	
	/**
	 * @param b
	 */
	public void setUpdateAgencyFlag(boolean b)
	{
		updateAgencyFlag = b;
	}
	
	/**
	 * @param b
	 */
	public void setUpdateFeatureFlag(boolean b)
	{
		updateFeatureFlag = b;
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
