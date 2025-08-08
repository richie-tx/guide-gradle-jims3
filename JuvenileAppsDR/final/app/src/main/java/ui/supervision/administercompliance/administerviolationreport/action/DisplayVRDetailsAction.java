//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\supervision\\administercompliance\\administerviolationreport\\action\\DisplayVRCommunityServiceSummaryAction.java

package ui.supervision.administercompliance.administerviolationreport.action;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administercaseload.GetSuperviseeHeaderInfoEvent;
import messaging.administercaseload.reply.SuperviseeInfoResponseEvent;
import messaging.cscdstaffposition.GetCSCDSupervisionStaffEvent;
import messaging.cscdstaffposition.reply.CSCDSupervisionStaffResponseEvent;
import messaging.supervisionorder.GetSuperviseeCaseOrdersEvent;
import messaging.supervisionorder.reply.SuperviseeCaseOrderResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CaseloadControllerServiceNames;
import naming.PDCodeTableConstants;
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
import ui.supervision.administercompliance.administerviolationreport.UIViolationReportHelper;
import ui.supervision.administercompliance.administerviolationreport.form.ViolationReportsForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class DisplayVRDetailsAction extends JIMSBaseAction {

	/**
	* @roseuid 47DA9D370287
	*/
	public DisplayVRDetailsAction() {

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
		ViolationReportsForm vrForm = (ViolationReportsForm) aForm;
		SuperviseeHeaderForm shForm = (SuperviseeHeaderForm) getSessionForm(aMapping, aRequest,	"superviseeHeaderForm", true);
		vrForm.clear();
		

        CSCDSupervisionStaffResponseEvent staffRep = getCSCDStaff( );
        boolean isUserAClo = isUserCLO( staffRep );
      
        vrForm.setCloPosition( isUserAClo );
     	vrForm.setTaskflowInd(true);
     	String ncResponseId = (String) aRequest.getAttribute(ViolationReportConstants.PARAM_NCRESPONSE_ID);
		String superviseeName = (String) aRequest.getAttribute(ViolationReportConstants.PARAM_SUPERVISEE_NAME);
		if (superviseeName != null){
			vrForm.setSuperviseeName(superviseeName.trim());
		}
		String defendant_id = (String) aRequest.getAttribute(ViolationReportConstants.PARAM_DEFENDANT_ID);
		String courtId = (String) aRequest.getAttribute(ViolationReportConstants.PARAM_COURT_NUMBER);
		String criminalCaseId = (String) aRequest.getAttribute(ViolationReportConstants.CASE_ID);
		String cdi = "";
		String caseNum = "";
		if ( !"".equals( criminalCaseId ) && criminalCaseId.length() > 14 ){
			
			cdi = criminalCaseId.substring( 0 , 3 );
			caseNum = criminalCaseId.substring( 3 );
			
		}
		
		CaseAssignmentForm caForm = (CaseAssignmentForm) getSessionForm(aMapping, aRequest,"caseAssignmentForm", true);

		GetSuperviseeHeaderInfoEvent getEvent = 
		            (GetSuperviseeHeaderInfoEvent) EventFactory.getInstance(CaseloadControllerServiceNames.GETSUPERVISEEHEADERINFO);
		getEvent.setDefendantId( defendant_id );
		SuperviseeInfoResponseEvent sprResponse = 
		            (SuperviseeInfoResponseEvent) MessageUtil.postRequest(getEvent, SuperviseeInfoResponseEvent.class);
		if (sprResponse != null) {
		            caForm.setLevelOfSupervision(sprResponse.getSupervisionLevel());
		            caForm.setOfficerNameStr(sprResponse.getOfficerName());
		            caForm.setProgramUnitName(sprResponse.getProgramUnit());
		            caForm.setSuperviseeNameStr(sprResponse.getDefendantName());
		}

		
		GetSuperviseeCaseOrdersEvent event = (GetSuperviseeCaseOrdersEvent) EventFactory.getInstance(SupervisionOrderControllerServiceNames.GETSUPERVISEECASEORDERS);
        event.setSuperviseeId( defendant_id );
        List cases = MessageUtil.postRequestListFilter(event, SuperviseeCaseOrderResponseEvent.class);
		if (cases != null && !cases.isEmpty()) {
			Iterator iter = cases.iterator();
			while(iter.hasNext()){
				SuperviseeCaseOrderResponseEvent score = (SuperviseeCaseOrderResponseEvent) iter.next();
				if (score.getCaseNumber() != null && score.getCaseNumber().equalsIgnoreCase( criminalCaseId )) {
					vrForm.setOrderId(score.getSupervisionOrderId());
					vrForm.setOffense( score.getOffenseCodeDesc());
					vrForm.setOrderActivationDate( DateUtil.dateToString( score.getOrderFiledDate(), ViolationReportConstants.DATE_FORMAT_YYYYMMDD) );
					vrForm.setSupervisionPeriodId( score.getSupervisionPeriodId() );
					break;
				}
			}
		}
		
		caForm.setDefendantId( defendant_id );
		caForm.setOffenseDesc(vrForm.getOffense());
		caForm.setCdi( cdi );
		caForm.setCaseNum( caseNum );
		caForm.setCourtNumber( courtId );
		vrForm.setTaskId((String) aRequest.getAttribute(ViolationReportConstants.TASK_ID));
		vrForm.setTaskCreatorId((String) aRequest.getAttribute(ViolationReportConstants.CREATOR_ID));
		vrForm.setTaskPSCreatorId((String) aRequest.getAttribute(ViolationReportConstants.STATUS_PENDING_SUBMISSION_CREATOR));
		vrForm.setViolationReportId( ncResponseId );
		vrForm.setCurrentLogonId(SecurityUIHelper.getLogonId());
		vrForm.setSuperviseeId( defendant_id );
		vrForm.setCourtNum( courtId );
		vrForm.setCdi( cdi );
		vrForm.setCaseNum( caseNum );
		shForm.clear();
	    shForm.setSuperviseeNameDesc(vrForm.getSuperviseeName());
	    shForm.setSuperviseeSpn(vrForm.getSuperviseeId());
	    shForm.setOfficerNameDesc(vrForm.getOfficerName());
	    shForm.setLOSDesc(vrForm.getLos());
	    shForm.setProgramUnitDesc(vrForm.getProgramUnit());
		shForm.setSuperviseeId(vrForm.getSuperviseeId());
	    shForm.setCompliant(UICommonSupervisionHelper.isSuperviseeCompliant( defendant_id ));	
		
        UIViolationReportHelper.loadDropDown(vrForm);
     	UIViolationReportHelper.LoadCurrentVRInfo(vrForm);
     	
     	String forwardStr = UIConstants.SUCCESS;
     	if (!ViolationReportConstants.STATUS_FILED.equalsIgnoreCase(vrForm.getStatusId())){
    		vrForm.setAllowUpdate(UIConstants.YES);	
     	}
     	if (vrForm.getTaskId() != null && !vrForm.getTaskId().equals("")){
     		vrForm.setSecondaryAction(UIConstants.UPDATE); 
     	}
     	if (ViolationReportConstants.STATUS_DRAFT.equalsIgnoreCase(vrForm.getStatusId())){
    		vrForm.setAllowUpdate(UIConstants.YES);	
    		vrForm.setSecondaryAction(UIConstants.UPDATE);  
         	vrForm.setTaskflowInd(false);  // needs to be false so create page flow is used instead of detail page flow 
     		forwardStr = UIConstants.DRAFT_SUCCESS;
     	}
     	
     	return aMapping.findForward(forwardStr);
	}	

	/**
	 * 
	 * @param myEvent
	 * @return
	 */
	private boolean isUserCLO( CSCDSupervisionStaffResponseEvent myEvent ){
					
		return myEvent.getJobTitleId().equals(PDCodeTableConstants.STAFF_JOB_TITLE_CLO) || 
										myEvent.getJobTitleId().equals(PDCodeTableConstants.STAFF_JOB_TITLE_CLOFLOATER);
	}
	
	private CSCDSupervisionStaffResponseEvent getCSCDStaff( ) {
			
		GetCSCDSupervisionStaffEvent myEvent= new GetCSCDSupervisionStaffEvent();
			
		myEvent.setUseStaffLogonId( true);
		myEvent.setAgencyId( SecurityUIHelper.getUserAgencyId().toString());
		myEvent.setStaffLogonId( SecurityUIHelper.getJIMSLogonId().toString()) ;
		
			CSCDSupervisionStaffResponseEvent sprResponse = (CSCDSupervisionStaffResponseEvent) MessageUtil.postRequest(myEvent,
				CSCDSupervisionStaffResponseEvent.class);;			
			
	
		return sprResponse;
	} 
}