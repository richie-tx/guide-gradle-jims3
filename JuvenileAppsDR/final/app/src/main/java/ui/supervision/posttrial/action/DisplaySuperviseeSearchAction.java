//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\action\\DisplayCaseHistoryListAction.java

package ui.supervision.posttrial.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.CodeHelper;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administercasenotes.form.SuperviseeSearchForm;

/*
 * 
 * @author mchowdhury
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */

public class DisplaySuperviseeSearchAction extends JIMSBaseAction {

	/**
	 *  
	 */
	public DisplaySuperviseeSearchAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link", "link");
		keyMap.put("button.advancedSuperviseeSearch", "advancedSearch");
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward link(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		SuperviseeSearchForm form = (SuperviseeSearchForm) aForm;
		form.clearAll();		
		form.setRaceList(CodeHelper.getRaces());
		form.setSexList(CodeHelper.getSexCodes());			
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	public ActionForward advancedSearch(ActionMapping aMapping,
			ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		SuperviseeSearchForm myForm = (SuperviseeSearchForm) aForm;
		myForm.clearAll();
		//			 set defaults up
		myForm.setAction(UIConstants.ADVANCED);
		return aMapping.findForward(UIConstants.ADVANCED_SEARCH_SUCCESS);
	}
}
