package ui.security.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.GetAgenciesEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.UIConstants;

//import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.form.UserGroupForm;

public class HandleUserGroupMAFindAgencyAction extends LookupDispatchAction
{

	/**
	 * @roseuid 425AB6C700EA
	 */
	public HandleUserGroupMAFindAgencyAction()
	{

	}

	/**
	 * @return Map
	 * @roseuid 4294862303BD
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();

		buttonMap.put("button.findAgencies", "findAgencies");
		buttonMap.put("button.refresh", "refresh");
		return buttonMap;
	}


	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 425551F8016B
	 */
	public ActionForward findAgencies(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetAgenciesEvent event = new GetAgenciesEvent();
		event.setAgencyTypeId(userGroupForm.getAgencyTypeId());
		event.setAgencyName(userGroupForm.getSearchAgencyName());
		event.setAgencyId(userGroupForm.getAgencyCode());
		dispatch.postEvent(event);
		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		
		Collection agencies = MessageUtil.compositeToCollection(compositeResponse, AgencyResponseEvent.class);
		if(agencies == null || agencies.size() == 0){
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.agency.found"));
			saveErrors(aRequest, errors);	
		}else{
			Collections.sort((List)agencies);
			userGroupForm.setAvailableAgencies(agencies);
			userGroupForm.setAgencySearchResultSize("" + agencies.size());
		}
/**		userGroupForm.setSearchAgencyName("");
		userGroupForm.setAgencyId("");
		userGroupForm.setAgencyTypeId(""); */
		return aMapping.findForward(UIConstants.FIND_SUCCESS);
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
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		//empty all collection so that the display page does not display it
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		userGroupForm .setAvailableAgencies(emptyColl);
		userGroupForm.setSearchAgencyName("");
//		userGroupForm.setAgencyId("");
		userGroupForm.setAgencyCode("");
		userGroupForm.setAgencyTypeId("");
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);		
	}		
}
