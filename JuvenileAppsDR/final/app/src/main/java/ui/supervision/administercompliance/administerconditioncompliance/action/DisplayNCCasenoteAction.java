// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\DisplayNCCasenoteAction.java

package ui.supervision.administercompliance.administerconditioncompliance.action;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.reply.ComplianceConditionResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
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

public class DisplayNCCasenoteAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public DisplayNCCasenoteAction() {

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
		compForm.setCompliancePage(UIConstants.FROM_COMPLIANCE_RESOLVE_CASENOTE);
		Iterator scIter = compForm.getSelectedConditions().iterator();
		while (scIter.hasNext()) {
			ComplianceConditionResponseEvent ccre = (ComplianceConditionResponseEvent) scIter.next();
			Iterator rcIter = compForm.getComplianceReasons().iterator();
			while (rcIter.hasNext()) {
				CodeResponseEvent cdre = (CodeResponseEvent) rcIter.next();
				if (cdre.getCode().equalsIgnoreCase(ccre.getComplianceReasonId())) {
					ccre.setComplianceReasonDescription(cdre.getDescription());
					break;
				}
			}
		}
		UIAdministerComplianceHelper.setAllCaseNotes(compForm);
		return aMapping.findForward(UIConstants.SUCCESS);
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
