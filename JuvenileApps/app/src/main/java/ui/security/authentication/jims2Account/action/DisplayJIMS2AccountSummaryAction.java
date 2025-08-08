//Source file: C:\\views\\Security\\app\\src\\ui\\security\\authenication\\registergenericaccount\\DisplayJIMS2AccountSummaryAction.java

package ui.security.authentication.jims2Account.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.authentication.ValidateJIMS2AccountEvent;
import messaging.security.authentication.reply.LoginErrorResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.LogonControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.authentication.jims2Account.form.JIMS2AccountForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayJIMS2AccountSummaryAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 4562206803AE
    */
   public DisplayJIMS2AccountSummaryAction() 
   {
    
   }

   protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.next", "next");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.update", "update");
		buttonMap.put("button.inactivate", "inactivate");	
		buttonMap.put("button.mainPage", "mainPage");
		buttonMap.put("button.reset", "reset");		
		return buttonMap;
	}
     
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 45621366008F
    */
   public ActionForward next(
   		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse) 
   {
   		JIMS2AccountForm jaForm = (JIMS2AccountForm) aForm;
   		String forward = UIConstants.FAILURE;
   		String basicUser = jaForm.getBasicUser();
   		if (basicUser != null && basicUser.equalsIgnoreCase("Y")){
   			forward = UIConstants.UPDATE_FAILURE;
   		}
  		
   		String action = jaForm.getAction();
   		if(action != null){
   			if (action.equalsIgnoreCase(UIConstants.INACTIVATE) ){
   				forward = UIConstants.INACTIVATE_SUCCESS;
   			}	
   			if (action.equalsIgnoreCase(UIConstants.UPDATE) && jaForm.getNewJIMS2LogonId().equalsIgnoreCase("")){
   				forward = UIConstants.UPDATE_SUCCESS;
   			}
   			if ((action.equalsIgnoreCase(UIConstants.UPDATE) && !jaForm.getNewJIMS2LogonId().equalsIgnoreCase(""))
   					|| action.equalsIgnoreCase(UIConstants.CREATE)){
   				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
   				ValidateJIMS2AccountEvent getEvent =
   					(ValidateJIMS2AccountEvent) EventFactory.getInstance(LogonControllerServiceNames.VALIDATEJIMS2ACCOUNT);
   					//getEvent.setJIMS2LogonId(jaForm.getNewJIMS2LogonId());

   				//RAC did for Mo 2/5/07
   				if(action.equalsIgnoreCase(UIConstants.CREATE)){
   					getEvent.setJIMS2LogonId(jaForm.getJims2LogonId());
   					getEvent.setJimsLogonId(jaForm.getJimsLogonId());
   					getEvent.setJIMS2AccountTypeId(jaForm.getUserType());
   				}else{
   					getEvent.setJIMS2LogonId(jaForm.getNewJIMS2LogonId());
   				}

   					dispatch.postEvent(getEvent);

   				CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
   				LoginErrorResponseEvent resp = (LoginErrorResponseEvent) MessageUtil.filterComposite(compositeResponse, LoginErrorResponseEvent.class);
   				Map dataMap = MessageUtil.groupByTopic(compositeResponse);
   				MessageUtil.processReturnException(dataMap);
  		
   				if (resp != null && resp.getMessage() != null)
   				{
   					ActionErrors errors = new ActionErrors();
   					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",resp.getMessage()));
   					saveErrors(aRequest, errors);
   					forward = UIConstants.UPDATE_FAILURE;
   					if(action.startsWith(UIConstants.CREATE)){
   	   	   		    	forward = UIConstants.CREATE_FAILURE;
   					}
   				}
   				if (resp == null)
   				{ 
   					forward = UIConstants.UPDATE_SUCCESS;
   					if(action.startsWith(UIConstants.CREATE))
   					{
   						forward = UIConstants.CREATE_SUCCESS;
   					}	
   	   		    }
   			}
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
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JIMS2AccountForm jaForm = (JIMS2AccountForm) aForm;
		jaForm.clear();
		return aMapping.findForward(UIConstants.CANCEL);
	}   
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward update(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JIMS2AccountForm jaForm = (JIMS2AccountForm) aForm;
		jaForm.setAction(UIConstants.UPDATE);
		return aMapping.findForward(UIConstants.UPDATE_SELECT_SUCCESS);
	} 
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward inactivate(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JIMS2AccountForm jaForm = (JIMS2AccountForm) aForm;
		jaForm.setAction(UIConstants.INACTIVATE);
		return aMapping.findForward(UIConstants.INACTIVATE_SELECT_SUCCESS);
	} 	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward mainPage(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.MAIN_PAGE);
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward reset(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		JIMS2AccountForm jaForm = (JIMS2AccountForm) aForm;
		String forward = UIConstants.FAILURE;
		String action = jaForm.getAction();
		if (action != null)
		{
			if (action.equalsIgnoreCase(UIConstants.CREATE))
			{
				jaForm.setJims2LogonId("");
				jaForm.setReenterJIMS2LogonId("");
				jaForm.setPassword("");
				jaForm.setReenterPassword("");	
				jaForm.setPasswordQuestionId("");
				jaForm.setPasswordAnswer(""); 
				forward = UIConstants.CREATE_RESET;
			}	
			if (action.equalsIgnoreCase(UIConstants.UPDATE))
			{
				jaForm.setAction(UIConstants.UPDATE);
				forward = UIConstants.UPDATE_RESET;
			}	
		}	
		return aMapping.findForward(forward);
	}
}
