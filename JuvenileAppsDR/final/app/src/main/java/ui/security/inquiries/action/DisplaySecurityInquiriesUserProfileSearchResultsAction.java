//Source file: C:\\views\\Security\\app\\src\\ui\\security\\inquiries\\action\\DisplaySecurityInquiriesUserProfileSearchResultsAction.java

package ui.security.inquiries.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.security.reply.UserResponseforUserAdministrationEvent;
import messaging.user.GetUserProfilesByUserAttributeAndLogonIdEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;
import naming.UserControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.security.SecurityUIHelper;
import ui.security.inquiries.form.SecurityInquiriesForm;

public class DisplaySecurityInquiriesUserProfileSearchResultsAction extends Action
{
   
   /**
    * @roseuid 44E9D1DE0177
    */
   public DisplaySecurityInquiriesUserProfileSearchResultsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 44D2218701B1
    */
   public ActionForward execute(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
	   	String forward = UIConstants.FAILURE;
	   	String msg = null;
	   	int size = 0;
	   	Collection emptyColl = new ArrayList();
	   	SecurityInquiriesForm securityInquiriesForm = (SecurityInquiriesForm) aForm; 
	   	securityInquiriesForm.setAgencies(emptyColl);
	   	securityInquiriesForm.setSearchResultsCount("");
	   	
	   	GetUserProfilesByUserAttributeAndLogonIdEvent requestEvent = (GetUserProfilesByUserAttributeAndLogonIdEvent) EventFactory.getInstance(UserControllerServiceNames.GETUSERPROFILESBYUSERATTRIBUTEANDLOGONID);
	   	String searchLogonId = securityInquiriesForm.getSearchJIMSLogonId();
	   	if (searchLogonId == null || searchLogonId.equals("")){
	   		searchLogonId = securityInquiriesForm.getSearchJIMS2LogonId();
	   	}

	   	if (searchLogonId == null || searchLogonId.equals("")){
	   		requestEvent.setLastName(securityInquiriesForm.getLastNamePrompt());
			requestEvent.setFirstName(securityInquiriesForm.getFirstNamePrompt());	
			requestEvent.setAgencyName(securityInquiriesForm.getUserAgencyNamePrompt());		
			requestEvent.setAgencyId(securityInquiriesForm.getUserAgencyIdPrompt());
			requestEvent.setDeptName(securityInquiriesForm.getUserDeptNamePrompt());		
			requestEvent.setDeptId(securityInquiriesForm.getUserDeptIdPrompt());
		}else{
			requestEvent.setJimsLogonId(securityInquiriesForm.getSearchJIMSLogonId());
			requestEvent.setJims2LogonId(securityInquiriesForm.getSearchJIMS2LogonId());
		}
		securityInquiriesForm.setSearchJIMSLogonId("");
		securityInquiriesForm.setSearchJIMS2LogonId("");		
		securityInquiriesForm.setLastNamePrompt("");
		securityInquiriesForm.setFirstNamePrompt("");	
		securityInquiriesForm.setUserAgencyNamePrompt("");		
		securityInquiriesForm.setUserAgencyIdPrompt("");
		securityInquiriesForm.setUserDeptNamePrompt("");		
		securityInquiriesForm.setUserDeptIdPrompt("");

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);
				
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
	
		Collection userProfiles = MessageUtil.compositeToCollection(compositeResponse, UserResponseforUserAdministrationEvent.class);
		if (userProfiles != null && !userProfiles.isEmpty()){
			size = userProfiles.size();
			if (size == 1){
				UserResponseforUserAdministrationEvent userProfile = (UserResponseforUserAdministrationEvent) userProfiles.toArray()[0];
				securityInquiriesForm.setLogonId(userProfile.getLogonId());
				//securityInquiriesForm.setJims2LogonId(userProfile.getJims2LogonId());//79250
				forward = UIConstants.USERPROFILE_SUCCESS;
			}else{
		    	String searchResultSize = String.valueOf(size);
		    	securityInquiriesForm.setSearchResultsCount(searchResultSize);
				securityInquiriesForm.setUserProfiles(SecurityUIHelper.sortUserProfiles(userProfiles));
				forward = UIConstants.USERPROFILE_LIST_SUCCESS;
			}
		}
		else{
			ActionErrors errors = new ActionErrors();
			if (msg == null)
			{
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.userProfile.found"));
			}
			saveErrors(aRequest, errors);
		}	
		return aMapping.findForward(forward);
   }
}
