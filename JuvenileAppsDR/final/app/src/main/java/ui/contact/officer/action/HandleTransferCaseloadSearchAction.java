package ui.contact.officer.action;

import messaging.contact.officer.reply.OfficerProfileResponseEvent;
import messaging.officer.GetOfficerProfileEvent;
import messaging.officer.GetOfficerProfilesByAttributeEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.OfficerProfileControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;

public class HandleTransferCaseloadSearchAction extends JIMSBaseAction
{
	public HandleTransferCaseloadSearchAction() {
	}
	
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.findOfficers", "findOfficers");
		keyMap.put("button.link", "link");
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward findOfficers(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
		{
			OfficerForm officerForm = (OfficerForm) aForm;
			
			//empty all collection so that the display page does not display it
			Collection emptyColl = new ArrayList();
			emptyColl = MessageUtil.processEmptyCollection(emptyColl);
			officerForm.setOfficerProfiles((List) emptyColl);
			
			List profiles = new ArrayList<OfficerProfileResponseEvent>();
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			GetOfficerProfilesByAttributeEvent event = (GetOfficerProfilesByAttributeEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILESBYATTRIBUTE);
			event.setAttributeName(UIConstants.OFFICER_MANAGER_USERID);
			event.setAttributeValue(officerForm.getManagerId());
			dispatch.postEvent(event);
			CompositeResponse response = (CompositeResponse) dispatch.getReply();
			List officerprofiles = MessageUtil.compositeToList(response, OfficerProfileResponseEvent.class);
			if (officerprofiles != null && officerprofiles.size() > 0)
			{
				Collections.sort(officerprofiles);
				Iterator<OfficerProfileResponseEvent> events = officerprofiles.iterator();
				while(events.hasNext()){
					OfficerProfileResponseEvent resp = events.next();
					// Here we need to retrieve only the officers who are of type Juvenile
					if(UIConstants.OFFICER_TYPE_JUVENILE.equalsIgnoreCase(resp.getOfficerTypeId())&& (resp.getStatusId().equals("A"))){
						profiles.add(resp);
					}
				}
			}
			List clms = (List) officerForm.getManagerProfiles();
			String clm = officerForm.getManagerId();
			officerForm.clear();
			officerForm.setManagerId(clm);
			officerForm.setManagerProfiles(clms);
			officerForm.setOfficerProfiles(profiles);
			if (profiles != null && profiles.size() == 0)
			{
				// zero results: forward 'searchFailure'
				ActionErrors errors = new ActionErrors();
				errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( "error.noRecords" ) );
				saveErrors( aRequest, errors );
				return aMapping.findForward( UIConstants.SEARCH_FAILURE );
			}
			
			ActionForward forward = aMapping.findForward(UIConstants.FIND_SUCCESS);
		    return forward;
		}
	
	public ActionForward next(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
		{
			OfficerForm officerForm = (OfficerForm) aForm;
			
			Iterator managerProfiles = officerForm.getManagerProfiles().iterator();
			while (managerProfiles.hasNext())
			{
				OfficerProfileResponseEvent officer = (OfficerProfileResponseEvent) managerProfiles.next();
				if (officerForm.getManagerId().equals(officer.getUserId())) {
					officerForm.setManagerFirstName(officer.getFirstName());
					officerForm.setManagerMiddleName(officer.getMiddleName());
					officerForm.setManagerLastName(officer.getLastName());
					break;
				}
			}
			
	    	List newList = new ArrayList();
		    if (officerForm.getSelectedUserIds() != null){
		    	int len = officerForm.getSelectedUserIds().length;
		    	int listSize = officerForm.getOfficerProfiles().size();

		    	for (int x =0; x < listSize; x++){
		    		OfficerProfileResponseEvent mre = (OfficerProfileResponseEvent) officerForm.getOfficerProfiles().get(x);
		    		for (int y =0; y < len; y++){
		                if (mre.getUserId().equalsIgnoreCase(officerForm.getSelectedUserIds()[y])){
		                     newList.add(mre);
		                     break;
		                }    
		           }
		    	}
		    }	
			officerForm.setSelectedList(newList);
			ActionForward forward = aMapping.findForward(UIConstants.NEXT_SUCCESS);
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
//			UIContactHelper.setManageOfficerValuesFromDropDownList(officerForm);
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
