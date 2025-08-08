//Source file: C:\\views\\dev\\app\\src\\ui\\contact\\agency\\action\\DisplayAgencySummaryAction.java
//08/20/2004  Hien Rodriguez - Create Action

package ui.contact.agency.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.agency.ValidateAgencyCreateRequirementsEvent;
import messaging.agency.ValidateAgencyDeleteRequirementsEvent;
import messaging.agency.ValidateAgencyUpdateRequirementsEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.contact.agency.reply.AgencyInUseErrorResponseEvent;
import messaging.security.reply.DuplicateRecordErrorResponseEvent;
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
public class DisplayAgencySummaryAction extends LookupDispatchAction
{

	/**
	 * @roseuid 4113F60D027E
	 */
	public DisplayAgencySummaryAction()
	{

	}

	/**
	 * @return buttonMap
	 */
	protected Map getKeyMethodMap()
	{
		Map buttonMap = new HashMap();
		buttonMap.put("button.update", "update");
		buttonMap.put("button.delete", "delete");
		buttonMap.put("button.cancel", "cancel");
		buttonMap.put("button.next", "next");
		return buttonMap;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4107F6EE0313
	 */
	public ActionForward update(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		AgencyForm agencyForm = (AgencyForm) aForm;

		agencyForm.setAgencyIdPrompt(agencyForm.getAgencyId());
		agencyForm.setAgencyNamePrompt(agencyForm.getAgencyName());
		agencyForm.setAction("update");
		return aMapping.findForward(UIConstants.UPDATE_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4107F6EE0313
	 */
	public ActionForward delete(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		AgencyForm agencyForm = (AgencyForm) aForm;
		agencyForm.setPageType("");

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		ValidateAgencyDeleteRequirementsEvent requestEvent = (ValidateAgencyDeleteRequirementsEvent) EventFactory.getInstance(AgencyControllerServiceNames.VALIDATEAGENCYDELETEREQUIREMENTS);
		requestEvent.setAgencyId(agencyForm.getAgencyIdPrompt());
		dispatch.postEvent(requestEvent);

		IEvent reply = dispatch.getReply();
		CompositeResponse compositeResponse = (CompositeResponse) reply;
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);
		
		Object obj = MessageUtil.filterComposite(compositeResponse, AgencyInUseErrorResponseEvent.class);
		if (obj != null)
		{
			AgencyInUseErrorResponseEvent event = (AgencyInUseErrorResponseEvent) obj;
			agencyForm.setErrorMessage(event.getMessage());
		}
		else
		{
			agencyForm.setErrorMessage("");
		}
		//NOTE: delete doesn't need any input from prompt
		agencyForm.setAction("delete");
		return aMapping.findForward(UIConstants.DELETE_SUMMARY);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4107F6EE0313
	 */
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		AgencyForm agencyForm = (AgencyForm) aForm;
		agencyForm.clear();
		return aMapping.findForward(UIConstants.CANCEL);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 4107F6EE0313
	 */
	public ActionForward next(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		AgencyForm agencyForm = (AgencyForm) aForm;
		agencyForm.setPageType("");
		if (agencyForm.getAction().equals(UIConstants.CREATE))
		{
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			ValidateAgencyCreateRequirementsEvent requestEvent = (ValidateAgencyCreateRequirementsEvent) EventFactory.getInstance(AgencyControllerServiceNames.VALIDATEAGENCYCREATEREQUIREMENTS);
			requestEvent.setAgencyName(agencyForm.getAgencyNamePrompt());
			requestEvent.setAgencyId(agencyForm.getAgencyIdPrompt());
			dispatch.postEvent(requestEvent);

			IEvent reply = dispatch.getReply();
			CompositeResponse compositeResponse = (CompositeResponse) reply;
			Map dataMap = MessageUtil.groupByTopic(compositeResponse);
			MessageUtil.processReturnException(dataMap);
						
			Object obj = MessageUtil.filterComposite(compositeResponse, DuplicateRecordErrorResponseEvent.class);
			if (obj != null)
			{
				DuplicateRecordErrorResponseEvent event = (DuplicateRecordErrorResponseEvent) obj;
				agencyForm.setErrorMessage(event.getMessage());
			}
			else
			{
				agencyForm.setErrorMessage("");
			}
		} 
		else
			if (agencyForm.getAction().equals(UIConstants.UPDATE)
				&& !agencyForm.getOriginalAgencyName().equals(agencyForm.getAgencyNamePrompt()))
			{
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				ValidateAgencyUpdateRequirementsEvent requestEvent = (ValidateAgencyUpdateRequirementsEvent) EventFactory.getInstance(AgencyControllerServiceNames.VALIDATEAGENCYUPDATEREQUIREMENTS);
				requestEvent.setAgencyName(agencyForm.getAgencyNamePrompt());
				dispatch.postEvent(requestEvent);

				IEvent reply = dispatch.getReply();
				CompositeResponse compositeResponse = (CompositeResponse) reply;
				Map dataMap = MessageUtil.groupByTopic(compositeResponse);
				MessageUtil.processReturnException(dataMap);

				Object obj = MessageUtil.filterComposite(compositeResponse, DuplicateRecordErrorResponseEvent.class);
				if (obj != null)
				{
					DuplicateRecordErrorResponseEvent event = (DuplicateRecordErrorResponseEvent) obj;
					agencyForm.setErrorMessage(event.getMessage());
				}
				else
				{
					agencyForm.setErrorMessage("");
				}
			}
		this.setAgencyInfoFromPrompt(agencyForm);
		return aMapping.findForward(UIConstants.UPDATE_SUMMARY);
	}

	/**
	 * @param agencyForm AgencyForm 
	 * @roseuid 4107F6EE0313
	 */
	private void setAgencyInfoFromPrompt(AgencyForm agencyForm)
	{
		if (agencyForm.getAction().equals(UIConstants.CREATE))
		{
			agencyForm.setAgencyId(agencyForm.getAgencyIdPrompt());
		} //for update, since agencyId is not editable, no need to copy value from prompt
		agencyForm.setAgencyName(agencyForm.getAgencyNamePrompt());
		Collection agencyTypes = agencyForm.getAgencyTypes();
		Iterator iter = agencyTypes.iterator();
		while (iter.hasNext())
		{
			CodeResponseEvent cEvent = (CodeResponseEvent) iter.next();
			if (cEvent.getCodeId().equals(agencyForm.getAgencyTypeId()))
			{
				agencyForm.setAgencyType(cEvent.getDescription());
			}
		}
	}

}
