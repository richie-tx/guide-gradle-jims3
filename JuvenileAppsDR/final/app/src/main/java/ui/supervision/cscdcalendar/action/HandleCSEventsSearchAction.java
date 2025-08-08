package ui.supervision.cscdcalendar.action;

import mojo.km.utilities.MessageUtil;
import naming.PDConstants;
import org.apache.struts.action.ActionForward;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import messaging.administercaseload.GetInOutActivityEvent;
import org.apache.struts.action.ActionMapping;
import ui.exception.GeneralFeedbackMessageException;
import java.util.Date;
import naming.UIConstants;
import messaging.cscdcalendar.GetCSEventsReportEvent;
import naming.CSEventsReportConstants;
import mojo.km.utilities.DateUtil;
import ui.supervision.cscdcalendar.form.CSCalendarOtherForm;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import ui.action.JIMSBaseAction;
import org.apache.struts.action.ActionForm;
import messaging.administercaseload.reply.CaseAssignInOutResponseEvent;
import ui.supervision.cscdcalendar.form.CSCalendarFVForm;
import ui.supervision.cscdcalendar.form.CSEventsSearchForm;
import javax.servlet.http.HttpServletResponse;
import messaging.cscdcalendar.reply.CSEventsReportReponseEvent;
import ui.supervision.cscdcalendar.CSEventsReportUIHelper;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import ui.supervision.administercaseload.form.OfficerCaseload;
import java.util.List;
import ui.supervision.cscdcalendar.form.CSCalendarOVForm;

/**
 */
public class HandleCSEventsSearchAction extends JIMSBaseAction
{
	/**
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.submit", "submit");
		keyMap.put("button.view", "view");
		keyMap.put("button.viewInOutActivity", "viewInOutActivity");
		keyMap.put("", "displaySearch");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @throws GeneralFeedbackMessageException
	 * @return 
	 */
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSEventsSearchForm csEventsSearchForm = (CSEventsSearchForm) aForm;
		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm) this.getSessionForm(aMapping, aRequest,
				UIConstants.CS_CASE_ASSIGNMENT_FORM, true);
		//		validate - start date should be <= end date
		Date startDate = DateUtil.stringToDate(csEventsSearchForm.getStartDateStr(), DateUtil.DATE_FMT_1);
		Date endDate = DateUtil.stringToDate(csEventsSearchForm.getEndDateStr(), DateUtil.DATE_FMT_1);
		int result = DateUtil.compare(startDate, endDate, DateUtil.DATE_FMT_1);
		if (result > 0)
		{
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Start date should be less than end date");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		//		to query the CS Calendar Events
		OfficerCaseload officerCaseload = caseAssignmentForm.getOfficerCaseload();
		String positionId = officerCaseload.getSelectedOfficerId();
		String selectedOfficerNameNPosition = officerCaseload.getSelectedOfficerName();
		if (positionId == null || positionId.length() <= 0)
		{
			positionId = officerCaseload.getSelectedSupervisorId();
			selectedOfficerNameNPosition = officerCaseload.getSelectedOfficerName();
		}
		csEventsSearchForm.setPositionId(positionId);
		csEventsSearchForm.setSelectedOfficerNameNPosition(selectedOfficerNameNPosition);
		GetCSEventsReportEvent requestEvent = CSEventsReportUIHelper.getCSEventsReportEvent(csEventsSearchForm);
		List responseEvtsList = this.postRequestListFilter(requestEvent, CSEventsReportReponseEvent.class);
		CSEventsReportUIHelper.convertResponseEventToBean(csEventsSearchForm, responseEvtsList);
		return aMapping.findForward(UIConstants.SEARCH_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @throws GeneralFeedbackMessageException
	 * @return 
	 */
	public ActionForward viewInOutActivity(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {
        		CSEventsSearchForm csEventsSearchForm = (CSEventsSearchForm)aForm;
        			//retrieve staff position id
        		CaseAssignmentForm caseAssignmentForm = (CaseAssignmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CS_CASE_ASSIGNMENT_FORM, true);
        		OfficerCaseload officerCaseload = caseAssignmentForm.getOfficerCaseload();
        		String positionId = null;
        		if (officerCaseload.getSelectedOfficerId() == null
        				|| !officerCaseload.getSelectedOfficerId().equals(PDConstants.BLANK)){
        			positionId = officerCaseload.getSelectedOfficerId();
        		} else {
        			positionId = officerCaseload.getSelectedSupervisorId();
        		} 
        		//retrieve officer name
        		GetLightCSCDStaffForUserEvent ev = new GetLightCSCDStaffForUserEvent();
        		ev.setStaffPositionId(positionId);
        		ev.setOfficerNameNeeded(true);
        		LightCSCDStaffResponseEvent staffResponseEvent = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(ev, LightCSCDStaffResponseEvent.class);
        		if(staffResponseEvent != null){
        			csEventsSearchForm.setOfficerName(staffResponseEvent.getOfficerNameQualifiedByPosition());
        		}
        			//validate - start date should be <= end date
        		Date startDate = DateUtil.stringToDate(csEventsSearchForm.getStartDateStr(), DateUtil.DATE_FMT_1);
        		Date endDate = DateUtil.stringToDate(csEventsSearchForm.getEndDateStr(), DateUtil.DATE_FMT_1);
        		int result = DateUtil.compare(startDate, endDate, DateUtil.DATE_FMT_1);
        		if(result > 0)
        		{
        			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY, "Start date should be less than end date");
        			return aMapping.findForward(UIConstants.FAILURE);
        		}
        			//Query the in/out activity
        		GetInOutActivityEvent in_out_activity_event = new GetInOutActivityEvent();
        		in_out_activity_event.setBeginDate(startDate);
        		in_out_activity_event.setEndDate(endDate);
        		in_out_activity_event.setAssignStaffPositionId(positionId);
        			//retrieve in/out activity
        		List<CaseAssignInOutResponseEvent>
        			in_out_responses = 
        				MessageUtil.postRequestListFilter(in_out_activity_event, 
        							CaseAssignInOutResponseEvent.class);
        			//convert responses to bean objects to display on form
        		CSEventsReportUIHelper.convertInOutActivityResponseEventToBean(
        									csEventsSearchForm, in_out_responses);	
        			//display in/out JSP
        		return aMapping.findForward("viewInOutActivity");
    }

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @throws GeneralFeedbackMessageException
	 * @return 
	 */
	public ActionForward view(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		CSEventsSearchForm csEventsSearchForm = (CSEventsSearchForm) aForm;
		String calendarCategory = csEventsSearchForm.getCalendarCategory();
		if ((CSEventsReportConstants.CS_CALENDAR_CATEGORY_FIELD_VISITS).equalsIgnoreCase(calendarCategory))
		{
			String fieldVisitId = CSEventsReportUIHelper.getFieldVisitId(csEventsSearchForm.getSelectedCSEventId());
			if (fieldVisitId != null)
			{
				CSCalendarFVForm fvForm = (CSCalendarFVForm) this.getSessionForm(aMapping, aRequest,
						UIConstants.CS_CALENDAR_FIELD_VISIT_FORM, true);
				fvForm.clear();
				CSEventsReportUIHelper.initializeFieldVisitForm(csEventsSearchForm, fvForm, fieldVisitId);
				CSEventsReportUIHelper.populateFVIternary(fvForm);
				return aMapping.findForward(UIConstants.VIEW_FIELD_VST_DETAILS_SUCCESS);
			}
			else
			{
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,
						"Failed to retrieve the selected Field Visit Details");
				return aMapping.findForward(UIConstants.VIEW_FAILURE);
			}
		}
		else if ((CSEventsReportConstants.CS_CALENDAR_CATEGORY_OFFICE_VISITS).equalsIgnoreCase(calendarCategory))
		{
			CSCalendarOVForm ovForm = (CSCalendarOVForm) this.getSessionForm(aMapping, aRequest,
					UIConstants.CS_CALENDAR_OFFICE_VISIT_FORM, true);
			ovForm.clear();
			String selectedCSEventTypeId = CSEventsReportUIHelper.initializeOfficeVisitForm(csEventsSearchForm, ovForm);
			CSEventsReportUIHelper.populateOfficeVisitsForPositionId(ovForm);
			if ((CSEventsReportConstants.CS_CALENDAR_CATEGORY_OFFICE_VISITS).equalsIgnoreCase(selectedCSEventTypeId))
			{
				return aMapping.findForward(UIConstants.VIEW_OFFICE_VST_DETAILS_SUCCESS);
			}
			else
			{
				return aMapping.findForward(UIConstants.VIEW_GROUP_VST_DETAILS_SUCCESS);
			}
		}
		else if ((CSEventsReportConstants.CS_CALENDAR_CATEGORY_OTHER_EVTS).equalsIgnoreCase(calendarCategory))
		{
			CSCalendarOtherForm otherEvtForm = (CSCalendarOtherForm) this.getSessionForm(aMapping, aRequest,
					UIConstants.CS_CALENDAR_OTHER_EVT_FORM, true);
			otherEvtForm.clear();
			CSEventsReportUIHelper.initializeOtherEventsForm(csEventsSearchForm, otherEvtForm);
			CSEventsReportUIHelper.populateOtherEventsForPositionId(otherEvtForm);
			return aMapping.findForward(UIConstants.VIEW_OTHER_EVTS_DETAILS_SUCCESS);
		}
		return aMapping.findForward(UIConstants.VIEW_FAILURE);
	}

	public ActionForward displaySearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		return null;
	}
}
