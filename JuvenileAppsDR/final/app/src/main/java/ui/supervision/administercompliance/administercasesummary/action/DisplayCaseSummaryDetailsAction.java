//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasesummary\\action\\DisplayCaseSummaryDetailsAction.java

package ui.supervision.administercompliance.administercasesummary.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetSuperviseeHeaderInfoEvent;
import messaging.administercaseload.reply.SuperviseeInfoResponseEvent;
import messaging.supervisionorder.GetSuperviseeCaseOrdersEvent;
import messaging.supervisionorder.reply.SuperviseeCaseOrderResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadControllerServiceNames;
import naming.SupervisionOrderControllerServiceNames;
import naming.UIConstants;
import naming.ViolationReportConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.security.SecurityUIHelper;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercompliance.administercasesummary.UICaseSummaryHelper;
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class DisplayCaseSummaryDetailsAction extends JIMSBaseAction {

	/**
	* @roseuid 47DA9D370287
	*/
	public DisplayCaseSummaryDetailsAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.tasks", "link");
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
		CaseSummaryForm csForm = (CaseSummaryForm) aForm;
		CaseAssignmentForm caForm = (CaseAssignmentForm) getSessionForm(aMapping, aRequest,	"caseAssignmentForm", true);
		SuperviseeHeaderForm shForm = (SuperviseeHeaderForm) getSessionForm(aMapping, aRequest,	"superviseeHeaderForm", true);
		csForm.clear();
     	csForm.setTaskflowInd(true);
		csForm.setViolationReportId((String) aRequest.getAttribute(ViolationReportConstants.PARAM_NCRESPONSE_ID));
		String superviseeName = (String) aRequest.getAttribute(ViolationReportConstants.PARAM_SUPERVISEE_NAME);
		if (superviseeName != null){
			csForm.setSuperviseeName(superviseeName.trim());
		}
		csForm.setSuperviseeId((String) aRequest.getAttribute(ViolationReportConstants.PARAM_DEFENDANT_ID));
		String courtId = (String) aRequest.getAttribute(ViolationReportConstants.PARAM_COURT_NUMBER);
		String criminalCaseId = (String) aRequest.getAttribute(ViolationReportConstants.CASE_ID);
		String cdi = "";
		String caseNumber = "";
		if ( !"".equals( criminalCaseId ) && criminalCaseId.length() > 14 ){
			
			cdi = criminalCaseId.substring( 0 , 3 );
			caseNumber = criminalCaseId.substring( 3 );
			
		}
		
		GetSuperviseeHeaderInfoEvent getEvent = 
            (GetSuperviseeHeaderInfoEvent) EventFactory.getInstance(CaseloadControllerServiceNames.GETSUPERVISEEHEADERINFO);
					getEvent.setDefendantId( csForm.getSuperviseeId() );
					SuperviseeInfoResponseEvent sprResponse = 
								(SuperviseeInfoResponseEvent) MessageUtil.postRequest(getEvent, SuperviseeInfoResponseEvent.class);
			if (sprResponse != null) {
					csForm.setLos( sprResponse.getSupervisionLevel());
					csForm.setOfficerName( sprResponse.getOfficerName());
					csForm.setProgramUnit( sprResponse.getProgramUnit() );
					csForm.setSuperviseeName( sprResponse.getDefendantName());
			}
		
		GetSuperviseeCaseOrdersEvent event = (GetSuperviseeCaseOrdersEvent) EventFactory.getInstance(SupervisionOrderControllerServiceNames.GETSUPERVISEECASEORDERS);
        event.setSuperviseeId(csForm.getSuperviseeId());
        List cases = MessageUtil.postRequestListFilter(event, SuperviseeCaseOrderResponseEvent.class);
		if (cases != null && !cases.isEmpty()) {
			String caseNum = cdi + caseNumber;
			Iterator iter = cases.iterator();
			while(iter.hasNext()){
				SuperviseeCaseOrderResponseEvent score = (SuperviseeCaseOrderResponseEvent) iter.next();
				if (score.getCaseNumber() != null && score.getCaseNumber().equalsIgnoreCase(caseNum)) {
					csForm.setOrderId(score.getSupervisionOrderId());
					csForm.setOffenseLiteral( score.getOffenseCodeDesc() );
					csForm.setSupervisionPeriodId( score.getSupervisionPeriodId() );
					break;
				}
			}
		}

		caForm.setSuperviseeNameStr(csForm.getSuperviseeName());
		caForm.setDefendantId(csForm.getSuperviseeId());
		caForm.setOfficerNameStr(csForm.getOfficerName());
		caForm.setLevelOfSupervision(csForm.getLos());
		caForm.setOffenseDesc(csForm.getOffenseLiteral());
		caForm.setProgramUnitName(csForm.getProgramUnit());
		caForm.setCdi(cdi);
		caForm.setCourtNumber( courtId );
		caForm.setCaseNum( caseNumber );
		csForm.setCdi(cdi);
		csForm.setCaseNum( caseNumber );
		csForm.setCourtNum( courtId );
		csForm.setTaskId((String) aRequest.getAttribute(ViolationReportConstants.TASK_ID));
		csForm.setTaskCreatorId((String) aRequest.getAttribute(ViolationReportConstants.CREATOR_ID));
		csForm.setTaskPSCreatorId((String) aRequest.getAttribute(ViolationReportConstants.STATUS_PENDING_SUBMISSION_CREATOR));
		csForm.setCurrentLogonId(SecurityUIHelper.getLogonId());
		shForm.clear();
		shForm.setSuperviseeNameDesc(csForm.getSuperviseeName());
	    shForm.setSuperviseeSpn(csForm.getSuperviseeId());
	    shForm.setOfficerNameDesc(csForm.getOfficerName());
	    shForm.setLOSDesc(csForm.getLos());
	    shForm.setProgramUnitDesc(csForm.getProgramUnit());
		shForm.setSuperviseeId(csForm.getSuperviseeId());
	    shForm.setCompliant(UICommonSupervisionHelper.isSuperviseeCompliant(shForm.getSuperviseeId()));	
		
        UICaseSummaryHelper.loadDropDown(csForm);
     	UICaseSummaryHelper.LoadCurrentCSInfo(csForm);
     	
     	String forwardStr = UIConstants.SUCCESS;
     	if (!ViolationReportConstants.STATUS_FILED.equalsIgnoreCase(csForm.getStatusId())){
     		csForm.setAllowUpdate(UIConstants.YES);	
     	}
     	if (csForm.getTaskId() != null && !csForm.getTaskId().equals("")){
     		csForm.setSecondaryAction(UIConstants.UPDATE); 
     	}
     	if (ViolationReportConstants.STATUS_DRAFT.equalsIgnoreCase(csForm.getStatusId())){
     		csForm.setAllowUpdate(UIConstants.YES);	
     		csForm.setSecondaryAction(UIConstants.UPDATE);  
     		csForm.setTaskflowInd(false);  // needs to be false so create page flow is used instead of detail page flow 
     		forwardStr = UIConstants.DRAFT_SUCCESS;
     	}
     	return aMapping.findForward(forwardStr);
	}	
}