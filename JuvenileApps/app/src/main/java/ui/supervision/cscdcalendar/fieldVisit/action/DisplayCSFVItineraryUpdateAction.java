//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\ui\\supervision\\cscdcalendar\\action\\DisplayCSFVItenaryUpdateAction.java

package ui.supervision.cscdcalendar.fieldVisit.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.cscdcalendar.form.CSCalendarFVForm;


public class DisplayCSFVItineraryUpdateAction extends JIMSBaseAction {
   
   /**
    * @roseuid 479A0F0A000C
    */
   public DisplayCSFVItineraryUpdateAction()  {
    
   }
   
   protected void addButtonMapping(Map keyMap) {   		
		keyMap.put("button.create", "create");
		keyMap.put("button.update", "update");
   }
   
   /**
    * @roseuid 47A22EE30125
    */
   public ActionForward create(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
   		return aMapping.findForward(UIConstants.SUCCESS);
   }
   
   public ActionForward update(
   		ActionMapping aMapping, 
		ActionForm aForm, 
		HttpServletRequest aRequest, 
		HttpServletResponse aResponse) {
   		
   		CSCalendarFVForm form = (CSCalendarFVForm)aForm;
   		form.setActivityFlow("updateItinerary");
		return aMapping.findForward(UIConstants.SUCCESS);
   }
   
}
