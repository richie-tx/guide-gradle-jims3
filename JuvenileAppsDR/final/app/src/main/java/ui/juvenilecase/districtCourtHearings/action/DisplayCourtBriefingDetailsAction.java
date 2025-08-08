package ui.juvenilecase.districtCourtHearings.action;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.error.reply.ErrorResponseEvent;
import messaging.juvenile.SearchJuvenileProfilesEvent;
import messaging.juvenile.reply.JuvenileProfileDetailResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.districtCourtHearings.JuvenileDistrictCourtHelper;
import ui.juvenilecase.districtCourtHearings.form.CourtHearingForm;

public class DisplayCourtBriefingDetailsAction extends LookupDispatchAction {

	public DisplayCourtBriefingDetailsAction() {
	}
	
	/** 
	 * displayJuvCourtBriefingDetails
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward displayJuvCourtBriefingDetails(ActionMapping aMapping,ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {

		CourtHearingForm courtHearingForm = (CourtHearingForm) aForm;
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		String juvNumber = aRequest.getParameter("juvenileNum");
		String prevAction = "";
		Collection juvenileProfiles = courtHearingForm.getJuvenileProfiles();
		if(courtHearingForm.getPrevAction().equalsIgnoreCase("JuvNameSearch")){
			prevAction = "JuvNameSearch";
		}
		courtHearingForm.reset();
		if(prevAction.equalsIgnoreCase("JuvNameSearch")){
			courtHearingForm.setJuvenileProfiles(juvenileProfiles);
		}
		SearchJuvenileProfilesEvent searchEvent = (SearchJuvenileProfilesEvent) 
					EventFactory.getInstance(JuvenileControllerServiceNames.SEARCHJUVENILEPROFILES);
		searchEvent.setJuvenileNum(juvNumber)	;
		courtHearingForm.setAction("JuvNumSearch");
		dispatch.postEvent(searchEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();
		JuvenileProfileDetailResponseEvent respEvent = (JuvenileProfileDetailResponseEvent)MessageUtil.filterComposite(response, JuvenileProfileDetailResponseEvent.class);
		Object errorResp = MessageUtil.filterComposite(response, ErrorResponseEvent.class);
		if (errorResp != null)
		{
		  ErrorResponseEvent error = (ErrorResponseEvent) errorResp;
		  ActionErrors errors = new ActionErrors();
		  errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
		  saveErrors(aRequest, errors);
		}
		if (respEvent == null )
		{
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "No records found. Please retry search"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.FAILURE);
		}
		JuvenileDistrictCourtHelper.setProfileDetail(respEvent, courtHearingForm);
		//JuvenileDistrictCourtHelper.setCasefilesAndJPOofRecord(juvNumber, courtHearingForm, aRequest );
		return aMapping.findForward(UIConstants.BRIEFING_DETAILS);
		}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) {
		return aMapping.findForward(UIConstants.BACK);
	}
	
	@Override
	protected Map getKeyMethodMap() {
		// TODO Auto-generated method stub
		Map<String, String> keyMap = new HashMap<String, String>();
		keyMap.put("button.link", "displayJuvCourtBriefingDetails");
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
	}
	

}
