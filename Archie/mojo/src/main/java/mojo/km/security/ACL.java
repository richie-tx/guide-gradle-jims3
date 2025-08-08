package mojo.km.security;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ACL implements java.io.Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    private ArrayList<ACE> aces;

    private HashSet<String> featureSet;

    public ACL()
    {
	/*
	 * Build the ACL. 0. If the user type is one of the AllAccessUserTypes defined in the
	 * Security.xml this user gets all Features. Retrieve all features and assign them to the
	 * ACEs. 1. Get all directly assigned Roles 2. Get all Roles assigned through User Groups of
	 * which the user is a member 3. For each Role, go through the features and create ACEs. Put
	 * the ACEs into a collection. What makes this a bit more complicated is that an ACE (i.e. a
	 * Feature) can be constrained by Constraints attached to the Role. If a Feature is granted
	 * through two Roles with different Constraints, there should be one ACE created with the
	 * product of the two Roles Constraints. Example: Feature1 is granted through Role1 which
	 * has Constraint Agency1 Feature1 is granted through Role2 which has Constraint Agency2
	 * 
	 * In this case one ACE will be created for Feature1. The ACE will have the Constraints
	 * Agency1 and Agency2
	 */
	this.aces = new ArrayList<ACE>();
	this.featureSet = new HashSet<String>(); //87188
    }

    /** @param userOID */
    /*	private void buildAccess(String userId,UserProfileResponse theUser)
    	{
    		LoginEvent loginEvent = new LoginEvent();
    		loginEvent.setUsername(userId);

    		Map constraintMap = this.buildConstraintMap(loginEvent);

    		List features = UserFeature.findAll(loginEvent);

    		int len = features.size();

    		HashMap<String, ACE> aceMap = new HashMap<String, ACE>(len);

    		for (int i = 0; i < len; i++)
    		{
    			UserFeature userFeature = (UserFeature) features.get(i);

    			ACE ace = (ACE) aceMap.get(userFeature.getName());
    			if (ace == null)
    			{
    				FeatureBean featureBean = userFeature.valueObject();
    				ace = new ACE(featureBean, userId);
    				aceMap.put(userFeature.getName(), ace);
    			}

    			String roleId = userFeature.getRoleId();
    			if (constraintMap.containsKey(roleId))
    			{
    				List constraints = (List) constraintMap.get(roleId);
    				ace.addAll(constraints);
    			}
    		}

    		this.aces = new ArrayList(len);
    		this.featureSet = new HashSet(len);

    		Iterator<ACE> i = aceMap.values().iterator();
    		while (i.hasNext())
    		{
    			ACE ace = i.next();
    			this.aces.add(ace);
    			this.featureSet.add(ace.getFeature().getOID());
    		}
    	}*/

    /** @param loginEvent
     * @return */
    /*	private Map buildConstraintMap(LoginEvent loginEvent)
    	{
    		List constraints = UserConstraint.findAll(loginEvent);
    		Map constraintMap = new HashMap();

    		int len = constraints.size();
    		for (int i = 0; i < len; i++)
    		{
    			UserConstraint constraint = (UserConstraint) constraints.get(i);
    			String roleId = constraint.getRoleId();
    			List roleConstraints = (List) constraintMap.get(roleId);
    			if (roleConstraints == null)
    			{
    				roleConstraints = new ArrayList();
    				constraintMap.put(roleId, roleConstraints);
    			}
    			roleConstraints.add(constraint.valueObject());
    		}

    		return constraintMap;
    	}*/
    /** #87188 Modified to accommodate the feature in general
     * 
     * @param userId
     * @param theUser */
    private void buildAccess(String userId, SecurityUser theUser)
    {
	HashMap<JIMS2FeaturesEntityBean, ACE> acesMap = new HashMap<JIMS2FeaturesEntityBean, ACE>();

	//IHome home = new Home();
	List<JIMS2FeaturesEntityBean> features = theUser.getUserAccess().getFeatures();//home.findAllList(Feature.class);
	if (features != null)
	{
	    int len = features.size();
	    for (int i = 0; i < len; i++)
	    {
		JIMS2FeaturesEntityBean feature = features.get(i);

		// Check if there is already an ACE for this feature			
		ACE ace = acesMap.get(feature);
		if (ace == null)
		{
		    ace = new ACE(getFeatureBean(feature), userId);
		    acesMap.put(feature, ace);
		    this.featureSet.add(ace.getFeature().getOID());
		}
	    }
	}
	aces = new ArrayList<ACE>(acesMap.values());
    }

    /** @return java.util.Collection
     * @roseuid 423068C60047 */
    public List<ACE> getACEs()
    {
	return aces;
    }

    //#87188
    /*private ConstraintBean getConstraintBean(Constraint constraint)
    {
    	ConstraintBean cb = new ConstraintBean();
    	cb.setConstrainerId(constraint.getConstrainerId());
    	cb.setConstrainerType(constraint.getConstrainerType());
    	cb.setConstrainsId(constraint.getConstrainsId());
    	cb.setConstrainsType(constraint.getConstrainsType());
    	return cb;
    }*/

    private FeatureBean getFeatureBean(JIMS2FeaturesEntityBean feature)
    {
	FeatureBean fb = new FeatureBean();
	fb.setName(feature.getDescription());
	fb.setFeatureCategory(feature.getFeaturecategory());
	fb.setFeatureType(feature.getFeaturetypejims2());
	fb.setParentId(feature.getParentfeaturename());
	fb.setOID(feature.getFeaturename());
	fb.setDescription(feature.getDescription());
	return fb;
    }

    /** @return Returns the featureSet. */
    public Set<String> getFeatureSet()
    {
	return featureSet;
    }

    /** //#87188
     * 
     * @param theUser
     * @return */
    /*private boolean hasFullAccess(UserProfileResponse theUser)
    {
    	boolean fullAccess = false;
    	List fullAccessTypes = SecurityProperties.getInstance().getFullAccessUserTypes();
    	int len = fullAccessTypes.size();
    	for (int i = 0; i < len; i++)
    	{
    		String fullTypeId = (String) fullAccessTypes.get(i);
    		if (fullTypeId.equalsIgnoreCase(theUser.getUserTypeId()))
    		{
    			fullAccess = true;
    			break;
    		}
    	}
    	return fullAccess;
    }
    *///#87188
    public void init(SecurityUser theUser)
    {
	//boolean fullAccess = this.hasFullAccess(theUser);

	String userOID = theUser.getJIMSLogonId();

	//if (fullAccess == true)
	//{
	this.buildAccess(userOID, theUser);
	//}
	/*else
	{
		this.buildAccess(userOID,theUser);
	}*/
    }

    /*	public void initOld(UserProfileResponse theUser)
    	{
    		boolean fullAccess = false;
    		List fullAccessTypes = SecurityProperties.getInstance().getFullAccessUserTypes();
    		Iterator a = fullAccessTypes.iterator();
    		while (a.hasNext())
    		{
    			String fullTypeId = (String) a.next();
    			if (fullTypeId.equalsIgnoreCase(theUser.getUserTypeId()))
    			{
    				fullAccess = true;
    				break;
    			}
    		}
    		HashMap<JIMS2FeaturesResponse,ACE> acesMap = new HashMap<JIMS2FeaturesResponse,ACE>();
            	if (fullAccess)
            	{
            	    List<JIMS2FeaturesResponse> allFeatures = theUser.getUseraccesses().getFeatures();//Feature.findAll(new AllQueryEvent());
            	    if (allFeatures != null)
            	    {
            		Iterator<JIMS2FeaturesResponse> allFeaturesItr = allFeatures.iterator();
            		while (allFeaturesItr.hasNext())
            		{
            		    JIMS2FeaturesResponse feature = allFeaturesItr.next();
            		    
            		     * Check if there is already an ACE for this feature
            		     
            		    ACE ace = (ACE) acesMap.get(feature);
            		    if (ace == null)
            		    {
            			ace = new ACE(getFeatureBean(feature), theUser.getJIMSLogonId());
            			acesMap.put(feature, ace);
            		    }
            		}
            	    }
            	}
    		else
    		{

    			ArrayList allRoles = new ArrayList();
    			Collection userRoles = theUser.getRoles();
    			if (userRoles != null)
    			{
    				allRoles.addAll(userRoles);
    			}

    			Collection userGroups = theUser.getUserGroups();
    			Iterator ugi = userGroups.iterator();
    			while (ugi.hasNext())
    			{
    				UserGroup ug = (UserGroup) ugi.next();
    				Collection ugRoles = ug.getRoles();
    				if (ugRoles != null)
    				{
    					allRoles.addAll(ugRoles);
    				}
    			}

    			Iterator allRolesIterator = allRoles.iterator();
    			while (allRolesIterator.hasNext())
    			{
    				Role role = (Role) allRolesIterator.next();
    				Collection features = role.getFeatures();
    				Iterator featuresIterator = features.iterator();
    				while (featuresIterator.hasNext())
    				{
    					Feature feature = (Feature) featuresIterator.next();
    					
    					 * Check if there is already an ACE for this feature
    					 
    					ACE ace = (ACE) acesMap.get(feature);
    					if (ace == null)
    					{
    						ace = new ACE(getFeatureBean(feature), theUser.getOID().toString());
    						acesMap.put(feature, ace);
    					}
    					Collection constraints = role.getConstraints();
    					if (constraints != null && constraints.size() > 0)
    					{
    						Iterator constraintsIterator = constraints.iterator();
    						while (constraintsIterator.hasNext())
    						{
    							Constraint constraint = (Constraint) constraintsIterator.next();
    							// addConstraint removes duplicates auomatically
    							ace.addConstraint(getConstraintBean(constraint));
    						}
    					}
    				}
    			}
    		}
    		
    		 * At this point the value set of the hashtable contains a unique set of all the ACEs
    		 
    		this.aces = new ArrayList();
    		this.aces.addAll(acesMap.values());

    		int len = aces.size();
    		this.featureSet = new HashSet(len);

    		for (int i = 0; i < len; i++)
    		{
    			ACE ace = aces.get(i);
    			this.featureSet.add(ace.getFeature().getOID());
    		}
    	}*/
}
