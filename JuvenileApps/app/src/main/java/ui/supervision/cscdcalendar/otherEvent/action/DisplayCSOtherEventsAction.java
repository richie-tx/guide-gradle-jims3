//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\ui\\supervision\\cscdcalendar\\action\\DisplayCSOtherEventsAction.java

package ui.supervision.cscdcalendar.otherEvent.action;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdcalendar.GetCSOtherEventsEvent;
import messaging.cscdcalendar.reply.CSOtherResponseEvent;
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
import ui.common.StringUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.cscdcalendar.form.CSCalendarDisplayForm;
import ui.supervision.cscdcalendar.form.CSCalendarOtherForm;


public class DisplayCSOtherEventsAction extends JIMSBaseAction {
   
   /**
    * @roseuid 479A0F0A02DB
    */
   public DisplayCSOtherEventsAction() {
    
   }
   
   protected void addButtonMapping(Map keyMap) {   		
		keyMap.put("button.link", "displayOtherEvents");
   }
   
   /**
    * @roseuid 47A227CD025E
    */
   public ActionForward displayOtherEvents(
   		ActionMapping aMapping, 
		ActionForm aForm, 
		HttpServletRequest aRequest, 
		HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
   {
   		CSCalendarOtherForm form = (CSCalendarOtherForm)aForm;
   		form.setEvents(null);
   		if(!StringUtil.isEmpty(form.getActivityFlow()) && form.getActivityFlow().equalsIgnoreCase("enterMultipleResults")){
   		form.setActivityFlow("");
   		}
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
   		
   		//get positionId & context from displayForm
		CSCalendarDisplayForm displayForm = 
			(CSCalendarDisplayForm)getSessionForm(
					aMapping, aRequest, 
					"csCalendarDisplayForm", true);
		form.setContext(displayForm.getContext());
		form.setPositionId(displayForm.getPositionId());
		form.setSuperviseeId(displayForm.getSuperviseeId());
		
   		GetCSOtherEventsEvent reqEvent = 
			(GetCSOtherEventsEvent)EventFactory.getInstance(
					CSEventControllerServiceNames.GETCSOTHEREVENTS);
   		reqEvent.setEventDate(form.getEventDate());
   		
		reqEvent.setCurrentContext(form.getContext());
		if(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION.equals(form.getContext())) {
			reqEvent.setPositionId(form.getPositionId());
		} else if (PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(form.getContext())) {
			reqEvent.setSuperviseeId(form.getSuperviseeId());
		}
		
   		CompositeResponse response = postRequestEvent(reqEvent);
   		Collection otherEvents = 
   			MessageUtil.compositeToCollection(
   					response, CSOtherResponseEvent.class);
   		Iterator otherEventsIter = otherEvents.iterator();
		
		while ( otherEventsIter.hasNext()){			
			CSOtherResponseEvent OEResponse = (CSOtherResponseEvent) otherEventsIter.next();
			String outCome = OEResponse.getOutcome();
			if ( "RE".equalsIgnoreCase( outCome ) ){
				otherEventsIter.remove();
			}
		} 
   		
   		form.setEvents(otherEvents);
   		
   		return aMapping.findForward("success");
   		
   		
   }
}
