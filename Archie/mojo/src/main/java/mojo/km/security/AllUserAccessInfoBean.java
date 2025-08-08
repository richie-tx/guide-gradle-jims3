package mojo.km.security;

import java.util.List;

public class AllUserAccessInfoBean
{

    private List<RolesEntityBean> roles;

    private List<UserGroupEntityBean> usergroups;

    private List<UsergroupRoleEntityBean> usergrouproles;

    public List<UserRoleEntityBean> userroles;

    public List<UserUserGroupEntityBean> userusergroups;

    public List<JIMS2FeaturesEntityBean> features;

    public List<FeatureRolesEntityBean> featureroles;

    public List<FeatureClearanceEntityBean> featureclearances;
    
    

    /** @return the roles */
    public List<RolesEntityBean> getRoles()
    {
	return roles;
    }

    /** @param roles
     *            the roles to set */
    public void setRoles(List<RolesEntityBean> roles)
    {
	this.roles = roles;
    }

    /** @return the usergroups */
    public List<UserGroupEntityBean> getUsergroups()
    {
	return usergroups;
    }

    /** @param usergroups
     *            the usergroups to set */
    public void setUsergroups(List<UserGroupEntityBean> usergroups)
    {
	this.usergroups = usergroups;
    }

    /** @return the usergrouproles */
    public List<UsergroupRoleEntityBean> getUsergrouproles()
    {
	return usergrouproles;
    }

    /** @param usergrouproles
     *            the usergrouproles to set */
    public void setUsergrouproles(List<UsergroupRoleEntityBean> usergrouproles)
    {
	this.usergrouproles = usergrouproles;
    }

    /** @return the userroles */
    public List<UserRoleEntityBean> getUserroles()
    {
	return userroles;
    }

    /** @param userroles
     *            the userroles to set */
    public void setUserroles(List<UserRoleEntityBean> userroles)
    {
	this.userroles = userroles;
    }

    /** @return the userusergroups */
    public List<UserUserGroupEntityBean> getUserusergroups()
    {
	return userusergroups;
    }

    /** @param userusergroups
     *            the userusergroups to set */
    public void setUserusergroups(List<UserUserGroupEntityBean> userusergroups)
    {
	this.userusergroups = userusergroups;
    }

    /** @return the features */
    public List<JIMS2FeaturesEntityBean> getFeatures()
    {
	return features;
    }

    /** @param features
     *            the features to set */
    public void setFeatures(List<JIMS2FeaturesEntityBean> features)
    {
	this.features = features;
    }

    /** @return the featureroles */
    public List<FeatureRolesEntityBean> getFeatureroles()
    {
	return featureroles;
    }

    /** @param featureroles
     *            the featureroles to set */
    public void setFeatureroles(List<FeatureRolesEntityBean> featureroles)
    {
	this.featureroles = featureroles;
    }

    /** @return the featureclearances */
    public List<FeatureClearanceEntityBean> getFeatureclearances()
    {
	return featureclearances;
    }

    /** @param featureclearances
     *            the featureclearances to set */
    public void setFeatureclearances(List<FeatureClearanceEntityBean> featureclearances)
    {
	this.featureclearances = featureclearances;
    }

}
