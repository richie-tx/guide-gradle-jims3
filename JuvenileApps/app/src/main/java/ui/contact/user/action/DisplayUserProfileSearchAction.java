//Source file: C:\\views\\MSA\\app\\src\\ui\\contact\\user\\action\\DisplayUserProfileSearchAction.java

package ui.contact.user.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.agency.GetAgencyEvent;
import messaging.agency.GetDepartmentEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.contact.agency.reply.DepartmentResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.CodeHelper;
import ui.contact.user.form.UserProfileForm;
import ui.contact.user.form.UserProfileSearchForm;
import ui.security.SecurityUIHelper;


public class DisplayUserProfileSearchAction extends LookupDispatchAction
{
	
   
   /**
    * @roseuid 43F4FC40027F
    */
   public DisplayUserProfileSearchAction() 
   {
    
   }
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 43F4EE300357
    */
   public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   		UserProfileSearchForm searchForm = (UserProfileSearchForm)aForm;   		
		searchForm.setUserTypes(CodeHelper.getCodes(PDCodeTableConstants.USER_TYPE));
		Collection coll = CodeHelper.getCodes(PDCodeTableConstants.AGENCY_STATUS);				
		searchForm.setUserStatuses(CodeHelper.getCodes(PDCodeTableConstants.AGENCY_STATUS));	
		searchForm.setGenericUserTypes(CodeHelper.getActiveCodesByStatus(PDCodeTableConstants.GENERIC_USER_TYPE, true)); //U.S #79250
		searchForm.clear();
		searchForm.setDisplayType(UIConstants.BASIC);
		UserProfileForm userProfileForm = getUserProfileForm(aRequest);
		if(userProfileForm != null)
		{
			userProfileForm.clear();
			userProfileForm.clearUserProfiles();
			userProfileForm.setNumUsers("zero");
		}
		searchForm.setUserIsMA("Y");
		searchForm.setUserIsLA("N");		
		if (!SecurityUIHelper.isUserMA()){
			searchForm.setUserIsMA("N");
			searchForm.setAgencyId(SecurityUIHelper.getUserAgencyId());	
// Get users agency name for display			
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			GetAgencyEvent event = (GetAgencyEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETAGENCY);
			event.setAgencyId(searchForm.getAgencyId());
			dispatch.postEvent(event);

			CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);				

			AgencyResponseEvent agency = (AgencyResponseEvent) MessageUtil.filterComposite(compositeResponse, AgencyResponseEvent.class);
			if (agency != null)
			{
				searchForm.setAgencyName(agency.getAgencyName());
			}
		
			if (SecurityUIHelper.getUserTypeId().equalsIgnoreCase("LA")){
				searchForm.setUserIsLA("Y");
				searchForm.setDepartmentId(SecurityUIHelper.getUserDepartmentId());
// Get users department name for display
				GetDepartmentEvent deptEvent = (GetDepartmentEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENT);
				deptEvent.setDepartmentId(searchForm.getDepartmentId());
				dispatch.postEvent(deptEvent);

				CompositeResponse deptCompositeResponse = (CompositeResponse) dispatch.getReply();
				Map dataMap2 = MessageUtil.groupByTopic(deptCompositeResponse);
				MessageUtil.processReturnException(dataMap);				

				DepartmentResponseEvent deptName = (DepartmentResponseEvent) MessageUtil.filterComposite(deptCompositeResponse, DepartmentResponseEvent.class);
				if (deptName != null)
				{
					searchForm.setDepartmentName(deptName.getDepartmentName());
				}
			}			
		}
		return aMapping.findForward(UIConstants.SUCCESS);
   }
   
   /* (non-Javadoc)
	* @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	*/
   protected Map getKeyMethodMap()
   {
	   Map buttonMap = new HashMap();
	   buttonMap.put("button.back", "back");
	   return buttonMap;
   }

   /**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	*/
   public ActionForward back(
	   ActionMapping aMapping,
	   ActionForm aForm,
	   HttpServletRequest aRequest,
	   HttpServletResponse aResponse)
   {
	   return aMapping.findForward(UIConstants.BACK);
   }
   /**
	* put the user profile form in session
	* @return HttpServletRequest
	*/
   private UserProfileForm getUserProfileForm(HttpServletRequest aRequest)
   {

	   UserProfileForm userProfileForm = null;
	   HttpSession session = aRequest.getSession();
	   Object obj = session.getAttribute(UIConstants.USERPROFILE_FORM);
	   if (obj != null || obj instanceof UserProfileForm)
	   {
		   userProfileForm = (UserProfileForm) obj;
	   }
	   else
	   {
		   userProfileForm = new UserProfileForm();
		   session.setAttribute(UIConstants.USERPROFILE_FORM, userProfileForm);
	   }
	   return userProfileForm;
   }
  
}