//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\ui\\supervision\\cscdcalendar\\action\\HandleCSEventTypeAction.java

package ui.supervision.cscdcalendar.action;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdcalendar.reply.CSEventTypeResponseEvent;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.cscdcalendar.form.CSCalendarDisplayForm;
import ui.supervision.cscdcalendar.form.CSCalendarFVForm;
import ui.supervision.cscdcalendar.form.CSCalendarOVForm;
import ui.supervision.cscdcalendar.form.CSCalendarOtherForm;


/**
 * @author awidjaja
 * This class will control the navigation flow to various 
 * sub-flows (i.e. field visit, office visit, other events)
 * depending on the user's selection of event type. 
 */
public class HandleCSEventTypeAction extends JIMSBaseAction {
   
	/**
    	* @roseuid 479A0F0B0116
    	*/
	public HandleCSEventTypeAction() {
    
   	}
   
   
   
   	/**
   	 * @roseuid 47A227AE0099
   	 */
	public ActionForward next(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{		
		CSCalendarDisplayForm form =
			(CSCalendarDisplayForm)aForm;
		CSEventTypeResponseEvent selectedEventType = null;
			
		// Clear Officer and Supervisor names
		form.setSelectedSupervisorName( "" );
		form.setSelectedOfficerName( "" );
		form.setSelectedOfficerId( form.getPositionId());
		
		for(Iterator eventTypeIter=form.getEventTypeList().iterator();
				eventTypeIter.hasNext();) {
			selectedEventType = (CSEventTypeResponseEvent)eventTypeIter.next();
			if(selectedEventType.getEventType().equalsIgnoreCase(
					form.getSelectedEventTypeCd())) {
				break;
			}
		}
		
		if(selectedEventType != null) {
			
			if(PDCodeTableConstants.CS_FIELD_VISIT_CATEGORY.equalsIgnoreCase(
				selectedEventType.getCategoryCode())) {
			
			
				//get CSCalendarOtherForm from session to initialize some data
				CSCalendarFVForm fvForm = 
					(CSCalendarFVForm)getSessionForm(
							aMapping, aRequest, 
							"csCalendarFVForm", true);
				fvForm.clear();
				
				//This affects caseloadSearch.jsp since the jsp is being shared
				//with multiple sub-flows, so the activity flow will indicate
				//whether it's create or add attendees flow.
				form.setActivityFlow(UIConstants.CREATE);
				
				//Transfer data from CSCalendarDisplayForm -> CSCalendarOtherForm
				fvForm.setEventTypeCd(form.getSelectedEventTypeCd());
				fvForm.setEventDate(form.getEventDate());
				fvForm.setPositionId(form.getPositionId());
				fvForm.setContext(form.getContext());
				fvForm.getCurrentFieldVisit().setFieldVisitDate(form.getEventDate());
				fvForm.setSuperviseeId(form.getSuperviseeId());
				fvForm.setJobTitleCd(form.getJobTitleCd());
				
				return aMapping.findForward("fieldVisitSuccess");
				
			} else if(PDCodeTableConstants.CS_OTHER_EVENT_CATEGORY.equalsIgnoreCase(
					selectedEventType.getCategoryCode())) {
				
				//get CSCalendarOtherForm from session to initialize some data
				CSCalendarOtherForm otherForm = 
					(CSCalendarOtherForm)getSessionForm(
							aMapping, aRequest, 
							"csCalendarOtherForm", true);
				otherForm.clear();
				
				//Transfer data from CSCalendarDisplayForm -> CSCalendarOtherForm
				otherForm.setEventTypeCd(form.getSelectedEventTypeCd());
				otherForm.setEventTypeDescription(selectedEventType.getDescription()) ;
				otherForm.setEventDate(form.getEventDate());
				otherForm.setOtherEventTypeName(form.getOtherEventTypeName());
				otherForm.setPositionId(form.getPositionId());
				otherForm.setContext(form.getContext());
				otherForm.setSuperviseeId(form.getSuperviseeId());
				
				return aMapping.findForward("otherEventSuccess");
				
				
			} else if(PDCodeTableConstants.CS_OFFICE_VISIT_CATEGORY.equalsIgnoreCase(
					selectedEventType.getCategoryCode())) {
				
				//This affects caseloadSearch.jsp since the jsp is being shared
				//with multiple sub-flows, so the activity flow will indicate
				//whether it's create or add attendees flow.
				form.setActivityFlow(UIConstants.CREATE);
				
				//get CSCalendarOtherForm from session to initialize some data
				CSCalendarOVForm ovForm = 
					(CSCalendarOVForm)getSessionForm(
							aMapping, aRequest, 
							"csCalendarOVForm", true);
				ovForm.clear();
				
				//ransfer data from CSCalendarDisplayForm -> CSCalendarOtherForm
				ovForm.setEventTypeCd(form.getSelectedEventTypeCd());
				ovForm.setEventDate(form.getEventDate());
				ovForm.setPositionId(form.getPositionId());
				ovForm.setContext(form.getContext());
				ovForm.setActivityFlow(UIConstants.CREATE);
				ovForm.setSuperviseeId(form.getSuperviseeId());
                				
				if(PDCodeTableConstants.CS_GROUP_OFFICE_VISIT_TYPE.equalsIgnoreCase(
						selectedEventType.getEventType())) {
						CSCalendarFVForm fvForm = 
							(CSCalendarFVForm)getSessionForm(
									aMapping, aRequest, 
									"csCalendarFVForm", true);
						fvForm.clear();
						form.setQuadrantSearch("");  // set Search By to default

					return aMapping.findForward("groupOfficeVisitSuccess");
					
				} else if(PDCodeTableConstants.CS_OFFICE_VISIT_TYPE.equalsIgnoreCase(
						selectedEventType.getEventType())) {
						CSCalendarFVForm fvForm = 
							(CSCalendarFVForm)getSessionForm(
									aMapping, aRequest, 
									"csCalendarFVForm", true);
						fvForm.clear();
						if(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION.equals(form.getContext())) { 
							return aMapping.findForward("officeVisitSuccess");
						} else {
							return aMapping.findForward("superviseeOfficeVisitSuccess");
					}
					
				} 
				
				
			} else {
				//may be return to the same page with an error message?
				return null;
			}
		}
			
		//may be return to the same page with an error message?
		return null;
		
		

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
	
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next","next");
		keyMap.put("button.back","back");
		keyMap.put("button.cancel","cancel");
	}
}
