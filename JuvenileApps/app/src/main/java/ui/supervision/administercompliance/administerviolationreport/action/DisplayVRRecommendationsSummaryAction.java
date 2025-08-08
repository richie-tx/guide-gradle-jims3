//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreport\\action\\DisplayVRRecommendationsSummaryAction.java

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

public class DisplayVRRecommendationsSummaryAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D3B0239
	 */
	public DisplayVRRecommendationsSummaryAction() {

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
		int len = vrForm.getSelectedSuggestedCourtActionIds().length;
		int listSize = vrForm.getCreate1ElementsList().size();
		List newList = new ArrayList();
		for (int x =0; x < listSize; x++){
			CodeResponseEvent cre = (CodeResponseEvent) vrForm.getCreate1ElementsList().get(x);
			for (int y =0; y < len; y++){
				if (cre.getCode().equalsIgnoreCase(vrForm.getSelectedSuggestedCourtActionIds()[y])){
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
		if (vrForm.isTaskflowInd()){
			forwardStr = UIConstants.TASKFLOW_BACK;
		}
		return aMapping.findForward(forwardStr);
	}		
}
