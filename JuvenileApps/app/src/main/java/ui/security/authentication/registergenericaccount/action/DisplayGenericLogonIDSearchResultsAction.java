//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\security\\registergenericaccount\\DisplayGenericLogonIDSearchResultsAction.java

package ui.security.authentication.registergenericaccount.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.authentication.registergenericaccount.reply.JIMSGenericAccountResponseEvent;
import messaging.info.reply.CountInfoMessage;
import messaging.registergenericaccount.GetJIMSGenericAccountsEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.InfoMessageEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.RegisterGenericControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
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

public class DisplayGenericLogonIDSearchResultsAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 4562205E0208
    */
   public DisplayGenericLogonIDSearchResultsAction() 
   {
    
   }
   
	/**
	 * @return Map
	 * @roseuid 4294862303BD
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.find", "find");
		buttonMap.put("button.refresh", "refresh");
		buttonMap.put("button.registerGenericAccount", "create");		
		return buttonMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 429486240004
	 */
	public ActionForward find(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		GenericLogonIdForm genericLogonIdForm = (GenericLogonIdForm) aForm;
		genericLogonIdForm.setNewPassword("");
		genericLogonIdForm.setReenterPassword("");
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetJIMSGenericAccountsEvent request =
			(GetJIMSGenericAccountsEvent) EventFactory.getInstance(RegisterGenericControllerServiceNames.GETJIMSGENERICACCOUNTS);

		String logonId = genericLogonIdForm.getSearchLogonId();
		if (logonId != null)
		{
			logonId = logonId.trim().toUpperCase();
		}
		request.setLogonId(logonId);
		dispatch.postEvent(request);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		Collection users =
			   MessageUtil.compositeToCollection(compositeResponse, JIMSGenericAccountResponseEvent.class);
		if (users == null || users.isEmpty())
		{
       		Collection emptyColl = new ArrayList();
       		genericLogonIdForm.setUsers(emptyColl);
			CountInfoMessage infoEvent = new CountInfoMessage();
	       	CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(compositeResponse,InfoMessageEvent.class);
	       	if (iMessage != null ){
	       		ActionErrors errors = new ActionErrors();
	       		ActionMessage error = new ActionMessage("error.max.limit.exceeded");
	       		errors.add(ActionErrors.GLOBAL_MESSAGE, error);
	       		saveErrors(aRequest, errors);
	       	} 
	       	else
	       	{	
	       		ActionErrors errors = new ActionErrors();
	       		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.genericaccount.found"));
	       		saveErrors(aRequest, errors);
			}
       		return aMapping.findForward(UIConstants.SEARCH_FAILURE);
		}
		Collections.sort((List) users);
		genericLogonIdForm.setUsers(users);
		genericLogonIdForm.setSearchResultsCount(users.size());
		return aMapping.findForward(UIConstants.FIND_SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 429486240004
	 */
	public ActionForward refresh(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		GenericLogonIdForm genericLogonIdForm = (GenericLogonIdForm) aForm;
	   	genericLogonIdForm.clear();
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 429486240004
	 */
	public ActionForward create(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		GenericLogonIdForm genericLogonIdForm = (GenericLogonIdForm) aForm;
	   	genericLogonIdForm.clear();
	   	genericLogonIdForm.setAction(UIConstants.CREATE_SUMMARY);
		return aMapping.findForward(UIConstants.CREATE_SUCCESS);
	}
}
