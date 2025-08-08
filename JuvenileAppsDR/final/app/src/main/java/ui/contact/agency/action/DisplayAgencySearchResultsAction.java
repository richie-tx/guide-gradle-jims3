//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\agency\\action\\DisplayAgencySearchResultsAction.java

package ui.contact.agency.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.GetAgenciesDepartmentsEvent;
import messaging.info.reply.CountInfoMessage;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.InfoMessageEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.PDContactConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.contact.agency.form.AgencyForm;

/**
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class DisplayAgencySearchResultsAction extends LookupDispatchAction
{
	/**
	 * @roseuid 4113F60D0242
	 */
	public DisplayAgencySearchResultsAction()
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
	 * @roseuid 4107F6F001E0
	 */
	public ActionForward findAgencies(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		if (!ui.security.SecurityUIHelper.isUserMA())
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.MAOnly.login"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}
		AgencyForm agencyForm = (AgencyForm) aForm;

		//empty all collection so that the display page does not display it
		Collection emptyColl = new ArrayList();
		emptyColl = MessageUtil.processEmptyCollection(emptyColl);
		agencyForm.setAgencies(emptyColl);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetAgenciesDepartmentsEvent aEvent = (GetAgenciesDepartmentsEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETAGENCIESDEPARTMENTS);
		aEvent.setAgencyName(agencyForm.getAgencyNamePrompt());
		aEvent.setAgencyTypeId(agencyForm.getAgencyTypeId());
		aEvent.setJmcRepId(agencyForm.getJmcRep());
		aEvent.setAgencyId(agencyForm.getAgencyIdPrompt());
		dispatch.postEvent(aEvent);

		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(response);
		MessageUtil.processReturnException(dataMap);

		Collection agencies = null;
		if (dataMap.containsKey(PDContactConstants.AGENCY_EVENT_TOPIC))
		{
			agencies = (Collection) dataMap.get(PDContactConstants.AGENCY_EVENT_TOPIC);
			if (agencies != null && agencies.size() > 0)
			{
				agencyForm.setAgencies(agencies);
			}
			else
				if (agencies == null || agencies.size() == 0)
				{
			       	CountInfoMessage infoEvent = new CountInfoMessage();
			       	CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(response,InfoMessageEvent.class);
			       	if (iMessage != null ){
			       		ActionErrors errors = new ActionErrors();
			       		ActionMessage error = new ActionMessage("error.max.limit.exceeded");
			       		errors.add(ActionErrors.GLOBAL_MESSAGE, error);
			       		saveErrors(aRequest, errors);
			       	} else {					
			       		ActionErrors errors = new ActionErrors();
			       		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.agency.found"));
			       		saveErrors(aRequest, errors);
			       	}
				}
		}
		else
		{
	       	CountInfoMessage infoEvent = new CountInfoMessage();
	       	CountInfoMessage iMessage = (CountInfoMessage) MessageUtil.filterComposite(response,InfoMessageEvent.class);
	       	if (iMessage != null ){
	       		ActionErrors errors = new ActionErrors();
	       		ActionMessage error = new ActionMessage("error.max.limit.exceeded");
	       		errors.add(ActionErrors.GLOBAL_MESSAGE, error);
	       		saveErrors(aRequest, errors);
	       	} else {			
	       		ActionErrors errors = new ActionErrors();
	       		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.no.agency.found"));
	       		saveErrors(aRequest, errors);
	       	}
		}   	
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
			AgencyForm agencyForm = (AgencyForm) aForm;
			//empty all collection so that the display page does not display it
			Collection emptyColl = new ArrayList();
			emptyColl = MessageUtil.processEmptyCollection(emptyColl);
			agencyForm.setAgencies(emptyColl);

			agencyForm.setAgencyNamePrompt("");
			agencyForm.setAgencyTypeId("");
			agencyForm.setAgencyIdPrompt("");
			agencyForm.setJmcRep("");

			return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
		}	
}
