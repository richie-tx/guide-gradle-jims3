/*
 * Created on Mar 31, 2008
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package ui.supervision.cscdcalendar.groupOfficeVisit.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetLightCSCDStaffForUserEvent;
import messaging.administercaseload.reply.CaseAssignmentResponseEvent;
import messaging.administercaseload.reply.LightCSCDStaffResponseEvent;
import messaging.contact.domintf.IName;
import messaging.contact.to.NameBean;
import messaging.cscdcalendar.GetCSOfficeVisitsEvent;
import messaging.cscdcalendar.reply.CSGroupOfficeVisitResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CSEventControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.StringUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.cscdcalendar.SuperviseeBean;
import ui.supervision.cscdcalendar.form.CSCalendarDisplayForm;
import ui.supervision.cscdcalendar.form.CSCalendarOVForm;

/**
 * @author awidjaja
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayCSGVSummaryAction extends JIMSBaseAction {

	public ActionForward next(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {
		CSCalendarOVForm form = (CSCalendarOVForm) aForm;

		CSCalendarDisplayForm displayForm = (CSCalendarDisplayForm) getSessionForm(
				aMapping, aRequest, "csCalendarDisplayForm", true);

		if (form.getActivityFlow().equals("addAttendees")) {
			form.getSuperviseeList().removeAll(form.getNewSuperviseeList());

			// add the newly selected superviseeId to the list
			String superviseeId = aRequest.getParameter("superviseeId");
			if (StringUtil.isEmpty(superviseeId)) {

				String[] superviseeIds = aRequest.getParameterValues("caseSuperviseeIds"); // if Caseload Search
				form.getNewSuperviseeList().clear();
				SuperviseeBean supervisee = null;

				if (superviseeIds != null && superviseeIds.length > 0) {
					for (String superviseId : superviseeIds) {

						for (int i = 0; i < displayForm.getDefendantsSupervised().size(); i++) {
							CaseAssignmentResponseEvent resp = (CaseAssignmentResponseEvent) displayForm.getDefendantsSupervised().get(i);
							if (resp.getDefendantId().equalsIgnoreCase(superviseId)) {
								supervisee = new SuperviseeBean();
								supervisee.setSpn(superviseId);
								supervisee.setName(resp.getDefendantName());
								if (supervisee.getName() == null && resp.getDefendantFullName() != null) {
									String[] nameStr = resp.getDefendantFullName().trim().split(",");
									IName aName = null;
									aName = new NameBean();
									aName.setLastName(nameStr[0].trim());
									aName.setFirstName(UIConstants.EMPTY_STRING);
									if (nameStr.length > 1){
										aName.setFirstName(nameStr[1].trim());
									}
									aName.setMiddleName(UIConstants.EMPTY_STRING);
									supervisee.setName( aName );
								}
								form.getNewSuperviseeList().add(supervisee);
								break;
							}
						}
					}
				}
				form.getSuperviseeList().addAll(form.getNewSuperviseeList());

			}

			else {
				SuperviseeBean supervisee = new SuperviseeBean();
				supervisee.setSpn(superviseeId);

				if (displayForm.getSearchBySPNResult() != null) // if SPN search
				// selected
				{
					supervisee.setName((IName) displayForm.getSearchBySPNResult().getDefendantName());
				}

				form.getNewSuperviseeList().clear();
				form.getNewSuperviseeList().add(supervisee);
				form.getSuperviseeList().addAll(form.getNewSuperviseeList());
			}
		}

		else if (UIConstants.CREATE.equals(form.getActivityFlow())) {

			// take priority over date being passed in through query string
			String calendarDate = aRequest.getParameter("calendardate");
			if (calendarDate != null) {
				Date date = new Date();
				date.setTime(Long.parseLong(calendarDate));
				form.setEventDate(date);
			}

			if (form.getEventDate() == null) {
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Event Date is missing.");
				return aMapping.findForward(UIConstants.SUCCESS);
			}

			// get positionId & context from displayForm

			form.setContext(displayForm.getContext());
			
			if (displayForm.getPositionId() != null && !"".equals(displayForm.getPositionId())) { 
				form.setPositionId(displayForm.getPositionId());
			} else {
		   		GetLightCSCDStaffForUserEvent gEvent = new GetLightCSCDStaffForUserEvent();
		   		gEvent.setLogonId(SecurityUIHelper.getLogonId());
		   		gEvent.setOfficerNameNeeded(false);
		   		LightCSCDStaffResponseEvent resp = (LightCSCDStaffResponseEvent) MessageUtil.postRequest(gEvent, LightCSCDStaffResponseEvent.class);
		   		if(resp != null){
		   			form.setPositionId(resp.getStaffPositionId());
		   		}
			}
			if(StringUtils.isNotEmpty(displayForm.getAgencyId())) {
				form.setAgencyId(displayForm.getAgencyId());
			}else{
				form.setAgencyId("");
			}

			GetCSOfficeVisitsEvent getOfficeVisits = (GetCSOfficeVisitsEvent) EventFactory.getInstance(CSEventControllerServiceNames.GETCSOFFICEVISITS);
			getOfficeVisits.setCurrentContext(form.getContext());
			getOfficeVisits.setEventDate(form.getEventDate());

			if (PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(displayForm.getContext())) {
				getOfficeVisits.setSuperviseeId(displayForm.getSuperviseeId());
			} else if (PDCodeTableConstants.CS_EVENT_CONTEXT_POSITION.equals(displayForm.getContext())) {
				getOfficeVisits.setPositionId(displayForm.getPositionId());
			}

			CompositeResponse response = postRequestEvent(getOfficeVisits);

			Collection groupOfficeVisits = MessageUtil.compositeToCollection(response, CSGroupOfficeVisitResponseEvent.class);

			List groupOfficeVisitList = new ArrayList();
			groupOfficeVisitList = (List) groupOfficeVisits;

			boolean nameExists = false;
			if (groupOfficeVisitList != null && groupOfficeVisitList.size() > 0) {
				if (form.getCurrentOfficeVisit() != null && form.getCurrentOfficeVisit().getEventName() != null) {
					Iterator i = groupOfficeVisitList.iterator();

					while (i.hasNext()) {
						CSGroupOfficeVisitResponseEvent resp = (CSGroupOfficeVisitResponseEvent) i.next();
						if (form.getCurrentOfficeVisit().getEventName().equalsIgnoreCase(resp.getEventName())) {
							nameExists = true;
							break;
						}
					}
				}
			}
			if (nameExists) {
				sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Name Exists. Please Enter Different Name");
				return aMapping.findForward(UIConstants.FAILURE);
			}
		}

		aRequest.setAttribute("state", "summary");

		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
	}
}