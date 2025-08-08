/*
 * Created on Apr 8, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.cscdcalendar.supervisee.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import mojo.km.utilities.MessageUtil;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.cscdcalendar.form.CSCalendarDisplayForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayCSSuperviseeCalendarAction extends JIMSBaseAction {

	protected void addButtonMapping(Map keyMap) {   		
		keyMap.put("button.link", "link");
	}
	
	public ActionForward link(
			ActionMapping aMapping, 
			ActionForm aForm, 
			HttpServletRequest aRequest, 
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException 
	{
		CSCalendarDisplayForm form = (CSCalendarDisplayForm)aForm;
		form.clear();
		
		
		String spnStr=(String)aRequest.getParameter("superviseeId");
		aRequest.setAttribute("superviseeId", "");
		if (spnStr == null || spnStr.equals("")) {
			SuperviseeHeaderForm myHeaderForm=(SuperviseeHeaderForm)getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);			
			spnStr = myHeaderForm.getSuperviseeSpn();
		}
		if (spnStr == null || spnStr.equals("")) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
					"Not able to find supervisee information"));
			saveErrors(aRequest, errors);
			return aMapping.findForward(UIConstants.SUCCESS);
		}
	
// spn, defendantId and superviseeId are all same value
		while (spnStr.length() < 8) {
			spnStr = "0" + spnStr;
		}
		
		//loading the supervisee header form so that the jsp page will have it
		SuperviseeHeaderForm superviseeHeaderForm = 
			(SuperviseeHeaderForm)getSessionForm(
					aMapping, aRequest, "superviseeHeaderForm", true); 
		
		superviseeHeaderForm.setSuperviseeId(spnStr);
		UICommonSupervisionHelper.populateSuperviseeHeaderForm(superviseeHeaderForm);
		
		
		form.setSuperviseeId(spnStr);
		form.setContext(PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE);
    	
   		if(!UIConstants.CSC.equals(SecurityUIHelper.getUserAgencyId())) {
   			return aMapping.findForward(UIConstants.SUCCESS);
   		}
   		
   		//Get StaffPositionId so that it can be saved (event will appear on staff calendar event too)
   		GetLightCSCDStaffForUserEvent gEvent = new GetLightCSCDStaffForUserEvent();
   		gEvent.setLogonId(SecurityUIHelper.getLogonId());
   		LightCSCDStaffResponseEvent resp = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(gEvent, LightCSCDStaffResponseEvent.class);

   		if(resp != null){
   			form.setPositionId(resp.getStaffPositionId());
   		}    		
		return aMapping.findForward(UIConstants.SUCCESS);
	}

}
