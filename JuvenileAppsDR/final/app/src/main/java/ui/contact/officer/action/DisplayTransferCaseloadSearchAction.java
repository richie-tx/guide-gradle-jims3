package ui.contact.officer.action;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import messaging.contact.officer.reply.OfficerProfileResponseEvent;
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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.contact.officer.form.OfficerForm;
import ui.exception.GeneralFeedbackMessageException;

public class DisplayTransferCaseloadSearchAction extends JIMSBaseAction
{
	public DisplayTransferCaseloadSearchAction() {
	}
	
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link", "transferCaseload");
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
	public ActionForward execute(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
		{
			OfficerForm officerForm = (OfficerForm) aForm;
			officerForm.clear();
			List profiles = new ArrayList<OfficerProfileResponseEvent>();
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			GetOfficerProfilesByAttributeEvent event = (GetOfficerProfilesByAttributeEvent) EventFactory.getInstance(OfficerProfileControllerServiceNames.GETOFFICERPROFILESBYATTRIBUTE);
			event.setAttributeName(UIConstants.OFFICER_SUBTYPE);
			event.setAttributeValue(UIConstants.OFFICER_SUBTYPE_CLM);
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
			officerForm.setManagerProfiles(profiles);
			if(profiles.size() == 0){
			// zero results: forward 'searchFailure'
				ActionErrors errors = new ActionErrors();
				errors.add( ActionErrors.GLOBAL_MESSAGE, new ActionMessage( "error.noRecords" ) );
				saveErrors( aRequest, errors );
				return aMapping.findForward( UIConstants.SEARCH_FAILURE );
			}
			ActionForward forward = aMapping.findForward(UIConstants.SEARCH_SUCCESS);
		    return forward;
		}
}
