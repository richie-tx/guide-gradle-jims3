//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreport\\action\\DisplayFileViolationReportSummaryAction.java

package ui.supervision.administercompliance.administercasesummary.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.reply.CodeResponseEvent;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;

public class DisplayCaseSummaryCourtActionsSummaryAction extends JIMSBaseAction {

	/**
	* @roseuid 47DA9D370287
	*/
	public DisplayCaseSummaryCourtActionsSummaryAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		List newList = new ArrayList();
		for (int y =0; y < csForm.getSelectedCourtActionsIds().length; y++){
			for (int x =0; x < csForm.getCreate1ElementsList().size(); x++){
				CodeResponseEvent cre = (CodeResponseEvent) csForm.getCreate1ElementsList().get(x);				
				if (cre.getCode().equalsIgnoreCase(csForm.getSelectedCourtActionsIds()[y])){
					newList.add(cre);
					break;
				}	
			}
		}
		csForm.setCreate2ElementsList(newList);		
     	return aMapping.findForward(UIConstants.SUCCESS);
	}	
}
