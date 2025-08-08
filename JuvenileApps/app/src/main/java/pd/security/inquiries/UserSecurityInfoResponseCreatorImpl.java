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
public class UserSecurityInfoResponseCreatorImpl /*extends ResponseCommonUtil implements ResponseCreator*/
{

    /* (non-Javadoc) //87191
     * @see pd.common.ResponseCreator#create(java.lang.Object)
     
    public Object create(Object object) {
    	UserProfile userProfile = (UserProfile) object;
    	ResponseContextFactory respFac = new ResponseContextFactory();
    	UserSecurityInfoResponseEvent uRespEvent = new UserSecurityInfoResponseEvent();
    	if("S".equalsIgnoreCase(userProfile.getGenericUserTypeId())){
    		GetSPProfileEvent spe = new GetSPProfileEvent();
    		spe.setLogonId(userProfile.getLogonId());
    		Iterator spIter = SP_Profile.findAll(spe);
    		while(spIter.hasNext()){
    			SP_Profile sp =  (SP_Profile) spIter.next();
    			if(sp != null){
    				uRespEvent.setServiceProviderName(sp.getServiceProviderName());
    			}
    		}
    	
    //		GetSPProfileEvent reqEvent =
    //			(GetSPProfileEvent) EventFactory.getInstance(ServiceProviderControllerServiceNames.GETSPPROFILE);
    //		reqEvent.setEmployeeId(userAccountOID);
    //		Iterator profiles = SP_Profile.findAll(reqEvent);
    //
    //		while (profiles.hasNext()) {
    //			SP_Profile spProfile = (SP_Profile) profiles.next();
    //			if (spProfile != null) {
    //				 uRespEvent.setServiceProviderName(spProfile.getServiceProviderName());
    //				 
    //			}
    //		}
    	}
    	// set the userprofile info
    	this.setUserInfo(uRespEvent,userProfile);
    			
    	AttributeEvent aEvent = new AttributeEvent();
    	aEvent.setAttributeValue(userProfile.getLogonId());
    	aEvent.setAttributeName("parentId");
    	Iterator userUserGroupIter = UserUserGroupsUserGroup.findAll(aEvent);
    // set userGroups in UserSecurityInfoResponseEvent
    	this.setUserGroups(uRespEvent,userUserGroupIter,respFac);
    			
    	aEvent.setAttributeValue(userProfile.getLogonId());
    	aEvent.setAttributeName("parentId");
    	Iterator userRoleIter = UserRolesRole.findAll(aEvent);
    	
    // set roles in UserSecurityInfoResponseEvent
    	this.setUserRoles(uRespEvent,userRoleIter,respFac);
    	return uRespEvent;
    }

    *//**
     * @param respEvent
     * @param userProfile
     */
    /*
    private void setUserInfo(UserSecurityInfoResponseEvent uRespEvent, UserProfile userProfile) {
    Name name = new Name();
    name.setFirstName(userProfile.getFirstName());
    name.setLastName(userProfile.getLastName());
    name.setMiddleName(userProfile.getMiddleName());
    uRespEvent.setFormattedName(name.getFormattedName());
    uRespEvent.setLogonId(userProfile.getLogonId());
    uRespEvent.setAgencyId(userProfile.getAgencyId());
    uRespEvent.setDepartmentId(userProfile.getDepartmentId());
    uRespEvent.setDepartmentName(userProfile.getDepartmentName());
    
    JIMS2AccountView view = JIMS2AccountView.findByLogonId(userProfile.getLogonId());
    if(view != null){
    	uRespEvent.setJims2LogonId(view.getJIMS2LogonId());
    }
    }

    
    *//**
     * @param respEvent
     *            ,
     * @param userRoleIter
     * @param respFac
     */
    /*
    private void setUserRoles(UserSecurityInfoResponseEvent respEvent,Iterator userRoleIterator, ResponseContextFactory respFac) {
    Collection roles = new ArrayList();
    while(userRoleIterator.hasNext()){
    	UserRolesRole userRolesRole = (UserRolesRole) userRoleIterator.next();
    	if(userRolesRole != null){
    		Role role = Role.find(userRolesRole.getChildId());
    		if(role != null){
    			roles.add(role);
    		}
    	}
    }
    respEvent.setRoles(this.getRoles(roles.iterator(),respFac));
    }
    
    *//**
     * @param userRoleIter
     * @param respFac
     */
    /*
    private Collection getRoles(Iterator userRoleIter, ResponseContextFactory respFac) {
    ResponseCreator rCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.ROLE_RESPONSE_LOCATOR,respFac);
    ResponseCreator fCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.FEATURE_RESPONSE_LOCATOR,respFac);
    Collection roles = new ArrayList();
    while(userRoleIter.hasNext()){
    	Role role = (Role) userRoleIter.next();
    	if(role != null){
    		RoleResponseEvent rResp = (RoleResponseEvent) rCreator.create(role);
    		Iterator featureIter = role.getFeatures().iterator();
    		while(featureIter.hasNext()){
    			Feature feature = (Feature) featureIter.next();
    			if(feature != null){
    				FeaturesResponseEvent fResp = (FeaturesResponseEvent) fCreator.create(feature);
    				rResp.addFeature(fResp);
    			}
    		}
    		roles.add(rResp);
       }
     }
    return roles;
    }

    *//**
     * @param respEvent
     * @param userUserGroupIter
     * @param respFac
     */
    /*
    private void setUserGroups(UserSecurityInfoResponseEvent respEvent, Iterator userUserGroupIter, ResponseContextFactory respFac) {
    ResponseCreator ugCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.USER_GROUP_RESPONSE_LOCATOR,respFac);
    while(userUserGroupIter.hasNext()){
    	UserUserGroupsUserGroup userUserGroupsUserGroup = (UserUserGroupsUserGroup) userUserGroupIter.next();
    	if(userUserGroupsUserGroup != null){
    		UserGroup userGroup = UserGroup.find(userUserGroupsUserGroup.getChildId());
    		if(userGroup != null){
    			UserGroupResponseEvent ugResp = (UserGroupResponseEvent) ugCreator.create(userGroup);
    			ugResp.setRoles(this.getRoles(userGroup.getRoles().iterator(),respFac));
    			respEvent.addUserGroup(ugResp);
    		}
    	}
    }
    }

    (non-Javadoc)
    * @see pd.common.ResponseCreator#createThinest(java.lang.Object)
    
    public Object createThinest(Object object) {
    // TODO Auto-generated method stub
    return null;
    }

    (non-Javadoc)
    * @see pd.common.ResponseCreator#createThin(java.lang.Object)
    
    public Object createThin(Object object) {
    // TODO Auto-generated method stub
    return null;
    }*///87191
}
