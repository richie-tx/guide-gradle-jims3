//Source file: C:\\views\\Security\\app\\src\\ui\\security\\authenication\\registergenericaccount\\SubmitJIMS2AccountCreateUpdateAction.java
// Not created from jims2 anymore U.s.79250
package ui.security.authentication.jims2Account.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.authentication.CreateJIMS2AccountEvent;
import messaging.authentication.UpdateJIMS2AccountEvent;
import messaging.registergenericaccount.UpdateJIMSGenericAccountEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.LogonControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.authentication.jims2Account.UIJIMS2AccountHelper;
import ui.security.authentication.jims2Account.form.JIMS2AccountForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SubmitJIMS2AccountCreateUpdateAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 4562207B0227
    */
   public SubmitJIMS2AccountCreateUpdateAction() 
   {
    
   }
   
   protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.inactivate", "inactivate");
		buttonMap.put("button.backToSearch", "backToSearch");
		buttonMap.put("button.logout", "logout");		
		return buttonMap;
	}
   
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BD0090
	 */
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JIMS2AccountForm jaForm = (JIMS2AccountForm) aForm;
  		String forward = UIConstants.FAILURE;
   		String basicUser = jaForm.getBasicUser().toUpperCase();
   		if (basicUser.equalsIgnoreCase("Y")){
   			forward = UIConstants.BASIC_USER_FAILURE;
   		}  		
   		String action = jaForm.getAction();
   		jaForm.setPageType(UIConstants.CONFIRM);
   		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
   		  			
		if(UIConstants.CREATE.equalsIgnoreCase(action)){
   	   		CreateJIMS2AccountEvent cRequest =(CreateJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.CREATEJIMS2ACCOUNT);
   	   		cRequest =UIJIMS2AccountHelper.prepareCreateRequestEvent(cRequest,jaForm);
   	   		dispatch.postEvent(cRequest);
   	   		forward = UIConstants.CREATE_SUCCESS;
	    }else if(UIConstants.INACTIVATE.equalsIgnoreCase(action) || UIConstants.UPDATE.equalsIgnoreCase(action)){
   	   		UpdateJIMS2AccountEvent uRequest =(UpdateJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.UPDATEJIMS2ACCOUNT);
   	   		if(UIConstants.INACTIVATE.equalsIgnoreCase(action)){
   	   			uRequest.setJims2AccountId(jaForm.getJims2AccountId());
				uRequest.setStatusId(UIConstants.INACTIVE_STATUS_ID);
				forward = UIConstants.INACTIVATE_SUCCESS;
   	   		}else{
	   	   		uRequest.setJims2AccountId(jaForm.getJims2AccountId());
				uRequest.setJims2LogonId(jaForm.getNewJIMS2LogonId());
				uRequest.setJimsLogonId(jaForm.getJimsLogonId());
				uRequest.setAction(UIConstants.EDIT);
				uRequest.setUserTypeId(jaForm.getUserType());
				if(!"N".equalsIgnoreCase(jaForm.getUserType())){
					uRequest.setFirstName(jaForm.getFirstName());
					uRequest.setLastName(jaForm.getLastName());
					uRequest.setMiddleName(jaForm.getMiddleName());
					uRequest.setPassword(jaForm.getNewPassword());
					uRequest.setPasswordQuestionId(jaForm.getPasswordQuestionId());
					uRequest.setPasswordAnswer(jaForm.getPasswordAnswer());
				}
				String newJIMS2LogonId = jaForm.getNewJIMS2LogonId();
				jaForm.setCurrentUserLogonIdChanged("N");
				if(newJIMS2LogonId != null && !newJIMS2LogonId.equals("")){
					if (jaForm.getJims2LogonId().equalsIgnoreCase(jaForm.getCurrentUserLogonId())){
						jaForm.setCurrentUserLogonIdChanged("Y");
					}
				}
				forward = UIConstants.UPDATE_SUCCESS;
/** Basic users and users changing their own userId should be automatically logged out after alert */	
		   		if (basicUser.equalsIgnoreCase("Y") || jaForm.getCurrentUserLogonIdChanged().equalsIgnoreCase("Y")){
		   			forward = UIConstants.BASIC_USER_UPDATE_SUCCESS;
		   		}  				
   	   		}
   	   	    dispatch.postEvent(uRequest);
	    }	
		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap); 
   		return aMapping.findForward(forward);
	}
	
	/**
	 * @param request
	 * @param logonId
	 * @param password
	 * @param statusId
	 * @param genericAccountId
	 */
	private void setRequestevent(UpdateJIMSGenericAccountEvent request, String logonId, String password, String statusId, String genericAccountId) {
		request.setLogonId(logonId);
		request.setPassword(password);
		request.setStatusId(statusId);
		request.setGenericAccountId(genericAccountId);
	} 

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BD0090
	 */
	public ActionForward inactivate(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		this.finish(aMapping,aForm,aRequest,aResponse);
		return aMapping.findForward(UIConstants.INACTIVATE_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BD0090
	 */
	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JIMS2AccountForm jaForm = (JIMS2AccountForm) aForm;
		String action = jaForm.getAction();
		if(action != null && action.equalsIgnoreCase(UIConstants.UPDATE_CONFIRM)){
			jaForm.setAction(UIConstants.UPDATE_SUMMARY);
		}else if(action != null && action.equalsIgnoreCase(UIConstants.INACTIVATE_CONFIRM)){
			jaForm.setAction(UIConstants.INACTIVATE_SUMMARY);
		}
		else if(action != null && action.equalsIgnoreCase(UIConstants.CREATE_CONFIRM)){
			jaForm.setAction(UIConstants.CREATE_SUMMARY);
		}
		return aMapping.findForward(UIConstants.BACK);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JIMS2AccountForm jaForm = (JIMS2AccountForm) aForm;
		String forward = UIConstants.CANCEL;
		if(jaForm.getBasicUser().equalsIgnoreCase("N")){
			jaForm.clear();
		}else {
			jaForm.setNewJIMS2LogonId("");
			jaForm.setReenterNewJIMS2LogonId("");
			jaForm.setNewPassword("");
			jaForm.setReenterNewPassword("");
			forward = UIConstants.BASIC_USER_CANCEL;
		}
		return aMapping.findForward(forward);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward backToSearch(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JIMS2AccountForm jaForm = (JIMS2AccountForm) aForm;
		jaForm.clear();
		return aMapping.findForward(UIConstants.RETURN_SUCCESS);
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward logout(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.LOGOUT_SUCCESS);
	}	
}
