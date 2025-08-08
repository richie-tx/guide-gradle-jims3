//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\ui\\supervision\\cscdcalendar\\action\\DisplayCSFVEventsAction.java

package ui.supervision.cscdcalendar.fieldVisit.action;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdcalendar.GetCSFVEventsEvent;
import messaging.cscdcalendar.reply.CSFVItineraryResponseEvent;
import messaging.cscdcalendar.reply.CSFieldVisitResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CSEventControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.cscdcalendar.cscdCalendarUIUtil;
import ui.supervision.cscdcalendar.form.CSCalendarDisplayForm;
import ui.supervision.cscdcalendar.form.CSCalendarFVForm;


public class DisplayCSFVEventsAction extends JIMSBaseAction {
   
   /**
    * @roseuid 479A0F0901D1
    */
   public DisplayCSFVEventsAction() {
    
   }
   
   protected void addButtonMapping(Map keyMap) {   		
		keyMap.put("button.link", "display");
   }
   
   /**
    * @roseuid 47A22D7B03B6
    */
   public ActionForward display(
   		ActionMapping aMapping, 
		ActionForm aForm, 
		HttpServletRequest aRequest, 
		HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
   {
   		CSCalendarFVForm form = (CSCalendarFVForm)aForm;
   		
   		//reset both flows since we do not know what the user will be doing
   		form.setActivityFlow("");
   		form.setSecondaryActivityFlow("");
   		
   		//take priority over date being passed in through query string
   		String calendarDate = aRequest.getParameter("calendardate");
   		if(calendarDate != null) {
   			Date date = new Date();
   			date.setTime(Long.parseLong(calendarDate));
   			form.setEventDate(date);
   		}
   		
   		if(form.getEventDate() == null) {
   			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Event Date is missing.");
   			return aMapping.findForward(UIConstants.SUCCESS);
   	   		
   		}
   		
   		
		//get CSCalendarOtherForm from session to initialize some data
		CSCalendarDisplayForm csCalendarDisplayForm = 
			(CSCalendarDisplayForm)getSessionForm(
					aMapping, aRequest, 
					"csCalendarDisplayForm", true);
		
		//This is needed to display caseload search page properly.
   		//At this point, we already know that the event type is going to be FV
		csCalendarDisplayForm.setSelectedEventTypeCd(PDCodeTableConstants.CS_FIELD_VISIT_CATEGORY); 

		form.setPositionId(csCalendarDisplayForm.getPositionId());	
		form.setContext(csCalendarDisplayForm.getContext());
		form.setJobTitleCd(csCalendarDisplayForm.getJobTitleCd());
		form.setSuperviseeId(csCalendarDisplayForm.getSuperviseeId());
		
		if(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION.equals(
				csCalendarDisplayForm.getContext())) {
			
	   		CSFVItineraryResponseEvent itineraryRE = 
	   			cscdCalendarUIUtil.getItinerary(form.getPositionId(), 
	   					form.getEventDate());
	   		
	   		if(itineraryRE != null) {
	   			form.getCurrentItinerary().load(itineraryRE);
	   			form.getCurrentItinerary().setAgencyId(form.getAgencyId());
	   			
	   	   		
	   	   		List events = cscdCalendarUIUtil.getEventsForItinerary(
	   	   				itineraryRE.getFvItineraryId(), form.getPositionId(), form.getEventDate());
	   	   		
	   	   		Iterator eventsIter = events.iterator();
	   	   		while ( eventsIter.hasNext() ) {
	   	   			CSFieldVisitResponseEvent thisEvent = (CSFieldVisitResponseEvent) eventsIter.next();
	   	   			String outComeCd = thisEvent.getOutcomeCd();
	   	   			if ( outComeCd.equals("RE") ) {
	   	   				eventsIter.remove();
	   	   			}
	   	   		}
	   	   		
	   	   		form.setEventsList(events);
	   		} else {
	   			//copy over event date from the form
	   			form.getCurrentItinerary().setEventDate(form.getEventDate());
	   			form.getCurrentItinerary().setPositionId(form.getPositionId());
	   			form.getCurrentItinerary().setAgencyId(form.getAgencyId());
	   		}
		}
   		else if(PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(form.getContext())){
   			
	   		GetCSFVEventsEvent getCSFVEvents = 
   				(GetCSFVEventsEvent)EventFactory.getInstance(
   						CSEventControllerServiceNames.GETCSFVEVENTS);
   	   		getCSFVEvents.setCurrentContext(form.getContext());
   	   		getCSFVEvents.setSuperviseeId(csCalendarDisplayForm.getSuperviseeId());
			getCSFVEvents.setEventDate(form.getEventDate());
			
			CompositeResponse response = postRequestEvent(getCSFVEvents);
   	   		List events = (List)
   	   			MessageUtil.compositeToCollection(
   	   					response, CSFieldVisitResponseEvent.class);
   	   		
   	   		form.setEventsForSupervisee(events);
   	   		
   			return aMapping.findForward("superviseeSuccess");
   		}
		
   		
		return aMapping.findForward(UIConstants.SUCCESS);
		
   }
}
