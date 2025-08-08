//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreport\\action\\SubmitVRCourtActionsAction.java

package ui.supervision.administercompliance.administercasesummary.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercasenotes.UpdateCasenoteEvent;
import messaging.administercasenotes.reply.CasenoteResponseEvent;
import messaging.administercompliance.UpdateNCRecommendationEvent;
import messaging.administercompliance.UpdateNCResponseEvent;
import messaging.codetable.GetSupervisionCodesEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.spnsplit.GetCaseSupervisionPeriodsEvent;
import messaging.spnsplit.reply.SpnSplitOrderPeriodResponseEvent;
import messaging.supervision.reply.SupervisionStaffResponseEvent;
import messaging.supervisionorder.GetSuperviseeCaseOrdersEvent;
import messaging.supervisionorder.reply.SuperviseeCaseOrderResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadConstants;
import naming.CasenoteControllerServiceNames;
import naming.CodeTableControllerServiceNames;
import naming.ComplianceControllerServiceNames;
import naming.PDCodeTableConstants;
import naming.SPNSplitControllerServiceNames;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;
import naming.ViolationReportConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.administercompliance.administercasehistory.UICaseHistoryHelper;
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;
import ui.supervision.administercompliance.administerviolationreport.UIViolationReportHelper;
import ui.supervision.administercompliance.administerviolationreport.form.ViolationReportsForm;

public class SubmitCaseSumUpdateCourtActionsCreateUpdateAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D4402B6
	 */
	public SubmitCaseSumUpdateCourtActionsCreateUpdateAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.finish", "finish");
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		csForm.setStatusId(ViolationReportConstants.STATUS_FILED);
		
		UpdateNCResponseEvent event = (UpdateNCResponseEvent) EventFactory.getInstance(ComplianceControllerServiceNames.UPDATENCRESPONSE);
    	// set flag that this is only an update, not a filing
		event.setUpdatePresentedRequest(true);
		event.setFiling(true);
    	event.setNcResponseId(csForm.getViolationReportId());
		event.setRequestType(ViolationReportConstants.REQUEST_RECOMMENDATION);
    	if(StringUtils.isNotEmpty(csForm.getCourtActionfiledDate())){
	    	String filingDate = csForm.getCourtActionfiledDate();
	    	Timestamp presentDate = UIViolationReportHelper.convertDateToTimeStamp(filingDate, "00:00", "AM");
	    	event.setFilledDate(presentDate);
    	}
    	event.setStatusId(ViolationReportConstants.STATUS_FILED);
    	event.setPositionIdOfPresentedBy("");
    	event.setSummaryOfCourtActions(csForm.getSummaryOfCourtActions());
		if (csForm.getPresentedByList() != null && !csForm.getPresentedByList().isEmpty()){
			for (int x = 0; x < csForm.getPresentedByList().size(); x++){
				SupervisionStaffResponseEvent ssRespEvt = (SupervisionStaffResponseEvent) csForm.getPresentedByList().get(x);
				if (ssRespEvt.getLogonId().equalsIgnoreCase(csForm.getPresentedById())){
					event.setPositionIdOfPresentedBy(ssRespEvt.getSupervisionStaffId());
					break;
				}
			}			
		}

    	event.setPositionIdOfSignedBy("");
    	String [] xName = csForm.getPresentedByName().split("\\|");
    	if (xName != null){
    		event.setPresentedBy(xName[0].toString().trim());
    	}	
    	event.setSignedBy(csForm.getWhoSignedName());
    	event.setPositionIdOfSignedBy(csForm.getWhoSignedId());
		int listSize = csForm.getCreate2ElementsList().size();
		UpdateNCRecommendationEvent req = null;
		for (int g = 0; g < listSize; g++){
			CodeResponseEvent cre = (CodeResponseEvent) csForm.getCreate2ElementsList().get(g);
			req = new UpdateNCRecommendationEvent();
			req.setCourtActionCodeDesc(cre.getDescription());
			req.setCourtActionCodeId(cre.getCode());
			req.setType(ViolationReportConstants.STATUS_FILED);
	    	event.addRequest(req);
		}	
    	
		MessageUtil.postRequest(event);    	
		csForm.setConfirmationMessage("Case Summary Report successfully updated.");
		csForm.setStatusDesc("Updated");
		csForm.setFileDateStr(csForm.getCourtActionfiledDate());
		csForm.setCurrentCourtActionsList(csForm.getCreate2ElementsList());
		csForm.setAllowUpdate(UIConstants.YES);
		csForm.setOrderId(findOrderId(csForm.getSuperviseeId(), csForm.getCdi(), csForm.getCaseNum()));
    	csForm.setTaskflowInd(false);
		return aMapping.findForward(UIConstants.FINISH);
	}

	public static String findOrderId(String superviseeId, String cdi, String caseNumber){
		String orderId = "";
    	String caseNum = cdi + caseNumber;
		GetSuperviseeCaseOrdersEvent event = (GetSuperviseeCaseOrdersEvent) EventFactory.getInstance(SupervisionOrderControllerServiceNames.GETSUPERVISEECASEORDERS);
        event.setSuperviseeId(superviseeId);

        List cases = MessageUtil.postRequestListFilter(event, SuperviseeCaseOrderResponseEvent.class);
		if (cases != null && !cases.isEmpty()) {
			Iterator iter = cases.iterator();
			while(iter.hasNext()){
				SuperviseeCaseOrderResponseEvent score = (SuperviseeCaseOrderResponseEvent) iter.next();
				if (score.getCaseNumber() != null && score.getCaseNumber().equalsIgnoreCase(caseNum)) {
					orderId = score.getSupervisionOrderId();
					break;
				}
			}
		}	
		return orderId;	
	}
}