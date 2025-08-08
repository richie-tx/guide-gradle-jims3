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
public class SecurityRoleInfoResponseCreatorImpl /*extends ResponseCommonUtil implements ResponseCreator*/
{
    /*
    *//**
     * @param object
     */
    /* 87191
    public Object create(Object object)
    {
    Role role = (Role) object;
    ResponseContextFactory respFac = new ResponseContextFactory();
    RoleSecurityInfoResponseEvent rsiRespEvent = new RoleSecurityInfoResponseEvent();
    
    // set Role info + agency info
    this.setRoleCOnstraintInfo(rsiRespEvent, role);
    
    Collection users = role.getUsers();
    // set individual users attached to the role
    this.setIndividualUsers(rsiRespEvent,users,respFac);
    		
    Collection features = role.getFeatures();
    // set features attached to the role 
    this.setFeatures(rsiRespEvent,features,respFac);
    
    Collection userGroups = role.getUserGroups();
    // set userGroups attached to the role
    this.setUserGroups(rsiRespEvent,userGroups,respFac);
    return rsiRespEvent;
    }
    
    *//**
     * @param rsiRespEvent
     * @param role
     */
    /*
    private void setRoleCOnstraintInfo(RoleSecurityInfoResponseEvent rsiRespEvent, Role role) {
    rsiRespEvent.setRoleName(role.getName());
    rsiRespEvent.setRoleDescription(role.getDescription());
    
    StringBuffer agencyNames = new StringBuffer();
    Iterator  agencyIter = role.getConstraintsByConstrainerType(Agency.class).iterator();
    while(agencyIter.hasNext()){
    	Constraint cons = (Constraint) agencyIter.next();
    	if(cons != null){
    		Agency agency = Agency.find(cons.getConstrainerId());
            if(agency != null){
            	agencyNames.append(agency.getAgencyName());
    		    agencyNames.append(" (");
    		    agencyNames.append(agency.getAgencyId());
    		    agencyNames.append(")");
    		    if(agencyIter.hasNext()){
    				agencyNames.append(",");
    			}
            }
    	}
    }
    rsiRespEvent.setAgencyName(agencyNames.toString());
    }

    *//**
     * @param rsiRespEvent
     * @param userGroups
     * @param respFac
     */
    /*
    private void setUserGroups(RoleSecurityInfoResponseEvent rsiRespEvent, Collection userGroups, ResponseContextFactory respFac) {
    ResponseCreator uCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.SECURITY_USER_RESPONSE_LOCATOR,respFac);
    ResponseCreator ugCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.USER_GROUP_RESPONSE_LOCATOR,respFac);
    Iterator userGroupsIter = userGroups.iterator();
    while(userGroupsIter.hasNext()){
    	UserGroup userGroup = (UserGroup) userGroupsIter.next();
    	if(userGroup != null){
    		UserGroupResponseEvent ugResp = (UserGroupResponseEvent) ugCreator.create(userGroup);
    		Iterator userGroupUsersIter = userGroup.getUsers().iterator();
    		while(userGroupUsersIter.hasNext()){
    			User user = (User) userGroupUsersIter.next();
    			if(user != null){
    				SecurityUserResponseEvent suResp = (SecurityUserResponseEvent) uCreator.createThin(user);
    				ugResp.addUser(suResp);
    			}
    		}
    		rsiRespEvent.addUserGroup(ugResp);
    	}
    }
    }

    *//**
     * @param rsiRespEvent
     * @param features
     * @param respFac
     */
    /*
    private void setFeatures(RoleSecurityInfoResponseEvent rsiRespEvent, Collection features, ResponseContextFactory respFac) {
    ResponseCreator fCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.FEATURE_RESPONSE_LOCATOR,respFac);
    Iterator featureIter = features.iterator();
    while(featureIter.hasNext()){
    	Feature feature = (Feature) featureIter.next();
    	if(feature != null){
    		FeaturesResponseEvent fResp = (FeaturesResponseEvent) fCreator.create(feature);
    		rsiRespEvent.addFeature(fResp);
    	}
    }
    }

    *//**
     * @param rsiRespEvent
     * @param users
     * @param respFac
     */
    /*
    private void setIndividualUsers(RoleSecurityInfoResponseEvent rsiRespEvent, Collection users, ResponseContextFactory respFac) {
    ResponseCreator uCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.SECURITY_USER_RESPONSE_LOCATOR,respFac);
    Iterator usersIter = users.iterator();
    while(usersIter.hasNext()){
    	User user = (User) usersIter.next();
    	if(user != null){
    		SecurityUserResponseEvent suResp = (SecurityUserResponseEvent) uCreator.createThin(user);
    		rsiRespEvent.addUser(suResp);
    	}
    }
    }

    (non-Javadoc)
    * @see pd.common.ResponseCreator#createThin(java.lang.Object)
    
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
