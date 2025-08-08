//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administercasehistory\\action\\HandleCaseHistorySelectionAction.java

package ui.supervision.administercompliance.administercasehistory.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetSuperviseeHeaderInfoEvent;
import messaging.administercaseload.reply.SuperviseeInfoResponseEvent;
import messaging.administercompliance.GetNCResponseDetailsEvent;
import messaging.administercompliance.reply.NCCommentResponseEvent;
import messaging.administercompliance.reply.NCResponseResponseEvent;
import messaging.supervisionorder.reply.SuperviseeCaseOrderResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadControllerServiceNames;
import naming.ComplianceControllerServiceNames;
import naming.UIConstants;
import naming.ViolationReportConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.administercaseload.form.CaseAssignmentForm;
import ui.supervision.administercompliance.administercasehistory.UICaseHistoryHelper;
import ui.supervision.administercompliance.administercasehistory.form.CaseHistoryForm;
import ui.supervision.administercompliance.administercasesummary.UICaseSummaryHelper;
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;
import ui.supervision.administercompliance.administerviolationreport.UIViolationReportHelper;
import ui.supervision.administercompliance.administerviolationreport.form.ViolationReportsForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class HandleCaseHistorySelectionAction extends JIMSBaseAction {

	/**
	 *  @roseuid 464368F103D5
	 */
	public HandleCaseHistorySelectionAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.violationReports", "violationReports");
		keyMap.put("button.caseSummaryList", "caseSummaryList");
		keyMap.put("button.motions", "motions");
	}
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */ 
	public ActionForward violationReports(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseHistoryForm chForm = (CaseHistoryForm) aForm;
		ViolationReportsForm vrForm = (ViolationReportsForm) getSessionForm(aMapping, aRequest,	"violationReportsForm", true);
		vrForm.clear();
		CaseAssignmentForm caForm = (CaseAssignmentForm) getSessionForm(aMapping, aRequest,	"caseAssignmentForm", true);
		
		String spnStr = chForm.getSuperviseeId();
		if (spnStr == null){
			SuperviseeHeaderForm shForm = (SuperviseeHeaderForm) getSessionForm(aMapping, aRequest,	"superviseeHeaderForm", true);
			spnStr = shForm.getSuperviseeSpn();
		}
		while (spnStr.length() < 8) {
			spnStr = "0" + spnStr;
		}
		vrForm.setSuperviseeId(spnStr);
		caForm.setDefendantId(spnStr);
		setSuperviseeHeaderInfo(caForm, chForm.getCaseHistoryList(), chForm.getSelectedOrderId());
		vrForm.setCaseNum(caForm.getCaseNum());
		vrForm.setCdi(caForm.getCdi());
		vrForm.setOrderId(chForm.getSelectedOrderId());
		vrForm.setOrderActivationDate(DateUtil.dateToString(caForm.getOrderFiledDate(), ViolationReportConstants.DATE_FORMAT_YYYYMMDD));
	   	int len = chForm.getCaseHistoryList().size();
		for (int x=0; x < len; x++)
		{
			SuperviseeCaseOrderResponseEvent score = (SuperviseeCaseOrderResponseEvent) chForm.getCaseHistoryList().get(x);
			if (score.getSupervisionOrderId() != null && score.getSupervisionOrderId().equalsIgnoreCase(chForm.getSelectedOrderId()))
			{	
				vrForm.setOrderStatus( score.getOrderStatus());
				vrForm.setSupervisionPeriodId( score.getSupervisionPeriodId() );
				break;
			} 
		} 
		
		List vrReports = UICaseHistoryHelper.getViolationReports(new StringBuffer(vrForm.getCdi()).append(vrForm.getCaseNum()).toString());
		if (vrReports == null || vrReports.isEmpty()) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","No reports found for this supervisee on this case"));
			saveErrors(aRequest, errors);
			vrForm.setViolationReportsDisplayList( new ArrayList() );
			// rjc - check both VR and CS reports types for the comment type RFT (RequestForTransfer) and see what the last "PRESENTED"
	        // report of either had as a value for the new report and set the setPreviousReasonForTransferComments() field to this value (YES or NO)
	        vrForm.setPreviousReasonForTransferComments(getCaseLevelPreviousReasonForTransferCommentsValue(vrForm.getCdi(), vrForm.getCaseNum()));
		} else {
			vrForm.setViolationReportsDisplayList(sortEventList(vrReports));
			CompositeResponse prevResponses = getPreviousComments( vrReports );
			// Clear Previous Comments
			vrForm.clearPreviousComments();
			List cre = MessageUtil.compositeToList( prevResponses, NCCommentResponseEvent.class );
	        if (cre != null && cre.size() > 0){
	        	for (int x =0; x < cre.size(); x++){
	        		NCCommentResponseEvent nccre = (NCCommentResponseEvent) cre.get(x);
	        		if (nccre.getReportType() != null){
	        			if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_LAW_VIOLATION)){
	        				vrForm.setPreviousLawViolationsComments(nccre.getComment());
	        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_DELINQUENT_FEE)){
	        				vrForm.setPreviousFeeHistoryComments(nccre.getComment());
	        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_MENTAL_HEALTH_COMMENTS)){
	        				vrForm.setPreviousMentalHealthComments(nccre.getComment());
	        			}else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_MENTAL_HEALTH_DIAGNOSIS)){
	        				vrForm.setPreviousMentalHealthDiagnosis(nccre.getComment());
	        			}else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_REPORTING)){
	        				vrForm.setPreviousReportingHistoryComments(nccre.getComment());
	        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_EMPLOYMENT)){
	        				vrForm.setPreviousEmploymentHistoryComments(nccre.getComment());
	        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_TREATMENT)){
	        				vrForm.setPreviousTreatmentIssuesComments(nccre.getComment());
	        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_COMMUNITY_SERVICE)){
	        				vrForm.setPreviousCommunityServiceComments(nccre.getComment());
	        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_POSITIVE_UA)){
	        				vrForm.setPreviousPositiveUrinalysisComments(nccre.getComment());
	        			} else if (nccre.getReportType().equalsIgnoreCase("RCM")) {
	       	       			if (ViolationReportConstants.SUGGESTED_COURT_ACTION.equalsIgnoreCase( nccre.getCommentType() )){
	        	    			vrForm.setPreviousSummaryOfCourtActions(nccre.getComment());
	       	       				} else {        				
	        	    			vrForm.setPreviousRecommendations(nccre.getComment());
	        	    		}	
	        		   	}
	        		}	
	     		} // end for loop
	     	}
	        // rjc - check both VR and CS reports types for the comment type RFT (RequestForTransfer) and see what the last "PRESENTED"
	        // report of either had as a value for the new report and set the setPreviousReasonForTransferComments() field to this value
	        vrForm.setPreviousReasonForTransferComments(getCaseLevelPreviousReasonForTransferCommentsValue(vrForm.getCdi(), vrForm.getCaseNum()));
		} 
		UIViolationReportHelper.loadDropDown(vrForm);
		vrForm.setConfirmationMessage("");
		vrForm.setStatusId("");
		vrForm.setStatusDesc("");
		vrForm.setViolationReportId("");
		return aMapping.findForward(UIConstants.VIOLATION_REPORTS_SUCCESS);
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward caseSummaryList(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		CaseHistoryForm chForm = (CaseHistoryForm) aForm;
		CaseSummaryForm csForm = (CaseSummaryForm) getSessionForm(aMapping, aRequest, "caseSummaryForm", true);		
		csForm.clear();
		CaseAssignmentForm caForm = (CaseAssignmentForm) getSessionForm(aMapping, aRequest,	"caseAssignmentForm", true);
		String spnStr = chForm.getSuperviseeId();
		if (spnStr == null){
			SuperviseeHeaderForm shForm = (SuperviseeHeaderForm) getSessionForm(aMapping, aRequest,	"superviseeHeaderForm", true);
			spnStr = shForm.getSuperviseeSpn();
		}
		
		while (spnStr.length() < 8) {
			spnStr = "0" + spnStr;
		}
		csForm.setSuperviseeId(spnStr);
		caForm.setDefendantId(spnStr);
		setSuperviseeHeaderInfo(caForm, chForm.getCaseHistoryList(), chForm.getSelectedOrderId());
		csForm.setCaseNum(caForm.getCaseNum());
		csForm.setCdi(caForm.getCdi());
		csForm.setOrderId(chForm.getSelectedOrderId());
		csForm.setActivationDate(DateUtil.dateToString(caForm.getOrderFiledDate(), ViolationReportConstants.DATE_FORMAT_YYYYMMDD));

	   	int len = chForm.getCaseHistoryList().size();
		for (int x=0; x < len; x++)
		{
			SuperviseeCaseOrderResponseEvent score = (SuperviseeCaseOrderResponseEvent) chForm.getCaseHistoryList().get(x);
			if (score.getSupervisionOrderId() != null && score.getSupervisionOrderId().equalsIgnoreCase(chForm.getSelectedOrderId()))
			{	
				csForm.setOrderStatus(score.getOrderStatus());
				break;
			} 
		} 
		List csReports = UICaseHistoryHelper.getCaseSummaries(new StringBuffer(csForm.getCdi()).append(csForm.getCaseNum()).toString());
		if (csReports == null || csReports.isEmpty()) {
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","No reports found for this supervisee on this case"));
			saveErrors(aRequest, errors);
			csForm.setCaseSummaryDisplayList(new ArrayList());
			// rjc - check both VR and CS reports types for the comment type RFT (RequestForTransfer) and see what the last "PRESENTED"
	        // report of either had as a value for the new report and set the setPreviousReasonForTransferComments() field to this value (YES or NO)
	        csForm.setPreviousReasonForTransferComments(getCaseLevelPreviousReasonForTransferCommentsValue(csForm.getCdi(), csForm.getCaseNum()));
		} else {
			csForm.setCaseSummaryDisplayList(sortEventList(csReports));
			
			// Clear previous comments
			csForm.clearPreviousComments();
			CompositeResponse prevResponses = getPreviousComments( csReports );
			
			List cre = MessageUtil.compositeToList( prevResponses, NCCommentResponseEvent.class );
	        if (cre != null && cre.size() > 0){
	        	for (int x =0; x < cre.size(); x++){
	        		NCCommentResponseEvent nccre = (NCCommentResponseEvent) cre.get(x);
	        		if ( nccre.getReportType() != null ){
	        			if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_LAW_VIOLATION)){
	        				csForm.setPreviousLawViolationsComments(nccre.getComment());
	        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_DELINQUENT_FEE)){
	        				csForm.setPreviousFeeHistoryComments(nccre.getComment());
	        			}else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_MENTAL_HEALTH_COMMENTS)){
	        				csForm.setPreviousMentalHealthComments(nccre.getComment());
	        			}else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_MENTAL_HEALTH_DIAGNOSIS)){
	        				csForm.setPreviousMentalHealthDiagnosis(nccre.getComment());
	        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_REPORTING)){
	        				csForm.setPreviousReportingHistoryComments(nccre.getComment());
	        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_EMPLOYMENT)){
	        				csForm.setPreviousEmploymentHistoryComments(nccre.getComment());
	        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_TREATMENT)){
	        				csForm.setPreviousTreatmentIssuesComments(nccre.getComment());
	        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_COMMUNITY_SERVICE)){
	        				csForm.setPreviousCommunityServiceComments(nccre.getComment());
	        			} else if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_POSITIVE_UA)){
	        				csForm.setPreviousPositiveUrinalysisComments(nccre.getComment());
	        			} else if (nccre.getReportType().equalsIgnoreCase("RCM")) {
	       	       			if (ViolationReportConstants.SUGGESTED_COURT_ACTION.equalsIgnoreCase( nccre.getCommentType() )){
	       	       				csForm.setPreviousSummaryOfCourtActions(nccre.getComment());
	       	       				} else {        				
	       	       				csForm.setPreviousRecommendations(nccre.getComment());
	        	    		}	
	        		   	}
	        		}	
	     		} // end for loop
	     	}
	        // rjc - check both VR and CS reports types for the comment type RFT (RequestForTransfer) and see what the last "PRESENTED"
	        // report of either had as a value for the new report and set the setPreviousReasonForTransferComments() field to this value (YES or NO)
	        csForm.setPreviousReasonForTransferComments(getCaseLevelPreviousReasonForTransferCommentsValue(csForm.getCdi(), csForm.getCaseNum()));
		} 
		UICaseSummaryHelper.loadDropDown(csForm);
		csForm.setConfirmationMessage("");
		csForm.setStatusId("");
		csForm.setStatusDesc("");
		csForm.setViolationReportId("");
		return aMapping.findForward(UIConstants.CASE_SUMMARY_SUCCESS);
	}	

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 */
	public ActionForward motions(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		ActionErrors errors = new ActionErrors();
		errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","Motions is Not available at this time"));
		saveErrors(aRequest, errors);		
		return aMapping.findForward(UIConstants.MOTIONS_SUCCESS);
	}	
	
	/**
	 * @param form
	 * @param superviseeId
	 * @return
	 */
	private void setSuperviseeHeaderInfo(CaseAssignmentForm caForm, List caseHistoryList, String orderId) {
	   	int len = caseHistoryList.size();
		for (int x=0; x < len; x++)
		{
			SuperviseeCaseOrderResponseEvent score = (SuperviseeCaseOrderResponseEvent) caseHistoryList.get(x);
			if (score.getSupervisionOrderId() != null && score.getSupervisionOrderId().equalsIgnoreCase(orderId))
			{	
				caForm.setCdi(score.getCdi());
				caForm.setCaseNum(score.getCaseNumber());
				caForm.setCourtNumber(score.getDisplayCourtId());
				caForm.setOffenseDesc(score.getOffenseCodeDesc());
				caForm.setOrderFiledDate(score.getOrderFiledDate());
				break;
			} 
		} 
		GetSuperviseeHeaderInfoEvent getEvent = (GetSuperviseeHeaderInfoEvent) EventFactory
				.getInstance(CaseloadControllerServiceNames.GETSUPERVISEEHEADERINFO);
		getEvent.setDefendantId(caForm.getDefendantId());
		SuperviseeInfoResponseEvent sprResponse = (SuperviseeInfoResponseEvent) MessageUtil.postRequest(getEvent,
				SuperviseeInfoResponseEvent.class);
		if (sprResponse != null) {
			caForm.setLevelOfSupervision(sprResponse.getSupervisionLevel());
			caForm.setOfficerNameStr(sprResponse.getOfficerName());
			caForm.setProgramUnitName(sprResponse.getProgramUnit());
			caForm.setSuperviseeNameStr(sprResponse.getDefendantName());
		}
	}	
	
	/**
	 * @param suggestedcourtactionList
	 * @return List
	 */
	public static List sortEventList(List theList){

		Collections.sort(theList, NCResponseResponseEvent.NCResponseResponseEventDateComparator);
		return theList;
	} 
	
   private CompositeResponse getPreviousComments( List reports ){
		
    	List sortedPrevVrReports = new ArrayList();
    	String prevRptId = "";
    	
		SortedMap map = new TreeMap();
		for ( int x = 0; x < reports.size(); x++ ){
			NCResponseResponseEvent rre = (NCResponseResponseEvent) reports.get(x);	
			if ( rre.getFiledDate() != null ){
				map.put( rre.getFiledDate(), rre.getNcResponseId());
			}
		}
		sortedPrevVrReports = new ArrayList( map.values());
		Collections.reverse( sortedPrevVrReports );
		if ( sortedPrevVrReports.size() > 0 ){
			prevRptId = (String) sortedPrevVrReports.get( 0 );
		}

    	GetNCResponseDetailsEvent event = (GetNCResponseDetailsEvent) EventFactory.getInstance
    										(ComplianceControllerServiceNames.GETNCRESPONSEDETAILS);
        event.setNcResponseId( prevRptId );
    	
    	return MessageUtil.postRequest( event );   	
    	
    }
   
   /**
    * rjc -retrieve comments from both types of reports and check if either one has a report that is a final state of PRESENTED.
    * if there is one or both, determine the latest report from both categories and return the value of the comment
    * to be set as the previousReasonForTransfer field on the form.  Explanation: each report saves its own comment for isExtended field,
    * but the business rule is to load up the previousReasonForTransfer field at the case level, not the comment/report level. This means
    * that if "either" or "both" report types has had a previous report taken to "PRESENTED", the latest value set for this field /comment
    * should be used as a default for each new "DRAFT" report created... the value should be at the "case" level. 
    */
   private String getCaseLevelPreviousReasonForTransferCommentsValue(String cdi, String caseNum){
	   
	   String previousReason = "";
	   List allReports = new ArrayList();
	   List csReports = UICaseHistoryHelper.getViolationReports(new StringBuffer(cdi).append(caseNum).toString());
	   allReports.addAll(csReports);
	   List vrReports = UICaseHistoryHelper.getCaseSummaries(new StringBuffer(cdi).append(caseNum).toString());
	   allReports.addAll(vrReports);
	   if(allReports != null && !allReports.isEmpty() ){
		   sortEventList(allReports);
		   CompositeResponse prevTranfserReasonResponses = getPreviousComments(allReports);
		   List commentEvents = MessageUtil.compositeToList( prevTranfserReasonResponses, NCCommentResponseEvent.class );
		   if (commentEvents != null && commentEvents.size() > 0){
	        	for (int x =0; x < commentEvents.size(); x++){
	        		NCCommentResponseEvent nccre = (NCCommentResponseEvent) commentEvents.get(x);
	        		if (nccre.getReportType() != null){
	        			if (nccre.getReportType().equalsIgnoreCase(ViolationReportConstants.REQUEST_REASON_FOR_TRANSFER)){
	        				// if the comment is null, blank or NO, set to blank
	        				if(nccre.getComment() == null || nccre.getComment().equalsIgnoreCase("NO")){
	        					previousReason = "";
	        				}else{
	        					previousReason = nccre.getComment();
	        				}
	        			}
	        		}
	        	}
		   }		   
	   }
	   return previousReason;
   }
}