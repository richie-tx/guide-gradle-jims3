//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\action\\SubmitReferralAssessmentAction.java

package ui.juvenilecase.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils ;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.casefile.CreateActivityEvent;
import messaging.casefile.reply.ActivityResponseEvent;
import messaging.codetable.reply.CodeResponseEvent;
import messaging.interviewinfo.SaveInterviewDocumentEvent;
import messaging.interviewinfo.reply.InterviewReportResponseEvent;
import messaging.juvenile.SaveJuvenileProfileDocumentEvent;
import messaging.juvenilecase.reply.CheckRiskAnalysisOneHourResponseEvent;
import messaging.juvenilecase.reply.RiskRecommendationResponseEvent;
import messaging.riskanalysis.CheckRiskAnalysisOneHourEvent;
import messaging.riskanalysis.SaveDelinquencyHistEvent;
import messaging.riskanalysis.SaveReferralAssessmentEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.messaging.exception.ReturnException;
import mojo.km.messaging.reporting.ReportResponseEvent;
import mojo.km.reporting.IReport;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCasefileControllerServiceNames;
import naming.JuvenileControllerServiceNames;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.RiskAnalysisConstants;
import naming.UIConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import pd.juvenilecase.interviewinfo.InterviewDocument;
import pd.report.transactions.PDFReporting;

import ui.common.UIUtil;
import ui.juvenilecase.casefile.PreCourtStaffingPrintBean;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;
import ui.juvenilecase.form.riskanalysis.RiskAssessmentReferralForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

public class SubmitReferralAssessmentAction extends LookupDispatchAction
{
	/**
	 * @roseuid 433D8A34008D
	 */
	public SubmitReferralAssessmentAction()
	{
	}

	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 433C3D3D02F9
	 */
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
//		RiskAssessmentReferralForm refForm = (RiskAssessmentReferralForm)aForm;
//		RiskAnalysisForm riskForm = (RiskAnalysisForm) aRequest.getSession().getAttribute("riskAnalysisForm");
        String forwardStr = UIConstants.SUCCESS;
		RiskAssessmentReferralForm refForm = (RiskAssessmentReferralForm) aRequest.getSession().getAttribute("riskReferralForm");
    	RiskAnalysisForm riskForm = (RiskAnalysisForm) aForm;

    	CheckRiskAnalysisOneHourEvent event =
        	(CheckRiskAnalysisOneHourEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.CHECKRISKANALYSISONEHOUR);
        event.setCasefileID(riskForm.getCasefileID());
        event.setAssessmentType(RiskAnalysisConstants.RISK_TYPE_DETENTION);
        CheckRiskAnalysisOneHourResponseEvent resp = (CheckRiskAnalysisOneHourResponseEvent) MessageUtil.postRequest(event,CheckRiskAnalysisOneHourResponseEvent.class);

        //If one hour check fails, forward back to summary page
        if (resp != null && resp.isMessage() == true) {
        	ActionErrors errors = new ActionErrors();
        	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.assessmenttypecannotbedonewithinhour", 
        	RiskAnalysisConstants.RISK_TYPE_DETENTION, riskForm.getCasefileID()));
            saveErrors(aRequest, errors); 
            forwardStr = "oneHourTestFailed";
        } else {	
        	SaveReferralAssessmentEvent saveEvt = (SaveReferralAssessmentEvent)
				EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVEREFERRALASSESSMENT);

        	if( StringUtils.equalsIgnoreCase( refForm.getIsNewReferral(), "true") )
        	{
        		saveEvt.setNewReferral(true);
        	}

        	if( StringUtils.isNotEmpty( refForm.getRiskMandatoryDetentionCd() ) )
        	{
        		saveEvt.setRiskMandatoryDetentionCd(refForm.getRiskMandatoryDetentionCd());
        	}
		
        	if( StringUtils.isNotEmpty( refForm.getMoreThanOneFailureString() ) )
        	{
        		if (refForm.getMoreThanOneFailureString().equals(UIConstants.YES_FULL_TEXT)) 
        		{
        			saveEvt.setMoreThanOneFailure(true);
        		} 
        		else 
        		{
        			saveEvt.setMoreThanOneFailure(false);
        		}
        	}

			saveEvt.setAssessmentDate(riskForm.getRiskAssessmentDate());
			saveEvt.setCasefileID(riskForm.getCasefileID());
			saveEvt.setJuvenileNum(riskForm.getJuvenileNum());
			//saveEvt.setJpoUserID(UIUtil.getCurrentUserID());
			saveEvt.setUpdate(false);
			saveEvt.setRiskFormulaId(riskForm.getRiskFormulaId());
			saveEvt.setAssessmentType(riskForm.getRiskAssessmentType());
	
			String pendingCourtVop = refForm.getPendingCourtVOP();
			String pendingCourt = refForm.getPendingCourt();
			String probationStatus = refForm.getCurrentlyOnProbation();
	
			pendingCourtVop = (StringUtils.equalsIgnoreCase( pendingCourtVop, "Yes")) ? "true" : "false" ;
			pendingCourt =    (StringUtils.equalsIgnoreCase( pendingCourt, "Yes")) ?  "true" : "false" ;
			probationStatus = (StringUtils.equalsIgnoreCase( probationStatus, "Yes")) ?  "true" : "false" ;
	
			saveEvt.setVopPendingCourt(pendingCourtVop);
			saveEvt.setPendingCourt(pendingCourt);
			saveEvt.setProbationStatus(probationStatus);
			saveEvt.setAdditionalCharges(Integer.parseInt(refForm.getNumberOfCharges()));

			Iterator<IEvent> ite = riskForm.getProcessedQuestionAnswers().iterator();
			while( ite.hasNext() )
			{
				saveEvt.addRequest( ite.next() );
			}
			
			SaveDelinquencyHistEvent histEvt = new SaveDelinquencyHistEvent();
			histEvt.setAgeFirstReferred(refForm.getAgeFirstReferred());
			histEvt.setSeriousnessIndex(refForm.getSeriousnessIndex());
			histEvt.setTotalCapitalFelony(refForm.getCapitalFelonyTotal());
			histEvt.setTotalClassAB(refForm.getMisdClassABTotal());
			histEvt.setTotalClassC(refForm.getMisdClassCTotal());
			histEvt.setTotalFelony1(refForm.getFelony1Total());
			histEvt.setTotalFelony2(refForm.getFelony2Total());
			histEvt.setTotalFelony3(refForm.getFelony3Total());
			histEvt.setTotalLevel(refForm.getLevelTotal());
			histEvt.setTotalOffenses(refForm.getOffensesTotal());
			histEvt.setTotalReferralsHistory(refForm.getReferralHistoryTotal());
			histEvt.setTotalStateJailFelony(refForm.getStateJailFelonyTotal());
			histEvt.setTotalStatusCO(refForm.getStatusCityOrdOffensesTotal());
	
			saveEvt.addRequest(histEvt);
		
//		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
//		dispatch.postEvent(saveEvt);
//
//		CompositeResponse response = (CompositeResponse)dispatch.getReply();
			CompositeResponse response = MessageUtil.postRequest(saveEvt);
//		MessageUtil.processReturnException(response);
//
	        List <RiskRecommendationResponseEvent> answers = MessageUtil.compositeToList( response, RiskRecommendationResponseEvent.class );
	        riskForm.setRecommendations(answers);
	        
	        //Get New Risk Analysis by going through first recommendation response only
	        Iterator<RiskRecommendationResponseEvent> iterRiskRecommendation = answers.iterator();
	        while(iterRiskRecommendation.hasNext()) 
			{
	        	RiskRecommendationResponseEvent riskRecommendationResponseEvent = iterRiskRecommendation.next();
	        	riskForm.setAssessmentId(riskRecommendationResponseEvent.getRiskAnalysisId());
	        	break;
			}
//			return aMapping.findForward( UIConstants.SUCCESS );
        }	
        
        if(refForm.getIsNewReferral().equalsIgnoreCase("false"))
        {
            riskForm.setIsPCSPrintable("true");
        }
        else
        {
            riskForm.setIsPCSPrintable("false");
        }
        	riskForm.setAction(UIConstants.CONFIRM);
       		return aMapping.findForward( forwardStr );
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 433C3D3D02F9
	 */
	public ActionForward updateReferralRiskAnalysisOverrideStatus(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		RiskAnalysisForm riskForm = (RiskAnalysisForm)aForm;
		SaveReferralAssessmentEvent saveEvt = (SaveReferralAssessmentEvent)
		EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVEREFERRALASSESSMENT);
		
		saveEvt.setAssessmentID(riskForm.getAssessmentId());
		saveEvt.setUpdateOverRiddenStatus(true);
		
		boolean recommendatationOverridden = riskForm.isRecommendationOverridden();
		saveEvt.setRecommendationOveridden(recommendatationOverridden);
		
		if (recommendatationOverridden) 
		{
			String overiddenReasonCd = riskForm.getOverRiddenReasonCd();
			String overiddenReasonFirstTwo = overiddenReasonCd.substring(0, 2);
			
			saveEvt.setOveriddenReasonCd(overiddenReasonCd);
			
			if( StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_RO)) 
			{
				saveEvt.setOveriddenReasonOther(riskForm.getOverRiddenReasonOther());
			} 
			else if (StringUtils.equals(overiddenReasonFirstTwo, RiskAnalysisConstants.OVERRIDE_TYPE_OTHER_FIRST_TWO_DO))
			{
				saveEvt.setOveriddenReasonOther(riskForm.getOverRiddenReasonDetentionOther());
			}
			else 
			{
				saveEvt.setOveriddenReasonOther( UIConstants.EMPTY_STRING );
			}
			
			 CreateActivityEvent reqEvent = (CreateActivityEvent)EventFactory.getInstance( JuvenileCasefileControllerServiceNames.CREATEACTIVITY ) ;
			 reqEvent.setSupervisionNumber(riskForm.getCasefileID());
			 reqEvent.setActivityDate( new Date() ) ;
			 reqEvent.setActivityTime( new SimpleDateFormat("yyyyMMdd hh:mm").format( new Date().getTime()) );
			
			 if ( "Detention Override".equals( riskForm.getOverRideType()) ) {
			     reqEvent.setActivityCodeId("DEO");
			     for (int i = 0; i < riskForm.getDetentionOverrideReasons().size(); i++ ) {
				     CodeResponseEvent response = (CodeResponseEvent)riskForm.getDetentionOverrideReasons().get(i) ;
				     if(riskForm.getOverRiddenReasonCd().equals(response.getCode())) {
					 reqEvent.setComments(response.getDescription());
				     }
				    
			     }
			 } else if ("Release Override".equals(riskForm.getOverRideType())) {
			     reqEvent.setActivityCodeId("ROR");
			     for (int i = 0; i < riskForm.getReleaseOverrideReasons().size(); i++ ) {
				     CodeResponseEvent response = (CodeResponseEvent)riskForm.getReleaseOverrideReasons().get(i) ;
				     if(riskForm.getOverRiddenReasonCd().equals(response.getCode())) {
					 reqEvent.setComments(response.getDescription());
				     }
				    
			     }
			 }
			 
			 if ( "RO04".equals( riskForm.getOverRiddenReasonCd() ) ) {
			     reqEvent.setComments(riskForm.getOverRiddenReasonDesc() + ". " + riskForm.getOverRiddenReasonOther());
			 } else if ( "DO03".equals(riskForm.getOverRiddenReasonCd()) ) {
			     reqEvent.setComments( riskForm.getOverRiddenReasonDesc() + ". " + riskForm.getOverRiddenReasonDetentionOther());
			 } 
			 MessageUtil.postRequest( reqEvent ) ;
			 
			
			
		} 
		else 
		{
			saveEvt.setOveriddenReasonCd( UIConstants.EMPTY_STRING );
			saveEvt.setOveriddenReasonOther( UIConstants.EMPTY_STRING );
		}
		
		IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(saveEvt);
				
		return aMapping.findForward( UIConstants.SUCCESS );
	}

	/*
	 * 
	 */
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return( aMapping.findForward(UIConstants.CANCEL) );
	}

	/*
	 * 
	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{		
		 //RiskAssessmentReferralForm refForm = (RiskAssessmentReferralForm) aForm;
	     RiskAnalysisForm riskAnalysisForm = (RiskAnalysisForm) aRequest.getSession().getAttribute("riskAnalysisForm");
		 
		 if (!riskAnalysisForm.getMode().equalsIgnoreCase("update")) 
		 {
			 riskAnalysisForm.setMode("back");
		 }
	     
		return( aMapping.findForward(UIConstants.BACK) );
	}
	
	public ActionForward generateBFOReport(ActionMapping aMapping, ActionForm aForm, 
		HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
        	//RiskAssessmentReferralForm refForm = (RiskAssessmentReferralForm) aForm;
        	RiskAnalysisForm riskAnalysisForm = (RiskAnalysisForm) aRequest.getSession().getAttribute("riskAnalysisForm");
        	  JuvenileCasefileForm casefileForm =
        	        	(JuvenileCasefileForm) aRequest.getSession().getAttribute("juvenileCasefileForm");
        	  JuvenileBriefingDetailsForm briefingForm = (JuvenileBriefingDetailsForm)aRequest.getSession().getAttribute("juvenileBriefingDetailsForm");
        	PreCourtStaffingPrintBean printBean = new PreCourtStaffingPrintBean();
        	printBean.setJuvenileName(casefileForm.getJuvenileFullName());
        	printBean.setJuvenileNumber(casefileForm.getJuvenileNum());
        	printBean.setRace(casefileForm.getRace());
        	printBean.setSex(casefileForm.getSex());
        	printBean.setAge(casefileForm.getCurrentAge());
        	printBean.setDob(casefileForm.getDob());
        	if(briefingForm!=null){
        	    if(briefingForm.getProfileDetail()!=null)
        	    printBean.setJpoOfRec(briefingForm.getProfileDetail().getJpoOfRecord());
        	}   
        	printBean.setEnteredBy(UIUtil.getCurrentUserName());
        	printBean.setCompletionDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
        	aRequest.getSession().setAttribute("reportInfo", printBean);
		BFOPdfManager pdfManager = BFOPdfManager.getInstance();
		//let the pdfManager know that the report should be saved in the request during report creation
		aRequest.setAttribute("isPdfSaveNeeded", "true");
		pdfManager.createPDFReport(aRequest, aResponse, PDFReport.PRE_COURT_STAFFING_REPORT);
		byte[] pdfDocument = (byte[]) aRequest.getAttribute("pdfSavedReport");
		if(pdfDocument!=null){
			// persist a copy of the BFO pdf document
		    				
			SaveInterviewDocumentEvent saveEvt = new SaveInterviewDocumentEvent();
			saveEvt.setCasefileId(riskAnalysisForm.getCasefileID());
			saveEvt.setDocument(pdfDocument);
			saveEvt.setDocumentTypeCodeId("PCS");
			// CODE_TABLE_NAME = INTERVIEW_DOCTYPE);

			CompositeResponse response = MessageUtil.postRequest(saveEvt);
			
			aRequest.removeAttribute("pdfSavedReport");
			aRequest.removeAttribute("isPdfSaveNeeded");
		}
		// remove the pdf report attributes from session when finished saving to database
		aRequest.removeAttribute("isPdfSaveNeeded");
        	return null;
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.update", "updateRiskAnalysis");
		keyMap.put("button.finish", "submit");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		keyMap.put("button.printPCS", "generateBFOReport");
		return keyMap;
	}
}
