//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\mentalhealth\\action\\DisplayMentalHealthTestSessionSummaryAction.java

package ui.juvenilecase.mentalhealth.action;

import java.util.Collection;
import java.util.Map;
import messaging.address.reply.AddressResponseEvent;
import messaging.administerlocation.GetJuvenileLocationEvent;
import messaging.administerlocation.reply.LocationResponseEvent;
import messaging.calendar.EventIdAttribute;
import messaging.calendar.GetCalendarServiceEventsEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.mentalhealth.GetMentalHealthTestDataEvent;
import messaging.mentalhealth.reply.TestingSessionResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileMentalHealthControllerServiceNames;
import naming.UIConstants;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import ui.action.JIMSBaseAction;
import ui.juvenilecase.mentalhealth.form.TestingSessionForm;
import ui.juvenilecase.programreferral.ProgramReferralState;
import ui.juvenilecase.programreferral.ProgramReferralStateManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DisplayMentalHealthTestSessionSummaryAction extends JIMSBaseAction
{
	/**
	 * @roseuid 45D4AEAC0276
	 */
	public DisplayMentalHealthTestSessionSummaryAction()
	{
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 45D49C900388
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		TestingSessionForm sessForm = (TestingSessionForm)aForm;
		sessForm.setActionType("summary");

		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
	}

	/*
	 * @param aMapping 
	 * @param aForm 
	 * @param aRequest 
	 * @param aResponse
	 * 
	 * @return
	 */
	public ActionForward view(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		TestingSessionForm sessForm = (TestingSessionForm)aForm;
		sessForm.setTestSessId(sessForm.getSelectedValue());

		Collection<TestingSessionResponseEvent> coll = sessForm.getTestResultsList();
		if( coll != null )
		{
			for( TestingSessionResponseEvent resp : coll )
			{
				if( sessForm.getTestSessId().equals(resp.getTestSessID()) )
				{
					sessForm.setServiceProviderName(resp.getServiceProviderName());
					
					if(resp.getInstructorName() != null) {
					    String name = resp.getInstructorName();

					    // Remove anything after the parenthesis only if the field contains one
					    if (name != null && name.contains("(")) {
						name = name.replaceAll("\\s*\\(.*\\)", "");
					    }
					   
					    resp.setInstructorName(name);
					}
					
					sessForm.setInstructorName(resp.getInstructorName());
					sessForm.setEventType(resp.getEventType());
					sessForm.setEventId(resp.getServiceEventId());
					sessForm.setProgramReferralNum(resp.getProgramReferralNum());

					ProgramReferralState referralState = ProgramReferralStateManager.getReferralState(
									resp.getReferralStatusCd(), resp.getReferralSubStatusCd());

					sessForm.setProgramStatus(referralState.getDescription());
					sessForm.setReferralDate(resp.getReferralDate());
					break;
				}
			}
		}

		GetMentalHealthTestDataEvent mEvent = (GetMentalHealthTestDataEvent)
				EventFactory.getInstance(JuvenileMentalHealthControllerServiceNames.GETMENTALHEALTHTESTDATA);

		mEvent.setTestSessID(sessForm.getSelectedValue());
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(mEvent);

		CompositeResponse response = (CompositeResponse)dispatch.getReply();
		Object obj = MessageUtil.filterComposite(response, TestingSessionResponseEvent.class);
		if( obj != null )
		{
			TestingSessionResponseEvent resp = (TestingSessionResponseEvent)obj;
			fillTestSess(resp, sessForm);
		}

		sessForm.setActionType("view");

		ActionForward forward = aMapping.findForward(UIConstants.SUCCESS);
		return forward;
	}

	/*
	 * @param resp
	 * 
	 * @param sessForm
	 */
	private void fillTestSess(TestingSessionResponseEvent resp, TestingSessionForm sessForm)
	{
		sessForm.setSessionDate(DateUtil.dateToString(resp.getSessionDate(), "MM/dd/yyyy"));

		{ String testType = resp.getTestType() ;
			sessForm.setTestTypeId( (testType == null) ? "" : testType );
		}
		sessForm.setActualSessionLength(resp.getActualSessionLength());

		// get event details
		CalendarServiceEventResponseEvent calResp = getEventDetails(resp.getServiceEventId());
		if( calResp != null )
		{
			sessForm.setEventType(calResp.getEventType());
			sessForm.setEvtSessionLength(calResp.getEventSessionLength());
			sessForm.setEventStatus(calResp.getEventStatus());
			sessForm.setEventTime(calResp.getEventTime());
			sessForm.setLocationDetails(calResp.getLocation());
			sessForm.setServiceLocationUnit(calResp.getServiceLocationName());
			sessForm.setLocationDetails(getLocationDetails(calResp.getLocationId()));
		}

		sessForm.setPsychoAssessment(resp.getPsychologicalAssessment());
		sessForm.setPsychiatricAssessment(resp.getPsychiatricAssessment());
		sessForm.setMentalRetardationDiagnosis(resp.getMentalRetardationDiagnosis());
		sessForm.setMentalIllnessDiagnosis(resp.getMentalIllnessDiagnosis());
		sessForm.setRecommendations(resp.getRecommendations());
	}

	/*
	 * @param eventId
	 * 
	 * @return
	 */
	private CalendarServiceEventResponseEvent getEventDetails(String eventId)
	{
		GetCalendarServiceEventsEvent calendarServicesEventsEvent = new GetCalendarServiceEventsEvent();
		EventIdAttribute idAttr = new EventIdAttribute();

		try
		{
			int ID = Integer.parseInt( eventId ) ;
			idAttr.setServiceEventId( ID );
		}
		catch( NumberFormatException nfe )
		{
			return( (CalendarServiceEventResponseEvent)null ) ;
		}
		
		calendarServicesEventsEvent.addCalendarAttribute(idAttr);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(calendarServicesEventsEvent);

		CompositeResponse response1 = (CompositeResponse)dispatch.getReply();
		CalendarServiceEventResponseEvent resp = (CalendarServiceEventResponseEvent)
				MessageUtil.filterComposite(response1, CalendarServiceEventResponseEvent.class);

		return resp;
	}

	/*
	 * @param locationId
	 * 
	 * @return
	 */
	private String getLocationDetails(String locationId)
	{
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);

		{ GetJuvenileLocationEvent locationEvent = new GetJuvenileLocationEvent();
			locationEvent.setLocationId(locationId);
			dispatch.postEvent(locationEvent);
		}

		CompositeResponse compositeResponse = (CompositeResponse)dispatch.getReply();
		Object obj = MessageUtil.filterComposite(compositeResponse,
				LocationResponseEvent.class);
		if( obj != null )
		{
			LocationResponseEvent resp = (LocationResponseEvent)obj;
			AddressResponseEvent addrResp = resp.getLocationAddress();
			StringBuffer buff = new StringBuffer();
			buff.append( addrResp.getStreetNum() + " " + 
					addrResp.getStreetName() + " " + addrResp.getCity() + " " + 
					addrResp.getState() + " " + addrResp.getZipCode());

			if( notNullNotEmptyString(addrResp.getAdditionalZipCode()) )
			{
				buff.append("-" + addrResp.getAdditionalZipCode());
			}

			return buff.toString();
		}

		return null;
	}

	/*
	 * @param aMapping
	 * 
	 * @param aForm
	 * 
	 * @param aRequest
	 * 
	 * @param aResponse
	 * 
	 * @return
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
		return forward;
	}

	/*
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		TestingSessionForm sessForm = (TestingSessionForm)aForm;
		sessForm.setActionType("");

		ActionForward forward = aMapping.findForward(UIConstants.BACK);
		return forward;
	}

	/*
	 * given a string, return true if it's not null and not empty
	 * 
	 * @param str
	 * 
	 * @return
	 */
	private boolean notNullNotEmptyString(String str)
	{
		return(str != null && (str.trim().length() > 0));
	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.view", "view");
	}
}
