//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreport\\action\\DisplayFileViolationReportSummaryAction.java

package ui.supervision.administercompliance.administerviolationreport.action;

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
import ui.supervision.administercompliance.administerviolationreport.form.ViolationReportsForm;

public class DisplayFileViolationReportSummaryAction extends JIMSBaseAction {

	/**
	* @roseuid 47DA9D370287
	*/
	public DisplayFileViolationReportSummaryAction() {

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
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		List newList = new ArrayList();
		for (int x =0; x < vrForm.getCreate1ElementsList().size(); x++){
			CodeResponseEvent cre = (CodeResponseEvent) vrForm.getCreate1ElementsList().get(x);
			for (int y =0; y < vrForm.getSelectedCourtActionsIds().length; y++){
				if (cre.getCode().equalsIgnoreCase(vrForm.getSelectedCourtActionsIds()[y])){
					newList.add(cre);
					break;
				}	
			}
		}
		vrForm.setCreate2ElementsList(newList);		
     	return aMapping.findForward(UIConstants.SUCCESS);
	}	
}
