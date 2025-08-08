package ui.juvenilecase.interviewinfo.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.CalendarContextEvent;
import messaging.calendar.GetCalendarServiceEventsEvent;
import messaging.calendar.reply.CalendarServiceEventResponseEvent;
import messaging.interviewinfo.GetInterviewsEvent;
import messaging.interviewinfo.reply.InterviewResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.InterviewConstants;
import naming.JuvenileInterviewInfoControllerServiceNames;
import naming.PDCalendarConstants;
import naming.ServiceEventControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.UIJuvenileCasefileStatusHelper;
import ui.juvenilecase.UIJuvenileInterviewInfoHelper;
import ui.juvenilecase.interviewinfo.form.JuvenileInterview;
import ui.juvenilecase.interviewinfo.form.JuvenileInterviewForm;
import ui.juvenilecase.schedulecalendarevent.CalendarRetrieverFactory;

/**
 * 
 * @author awidjaja
 */
public class DisplayJuvInterviewListAction extends JIMSBaseAction
{
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 42F79A090282
	 */
	public ActionForward displayJuvInterviewList(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		JuvenileInterviewForm form = (JuvenileInterviewForm) aForm;
		form.clear();
		
		UIJuvenileInterviewInfoHelper.populateInterviewForm(form, aRequest);
		
		Collection interviewsList = getAllInterviews(form.getCasefileId(), null);
		Collection interviews = JuvenileInterview.convertRE(interviewsList, 
				form.getInterviewLocationList(), form.getPersonsInterviewedList());
		form.setAllInterviews(interviews);
		
		form.setTodayAppointments( 
			getTodayAppointments(form, form.getInterviewLocationList(),	form.getPersonsInterviewedList()));

		form.setAllowUpdates(UIJuvenileCasefileStatusHelper.casefileStatusClosed(aRequest));

		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	/**
	 * Interviews with PENDING status will not be returned as it will show up in today's 
	 * appointments.
	 * @param casefileId
	 * @param juvenileNum
	 * @return
	 */
	protected ArrayList getAllInterviews(String casefileId, String juvenileNum) 
	{
		ArrayList<InterviewResponseEvent> interviewsList = null ;
		
		{ //get list of all interviews
			GetInterviewsEvent getInterviewsEvent = (GetInterviewsEvent) 
					EventFactory.getInstance(JuvenileInterviewInfoControllerServiceNames.GETINTERVIEWS);
			
			if( casefileId != null )
			{
				getInterviewsEvent.setCasefileId(casefileId);
			}

			if( juvenileNum != null )
			{
				getInterviewsEvent.setJuvenileId(juvenileNum);
			}
			
			CompositeResponse compositeResponse = postRequestEvent(getInterviewsEvent);
			interviewsList = (ArrayList)MessageUtil.compositeToCollection(compositeResponse, InterviewResponseEvent.class);
		}
		
		//need 2 lists to work with, we encounters java.util.ConcurrentModificationException 
		//while removing items from the list which we're iterating from 
		ArrayList filteredInterviewsList = new ArrayList();
		
		if(interviewsList != null && interviewsList.size() > 0)
		{
			Date currentDateAndTime = DateUtil.getCurrentDate();
			
			for( InterviewResponseEvent ire : interviewsList )
			{
				if( ! InterviewConstants.INTERVIEW_STATUS_PENDING.equals(ire.getInterviewStatusCd())
						||  (ire.getInterviewDate() == null || ire.getInterviewDate().before(currentDateAndTime)) ) 
				{
					/* Only add Interviews with status other than PENDING,
					 * as it will show up in today's appointments - awidjaja
					 * even if status is PENDING, add the interview if 
					 * interview date is other than TODAY
					 * awidjaja 5/14/08 Defect 51808
					 */
					filteredInterviewsList.add(ire);
				}
			}
			
			Collections.sort((ArrayList)filteredInterviewsList);
		}
		
		return filteredInterviewsList;
	}

	/*
	 * 
	 */
	protected Collection getTodayAppointments(JuvenileInterviewForm interviewForm, 
			Collection interviewLocationList, Collection personsInterviewedList) 
	{
		GetCalendarServiceEventsEvent gce =(GetCalendarServiceEventsEvent) 
				EventFactory.getInstance( ServiceEventControllerServiceNames.GETCALENDARSERVICEEVENTS);
 			
		Calendar requestedDate = Calendar.getInstance();
		requestedDate.setTime(new Date());
		requestedDate.set(Calendar.HOUR_OF_DAY, 0);
		requestedDate.set(Calendar.MINUTE, 0);
		requestedDate.set(Calendar.SECOND, 0);
		requestedDate.set(Calendar.MILLISECOND, 0);
		
		gce.setStartDatetime(requestedDate.getTime());
		
		requestedDate.set(Calendar.HOUR_OF_DAY, 23);
		requestedDate.set(Calendar.MINUTE, 59);
		requestedDate.set(Calendar.SECOND, 59);
		requestedDate.set(Calendar.MILLISECOND, 0);
		
		gce.setEndDatetime(requestedDate.getTime());
				
		String juvenileNum = interviewForm.getJuvenileNum();
		
		CalendarContextEvent calendarContextEvent = new CalendarContextEvent();
		calendarContextEvent.setJuvenileId(juvenileNum);
		gce.setCalendarContextEvent(calendarContextEvent); 
		
		gce.setRetriever(CalendarRetrieverFactory.getRetrieverName(CalendarRetrieverFactory.ACTIVEEVENTS_RETRIEVER));		
		
		List<CalendarServiceEventResponseEvent> events = 
					MessageUtil.postRequestListFilter(gce, CalendarServiceEventResponseEvent.class);
		Collections.sort(events);
		Collection todayAppointments = new ArrayList();
		
		for( CalendarServiceEventResponseEvent re : events )
		{
			if( criteriaMatches(re, interviewForm) )
			{
				JuvenileInterview interview = new JuvenileInterview(interviewLocationList, personsInterviewedList);
				
				interview.setInterviewDate(re.getEventDate());
				interview.setJuvLocUnitDescription(re.getServiceLocationName());
				interview.setInterviewType(re.getInterviewType());
				interview.setInterviewId(re.getInterviewId());
				interview.setEventStatusCd(re.getEventStatusCode());
				interview.setEventStatusDescription(re.getEventStatus());
				interview.setCasefileId(re.getCasefileId());
				todayAppointments.add(interview);
			}
		} // for
		
		return todayAppointments;
	}

	/* 
	 * break down the complicated logic checks to this function
	 */
	private boolean criteriaMatches( CalendarServiceEventResponseEvent re, 
			JuvenileInterviewForm interviewForm )
	{
		boolean rtn = false ;
		
		if( (re.getEventTypeCode().equals(UIConstants.INTERVIEW_SCHEDULED) && 
				 re.getEventStatusCode().equalsIgnoreCase(PDCalendarConstants.SERVICE_EVENT_STATUS_SCHEDULED) )
				 &&
				 InterviewConstants.INTERVIEW_STATUS_PENDING.equals(re.getInterviewStatusCd())
				 &&
				 (interviewForm.getCasefileId() == null ||
						 interviewForm.getCasefileId().trim().length() < 1 ||
						 re.getCasefileId().equals(interviewForm.getCasefileId()) ) )
		{
			rtn = true ;
		}

		return( rtn ) ;
	}

	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) 
	{
		keyMap.put("button.link", "displayJuvInterviewList");
	}		
}