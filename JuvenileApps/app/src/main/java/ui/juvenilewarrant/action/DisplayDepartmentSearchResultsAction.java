//Source file: C:\\views\\MJW\\app\\src\\ui\\juvenileWarrant\\action\\DisplayDepartmentSearchResultsAction.java

package ui.juvenilewarrant.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.GetDepartmentsAndAgencyEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.InfoMessageEvent;
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
import ui.juvenilewarrant.form.JuvenileWarrantForm;

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
   public ActionForward findDepartments(
	ActionMapping aMapping,
	ActionForm aForm,
	HttpServletRequest aRequest,
	HttpServletResponse aResponse) 
   {
   	JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;

	GetDepartmentsAndAgencyEvent getDeptsEvent =
		(GetDepartmentsAndAgencyEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETDEPARTMENTSANDAGENCY);
	getDeptsEvent.setDepartmentId(jwForm.getSearchDepartmentId());
	getDeptsEvent.setDepartmentName(jwForm.getSearchDepartmentName());
		
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	dispatch.postEvent(getDeptsEvent);
		
	CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
	InfoMessageEvent iMessage = (InfoMessageEvent) MessageUtil.filterComposite(compositeResponse,InfoMessageEvent.class);
	if (iMessage != null && iMessage.toString().indexOf("count exceeded") > 0 ){
		ActionErrors errors = new ActionErrors();
		ActionMessage error = new ActionMessage("error.max.limit.exceeded");
		errors.add(ActionErrors.GLOBAL_MESSAGE, error);
		saveErrors(aRequest, errors);
		return aMapping.findForward(UIConstants.FAILURE);
	}
	MessageUtil.processReturnException(compositeResponse);

	return handleResponse(aMapping, aForm, aRequest, aResponse,AgencyResponseEvent.class, compositeResponse);
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
   	JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
	jwForm.setSearchDepartmentName("");
	jwForm.setSearchDepartmentId("");
	jwForm.setDepartments(new ArrayList());
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
   	JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
	jwForm.setDepartments(data);
	jwForm.setDepartmentSize(data.size());
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
	   JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
	   jwForm.setDepartments(depts);
	   jwForm.setDepartmentSize(depts.size());

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
   	JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
   	jwForm.setDepartments(new ArrayList());
// 	InfoMessageEvent iMessage = (InfoMessageEvent) MessageUtil.filterComposite(compositeResponse,InfoMessageEvent.class);
//	if (iMessage != null ){
//		ActionErrors errors = new ActionErrors();
//		ActionMessage error = new ActionMessage("error.max.limit.exceeded");
//		errors.add(ActionErrors.GLOBAL_ERROR, error);
//		saveErrors(aRequest, errors);
//		return aMapping.findForward(UIConstants.FAILURE);
//	} else {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.department.found","Department not found."));			
		saveErrors(aRequest, errors);
//	}
	return aMapping.findForward(UIConstants.SUCCESS);
   }
   
   public ActionForward select(
	   ActionMapping aMapping,
	   ActionForm aForm,
	   HttpServletRequest aRequest,
	   HttpServletResponse aResponse)
   {
	   JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
	   StringTokenizer stok=new StringTokenizer(jwForm.getSelectedValue(),"+");
	   int counter=0;
	   String deptId="";
	   String deptName="";
	   while(stok.hasMoreTokens())
	   {
	   	  String str=stok.nextToken();
	   	  if(counter==0)
	   	  	deptId=str;
	   	  else if(counter==1)
	   	  	deptName=str;
	   	  counter++;
	   }
	   if(jwForm.getWarrantTypeUI().equals("warrantService"))
	   {
	   		jwForm.setOfficerAgencyId(deptId);
			jwForm.setOfficerAgencyName(deptName);	
			jwForm.setDepartments(null);
			return aMapping.findForward("selectServiceSuccess");			
	   } else if(jwForm.getWarrantTypeUI().equals("releaseToJP"))
	   		{
			jwForm.setTransferOfficerDepartmentId(deptId);
			jwForm.setTransferOfficerDepartmentName(deptName);			
			jwForm.setDepartments(null);
// this value is radomingly being blanked, required for correct jsp display			
	   		jwForm.setSearch("officerSearch");	
			return aMapping.findForward("selectTransferSuccess");	   	
	   		}	   
	   else
	   {
   		jwForm.setOfficerAgencyId(deptId);
		jwForm.setOfficerAgencyName(deptName);	
		jwForm.setDepartments(null);
		return aMapping.findForward("selectCreateSuccess" );
	   }
   }
   public ActionForward cancel(
	   ActionMapping aMapping,
	   ActionForm aForm,
	   HttpServletRequest aRequest,
	   HttpServletResponse aResponse)
   {
	   JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
	   jwForm.setSearchDepartmentId("");
	   jwForm.setSearchDepartmentName("");
	   jwForm.setDepartments(new ArrayList());	
	   String forward = "cancelCreate";
// warrantTypeUI attribute needs to be set for displayJOTSearch action to function correctly	   
	   if(jwForm.getWarrantTypeUI().equals("warrantService"))
	   {
	   		forward = "cancelService";			
	   } else if(jwForm.getWarrantTypeUI().equals("releaseToJP"))
	   		{
		   		forward = "cancelTransfer";	
	   		} else 
	   		{
	   			aRequest.setAttribute(UIConstants.WARRANT_TYPE_UI,jwForm.getWarrantTypeUI());
	   		}
	   return aMapping.findForward(forward);
   }
   
   public ActionForward back(
	   ActionMapping aMapping,
	   ActionForm aForm,
	   HttpServletRequest aRequest,
	   HttpServletResponse aResponse)
   {
	   JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
	   jwForm.setSearchDepartmentId("");
	   jwForm.setSearchDepartmentName("");
	   jwForm.setTransferOfficerDepartmentId("");
	   jwForm.setTransferOfficerDepartmentName("");			
	   jwForm.setDepartments(null);	   
	   String forward = "backCreate";
	   if(jwForm.getWarrantTypeUI().equals("warrantService"))
	   {
			forward = "backService";

	   } else if(jwForm.getWarrantTypeUI().equals("releaseToJP"))
	   		{
//	  this value is radomingly being blanked, required for correct jsp display	   	
	   		jwForm.setSearch("officerSearch");	   	
	   		forward = "backTransfer";	
	   		}   
	   return aMapping.findForward(forward);	   
   }
}
