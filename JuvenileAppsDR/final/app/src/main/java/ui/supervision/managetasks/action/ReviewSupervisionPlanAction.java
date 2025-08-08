package ui.supervision.managetasks.action;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.supervisionorder.reply.SupervisionPeriodResponseEvent;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CSCAssessmentConstants;
import naming.SupervisionPlanConstants;
import naming.UIConstants;
import naming.ViolationReportConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administerassessments.AdminAssessmentsHelper;
import ui.supervision.administerassessments.form.AssessmentForm;
import ui.supervision.administersupervisionplan.form.SupervisionPlanForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;

public class ReviewSupervisionPlanAction extends JIMSBaseAction {	

	/**
	 * @roseuid 464368F103D5
	 */
	public ReviewSupervisionPlanAction() {

	}

	protected void addButtonMapping(Map keyMap) {
		keyMap.put("button.link", "reviewSupervisionPlan");		 
	}

	public ActionForward reviewSupervisionPlan(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {	
		
        AssessmentForm assessmentForm = (AssessmentForm)aForm;
          
//		clear the assessment form and 
		assessmentForm.clearSuperviseeDetails();
		assessmentForm.clear();
		
		SupervisionPlanForm supervisionPlanForm = (SupervisionPlanForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_SUPERVISION_PLAN_FORM, true);
		supervisionPlanForm.clearAll();		
		//assessmentForm.setSupervisionPeriod(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV);
			
		
			
			String taskId = (String) aRequest.getAttribute("taskId");
			String spnStr = (String) aRequest.getAttribute( ViolationReportConstants.PARAM_DEFENDANT_ID );
			String supPlanId = (String) aRequest.getAttribute( SupervisionPlanConstants.SUPERVISION_PLAN_ID );
			
		
			supervisionPlanForm.setSupervisionPlanId(supPlanId);
			supervisionPlanForm.setSelectedSupervisionPlanId(supPlanId);
			supervisionPlanForm.setTaskId(taskId);
			
			while (spnStr.length() < 8) {
				spnStr = "0" + spnStr;
			}
			
			assessmentForm.setDefendantId(spnStr);
			assessmentForm.setSupervisionPeriod(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV);
			
			SuperviseeHeaderForm superviseeHeaderForm=(SuperviseeHeaderForm)this.getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
			if (superviseeHeaderForm.getSuperviseeId() == null || superviseeHeaderForm.getSuperviseeId().trim().equals("") || !superviseeHeaderForm.getSuperviseeId().equals(spnStr)){
				superviseeHeaderForm.setSuperviseeId(spnStr);
				UICommonSupervisionHelper.populateSuperviseeHeaderForm(superviseeHeaderForm);
			}
			
			RequestEvent reqEvent = AdminAssessmentsHelper.getAssessmentsSummaryEvent(assessmentForm);
			CompositeResponse compResponse = this.postRequestEvent(reqEvent);
			
			SupervisionPeriodResponseEvent supervisionPeriodRespEvt = (SupervisionPeriodResponseEvent)MessageUtil.filterComposite(compResponse, SupervisionPeriodResponseEvent.class);
			if(supervisionPeriodRespEvt != null)
			{
			AdminAssessmentsHelper.setSupervisionPeriodResponseEvent(assessmentForm, supervisionPeriodRespEvt);
			}
			
			
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}
	
}
