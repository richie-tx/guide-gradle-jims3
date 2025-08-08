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
import ui.supervision.AssignReassignSuperviseeHelper;
import ui.supervision.administercompliance.administercasesummary.form.CaseSummaryForm;
import ui.supervision.administercompliance.administerviolationreport.UIViolationReportHelper;

public class SubmitCaseSummaryCourtActionsAction extends JIMSBaseAction {

	/**
	 * @roseuid 47DA9D4402B6
	 */
	public SubmitCaseSummaryCourtActionsAction() {

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
    	event.setRequestType(ViolationReportConstants.REQUEST_RECOMMENDATION);
    	event.setComments(csForm.getSummaryOfCourtActions());
    	event.setCommentType(ViolationReportConstants.SUGGESTED_COURT_ACTION);
    	event.setNcResponseId(csForm.getViolationReportId());
    	event.setFiling(true);
    	if(StringUtils.isNotEmpty(csForm.getCourtActionfiledDate())){
	    	String filingDate = csForm.getCourtActionfiledDate();
	    	Timestamp presentDate = UIViolationReportHelper.convertDateToTimeStamp(filingDate, "00:00", "AM");
	    	event.setFilledDate(presentDate);
    	}
    	event.setStatusId(ViolationReportConstants.STATUS_FILED);
    	event.setPositionIdOfPresentedBy("");
    	event.setSummaryOfCourtActions(csForm.getSummaryOfCourtActions());	
    	event.setTaskId(csForm.getTaskId());
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
    	csForm.setConfirmationMessage("Case Summary successfully filed. Court actions successfully processed.");
    	csForm.setFileDateStr(csForm.getCourtActionfiledDate());
    	csForm.setCurrentCourtActionsList(csForm.getCreate2ElementsList());
     	csForm.setAllowUpdate(UIConstants.NO);
     	csForm.setOrderId(findOrderId(csForm.getSuperviseeId(), csForm.getCdi(), csForm.getCaseNum()));
		createCasenote( csForm );
     	
		
		//Close cstask after File
		AssignReassignSuperviseeHelper helper = AssignReassignSuperviseeHelper.getInstance();
		helper.closePreviousTask( csForm.getTaskId() );
		
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
	
	/**
	 * @param vrForm
	 * Method to create CaseNote
	 */
	public static void createCasenote( CaseSummaryForm csForm ) {
		String orderId = csForm.getOrderId();
		String superviseeId = csForm.getSuperviseeId();
		String note = "";
		StringBuffer notes = new StringBuffer("");
		notes.append("Present Date - ");
		notes.append(csForm.getCourtActionfiledDate());
		notes.append("<br/>Court Actions - ");
		List currentCourtActionsList = csForm.getCurrentCourtActionsList();	
		if (currentCourtActionsList != null) {
			StringBuffer courtActions = new StringBuffer();
			Iterator currentCourtActionsIter = currentCourtActionsList.iterator();
			while (currentCourtActionsIter.hasNext()) {
				CodeResponseEvent currentCourtAction = (CodeResponseEvent) currentCourtActionsIter.next(); 
				courtActions.append(currentCourtAction.getDescription());
				courtActions.append(", ");
			}
			notes.append(courtActions.toString());
		}
		notes.append("<br/>Summary of Court Actions: ");
		notes.append( csForm.getSummaryOfCourtActions() );
		note = notes.toString();
		
        UpdateCasenoteEvent updateCasenote = (UpdateCasenoteEvent) EventFactory
        	.getInstance(CasenoteControllerServiceNames.UPDATECASENOTE);
		updateCasenote.setEntryDate(DateUtil.stringToDate( csForm.getCourtActionfiledDate(), DateUtil.DATE_FMT_1));
		updateCasenote.setHowGeneratedId(PDCodeTableConstants.CASENOTE_SYSTEM_GENERATED_ID);
		updateCasenote.setNotes(note);
		updateCasenote.setSuperviseeId(superviseeId);
		updateCasenote.setSupervisionOrderId(orderId);
		updateCasenote.setSupervisionPeriodId( csForm.getSupervisionPeriodId() );
		updateCasenote.setSaveAsDraft(true);
		
		String assignmentCodeId = getCasenoteSubjectCodeId();
		Collection subjects = new ArrayList();
		subjects.add(assignmentCodeId);
		
		updateCasenote.setSubjects(subjects);		
		updateCasenote.setContactMethodId(PDCodeTableConstants.CASENOTE_CONTACT_METHOD_COURT);		
		updateCasenote.setContextType(PDCodeTableConstants.CASENOTE_TYPE_ID_SUPERVISION);
		
		CasenoteResponseEvent response = (CasenoteResponseEvent) MessageUtil.postRequest( updateCasenote , CasenoteResponseEvent.class );	
		
		if (response != null) {
			csForm.setCasenoteId( response.getCasenoteId() );
		}
	}
	
	/**
     *  
     */
    private static String getCasenoteSubjectCodeId()
    {
        GetSupervisionCodesEvent request = (GetSupervisionCodesEvent) EventFactory
                .getInstance(CodeTableControllerServiceNames.GETSUPERVISIONCODES);
        request.setCodeTableName(CaseloadConstants.CASENOTE_SUBJECT_CODE_TABLE_NAME);
        request.setCode( PDCodeTableConstants.CASENOTE_SUBJECT_CASESUMMARY );
        request.setAgencyId(SecurityUIHelper.getUserAgencyId());

        CodeResponseEvent codeResponse = (CodeResponseEvent) MessageUtil.postRequest(request, CodeResponseEvent.class);

        return codeResponse.getCodeId();
    }

}