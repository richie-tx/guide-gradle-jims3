/*
 * Created on Mar 13, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
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

/**
 * @author awidjaja
 *
 * This action serves the purpose of forwarding to another action,
 * which does not have the same form as current action.
 *
 */
public class DisplayCSFVCaseloadSearchAction extends JIMSBaseAction {

	public ActionForward next(ActionMapping aMapping, 
			ActionForm aForm, 
			HttpServletRequest aRequest, 
			HttpServletResponse aResponse) {
		
		CSCalendarFVForm form = (CSCalendarFVForm)aForm;
		
		//reschedule can't reassign the event to another supervisee
		if("reschedule".equals(form.getActivityFlow()) && 
				form.getCurrentFieldVisit().getSuperviseeId() != null &&
				form.getCurrentFieldVisit().getSuperviseeId().length() > 0) {
			return aMapping.findForward("rescheduleSuccess");
		}
		else {
			return aMapping.findForward(UIConstants.SUCCESS);
		}
		
	}
	
	
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		
		
	}

}
