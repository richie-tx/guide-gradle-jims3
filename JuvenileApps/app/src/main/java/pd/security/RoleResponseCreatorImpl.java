/*
 * Created on Aug 15, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.security;

import pd.common.ResponseCreator;
import messaging.security.reply.RoleResponseEvent;
import mojo.km.security.Role;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RoleResponseCreatorImpl /*implements ResponseCreator*/{
     
	/**
	 * @param object
	 */
/*	public Object create(Object object)
 	{
    	Role role = (Role) object;
		RoleResponseEvent responseEvent = new RoleResponseEvent();
 		responseEvent.setRoleId(role.getOID().toString());
 		responseEvent.setRoleName(role.getName());
 		responseEvent.setRoleDescription(role.getDescription());
 		responseEvent.setRoleType(role.getRoleType());
 		return responseEvent;
 	}*/

	/* (non-Javadoc)
	 * @see pd.security.SecurityResponseCreator#createThin(java.lang.Object)
	 */
	public Object createThin(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see pd.common.ResponseCreator#createThinest(java.lang.Object)
	 */
	public Object createThinest(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
