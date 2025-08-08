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

import messaging.contact.to.PhoneNumberBean;
import messaging.cscdcalendar.SaveCSOfficeVisitEvent;
import messaging.error.reply.ErrorResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CSEventControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.PDConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.StringUtil;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.cscdcalendar.CSOVBean;
import ui.supervision.cscdcalendar.form.CSCalendarOVForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

/**
 * @author awidjaja
 * 
 *         TODO To change the template for this generated type comment go to
 *         Window - Preferences - Java - Code Style - Code Templates
 */
public class SubmitCSOVUpdateAction extends JIMSBaseAction {

	public ActionForward finish(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {

		CompositeResponse response = postRequestEvent(setSaveEvent(aMapping, aForm, aRequest)); 

		ErrorResponseEvent error = 
			(ErrorResponseEvent) MessageUtil.filterComposite(response, ErrorResponseEvent.class);

		if (error != null) {
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,	error.getMessage());
			aRequest.setAttribute("state", "summary");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		CSCalendarOVForm ovForm = (CSCalendarOVForm) aForm;
		ovForm.setAltFlow("");
		if(!UIConstants.DELETE.equals(ovForm.getActivityFlow())){
		aRequest.setAttribute("state", UIConstants.CONFIRM);
		}		
		ovForm.setConfirmMsg("Office Visit successfully saved");
		ovForm.setEventDate(ovForm.getCurrentOfficeVisit().getEventDate());
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	public ActionForward finishAndScheduleNext(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
			throws GeneralFeedbackMessageException {

		CompositeResponse response = postRequestEvent(setSaveEvent(aMapping, aForm, aRequest));

		ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil
				.filterComposite(response, ErrorResponseEvent.class);

		if (error != null) {
			sendToErrorPage(aRequest, JIMSBaseAction.GENERIC_ERROR_MSG_KEY,	error.getMessage());
			aRequest.setAttribute("state", "summary");
			return aMapping.findForward(UIConstants.FAILURE);
		}
		CSCalendarOVForm ovForm = (CSCalendarOVForm) aForm;
		ovForm.setConfirmMsg("Office Visit successfully saved");
		ovForm.setAltFlow(UIConstants.YES);
		ovForm.setEventDate(null);
		return aMapping.findForward("createOfficeVisit");
	}


	private SaveCSOfficeVisitEvent setSaveEvent(ActionMapping aMapping,	ActionForm aForm,
			HttpServletRequest aRequest)
			throws GeneralFeedbackMessageException{
		
		CSCalendarOVForm ovForm = (CSCalendarOVForm) aForm;
		
		SuperviseeHeaderForm myHeaderForm = (SuperviseeHeaderForm) getSessionForm(
				aMapping, aRequest, UIConstants.SUPERVISEE_HEADER_FORM, true);
		
// supervieeId only needed for finish and schedule next flow
		if (ovForm.getSuperviseeId() == null || ovForm.getSuperviseeId().equals("")){
			ovForm.setSuperviseeId(myHeaderForm.getSuperviseeId());
		}
		
		SaveCSOfficeVisitEvent saveEvent = (SaveCSOfficeVisitEvent) EventFactory
		.getInstance(CSEventControllerServiceNames.SAVECSOFFICEVISIT);
		
		CSOVBean currentOfficeVisit = ovForm.getCurrentOfficeVisit();
		if (UIConstants.CREATE.equals(ovForm.getActivityFlow())) {
			saveEvent.setCreate(true);
		} else if (UIConstants.UPDATE.equals(ovForm.getActivityFlow())) {
			saveEvent.setUpdate(true);
			saveEvent.setEventId(currentOfficeVisit.getEventId());
		} else if ("enterResults".equals(ovForm.getActivityFlow())) {
			saveEvent.setResults(true);
			saveEvent.setEventId(currentOfficeVisit.getEventId());
			saveEvent.setResultPositionId(ovForm.getPositionId());
		} else if ("reschedule".equals(ovForm.getActivityFlow())) {
			saveEvent.setReschedule(true);
			saveEvent.setRescheduleOVId(currentOfficeVisit.getEventId());
		} else if (UIConstants.DELETE.equals(ovForm.getActivityFlow())) {
			saveEvent.setDelete(true);
			saveEvent.setDeleteOVId(currentOfficeVisit.getEventId());
			if(currentOfficeVisit.getStatus()!= null && currentOfficeVisit.getStatus().equalsIgnoreCase(PDCodeTableConstants.CS_EVENT_STATUS_CLOSE)){
				aRequest.setAttribute("state", UIConstants.SUCCESS);	
			}
			else{
				aRequest.setAttribute("state", UIConstants.CONFIRM);	
			}
		}

		// saveEvent.setCreatedBy();
		saveEvent.setEventDate(currentOfficeVisit.getEventDate());
		saveEvent.setEventName(currentOfficeVisit.getEventName());
		if(!StringUtil.isEmpty(currentOfficeVisit.getStartTime())){
			saveEvent.setStartTime(currentOfficeVisit.getStartTime() + " " + currentOfficeVisit.getAMPMId1());
			}
			else{
				saveEvent.setStartTime(currentOfficeVisit.getStartTime());
			}
		
		if(!StringUtil.isEmpty(currentOfficeVisit.getEndTime())){
			saveEvent.setEndTime(currentOfficeVisit.getEndTime() + " " + currentOfficeVisit.getAMPMId2());
			}
			else{
				saveEvent.setEndTime(currentOfficeVisit.getEndTime());
			}
		
		saveEvent.setSuperviseeId(currentOfficeVisit.getSuperviseeId());
		PhoneNumberBean phoneBean = currentOfficeVisit.getAltPhone();
		if (phoneBean.getAreaCode() != null && !phoneBean.getAreaCode().equals(PDConstants.BLANK) 
				&& phoneBean.getPrefix() != null && !phoneBean.getPrefix().equals(PDConstants.BLANK) 
				&& phoneBean.getFourDigit() != null && !phoneBean.getFourDigit().equals(PDConstants.BLANK)){
			saveEvent.setPhonenum(phoneBean.getPhoneNumber());
		}
		saveEvent.setPurpose(currentOfficeVisit.getPurpose());
		saveEvent.setEventType(currentOfficeVisit.getEventTypeCd());

		if (PDCodeTableConstants.CS_EVENT_CONTEXT_SUPERVISEE.equals(ovForm.getContext())) {
			if (myHeaderForm.getOfficerPositionId() != null) {
				saveEvent.setPositionId(myHeaderForm.getOfficerPositionId());
			}
		} else {
			saveEvent.setPositionId(currentOfficeVisit.getPositionId());
		}
		saveEvent.setAssignStaffPos_Id(myHeaderForm.getOfficerPositionId());
		saveEvent.setOutcome(currentOfficeVisit.getOutcomeCd());
		saveEvent.setNarrative(currentOfficeVisit.getNarrative());
		saveEvent.setRescheduleReason(currentOfficeVisit.getRescheduleReason());

		return saveEvent;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.finishAndScheduleNext", "finishAndScheduleNext");
		keyMap.put("button.next", "finishAndScheduleNext");  //link from handleCSOVSelection
	}
}