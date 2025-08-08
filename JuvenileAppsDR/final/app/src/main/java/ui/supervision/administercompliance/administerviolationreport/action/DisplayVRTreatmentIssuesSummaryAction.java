//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreport\\action\\DisplayVRTreatmentIssuesSummaryAction.java

package ui.supervision.administercompliance.administerviolationreport.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercompliance.RefreshNCResponseComponentsEvent;
import messaging.administerprogramreferrals.reply.CSProgramReferralResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
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
import ui.supervision.administercompliance.administerconditioncompliance.UIAdministerComplianceHelper;
import ui.supervision.administercompliance.administerviolationreport.UIViolationReportHelper;
import ui.supervision.administercompliance.administerviolationreport.form.ViolationReportsForm;

public class DisplayVRTreatmentIssuesSummaryAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D3C0343
	 */
	public DisplayVRTreatmentIssuesSummaryAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.next", "next");
		keyMap.put("button.addTreatmentIssue", "addTreatmentIssue");
		keyMap.put("button.removeSelected", "remove");
		keyMap.put("button.refreshTreatmentIssues", "refresh");	
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
		addNewTreatmentIssue(vrForm, false);
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward addTreatmentIssue(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		addNewTreatmentIssue(vrForm, true);
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
	}
	
	private void addNewTreatmentIssue( ViolationReportsForm vrForm, boolean showAdd)
	{	
		if (vrForm.isShowAddFields() == true){
			if ((vrForm.getReferralTypeDesc() != null && !vrForm.getReferralTypeDesc().equals("") ) ||
				(vrForm.getServiceProviderName() != null && !vrForm.getServiceProviderName().equals("") ) ||
				(vrForm.getReferralBeginDateStr() != null && !vrForm.getReferralBeginDateStr().equals("") ) ||
				(vrForm.getReferralExitDateStr() != null && !vrForm.getReferralExitDateStr().equals("") ) ||
				(vrForm.getSelectedDischargeReasonId().length() != 0) ) {
					vrForm.setAddItemIndex(UIViolationReportHelper.getAddIndex(vrForm.getAddItemIndex()));
					CSProgramReferralResponseEvent newEvent = new CSProgramReferralResponseEvent();
					StringBuffer referralTypeCode = new StringBuffer();
					referralTypeCode.append(vrForm.getProgramGroupId());
					referralTypeCode.append(" ");
					referralTypeCode.append(vrForm.getProgramTypeId());
					newEvent.setReferralTypeCode(referralTypeCode.toString());
					newEvent.setNewServiceProviderName(vrForm.getServiceProviderName().toUpperCase());
			
					if (vrForm.getReferralBeginDateStr() != null && !vrForm.getReferralBeginDateStr().equals("")){
						newEvent.setProgramBeginDate(UIAdministerComplianceHelper.convertToDateTime(vrForm.getReferralBeginDateStr(), "00:00"));
					}
					if (vrForm.getReferralExitDateStr() != null && !vrForm.getReferralExitDateStr().equals("")){
						newEvent.setProgramEndDate(UIAdministerComplianceHelper.convertToDateTime(vrForm.getReferralExitDateStr(), "00:00"));
						}
					if (vrForm.getSelectedDischargeReasonId()!= null){
						for (int y=0; y<vrForm.getDischargeReasons().size(); y++){
							CodeResponseEvent cre2 = (CodeResponseEvent) vrForm.getDischargeReasons().get(y);
							if (cre2.getCode().equals(vrForm.getSelectedDischargeReasonId())){
								newEvent.setDischargeReason(cre2.getDescription());
								break;
							}
						}
					}
					newEvent.setProgramReferralId(vrForm.getAddItemIndex());
					newEvent.setManualAdded(true);
					vrForm.getCreate1ElementsList().add(newEvent);
			}		
		}
		vrForm.setShowAddFields(showAdd);	
		vrForm.clearTreatmentIssuesAdds();
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward remove(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		int len = vrForm.getSelectedTreatmentIssuesIds().length;
		int listSize = vrForm.getCreate1ElementsList().size();
		boolean itemMatched = false;
		List reducedList = new ArrayList();
		for (int x =0; x < listSize; x++){
			CSProgramReferralResponseEvent tire = (CSProgramReferralResponseEvent) vrForm.getCreate1ElementsList().get(x);
			itemMatched = false;
			for (int y =0; y < len; y++){
				if (tire.getProgramReferralId().equalsIgnoreCase(vrForm.getSelectedTreatmentIssuesIds()[y])){
					itemMatched = true;
					break;
				}	
			}
			if (!itemMatched){
				reducedList.add(tire);
			}
		}
		vrForm.setCreate1ElementsList(reducedList);
		vrForm.clearTreatmentIssuesAdds();		
		return aMapping.findForward(UIConstants.REMOVE_FROM_LIST_SUCCESS);
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
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
  	    RefreshNCResponseComponentsEvent event = (RefreshNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.REFRESHNCRESPONSECOMPONENTS);
        event.setRequestType(ViolationReportConstants.REQUEST_TREATMENT);
        event.setNcResponseId(vrForm.getViolationReportId());
    	event.setCaseId(vrForm.getCaseNum());
		event.setCdi(vrForm.getCdi());    

		List list = MessageUtil.postRequestListFilter(event, CSProgramReferralResponseEvent.class);
		if (list != null){
			vrForm.setCreate1ElementsList(list);
		} else {
			vrForm.setCreate1ElementsList(new ArrayList());
		}
		vrForm.setCreate1Comments("");
		vrForm.setCurrentTreatmentIssuesList(new ArrayList());
		vrForm.setCurrentTreatmentIssuesComments("");
		vrForm.clearTreatmentIssuesAdds();
		vrForm.setShowAddFields(false);
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
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		String forwardStr = UIConstants.BACK;
		if (vrForm.isTaskflowInd()){
			forwardStr = UIConstants.TASKFLOW_BACK;
		}
		return aMapping.findForward(forwardStr);
	}	
}
