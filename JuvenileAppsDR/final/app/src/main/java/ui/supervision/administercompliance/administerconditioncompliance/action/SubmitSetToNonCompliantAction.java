// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\SubmitSetToNonCompliantAction.java

package ui.supervision.administercompliance.administerconditioncompliance.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercasenotes.UpdateCasenoteEvent;
import messaging.administercompliance.CreateNonCompliantEventEvent;
import messaging.administercompliance.CreateNonCompliantLikeEventEvent;
import messaging.administercompliance.SetToNonCompliantEvent;
import messaging.supervisionoptions.reply.GroupResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.utilities.MessageUtil;
import naming.ComplianceControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administercompliance.administerconditioncompliance.UIAdministerComplianceHelper;
import ui.supervision.administercompliance.administerconditioncompliance.form.ComplianceForm;
import ui.supervision.administercompliance.administerconditioncompliance.form.ComplianceSuperviseeInfoForm;

public class SubmitSetToNonCompliantAction extends JIMSBaseAction {

	/**
	 * @roseuid 473B8C24006A
	 */
	public SubmitSetToNonCompliantAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
		keyMap.put("button.finishCasenoteLater", "finishCasenoteLater");

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 473B755700BC
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ComplianceForm compForm = (ComplianceForm) aForm;
		ActionForward forward = aMapping.findForward(UIConstants.FAILURE);
		SetToNonCompliantEvent rEvent = this.prepareRequestEvent(compForm, false);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(rEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		ReturnException returnException = (ReturnException) MessageUtil
				.filterComposite(response, ReturnException.class);
		if (returnException == null) {
			compForm.setConfirmMessage("Condition(s) successfully set to Noncompliant.");
			UIAdministerComplianceHelper.loadComplianceConditions(compForm);
			ComplianceSuperviseeInfoForm compSupForm = (ComplianceSuperviseeInfoForm) getSessionForm(aMapping, aRequest,
					"complianceSuperviseeInfoForm", true);
			compSupForm.setSuperviseeCompliant(UIAdministerComplianceHelper.getSuperviseeComplianceStatus(
					compForm.getUniqueConditions(), compForm.getLikeConditions()));	
			compSupForm.setSuperviseeCompliant(false);
			forward = aMapping.findForward(UIConstants.SUCCESS);
		} else {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic", "Error occured saving information"));
			saveErrors(aRequest, errors);
		}
		return forward;
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward finishCasenoteLater(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ComplianceForm compForm = (ComplianceForm) aForm;
		ActionForward forward = aMapping.findForward(UIConstants.FAILURE);
		SetToNonCompliantEvent rEvent = this.prepareRequestEvent(compForm, true);

		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(rEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		ReturnException returnException = 
			(ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
		if (returnException == null) {
			compForm.setConfirmMessage("Condition(s) successfully set to Noncompliant.");
			UIAdministerComplianceHelper.loadComplianceConditions(compForm);
			ComplianceSuperviseeInfoForm compSupForm = 
				(ComplianceSuperviseeInfoForm) getSessionForm(aMapping, aRequest,"complianceSuperviseeInfoForm", true);
			compSupForm.setSuperviseeCompliant(UIAdministerComplianceHelper.getSuperviseeComplianceStatus(
					compForm.getUniqueConditions(), compForm.getLikeConditions()));	
			compSupForm.setSuperviseeCompliant(false);
			forward = aMapping.findForward(UIConstants.SUCCESS);
		} else {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic",
							"Error occured saving information."));
			saveErrors(aRequest, errors);
		}
		return forward;
	}

	/**
	 * @param compForm
	 * @return Event
	 */
	private SetToNonCompliantEvent prepareRequestEvent(ComplianceForm compForm, boolean isDraft) {
		SetToNonCompliantEvent rEvent = 
			(SetToNonCompliantEvent) EventFactory.getInstance(ComplianceControllerServiceNames.SETTONONCOMPLIANT);
		rEvent.setDefendantId(compForm.getDefendantId());
		UpdateCasenoteEvent uEvent = new UpdateCasenoteEvent();
		uEvent.setAgencyId(compForm.getAgencyId());
		uEvent.setCasenoteStatusId(compForm.getCasenoteStatusId());
		uEvent.setCollateralId(compForm.getCollateralId());
		uEvent.setContactMethodId(compForm.getContactMethodId());
		uEvent.setSaveAsDraft(isDraft);
		//uEvent.setSupervisionOrderId(compForm.getOrderId());
		String casenoteTime = compForm.getCasenoteTime();
		if (compForm.getAMPMId().equalsIgnoreCase("PM")){
			String[] theTime = compForm.getCasenoteTime().split(":");
			int iHr = Integer.parseInt(theTime[0]);
			if (iHr < 12){
				iHr = iHr + 12;
			}
			String sHr = Integer.toString(iHr);
			casenoteTime = sHr + ":" + theTime[1];
		}
		uEvent.setEntryDate(UIAdministerComplianceHelper.convertToDateTime(compForm.getCasenoteDateAsString(), casenoteTime));
		uEvent.setSuperviseeId(compForm.getSuperviseeId());

		Collection subjects = new ArrayList();
		for (int i = 0; i < compForm.getSubjectIds().length; i++) {
			subjects.add(compForm.getSubjectIds()[i]);
		}
		uEvent.setSubjects(subjects);
		
		String associateIds[] = compForm.getSelectedAssociateIds();
		Collection associates = new ArrayList();
		for (int i = 0; i < associateIds.length; i++) {
			if(associateIds[i]!=null && !associateIds[i].equals("") )
				associates.add(associateIds[i]);
		}
		uEvent.setAssociates(associates);
		uEvent.setCasenoteTypeId(compForm.getCasenoteTypeId());
		rEvent.addRequest(uEvent);
		
		Iterator groupsIterator = compForm.getGroups().iterator();
		Map groups = new HashMap();
		while (groupsIterator.hasNext()) {
			GroupResponseEvent resp = (GroupResponseEvent) groupsIterator.next();
			Iterator childGroups = resp.getSubgroups().iterator();
			while(childGroups.hasNext()){
				GroupResponseEvent childResp = (GroupResponseEvent) childGroups.next();
				groups.put(childResp.getGroupId(), childResp.getName());
			}
			groups.put(resp.getGroupId(), resp.getName());
		}
		
		StringBuffer notes = new StringBuffer();	
		String occurrenceTime = "";
		// unique conditions
		if (compForm.getSelectedUniqueConditionsEvents() != null) {
			Iterator sceIter = compForm.getSelectedUniqueConditionsEvents().iterator();	
			occurrenceTime = "";
			while (sceIter.hasNext()) {
				CreateNonCompliantEventEvent scEvent = (CreateNonCompliantEventEvent) sceIter.next();
				String[] eventTypeCodeIds = new String[20];
				notes.append(scEvent.getOrderConditionName());
				notes.append(": ");
				String groupName = (String) groups.get(scEvent.getGroupId());
				if (groupName != null){
					notes.append(groupName);
					notes.append(": ");
				}
//	 10/15/08 defect54593 change					
//				notes.append((scEvent.isCompliant() == true)?"Compliant":"Non Compliant");
				notes.append("Non Compliant");
				notes.append("<BR>");	
				
				for (int i = 0; i < scEvent.getEventTypeCodeIds().length; i++) {
					eventTypeCodeIds[i] = scEvent.getEventTypeCodeIds()[i];
				}
				
				scEvent.setDetails(scEvent.getDetails());
				scEvent.setEventTypeCodeIds(scEvent.getEventTypeCodeIds());					
				scEvent.setOccurrenceDate(scEvent.getOccurrenceDate());	
				scEvent.setNewEventType(scEvent.getNewEventType());
				if (scEvent.getAMPMId().equalsIgnoreCase("PM")){
					String[] theTime = scEvent.getOccurrenceTime().split(":");
					int iHr = Integer.parseInt(theTime[0]);
					if (iHr < 12){
						iHr = iHr + 12;
					}					
					String sHr = Integer.toString(iHr);
					occurrenceTime = sHr + ":" + theTime[1];
					scEvent.setOccurrenceTime(occurrenceTime);
				}
				rEvent.addRequest(scEvent);
			}
		}
		// like conditions
		if (compForm.getSelectedLikeConditionsEvents() != null) {
			String details = "";
			String occurrenceDate = "";
			occurrenceTime = "";
			String[] eventTypeCodeIds = new String[20];
			Iterator likeIter = compForm.getSelectedLikeConditionsEvents().iterator();
			while (likeIter.hasNext()) {
				CreateNonCompliantLikeEventEvent cncEvent = (CreateNonCompliantLikeEventEvent) likeIter.next();
				Iterator sceIter = cncEvent.getCreateNonCompliantEventEvent().iterator();
				boolean isNonCompliant = false;
				String groupId = "";
				while (sceIter.hasNext()) {
					CreateNonCompliantEventEvent scEvent = (CreateNonCompliantEventEvent) sceIter.next();
					scEvent.setAM(true);
					
					for (int i = 0; i < cncEvent.getEventTypeCodeIds().length; i++) {
						eventTypeCodeIds[i] = cncEvent.getEventTypeCodeIds()[i];
					}
					
					scEvent.setDetails(cncEvent.getDetails());
					scEvent.setEventTypeCodeIds(eventTypeCodeIds);					
					scEvent.setOccurrenceDate(cncEvent.getOccurrenceDate());					
					if (cncEvent.getAMPMId().equalsIgnoreCase("PM")){
						String[] theTime = cncEvent.getOccurrenceTime().split(":");
						int iHr = Integer.parseInt(theTime[0]);
						if (iHr < 12){
							iHr = iHr + 12;
						}	
						String sHr = Integer.toString(iHr);
						occurrenceTime = sHr + ":" + theTime[1];
					}
					scEvent.setOccurrenceTime(cncEvent.getOccurrenceTime());
					scEvent.setNewEventType(cncEvent.getNewEventType());
					groupId = scEvent.getGroupId();
					if(!scEvent.isCompliant()){
						isNonCompliant = true;
					}
					rEvent.addRequest(scEvent);
				}
				notes.append(" ");
				notes.append(cncEvent.getOrderConditionName());
				notes.append(": ");
				String groupName = (String) groups.get(groupId);
				notes.append(groupName);
				notes.append(": ");
// 10/15/08 defect54593 change				
//				notes.append((isNonCompliant == false)?"Compliant":"Non Compliant");
				notes.append("Non Compliant");
				notes.append("<BR>");	
			}			
			
		}
		notes.append(compForm.getCasenoteText());
		uEvent.setNotes(notes.toString());		
		return rEvent;
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */	
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ComplianceForm compForm = (ComplianceForm) aForm;
		compForm.setConfirmMessage("");
		return super.cancel(aMapping,aForm,aRequest,aResponse);
	}	
}