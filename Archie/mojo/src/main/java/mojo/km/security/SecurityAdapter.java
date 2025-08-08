package mojo.km.security;

import java.util.List;
import java.util.Set;

import mojo.km.config.SecurityProperties;
import mojo.km.security.role.ldap.IUserContext;

public class SecurityAdapter implements ISecurityManager
{
	/**
     * 
     */
    
	private ACL acl;
	private IUserInfo iUser;

	/**
	 * @roseuid 422C77F30259
	 */
	public SecurityAdapter()
	{

	}

	/**
	 * Will set the UserBean for this instance of the security
	 * manager and instantiate the proper ACL and ACEs for this
	 * user.  Passthrough to setUser(User user)
	 * @param user
	 * @roseuid 42247C8D00A3
	 */
	public SecurityAdapter(IUserInfo user)
	{
		this.setIUserInfo(user);
	}

	/* (non-Javadoc)
	 * @see mojo.km.security.ISecurityManager#setUser(mojo.km.security.User)
	 */
	public void buildAcl(SecurityUser user) //#87188
	{
		try
		{
			this.acl = new ACL();
			this.acl.init(user);
		}
		catch (Exception e)
		{
		    // TODO (JMF) Should this exception be propogated?
			e.printStackTrace();
		}
	}

	/**
	 * @return mojo.km.security.ACL
	 * @roseuid 4224C9F4024A
	 */
	public ACL getACL()
	{
		return acl;
	}

	/**
	 * 87191
	 * @param feature
	 * @return java.util.Collection
	 * @roseuid 422C77F3029F
	 */
	/*public List<ACE> getConstraintsForWhichUserCanDoFeature(String feature)
	{
		
		 * We go through all the ACEs to see if there is one for the specified feature
		 * If so we return the constraints (if any) associated with that ACE.
		 * If the user is not allowed to perform the feature we return null.
		 * If the user is allowed to perform the feature and is not limited
		 * to any constraints we return an empty Collection
		 
		List<ACE> aces = acl.getACEs();
		int len = aces.size();
		for(int i=0;i<len;i++)
		{
			ACE ace = (ACE) aces.get(i);
			if (ace.getFeature().getOID().equals(feature))
			{
				if (ace.getConstraints() != null) // no contraints //87191
				{
					return ace.getConstraints();
				}
				else
       			// If the user has the feature but it is not limited to any Constraints
				//{
					return new ArrayList<ACE>();
				//}
			}
		}
		// User does not have the feature
		return null;
	}*/

	/**
	 * @return java.util.Collection
	 * @roseuid 422C77F302DB
	 */
	public Set<String> getFeatures()
	{
		/*Set features = new HashSet();
		List aces = acl.getACEs();
		int len = aces.size();
		for(int i=0;i<len;i++)
		{
			ACE ace = (ACE) aces.get(i);
			String featureOID = ace.getFeature().getOID();
			features.add(featureOID);
		}*/
		return acl.getFeatureSet();
	}

	/**
	 * @return mojo.km.security.UserBean
	 * @roseuid 422C77F302C7
	 */
	public IUserInfo getIUserInfo()
	{
		return (IUserInfo) iUser;
	}

	/* (non-Javadoc)
	 * @see mojo.km.security.ISecurityManager#getUser()
	 */
	public IUserContext getUser()
	{
		return null;
	}

	/**
	 * @param feature
	 * @return boolean
	 * @roseuid 422C77F30277
	 */
	public boolean isAllowed(String feature)
	{
		return isAllowed(feature, null);
	}

	/**
	 * @param feature
	 * @param constraint
	 * @param constraintToCheck
	 * @return Boolean
	 * @roseuid 4225BE9E016C
	 */
	public boolean isAllowed(String feature, Constraint constraintToCheck)
	{
		/*
		 * Iterate through ACEs, check if the feature is present.
		 * If present, check if it is limited to any constraints.
		 * If it is not, return true. If it is, return true
		 * only if the passed in constraint is in the list of 
		 * constraints.
		 * 
		 * Note:  If user is a full access user as defined in the
		 * Security.xml return true always
		 */

		List types = SecurityProperties.getInstance().getFullAccessUserTypes();
		int len = types.size();
		for(int i=0;i<len;i++)
		{
			String type = (String) types.get(i);
			if (type.equalsIgnoreCase(this.iUser.getUserTypeId()))
			{
				return true;
			}
		}

		if (acl != null)
		{
			List aces = acl.getACEs();
			int acesLen = aces.size(); 
			for(int i=0;i<acesLen;i++)
			{
				ACE ace = (ACE) aces.get(i);
				if (ace.getFeature().getOID().equalsIgnoreCase(feature.toLowerCase()))
				{
				    //87191 not used anymore.
					/*if (constraintToCheck != null && ace.getConstraints() != null && ace.getConstraints().size() > 0)
					{
						List constraints = ace.getConstraints();
						len = constraints.size();
						for(int j=0;j<len;j++)
						{
							Constraint constraint = (Constraint) constraints.get(j);
							if (constraintToCheck.equals(constraint))
							{
								// The constraint to check is present in the ACE.
								// We return true...
								return true;
							}
						}
					}
					else*/ //87191. Always returned through ACE
					//{
						// No need to check against constraint. Feature is granted 
						// through the ACE. We return true...
						return true;
					//}
				}
			}
		}
		return false;
	}

	/**
	 * Returns true if the user has been granted any of the specified Features
	 * or if the passed array is empty or null.
	 * @param features
	 * @return boolean
	 * @roseuid 42499541011C
	 */
	public boolean isAllowed(String[] features)
	{
		if (features == null || features.length == 0)
		{
			return true;
		}
		boolean granted = false;
		for (int i = 0; i < features.length; i++)
		{
			if (isAllowed(features[i]))
			{
				granted = true;
				break;
			}
		}
		return granted;
	}

	/**
	 * @param type
	 * @return boolean
	 * @roseuid 42307119000D
	 */
	public boolean isUserOfType(String typeId)
	{
		return (iUser.getUserTypeId().equalsIgnoreCase(typeId));
	}

	/**
	 * 
	 * @param types
	 * @return true, if user is of one of the types
	 */
	public boolean isUserOfType(String[] types)
	{
		if (types == null || types.length == 0)
		{
			return true;
		}
		boolean granted = false;
		for (int i = 0; i < types.length; i++)
		{
			if (isUserOfType(types[i]))
			{
				granted = true;
				break;
			}
		}
		return granted;
	}

	/* (non-Javadoc)
	 * @see mojo.km.security.ISecurityManager#setIUserInfo(mojo.km.security.IUserInfo)
	 */
	public void setIUserInfo(IUserInfo iUserInfo)
	{
		this.iUser = iUserInfo;
	}

}
