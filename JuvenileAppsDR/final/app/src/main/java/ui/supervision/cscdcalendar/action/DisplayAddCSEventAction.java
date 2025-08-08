//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\ui\\supervision\\cscdcalendar\\action\\DisplayAddCSEventAction.java

package ui.supervision.cscdcalendar.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.supervision.cscdcalendar.form.CSCalendarDisplayForm;


public class DisplayAddCSEventAction extends JIMSBaseAction {
   
   /**
    * @roseuid 479A0F0900E7
    */
   public DisplayAddCSEventAction() {
    
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
		keyMap.put("button.addNewEvent","addNewEvent");
		keyMap.put("button.back","back");
		keyMap.put("button.cancel","cancel");
	}
   
   /**
    * @roseuid 47A2275B03E4
    */
   public ActionForward addNewEvent(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
   		
   		CSCalendarDisplayForm form = (CSCalendarDisplayForm)aForm;
   		if (form.getContext().equals(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION)){	
   			//this clears the spn in case it was cached in the form - adding an event from the officer's calendar should never already have a spn.
   			form.setSuperviseeId( "" );
   			form.setSelectedOfficerId( "" );
   		}
   		form.reset();
   		form.setEventDate(null);
   		form.setActivityFlow(UIConstants.CREATE);
   		
   		return aMapping.findForward(UIConstants.SUCCESS);
   }
}
