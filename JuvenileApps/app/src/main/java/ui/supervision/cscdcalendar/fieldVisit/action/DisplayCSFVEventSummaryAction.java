//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\ui\\supervision\\cscdcalendar\\action\\DisplayCSFVEventSummaryAction.java

package ui.supervision.cscdcalendar.fieldVisit.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.cscdcalendar.reply.CSFieldVisitResponseEvent;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.supervision.cscdcalendar.form.CSCalendarFVForm;


public class DisplayCSFVEventSummaryAction extends JIMSBaseAction {
   
   /**
    * @roseuid 479A0F09023F
    */
   public DisplayCSFVEventSummaryAction() {
    
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
		keyMap.put("button.next", "next");
		keyMap.put("button.back","back");
		keyMap.put("button.cancel","cancel");
   }
   
   /**
    * @roseuid 47A22E5C03A6
    */
   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
   		CSCalendarFVForm form = (CSCalendarFVForm)aForm;
// 6-14-10 found state and streettype descriptions not being set while testing defect 65901   		
   		if (form.getCurrentFieldVisit().getAlternateAddress().getStateCode() != null &&
   	   		!"".equals(form.getCurrentFieldVisit().getAlternateAddress().getStateCode()) ) {
   	   			form.getCurrentFieldVisit().getAlternateAddress().setState(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.STATE_ABBR, form.getCurrentFieldVisit().getAlternateAddress().getStateCode()));
   	   	} else {
   	   	form.getCurrentFieldVisit().getAlternateAddress().setState("");
   	   	}
   	   	if (form.getCurrentFieldVisit().getAlternateAddress().getStreetTypeCode() != null &&
   	   	   	!"".equals(form.getCurrentFieldVisit().getAlternateAddress().getStreetTypeCode()) ) {
   	   	   		form.getCurrentFieldVisit().getAlternateAddress().setStreetType(SimpleCodeTableHelper.getDescrByCode(PDCodeTableConstants.STREET_TYPE, form.getCurrentFieldVisit().getAlternateAddress().getStreetTypeCode()));
   	   	} else {
   	   		form.getCurrentFieldVisit().getAlternateAddress().setStreetType("");
   	   	}
   	   	if (StringUtils.isNotEmpty(form.getCurrentFieldVisit().getAlternatePhone().getPhoneNumber()) ) {
	   	   	List events = form.getEventsList();
	   	   	if (events != null && events.size() > 0) {
		   	   	for (int x = 0; x < events.size(); x++ ) {
		   	   		CSFieldVisitResponseEvent event = (CSFieldVisitResponseEvent) events.get(x);
		   	   		if (StringUtils.isNotEmpty(form.getCurrentFieldVisit().getFvEventId()) && form.getCurrentFieldVisit().getFvEventId().equals(event.getFvEventId())) {
		   	   			event.setAltPhone(form.getCurrentFieldVisit().getAlternatePhone().getPhoneNumber());
		   	   			break;
		   	   		}
		   	   	}
	   	   	}
	   	}
		
   		aRequest.setAttribute("status", "summary");
   		
   		return aMapping.findForward("next");	
   }
   
   /**
    * @roseuid 47A22E670210
    */
   public ActionForward nextResults(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
   	return aMapping.findForward("nextResults");
   }
   
   /**
    * @roseuid 47A22E7300D7
    */
   public ActionForward nextReschedule(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
   	return aMapping.findForward("nextReschedule");
   }
}
