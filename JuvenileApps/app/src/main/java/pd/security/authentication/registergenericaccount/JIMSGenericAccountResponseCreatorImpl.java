/*
 * Created on Nov 21, 2006
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package pd.security.authentication.registergenericaccount;

import messaging.authentication.registergenericaccount.reply.JIMSGenericAccountResponseEvent;
import naming.PDSecurityConstants;
import pd.codetable.Code;
import pd.common.ResponseCommonUtil;
import pd.common.ResponseContextFactory;
import pd.common.ResponseCreator;
import pd.contact.user.UserProfile;

/**
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class JIMSGenericAccountResponseCreatorImpl extends ResponseCommonUtil implements ResponseCreator{
     
	/**
	 * Creates Jims generic Account response event from entity.
	 * @param object
	 * @return object JIMSGenericAccountResponseEvent
	 */
	public Object create(Object object){
		JIMSGenericAccountAccttypeView view = (JIMSGenericAccountAccttypeView) object;
		ResponseContextFactory respFac = new ResponseContextFactory();
		
		JIMSGenericAccountResponseEvent resp = new JIMSGenericAccountResponseEvent();
     	resp.setGenericAccountId(view.getJimsGenericId());
     	resp.setLogonId(view.getLogonId());
     	resp.setPassword(view.getPassword());
     	
     	// set the status and statusId
     	String statusId = view.getStatusId();
     	resp.setStatusId(statusId);
     	if(statusId != null && !statusId.equals("")){
     		Code status = view.getStatus();
     		if(status != null){
     			resp.setStatusDesc(status.getDescription());
     		}
     		String jimsAccountTypeId = view.getJimsAccountTypeId();
     		if(statusId.equals(PDSecurityConstants.ACTIVE) && (jimsAccountTypeId == null || jimsAccountTypeId.equals(""))){
     			resp.setCanInactivate("Y");
     		}
     	}
     	
     	// set the department Id
     	UserProfile userProfile = view.getUserProfile();
     	if(userProfile != null){
     		resp.setDepartmentId(userProfile.getDepartmentId());
     	}
 		return resp;
	}

	/* (non-Javadoc)
	 * @see pd.common.ResponseCreator#createThin(java.lang.Object)
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

