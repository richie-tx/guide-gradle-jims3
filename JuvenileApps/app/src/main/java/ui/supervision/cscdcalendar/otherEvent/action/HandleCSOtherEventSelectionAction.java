//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\ui\\supervision\\cscdcalendar\\action\\DisplayCSOtherEventUpdateAction.java

package ui.supervision.cscdcalendar.otherEvent.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdcalendar.reply.CSOtherResponseEvent;
import messaging.cscdcalendar.reply.CSSpnViewConditionsResponseEvent;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administersupervisee.UIAdministerSuperviseeHelper;
import ui.supervision.cscdcalendar.form.CSCalendarOtherForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;


public class HandleCSOtherEventSelectionAction extends JIMSBaseAction
{
   
   /**
    * @roseuid 479A0F0A03B6
    */
   public HandleCSOtherEventSelectionAction() {
    
   }
   
   	public ActionForward back(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		
		return aMapping.findForward(UIConstants.BACK);
	}
		
	public ActionForward cancel(
		ActionMapping aMapping,
		ActionForm aForm,
		HttpServletRequest aRequest,
		HttpServletResponse aResponse)
	{
		
		return aMapping.findForward(UIConstants.CANCEL);
	}

   protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.addNewEvent", "addNewEvent");
		keyMap.put("button.update", "update");
		keyMap.put("button.delete", "delete");
		keyMap.put("button.reschedule", "reschedule");
		keyMap.put("button.enterResults", "enterResults");
		keyMap.put("button.view", "view");
		keyMap.put("button.link", "link");
		
		
   }   
   
   /**
    * @roseuid 47A22846002B
    */
   public ActionForward enterResults(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
		CSCalendarOtherForm form = (CSCalendarOtherForm)aForm;
	   	
		String eventLoaded = aRequest.getParameter("eventLoaded");
		if(!"true".equals(eventLoaded)) {
	   		CSOtherResponseEvent selectedEvent = null;
	   		for(Iterator iter = form.getEvents().iterator();iter.hasNext();) {
	   			CSOtherResponseEvent event = (CSOtherResponseEvent)iter.next();
	   			if(event.getEventId().equals(form.getSelectedEventId())) {
	   				selectedEvent = event;
	   			}
	   		}
	   		
	   		if(selectedEvent != null) {
	   			form.populate(selectedEvent);
	   			if (selectedEvent.getSuperviseeId()!=null){
					instantiateHeaderForm(selectedEvent.getSuperviseeId(), aMapping, aRequest);						
				}	
	   		}
	   		
		}
		
		form.setActivityFlow("enterResults");
		
		
		return aMapping.findForward("enterResultsSuccess");
   }
   
   /**
    * @roseuid 47A228010164
    */
   public ActionForward addNewEvent(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
   		CSCalendarOtherForm form = (CSCalendarOtherForm)aForm;
   	   	form.clear();
   		form.setActivityFlow(UIConstants.CREATE);
   		
   		
   		return aMapping.findForward(UIConstants.SUCCESS);
   }
   
   /**
    * @roseuid 47A2280A028D
    */
   public ActionForward update(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
   		CSCalendarOtherForm form = (CSCalendarOtherForm)aForm;
   		
   		String eventLoaded = aRequest.getParameter("eventLoaded");
		if(!"true".equals(eventLoaded)) {
	   		CSOtherResponseEvent selectedEvent = null;
	   		for(Iterator iter = form.getEvents().iterator();iter.hasNext();) {
	   			CSOtherResponseEvent event = (CSOtherResponseEvent)iter.next();
	   			if(event.getEventId().equals(form.getSelectedEventId())) {
	   				selectedEvent = event;
	   			}
	   		}
	   		
	   		if(selectedEvent != null) {
	   			form.populate(selectedEvent);
	   			if (selectedEvent.getSuperviseeId()!=null){
					instantiateHeaderForm(selectedEvent.getSuperviseeId(), aMapping, aRequest);						
				}	
	   		}
	   		
	   		
		}
		
		
   		
		form.setActivityFlow(UIConstants.UPDATE);
   		return aMapping.findForward(UIConstants.SUCCESS);
   }
   
   /**
    * @roseuid 47A228210125
    */
   public ActionForward delete(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
   		CSCalendarOtherForm form = (CSCalendarOtherForm)aForm;
   		form.setActivityFlow(UIConstants.DELETE);
   		aRequest.setAttribute("state", "summary");
		
   		return aMapping.findForward(UIConstants.DELETE_SUCCESS);
   }
   
   /**
    * @roseuid 47A2282C01A2
    */
   public ActionForward reschedule(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
   		CSCalendarOtherForm form = (CSCalendarOtherForm)aForm;
   	
   		String eventLoaded = aRequest.getParameter("eventLoaded");
   		if(!"true".equals(eventLoaded)) {
	   		
	   		
			CSOtherResponseEvent selectedEvent = null;
			for(Iterator iter = form.getEvents().iterator();iter.hasNext();) {
				CSOtherResponseEvent event = (CSOtherResponseEvent)iter.next();
				if(event.getEventId().equals(form.getSelectedEventId())) {
					selectedEvent = event;
				}
			}
			
			if(selectedEvent != null) {
				form.populate(selectedEvent);
				if (selectedEvent.getSuperviseeId()!=null){
					instantiateHeaderForm(selectedEvent.getSuperviseeId(), aMapping, aRequest);						
				}
			}
   		}
		
		
		
   		form.setActivityFlow("reschedule");
   		return aMapping.findForward(UIConstants.SUCCESS);
   }
   
   
   public ActionForward view(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
	   	CSCalendarOtherForm form = (CSCalendarOtherForm)aForm;
	   	
		CSOtherResponseEvent selectedEvent = null;
		for(Iterator iter = form.getEvents().iterator();iter.hasNext();) {
			CSOtherResponseEvent event = (CSOtherResponseEvent)iter.next();
			if(event.getEventId().equals(form.getEventId())) {
				selectedEvent = event;
			}
		}
		
		if(selectedEvent != null) {			
			form.populate(selectedEvent);
			if (selectedEvent.getSuperviseeId()!=null){
				instantiateHeaderForm(selectedEvent.getSuperviseeId(), aMapping, aRequest);						
			}	
		}
		
			
			
		form.setActivityFlow(UIConstants.VIEW);
   		return aMapping.findForward(UIConstants.VIEW_SUCCESS);
   }
   
   public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
  		CSCalendarOtherForm form = (CSCalendarOtherForm)aForm;
  		
  		form.setActivityFlow("enterMultipleResults");
  		List filteredList = new ArrayList();
  		for(Iterator iter = form.getEvents().iterator();iter.hasNext();) {
			CSOtherResponseEvent event = (CSOtherResponseEvent)iter.next();
			if(event.getOutcome().equals(PDCodeTableConstants.OTHER_AND_FV_OUTCOME_SCHEDULED)) {
				filteredList.add(event);
			}
		}
  		if(filteredList != null){
  			form.setFilteredEvents(filteredList);
  		}
  		
  		return aMapping.findForward(UIConstants.NEXT);
  }
   
   
   //instantiates and populates supervisee header form for subsequent page - only necessary in other events since some events are supervisee context and others are officer context
   public void instantiateHeaderForm(String spn, ActionMapping aMapping, HttpServletRequest aRequest) {
	   SuperviseeHeaderForm myHeaderForm = null;
		try {
			myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
					aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		} catch (GeneralFeedbackMessageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		myHeaderForm.setSuperviseeId(spn);
		UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);	   
   }
}
