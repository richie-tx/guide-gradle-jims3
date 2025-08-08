/*
 * Created on Mar 11, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.cscdcalendar.officeVisit.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administersupervisee.UIAdministerSuperviseeHelper;
import ui.supervision.cscdcalendar.form.CSCalendarOVForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;
import ui.supervision.supervisee.form.SuperviseeForm;

/**
 * @author awidjaja
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayCSOVDetailsAction extends JIMSBaseAction {

	public ActionForward next(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCalendarOVForm ovForm = (CSCalendarOVForm)aForm;
		ovForm.setConfirmMsg("");
		aRequest.setAttribute("status", UIConstants.SUMMARY);
		
		//load supervisee form to be displayed in JSP
		SuperviseeForm superviseeForm = (SuperviseeForm)getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_FORM,true);
		superviseeForm.setSuperviseeId(ovForm.getCurrentOfficeVisit().getSuperviseeId());
		UIAdministerSuperviseeHelper.populateSuperviseeForm(superviseeForm);
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward link(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSCalendarOVForm form = (CSCalendarOVForm)aForm;
		
		form.loadSelectedEvent(form.getSelectedEventId());
		//load supervisee header form to be displayed in JSP
		SuperviseeHeaderForm myHeaderForm = 
			(SuperviseeHeaderForm)getSessionForm(
					aMapping, aRequest,
					UIConstants.SUPERVISEE_HEADER_FORM,true);
		myHeaderForm.setSuperviseeId(form.getCurrentOfficeVisit().getSuperviseeId());
		UICommonSupervisionHelper.populateSuperviseeHeaderForm(myHeaderForm);
		
		//load supervisee form to be displayed in JSP
		SuperviseeForm superviseeForm = (SuperviseeForm)getSessionForm(aMapping, aRequest, UIConstants.SUPERVISEE_FORM,true);
		superviseeForm.setSuperviseeId(form.getCurrentOfficeVisit().getSuperviseeId());
		UIAdministerSuperviseeHelper.populateSuperviseeForm(superviseeForm);
		
		form.setActivityFlow(UIConstants.VIEW);
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next","next");
		keyMap.put("button.link","link");
	}
	
	

}
