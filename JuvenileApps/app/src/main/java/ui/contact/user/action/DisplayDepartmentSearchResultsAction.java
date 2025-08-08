//Source file: C:\\views\\MSA\\app\\src\\ui\\contact\\user\\action\\DisplayDepartmentSearchResultsAction.java

package ui.contact.user.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.GetDepartmentsAndAgencyEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import messaging.info.reply.CountInfoMessage;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.AbstractLookupResultsTemplateAction;
import ui.contact.user.form.UserProfileForm;

public class DisplayDepartmentSearchResultsAction extends AbstractLookupResultsTemplateAction
{
   //private UserProfileForm userProfileForm = null;
   
   /**
    * @roseuid 43F4FC3001D2
    */
   public DisplayDepartmentSearchResultsAction() 
   {
    
   }
   /* (non-Javadoc)
	* @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	*/
  protected Map getKeyMethodMap()
  {
	   Map buttonMap = new HashMap();
	   buttonMap.put("button.refresh", "refresh");
	   buttonMap.put("button.findDepartments", "findDepartments");
	   buttonMap.put("button.select", "select");
	   buttonMap.put("button.back", "back");
	   buttonMap.put("button.cancel", "cancel");
	   return buttonMap;
  }
  
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 43F4EE2F007A
    */
   public ActionForward findDepartments(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   	UserProfileForm userProfileForm = (UserProfileForm) aForm;

	GetDepartmentsAndAgencyEvent getDeptsEvent =
		(GetDepartmentsAndAgencyEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENTSANDAGENCY);
	getDeptsEvent.setDepartmentId(userProfileForm.getSearchDepartmentId());
	getDeptsEvent.setDepartmentName(userProfileForm.getSearchDepartmentName());
		
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(getDeptsEvent);
		
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	MessageUtil.processReturnException(compositeResponse);
	// check for max. limit exceeded 
   	CountInfoMessage infoEvent = new CountInfoMessage();
   	CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,CountInfoMessage.class);
   	if (iMessage != null ){
   		userProfileForm.setDepartments(new ArrayList());   		
   		ActionErrors errors = new ActionErrors();
   		ActionMessage error = new ActionMessage("error.max.limit.exceeded");
   		errors.add(ActionErrors.GLOBAL_MESSAGE, error);
   		saveErrors(aRequest, errors);
   		return aMapping.findForward(UIConstants.SEARCH_FAILURE);
   	} else {
   		return handleResponse(aMapping, aForm, aRequest, aResponse,AgencyResponseEvent.class, compositeResponse);
   		}
   }

   /**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	*/
   public ActionForward refresh(
	   ActionMapping aMapping,
	   ActionForm aForm,
	   HttpServletRequest aRequest,
	   HttpServletResponse aResponse)
   {
   	UserProfileForm userProfileForm = (UserProfileForm) aForm;
	userProfileForm.setSearchDepartmentName("");
	userProfileForm.setSearchDepartmentId("");
	userProfileForm.setDepartments(new ArrayList());
	return aMapping.findForward("reset");
   }
	
   /* (non-Javadoc)
	* @see ui.action.IErrorResultsActionHandler#processBusinessExceptions(javax.servlet.http.HttpServletRequest, mojo.km.messaging.Composite.CompositeResponse, org.apache.struts.action.ActionErrors)
	*/
   public void processBusinessExceptions(HttpServletRequest aRequest, CompositeResponse response, ActionErrors errors)
   {
	   // TODO: Anything to do here ??

   }

   /* (non-Javadoc)
	* @see ui.action.IResultsActionTemplate#handleMultipleResults(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, mojo.km.messaging.Composite.CompositeResponse, java.util.Collection)
	*/
   public ActionForward handleMultipleResults(
	   ActionMapping aMapping,
	   ActionForm aForm,
	   HttpServletRequest aRequest,
	   HttpServletResponse aResponse,
	   CompositeResponse event,
	   Collection data)
   {
   	UserProfileForm userProfileForm = (UserProfileForm) aForm;
		userProfileForm.setDepartments(data);
		userProfileForm.setDepartmentsSize(data.size());
	   return aMapping.findForward(UIConstants.SUCCESS);
   }

   /* (non-Javadoc)
	* @see ui.action.IResultsActionTemplate#handleSingleResult(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, mojo.km.messaging.Composite.CompositeResponse, mojo.km.messaging.IEvent)
	*/
   public ActionForward handleSingleResult(
	   ActionMapping aMapping,
	   ActionForm aForm,
	   HttpServletRequest aRequest,
	   HttpServletResponse aResponse,
	   CompositeResponse event,
	   IEvent data)
   {
	   Collection depts = new ArrayList();
	   depts.add(data);
	   UserProfileForm userProfileForm = (UserProfileForm) aForm;
	   userProfileForm.setDepartments(depts);
	   userProfileForm.setDepartmentsSize(depts.size());

	   return aMapping.findForward(UIConstants.SUCCESS);
   }

   /* (non-Javadoc)
	* @see ui.action.IResultsActionTemplate#handleZeroResults(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, mojo.km.messaging.Composite.CompositeResponse)
	*/
   public ActionForward handleZeroResults(
	   ActionMapping aMapping,
	   ActionForm aForm,
	   HttpServletRequest aRequest,
	   HttpServletResponse aResponse,
	   CompositeResponse event)
   {
   	UserProfileForm userProfileForm = (UserProfileForm) aForm;
   	userProfileForm.setDepartments(new ArrayList());
	ActionErrors errors = new ActionErrors();
	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.department.found","Department not found."));			
	saveErrors(aRequest, errors);

	return aMapping.findForward(UIConstants.SUCCESS);
   }
   
   public ActionForward select(
	   ActionMapping aMapping,
	   ActionForm aForm,
	   HttpServletRequest aRequest,
	   HttpServletResponse aResponse)
   {
	   UserProfileForm userForm = (UserProfileForm) aForm;
	   StringTokenizer stok=new StringTokenizer(userForm.getSelectedValue(),"+");
	   int counter=0;
	   String deptId="";
	   String deptName="";
	   String orgCode="";
	   while(stok.hasMoreTokens())
	   {
	   	  String str=stok.nextToken();
	   	  if(counter==0)
	   	  	deptId=str;
	   	  else if(counter==1)
	   	  	deptName=str;
	   	  else
	   	  	orgCode=str;
	   	  counter++;
	   	  		
	   }
	   if(userForm.getAction().equals("create") || userForm.getAction().equals("update"))
	   {
	   		userForm.setDepartmentId(deptId);
			userForm.setDepartmentName(deptName);	
			userForm.setDepartments(null);
			userForm.setOrgCode(orgCode);
	   	   return aMapping.findForward("selectCreateUpdateDepartmentSuccess" );
	   }
	   else
	   {
			userForm.setNewDepartmentId(deptId);
			userForm.setNewDepartmentName(deptName);
			userForm.setDepartments(null);
			userForm.setOrgCode(orgCode);
		   return aMapping.findForward("selectTransferDepartmentSuccess" );
	   }
   }
   public ActionForward cancel(
	   ActionMapping aMapping,
	   ActionForm aForm,
	   HttpServletRequest aRequest,
	   HttpServletResponse aResponse)
   {
	   UserProfileForm userProfileForm = (UserProfileForm) aForm;
	   userProfileForm.clearDepartmentDetails();
	   return aMapping.findForward(UIConstants.CANCEL);
   }
   
   public ActionForward back(
	   ActionMapping aMapping,
	   ActionForm aForm,
	   HttpServletRequest aRequest,
	   HttpServletResponse aResponse)
   {
	   UserProfileForm userProfileForm = (UserProfileForm) aForm;
	   userProfileForm.clearDepartmentDetails();
	   return aMapping.findForward(UIConstants.BACK);
   }

}
