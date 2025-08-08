package ui.supervision.administerassessments.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.administerassessments.reply.AssessmentSummaryResponseEvent;
import messaging.administerassessments.reply.HistoricalAssessmentResponseEvent;
import messaging.administersupervisionplan.GetSupervisionPlanSummaryEvent;
import messaging.administersupervisionplan.reply.SupervisionPlanSummaryResponseEvent;
import messaging.supervisionorder.reply.SupervisionPeriodResponseEvent;
import mojo.km.messaging.RequestEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.CSCAssessmentConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.supervision.UICommonSupervisionHelper;
import ui.supervision.administerassessments.AdminAssessmentsHelper;
import ui.supervision.administerassessments.AssessmentLightBean;
import ui.supervision.administerassessments.form.AssessmentForm;
import ui.supervision.administerassessments.form.ForceFieldAssessmentForm;
import ui.supervision.administerassessments.form.LSIRAssessmentForm;
import ui.supervision.administerassessments.form.SCSAssessmentForm;
import ui.supervision.administerassessments.form.SCSInterviewAssessmentForm;
import ui.supervision.administerassessments.form.WisconsinAssessmentForm;
import ui.supervision.administersupervisionplan.AdminSupervisionPlanHelper;
import ui.supervision.administersupervisionplan.SupervisionPlanBean;
import ui.supervision.administersupervisionplan.form.SupervisionPlanForm;
import ui.supervision.supervisee.form.SuperviseeHeaderForm;


/**
 * 
 * @author cc_bjangay
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class DisplayAssessmentSummaryAction extends JIMSBaseAction
{
	
	/* (non-Javadoc)
	 * @see ui.action.JIMSBaseAction#addButtonMapping(java.util.Map)
	 */
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.advancedSuperviseeSearch", "casenotesSuperviseeSearch");
		keyMap.put("button.casenotes", "casenotes");
		
		keyMap.put("button.link","link");
		keyMap.put("button.viewHistoricalAssessments","viewHistory");
		
		keyMap.put("button.filter","filter");
		keyMap.put("button.refresh","refresh");
	}
	
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward casenotesSuperviseeSearch(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		
		System.out.println("AdministerAssessments::DisplayAssessmentSummaryAction::casenotesSuperviseeSearch()::Method Begin");
		
		AssessmentForm assessmentForm = (AssessmentForm) aForm;
		assessmentForm.clearParentInfo();
		// set up what is necessary to be able to go back to casenotes page on click of Cancel button
		ActionForward myForward = link(aMapping, assessmentForm, aRequest, aResponse);
		assessmentForm.setParentPage(CSCAssessmentConstants.CASENOTE_SUPERVISEE_SEARCH);
		return myForward;
	}

	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward casenotes(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException {
		
		System.out.println("AdministerAssessments::DisplayAssessmentSummaryAction::casenotes()::Method Begin");
		
		AssessmentForm assessmentForm = (AssessmentForm) aForm;
		assessmentForm.clearParentInfo();
//		 set up what is necessary to be able to go back to casenotes page on click of Cancel button
		ActionForward myForward = link(aMapping, assessmentForm, aRequest, aResponse);
		assessmentForm.setParentPage(CSCAssessmentConstants.CASENOTE_JOURNAL);
		return myForward;
	}
	
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward link(ActionMapping aMapping,ActionForm aForm,HttpServletRequest aRequest,HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{		
		System.out.println("AdministerAssessments::DisplayAssessmentSummaryAction::link()::Method Begin");
		
		AssessmentForm assessmentForm = (AssessmentForm)aForm;
		
//		clear the assessment form and Supervisee details
		assessmentForm.clearSuperviseeDetails();
		assessmentForm.clear();
		clearAssessmentForms(aMapping,aRequest);
		
		assessmentForm.setSupervisionPeriod(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV);
	
		String spnStr = aRequest.getParameter("superviseeId");
		aRequest.setAttribute("superviseeId", "");
		if (spnStr == null || spnStr.trim().equals("")) {
			SuperviseeHeaderForm myHeaderForm=(SuperviseeHeaderForm)getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);			
			spnStr = myHeaderForm.getSuperviseeSpn();
		}	
		if (spnStr == null || spnStr.trim().equals("")) { 
				ActionErrors errors = new ActionErrors();
				errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.generic","Not able to find supervisee information."));
				saveErrors(aRequest, errors);
				return aMapping.findForward(UIConstants.SUCCESS);
	    }
		while (spnStr.length() < 8) {
			spnStr = "0" + spnStr;
		}
		assessmentForm.setDefendantId(spnStr);
		
		SuperviseeHeaderForm superviseeHeaderForm=(SuperviseeHeaderForm)this.getSessionForm(aMapping,aRequest,UIConstants.SUPERVISEE_HEADER_FORM,true);
		if (superviseeHeaderForm.getSuperviseeId() == null || superviseeHeaderForm.getSuperviseeId().trim().equals("") || !superviseeHeaderForm.getSuperviseeId().equals(spnStr)){
			superviseeHeaderForm.setSuperviseeId(spnStr);
			UICommonSupervisionHelper.populateSuperviseeHeaderForm(superviseeHeaderForm);
		}
// need to reload compliance value in case value has changed.		
		superviseeHeaderForm.setCompliant(UICommonSupervisionHelper.isSuperviseeCompliant(spnStr));
		
		RequestEvent reqEvent = AdminAssessmentsHelper.getAssessmentsSummaryEvent(assessmentForm);
		CompositeResponse compResponse = this.postRequestEvent(reqEvent);
		
//		get the collection of assessmentsSummary response objects
		Collection assessmentsSummaryList = MessageUtil.compositeToCollection(compResponse, AssessmentSummaryResponseEvent.class);
		if(assessmentsSummaryList == null)
		{
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Failed to retrieve Assessments for the selected defendant");
			return aMapping.findForward(UIConstants.SUCCESS);
		}
		
//		check if the Supervisee has Historical Supervision Period
		HistoricalAssessmentResponseEvent histAssessEvt = (HistoricalAssessmentResponseEvent)MessageUtil.filterComposite(compResponse, HistoricalAssessmentResponseEvent.class);
		if(histAssessEvt != null)
		{
			assessmentForm.setHasHistoricalAssessments(true);
		}
		
		SupervisionPeriodResponseEvent supervisionPeriodRespEvt = (SupervisionPeriodResponseEvent)MessageUtil.filterComposite(compResponse, SupervisionPeriodResponseEvent.class);
		if(supervisionPeriodRespEvt == null)
		{
			assessmentForm.setSupervisionPeriod(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_INACTV);
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"No active supervision order exists for SPN.  Contact CLO of court to activate order");
			return aMapping.findForward(UIConstants.SUCCESS);
		}
		assessmentForm.setActiveSupPlanFound(true);
		
//		populate the AssessmentForm with SupervisionPeriodResponseEvent
		AdminAssessmentsHelper.setSupervisionPeriodResponseEvent(assessmentForm, supervisionPeriodRespEvt);
//		populate the AssessmentForm with AssessmentSummaryResponseEvent 
		AdminAssessmentsHelper.setAssessmentSummaryResponseEventCollection(assessmentForm, (ArrayList)assessmentsSummaryList);
		
//		display Supervision Plans for the supervisee
		GetSupervisionPlanSummaryEvent supPlanSummaryEvt = (GetSupervisionPlanSummaryEvent) AdminSupervisionPlanHelper.getSupervisionPlanSummaryEvent(assessmentForm);
		
		CompositeResponse compositeResponse =  this.postRequestEvent(supPlanSummaryEvt);
		Collection supPlanSummRespEvtList = MessageUtil.compositeToCollection(compositeResponse, SupervisionPlanSummaryResponseEvent.class);
		if(supPlanSummRespEvtList==null)
		{
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Failed to retrieve Supervision Plans for the selected defendant");
			return aMapping.findForward(UIConstants.SUCCESS);
		}
		
//		populate the AssessmentForm with SupervisionPlanSummaryResponseEvent
		AdminSupervisionPlanHelper.populateSupervisionPlanSummaryResponseEvent(assessmentForm, supPlanSummRespEvtList);
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}//end of method link()	
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 * @throws GeneralFeedbackMessageException
	 */
	public ActionForward viewHistory(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{	
		System.out.println("AdministerAssessments::DisplayAssessmentSummaryAction::viewHistory()::Method Begin");
		
		AssessmentForm assessmentForm = (AssessmentForm)aForm;
		
//		clear the assessment form	
		assessmentForm.clear();
		clearAssessmentForms(aMapping,aRequest);
		
		assessmentForm.setSupervisionPeriod(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_INACTV);		
		
		RequestEvent reqEvent = AdminAssessmentsHelper.getAssessmentsSummaryEvent(assessmentForm);		
		CompositeResponse compResponse = this.postRequestEvent(reqEvent);		
//		check for any error messages
		Map dataMap = MessageUtil.groupByTopic(compResponse);
		MessageUtil.processReturnException(dataMap);
		
//		get the collection of assessmentsSummary response objects
		Collection assessmentsSummaryList = MessageUtil.compositeToCollection(compResponse, AssessmentSummaryResponseEvent.class);	
		if(assessmentsSummaryList == null)
		{
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Failed to retrieve Historical Assessments of the selected defendant");
			return aMapping.findForward(UIConstants.SUCCESS);
		}
		
//		populate the AssessmentForm with AssessmentSummaryResponseEvent and SupervisionPeriodResponseEvent data
		AdminAssessmentsHelper.setAssessmentSummaryResponseEventCollection(assessmentForm, (ArrayList)assessmentsSummaryList);
		
//		display Supervision Plans for the supervisee
		GetSupervisionPlanSummaryEvent supPlanSummaryEvt = (GetSupervisionPlanSummaryEvent) AdminSupervisionPlanHelper.getSupervisionPlanSummaryEvent(assessmentForm);
		
		CompositeResponse compositeResponse =  this.postRequestEvent(supPlanSummaryEvt);
		Collection supPlanSummRespEvtList = MessageUtil.compositeToCollection(compositeResponse, SupervisionPlanSummaryResponseEvent.class);
		if(supPlanSummRespEvtList==null)
		{
			sendToErrorPage(aRequest,JIMSBaseAction.GENERIC_ERROR_MSG_KEY,"Failed to retrieve Historical Supervision Plans for the selected defendant");
			return aMapping.findForward(UIConstants.SUCCESS);
		}
		
//		populate the AssessmentForm with SupervisionPlanSummaryResponseEvent
		AdminSupervisionPlanHelper.populateSupervisionPlanSummaryResponseEvent(assessmentForm, supPlanSummRespEvtList);
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}//end of viewHistory()
	
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward filter(ActionMapping aMapping,ActionForm aForm,HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		System.out.println("AdministerAssessments::DisplayAssessmentSummaryAction::filter()::Method Begin");
		
		AssessmentForm assessmentForm = (AssessmentForm)aForm;
		
		assessmentForm.setInitialAssessmentExist(CSCAssessmentConstants.ASSESSMENT_NOT_EXIST);
		assessmentForm.setScsAssessmentExist(CSCAssessmentConstants.ASSESSMENT_NOT_EXIST);
		
		String historicalBeginDateStr = assessmentForm.getHistoricalBeginDateStr();
		String historicalEndDateStr = assessmentForm.getHistoricalEndDateStr();
		
		Date historicalBeginDate = null;
		Date historicalEndDate = null;
		
		try
		{
			historicalBeginDate = DateUtil.stringToDate(historicalBeginDateStr, DateUtil.DATE_FMT_1);
		}
		catch(Exception ex)
		{
			historicalBeginDate = null;
		}
		try
		{
			historicalEndDate = DateUtil.stringToDate(historicalEndDateStr, DateUtil.DATE_FMT_1);
		}
		catch(Exception ex)
		{
			historicalEndDate = null;
		}
		
//		Filter Initial Assessments List
		ArrayList allHistoricalInitialAssessList = (ArrayList)assessmentForm.getAllHistoricalInitialAssessmentsList();
		ArrayList filteredList = getAssessmentsListInDateRange(allHistoricalInitialAssessList, historicalBeginDate, historicalEndDate);
		if(filteredList.size() > 0)
		{
			assessmentForm.setInitialAssessmentExist(CSCAssessmentConstants.ASSESSMENT_EXIST);
		}
		assessmentForm.setInitialAssessmentsList(filteredList);
		
//		Filter SCS Assessments List
		ArrayList allHistoricalSCSAssessList = (ArrayList)assessmentForm.getAllHistoricalScsAssessmentsList();
		filteredList = getAssessmentsListInDateRange(allHistoricalSCSAssessList, historicalBeginDate, historicalEndDate);
		if(filteredList.size() > 0)
		{
			assessmentForm.setScsAssessmentExist(CSCAssessmentConstants.ASSESSMENT_EXIST);
		}
		assessmentForm.setScsAssessmentsList(filteredList);
		
//		Filter Reassessments List
		ArrayList allHistoricalReassessList = (ArrayList)assessmentForm.getAllHistoricalReassessmentsList();
		filteredList = getAssessmentsListInDateRange(allHistoricalReassessList, historicalBeginDate, historicalEndDate);
		assessmentForm.setReassessmentsList(filteredList);
		
//		Filter SupervisionPlans List
		ArrayList allHistoricalSupPlansList = (ArrayList)assessmentForm.getAllHistoricalSupervisionPlansList();
		filteredList = getSupPlansListInDateRange(allHistoricalSupPlansList, historicalBeginDate, historicalEndDate);
		assessmentForm.setSupervisionPlansList(filteredList);
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}//end of filter()
	
	

	/**
	 * 
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return
	 */
	public ActionForward refresh(ActionMapping aMapping,ActionForm aForm,HttpServletRequest aRequest,HttpServletResponse aResponse)
	{
		System.out.println("AdministerAssessments::DisplayAssessmentSummaryAction::refresh()::Method Begin");
		
		AssessmentForm assessmentForm = (AssessmentForm)aForm;
		assessmentForm.setHistoricalBeginDateStr("");
		assessmentForm.setHistoricalEndDateStr("");
		
		assessmentForm.setInitialAssessmentExist(CSCAssessmentConstants.ASSESSMENT_NOT_EXIST);
		assessmentForm.setScsAssessmentExist(CSCAssessmentConstants.ASSESSMENT_NOT_EXIST);
		
//		Initial Assessments List
		ArrayList allHistoricalInitialAssessList = (ArrayList)assessmentForm.getAllHistoricalInitialAssessmentsList();
		if(allHistoricalInitialAssessList.size() > 0)
		{
			assessmentForm.setInitialAssessmentExist(CSCAssessmentConstants.ASSESSMENT_EXIST);
		}
		assessmentForm.setInitialAssessmentsList(allHistoricalInitialAssessList);
		
//		SCS Assessments List
		ArrayList allHistoricalSCSAssessList = (ArrayList)assessmentForm.getAllHistoricalScsAssessmentsList();
		if(allHistoricalSCSAssessList.size() > 0)
		{
			assessmentForm.setScsAssessmentExist(CSCAssessmentConstants.ASSESSMENT_EXIST);
		}
		assessmentForm.setScsAssessmentsList(allHistoricalSCSAssessList);
		
//		Reassessments List
		ArrayList allHistoricalReassessList = (ArrayList)assessmentForm.getAllHistoricalReassessmentsList();
		assessmentForm.setReassessmentsList(allHistoricalReassessList);
		
//		Supervision Plans List
		ArrayList allHistoricalSupPlansList = (ArrayList)assessmentForm.getAllHistoricalSupervisionPlansList();
		assessmentForm.setSupervisionPlansList(allHistoricalSupPlansList);
		
		return aMapping.findForward(UIConstants.SUCCESS);
	}//end of refresh()
	
	
	
	/**
	 * 
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
			HttpServletResponse aResponse)
	{
		System.out.println("AdministerAssessments::DisplayAssessmentSummaryAction::cancel()::Method Begin");
		
		String forward = UIConstants.CANCEL;
		
		AssessmentForm assessmentForm = (AssessmentForm) aForm;
		
		if(assessmentForm.getSupervisionPeriod().equalsIgnoreCase(CSCAssessmentConstants.ASSESSMENT_SUPERVISION_PRD_ACTV))
		{		
			if (assessmentForm.getParentPage() != null
					&& CSCAssessmentConstants.CASENOTE_JOURNAL.equals(assessmentForm.getParentPage())) {
				forward = "failureCasenotesJournal";
			}
			else if (assessmentForm.getParentPage() != null
					&& CSCAssessmentConstants.CASENOTE_SUPERVISEE_SEARCH.equalsIgnoreCase(assessmentForm.getParentPage())) {
				forward = "failureCasenotesSearch";
			}
		}
		
		return aMapping.findForward(forward);
	}//end of cancel()
	
	
	
	/**
	 * 
	 * @param assessmentsList
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	private ArrayList getAssessmentsListInDateRange(ArrayList assessmentsList, Date beginDate, Date endDate)
	{
		ArrayList tempAssessList = new ArrayList();
		
		if((assessmentsList!= null) && (assessmentsList.size() > 0))
		{
//			If Start and End Filter Date is not entered
			if((beginDate==null) && (null == endDate))
			{
				return assessmentsList;
			}
			
//			Only End Filter date is entered	
			if((beginDate==null) && (null != endDate))
			{
				Iterator it = assessmentsList.iterator();
				
				while(it.hasNext())
				{
					AssessmentLightBean assessLightBean = (AssessmentLightBean)it.next();
					Date assessDate = assessLightBean.getAssessmentDate();
					
					int result = 0;
					
					result = DateUtil.compare(assessDate, endDate, DateUtil.DATE_FMT_1);
					if(result <=0)
					{
						tempAssessList.add(assessLightBean);
					}
				}
				return tempAssessList;
			}
			
//			Only Start Filter date is entered	
			if((beginDate!=null) && (null == endDate))
			{
				Iterator it = assessmentsList.iterator();
				
				while(it.hasNext())
				{
					AssessmentLightBean assessLightBean = (AssessmentLightBean)it.next();
					Date assessDate = assessLightBean.getAssessmentDate();
					
					int result = 0;
					
					result = DateUtil.compare(assessDate, beginDate, DateUtil.DATE_FMT_1);
					if(result >= 0)
					{
						tempAssessList.add(assessLightBean);
					}
				}
				return tempAssessList;
			}	
			
//			If both Start and End Filter Date are entered
			Iterator it = assessmentsList.iterator();
			
			while(it.hasNext())
			{
				AssessmentLightBean assessLightBean = (AssessmentLightBean)it.next();
				Date assessDate = assessLightBean.getAssessmentDate();
				
				int result = 0;
				result = DateUtil.compare(assessDate, beginDate, DateUtil.DATE_FMT_1);
				if(result >= 0)
				{
					result = DateUtil.compare(assessDate, endDate, DateUtil.DATE_FMT_1);
					if(result <=0)
					{
						tempAssessList.add(assessLightBean);
					}
				}
			}
		}
		return tempAssessList;
	}//end of getAssessmentsListInDateRange()
	
	
	
	/**
	 * 
	 * @param supervisionPlansList
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	private ArrayList getSupPlansListInDateRange(ArrayList supervisionPlansList, Date beginDate, Date endDate)
	{
		ArrayList tempAssessList = new ArrayList();
		
		if((supervisionPlansList!= null) && (supervisionPlansList.size() > 0))
		{
//			If Start and End Filter Date is not entered
			if((beginDate==null) && (null == endDate))
			{
				return supervisionPlansList;
			}
			
//			Only End Filter date is entered	
			if((beginDate==null) && (null != endDate))
			{
				Iterator it = supervisionPlansList.iterator();
				
				while(it.hasNext())
				{
					SupervisionPlanBean supervisionPlanBean = (SupervisionPlanBean)it.next();
				 //Date supPlanDate = supervisionPlanBean.getSupervisionPlanDate();
				   Date supPlanDate = supervisionPlanBean.getLastChangeDate();
					int result = 0;
					
					result = DateUtil.compare(supPlanDate, endDate, DateUtil.DATE_FMT_1);
					if(result <=0)
					{
						tempAssessList.add(supervisionPlanBean);
					}
				}
				return tempAssessList;
			}
			
//			Only Start Filter date is entered	
			if((beginDate!=null) && (null == endDate))
			{
				Iterator it = supervisionPlansList.iterator();
				
				while(it.hasNext())
				{
					SupervisionPlanBean supervisionPlanBean = (SupervisionPlanBean)it.next();
					//Date supPlanDate = supervisionPlanBean.getSupervisionPlanDate();
					  Date supPlanDate = supervisionPlanBean.getLastChangeDate();
					
					int result = 0;
					
					result = DateUtil.compare(supPlanDate, beginDate, DateUtil.DATE_FMT_1);
					if(result >= 0)
					{
						tempAssessList.add(supervisionPlanBean);
					}
				}
				return tempAssessList;
			}	
			
//			If both Start and End Filter Date are entered
			Iterator it = supervisionPlansList.iterator();
			
			while(it.hasNext())
			{
				SupervisionPlanBean supervisionPlanBean = (SupervisionPlanBean)it.next();
				//Date supPlanDate = supervisionPlanBean.getSupervisionPlanDate();
				  Date supPlanDate = supervisionPlanBean.getLastChangeDate();
				
				int result = 0;
				result = DateUtil.compare(supPlanDate, beginDate, DateUtil.DATE_FMT_1);
				if(result >= 0)
				{
					result = DateUtil.compare(supPlanDate, endDate, DateUtil.DATE_FMT_1);
					if(result <=0)
					{
						tempAssessList.add(supervisionPlanBean);
					}
				}
			}
		}
		return tempAssessList;
	}//end of getSupPlansListInDateRange()
	
	
	
	/**
	 * 
	 * @param aMapping
	 * @param aRequest
	 * @throws GeneralFeedbackMessageException
	 */
	private void clearAssessmentForms(ActionMapping aMapping,HttpServletRequest aRequest) throws GeneralFeedbackMessageException
	{
		WisconsinAssessmentForm wisconsinForm = (WisconsinAssessmentForm)this.getSessionForm(aMapping,aRequest,UIConstants.CSC_ASSESS_WISC_ASSESSMENT_FORM,true);
		wisconsinForm.clearAll();
		
		LSIRAssessmentForm lsirForm = (LSIRAssessmentForm)this.getSessionForm(aMapping,aRequest,UIConstants.CSC_ASSESS_LSIR_ASSESSMENT_FORM,true);
		lsirForm.clearAll();
		
		SCSAssessmentForm scsForm = (SCSAssessmentForm)this.getSessionForm(aMapping,aRequest,UIConstants.CSC_ASSESS_SCS_ASSESSMENT_FORM,true);
		scsForm.clearAll();
		
		SCSInterviewAssessmentForm scsInterviewForm = (SCSInterviewAssessmentForm)this.getSessionForm(aMapping,aRequest,UIConstants.CSC_ASSESS_SCS_INTERVIEW_ASSESSMENT_FORM,true);
		scsInterviewForm.clearAll();
		
		ForceFieldAssessmentForm forceFieldForm = (ForceFieldAssessmentForm)this.getSessionForm(aMapping,aRequest,UIConstants.CSC_ASSESS_FORCE_FIELD_ASSESSMENT_FORM,true);
		forceFieldForm.clearAll();
		
		SupervisionPlanForm supervisionPlanForm = (SupervisionPlanForm)this.getSessionForm(aMapping, aRequest, UIConstants.CSC_SUPERVISION_PLAN_FORM, true);
		supervisionPlanForm.clearAll();
	}//end of clearAssessmentForms()
	
}