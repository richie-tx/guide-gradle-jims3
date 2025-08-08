//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\security\\registergenericaccount\\SubmitGenericLogonIDCreateUpdateAction.java

package ui.security.authentication.registergenericaccount.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.registergenericaccount.UpdateJIMSGenericAccountEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.RegisterGenericControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.authentication.registergenericaccount.form.GenericLogonIdForm;

/*
 * 
 * @author mchowdhury
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SubmitGenericLogonIDCreateUpdateAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 4562207B0227
    */
   public SubmitGenericLogonIDCreateUpdateAction() 
   {
    
   }
   
   protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.back", "addUser");
		buttonMap.put("button.inactivate", "inactivate");
		buttonMap.put("button.returnToSearch", "returnToSearch");
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
		GenericLogonIdForm genericLogonIdForm = (GenericLogonIdForm) aForm;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		UpdateJIMSGenericAccountEvent request =
			(UpdateJIMSGenericAccountEvent) EventFactory.getInstance(RegisterGenericControllerServiceNames.UPDATEJIMSGENERICACCOUNT);
		
		String action = genericLogonIdForm.getAction();
		if(action.startsWith(UIConstants.UPDATE)){
			this.setRequestevent(request,genericLogonIdForm.getLogonId(),genericLogonIdForm.getNewPassword(),genericLogonIdForm.getStatusId(), genericLogonIdForm.getGenericLogonId());
			genericLogonIdForm.setAction(UIConstants.UPDATE_CONFIRM);
		}else if(action.startsWith(UIConstants.INACTIVATE)){
			this.setRequestevent(request,genericLogonIdForm.getLogonId(),genericLogonIdForm.getCurrentPassword(), "I", genericLogonIdForm.getGenericLogonId());
			genericLogonIdForm.setAction(UIConstants.INACTIVATE_CONFIRM);
		}else{
			this.setRequestevent(request,genericLogonIdForm.getLogonId(),genericLogonIdForm.getNewPassword(),"A", "");
			genericLogonIdForm.setAction("createConfirm");
		}
		dispatch.postEvent(request);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		return aMapping.findForward(UIConstants.SUCCESS);
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
		return aMapping.findForward(UIConstants.SUCCESS);
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
		GenericLogonIdForm genericLogonIdForm = (GenericLogonIdForm) aForm;
		String action = genericLogonIdForm.getAction();
		if(action != null && action.equalsIgnoreCase(UIConstants.UPDATE_CONFIRM)){
			genericLogonIdForm.setAction(UIConstants.UPDATE_SUMMARY);
		}else if(action != null && action.equalsIgnoreCase(UIConstants.INACTIVATE_CONFIRM)){
			genericLogonIdForm.setAction(UIConstants.INACTIVATE_SUMMARY);
		}
		else if(action != null && action.equalsIgnoreCase(UIConstants.CREATE_CONFIRM)){
			genericLogonIdForm.setAction(UIConstants.CREATE_SUMMARY);
		}
		return aMapping.findForward(UIConstants.BACK);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BD0090
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		GenericLogonIdForm genericLogonIdForm = (GenericLogonIdForm) aForm;
		genericLogonIdForm.clear();
		return aMapping.findForward(UIConstants.CANCEL);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BD0090
	 */
	public ActionForward returnToSearch(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		GenericLogonIdForm genericLogonIdForm = (GenericLogonIdForm) aForm;
		genericLogonIdForm.clear();
		return aMapping.findForward(UIConstants.RETURN_SUCCESS);
	}
}
