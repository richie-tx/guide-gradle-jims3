//Source file: C:\\views\\Security\\app\\src\\ui\\security\\inquiries\\action\\DisplaySecurityInquiriesSubsystemDetailsAction.java

package ui.security.inquiries.action;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.inquiries.GetFeatureSecurityInfoEvent;
import messaging.security.GetFeaturesEvent;
import messaging.security.inquiries.reply.FeatureSecurityInfoResponseEvent;
import messaging.security.reply.FeaturesResponseEvent;
import messaging.security.reply.RoleResponseEvent;
import messaging.security.reply.UserGroupResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.InquiriesAdminControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.security.SecurityUIHelper;
import ui.security.inquiries.form.SecurityInquiriesForm;

public class DisplaySecurityInquiriesSubsystemDetailsAction extends Action
{
   
   /**
    * @roseuid 44E9D1DE00CB
    */
   public DisplaySecurityInquiriesSubsystemDetailsAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 44D221AC02F9
    */
   public ActionForward execute(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
		String forward = UIConstants.FAILURE;
		SecurityInquiriesForm securityInquiriesForm = (SecurityInquiriesForm) aForm; 
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetFeatureSecurityInfoEvent featureEvent =
			(GetFeatureSecurityInfoEvent) EventFactory.getInstance(InquiriesAdminControllerServiceNames.GETFEATURESECURITYINFO);
		featureEvent.setFeatureId(securityInquiriesForm.getFeatureId());
		dispatch.postEvent(featureEvent);
		CompositeResponse replyEvent = (CompositeResponse) dispatch.getReply();
		
		FeatureSecurityInfoResponseEvent respEvent = (FeatureSecurityInfoResponseEvent) MessageUtil.filterComposite(replyEvent, FeatureSecurityInfoResponseEvent.class);
		if (respEvent != null)
		{
			forward = UIConstants.SUCCESS;
			securityInquiriesForm.setFeatureName(respEvent.getFeatureName());
			securityInquiriesForm.setFeatureCategoryName("");
			String featureId = respEvent.getFeatureId();
			if (featureId != null){
				GetFeaturesEvent requestEvent = new GetFeaturesEvent();
				requestEvent.setFeatureId(featureId);
				IDispatch dispatch2 = EventManager.getSharedInstance(EventManager.REQUEST);
				dispatch.postEvent(requestEvent);
  			
				CompositeResponse compositeResponse = (CompositeResponse) dispatch2.getReply();
				Map dataMap = MessageUtil.groupByTopic(compositeResponse);
				MessageUtil.processReturnException(dataMap);

				Collection features = MessageUtil.compositeToCollection(compositeResponse, FeaturesResponseEvent.class);
/** save featureCategoryName for display  */
				if (features != null && !(features.isEmpty())){
					Iterator iter = features.iterator();
					String parentId = null;
					while (iter.hasNext())
					{
						FeaturesResponseEvent responseEvent = (FeaturesResponseEvent) iter.next();
						if (responseEvent.getFeatureId().equals(securityInquiriesForm.getFeatureId())){
							parentId = responseEvent.getParentId(); 
							break;
						}	
					}
					
					iter = features.iterator();
					while (iter.hasNext())
					{
						FeaturesResponseEvent responseEvent = (FeaturesResponseEvent) iter.next();
						if (responseEvent.getFeatureId().equals(parentId)){
							securityInquiriesForm.setFeatureCategoryName(responseEvent.getDescription());
							break;
						}	
					}
				} 
			}
		}

		securityInquiriesForm.setRoles(SecurityUIHelper.sortRoleNames(respEvent.getRoles()));
		Collection roles = securityInquiriesForm.getRoles();
		if (roles != null && !(roles.isEmpty())){
			Iterator iterRoles = roles.iterator();
			while (iterRoles.hasNext()){
				RoleResponseEvent roleResp = (RoleResponseEvent) iterRoles.next();
				Collection indUsers = roleResp.getUsers();	
				if (indUsers != null){
					Collections.sort((List) indUsers);
				}	
				Collection userGroups = roleResp.getUserGroups();
				if (userGroups != null){
					Collections.sort((List) userGroups);
					Iterator iterUserGroups = userGroups.iterator();
					while (iterUserGroups.hasNext()){
						UserGroupResponseEvent ugResp = (UserGroupResponseEvent) iterUserGroups.next();	
						Collection users = ugResp.getUsers();
						if (users != null){
							Collections.sort((List) users);
						}
					}
				}
				
			}
		}
		if (respEvent == null){
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.features.found"));
			saveErrors(aRequest, errors);
		}
	  	return aMapping.findForward(forward);
   }
}
