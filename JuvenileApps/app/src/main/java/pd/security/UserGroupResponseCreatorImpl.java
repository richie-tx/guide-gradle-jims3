/*
 * Created on Aug 15, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.security;

import java.util.Collection;
import java.util.Iterator;

import messaging.security.reply.UserGroupResponseEvent;
import mojo.km.persistence.Reference;
import mojo.km.security.Constraint;
import mojo.km.security.UserGroup;
import naming.PDCodeTableConstants;
import naming.PDSecurityConstants;
import pd.codetable.Code;
import pd.common.ResponseCreator;
import pd.contact.agency.Agency;
import pd.contact.user.UserProfile;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class UserGroupResponseCreatorImpl {/*implements ResponseCreator {*/
     
	/**
	 * @param object
	 * @return object
	 *//* //87191
	public Object create(Object object)
	{
		UserGroup userGroup = (UserGroup) object;
	    UserGroupResponseEvent ugEvent = (UserGroupResponseEvent) this.createThin(userGroup);
		Iterator constraints = userGroup.getConstraints().iterator();
		while (constraints.hasNext())
		{
			Constraint constraint = (Constraint) constraints.next();
			if (constraint != null)
			{
				Agency agency = (Agency) constraint.getConstrainerObject();
				if (agency != null)
				{
					ugEvent.setAgencyId(agency.getAgencyId());
					ugEvent.setAgencyName(agency.getAgencyName());
				}
			}
		}
		ugEvent.setCreatorId(userGroup.getCreateUserID());
		if ((userGroup.getCreateUserID() != null) && (!(userGroup.getCreateUserID().equals(""))))
		{
			UserProfile user = UserProfile.find(userGroup.getCreateUserID());
			if(user != null){
				ugEvent.setCreatorLastName(user.getLastName());
				ugEvent.setCreatorFirstName(user.getFirstName());
				ugEvent.setCreatorMiddleName(user.getMiddleName());
			}
		}
		ugEvent.setCategory(userGroup.getCategory());
		ugEvent.setStatusId(userGroup.getStatusId());
		if ((userGroup.getStatusId() != null) && (!(userGroup.getStatusId().equals(""))))
		{
			String statusId = userGroup.getStatusId();
			Code statusCode =
				(Code) new Reference(statusId, Code.class, PDCodeTableConstants.USER_GROUP_STATUS).getObject();
			if (statusCode != null)
			{
				ugEvent.setStatus(statusCode.getDescription());
			}
		}
		Collection roles = userGroup.getRoles();
		if(roles != null && !roles.isEmpty()){
			ugEvent.setRoleExist(true);
		}
		return ugEvent;
	}
	
	*//**
	 * @param object
	 * @return object
	 *//*
	public Object createThin(Object object)
	{
	    UserGroup userGroup = (UserGroup) object;
	    UserGroupResponseEvent ugEvent = new UserGroupResponseEvent();
		ugEvent.setDescription(userGroup.getDescription());
		ugEvent.setName(userGroup.getName());
		ugEvent.setUserGroupId(userGroup.getOID().toString());
		ugEvent.setUserGroupType(userGroup.getGroupType());
		ugEvent.setTopic(PDSecurityConstants.USER_GROUP_EVENT_TOPIC);
		return ugEvent;		
	}

	 (non-Javadoc)
	 * @see pd.common.ResponseCreator#createThinest(java.lang.Object)
	 
	public Object createThinest(Object object) {
		// TODO Auto-generated method stub
		return null;
	}*/ //87191
}
