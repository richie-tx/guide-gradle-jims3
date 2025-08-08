package ui.juvenilecase.prodsupport.action.update;

import java.util.Iterator;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.CalendarEventContextResponse;
import messaging.casefile.reply.ActivityResponseEvent;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.caseplan.reply.CaseplanDetailsResponseEvent;
import messaging.error.reply.ErrorResponseEvent;
import messaging.interviewinfo.reply.InterviewReportHeaderResponseEvent;
import messaging.interviewinfo.reply.InterviewResponseEvent;
import messaging.juvenile.reply.DrugTestingResponseEvent;
import messaging.juvenile.reply.SubstanceAbuseResponseEvent;
import messaging.juvenilecase.reply.AssignmentResponseEvent;
import messaging.juvenilecase.reply.JPOAssignmentHistoryResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.juvenilecase.reply.RiskAssessmentListResponseEvent;
import messaging.juvenilecase.reply.RiskResponseEvent;
import messaging.productionsupport.DeleteProductionSupportCasefileEvent;
import messaging.productionsupport.DeleteProductionSupportDrugTestingInfoEvent;
import messaging.productionsupport.DeleteProductionSupportEventTaskEvent;
import messaging.productionsupport.DeleteProductionSupportRiskResponseEvent;
import messaging.productionsupport.DeleteProductionSupportSubstanceAbuseEvent;
import messaging.productionsupport.UpdateProductionSupportNTTaskEvent;
import messaging.productionsupport.reply.ProductionSupportBenefitsAssessmentsResponseEvent;
import messaging.productionsupport.reply.ProductionSupportEventTaskResponseEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileProgramReferralResponseEvent;
import messaging.productionsupport.reply.ProductionSupportNTTasksResponseEvent;
import messaging.productionsupport.reply.ProductionSupportSupervisionRulesResponseEvent;
import messaging.programreferral.reply.ProgramReferralAssignmentHistoryResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.productionsupport.transactions.DeleteProductionSupportSubstanceAbuseCommand;

import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter This action performs the deletes necessary to delete a
 *         Juvenile Casefile (and all required associated children) and verifies
 *         that all records were deleted before returning the user to a summary
 *         screen.
 */
public class PerformCaseDeleteAction extends Action
{

    private Logger log = Logger.getLogger("PerformCaseDeleteAction");

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {

	ProdSupportForm regform = (ProdSupportForm) form;
	ActionErrors errors = new ActionErrors();

	String casefileId = regform.getCasefileId();

	if (casefileId == null || casefileId.equals(""))
	{
	    regform.setMsg("PerformCaseDeleteAction.java (63) - CasefileID was null.");
	    return (mapping.findForward("error"));
	}

	log.info("BEGIN DELETE CASEFILE - " + " LogonId: " + SecurityUIHelper.getLogonId());

	/**
	 * First, log all the IDs of the dependent files that will be deleted
	 * automatically via RI
	 **/
	writeLogEntries(regform, SecurityUIHelper.getLogonId(), casefileId);

	/**
	 * If record has entries in JCRISKRESPONSES they have to be deleted or
	 * the RISKANALYSIS delete will bomb on constraint FKRRR1
	 **/
	if (regform.getRiskresponsesCount() > 0)
	{
	    Iterator riskiter = regform.getRiskresponses().iterator();
	    // save analysis ids to avoid duplicate delete calls
	    TreeMap<String, String> riskAnalysisMap = new TreeMap<String, String>();
	    while (riskiter.hasNext())
	    {
		RiskResponseEvent nextRiskResponse = (RiskResponseEvent) riskiter.next();
		String riskAnalysisId = nextRiskResponse.getRiskAnalysisID();
		if (riskAnalysisId != null && !riskAnalysisId.equals(""))
		{
		    if (riskAnalysisMap.get(riskAnalysisId) == null)
		    {
			DeleteProductionSupportRiskResponseEvent deleteRiskAnalysisResponseEvent = (DeleteProductionSupportRiskResponseEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTRISKRESPONSE);
			deleteRiskAnalysisResponseEvent.setRiskAnalysisId(riskAnalysisId);
			IDispatch dispatchEvent = EventManager.getSharedInstance(EventManager.REQUEST);
			dispatchEvent.postEvent(deleteRiskAnalysisResponseEvent);
			log.info("DELETE JCRISKRESPONSES ID WITH RISKANALYSISID: " + riskAnalysisId + " LogonId: " + SecurityUIHelper.getLogonId());
			riskAnalysisMap.put(riskAnalysisId, riskAnalysisId);
		    }

		}
	    }
	}
	
	/*Delete Drug testing Info.*/
	if (regform.getDrugTestingCount() > 0)
	{
	    Iterator iter = regform.getDrugTestingInfos().iterator();
	    while (iter.hasNext())
	    {
		DrugTestingResponseEvent drugTestingResponse = (DrugTestingResponseEvent) iter.next();
		DeleteProductionSupportDrugTestingInfoEvent deleteEvent = (DeleteProductionSupportDrugTestingInfoEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTDRUGTESTINGINFO);
		deleteEvent.setDrugTestingId(drugTestingResponse.getDrugTestingId());
		CompositeResponse composite = MessageUtil.postRequest(deleteEvent);
		ErrorResponseEvent errorEvt = (ErrorResponseEvent) MessageUtil.filterComposite(composite, ErrorResponseEvent.class);
		if (errorEvt != null)
		{
		    if (errorEvt.getMessage() != null)
		    {
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("Error on deleting the drug testing information."));
		    }
		}
	    }
	    
	    log.info(" DELETED Drug testing Info for CASEFILE ID: " + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	}
	
	if (errors != null && errors.size() > 0)
	{
	    regform.setMsg("Error - The Drug testing info linked to casefile could not be deleted: " + casefileId + ". Please make a screenprint of this page and contact ITC to " + "either completely delete this file or restore it back.");
	    return mapping.findForward("error");
	}
	
	/*Delete Substance Abuse.*/
	if (regform.getSubAbuseCount() > 0)
	{
	    Iterator iter = regform.getSubAbuseInfos().iterator();
	    while (iter.hasNext())
	    {
		SubstanceAbuseResponseEvent substanceAbuseResponse = (SubstanceAbuseResponseEvent) iter.next();
		DeleteProductionSupportSubstanceAbuseEvent deleteEvent = (DeleteProductionSupportSubstanceAbuseEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTSUBSTANCEABUSE);
		deleteEvent.setSubstanceAbuseId(substanceAbuseResponse.getSubstanceAbuseId());
		CompositeResponse composite = MessageUtil.postRequest(deleteEvent);
		ErrorResponseEvent errorEvt = (ErrorResponseEvent) MessageUtil.filterComposite(composite, ErrorResponseEvent.class);
		if (errorEvt != null)
		{
		    if (errorEvt.getMessage() != null)
		    {
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("Error on deleting the substance abuse information."));
		    }
		}
	    }
	    
	    log.info(" DELETED Substance Abuse for CASEFILE ID: " + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	}
	
	if (errors != null && errors.size() > 0)
	{
	    regform.setMsg("Error - The Substance Abuse linked to casefile could not be deleted: " + casefileId + ". Please make a screenprint of this page and contact ITC to " + "either completely delete this file or restore it back.");
	    return mapping.findForward("error");
	}
	
	/** delete casefile record (and children by default) **/
	DeleteProductionSupportCasefileEvent deleteCasefileEvent = (DeleteProductionSupportCasefileEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTCASEFILE);

	deleteCasefileEvent.setCasefileId(casefileId);
	IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
	log.info("DELETE ORIGINAL FROM CASEFILE RECORD FOR DELETE CASEFILE: " + SecurityUIHelper.getLogonId());
	dispatch.postEvent(deleteCasefileEvent);
	CompositeResponse nonComplianceNoticeResponse = (CompositeResponse) dispatch.getReply();
	ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(nonComplianceNoticeResponse, ErrorResponseEvent.class);
	if (error != null)
	{
	    errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
	}
	if (errors != null && errors.size() > 0)
	{
	    regform.setMsg("Error - The casefile could not be deleted: " + casefileId + ". Please make a screenprint of this page and contact ITC to " + "either completely delete this file or restore it back. Most likely cause is that a Caseplan exists with a Caseplan Revision.");
	    return mapping.findForward("error");
	}
	log.info(" DELETED FROM CASEFILE ID: " + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	
	/** If any EVENTTASKs showed up, delete those. **/
	if (regform.getEventtaskCount() > 0)
	{
	    Iterator iter = regform.getEventtasks().iterator();
	    while (iter.hasNext())
	    {
		ProductionSupportEventTaskResponseEvent nextEventTask = (ProductionSupportEventTaskResponseEvent) iter.next();
		if (nextEventTask.getEventTaskId() != null && nextEventTask.getEventTaskId().equals("") == false)
		{
		    String eventTaskId = nextEventTask.getEventTaskId();
		    log.info("START CASEFILE DELETE - DELETE EVENT TASK: Event Task Id: " + eventTaskId + " LogonId: " + SecurityUIHelper.getLogonId());
		    DeleteProductionSupportEventTaskEvent deleteEventTaskEvent = (DeleteProductionSupportEventTaskEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTEVENTTASK);
		    deleteEventTaskEvent.setEventTaskId(eventTaskId);
		    deleteEventTaskEvent.setCasefileId(casefileId);
		    IDispatch dispatchEvent = EventManager.getSharedInstance(EventManager.REQUEST);
		    dispatchEvent.postEvent(deleteEventTaskEvent);
		    log.info("END CASEFILE DELETE - DELETE EVENT TASK: Event Task Id: " + eventTaskId + " LogonId: " + SecurityUIHelper.getLogonId());
		}
	    }
	}
	/**
	 * If an NTTASK entry is present and NOT ALREADY status C, set status to
	 * C.
	 **/
	if (regform.getNttaskCount() > 0)
	{
	    Iterator iter = regform.getNttasks().iterator();
	    while (iter.hasNext())
	    {
		ProductionSupportNTTasksResponseEvent nextNTTask = (ProductionSupportNTTasksResponseEvent) iter.next();
		if (nextNTTask.getStatusId().equals("C"))
		{
		    break;
		}
		else
		{
		    String ntTaskId = nextNTTask.getTaskId();
		    log.info("START CASEFILE DELETE - UPDATE NT TASK: NT Task Id: " + ntTaskId + " LogonId: " + SecurityUIHelper.getLogonId());
		    UpdateProductionSupportNTTaskEvent updateNTTaskEvent = (UpdateProductionSupportNTTaskEvent) EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTNTTASK);
		    updateNTTaskEvent.setCasefileId(casefileId);
		    updateNTTaskEvent.setStatusCd("C");
		    IDispatch dispatchNTEvent = EventManager.getSharedInstance(EventManager.REQUEST);
		    dispatchNTEvent.postEvent(updateNTTaskEvent);
		    log.info("END CASEFILE DELETE - UPDATE NT TASK: NT Task Id: " + ntTaskId + " LogonId: " + SecurityUIHelper.getLogonId());
		}
	    }
	}
	
	
	
	

	/** Log for auditing purposes **/
	log.info("SCENARIO CASEFILE DELETE - Performed a CASEFILE DELETE for casefileID=" + regform.getCasefileId() + "LogonId: " + SecurityUIHelper.getLogonId());

	regform.setMsg("");
	return mapping.findForward("success");

    }

    /**
     * log all the records that are going to be deleted with casefile
     * 
     * @param regform
     * @param logonid
     */
    private void writeLogEntries(ProdSupportForm regform, String logonid, String casefileId)
    {

	if (regform.getCasefiles() != null)
	{
	    Iterator iter = regform.getCasefiles().iterator();
	    while (iter.hasNext())
	    {
		iter.next();
		log.info("**** START LOGGING THE DELETE PROCESS FOR CASEFILE ****");
		log.info("DELETE - JCCASEFILE ID: " + regform.getFromCasefile() + " TO casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	    }
	}

	if (regform.getActivities() != null)
	{
	    Iterator iter = regform.getActivities().iterator();
	    while (iter.hasNext())
	    {
		ActivityResponseEvent activityResponse = (ActivityResponseEvent) iter.next();
		log.info(" DELETE - JCACTIVITY ID: " + activityResponse.getActivityId() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());

	    }
	}

	if (regform.getAssignments() != null)
	{
	    Iterator iter = regform.getAssignments().iterator();
	    while (iter.hasNext())
	    {
		AssignmentResponseEvent assignmentResponse = (AssignmentResponseEvent) iter.next();
		log.info(" DELETE - JCASSIGNMENT_ID: " + assignmentResponse.getAssignmentId() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	    }
	}

	if (regform.getAssnmnthists() != null)
	{
	    Iterator iter = regform.getAssnmnthists().iterator();
	    while (iter.hasNext())
	    {
		JPOAssignmentHistoryResponseEvent jpsAssignmentHistoryResponse = (JPOAssignmentHistoryResponseEvent) iter.next();
		log.info(" DELETE - JPOASSNMNTHIST_ID: " + jpsAssignmentHistoryResponse.getJpoAssignmentHistoryId() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	    }
	}

	if (regform.getCaseplans() != null)
	{
	    Iterator iter = regform.getCaseplans().iterator();
	    while (iter.hasNext())
	    {
		CaseplanDetailsResponseEvent caseplanDetailsResponse = (CaseplanDetailsResponseEvent) iter.next();
		log.info(" DELETE - JCCASEPLAN_ID: " + caseplanDetailsResponse.getCaseplanID() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	    }
	}

	if (regform.getCasefileclosings() != null)
	{
	    Iterator iter = regform.getCasefileclosings().iterator();
	    while (iter.hasNext())
	    {
		CasefileClosingResponseEvent casefileClosingResponse = (CasefileClosingResponseEvent) iter.next();
		log.info(" DELETE - JCCASFILECLOSNG_ID: " + casefileClosingResponse.getCasefileClosingInfoId() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	    }
	}

	if (regform.getInterviews() != null)
	{
	    Iterator iter = regform.getInterviews().iterator();
	    while (iter.hasNext())
	    {
		InterviewResponseEvent interviewResponse = (InterviewResponseEvent) iter.next();
		log.info(" DELETE - JCINTERVIEW ID: " + interviewResponse.getInterviewId() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	    }
	}

	if (regform.getCaleventconts() != null)
	{
	    Iterator iter = regform.getCaleventconts().iterator();
	    while (iter.hasNext())
	    {
		CalendarEventContextResponse calendarEventContextResponse = (CalendarEventContextResponse) iter.next();
		log.info(" DELETE - JCCALEVENTCONT ID: " + calendarEventContextResponse.getCalendarEventContextId() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	    }
	}

	if (regform.getProgrfasgnhists() != null)
	{
	    Iterator iter = regform.getProgrfasgnhists().iterator();
	    while (iter.hasNext())
	    {
		ProgramReferralAssignmentHistoryResponseEvent programReferralAssignmentHistoryResponse = (ProgramReferralAssignmentHistoryResponseEvent) iter.next();
		log.info(" DELETE - CSPROGRFASGNHIST ID: " + programReferralAssignmentHistoryResponse.getProgramReferralAssignmentHistoryId() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	    }
	}

	if (regform.getIviewdocs() != null)
	{
	    Iterator iter = regform.getIviewdocs().iterator();
	    while (iter.hasNext())
	    {
		InterviewReportHeaderResponseEvent interviewReportHeaderResponse = (InterviewReportHeaderResponseEvent) iter.next();
		log.info(" DELETE - JCIVIEWDOC ID: " + interviewReportHeaderResponse.getReportId() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	    }
	}

	if (regform.getRiskanalyses() != null)
	{
	    Iterator iter = regform.getRiskanalyses().iterator();
	    while (iter.hasNext())
	    {
		RiskAssessmentListResponseEvent riskAnalysis = (RiskAssessmentListResponseEvent) iter.next();
		log.info(" DELETE - JCRISKANALYSIS ID: " + riskAnalysis.getAssessmentID() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	    }
	}

	if (regform.getRiskresponses() != null)
	{
	    Iterator iter = regform.getRiskresponses().iterator();
	    while (iter.hasNext())
	    {
		RiskResponseEvent riskResponse = (RiskResponseEvent) iter.next();
		log.info(" DELETE - JCRISKRESPONSES ID: " + riskResponse.getResponseID() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	    }
	}

	if (regform.getJuvprogrefs() != null)
	{
	    Iterator iter = regform.getJuvprogrefs().iterator();
	    while (iter.hasNext())
	    {
		ProductionSupportJuvenileProgramReferralResponseEvent juvenileProgramReferralResponse = (ProductionSupportJuvenileProgramReferralResponseEvent) iter.next();
		log.info(" DELETE - CSJUVPROGREF ID: " + juvenileProgramReferralResponse.getJuvenileProgramReferralId() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	    }
	}

	if (regform.getBeneasmnts() != null)
	{
	    Iterator iter = regform.getBeneasmnts().iterator();
	    while (iter.hasNext())
	    {
		ProductionSupportBenefitsAssessmentsResponseEvent benefitsAssessmentsResponse = (ProductionSupportBenefitsAssessmentsResponseEvent) iter.next();
		log.info(" DELETE - JCBENEASMNT ID: " + benefitsAssessmentsResponse.getAssessmentId() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	    }
	}

	if (regform.getSuprules() != null)
	{
	    Iterator iter = regform.getSuprules().iterator();
	    while (iter.hasNext())
	    {
		ProductionSupportSupervisionRulesResponseEvent supervisionRulesResponse = (ProductionSupportSupervisionRulesResponseEvent) iter.next();
		log.info(" DELETE - JCSUPRULE ID: " + supervisionRulesResponse.getSupervisionRuleId() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	    }
	}

	if (regform.getTraits() != null)
	{
	    Iterator iter = regform.getTraits().iterator();
	    while (iter.hasNext())
	    {
		JuvenileTraitResponseEvent juvenileTraitResponse = (JuvenileTraitResponseEvent) iter.next();
		log.info(" DELETE - JCTRAITS ID: " + juvenileTraitResponse.getJuvenileTraitId() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	    }
	}

	if (regform.getEventtasks() != null)
	{
	    Iterator iter = regform.getEventtasks().iterator();
	    while (iter.hasNext())
	    {
		ProductionSupportEventTaskResponseEvent eventTaskResponse = (ProductionSupportEventTaskResponseEvent) iter.next();
		log.info(" DELETE - EVENTTASK ID: " + eventTaskResponse.getEventTaskId() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	    }
	}

	if (regform.getTraits() != null)
	{
	    Iterator iter = regform.getTraits().iterator();
	    while (iter.hasNext())
	    {
		JuvenileTraitResponseEvent juvenileTraitResponse = (JuvenileTraitResponseEvent) iter.next();
		log.info(" DELETE - JCTRAITS ID: " + juvenileTraitResponse.getJuvenileTraitId() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	    }
	}

	if (regform.getDrugTestingInfos() != null)
	{
	    Iterator iter = regform.getDrugTestingInfos().iterator();
	    while (iter.hasNext())
	    {
		DrugTestingResponseEvent drugTestingResponse = (DrugTestingResponseEvent) iter.next();
		log.info(" DELETE - JCDRUGTESTING ID: " + drugTestingResponse.getDrugTestingId() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	    }
	}

	if (regform.getSubAbuseInfos() != null)
	{
	    Iterator iter = regform.getSubAbuseInfos().iterator();
	    while (iter.hasNext())
	    {
		SubstanceAbuseResponseEvent substanceAbuseResponse = (SubstanceAbuseResponseEvent) iter.next();
		log.info(" DELETE - JCSUBABUSE ID: " + substanceAbuseResponse.getSubstanceAbuseId() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
	    }
	}

	if (regform.getNttasks() != null)
	{
	    Iterator iter = regform.getNttasks().iterator();
	    while (iter.hasNext())
	    {
		ProductionSupportNTTasksResponseEvent ntTaskResponse = (ProductionSupportNTTasksResponseEvent) iter.next();
		log.info(" DELETE - NTTASKS ID: " + ntTaskResponse.getTaskId() + " with casefileID=" + casefileId + "Logon Id: " + SecurityUIHelper.getLogonId());
		log.info("**** END LOGGING THE DELETE PROCESSESS FOR CASEFILE ****");
	    }
	}

    }
}
