package ui.supervision.managetasks.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerassessments.reply.AssessmentSummaryResponseEvent;
import messaging.supervisionorder.reply.SupervisionPeriodResponseEvent;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CSCAssessmentConstants;
import naming.PDCodeTableConstants;
import naming.UIConstants;
import naming.ViolationReportConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administerassessments.AdminAssessmentsHelper;
import ui.supervision.administerassessments.form.AssessmentForm;
import ui.supervision.administerassessments.form.SCSInterviewAssessmentForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class CompleteSCSInterviewAction extends JIMSBaseAction {	

	/**
	 * @roseuid 464368F103D5
	 */
	public CompleteSCSInterviewAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link", "completeSCSInterview");		 
	}

	public ActionForward completeSCSInterview(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {	
		
		AssessmentForm assessmentForm = (AssessmentForm)aForm;
		SCSInterviewAssessmentForm scsInterviewForm = (SCSInterviewAssessmentForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_ASSESS_SCS_INTERVIEW_ASSESSMENT_FORM, true);
		SuperviseeHeaderForm superviseeHeaderForm=(SuperviseeHeaderForm)this.getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
          
//		clear the assessment and scsInterview form
		assessmentForm.clearSuperviseeDetails();
		assessmentForm.clear();
		scsInterviewForm.clearAll();		
		
		String taskId = (String) aRequest.getAttribute("taskId");
		String spnStr = (String) aRequest.getAttribute( ViolationReportConstants.PARAM_DEFENDANT_ID );
		String assessmentId = (String) aRequest.getAttribute( "assessmentId" );
		while (spnStr.length() < 8) {
			spnStr = "0" + spnStr;
		}
		if (StringUtils.isNotEmpty(superviseeHeaderForm.getSuperviseeId()) || !superviseeHeaderForm.getSuperviseeId().equals(spnStr)){
			superviseeHeaderForm.setSuperviseeId(spnStr);
			UICommonSupervisionHelper.populateSuperviseeHeaderForm(superviseeHeaderForm);
		}
		assessmentForm.setDefendantId(spnStr);
		assessmentForm.setSupervisionPeriod(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV);
		
		RequestEvent reqEvent = AdminAssessmentsHelper.getAssessmentsSummaryEvent(assessmentForm);
		CompositeResponse compResponse = this.postRequestEvent(reqEvent);
		
		SupervisionPeriodResponseEvent supervisionPeriodRespEvt = (SupervisionPeriodResponseEvent)MessageUtil.filterComposite(compResponse, SupervisionPeriodResponseEvent.class);
		if(supervisionPeriodRespEvt != null)
		{
		AdminAssessmentsHelper.setSupervisionPeriodResponseEvent(assessmentForm, supervisionPeriodRespEvt);
		}
		
		scsInterviewForm.setTaskId(taskId);
		scsInterviewForm.setAction(UIConstants.UPDATE); 
	    scsInterviewForm.setSecondaryAction("");
	    scsInterviewForm.setDefendantId(spnStr);
	    scsInterviewForm.setSupervisionBeginDate(assessmentForm.getSupervisionBeginDate());
	    scsInterviewForm.setSupervisionEndDate(assessmentForm.getSupervisionEndDate());
	    scsInterviewForm.setSupervisionPeriod(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV);
	    scsInterviewForm.setAssessmentTypeId(PDCodeTableConstants.CSC_ASSESSMENTS_TYPE_ID_SCS_INTERVIEW);
		
		
//		get the collection of assessmentsSummary response objects
		Collection assessmentCollection = MessageUtil.compositeToCollection(compResponse, AssessmentSummaryResponseEvent.class);
		if(assessmentCollection.size()>0){
			int size = assessmentCollection.size();
			List assessmentsList = (List) assessmentCollection;
			for (int s=0; s<size; s++){
				AssessmentSummaryResponseEvent assessment = (AssessmentSummaryResponseEvent) assessmentsList.get(s);
				if(StringUtils.isNotEmpty(assessment.getAssessmentId()) && assessmentId.equals(assessment.getAssessmentId()) ) {
					StringBuffer selectedAssessBeanId = new StringBuffer();
					selectedAssessBeanId.append(assessment.getAssessmentTypeId());
					selectedAssessBeanId.append(assessment.getMasterAssessmentId());
					selectedAssessBeanId.append(assessment.getAssessmentId());
//					populate the AssessmentForm with AssessmentSummaryResponseEvent 
					AdminAssessmentsHelper.setAssessmentSummaryResponseEventCollection(assessmentForm, (ArrayList)assessmentCollection);
				    scsInterviewForm.setSelectedAssessmentBeanId(selectedAssessBeanId.toString());
					break;
				}
			}
		}		
		return aMapping.findForward(UIConstants.SUCCESS);
	}	
}
