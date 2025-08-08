//Source file: C:\\views\\Security\\app\\src\\ui\\security\\inquiries\\action\\DisplaySecurityInquiriesUserGroupSearchResultsAction.java

package ui.security.inquiries.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
public class DisplaySecurityInquiriesUserGroupSearchResultsAction extends Action
{
   
   /**
    * @roseuid 44E9D1DE0177
    */
   public DisplaySecurityInquiriesUserGroupSearchResultsAction() 
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
	   	//87191
	   	/*String msg = null;
	   	int size = 0;
	   	Collection emptyColl = new ArrayList();
	   	SecurityInquiriesForm securityInquiriesForm = (SecurityInquiriesForm) aForm; 
	   	securityInquiriesForm.setUserGroups(emptyColl);
	   	securityInquiriesForm.setSearchResultsCount("");
	   	
		GetUserGroupsEvent requestEvent = new GetUserGroupsEvent();
		requestEvent.setUserGroupName(securityInquiriesForm.getUserGroupName());
		requestEvent.setUserGroupDescription(securityInquiriesForm.getUserGroupDescription());
		requestEvent.setAgencyName(securityInquiriesForm.getUserGroupAgencyName());
		requestEvent.setAgencyId(securityInquiriesForm.getUserGroupAgencyId());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(requestEvent);
				
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
	
		Collection userGroups = MessageUtil.compositeToCollection(compositeResponse, UserGroupResponseEvent.class);
		if (userGroups != null && !userGroups.isEmpty()){
			size = userGroups.size();
			if (size == 1){
				UserGroupResponseEvent userGroup = (UserGroupResponseEvent) userGroups.toArray()[0];
				securityInquiriesForm.setUserGroupId(userGroup.getUserGroupId());
				forward = UIConstants.USERGROUP_SUCCESS;
		    }else{
		    	String searchResultSize = String.valueOf(size);
		    	securityInquiriesForm.setSearchResultsCount(searchResultSize);
		    	securityInquiriesForm.setUserGroups(SecurityUIHelper.sortUserGroups(userGroups));		
				forward = UIConstants.USERGROUP_LIST_SUCCESS;
		    }
		}
		else{
			ActionErrors errors = new ActionErrors();
			if (msg == null)
			{
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("error.no.user.group.found"));
			}
			saveErrors(aRequest, errors);
		}	*/ //87191
		return aMapping.findForward(forward);
	}
}
