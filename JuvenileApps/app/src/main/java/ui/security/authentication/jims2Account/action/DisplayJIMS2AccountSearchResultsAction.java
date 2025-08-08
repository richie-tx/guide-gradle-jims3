//Source file: C:\\views\\Security\\app\\src\\ui\\security\\authenication\\registergenericaccount\\DisplayJIMS2AccountSearchResultsAction.java

package ui.security.authentication.jims2Account.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.authentication.GetJIMS2AccountsEvent;
import messaging.security.authentication.reply.JIMS2AccountResponseEvent;
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

public class DisplayJIMS2AccountSearchResultsAction extends LookupDispatchAction
{
   
   /**
    * @roseuid 4562205E0208
    */
   public DisplayJIMS2AccountSearchResultsAction() 
   {
    
   }
   
   /**
    * Resets form search fields as needed
    * @param jaForm
    */
   private void resetSearchFields(JIMS2AccountForm jaForm) {
   		jaForm.setSearchLastName("");
   		jaForm.setSearchFirstName("");
   		jaForm.setSearchJIMSLogonId("");		
   		jaForm.setSearchJIMS2LogonId("");
   }
   
	/**
	 * @return Map
	 * @roseuid 4294862303BD
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.find", "find");
		buttonMap.put("button.createNewJIMS2Account", "create");
		buttonMap.put("button.refresh", "refresh");
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
		JIMS2AccountForm jaForm = (JIMS2AccountForm) aForm;
		jaForm.setUsers(new ArrayList());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetJIMS2AccountsEvent getEvent =
			(GetJIMS2AccountsEvent) EventFactory.getInstance(LogonControllerServiceNames.GETJIMS2ACCOUNTS);
		
		String searchJIMSLogonId = jaForm.getSearchJIMSLogonId();		
		String searchJIMS2LogonId = jaForm.getSearchJIMS2LogonId();
		String lastName = jaForm.getSearchLastName();
		String firstName = jaForm.getSearchFirstName();
		
		if(searchJIMSLogonId != null && !searchJIMSLogonId.equals("")){
			getEvent.setJimsLogonId(searchJIMSLogonId.toUpperCase());
		}

		if(searchJIMS2LogonId != null && !searchJIMS2LogonId.equals("")){
			getEvent.setJims2LogonId(searchJIMS2LogonId.toUpperCase());
		}
		
		if(lastName != null && !lastName.equals("")){
			getEvent.setLastName(lastName.toUpperCase());
		}
		
		if(firstName != null && !firstName.equals("")){
			getEvent.setFirstName(firstName.toUpperCase());
		}
		dispatch.postEvent(getEvent);
        
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Collection users = MessageUtil.compositeToCollection(compositeResponse, JIMS2AccountResponseEvent.class);
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		if (users == null || users.isEmpty())
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.jims2account.found"));
			saveErrors(aRequest, errors);
			
		   	jaForm.setUsers(new ArrayList());
			
			return aMapping.findForward(UIConstants.FAILURE);
		}

	   	resetSearchFields(jaForm);
		
		jaForm.setUsers(users);
		jaForm.setSearchResultCount("" + users.size()); 
		return aMapping.findForward(UIConstants.LIST_SUCCESS);
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
		JIMS2AccountForm jaForm = (JIMS2AccountForm) aForm;
		String searchTypeId = jaForm.getSearchTypeId();
		jaForm.clear();
		jaForm.setSearchTypeId(searchTypeId);
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
		JIMS2AccountForm jaForm = (JIMS2AccountForm) aForm;
		jaForm.clear();
		return aMapping.findForward(UIConstants.CREATE_SUCCESS);
	}
}
