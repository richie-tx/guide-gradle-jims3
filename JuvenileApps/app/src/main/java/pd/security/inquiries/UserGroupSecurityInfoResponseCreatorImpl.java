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

public class UserGroupSecurityInfoResponseCreatorImpl /*extends ResponseCommonUtil implements ResponseCreator*/
{

    /*	*//**
     * @param object
     * @return object
     */
    /* 87191
    public Object create(Object object)
    {
    UserGroup userGroup = (UserGroup) object;
    ResponseContextFactory respFac = new ResponseContextFactory();
    UserGroupSecurityInfoResponseEvent ugsRespEvent = new UserGroupSecurityInfoResponseEvent();
    
    // set UserGroup info + agency info
    this.setUserGroupCOnstraintInfo(ugsRespEvent, userGroup);
    
    ugsRespEvent.setName(userGroup.getName());
    ugsRespEvent.setDescription(userGroup.getDescription());
    
    Collection users = userGroup.getUsers();
    // set users in UserGroupSecurityInfoResponseEvent
    this.setUsers(ugsRespEvent,users,respFac);
    		
    Collection roles = userGroup.getRoles();
    // set roles in UserGroupSecurityInfoResponseEvent
    this.setRoles(ugsRespEvent,roles,respFac);
    return ugsRespEvent;
    }

    *//**
     * @param ugsRespEvent
     * @param userGroup
     */
    /*
    private void setUserGroupCOnstraintInfo(UserGroupSecurityInfoResponseEvent ugsRespEvent, UserGroup userGroup) {
    ugsRespEvent.setName(userGroup.getName());
    ugsRespEvent.setDescription(userGroup.getDescription());
    
    Iterator constraints = userGroup.getConstraints().iterator();
    StringBuffer agencyName = new StringBuffer();
    while (constraints.hasNext())
    {
    	Constraint constraint = (Constraint) constraints.next();
    	if (constraint != null)
    	{
    		Agency agency = (Agency) constraint.getConstrainerObject();
    		if (agency != null)
    		{
    			agencyName.append(agency.getAgencyName());
    			agencyName.append(" (");
    			agencyName.append(agency.getAgencyId());
    			agencyName.append(")");
    			if(constraints.hasNext()){
    				agencyName.append(", ");
    			}
    		}
    	}
    	ugsRespEvent.setAgencyName(agencyName.toString());
    }
    }

    *//**
     * @param ugsRespEvent
     * @param roles
     * @param respFac
     */
    /*
    private void setRoles(UserGroupSecurityInfoResponseEvent ugsRespEvent, Collection roles, ResponseContextFactory respFac) {
    ResponseCreator rCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.ROLE_RESPONSE_LOCATOR,respFac);
    ResponseCreator fCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.FEATURE_RESPONSE_LOCATOR,respFac);
    Iterator roleIter = roles.iterator();
    while(roleIter.hasNext()){
    	Role role = (Role) roleIter.next();
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
    		ugsRespEvent.addRole(rResp);
    	}
     }		
    }

    *//**
     * @param ugsRespEvent
     * @param users
     * @param respFac
     */
    /*
    private void setUsers(UserGroupSecurityInfoResponseEvent ugsRespEvent, Collection users, ResponseContextFactory respFac) {
    ResponseCreator uCreator = (ResponseCreator) getResponseInstance(ResponseLocatorConstants.SECURITY_USER_RESPONSE_LOCATOR,respFac);
    Iterator usersIter = users.iterator();
    while(usersIter.hasNext()){
    	User user = (User) usersIter.next();
    	if(user != null){
    		SecurityUserResponseEvent suResp = (SecurityUserResponseEvent) uCreator.createThin(user);
    		ugsRespEvent.addUser(suResp);
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
