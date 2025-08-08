// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\DisplayAddCasenotesSummaryAction.java

package ui.supervision.administercompliance.administerconditioncompliance.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administercompliance.administerconditioncompliance.UIAdministerComplianceCasenoteHelper;
import ui.supervision.administercompliance.administerconditioncompliance.form.ComplianceForm;

/*
 * 
 * @author mchowdhury
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class DisplayAddCasenotesSummaryAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public DisplayAddCasenotesSummaryAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.reset", "reset");
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
		if (compForm.getCollateralId().equalsIgnoreCase("AS")){
			UIAdministerComplianceCasenoteHelper.associateNameRelationshipFormatter(compForm);
		}
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	   /**
	    * @param aMapping
	    * @param aForm
	    * @param aRequest
	    * @param aResponse
	    * @return ActionForward
	    */
	   public ActionForward reset(ActionMapping aMapping,
				ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException    
	   {
		    ComplianceForm compForm = (ComplianceForm) aForm;
		    UIAdministerComplianceCasenoteHelper.resetInputs(compForm);
			return aMapping.findForward(UIConstants.RESET_SUCCESS);
	   }
}
