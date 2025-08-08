//Source file: C:\\PROGRA~1\\IBM\\SQLLIB\\bin\\ui\\supervision\\cscdcalendar\\action\\DisplayCSOtherEventSummaryAction.java

package ui.supervision.cscdcalendar.otherEvent.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;


public class DisplayCSOtherEventSummaryAction extends JIMSBaseAction {
   
   /**
    * @roseuid 479A0F0A0348
    */
   public DisplayCSOtherEventSummaryAction() {
    
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
    * @roseuid 47A2286D0329
    */
   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
   		aRequest.setAttribute("state","summary");
   		return aMapping.findForward(UIConstants.NEXT);
   }
   
   /**
    * @roseuid 47A228770193
    */
   public ActionForward nextResults(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
   	return aMapping.findForward(null);
   }
   
   /**
    * @roseuid 47A228B8026D
    */
   public ActionForward nextReschedule(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
   	return aMapping.findForward(null);
   }
   
   /**
    * @roseuid 47A228C50319
    */
   public ActionForward nextDelete(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
   	return aMapping.findForward(null);
   }
}
