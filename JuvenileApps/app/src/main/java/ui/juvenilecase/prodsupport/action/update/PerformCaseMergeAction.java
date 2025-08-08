package ui.juvenilecase.prodsupport.action.update;

import java.util.ArrayList;
import java.util.Iterator;
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
import messaging.juvenilecase.GetAssignmentsByCasefileIdEvent;
import messaging.juvenilecase.reply.AssignmentResponseEvent;
import messaging.juvenilecase.reply.JPOAssignmentHistoryResponseEvent;
import messaging.juvenilecase.reply.JuvenileDetentionFacilitiesResponseEvent;
import messaging.juvenilecase.reply.JuvenileFacilityHeaderResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.juvenilecase.reply.RiskAssessmentListResponseEvent;
import messaging.juvenilecase.reply.RiskResponseEvent;
import messaging.productionsupport.DeleteProductionSupportCasefileEvent;
import messaging.productionsupport.DeleteProductionSupportEventTaskEvent;
import messaging.productionsupport.UpdateProductionSupportActivityEvent;
import messaging.productionsupport.UpdateProductionSupportAssignmentEvent;
import messaging.productionsupport.UpdateProductionSupportBenefitAssessmentEvent;
import messaging.productionsupport.UpdateProductionSupportCalendarEventContextEvent;
import messaging.productionsupport.UpdateProductionSupportCasefileClosingEvent;
import messaging.productionsupport.UpdateProductionSupportCasefileEvent;
import messaging.productionsupport.UpdateProductionSupportCaseplanEvent;
import messaging.productionsupport.UpdateProductionSupportDrugTestingInfoEvent;
import messaging.productionsupport.UpdateProductionSupportIViewDocEvent;
import messaging.productionsupport.UpdateProductionSupportInterviewEvent;
import messaging.productionsupport.UpdateProductionSupportJpoAssignmentHistoryEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenileCasefileSupervisionRulesEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenileProgramReferralAssignmentHistoryEvent;
import messaging.productionsupport.UpdateProductionSupportJuvenileProgramReferralEvent;
import messaging.productionsupport.UpdateProductionSupportNTTaskEvent;
import messaging.productionsupport.UpdateProductionSupportRiskAnalysisEvent;
import messaging.productionsupport.UpdateProductionSupportSubstanceAbuseEvent;
import messaging.productionsupport.UpdateProductionSupportSupervisionNumEvent;
import messaging.productionsupport.UpdateProductionSupportTraitEvent;
import messaging.productionsupport.reply.ProductionSupportBenefitsAssessmentsResponseEvent;
import messaging.productionsupport.reply.ProductionSupportEventTaskResponseEvent;
import messaging.productionsupport.reply.ProductionSupportFacilityDetentionResponseEvent;
import messaging.productionsupport.reply.ProductionSupportFacilityHeaderResponseEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileProgramReferralResponseEvent;
import messaging.productionsupport.reply.ProductionSupportNTTasksResponseEvent;
import messaging.productionsupport.reply.ProductionSupportSupervisionRulesResponseEvent;
import messaging.programreferral.reply.ProgramReferralAssignmentHistoryResponseEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import pd.productionsupport.transactions.UpdateProductionSupportCaseplanCommand;

import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 */

public class PerformCaseMergeAction extends Action {

	private Logger log = Logger.getLogger("PerformCaseMergeAction");
	private String logonid = SecurityUIHelper.getLogonId();

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;
		ActionErrors errors = new ActionErrors();

		String fromCasefile = regform.getFromCasefile();
		String toCasefile = regform.getToCasefile();

		if ((fromCasefile == null || fromCasefile.equals(" "))
			||(toCasefile == null || toCasefile.equals(" "))) {
			regform.setMsg("CasefileId was null.");
			return mapping.findForward("error");
		}
		// log the beginning of casemerge
		log.info(" BEGIN Casefile MERGE " + fromCasefile + " into " + toCasefile + "Logon Id: " +  logonid);
				
		// next, log all the IDs of the dependent files that will be deleted automatically via RI **/ 
		writeLogEntries(regform);
		
		/** Perform the updates **/	
		performMergeUpdates(regform, fromCasefile, toCasefile);
		
		// delete the FROM casefile - This needs to be done first to make sure it can be done... All other transactions are update but this one can fail 
		DeleteProductionSupportCasefileEvent deleteCasefileEvent = 
			(DeleteProductionSupportCasefileEvent) 
		EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTCASEFILE) ;
	
		deleteCasefileEvent.setCasefileId(fromCasefile);
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		log.info("DELETE ORIGINAL FROM CASEFILE RECORD FOR MERGE CASEFILE: " + SecurityUIHelper.getLogonId());
		dispatch.postEvent(deleteCasefileEvent);	
		CompositeResponse nonComplianceNoticeResponse = (CompositeResponse) dispatch.getReply();
		ErrorResponseEvent error = (ErrorResponseEvent) MessageUtil.filterComposite(nonComplianceNoticeResponse, ErrorResponseEvent.class);
		if(error != null){
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error.getMessage()));
		}
		if(errors != null && errors.size() > 0){
			regform.setMsg("Error - The casefile could not be deleted: " + fromCasefile + ". Please make a screenprint of this page and contact ITC to " +
					"either completely delete this file or restore it back." );
			return mapping.findForward("error");
		}
		log.info(" DELETED FROM CASEFILE ID: " + fromCasefile + "Logon Id: " + 
				SecurityUIHelper.getLogonId());
		
		// if there were any errors with the update, then let the user know
		if(errors != null && errors.size() > 0){
			regform.setMsg("Error - Important: There was a problem with the Casefile Merge. Please take a screenshot and contact ITC to make sure that the data is still valid" + errors.toString());
			return mapping.findForward("error");
		}
		
		/** If any EVENTTASKs showed up, delete those. **/
		if (regform.getEventtaskCount() > 0){
				Iterator iter = regform.getEventtasks().iterator();
				while (iter.hasNext()){
					ProductionSupportEventTaskResponseEvent nextEventTask = (ProductionSupportEventTaskResponseEvent)iter.next();
					if (nextEventTask.getEventTaskId()!= null && nextEventTask.getEventTaskId().equals("") == false){
						String eventTaskId = nextEventTask.getEventTaskId();
						log.info("START CASEFILE MERGE DELETE EVENT TASK: Event Task Id: " + eventTaskId +
								" LogonId: " + SecurityUIHelper.getLogonId());
						DeleteProductionSupportEventTaskEvent deleteEventTaskEvent = (DeleteProductionSupportEventTaskEvent) 
						EventFactory.getInstance(ProductionSupportControllerServiceNames.DELETEPRODUCTIONSUPPORTEVENTTASK) ;					
						deleteEventTaskEvent.setEventTaskId(eventTaskId);
						deleteEventTaskEvent.setCasefileId(fromCasefile);
						IDispatch dispatchEvent = EventManager.getSharedInstance(EventManager.REQUEST);
						dispatchEvent.postEvent(deleteEventTaskEvent);	
						
						log.info("END CASEFILE MERGE DELETE EVENT TASK: Event Task Id: " + eventTaskId +
								" LogonId: " + SecurityUIHelper.getLogonId());
					}
				}
		}			
		/** If an NTTASK entry is present and NOT ALREADY status C, set status to C. **/
		if (regform.getNttaskCount() > 0){
			Iterator iter = regform.getNttasks().iterator();				
			while (iter.hasNext())
			{
				ProductionSupportNTTasksResponseEvent nextNTTask = (ProductionSupportNTTasksResponseEvent)iter.next();
				if (nextNTTask.getStatusId().equals("C")){
					break;
				}else{
					String ntTaskId = nextNTTask.getTaskId();
					log.info("START CASEFILE MERGE UPDATE NT TASK: NT Task Id: " + ntTaskId +
							" LogonId: " + SecurityUIHelper.getLogonId());	
					UpdateProductionSupportNTTaskEvent updateNTTaskEvent = (UpdateProductionSupportNTTaskEvent) 
					EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTNTTASK);					
					updateNTTaskEvent.setCasefileId(fromCasefile);
					updateNTTaskEvent.setMergeToCasefileId(toCasefile);
					updateNTTaskEvent.setStatusCd("C");
					IDispatch dispatchNTEvent = EventManager.getSharedInstance(EventManager.REQUEST);
					dispatchNTEvent.postEvent(updateNTTaskEvent);							
					log.info("END CASEFILE MERGE UPDATE NT TASK: NT Task Id: " + ntTaskId +
							" LogonId: " + SecurityUIHelper.getLogonId());
				}
			}			
		}
			
		/** Log for auditing purposes **/
		log.info("**** END - Performed a CASEFILE MERGE ****");
		log.info("FROM casefileID="	+ regform.getFromCasefile() + " TO casefuleId " + regform.getToCasefile() + 
				SecurityUIHelper.getLogonId());
		
		regform.setMsg("");
		return mapping.findForward("success");
		

		
	}
	
	/**
	 * log all the data that is going to me updated and merged.
	 * @param regform
	 */
	private void writeLogEntries(ProdSupportForm regform){
			
			
	
			if (regform.getCasefiles() != null){
				Iterator iter = regform.getCasefiles().iterator();
					while (iter.hasNext()){
						iter.next();
						log.info("**** START LOGGING THE MERGE PROCESSESS FOR CASEFILE ****");
						log.info("MERGE - JCCASEFILE ID: "
								+ regform.getFromCasefile() + " TO casefileID=" + regform.getToCasefile() + "Logon Id: " + 
								SecurityUIHelper.getLogonId());					
					}
			}
			
			if (regform.getActivities()!= null){
			Iterator iter = regform.getActivities().iterator();
				while (iter.hasNext()){
					ActivityResponseEvent activityResponse = (ActivityResponseEvent)iter.next();
					log.info(" MERGE - JCACTIVITY ID: "
							+ activityResponse.getActivityId() + " with casefileID=" + regform.getToCasefile() + "Logon Id: " + 
							SecurityUIHelper.getLogonId());
					
				}
			}
			
			if (regform.getAssignments()!= null){
				Iterator iter = regform.getAssignments().iterator();
				while (iter.hasNext()){
					AssignmentResponseEvent assignmentResponse = (AssignmentResponseEvent)iter.next();
					log.info(" MERGE - JCASSIGNMENT_ID: "
							+ assignmentResponse.getAssignmentId() + " with casefileID=" + regform.getToCasefile() + "Logon Id: " + 
							SecurityUIHelper.getLogonId());
				}
			}
			
			if (regform.getAssnmnthists()!= null){
				Iterator iter = regform.getAssnmnthists().iterator();
				while (iter.hasNext()){
					JPOAssignmentHistoryResponseEvent jpsAssignmentHistoryResponse = (JPOAssignmentHistoryResponseEvent)iter.next();
					log.info(" MERGE - JPOASSNMNTHIST_ID: "
							+ jpsAssignmentHistoryResponse.getJpoAssignmentHistoryId() + " with casefileID=" + regform.getToCasefile() + 
							"Logon Id: " + SecurityUIHelper.getLogonId());
				}		
			}
			
			if (regform.getCaseplans()!=null){
				Iterator iter = regform.getCaseplans().iterator();
				while (iter.hasNext()){
					CaseplanDetailsResponseEvent caseplanDetailsResponse = (CaseplanDetailsResponseEvent)iter.next();
					log.info(" MERGE - JCCASEPLAN_ID: "
							+ caseplanDetailsResponse.getCaseplanID() + " with casefileID=" + regform.getToCasefile() + 
							"Logon Id: " + SecurityUIHelper.getLogonId());
				}
			}
			
			if (regform.getCasefileclosings()!=null){
				Iterator iter = regform.getCasefileclosings().iterator();
				while (iter.hasNext()){
					CasefileClosingResponseEvent casefileClosingResponse = (CasefileClosingResponseEvent)iter.next();
					log.info(" MERGE - JCCASFILECLOSNG_ID: "
							+ casefileClosingResponse.getCasefileClosingInfoId() + " with casefileID=" + regform.getToCasefile() + "Logon Id: " + 
							SecurityUIHelper.getLogonId());
				}
			}
			
			if (regform.getInterviews()!=null){
				Iterator iter = regform.getInterviews().iterator();
				while (iter.hasNext()){
					InterviewResponseEvent interviewResponse = (InterviewResponseEvent)iter.next();
					log.info(" MERGE - JCINTERVIEW ID: " + interviewResponse.getInterviewId() +
							" with casefileID=" + regform.getToCasefile() + "Logon Id: " + 
							SecurityUIHelper.getLogonId());
				}
			}
			
			if (regform.getCaleventconts()!=null){
				Iterator iter = regform.getCaleventconts().iterator();
				while (iter.hasNext()){
					CalendarEventContextResponse calendarEventContextResponse = (CalendarEventContextResponse)iter.next();
					log.info(" MERGE - JCCALEVENTCONT ID: " + calendarEventContextResponse.getCalendarEventContextId()
							+ " with casefileID=" + regform.getToCasefile() + "Logon Id: " + 
							SecurityUIHelper.getLogonId());
				}
			}
			
			if (regform.getProgrfasgnhists()!=null){
				Iterator iter = regform.getProgrfasgnhists().iterator();
				while (iter.hasNext()){
					ProgramReferralAssignmentHistoryResponseEvent programReferralAssignmentHistoryResponse = (ProgramReferralAssignmentHistoryResponseEvent)iter.next();
					log.info(" MERGE - CSPROGRFASGNHIST ID: " + programReferralAssignmentHistoryResponse.getProgramReferralAssignmentHistoryId()
							+ " with casefileID=" + regform.getToCasefile() + "Logon Id: " + 
							SecurityUIHelper.getLogonId());
				}
			}
			
			if (regform.getIviewdocs()!=null){
				Iterator iter = regform.getIviewdocs().iterator();
				while (iter.hasNext()){
					InterviewReportHeaderResponseEvent interviewReportHeaderResponse = (InterviewReportHeaderResponseEvent)iter.next();
					log.info(" MERGE - JCIVIEWDOC ID: " + interviewReportHeaderResponse.getReportId()
							+ " with casefileID=" + regform.getToCasefile() + "Logon Id: " + 
							SecurityUIHelper.getLogonId());
				}
			}
			
			if (regform.getRiskanalyses()!=null){
				Iterator iter = regform.getRiskanalyses().iterator();
				while (iter.hasNext()){
					RiskAssessmentListResponseEvent riskAnalysis = (RiskAssessmentListResponseEvent)iter.next();
					log.info(" MERGE - JCRISKANALYSIS ID: " + riskAnalysis.getAssessmentID()
							+ " with casefileID=" + regform.getToCasefile() + "Logon Id: " + 
							SecurityUIHelper.getLogonId());
				}
			}
			
			if (regform.getRiskresponses()!=null){
				Iterator iter = regform.getRiskresponses().iterator();
				while (iter.hasNext()){
					RiskResponseEvent riskResponse = (RiskResponseEvent)iter.next();
					log.info(" MERGE - JCRISKRESPONSES ID: " + riskResponse.getResponseID()
							+ " with casefileID=" + regform.getToCasefile() + "Logon Id: " + 
							SecurityUIHelper.getLogonId());
				}
			}
			
			if (regform.getJuvprogrefs()!=null){
				Iterator iter = regform.getJuvprogrefs().iterator();
				while (iter.hasNext()){
					ProductionSupportJuvenileProgramReferralResponseEvent juvenileProgramReferralResponse = (ProductionSupportJuvenileProgramReferralResponseEvent)iter.next();
					log.info(" MERGE - CSJUVPROGREF ID: " + juvenileProgramReferralResponse.getJuvenileProgramReferralId()
							+ " with casefileID=" + regform.getToCasefile() + "Logon Id: " + 
							SecurityUIHelper.getLogonId());
				}
			}
			
			if (regform.getBeneasmnts()!=null){
				Iterator iter = regform.getBeneasmnts().iterator();
				while (iter.hasNext()){
					ProductionSupportBenefitsAssessmentsResponseEvent benefitsAssessmentsResponse = (ProductionSupportBenefitsAssessmentsResponseEvent)iter.next();
					log.info(" MERGE - JCBENEASMNT ID: " + benefitsAssessmentsResponse.getAssessmentId()
							+ " with casefileID=" + regform.getToCasefile() + "Logon Id: " + 
							SecurityUIHelper.getLogonId());
				}
			}
			
			if (regform.getSuprules()!=null){
				Iterator iter = regform.getSuprules().iterator();
				while (iter.hasNext()){
					ProductionSupportSupervisionRulesResponseEvent supervisionRulesResponse = (ProductionSupportSupervisionRulesResponseEvent)iter.next();
					log.info(" MERGE - JCSUPRULE ID: "+ supervisionRulesResponse.getSupervisionRuleId()
							+" with casefileID="+regform.getToCasefile()+ "Logon Id: " + 
							SecurityUIHelper.getLogonId());
				}
			}	
			
			if (regform.getTraits()!= null){
				Iterator iter = regform.getTraits().iterator();
				while (iter.hasNext()){
					JuvenileTraitResponseEvent juvenileTraitResponse = (JuvenileTraitResponseEvent)iter.next();
					log.info(" MERGE - JCTRAITS ID: "+ juvenileTraitResponse.getJuvenileTraitId()
							+" with casefileID=" + regform.getToCasefile()+ "Logon Id: " + 
							SecurityUIHelper.getLogonId());
				}
			}	
			
			if (regform.getEventtasks()!= null){
				Iterator iter = regform.getEventtasks().iterator();
				while (iter.hasNext()){
					ProductionSupportEventTaskResponseEvent eventTaskResponse = (ProductionSupportEventTaskResponseEvent)iter.next();
					log.info(" MERGE - EVENTTASK ID: "+ eventTaskResponse.getEventTaskId()
							+" with casefileID=" + regform.getToCasefile()+ "Logon Id: " + 
							SecurityUIHelper.getLogonId());
				}
			}
			
			if (regform.getNttasks()!= null){
				Iterator iter = regform.getNttasks().iterator();
				while (iter.hasNext()){
					ProductionSupportNTTasksResponseEvent ntTaskResponse = (ProductionSupportNTTasksResponseEvent)iter.next();
					log.info(" MERGE - NTTASKS ID: "+ ntTaskResponse.getTaskId()
							+" with casefileID=" + regform.getToCasefile()+ "Logon Id: " + 
							SecurityUIHelper.getLogonId());
					log.info("**** END LOGGING THE MERGE PROCESSESS FOR CASEFILE ****");
				}
			}
			
			if (regform.getFacilityDetentionList()!= null){
				Iterator iter = regform.getFacilityDetentionList().iterator();
				while (iter.hasNext()){
				    JuvenileDetentionFacilitiesResponseEvent detentionResponse = (JuvenileDetentionFacilitiesResponseEvent)iter.next();
					log.info(" MERGE - NTTASKS ID: "+ detentionResponse.getDetentionId()
							+" with casefileID=" + regform.getToCasefile()+ "Logon Id: " + 
							SecurityUIHelper.getLogonId());
					log.info("**** END LOGGING THE MERGE PROCESSESS FOR CASEFILE ****");
				}
			}
			if (regform.getFacilityHeaderList()!= null){
				Iterator iter = regform.getFacilityHeaderList().iterator();
				while (iter.hasNext()){
				    JuvenileFacilityHeaderResponseEvent headerResponse = (JuvenileFacilityHeaderResponseEvent)iter.next();
					log.info(" MERGE - NTTASKS ID: "+ headerResponse.getHeaderId()
							+" with casefileID=" + regform.getToCasefile()+ "Logon Id: " + 
							SecurityUIHelper.getLogonId());
					log.info("**** END LOGGING THE MERGE PROCESSESS FOR CASEFILE ****");
				}
			}
			
			if (regform.getDrugTestingInfos() != null)
			{
			    Iterator iter = regform.getDrugTestingInfos().iterator();
			    while (iter.hasNext())
			    {
				DrugTestingResponseEvent drugTestingResponse = (DrugTestingResponseEvent) iter.next();
				log.info(" MERGE - JCDRUGTESTING ID: " + drugTestingResponse.getDrugTestingId() + " with casefileID=" + regform.getToCasefile() + "Logon Id: " + SecurityUIHelper.getLogonId());
				log.info("**** END LOGGING THE MERGE PROCESSESS FOR CASEFILE ****");
			    }
			}

			if (regform.getSubAbuseInfos() != null)
			{
			    Iterator iter = regform.getSubAbuseInfos().iterator();
			    while (iter.hasNext())
			    {
				SubstanceAbuseResponseEvent substanceAbuseResponse = (SubstanceAbuseResponseEvent) iter.next();
				log.info(" MERGE - JCSUBABUSE ID: " + substanceAbuseResponse.getSubstanceAbuseId() + " with casefileID=" + regform.getToCasefile() + "Logon Id: " + SecurityUIHelper.getLogonId());
				log.info("**** END LOGGING THE MERGE PROCESSESS FOR CASEFILE ****");
			    }
			}
	}
	
	/**
	 * perform merge transaction
	 * @param regform
	 * @param fromCasefile
	 * @param toCasefile
	 * @param errors
	 */
	private void performMergeUpdates(ProdSupportForm regform, String fromCasefile, String toCasefile){
		log.info("**** START - Performed a CASEFILE MERGE ****");
		if (regform.getCaseplans()!=null){
			UpdateProductionSupportCaseplanEvent updateCaseplanEvent = (UpdateProductionSupportCaseplanEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTCASEPLAN) ;
		
			updateCaseplanEvent.setCasefileId(fromCasefile);
			updateCaseplanEvent.setMergeToCasefileId(toCasefile);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			log.info("START CASEFILE MERGE CASEPLANS: " + SecurityUIHelper.getLogonId());
			dispatch.postEvent(updateCaseplanEvent);	
			log.info("FINISH CASEFILE MERGE CASEPLANS: " + SecurityUIHelper.getLogonId());
		}
		if (regform.getActivities()!=null){			
			UpdateProductionSupportActivityEvent updateActivityEvent = (UpdateProductionSupportActivityEvent) 
				EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTACTIVITY) ;
			
			updateActivityEvent.setCasefileId(fromCasefile);
			updateActivityEvent.setMergeToCasefileId(toCasefile);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			log.info("START CASEFILE MERGE ACTIVITIES: " + SecurityUIHelper.getLogonId());
			dispatch.postEvent(updateActivityEvent);		
			log.info("FINISH CASEFILE MERGE ACTIVITIES: " + SecurityUIHelper.getLogonId());
		}
		
		if (regform.getAssignments()!=null){
			
			UpdateProductionSupportAssignmentEvent updateAssignmentEvent = (UpdateProductionSupportAssignmentEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTASSIGNMENT) ;
			// assignment logic
			ArrayList<AssignmentResponseEvent> newAssignments = 
				getNewAssignments(regform.getAssignments(), toCasefile);		
			if (newAssignments!=null){
		
				Iterator iter = newAssignments.iterator();		
				while (iter.hasNext()){
					AssignmentResponseEvent next = (AssignmentResponseEvent)iter.next();
					updateAssignmentEvent.setCasefileId(fromCasefile);
					updateAssignmentEvent.setMergeToCasefileId(toCasefile);
					updateAssignmentEvent.setReferralNumber(next.getReferralNum());
					IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
					log.info("START CASEFILE MERGE ASSIGNMENTS: " + SecurityUIHelper.getLogonId());
					dispatch.postEvent(updateAssignmentEvent);	
					log.info("FINISH CASEFILE MERGE ASSIGNMENTS: " + SecurityUIHelper.getLogonId());
				}
			}
		}
		
		if (regform.getAssnmnthists() != null){
			
			UpdateProductionSupportJpoAssignmentHistoryEvent updateJpoAssignmentHistoryEvent = (UpdateProductionSupportJpoAssignmentHistoryEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTJPOASSIGNMENTHISTORY) ;
		
			updateJpoAssignmentHistoryEvent.setCasefileId(fromCasefile);
			updateJpoAssignmentHistoryEvent.setMergeToCasefileId(toCasefile);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			log.info("START CASEFILE MERGE JPO ASSIGNMENT HISTORY: " + SecurityUIHelper.getLogonId());
			dispatch.postEvent(updateJpoAssignmentHistoryEvent);	
			log.info("FINISH CASEFILE MERGE JPO ASSIGNMENT HISTORY: " + SecurityUIHelper.getLogonId());
		}
		
		if (regform.getCasefileclosings()!=null){
			/**There can only be one casefileclosing record per casefile,
			 * so if one already exists, skip this step.
			 */
			
			if (closingExists(toCasefile)==false){
				UpdateProductionSupportCasefileClosingEvent updateCasefileClosingEvent = (UpdateProductionSupportCasefileClosingEvent) 
				EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTCASEFILECLOSING) ;
			
				updateCasefileClosingEvent.setCasefileId(fromCasefile);
				updateCasefileClosingEvent.setMergeToCasefileId(toCasefile);
				IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
				log.info("START CASEFILE MERGE CASEFILE CLOSING: " + SecurityUIHelper.getLogonId());
				dispatch.postEvent(updateCasefileClosingEvent);	
				log.info("FINISH CASEFILE MERGE CASEFILE CLOSING: " + SecurityUIHelper.getLogonId());
			}
		}
		
		if (regform.getInterviews()!=null){
			
			UpdateProductionSupportInterviewEvent updateInterviewEvent = (UpdateProductionSupportInterviewEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTINTERVIEW) ;
		
			updateInterviewEvent.setCasefileId(fromCasefile);
			updateInterviewEvent.setMergeToCasefileId(toCasefile);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			log.info("START CASEFILE MERGE INTERVIEWS: " + SecurityUIHelper.getLogonId());
			dispatch.postEvent(updateInterviewEvent);	
			log.info("FINISH CASEFILE MERGE INTERVIEWS: " + SecurityUIHelper.getLogonId());
		}
		
		if (regform.getIviewdocs()!=null){
			
			UpdateProductionSupportIViewDocEvent updateIViewDocEvent = (UpdateProductionSupportIViewDocEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTIVIEWDOC) ;
		
			updateIViewDocEvent.setCasefileId(fromCasefile);
			updateIViewDocEvent.setMergeToCasefileId(toCasefile);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			log.info("START CASEFILE MERGE IVIEWDOCS: " + SecurityUIHelper.getLogonId());
			dispatch.postEvent(updateIViewDocEvent);	
			log.info("FINISH CASEFILE MERGE IVIEWDOCS: " + SecurityUIHelper.getLogonId());
		}
		
		if (regform.getRiskanalyses()!=null){
			
			UpdateProductionSupportRiskAnalysisEvent updateRiskAnalysisEvent = (UpdateProductionSupportRiskAnalysisEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTRISKANALYSIS) ;
		
			updateRiskAnalysisEvent.setCasefileId(fromCasefile);
			updateRiskAnalysisEvent.setMergeToCasefileId(toCasefile);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			log.info("START CASEFILE MERGE RISK ANALYSIS: " + SecurityUIHelper.getLogonId());
			dispatch.postEvent(updateRiskAnalysisEvent);	
			log.info("FINISH CASEFILE MERGE RISK ANALYSIS: " + SecurityUIHelper.getLogonId());
		}
		
		if (regform.getJuvprogrefs()!=null){
			
			UpdateProductionSupportJuvenileProgramReferralEvent updateJuvenileProgramReferralEvent = (UpdateProductionSupportJuvenileProgramReferralEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTJUVENILEPROGRAMREFERRAL) ;
		
			updateJuvenileProgramReferralEvent.setCasefileId(fromCasefile);
			updateJuvenileProgramReferralEvent.setMergeToCasefileId(toCasefile);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			log.info("START CASEFILE MERGE JUVENILE PROGRAM REFERRAL: " + SecurityUIHelper.getLogonId());
			dispatch.postEvent(updateJuvenileProgramReferralEvent);	
			log.info("FINISH CASEFILE MERGE JUVENILE PROGRAM REFERRAL: " + SecurityUIHelper.getLogonId());
		}	
		
		if (regform.getBeneasmnts()!=null){
			
			UpdateProductionSupportBenefitAssessmentEvent updateBenefitAssessmentEvent = (UpdateProductionSupportBenefitAssessmentEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTBENEFITASSESSMENT) ;
		
			updateBenefitAssessmentEvent.setCasefileId(fromCasefile);
			updateBenefitAssessmentEvent.setMergeToCasefileId(toCasefile);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			log.info("START CASEFILE MERGE BENEFIT ASSESSMENT: " + SecurityUIHelper.getLogonId());
			dispatch.postEvent(updateBenefitAssessmentEvent);	
			log.info("FINISH CASEFILE MERGE BENEFIT ASSESSMENT: " + SecurityUIHelper.getLogonId());
		}	
		
		if (regform.getSuprules()!=null){
			
			UpdateProductionSupportJuvenileCasefileSupervisionRulesEvent updateSupervisionRulesEvent = 
				(UpdateProductionSupportJuvenileCasefileSupervisionRulesEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTJUVENILECASEFILESUPERVISIONRULES) ;
		
			updateSupervisionRulesEvent.setCasefileId(fromCasefile);
			updateSupervisionRulesEvent.setMergeToCasefileId(toCasefile);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			log.info("START CASEFILE MERGE SUPERVISION RULES: " + SecurityUIHelper.getLogonId());
			dispatch.postEvent(updateSupervisionRulesEvent);	
			log.info("FINISH CASEFILE MERGE SUPERVISION RULES: " + SecurityUIHelper.getLogonId());
		}
		
		if (regform.getCaleventconts()!=null){
			UpdateProductionSupportCalendarEventContextEvent updateCalendarContextEvent = 
				(UpdateProductionSupportCalendarEventContextEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTCALENDAREVENTCONTEXT) ;
		
			updateCalendarContextEvent.setCasefileId(fromCasefile);
			updateCalendarContextEvent.setMergeToCasefileId(toCasefile);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			log.info("START CASEFILE MERGE CALENDAR CONTEXT: " + SecurityUIHelper.getLogonId());
			dispatch.postEvent(updateCalendarContextEvent);	
			log.info("FINISH CASEFILE MERGE CALENDAR CONTEXT: " + SecurityUIHelper.getLogonId());
		}
		
		if (regform.getProgrfasgnhists()!=null){
		
			UpdateProductionSupportJuvenileProgramReferralAssignmentHistoryEvent updateProgramReferralAssignmentHistoryEvent = 
				(UpdateProductionSupportJuvenileProgramReferralAssignmentHistoryEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTJUVENILEPROGRAMREFERRALASSIGNMENTHISTORY) ;
		
			updateProgramReferralAssignmentHistoryEvent.setCasefileId(fromCasefile);
			updateProgramReferralAssignmentHistoryEvent.setMergeToCasefileId(toCasefile);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			log.info("START CASEFILE MERGE PROGRAM REFERRAL ASSIGNMENT HISTORY: " + SecurityUIHelper.getLogonId());
			dispatch.postEvent(updateProgramReferralAssignmentHistoryEvent);	
			log.info("FINISH CASEFILE MERGE PROGRAM REFERRAL ASSIGNMENT HISTORY: " + SecurityUIHelper.getLogonId());
		}
		
		if (regform.getTraits()!=null){
			UpdateProductionSupportTraitEvent updateTraitEvent = (UpdateProductionSupportTraitEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTTRAIT) ;
		
			updateTraitEvent.setCasefileId(fromCasefile);
			updateTraitEvent.setMergeToCasefileId(toCasefile);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			log.info("START CASEFILE MERGE TRAITS: " + SecurityUIHelper.getLogonId());
			dispatch.postEvent(updateTraitEvent);	
			log.info("FINISH CASEFILE MERGE TRAITS: " + SecurityUIHelper.getLogonId());
		}
		
		if (regform.getDrugTestingInfos()!=null && regform.getDrugTestingCount() > 0){
			UpdateProductionSupportDrugTestingInfoEvent updateEvent = (UpdateProductionSupportDrugTestingInfoEvent)EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTDRUGTESTINGINFO);
				new UpdateProductionSupportDrugTestingInfoEvent();		
			updateEvent.setCasefileId(fromCasefile);
			updateEvent.setMergeToCasefileId(toCasefile);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			log.info("START CASEFILE MERGE DRUG TESTING INFO: " + SecurityUIHelper.getLogonId());
			dispatch.postEvent(updateEvent);	
			log.info("FINISH CASEFILE DRUG TESTING INFO: " + SecurityUIHelper.getLogonId());
		}
		
		if (regform.getSubAbuseInfos()!=null && regform.getSubAbuseCount() > 0){
			UpdateProductionSupportSubstanceAbuseEvent updateEvent = (UpdateProductionSupportSubstanceAbuseEvent)EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTSUBSTANCEABUSE);	
			updateEvent.setCasefileId(fromCasefile);
			updateEvent.setMergeToCasefileId(toCasefile);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			log.info("START CASEFILE MERGE SUBSTANCE ABUSE: " + SecurityUIHelper.getLogonId());
			dispatch.postEvent(updateEvent);	
			log.info("FINISH CASEFILE MERGE SUBSTANCE ABUSE: " + SecurityUIHelper.getLogonId());
		}
		
		
		//MERGE BOOKING SUPERVISION NUM and CURRENT SUPERVISION NUM US 156575 STARTS
				
		if (regform.getFacilityDetentionList() != null || regform.getHeaderInfo() != null){
		    UpdateProductionSupportSupervisionNumEvent updateSupervisionNum = (UpdateProductionSupportSupervisionNumEvent)
			    EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTSUPERVISIONNUM);
		    
		    updateSupervisionNum.setCasefileId(fromCasefile);
		    updateSupervisionNum.setMergeToCasefileId(toCasefile);
		    IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		    log.info("START CASEFILE MERGE FACILITY ADMIT/RELEASE: " + SecurityUIHelper.getLogonId());
		    dispatch.postEvent(updateSupervisionNum);	
		    log.info("FINISH CASEFILE MERGE FACILITY ADMIT/RELEASE: " + SecurityUIHelper.getLogonId());
		}
		//MERGE BOOKING SUPERVISION NUM and CURRENT SUPERVISION NUM US 156575 ENDS

		/* Refresh UPDATEDATE on the new CASEFILE record */
		if (toCasefile!=null && !toCasefile.equals("")){
			UpdateProductionSupportCasefileEvent updateCasefileEvent = (UpdateProductionSupportCasefileEvent) 
			EventFactory.getInstance(ProductionSupportControllerServiceNames.UPDATEPRODUCTIONSUPPORTCASEFILE) ;
			// update logon id update field to also set update date 
			updateCasefileEvent.setSupervisionId(fromCasefile);
			IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
			log.info("START CASEFILE MERGE CASEPLANS: " + SecurityUIHelper.getLogonId());
			dispatch.postEvent(updateCasefileEvent);	
			log.info("FINISH CASEFILE MERGE CASEPLANS: " + SecurityUIHelper.getLogonId());
		}
		
	}
	
	/**
	 * Check if a Casefile Closing already exists for the To Casefile Record
	 * @param toCasefile
	 * @return
	 */
	private boolean closingExists(String toCasefile){
		boolean exists = false;

		CasefileClosingResponseEvent respEvent = UIJuvenileCasefileClosingHelper.
		getCasefileClosingDetails(toCasefile);	
		
		if (respEvent != null && respEvent.getCasefileClosingInfoId() != null){
			log.info(" MERGE CASEFILE_ID: " + toCasefile
					+ ", Closing information already exists. Will not merge new record. " + SecurityUIHelper.getLogonId());

			exists=true;
		}
			
		return exists;
	}
	
	/**
	 * Retrieve existing assignments on merge To Casefile and determine if they should be merged
	 * @param assignments
	 * @param toCasefile
	 * @return
	 */
	private ArrayList<AssignmentResponseEvent> getNewAssignments(ArrayList<AssignmentResponseEvent> fromAssignments, String toCasefile)
	{
		ArrayList<AssignmentResponseEvent> uniqueAssignments = new ArrayList<AssignmentResponseEvent>();
		if (toCasefile != null && toCasefile.equals("") == false){
			GetAssignmentsByCasefileIdEvent getAssignments = (GetAssignmentsByCasefileIdEvent)
			EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETASSIGNMENTSBYCASEFILEID);
			getAssignments.setCasefileId(toCasefile);
			CompositeResponse assignmentResponse = MessageUtil.postRequest(getAssignments);
			ArrayList toAssignmentList = (ArrayList) MessageUtil.compositeToCollection(assignmentResponse,
					AssignmentResponseEvent.class);
		
			if (toAssignmentList != null){
			
				Iterator toIter = toAssignmentList.iterator();
				while (toIter.hasNext()){		
					
					AssignmentResponseEvent toAssignmentRecord = (AssignmentResponseEvent) toIter.next();
					Iterator fromIter = fromAssignments.iterator();			
					while (fromIter.hasNext()){
						AssignmentResponseEvent fromAssignmentRecord = (AssignmentResponseEvent)fromIter.next();
						if (fromAssignmentRecord.getReferralNum().equals(toAssignmentRecord.getReferralNum())){
							log.info(" MERGE JCASSIGNMENT_ID: " +
									fromAssignmentRecord.getAssignmentId() + ", Referral Number "
									+ fromAssignmentRecord.getReferralNum() + " already exists in target, not merged." + SecurityUIHelper.getLogonId());					
							fromAssignmentRecord.setIsDup(true);
							break;
						}
							
			
					}
				}
				Iterator fromIter = fromAssignments.iterator();			
				while (fromIter.hasNext()){
					AssignmentResponseEvent fromAssignmentRecord = (AssignmentResponseEvent)fromIter.next();
					if(fromAssignmentRecord.getIsDup() == false){
						fromAssignmentRecord.setWasMigrated(true);
						fromAssignmentRecord.setWasChecked(true);
						uniqueAssignments.add(fromAssignmentRecord);
					}
				}				
			/**Discovered a unique case where the TO casefile had no assignments. 
			 * In this case, all assignments must be migrated. **/
			}else{			
				uniqueAssignments = new ArrayList<AssignmentResponseEvent>();
				Iterator fromIter = fromAssignments.iterator();			
				while (fromIter.hasNext()){
					AssignmentResponseEvent fromAssignmentRecord = (AssignmentResponseEvent)fromIter.next();			
					fromAssignmentRecord.setWasMigrated(true);
					fromAssignmentRecord.setWasChecked(true);
					uniqueAssignments.add(fromAssignmentRecord);		
				}		
			}
		}
		return uniqueAssignments;
	}
	
}
