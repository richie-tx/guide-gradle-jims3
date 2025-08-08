// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\medical\\action\\DisplayMedicalSearchAction.java

package ui.juvenilecase.medical.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.medical.form.MedicalForm;


public class DisplayMedicalMedicationSearchAction  extends LookupDispatchAction {

	/**
	 * @roseuid 44CF7757030C
	 */
	public DisplayMedicalMedicationSearchAction() {

	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 44C8E0DA037A
	 */
	public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		MedicalForm medForm = (MedicalForm)aForm;
		medForm.setMedications(new ArrayList());
		medForm.getSearchMedication().clearSearchCriteria();
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.link", "link");
		return keyMap;	
	}   
}
