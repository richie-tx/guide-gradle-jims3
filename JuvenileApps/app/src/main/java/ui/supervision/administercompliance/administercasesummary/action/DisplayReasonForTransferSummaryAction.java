// Source file:
// C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasesummarys\\DisplayReasonForTransferSummaryAction.java

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

public class DisplayReasonForTransferSummaryAction extends JIMSBaseAction {

	/**
	 * @roseuid 464368F103D5
	 */
	public DisplayReasonForTransferSummaryAction() {

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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		List newList = new ArrayList();
		for (int x=0; x<csForm.getCreate1ElementsList().size(); x++){
			CodeResponseEvent cre = (CodeResponseEvent) csForm.getCreate1ElementsList().get(x);
			for (int y =0; y < csForm.getSelectedReasonForTransferIds().length; y++){
				if (cre.getCode().equalsIgnoreCase(csForm.getSelectedReasonForTransferIds()[y])){
					newList.add(cre);
					break;
				}	
			}
		}
		csForm.setCreate2ElementsList(newList);
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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		String forwardStr = UIConstants.BACK;
		// set isExtended to blank to clear out chosen isExtended value if cancel button was hit
		if(csForm.getSelectedReasonForTransferIds() == null || csForm.getSelectedReasonForTransferIds().length == 0 ){
			csForm.setIsExtended("");
		}else if(csForm.getCurrentReasonForTransferComments() != null && !csForm.getCurrentReasonForTransferComments().equals(csForm.getIsExtended())){
			csForm.setIsExtended(csForm.getCurrentReasonForTransferComments());
		}
		
		if (csForm.isTaskflowInd()){
			forwardStr = UIConstants.TASKFLOW_BACK;
		}
		return aMapping.findForward(forwardStr);
	}
}