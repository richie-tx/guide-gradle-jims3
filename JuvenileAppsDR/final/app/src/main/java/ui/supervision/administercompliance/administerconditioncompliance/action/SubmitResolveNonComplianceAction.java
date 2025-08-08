// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\SubmitResolveNonComplianceAction.java

package ui.supervision.administercompliance.administerconditioncompliance.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercasenotes.UpdateCasenoteEvent;
import messaging.administercompliance.reply.ComplianceConditionResponseEvent;
import messaging.administercompliance.ResolveNonComplianceEvent;
import messaging.administercompliance.UpdateOrderConditionEvent;
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
import ui.supervision.administercasenotes.form.CasenoteForm;
import ui.supervision.administercompliance.administerconditioncompliance.UIAdministerComplianceHelper;
import ui.supervision.administercompliance.administerconditioncompliance.form.ComplianceForm;
import ui.supervision.administercompliance.administerconditioncompliance.form.ComplianceSuperviseeInfoForm;

public class SubmitResolveNonComplianceAction extends JIMSBaseAction {

	/**
	 * @roseuid 473B8C2300A8
	 */
	public SubmitResolveNonComplianceAction() {

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
	 * @roseuid 473B755403E8
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ComplianceForm compForm = (ComplianceForm) aForm;
		CasenoteForm cnForm = (CasenoteForm) getSessionForm(aMapping, aRequest, "casenoteForm", true);
		ActionForward forward = aMapping.findForward(UIConstants.FAILURE);

		ResolveNonComplianceEvent rEvent = this.prepareRequestEvent(compForm, false);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(rEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		ReturnException returnException = (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
		if (returnException == null) {
			compForm.setConfirmMessage("Noncompliance successfully resolved.");
			UIAdministerComplianceHelper.loadComplianceConditions(compForm);
			ComplianceSuperviseeInfoForm compSupForm = (ComplianceSuperviseeInfoForm) getSessionForm(aMapping, aRequest,
					"complianceSuperviseeInfoForm", true);
			compSupForm.setSuperviseeCompliant(UIAdministerComplianceHelper.getSuperviseeComplianceStatus(
					compForm.getUniqueConditions(), compForm.getLikeConditions()));			
			forward = aMapping.findForward(UIConstants.SUCCESS);
		} else {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","Error occured saving information"));
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
	public ActionForward finishCasenoteLater(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ComplianceForm compForm = (ComplianceForm) aForm;
		ActionForward forward = aMapping.findForward(UIConstants.FAILURE);
		ResolveNonComplianceEvent rEvent = this.prepareRequestEvent(compForm, true);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(rEvent);
		CompositeResponse response = (CompositeResponse) dispatch.getReply();

		ReturnException returnException = (ReturnException) MessageUtil.filterComposite(response, ReturnException.class);
		if (returnException == null) {
			compForm.setConfirmMessage("Noncompliance successfully resolved.");
			UIAdministerComplianceHelper.loadComplianceConditions(compForm);			
			ComplianceSuperviseeInfoForm compSupForm = (ComplianceSuperviseeInfoForm) getSessionForm(aMapping, aRequest,
					"complianceSuperviseeInfoForm", true);
			compSupForm.setSuperviseeCompliant(UIAdministerComplianceHelper.getSuperviseeComplianceStatus(
					compForm.getUniqueConditions(), compForm.getLikeConditions()));				
			forward = aMapping.findForward(UIConstants.SUCCESS);
		} else {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","Error occured saving information."));
			saveErrors(aRequest, errors);
		}
		return forward;
	}

	/**
	 * @param compForm
	 * @return Event
	 */
	private ResolveNonComplianceEvent prepareRequestEvent(ComplianceForm compForm, boolean isDraft) {
		ResolveNonComplianceEvent rEvent = (ResolveNonComplianceEvent) EventFactory.getInstance(ComplianceControllerServiceNames.RESOLVENONCOMPLIANCE);
		rEvent.setDefendantId(compForm.getDefendantId());

		UpdateCasenoteEvent uEvent = new UpdateCasenoteEvent();
		uEvent.setAgencyId(compForm.getAgencyId());
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

		StringBuffer notes = new StringBuffer();		
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
		
		List selectedCondList = compForm.getSelectedConditions();
		Set notesSet = new HashSet();
		
		for ( int x = 0; x < selectedCondList.size() ; x++ ) {
			UpdateOrderConditionEvent ocEvent = new UpdateOrderConditionEvent();
			
			ComplianceConditionResponseEvent ccrEvent = (ComplianceConditionResponseEvent) selectedCondList.get( x );
			ocEvent.setSprOrderConditionId(Integer.parseInt( ccrEvent.getSprOrderConditionId() ));
			ocEvent.setComplianceReasonCodeId(ccrEvent.getComplianceReasonId());
			ocEvent.setCaseNumber(ccrEvent.getCaseNumber());
			ocEvent.setOrderChainNum(ccrEvent.getOrderChainNumber());
			ocEvent.setConditionId(Integer.parseInt( ccrEvent.getConditionId() ));
			rEvent.addRequest( ocEvent );
			if(!notesSet.contains(ccrEvent.getOrderConditionName())){
				notes.append(ccrEvent.getOrderConditionName());
				notes.append(": ");
				String groupName = (String) groups.get(ccrEvent.getGroupId());
				if (groupName != null){
					notes.append(groupName);
					notes.append(": ");
				}
				notes.append((ccrEvent.isCompliant() == true)?"Compliant":"Non Compliant");
				notes.append("<BR>");	
				notesSet.add(ccrEvent.getOrderConditionName());
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
