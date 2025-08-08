//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasesuumary\\action\\DisplayCommunityServiceSummaryAction.java

package ui.supervision.administercompliance.administercasesummary.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.RefreshNCResponseComponentsEvent;
import messaging.administercompliance.reply.NCCommunityServiceResponseEvent;
import messaging.administercompliance.reply.NCTreatmentResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.ComplianceControllerServiceNames;
import naming.UIConstants;
import naming.ViolationReportConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;

public class DisplayCommunityServiceSummaryAction extends JIMSBaseAction {

	/**
	* @roseuid 47DA9D370287
	*/
	public DisplayCommunityServiceSummaryAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.refreshCommunityService", "refresh");
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
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
// this needs more work -- remove existing comment values
		RefreshNCResponseComponentsEvent event = (RefreshNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.REFRESHNCRESPONSECOMPONENTS);
        event.setRequestType(ViolationReportConstants.REQUEST_TREATMENT);
    	event.setNcResponseId(csForm.getViolationReportId());
    	event.setCaseId(csForm.getCaseNum());
		event.setCdi(csForm.getCdi());    

		List list = MessageUtil.postRequestListFilter(event, NCTreatmentResponseEvent.class);
		if (list != null){
	        for (int x =0; x < list.size(); x++){
				NCCommunityServiceResponseEvent csre = (NCCommunityServiceResponseEvent) list.get(x);
				csForm.setHoursOrdered(csre.getHoursOrdered());
				csForm.setHoursCompleted(csre.getHoursCompleted());
				break;
			}
		}
		csForm.setCreate1Comments("");
		csForm.setCurrentCommunityServiceComments("");
		csForm.setCurrentHoursCompleted("");
		csForm.setCurrentHoursOrdered("");
		return aMapping.findForward(UIConstants.REFRESH_SUCCESS);
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
		if (csForm.isTaskflowInd()){
			forwardStr = UIConstants.TASKFLOW_BACK;
		}
		return aMapping.findForward(forwardStr);
	}	
}
