// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreports\\DisplayVRReasonForTransferSummaryAction.java

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

public class DisplayVRReasonForTransferSummaryAction extends JIMSBaseAction {

	/**
	 * @roseuid 464368F103D5
	 */
	public DisplayVRReasonForTransferSummaryAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "back");  // cancel functions same as back		
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
		for (int x=0; x<vrForm.getCreate1ElementsList().size(); x++){
			CodeResponseEvent cre = (CodeResponseEvent) vrForm.getCreate1ElementsList().get(x);
			for (int y =0; y < vrForm.getSelectedReasonForTransferIds().length; y++){
				if (cre.getCode().equalsIgnoreCase(vrForm.getSelectedReasonForTransferIds()[y])){
					newList.add(cre);
					break;
				}	
			}
		}
		vrForm.setCreate2ElementsList(newList);
		return aMapping.findForward(UIConstants.SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) {
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		String forwardStr = UIConstants.BACK;
		// set isExtended to blank to clear out chosen isExtended value if cancel button was hit
		if(vrForm.getSelectedReasonForTransferIds() == null || vrForm.getSelectedReasonForTransferIds().length == 0 ){
			vrForm.setIsExtended("");
		}else if(vrForm.getCurrentReasonForTransferComments() != null && !vrForm.getCurrentReasonForTransferComments().equals(vrForm.getIsExtended())){
			vrForm.setIsExtended(vrForm.getCurrentReasonForTransferComments());
		}
		if (vrForm.isTaskflowInd()){
			forwardStr = UIConstants.TASKFLOW_BACK;
		}
		return aMapping.findForward(forwardStr);
	}
}