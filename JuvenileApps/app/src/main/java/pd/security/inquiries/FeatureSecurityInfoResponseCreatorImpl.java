/*
 * Created on Aug 15, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.security.inquiries;

/**
 * @author mchowdhury TODO To change the template for this generated type
 *         comment go to Window - Preferences - Java - Code Style - Code
 *         Templates
 */
public class FeatureSecurityInfoResponseCreatorImpl/* extends ResponseCommonUtil implements ResponseCreator*/
{
    /*   	*//**
     * @param object
     * @returns object.
     */
    /* 87191
    public Object create(Object object)
    {
    Feature feature = (Feature) object;
    FeatureSecurityInfoResponseEvent responseEvent = new FeatureSecurityInfoResponseEvent();
    responseEvent.setFeatureId(feature.getOID().toString());
    responseEvent.setFeatureName(feature.getName());
    
    AttributeEvent aEvent = new AttributeEvent();
    aEvent.setAttributeValue(feature.getOID().toString());
    aEvent.setAttributeName("parentId");
    Iterator featureRoleIter = FeatureRolesRole.findAll(aEvent);
    
    ResponseContextFactory respFac = new ResponseContextFactory();
    ResponseCreator fCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.FEATURE_RESPONSE_LOCATOR,respFac);
    ResponseCreator rCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.ROLE_RESPONSE_LOCATOR,respFac);

    while(featureRoleIter.hasNext()){
    	FeatureRolesRole featureRolesRole = (FeatureRolesRole) featureRoleIter.next();
    	if(featureRolesRole != null){
    		Role role = featureRolesRole.getChild();
    		if(role != null){
    			RoleResponseEvent suResp = (RoleResponseEvent) rCreator.create(role);
    							
    			Collection userGroups = role.getUserGroups();
    			this.setUserGroups(suResp,userGroups,respFac);
    			
    			Collection users = role.getUsers();
    			this.setIndividualUsers(suResp,users,respFac);
    			responseEvent.addRole(suResp);
    		}
    	}
    }
    return responseEvent;
    }

    *//**
     * @param suResp
     * @param users
     * @param respFac
     */
    /*
    private void setIndividualUsers(RoleResponseEvent suResp, Collection users, ResponseContextFactory respFac) {
    ResponseCreator uCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.SECURITY_USER_RESPONSE_LOCATOR,respFac);
    Iterator userIterator = users.iterator();
    while(userIterator.hasNext()){
    	User user = (User) userIterator.next();
    	if(user != null){
    		SecurityUserResponseEvent uResp = (SecurityUserResponseEvent) uCreator.createThin(user);
    		suResp.addUser(uResp);
    	}
    }	}

    *//**
     * @param suResp
     * @param userGroups
     * @param respFac
     */
    /*
    private void setUserGroups(RoleResponseEvent suResp, Collection userGroups, ResponseContextFactory respFac) {
    Iterator userGroupIter = userGroups.iterator();
    ResponseCreator ugCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.USER_GROUP_RESPONSE_LOCATOR,respFac);
    ResponseCreator uCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.SECURITY_USER_RESPONSE_LOCATOR,respFac);
    while(userGroupIter.hasNext()){
    	UserGroup userGroup = (UserGroup) userGroupIter.next();
    	if(userGroup != null){
    		UserGroupResponseEvent ugResp = (UserGroupResponseEvent) ugCreator.create(userGroup);
    		Iterator userIterator = userGroup.getUsers().iterator();
    		while(userIterator.hasNext()){
    			User user = (User) userIterator.next();
    			if(user != null){
    				SecurityUserResponseEvent uResp = (SecurityUserResponseEvent) uCreator.createThin(user);
    				ugResp.addUser(uResp);
    			}
    		}
    		suResp.addUserGroup(ugResp);
    	}
    }
    }

    (non-Javadoc)
    * @see pd.security.SecurityResponseCreator#createThin(java.lang.Object)
    
    public Object createThin(Object object) {
    // TODO Auto-generated method stub
    return null;
    }

    (non-Javadoc)
    * @see pd.common.ResponseCreator#createThinest(java.lang.Object)
    
    public Object createThinest(Object object) {
    // TODO Auto-generated method stub
    return null;
    }*///87191
}
