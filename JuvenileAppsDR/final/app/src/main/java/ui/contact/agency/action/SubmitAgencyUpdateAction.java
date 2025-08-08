//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\agency\\action\\DisplayAgencyCreateAction.java

package ui.contact.agency.action;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.agency.DeleteAgencyEvent;
import messaging.agency.UpdateAgencyEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.AgencyControllerServiceNames;
import naming.UIConstants;
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
public class SubmitAgencyUpdateAction extends LookupDispatchAction
{

	/**
	 * @roseuid 4113F60D00DA
	 */
	public SubmitAgencyUpdateAction()
	{
	}

	/**
	 * @return Map
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();

		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.finish", "finish");
		buttonMap.put("button.mainPage", "mainPage");

		return buttonMap;
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
		return aMapping.findForward(UIConstants.CANCEL);
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
	public ActionForward finish(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		String forward = UIConstants.FAILURE;
		AgencyForm agencyForm = (AgencyForm) aForm;
		agencyForm.setErrorMessage("");
		String action = agencyForm.getAction();
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		if (action.equals(UIConstants.CREATE) || action.equals(UIConstants.UPDATE))
		{
			forward = this.update(agencyForm, aRequest, dispatch);
		}
		else
			if (action.equals(UIConstants.DELETE))
			{
				forward = this.delete(agencyForm, aRequest, dispatch);
			}
			else
			{
				return aMapping.findForward(forward);
			}

		IEvent reply = dispatch.getReply();
		CompositeResponse compositeResponse = (CompositeResponse) reply;
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		agencyForm.setPageType(UIConstants.CONFIRM);
		return aMapping.findForward(forward);
	}

	/**
	 * @param agencyForm
	 * @param aRequest HttpServletRequest
	 * @param dispatch IDispatch 
	 * @return String forward
	 */
	private String delete(AgencyForm agencyForm, HttpServletRequest aRequest, IDispatch dispatch)
	{
		DeleteAgencyEvent requestEvent = (DeleteAgencyEvent) EventFactory.getInstance(AgencyControllerServiceNames.DELETEAGENCY);
		requestEvent.setAgencyId(agencyForm.getAgencyId());
		dispatch.postEvent(requestEvent);
		return UIConstants.CONFIRM_DELETE_SUCCESS;
	}

	/**
	 * @param agencyForm
	 * @param aRequest HttpServletRequest
		 * @param dispatch IDispatch 
		 * @return String forward
	 */
	private String update(AgencyForm agencyForm, HttpServletRequest aRequest, IDispatch dispatch)
	{
		UpdateAgencyEvent agencyEvent = this.populateAgency(agencyForm);
		dispatch.postEvent(agencyEvent);
		if (agencyForm.getAction().equals(UIConstants.UPDATE))
		{
			return UIConstants.CONFIRM_UPDATE_SUCCESS;
		}
		else
		{
			return UIConstants.CONFIRM_CREATE_SUCCESS;
		}
	}

	/**
	 * @param agencyForm
	 * @return UpdateAgencyEvent
	 */
	private UpdateAgencyEvent populateAgency(AgencyForm agencyForm)
	{
		UpdateAgencyEvent agencyEvent =	(UpdateAgencyEvent) EventFactory.getInstance(AgencyControllerServiceNames.UPDATEAGENCY);
		agencyEvent.setJmcRep(agencyForm.getJmcRep());
		agencyEvent.setAgencyTypeId(agencyForm.getAgencyTypeId());
		agencyEvent.setAgencyName(agencyForm.getAgencyName());
		agencyEvent.setAgencyId(agencyForm.getAgencyId());
		return agencyEvent;
	}
}
