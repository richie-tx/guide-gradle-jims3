package ui.contact.officer.action;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.GetOfficerProfileEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.OfficerProfileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.contact.UIContactHelper;
import ui.contact.officer.form.OfficerForm;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;

import org.apache.struts.action.ActionForward;

import java.util.Iterator;
import java.util.Map;
import org.apache.struts.action.ActionForm;

public class DisplayTransferOfficerSummaryAction extends JIMSBaseAction
{
	public DisplayTransferOfficerSummaryAction() {
	}
	
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.next", "next");
		keyMap.put("button.link", "link");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward next(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
		{
			OfficerForm officerForm = (OfficerForm) aForm;
			//populate new manager name on form
			Iterator managerProfiles = officerForm.getManagerProfiles().iterator();
			while (managerProfiles.hasNext())
			{
				OfficerProfileResponseEvent officer = (OfficerProfileResponseEvent) managerProfiles.next();
				if (officerForm.getNewManagerId().equals(officer.getUserId())) {
					officerForm.setNewManagerFirstName(officer.getFirstName());
					officerForm.setNewManagerMiddleName(officer.getMiddleName());
					officerForm.setNewManagerLastName(officer.getLastName());
					break;
				}
			}
			officerForm.setAction("summary");
			ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		    return forward;
		}

	public ActionForward link (ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
		{
		String officerProfileId = aRequest.getParameter("officerProfileId");
	    OfficerForm officerForm = (OfficerForm) aForm;
	    if (SecurityUIHelper.getUserTypeId().equalsIgnoreCase("MA"))
		{
			officerForm.setJuvLocations(CodeHelper.getLocationCodes("JUV"));
		} else {
			officerForm.setJuvLocations(CodeHelper.getLocationCodes());
		}
		officerForm.setJuvUnits(UIContactHelper.getJuvUnitCodes()); 

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		GetOfficerProfileEvent officerEvent = (GetOfficerProfileEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILE);
		officerEvent.setOfficerProfileId(officerProfileId);
		dispatch.postEvent(officerEvent);

		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		Map dataMap = MessageUtil.groupByTopic(compositeResponse);
		MessageUtil.processReturnException(dataMap);

		OfficerProfileResponseEvent officerResponseEvent = (OfficerProfileResponseEvent) MessageUtil.filterComposite(compositeResponse, OfficerProfileResponseEvent.class);
		UIContactHelper.setOfficerProfileForm(officerResponseEvent, officerForm);

		// set the original value of critical data
		setOriginalValueOfCriticalData(officerResponseEvent, officerForm);
		ActionForward forward = aMapping.findForward(UIConstants.VIEW_SUCCESS);
	    return forward;
        }
	
	/**
	 * @param officerResponseEvent
	 * @param officerForm
	 */
	private void setOriginalValueOfCriticalData(
		OfficerProfileResponseEvent officerResponseEvent,
		OfficerForm officerForm)
	{
		officerForm.setOriginalBadgeNumber(officerResponseEvent.getBadgeNum());
		officerForm.setOriginalOtherIdNumber(officerResponseEvent.getOtherIdNum());
		officerForm.setOriginalUserId(officerResponseEvent.getUserId());
	}
}

