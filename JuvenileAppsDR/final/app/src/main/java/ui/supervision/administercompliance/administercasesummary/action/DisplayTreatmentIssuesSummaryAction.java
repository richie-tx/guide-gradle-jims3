//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasesummary\\action\\DisplayTreatmentIssuesSummaryAction.java

package ui.supervision.administercompliance.administercasesummary.action;

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
import ui.supervision.administercompliance.administercasesummary.UICaseSummaryHelper;
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;
import ui.supervision.administercompliance.administerconditioncompliance.UIAdministerComplianceHelper;

public class DisplayTreatmentIssuesSummaryAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D3C0343
	 */
	public DisplayTreatmentIssuesSummaryAction() {

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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		addNewTreatmentIssue(csForm, false);
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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		addNewTreatmentIssue(csForm, true);
		return aMapping.findForward(UIConstants.ADD_SUCCESS);
	}	

	private void addNewTreatmentIssue(CaseSummaryForm csForm, boolean showAdd)
	{	
		if (csForm.isShowAddFields() == true){
			if ((csForm.getReferralTypeDesc() != null && !csForm.getReferralTypeDesc().equals("") ) ||
				(csForm.getServiceProviderName() != null && !csForm.getServiceProviderName().equals("") ) ||
				(csForm.getReferralBeginDateStr() != null && !csForm.getReferralBeginDateStr().equals("") ) ||
				(csForm.getReferralExitDateStr() != null && !csForm.getReferralExitDateStr().equals("") ) ||
				(csForm.getSelectedDischargeReasonId().length() != 0) ) {
					csForm.setAddItemIndex(UICaseSummaryHelper.getAddIndex(csForm.getAddItemIndex()));
					CSProgramReferralResponseEvent newEvent = new CSProgramReferralResponseEvent();
					StringBuffer referralTypeCode = new StringBuffer();
					referralTypeCode.append(csForm.getProgramGroupId());
					referralTypeCode.append(" ");
					referralTypeCode.append(csForm.getProgramTypeId());
					newEvent.setReferralTypeCode(referralTypeCode.toString());
					newEvent.setNewServiceProviderName(csForm.getServiceProviderName().toUpperCase());
			
					if (csForm.getReferralBeginDateStr() != null && !csForm.getReferralBeginDateStr().equals("")){
						newEvent.setProgramBeginDate(UIAdministerComplianceHelper.convertToDateTime(csForm.getReferralBeginDateStr(), "00:00"));
					}
					if (csForm.getReferralExitDateStr() != null && !csForm.getReferralExitDateStr().equals("")){
						newEvent.setProgramEndDate(UIAdministerComplianceHelper.convertToDateTime(csForm.getReferralExitDateStr(), "00:00"));
						}
					if (csForm.getSelectedDischargeReasonId()!= null){
						for (int y=0; y<csForm.getDischargeReasons().size(); y++){
							CodeResponseEvent cre2 = (CodeResponseEvent) csForm.getDischargeReasons().get(y);
							if (cre2.getCode().equals(csForm.getSelectedDischargeReasonId())){
								newEvent.setDischargeReason(cre2.getDescription());
								break;
							}
						}
					}
					newEvent.setProgramReferralId(csForm.getAddItemIndex());
					newEvent.setManualAdded(true);
					csForm.getCreate1ElementsList().add(newEvent);
			}		
		}
		csForm.setShowAddFields(showAdd);
		csForm.clearTreatmentIssuesAdds();
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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		int len = csForm.getSelectedTreatmentIssuesIds().length;
		int listSize = csForm.getCreate1ElementsList().size();
		boolean itemMatched = false;
		List reducedList = new ArrayList();
		for (int x =0; x < listSize; x++){
			CSProgramReferralResponseEvent tire = (CSProgramReferralResponseEvent) csForm.getCreate1ElementsList().get(x);
			itemMatched = false;
			for (int y =0; y < len; y++){
				if (tire.getProgramReferralId().equalsIgnoreCase(csForm.getSelectedTreatmentIssuesIds()[y])){
					itemMatched = true;
					break;
				}	
			}
			if (!itemMatched){
				reducedList.add(tire);
			}
		}
		csForm.setCreate1ElementsList(reducedList);
		csForm.clearTreatmentIssuesAdds();		
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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
  	    RefreshNCResponseComponentsEvent event = (RefreshNCResponseComponentsEvent) EventFactory.getInstance(ComplianceControllerServiceNames.REFRESHNCRESPONSECOMPONENTS);
        event.setRequestType(ViolationReportConstants.REQUEST_TREATMENT);
        event.setNcResponseId(csForm.getViolationReportId());
    	event.setCaseId(csForm.getCaseNum());
		event.setCdi(csForm.getCdi());    

		List list = MessageUtil.postRequestListFilter(event, CSProgramReferralResponseEvent.class);
		if (list != null){
			csForm.setCreate1ElementsList(list);
		} else {
			csForm.setCreate1ElementsList(new ArrayList());
		}
		csForm.setCreate1Comments("");
		csForm.setCurrentTreatmentIssuesList(new ArrayList());
		csForm.setCurrentTreatmentIssuesComments("");
		csForm.clearTreatmentIssuesAdds();
		csForm.setShowAddFields(false);
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
