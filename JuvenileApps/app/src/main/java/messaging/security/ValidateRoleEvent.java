package messaging.security;

import java.util.Collection;
import mojo.km.messaging.Composite.CompositeRequest;

public class ValidateRoleEvent extends CompositeRequest 
{
	private String roleName;
	private Collection agencyIdList = null;
   
   /**
    * @roseuid 42568FDA00BB
    */
   public ValidateRoleEvent() 
   {
    
   }
   
	/**
	 * @return
	 */
	public String getRoleName()
	{
		return roleName;
	}

	/**
	 * @param string
	 */
	public void setRoleName(String string)
	{
		roleName = string;
	}
	/**
	 * @return
	 */
	public Collection getAgencyIdList()
	{
		return agencyIdList;
	}

	/**
	 * @param collection
	 */
	public void setAgencyIdList(Collection collection)
	{
		agencyIdList = collection;
	}

}
