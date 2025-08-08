package ui.juvenilecase.schedulecalendarevent.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerserviceprovider.reply.ServiceProviderResponseEvent;
import messaging.administerserviceprovider.reply.ServiceResponseEvent;
import messaging.calendar.CalendarContextEvent;
import messaging.calendar.GetCalendarServiceEventsEvent;
import messaging.calendar.ServiceAttribute;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.juvenilecase.GetJuvenileCasefileEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.productionsupport.RetrieveJuvPgmRefsByProvPgmIdEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.PDConstants;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.supervision.administerserviceprovider.JuvenileServiceProvider;
import pd.supervision.programreferral.JuvenileProgramReferral;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileHelper;
import ui.juvenilecase.programreferral.UIProgramReferralBean;
import ui.juvenilecase.programreferral.UIProgramReferralHelper;
import ui.juvenilecase.schedulecalendarevent.CalendarRetrieverFactory;
import ui.juvenilecase.workshopcalendar.form.ScheduleNewEventForm;

public class DisplayCalendarSPSelectEventsAction extends JIMSBaseAction
{
	private static final String TRUE_STR = "true";
	private static final String YES_STR = "yes";
	private static final String NO_STR = "no";
	/**
	 * @roseuid 4576E78400F1
	 */
	public DisplayCalendarSPSelectEventsAction()
	{
	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.viewPrograms", "loadProgramInfo");
	}	

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		ScheduleNewEventForm sneForm = (ScheduleNewEventForm)aForm ;
		sneForm.setServiceProviderName("");
		sneForm.setProgramName("");
		sneForm.setServiceName("");
		sneForm.setProgramId("");
		//US MaxYouth changes STARTS US 180163
		JuvenileServiceProvider juvServiceProvider = JuvenileServiceProvider.find(Integer.valueOf(sneForm.getServiceProviderId()));
		//get the MAX YOUTH number for the selected vendor from the table: CSJUVSERVPROV
		if (juvServiceProvider.getMaxYouth() != null)
		{
		    int maxYouth = Integer.parseInt(juvServiceProvider.getMaxYouth());
		    if (maxYouth != 0)
		    {
			String selectedSPVendorId = sneForm.getServiceProviderId();
			//get all the providerProgramIds for selectedServProvider/selectedSPVendorId
			Collection spList = sneForm.getServiceProviderList();
			ServiceProviderResponseEvent selectedSP = new ServiceProviderResponseEvent();
			Iterator servProviderItr = spList.iterator();
			while (servProviderItr.hasNext())
			{
			    ServiceProviderResponseEvent sp = (ServiceProviderResponseEvent) servProviderItr.next();
			    if (sp.getJuvServProviderId().equalsIgnoreCase(selectedSPVendorId))
			    {
				selectedSP = sp;
				break;
			    }
			}

			Set<Integer> distinctJuvenileIds = new HashSet<>(); // Set to hold distinct juvenile IDs
			List<ServiceResponseEvent> activeProgramsList = (List<ServiceResponseEvent>) selectedSP.getServiceResponseEvents(); //get all the ACTIVE programs for the selected vendor Service Provider Name

			for (ServiceResponseEvent program : activeProgramsList)
			{
			    RetrieveJuvPgmRefsByProvPgmIdEvent getJuvProgramReferralEvent = new RetrieveJuvPgmRefsByProvPgmIdEvent();
			    getJuvProgramReferralEvent.setProvProgramId(program.getProgramId());
			    ArrayList<JuvenileProgramReferral> juvenileProgramReferralList = (ArrayList) CollectionUtil.iteratorToList(JuvenileProgramReferral.findAll(getJuvProgramReferralEvent));
			    // call above gets data FROM JIMS2.CSJUVPROGREF WHERE PROVPROGRAM_ID = program.getProgramId() 
			    Iterator juvPgrmRefItr = juvenileProgramReferralList.iterator();
			    while (juvPgrmRefItr.hasNext())
			    {
				JuvenileProgramReferral jpr = (JuvenileProgramReferral) juvPgrmRefItr.next();
				if (jpr.getEndDate() == null) //
				{
				    String casefileID = jpr.getCasefileId();
				    GetJuvenileCasefileEvent getCasefileEvent = (GetJuvenileCasefileEvent) EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETJUVENILECASEFILE);
				    getCasefileEvent.setSupervisionNumber(casefileID); //call to JCCASEFILE
				    CompositeResponse response = MessageUtil.postRequest(getCasefileEvent);
				    JuvenileCasefileResponseEvent casefile = (JuvenileCasefileResponseEvent) MessageUtil.filterComposite(response, JuvenileCasefileResponseEvent.class);
				    if (program.getServiceProviderId() != null && program.getServiceProviderId().equalsIgnoreCase(juvServiceProvider.getOID()) && program.getProgramEndDate() == null)
				    {

					distinctJuvenileIds.add(Integer.parseInt(casefile.getJuvenileNum()));
				    }
				}
			    }
			}
			if (distinctJuvenileIds.size() >= maxYouth) //check if the total UNIQUE juv count is less than the MAXYOUTH number 
			{
			    //TO DO check if the juvenileNum is already in the set, if yes, go forward with addition of program ref
			    if (distinctJuvenileIds.contains(Integer.parseInt(sneForm.getJuvenileNum())))
			    {
				System.out.println(distinctJuvenileIds);
				System.out.println(sneForm.getJuvenileNum());
			    }
			    else
			    {
				ActionErrors errors = new ActionErrors();
				String msg = "Maximum Youth Limit Exceeded for this Service Provider";
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", msg));
				saveErrors(aRequest, errors);
				return aMapping.findForward(UIConstants.FAILURE);
			    }
			}
		    }
		}
		// US MaxYouth changes ENDS TASK 180442 US 180163
		// get Service Provider display names for jsp
		List temp = sneForm.getServiceProviderList();
		for (int x = 0; x <temp.size(); x++)
		{
			ServiceProviderResponseEvent spre = (ServiceProviderResponseEvent) temp.get(x);
			if (spre.getServiceResponseEvents() != null)
			{	
				List temp1 = (List)spre.getServiceResponseEvents();
				if (temp1 != null)
				{	
					for (int y = 0; y < temp1.size(); y++)
					{
						ServiceResponseEvent sre1 = (ServiceResponseEvent) temp1.get(y);
						List temp2 = sre1.getServices();
						for (int z = 0; z < temp2.size(); z++ )
						{
							ServiceResponseEvent sre2 = (ServiceResponseEvent) temp2.get(z);
							if (sneForm.getSelectedServiceId().equals(sre2.getServiceId() ) )
							{
								sneForm.setServiceProviderName(spre.getJuvServProviderName());
								sneForm.setProgramName(sre1.getProgramName());
								sneForm.setProgramId(sre1.getProgramId());
								sneForm.setServiceName(sre2.getServiceName());
								break;
							}
						}
					}
				}
			}
		}	
		// get list of matching events
		sneForm.setEventList(new ArrayList());
		Collection attributes = new ArrayList();
		ServiceAttribute sa = new ServiceAttribute();
		sa.setServiceId(new Integer(sneForm.getSelectedServiceId().trim()));
		attributes.add(sa);

		GetCalendarServiceEventsEvent gce = (GetCalendarServiceEventsEvent) EventFactory.getInstance(ServiceEventControllerServiceNames.GETCALENDARSERVICEEVENTS);
		gce.setSpEventsOnly(true);
		gce.setResponseType(PDConstants.CAL_DETAIL_MEDIUM);
		gce.setFilterInvalidContexts(false);
		gce.setRetriever(CalendarRetrieverFactory.getRetrieverName(CalendarRetrieverFactory.PRESCHEDULED_RETRIEVER));
		gce.setCalendarAttributes(attributes);
		// Begin date range processing
		Date endDate = new Date();
		Date startDate = new Date();
		String xDate = "";
		if (sneForm.getEventFromDate() == null || "".equals(sneForm.getEventFromDate()) )
		{	
			Calendar cal = Calendar.getInstance(); 
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 99);
			cal.add(Calendar.DATE, - 7);
			startDate = cal.getTime();  // just after midnight 7 day ago
			gce.setStartDatetime(startDate);
		} else {	
			xDate = sneForm.getEventFromDate() + " 00:00";
			startDate = DateUtil.stringToDate(xDate, DateUtil.DATETIME24_FMT_1);
			gce.setStartDatetime(startDate);
		}
		
		if (sneForm.getEventToDate() == null || "".equals(sneForm.getEventToDate()) )
		{
			if (sneForm.getEventFromDate() != null && !"".equals(sneForm.getEventFromDate()) )
			{	
				xDate = sneForm.getEventFromDate();
				int mm = Integer.parseInt(xDate.substring(0,2));
				int dd = Integer.parseInt(xDate.substring(3,5));
				int yr = Integer.parseInt(xDate.substring(6,10));
				Calendar cal = Calendar.getInstance();
				cal.set(Calendar.MONTH, mm - 1);
				cal.set(Calendar.DAY_OF_MONTH, dd);
				cal.set(Calendar.YEAR, yr);
				cal.set(Calendar.HOUR_OF_DAY, 23);
				cal.set(Calendar.MINUTE, 59);
				cal.set(Calendar.SECOND, 0);
				cal.set(Calendar.MILLISECOND, 99);
				cal.add(Calendar.DATE, + 372);
				endDate = cal.getTime();
				gce.setEndDatetime(endDate);
			} else {
				Calendar cal2 = Calendar.getInstance(); 
				cal2.set(Calendar.HOUR_OF_DAY, 23);
				cal2.set(Calendar.MINUTE, 59);
				cal2.set(Calendar.SECOND, 00);
//				cal2.set(Calendar.MILLISECOND, 00);
				cal2.add(Calendar.DATE, + 365);
				endDate = cal2.getTime();
				gce.setEndDatetime(endDate); // midnight for current date
			}
		} else {
			xDate = sneForm.getEventToDate() + " 23:59";
			endDate = DateUtil.stringToDate(xDate, DateUtil.DATETIME24_FMT_1);
			gce.setEndDatetime(endDate);
		}

		// End date range processing
		CalendarContextEvent calendarContextEvent = new CalendarContextEvent();
		calendarContextEvent.setJuvenileId(sneForm.getJuvenileNum());
		gce.setCalendarContextEvent(calendarContextEvent);

		List <CalendarServiceEventResponseEvent> events = MessageUtil.postRequestListFilter(gce, CalendarServiceEventResponseEvent.class);
		
		if (events == null || events.isEmpty()) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","No events found for supplied input"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.SEARCH_FAILURE);
		}	
		temp = new ArrayList();
		for(int x = 0; x <events.size(); x++)
		{
			CalendarServiceEventResponseEvent csere = (CalendarServiceEventResponseEvent) events.get(x);
			if(csere!=null && csere.getProgramScheduleTypeId()!=null && csere.getProgramScheduleTypeId().equals("P")){ //added for U.S #11099
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","Program requires creation in the Program Referral tab. Calendar use is not required"));
				saveErrors(aRequest, errors);
				return aMapping.findForward(UIConstants.SEARCH_FAILURE);
			}
			
			if(UIJuvenileHelper.isFeatureAllowed("JCW-CFC-SP-EVENTS-MORETHAN7DAYSOLD")){
				temp.add(csere);
			}
			else
			{
				if (csere.getStartDatetime().compareTo(endDate) < 0 & csere.getStartDatetime().compareTo(startDate) > -1)
				{	
					boolean validActiveEvent = validateEvent(csere.getEventStatusCode(), csere.getCurrentEnrollment(), csere.getMaxAttendance());
					if (validActiveEvent == true )
					{		
						temp.add(csere);
					}
				}	
			}
		}
		if (temp == null || temp.isEmpty()) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","No events found for supplied input"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.SEARCH_FAILURE);
		}	
		
		//djn
		String programId = sneForm.getProgramId();
		String hasProgramReferral = "";
		if (programId != null) {
			UIProgramReferralBean referralBean = UIProgramReferralHelper.getActiveJuvenileProgramReferral(sneForm.getJuvenileNum(), programId);
			if (referralBean != null) {
				if (!referralBean.getCasefileId().equals(sneForm.getCaseFileId())) {
					hasProgramReferral = TRUE_STR; // Green - Open Referral on another casefile, can't schedule
					ActionErrors errors = new ActionErrors();
					errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.programReferral"));
					saveErrors(aRequest, errors);
				} else {
					hasProgramReferral = YES_STR; //Blue - Open referral on this casefile, you can schedule
				}
				sneForm.setJuvPgmReferralId(referralBean.getReferralId());
			} else {
				hasProgramReferral = NO_STR; //Crossed Out Blue - No Referrals anywhere, you can schedule
			}
		}
		sneForm.setHasProgramReferral(hasProgramReferral);	
		//djn	
		sneForm.setEventList(temp);
		sneForm.setSelectedEventIds(new String[0]);
		sneForm.setPageState("Summary");

		return aMapping.findForward(UIConstants.SUCCESS);
	}

	private boolean validateEvent(String eventStatus, String currentEnrollment, String maxEnrollment)
	{
		if (eventStatus == null || "".equals(eventStatus) || "CC".equals(eventStatus))
		{
			return false;
		}
		if ("CM".equals(eventStatus))
		{
			if ( currentEnrollment == null || maxEnrollment == null || "".equals(currentEnrollment) || "".equals(maxEnrollment) )
			{		
				return true;
			}	
			if (currentEnrollment.equals(maxEnrollment) )
			{	
				return false;
			}	
		}
		return true;
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward loadProgramInfo(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		ScheduleNewEventForm sneForm = (ScheduleNewEventForm)aForm ;
		sneForm.setProgramList(new ArrayList());
		if (sneForm.getServiceProviderId() != null && !"".equals(sneForm.getServiceProviderId() ) )
		{
			int max = sneForm.getServiceProviderList().size();
			for (int x =0; x<max; x++)
			{
				ServiceProviderResponseEvent spre = (ServiceProviderResponseEvent) sneForm.getServiceProviderList().get(x);
				if (sneForm.getServiceProviderId().equals(spre.getJuvServProviderId() ) )
				{
					List workList = new ArrayList();
					if (spre.getServiceResponseEvents() != null)
					{	
						Iterator itr2 = spre.getServiceResponseEvents().iterator();
						while (itr2.hasNext())
						{
							ServiceResponseEvent spe = (ServiceResponseEvent) itr2.next();
							workList.add(spe);
						}
					}
					sneForm.setProgramList(workList);
					break;
				}	
			}	
		}	
		return aMapping.findForward( UIConstants.SERVICE_PROVIDER_SUCCESS) ;
	}	
}