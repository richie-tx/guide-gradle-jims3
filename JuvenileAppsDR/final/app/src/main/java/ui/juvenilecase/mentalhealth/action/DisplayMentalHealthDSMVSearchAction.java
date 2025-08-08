package ui.juvenilecase.mentalhealth.action;

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

import ui.juvenilecase.mentalhealth.form.TestingSessionForm;



public class DisplayMentalHealthDSMVSearchAction extends LookupDispatchAction{
	/**
	 * @roseuid 44CF7757030C
	 */
	public DisplayMentalHealthDSMVSearchAction() {

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
		TestingSessionForm testSessionform = (TestingSessionForm)aForm;
		testSessionform.getSearchDSMV().clearSearchCriteria();
		testSessionform.getDsmVcodes().clear();
		String diagnosisField = aRequest.getParameter("diagnosisField");
		testSessionform.setDiagnosisField(diagnosisField);
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	
	protected Map getKeyMethodMap() {
		Map keyMap = new HashMap();
		keyMap.put("button.link", "link");
		return keyMap;	
	} 

}
