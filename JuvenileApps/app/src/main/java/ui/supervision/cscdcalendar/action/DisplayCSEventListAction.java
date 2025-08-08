/*
 * Created on May 1, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.cscdcalendar.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.PDCodeTableConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayCSEventListAction extends JIMSBaseAction {

	public ActionForward displayEventList(
			ActionMapping aMapping, 
			ActionForm aForm, 
			HttpServletRequest aRequest, 
			HttpServletResponse aResponse) {
		
		String returnString = "";
		String eventCategory = aRequest.getParameter("eventCategory");
		String calendarDate = aRequest.getParameter("calendarDate");
		String context = aRequest.getParameter("context");
		String superviseeId = aRequest.getParameter("superviseeId");
		String positionId = aRequest.getParameter("positionId");
		
		aRequest.setAttribute("superviseeId", superviseeId);
		aRequest.setAttribute("positionId", positionId);
		aRequest.setAttribute("calendarDate", calendarDate);
		aRequest.setAttribute("context", context);
		
		
		if(PDCodeTableConstants.CS_OTHER_EVENT_CATEGORY.equals(eventCategory)) {
			if(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION.equals(context)) {
				returnString = "otherEventSuccessPosition";
			} else if(PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(context)) {
				returnString = "otherEventSuccessSupervisee";
			}
			
		} else if(PDCodeTableConstants.CS_FIELD_VISIT_CATEGORY.equals(eventCategory)) {
			if(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION.equals(context)) {
				returnString = "fieldVisitSuccessPosition";
			} else if(PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(context)) {
				returnString = "fieldVisitSuccessSupervisee";
			}
		} else if(PDCodeTableConstants.CS_OFFICE_VISIT_CATEGORY.equals(eventCategory)) {
			if(PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION.equals(context)) {
				returnString = "officeVisitSuccessPosition";
			} else if(PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(context)) {
				returnString = "officeVisitSuccessSupervisee";
			}
		}
		
		return aMapping.findForward( returnString );
		
	}
	   	
		
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link","displayEventList");

	}

}
