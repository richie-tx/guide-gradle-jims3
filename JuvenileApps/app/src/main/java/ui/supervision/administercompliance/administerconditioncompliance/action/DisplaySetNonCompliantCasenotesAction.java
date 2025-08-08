//Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\DisplaySetNonCompliantCasenotesAction.java

package ui.supervision.administercompliance.administerconditioncompliance.action;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.reply.NonComplianceEventTypeCodeResponseEvent;
import messaging.administercompliance.CreateNonCompliantEventEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administercompliance.administerconditioncompliance.UIAdministerComplianceCasenoteHelper;
import ui.supervision.administercompliance.administerconditioncompliance.UIAdministerComplianceHelper;
import ui.supervision.administercompliance.administerconditioncompliance.form.ComplianceForm;

/*
 * 
 * @author mchowdhury
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class DisplaySetNonCompliantCasenotesAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public DisplaySetNonCompliantCasenotesAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.addAssociate", "associateAdded" );
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward next(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ComplianceForm compForm = (ComplianceForm) aForm;
		compForm.clearCasenoteSearch();
		compForm.clearCasenoteInputs();
		this.setEventTypes(compForm);
		compForm.setDisplayAction(UIConstants.BASIC);
		compForm.setCompliancePage(UIConstants.FROM_COMPLIANCE_SET_TO_NONCOMPLIANT_CASENOTE);
		UIAdministerComplianceHelper.setAllCaseNotes(compForm);
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	/**
	 * @param form
	 */
	private void setEventTypes(ComplianceForm compForm) {
		Collection theConditions = compForm.getSelectedUniqueConditionsEvents();
		if (theConditions != null && !theConditions.isEmpty()){
			Iterator uCondIter = theConditions.iterator();
			while (uCondIter.hasNext()){
				CreateNonCompliantEventEvent ccre1 = (CreateNonCompliantEventEvent) uCondIter.next();
				ccre1.setEventTypes(this.getEventTypeDescriptions(ccre1)); 
			}
		}
		theConditions = compForm.getSelectedLikeConditionsEvents();
		if (theConditions != null && !theConditions.isEmpty()){
			Iterator lCondIter = theConditions.iterator();
			while (lCondIter.hasNext()){
				CreateNonCompliantEventEvent ccre2 = (CreateNonCompliantEventEvent) lCondIter.next();
				ccre2.setEventTypes(this.getEventTypeDescriptions(ccre2));				
			}
		}
	}
	
	/**
	 * @param Event
	 */
	private String getEventTypeDescriptions(CreateNonCompliantEventEvent theEvent) {
		String EventTypeDescStr = "";
		String EventTypeCodeId = "";
		for (int i = 0; i < theEvent.getEventTypeCodeIds().length; i++) {
			EventTypeCodeId = theEvent.getEventTypeCodeIds()[i];
			if (!EventTypeCodeId.equals("") && !EventTypeCodeId.equalsIgnoreCase("NEW") ){
				Iterator etIter = theEvent.getEventTypeList().iterator();
				while (etIter.hasNext()){
					NonComplianceEventTypeCodeResponseEvent ncResp = (NonComplianceEventTypeCodeResponseEvent) etIter.next();
					if (EventTypeCodeId.equals(ncResp.getNonComplianceEventTypeCodeId())){
						EventTypeDescStr = EventTypeDescStr + ncResp.getNonComplianceEventTypeCodeDesc();
					}
				}
			}
			if (i + 1 < theEvent.getEventTypeCodeIds().length){
				EventTypeDescStr = EventTypeDescStr + ", ";
			}
		}		
		return EventTypeDescStr;
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward associateAdded(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ComplianceForm compForm = (ComplianceForm) aForm;
		UIAdministerComplianceCasenoteHelper.associateAddhandler(compForm);
		return aMapping.findForward(UIConstants.SUCCESS);
	}	
}
