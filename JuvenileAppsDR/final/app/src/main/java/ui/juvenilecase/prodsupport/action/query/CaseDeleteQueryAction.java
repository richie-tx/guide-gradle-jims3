package ui.juvenilecase.prodsupport.action.query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.calendar.reply.CalendarEventContextResponse;
import messaging.casefile.reply.CasefileClosingResponseEvent;
import messaging.caseplan.reply.CaseplanDetailsResponseEvent;
import messaging.interviewinfo.GetInterviewReportsEvent;
import messaging.interviewinfo.reply.InterviewReportHeaderResponseEvent;
import messaging.interviewinfo.reply.InterviewResponseEvent;
import messaging.juvenile.GetDrugTestingInfoEvent;
import messaging.juvenile.GetSubstanceAbuseInfoEvent;
import messaging.juvenile.reply.DrugTestingResponseEvent;
import messaging.juvenile.reply.SubstanceAbuseResponseEvent;
import messaging.juvenilecase.GetAssignmentsByCasefileIdEvent;
import messaging.juvenilecase.GetJuvenileCasefileByCasefileIdEvent;
import messaging.juvenilecase.reply.AssignmentResponseEvent;
import messaging.juvenilecase.reply.JPOAssignmentHistoryResponseEvent;
import messaging.juvenilecase.reply.JuvenileCasefileResponseEvent;
import messaging.juvenilecase.reply.JuvenileTraitResponseEvent;
import messaging.juvenilecase.reply.RiskAssessmentListResponseEvent;
import messaging.juvenilecase.reply.RiskResponseEvent;
import messaging.productionsupport.GetProductionSupportAssociatedInterviewsEvent;
import messaging.productionsupport.GetProductionSupportBenefitAssessmentsEvent;
import messaging.productionsupport.GetProductionSupportCalendarEventContextEvent;
import messaging.productionsupport.GetProductionSupportCaseplansEvent;
import messaging.productionsupport.GetProductionSupportEventTasksEvent;
import messaging.productionsupport.GetProductionSupportJpoAssignmentHistoryEvent;
import messaging.productionsupport.GetProductionSupportJuvenileCasefileSupervisionRulesEvent;
import messaging.productionsupport.GetProductionSupportJuvenileProgramReferralAssignmentHistoryEvent;
import messaging.productionsupport.GetProductionSupportJuvenileProgramReferralsEvent;
import messaging.productionsupport.GetProductionSupportNTTasksEvent;
import messaging.productionsupport.GetProductionSupportRiskResponsesEvent;
import messaging.productionsupport.GetProductionSupportTraitsEvent;
import messaging.productionsupport.reply.ProductionSupportBenefitsAssessmentsResponseEvent;
import messaging.productionsupport.reply.ProductionSupportEventTaskResponseEvent;
import messaging.productionsupport.reply.ProductionSupportJuvenileProgramReferralResponseEvent;
import messaging.productionsupport.reply.ProductionSupportNTTasksResponseEvent;
import messaging.productionsupport.reply.ProductionSupportSupervisionRulesResponseEvent;
import messaging.programreferral.reply.ProgramReferralAssignmentHistoryResponseEvent;
import messaging.riskanalysis.GetRiskAssessmentsByCasefileEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCaseControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileInterviewInfoControllerServiceNames;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.ProductionSupportControllerServiceNames;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.juvenilecase.UIJuvenileCasefileClosingHelper;
import ui.juvenilecase.activities.ActivitiesHelper;
import ui.juvenilecase.form.ProdSupportForm;
import ui.juvenilecase.prodsupport.helpers.Constants;
import ui.juvenilecase.prodsupport.helpers.QueryObject;
import ui.security.SecurityUIHelper;

/**
 * @author rcarter
 * 
 *         The DELETE process is divided into two actions, CaseDeleteQueryAction and
 *         PerformCaseDeleteAction.
 *         CaseDeleteQueryAction gathers information on what data will be affected
 *         by the delete, and displays this information to the user on the
 *         caseDeleteQueryResult.jsp.
 *         PeformCaseDeleteAction actually executes the delete statement after
 *         getting confirmation from the user, and displays a summary on the
 *         caseDeleteSummary.jsp.
 */
public class CaseDeleteQueryAction extends Action {

	private Logger log = Logger.getLogger("CaseDeleteQueryAction");
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		ProdSupportForm regform = (ProdSupportForm) form;

		/** Check for initial load of this page **/
		String clrChk = request.getParameter("clr");
		if (clrChk != null && clrChk.equalsIgnoreCase("Y")) {
			regform.clearAllResults();
			regform.setMsg("");
			return mapping.findForward("error");
		}

		String casefileId = regform.getCasefileId();

		if (casefileId == null || casefileId.equals(" ")) {
			regform.setMsg("CasefileId was null.");
			return mapping.findForward("error");
		}

		// Clear the form
		regform.clearAllResults();

		//Check for invalid casefile
		if (casefileExists(casefileId)==false){
			regform.setMsg("CasefileId " + casefileId + " does not exist in the database.");
			return mapping.findForward("error");
		}
		
		// Log the query attempt
		log.info("Casefile Query ID: " + casefileId + " LogonId: " +
				SecurityUIHelper.getLogonId());
		
		// Search and populate the casefile records
		//regform = retrieveCasefileRecords(regform, casefileId);
		regform = retrieveDataForCaseFileDelete(regform, casefileId);

		if (regform.getCasefiles() == null || regform.getCasefiles().size() == 0) {
			regform.setMsg("No casefile records returned for casefileID "
					+ casefileId);
			return mapping.findForward("error");
		}

		regform.setMsg("");
		return mapping.findForward("success");

	}

	/**
	 * 
	 * @param existingProdSupportForm
	 * @return
	 */
	private ProdSupportForm retrieveDataForCaseFileDelete(ProdSupportForm  existingProdSupportForm, String casefileId){
	
		// Get and set CaseFile
		JuvenileCasefileResponseEvent fromCaseFileReturnEvent = retrieveCaseFile(casefileId);
		ArrayList casefileList = new ArrayList();
		if(fromCaseFileReturnEvent != null){
			casefileList.add(fromCaseFileReturnEvent);
			existingProdSupportForm.setCasefileCount(casefileList.size());
			existingProdSupportForm.setCasefiles(casefileList);
		}
		
		// Get and set Associated Activities
		ArrayList caseFileActivitiesList = (ArrayList)ActivitiesHelper.getActivities(casefileId, null, null, null, null);
		if (caseFileActivitiesList != null && caseFileActivitiesList.size() > 0) {
			existingProdSupportForm.setActivityCount(caseFileActivitiesList.size());
			existingProdSupportForm.setActivities(caseFileActivitiesList);
		}
		
		// Get and set Associated Assignments
		GetAssignmentsByCasefileIdEvent getAssignments = (GetAssignmentsByCasefileIdEvent)
		EventFactory.getInstance(JuvenileCaseControllerServiceNames.GETASSIGNMENTSBYCASEFILEID);
		getAssignments.setCasefileId(casefileId);
		CompositeResponse assignmentResponse = MessageUtil.postRequest(getAssignments);
		ArrayList assignmentList = (ArrayList) MessageUtil.compositeToCollection(assignmentResponse,
				AssignmentResponseEvent.class);
		
		if (assignmentList != null && assignmentList.size() > 0) {
			existingProdSupportForm.setAssignmentCount(assignmentList.size());
			existingProdSupportForm.setAssignments(assignmentList);
		}
		
		// Get and set Associated JPO AssnmentHists
		GetProductionSupportJpoAssignmentHistoryEvent getAssignmentHistoryEvent = (GetProductionSupportJpoAssignmentHistoryEvent) 
		EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJPOASSIGNMENTHISTORY) ;
	
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		getAssignmentHistoryEvent.setCasefileId(casefileId);
		dispatch.postEvent(getAssignmentHistoryEvent);		
		CompositeResponse compositeResponse = (CompositeResponse) dispatch.getReply();
		
		ArrayList<JPOAssignmentHistoryResponseEvent> assignmentHistoryEvents = (ArrayList)
			MessageUtil.compositeToCollection(compositeResponse, JPOAssignmentHistoryResponseEvent.class);
		if (assignmentHistoryEvents != null && assignmentHistoryEvents.size() > 0) {
			existingProdSupportForm.setAssnmnthistCount(assignmentHistoryEvents.size());
			existingProdSupportForm.setAssnmnthists(assignmentHistoryEvents);
		}
		
		// Get and set Associated Casefile Closings
		CasefileClosingResponseEvent respEvent = UIJuvenileCasefileClosingHelper.
			getCasefileClosingDetails(casefileId);
		ArrayList assignmentHistoryEventsList = new ArrayList();		
		if (respEvent != null) {
			assignmentHistoryEventsList.add(respEvent);
			existingProdSupportForm.setCasefileClosingCount(assignmentHistoryEventsList.size());
			existingProdSupportForm.setCasefileclosings(assignmentHistoryEventsList);
		}
		
		// Get and set Associated Interviews
		GetProductionSupportAssociatedInterviewsEvent getInterviewsEvent = (GetProductionSupportAssociatedInterviewsEvent)
		EventFactory.getInstance(ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTASSOCIATEDINTERVIEWS);
		getInterviewsEvent.setCasefileId(casefileId);
		dispatch.postEvent(getInterviewsEvent);
		CompositeResponse interviewsResponse = (CompositeResponse) dispatch.getReply();
		ArrayList interviewsList = (ArrayList) MessageUtil.compositeToCollection(interviewsResponse, InterviewResponseEvent.class);
		if (interviewsList != null && interviewsList.size() > 0) {
			existingProdSupportForm.setInterviewCount(interviewsList.size());
			existingProdSupportForm.setInterviews(interviewsList);
		}
		
		// Get and set Associated Iviewdocs
		GetInterviewReportsEvent getInterviewDocs = (GetInterviewReportsEvent)
			EventFactory.getInstance(JuvenileInterviewInfoControllerServiceNames.GETINTERVIEWREPORTS);
		getInterviewDocs.setCasefileId(casefileId);
		dispatch.postEvent(getInterviewDocs);
		CompositeResponse interviewDocsResponse = (CompositeResponse) dispatch.getReply();
		ArrayList interviewsDocsList = (ArrayList) MessageUtil.compositeToCollection(interviewDocsResponse, InterviewReportHeaderResponseEvent.class);
		if (interviewsDocsList != null && interviewsDocsList.size() > 0) {	
			existingProdSupportForm.setIviewdocCount(interviewsDocsList.size());
			existingProdSupportForm.setIviewdocs(interviewsDocsList);
		}
		
		// Get and set Associated Risk Analyses
		GetRiskAssessmentsByCasefileEvent riskAssessEvent = (GetRiskAssessmentsByCasefileEvent)EventFactory.
		getInstance( JuvenileRiskAnalysisControllerServiceNames.GETRISKASSESSMENTSBYCASEFILE );
		riskAssessEvent.setCasefileId( casefileId );
		CompositeResponse riskAssessResp = MessageUtil.postRequest(riskAssessEvent);
		ArrayList riskAssessmentsList = (ArrayList) MessageUtil.compositeToCollection(riskAssessResp, RiskAssessmentListResponseEvent.class);
		if (riskAssessmentsList != null && riskAssessmentsList.size() > 0) {
			existingProdSupportForm.setRiskanalysisCount(riskAssessmentsList.size());
			existingProdSupportForm.setRiskanalyses(riskAssessmentsList);
		}
		
		// Get and set Risk Responses
		GetProductionSupportRiskResponsesEvent riskResponseEvent = (GetProductionSupportRiskResponsesEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTRISKRESPONSES );
		ArrayList<RiskResponseEvent> riskResponseEventList = new ArrayList<RiskResponseEvent>();
		if(existingProdSupportForm.getRiskanalyses() != null){
			for( int i = 0; i < existingProdSupportForm.getRiskanalyses().size() ; i ++ ){
				RiskAssessmentListResponseEvent riskAssessmentResponseEvent = (RiskAssessmentListResponseEvent) existingProdSupportForm.getRiskanalyses().get(i);
				riskResponseEvent.setRiskAssessmentId( riskAssessmentResponseEvent.getAssessmentID());
				CompositeResponse riskResponsesResponse = MessageUtil.postRequest(riskResponseEvent);
				ArrayList riskResponsesList = (ArrayList) MessageUtil.compositeToCollection(riskResponsesResponse, RiskResponseEvent.class);
				if (riskResponsesList != null && riskResponsesList.size() > 0) {
					riskResponseEventList.addAll(riskResponsesList);
				}
			}
			existingProdSupportForm.setRiskresponsesCount(riskResponseEventList.size());
			existingProdSupportForm.setRiskresponses(riskResponseEventList);
		}
		
		// Get and set Associated JuvProgRefs
		GetProductionSupportJuvenileProgramReferralsEvent getJuvenileProgramRerralsEvent = (GetProductionSupportJuvenileProgramReferralsEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEPROGRAMREFERRALS );
		getJuvenileProgramRerralsEvent.setCasefileId( casefileId );
		CompositeResponse juvenileProgramReferralsResp = MessageUtil.postRequest(getJuvenileProgramRerralsEvent);
		ArrayList juvenileProgramReferralsList = (ArrayList) MessageUtil.compositeToCollection(juvenileProgramReferralsResp, ProductionSupportJuvenileProgramReferralResponseEvent.class);
		if (juvenileProgramReferralsList != null && juvenileProgramReferralsList.size() > 0) {
			existingProdSupportForm.setJuvprogrefCount(juvenileProgramReferralsList.size());
			existingProdSupportForm.setJuvprogrefs(juvenileProgramReferralsList);
		}
		
		// Get and set Associated BenefitsAssessment Beneasmnts
		GetProductionSupportBenefitAssessmentsEvent benefitAssessmentsEvent = (GetProductionSupportBenefitAssessmentsEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTBENEFITASSESSMENTS );
		benefitAssessmentsEvent.setCasefileId( casefileId );
		CompositeResponse benefitAssessmentsResponse = MessageUtil.postRequest(benefitAssessmentsEvent);
		ArrayList benefitAssessmentResponsesList = (ArrayList) MessageUtil.compositeToCollection(benefitAssessmentsResponse, ProductionSupportBenefitsAssessmentsResponseEvent.class);
		if (benefitAssessmentResponsesList != null && benefitAssessmentResponsesList.size() > 0) {
			existingProdSupportForm.setBeneasmntCount(benefitAssessmentResponsesList.size());
			existingProdSupportForm.setBeneasmnts(benefitAssessmentResponsesList);
		}
		
		
		// Get and set Associated Associated Supervision Rules (Suprules)
		GetProductionSupportJuvenileCasefileSupervisionRulesEvent juvenileCasefileSupervisionRulesEvent = (GetProductionSupportJuvenileCasefileSupervisionRulesEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILECASEFILESUPERVISIONRULES );
		juvenileCasefileSupervisionRulesEvent.setCasefileId( casefileId );
		CompositeResponse juvenileCasefileSupervisionResponse = MessageUtil.postRequest(juvenileCasefileSupervisionRulesEvent);
		ArrayList juvenileCasefileSupervisionResponsesList = (ArrayList) MessageUtil.compositeToCollection(juvenileCasefileSupervisionResponse, ProductionSupportSupervisionRulesResponseEvent.class);
		
		if (juvenileCasefileSupervisionResponsesList != null && juvenileCasefileSupervisionResponsesList.size()  > 0) {
			existingProdSupportForm.setSupruleCount(juvenileCasefileSupervisionResponsesList.size());
			existingProdSupportForm.setSuprules(juvenileCasefileSupervisionResponsesList);
		}
		
		// Get and set Associated CalEventConts
		GetProductionSupportCalendarEventContextEvent getCalendarEventContextEvent = (GetProductionSupportCalendarEventContextEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCALENDAREVENTCONTEXT );
		getCalendarEventContextEvent.setCasefileId( casefileId );
		CompositeResponse calendarEventContextResponse = MessageUtil.postRequest(getCalendarEventContextEvent);
		ArrayList calendarEventContextResponsesList = (ArrayList) MessageUtil.compositeToCollection(calendarEventContextResponse, CalendarEventContextResponse.class);
		
		if (calendarEventContextResponsesList != null && calendarEventContextResponsesList.size()  > 0) {
			existingProdSupportForm.setCaleventcontCount(calendarEventContextResponsesList.size());
			existingProdSupportForm.setCaleventconts(calendarEventContextResponsesList);
		}
		
		// Get and set Associated EventTasks
		GetProductionSupportEventTasksEvent getEventTasksEvent = (GetProductionSupportEventTasksEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTEVENTTASKS );
		getEventTasksEvent.setCasefileId( casefileId );
		CompositeResponse getEventTasksResponse = MessageUtil.postRequest(getEventTasksEvent);
		ArrayList getEventTasksResponsesList = (ArrayList) MessageUtil.compositeToCollection(getEventTasksResponse, ProductionSupportEventTaskResponseEvent.class);
		
		if (getEventTasksResponsesList != null && getEventTasksResponsesList.size()  > 0) {
			existingProdSupportForm.setEventtaskCount(getEventTasksResponsesList.size());
			existingProdSupportForm.setEventtasks(getEventTasksResponsesList);
		}
		
		// Get and set Associated NTTasks
		GetProductionSupportNTTasksEvent getNTTasksEvent = (GetProductionSupportNTTasksEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTNTTASKS );
		getNTTasksEvent.setCasefileId( casefileId );
		CompositeResponse getNTTasksResponse = MessageUtil.postRequest(getNTTasksEvent);
		ArrayList getNTTasksResponsesList = (ArrayList) MessageUtil.compositeToCollection(getNTTasksResponse, ProductionSupportNTTasksResponseEvent.class);
		
		if (getNTTasksResponsesList != null && getNTTasksResponsesList.size()  > 0) {
			existingProdSupportForm.setNttaskCount(getNTTasksResponsesList.size());
			existingProdSupportForm.setNttasks(getNTTasksResponsesList);
		}
		
		// Get and set Associated Caseplans
		GetProductionSupportCaseplansEvent getCaseplansEvent = (GetProductionSupportCaseplansEvent)EventFactory.
		getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTCASEPLANS );
		getCaseplansEvent.setCasefileId( casefileId );
		CompositeResponse getCaseplansResponse = MessageUtil.postRequest(getCaseplansEvent);
		ArrayList getCaseplansResponsesList = (ArrayList) MessageUtil.compositeToCollection(getCaseplansResponse, CaseplanDetailsResponseEvent.class);
		
		if (getCaseplansResponsesList != null && getCaseplansResponsesList.size()  > 0) {
			existingProdSupportForm.setCaseplanCount(getCaseplansResponsesList.size());
			existingProdSupportForm.setCaseplans(getCaseplansResponsesList);
		}
		
		// Get and set Associated Program Referral Assignment Histories (Associated ProgRefAsgnHists
		GetProductionSupportJuvenileProgramReferralAssignmentHistoryEvent getJuvProgramAssignmentHistoriesEvent = 
			(		GetProductionSupportJuvenileProgramReferralAssignmentHistoryEvent)EventFactory.
			getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTJUVENILEPROGRAMREFERRALASSIGNMENTHISTORY );
		getJuvProgramAssignmentHistoriesEvent.setCasefileId( casefileId );
		CompositeResponse getJuvProgramAssignmentHistoriesResponse = MessageUtil.postRequest(getJuvProgramAssignmentHistoriesEvent);
		ArrayList getJuvProgramAssignmentHistoriesResponsesList = 			
			(ArrayList) MessageUtil.compositeToCollection(getJuvProgramAssignmentHistoriesResponse, ProgramReferralAssignmentHistoryResponseEvent.class);
		
		if (getJuvProgramAssignmentHistoriesResponsesList != null && getJuvProgramAssignmentHistoriesResponsesList.size()  > 0) {
			existingProdSupportForm.setProgrfasgnhistCount(getJuvProgramAssignmentHistoriesResponsesList.size());
			existingProdSupportForm.setProgrfasgnhists(getJuvProgramAssignmentHistoriesResponsesList);
		}	
		
		// Get and set Associated Associated JCTraits
		GetProductionSupportTraitsEvent getTraitsEvent = (GetProductionSupportTraitsEvent)EventFactory.
			getInstance( ProductionSupportControllerServiceNames.GETPRODUCTIONSUPPORTTRAITS );
		getTraitsEvent.setCasefileId( casefileId );
		CompositeResponse getTraitsResponse = MessageUtil.postRequest(getTraitsEvent);
		ArrayList getTraitsResponsesList = (ArrayList) MessageUtil.compositeToCollection(getTraitsResponse, JuvenileTraitResponseEvent.class);
		
		if (getTraitsResponsesList != null && getTraitsResponsesList.size()  > 0) {
			existingProdSupportForm.setTraitCount(getTraitsResponsesList.size());
			existingProdSupportForm.setTraits(getTraitsResponsesList);
		}
		
		 GetDrugTestingInfoEvent getDrugTestingInfoEvent = (GetDrugTestingInfoEvent)
				EventFactory.getInstance(JuvenileControllerServiceNames.GETDRUGTESTINGINFO);
		
		 getDrugTestingInfoEvent.setCasefileId(casefileId);
		 CompositeResponse replyEvent = MessageUtil.postRequest(getDrugTestingInfoEvent);
		 Collection<DrugTestingResponseEvent> drugTestingInfos = MessageUtil.compositeToCollection(replyEvent, DrugTestingResponseEvent.class);
		  if ( drugTestingInfos != null
			  && drugTestingInfos.size() > 0 ) {
		      
		      SimpleDateFormat sdf = new SimpleDateFormat("HH:mm"); 
		      
		      for (DrugTestingResponseEvent info : drugTestingInfos) 
		      { 
			  Date testTime = info.getTestTime(); 
			  if (testTime != null) 
			  { 
			      String formattedTime = sdf.format(testTime); 
			      info.setFormattedTestTime(formattedTime); 
			  }
		      }
		      
		      existingProdSupportForm.setDrugTestingCount( drugTestingInfos.size());
		      existingProdSupportForm.setDrugTestingInfos(new ArrayList<DrugTestingResponseEvent>(drugTestingInfos));
		  }
		  else
		  {
		      existingProdSupportForm.setDrugTestingCount(0);
		  }
		  
		  GetSubstanceAbuseInfoEvent getSubstanceAbuseInfoEvent = (GetSubstanceAbuseInfoEvent)
			  EventFactory.getInstance(JuvenileControllerServiceNames.GETSUBSTANCEABUSEINFO);
		  getSubstanceAbuseInfoEvent.setCasefileId( casefileId );
		  CompositeResponse substanceAbuseInfoResp = MessageUtil.postRequest(getSubstanceAbuseInfoEvent);
		  Collection<SubstanceAbuseResponseEvent>substanceAbuseInfos = MessageUtil.compositeToCollection(substanceAbuseInfoResp, SubstanceAbuseResponseEvent.class);
		  if (substanceAbuseInfos != null
			  && substanceAbuseInfos.size() > 0 ){
		      existingProdSupportForm.setSubAbuseCount(substanceAbuseInfos.size());
		      existingProdSupportForm.setSubAbuseInfos(new ArrayList<SubstanceAbuseResponseEvent>(substanceAbuseInfos));
		  }
		  else
		  {
		      existingProdSupportForm.setSubAbuseCount(0);
		  }
		
		
		

		return existingProdSupportForm;	
	}
	
	

	/**
	 * This method was a special request from Carla Glover. 1. Retrieve UV user
	 * code 2. Set jpouserid in associated object to that value
	 **/

	public static void retrieveJpoUserId(QueryObject record) {
		String select = "select userid from jims2.officer where officer_id in ("
				+ "select officer_id from jims2.jccasefile where casefile_id= "
				+ record.getCasefileid() + ");";

		String jpoUserId = null;

		ArrayList<QueryObject> officers = Constants.runQuery(select);

		if (officers != null) {
			Iterator iter = officers.iterator();
			if (iter.hasNext()) {
				QueryObject next = (QueryObject) iter.next();
				jpoUserId = next.getJpouserid();
			}

		}

		if (jpoUserId != null)
			record.setJpouserid(jpoUserId.toUpperCase());
	}

	/**
	 * This method is a modified version of the method above. 1. Retrieve UV
	 * code from supplied officer_ID 2. Return the UV code
	 **/

	public static String retrieveJpoUserByOfficerId(String officerId) {
		String select = "select userid from jims2.officer where officer_id="
				+ officerId;
		String jpoUserId = null;

		ArrayList<QueryObject> officers = Constants.runQuery(select);

		if (officers != null) {
			Iterator iter = officers.iterator();
			if (iter.hasNext()) {
				QueryObject next = (QueryObject) iter.next();
				jpoUserId = next.getJpouserid();
			}

		}

		return jpoUserId;
	}

	/**
	 * This method is the opposite of the method above. 1. Retrieve OFFICER_ID
	 * from supplied UV user code 2. Return the OFFICER_ID
	 **/

	public static String retrieveOfficerId(String uvcode) {
		String officerId = null;

		String select = "select officer_id from jims2.officer where userid='"
				+ uvcode + "';";

		ArrayList<QueryObject> officers = Constants.runQuery(select);

		if (officers != null) {
			Iterator iter = officers.iterator();
			if (iter.hasNext()) {
				QueryObject next = (QueryObject) iter.next();
				officerId = next.getOfficerid();
			}

		}

		return officerId;
	}
	
	/**
	 * 
	 * @param casefileId
	 * @return
	 */
	private JuvenileCasefileResponseEvent retrieveCaseFile(String casefileId){
	
	/**
	 * Search for Casefiles
	 */
	GetJuvenileCasefileByCasefileIdEvent getCasefileEvent = (GetJuvenileCasefileByCasefileIdEvent)
	EventFactory.getInstance( JuvenileCaseControllerServiceNames.GETJUVENILECASEFILEBYCASEFILEID ) ;
	getCasefileEvent.setSupervisionNumber( casefileId ) ;
	
	IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST ) ;
	dispatch.postEvent( getCasefileEvent ) ;

	CompositeResponse aResponse = (CompositeResponse)dispatch.getReply( ) ;
	
	JuvenileCasefileResponseEvent casefile = (JuvenileCasefileResponseEvent)
		MessageUtil.filterComposite( aResponse, JuvenileCasefileResponseEvent.class ) ;

	return casefile;	
	}
	
	/**
	 * 
	 * @param casefileId
	 * @return
	 */
	private boolean casefileExists(String casefileId){
	
	Boolean exists = false;
	/**
	 * Check for Casefile
	 */	
	JuvenileCasefileResponseEvent caseFileReturnEvent = retrieveCaseFile(casefileId);

	if (caseFileReturnEvent  != null){
		exists = true;
	}
	
	return exists;	
	}
	

}
