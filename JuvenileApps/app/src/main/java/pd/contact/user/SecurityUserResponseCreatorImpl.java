/*
 * Created on Aug 15, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.contact.user;

import messaging.contact.user.reply.SecurityUserResponseEvent;
import mojo.km.security.User;
import mojo.km.utilities.Name;
import pd.common.ResponseCommonUtil;
import pd.common.ResponseCreator;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SecurityUserResponseCreatorImpl extends ResponseCommonUtil implements ResponseCreator{
 
	/* (non-Javadoc)
	 * @see pd.common.ResponseCreator#createThin(java.lang.Object)
	 */
	public Object createThin(Object object) {
		SecurityUserResponseEvent ure = new SecurityUserResponseEvent();
		/*User user = (User) object; 87191
		if(user != null){
			Name name = new Name();
			name.setFirstName(user.getFirstName());
			name.setLastName(user.getLastName());
			name.setMiddleName(user.getMiddleName());
			ure.setFormattedName(name.getFormattedName());
			ure.setLogonId(user.getJIMSLogonId());
			ure.setDepartmentId(user.getDepartmentId());
		}
		return ure;*/
		return null;
	}

	/* (non-Javadoc)
	 * @see pd.common.ResponseCreator#create(java.lang.Object)
	 */
	public Object create(Object object) {
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
