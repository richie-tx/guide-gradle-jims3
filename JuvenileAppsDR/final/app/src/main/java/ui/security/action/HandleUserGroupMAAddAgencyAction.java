//Source file: C:\\views\\archproduction\\app\\src\\ui\\security\\action\\HandleUserGroupMAAddAgencyAction.java

package ui.security.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.agency.GetAgencyEvent;
import messaging.contact.agency.reply.AgencyResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.security.form.UserGroupForm;

public class HandleUserGroupMAAddAgencyAction extends LookupDispatchAction
{

	/**
	 * @roseuid 42971FE10196
	 */
	public HandleUserGroupMAAddAgencyAction()
	{

	}
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.next", "next");
		buttonMap.put("button.cancel", "cancel");
		return buttonMap;
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BC036D
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		UserGroupForm userGroupForm = (UserGroupForm) aForm;
		if (userGroupForm.getAgencyId() != null && !userGroupForm.getAgencyId().equals(""))
		{
			String agencyName = fetchAgencyName(userGroupForm.getAgencyId());
			userGroupForm.setAgencyName(agencyName);
		}
		return aMapping.findForward(UIConstants.SUCCESS);
	}

    /**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 428B82BC036D
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		return aMapping.findForward(UIConstants.CANCEL);
	}

	public String fetchAgencyName(String agencyId)
	{
		GetAgencyEvent agencyRequestEvent = (GetAgencyEvent) EventFactory.getInstance(AgencyControllerServiceNames.GETAGENCY);
		agencyRequestEvent.setAgencyId(agencyId);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(agencyRequestEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		AgencyResponseEvent agencyEvent = (AgencyResponseEvent) MessageUtil.filterComposite(compositeResponse, AgencyResponseEvent.class);
		String agencyName = agencyEvent.getAgencyName();
		return agencyName;
	}	
}