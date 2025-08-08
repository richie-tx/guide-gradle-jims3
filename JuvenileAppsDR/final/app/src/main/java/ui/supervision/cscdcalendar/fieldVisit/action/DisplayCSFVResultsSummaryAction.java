/*
 * Created on Feb 8, 2008
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
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayCSFVResultsSummaryAction extends JIMSBaseAction {

	protected void addButtonMapping(Map keyMap) {   	
		keyMap.put("button.next", "next");
   }
   
   /**
    * @roseuid 47A22E1201F0
    */
   public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) {
   		CSCalendarFVForm form = (CSCalendarFVForm) aForm;
   		return aMapping.findForward(UIConstants.SUCCESS);
   }

}
