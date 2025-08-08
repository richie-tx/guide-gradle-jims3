package ui.juvenilewarrant.action;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilewarrant.GetJOTDataEvent;
import messaging.juvenilewarrant.reply.ActiveWarrantErrorEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileWarrantControllerServiceNames;
import naming.PDJuvenileWarrantConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.AbstractAction;
import ui.juvenilewarrant.form.JuvenileWarrantForm;

public class DisplayJOTSearchResultsAction extends AbstractAction
{

	/**
	@roseuid 40D89C070110
	 */
	public DisplayJOTSearchResultsAction()
	{

	}

	/**
	@param aMapping
	@param aForm
	@param aRequest
	@param aResponse
	@return ActionForward
	@roseuid 40D891250148
	 */
	public ActionForward execute(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		String forwardStr = UIConstants.SEARCH_FAILURE;

		JuvenileWarrantForm jwForm = (JuvenileWarrantForm) aForm;
//		trap refresh button	
		if (jwForm.getRefreshButton().equalsIgnoreCase("Y"))
		{
			jwForm.clear();
			return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
		}
		GetJOTDataEvent event =
			(GetJOTDataEvent) EventFactory.getInstance(JuvenileWarrantControllerServiceNames.GETJOTDATA);

		jwForm.fillIdentity(event);

		CompositeResponse response = MessageUtil.postRequest(event);

		ActionErrors errors = new ActionErrors();

		this.processBusinessExceptions(aRequest, response, errors);
		this.saveErrors(aRequest, errors);

		if (errors.isEmpty())
		{			
			boolean unrecoverableError = jwForm.setJOTProperties(response, errors);

			this.saveErrors(aRequest, errors);

			if (unrecoverableError == true)
			{
				super.setUnrecoverableError(aRequest);
				forwardStr = UIConstants.SEARCH_FAILURE;
			}
			else
			{
				jwForm.setUserId("");
				jwForm.setOfficerAgencyId("");
				jwForm.setOfficerAgencyName("");
				jwForm.setAffidavitStatement("");
				jwForm.setSearch("");
				forwardStr = UIConstants.SUCCESS;
			}
		}
		else
		{
			forwardStr = UIConstants.SEARCH_FAILURE;
		}

		jwForm.setBackToWarrantUrl(aMapping.findForward(forwardStr).getPath());
		jwForm.setBackForward(forwardStr);

		return aMapping.findForward(forwardStr);
	}

	/* (non-Javadoc)
	 * @see ui.action.AbstractAction#processBusinessExceptions(mojo.km.messaging.Composite.CompositeResponse)
	 */
	public void processBusinessExceptions(HttpServletRequest aRequest, CompositeResponse response, ActionErrors errors)
	{
		Map jotData = MessageUtil.groupByTopic(response);

		Collection jotResponse =
			(Collection) jotData.get(PDJuvenileWarrantConstants.JUVENILE_OFFENDER_TRACKING_EVENT_TOPIC);

		Collection charges = (Collection) jotData.get(PDJuvenileWarrantConstants.PETITION_EVENT_TOPIC);

		ActiveWarrantErrorEvent activeError =
			(ActiveWarrantErrorEvent) MessageUtil.filterComposite(response, ActiveWarrantErrorEvent.class);

		if (activeError != null)
		{
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(UIConstants.JW_ACTIVEORPENDINGWARRANT_ERROR));
		}
		else if (jotResponse == null)
		{
			// no records found, return to the search page with error				
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(UIConstants.NO_RECORDS_ERROR));
		}
		else if (charges == null)
		{
			// no charges found, return to the search page with error
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(UIConstants.JW_NO_CHARGES_ERROR));
		}
	}

}