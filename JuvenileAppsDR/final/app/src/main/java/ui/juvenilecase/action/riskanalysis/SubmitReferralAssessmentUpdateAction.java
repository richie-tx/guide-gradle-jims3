//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\action\\SubmitReferralAssessmentAction.java

package ui.juvenilecase.action.riskanalysis;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.interviewinfo.SaveInterviewDocumentEvent;
import messaging.juvenilecase.reply.RiskRecommendationResponseEvent;
import messaging.riskanalysis.SaveReferralAssessmentEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.DateUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.common.UIUtil;
import ui.juvenilecase.casefile.PreCourtStaffingPrintBean;
import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;
import ui.juvenilecase.form.riskanalysis.RiskAssessmentReferralForm;
import ui.util.BFOPdfManager;
import ui.util.PDFReport;

public class SubmitReferralAssessmentUpdateAction extends LookupDispatchAction
{
	/**
	 * @roseuid 433D8A34008D
	 */
	public SubmitReferralAssessmentUpdateAction()
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
    	RiskAssessmentReferralForm refForm = (RiskAssessmentReferralForm) aRequest.getSession().getAttribute("riskReferralForm");
    	RiskAnalysisForm riskForm = (RiskAnalysisForm) aForm;
		 
		SaveReferralAssessmentEvent saveEvt = (SaveReferralAssessmentEvent)
				EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVEREFERRALASSESSMENT);
		
		saveEvt.setUpdate(true);
		saveEvt.setAssessmentID(riskForm.getAssessmentId());
		saveEvt.setRiskAnalysisId(riskForm.getAssessmentId());
		saveEvt.setRiskFormulaId(riskForm.getRiskFormulaId());
		saveEvt.setAssessmentType(riskForm.getRiskAssessmentType());

		if( notNullEqualsString( refForm.getIsNewReferral(), "true") )
		{
			saveEvt.setNewReferral(true);
		}

		if( (refForm.getRiskMandatoryDetentionCd() != null) && 
				(refForm.getRiskMandatoryDetentionCd().length() > 0) )
		{
			saveEvt.setRiskMandatoryDetentionCd(refForm.getRiskMandatoryDetentionCd());
		}
		
		if( (refForm.getMoreThanOneFailureString() != null) && 
				(refForm.getMoreThanOneFailureString().length() > 0) )
		{
			if (refForm.getMoreThanOneFailureString().equals(UIConstants.YES_FULL_TEXT)) {
				saveEvt.setMoreThanOneFailure(true);
			} else {
				saveEvt.setMoreThanOneFailure(false);
			}
		}
		
		if ((riskForm.getModReason() != null) && 
				(riskForm.getModReason().length() > 0)) 
		{
			saveEvt.setModReason(riskForm.getModReason());
			//saveEvt.setFilteredModReason(riskForm.getFilteredModReason());
		}
		

		saveEvt.setAssessmentDate(new Date());
		saveEvt.setCasefileID(riskForm.getCasefileID());
		saveEvt.setJuvenileNum(riskForm.getJuvenileNum());
		//saveEvt.setJpoUserID(UIUtil.getCurrentUserID());

		String pendingCourtVop = refForm.getPendingCourtVOP();
		String pendingCourt = refForm.getPendingCourt();
		String probationStatus = refForm.getCurrentlyOnProbation();

		pendingCourtVop = (notNullEqualsString(pendingCourtVop, "Yes")) ? "true" : "false" ;
		pendingCourt =    (notNullEqualsString( pendingCourt, "Yes")) ?  "true" : "false" ;
		probationStatus = (notNullEqualsString( probationStatus, "Yes")) ?  "true" : "false" ;

		saveEvt.setVopPendingCourt(pendingCourtVop);
		saveEvt.setPendingCourt(pendingCourt);
		saveEvt.setProbationStatus(probationStatus);
		if (refForm.getNumberOfCharges() != null
				&& !refForm.getNumberOfCharges().equals(UIConstants.EMPTY_STRING)){
			saveEvt.setAdditionalCharges(Integer.parseInt(refForm.getNumberOfCharges()));
		}

		Iterator <IEvent> ite = riskForm.getProcessedQuestionAnswers().iterator();
		while( ite.hasNext() )
		{
			saveEvt.addRequest( ite.next() );
		}
		
		/* IDispatch dispatch = EventManager.getSharedInstance(EventManager.REQUEST);
		dispatch.postEvent(saveEvt);

		CompositeResponse composite = (CompositeResponse)dispatch.getReply();
		MessageUtil.processReturnException(composite);
		RiskRecommendationResponseEvent response = (RiskRecommendationResponseEvent)
				MessageUtil.filterComposite(composite, RiskRecommendationResponseEvent.class);

		refForm.setRecommendationScore(Integer.toString(response.getRiskAnalysisScore()));
		refForm.setRecommendation(response.getRiskAnalysisRecommendation());
		refForm.setRiskAnalysisId(response.getRiskAnalysisId());
		*/
		
	    CompositeResponse response = MessageUtil.postRequest(saveEvt);
	    List <RiskRecommendationResponseEvent> answers = MessageUtil.compositeToList( response, RiskRecommendationResponseEvent.class );
	    riskForm.setRecommendations(answers);
	        
	    //Get New Risk Analysis by going through first recommendation response only
	    Iterator <RiskRecommendationResponseEvent> iterRiskRecommendation = answers.iterator();
	    while(iterRiskRecommendation.hasNext()) 
		{
	       	RiskRecommendationResponseEvent riskRecommendationResponseEvent = iterRiskRecommendation.next();
	       	riskForm.setAssessmentId(riskRecommendationResponseEvent.getRiskAnalysisId());
	       	break;
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
	return aMapping.findForward("success");
	}
	
	/*
	 * given two strings, return true if the first String
	 * is not null and is equal to the second String
	 */
	private boolean notNullEqualsString( String str, String chk )
	{
		return( str != null && str.equalsIgnoreCase(chk) ) ;
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
        	if(briefingForm!=null)
        	    printBean.setJpoOfRec(briefingForm.getProfileDetail().getJpoOfRecord());
        	printBean.setEnteredBy(UIUtil.getCurrentUserName());
        	printBean.setCompletionDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));
        	printBean.setAction("updatePCSRisk");
        	if ((riskAnalysisForm.getModReason() != null) && 
			(riskAnalysisForm.getModReason().length() > 0)) 
        	{
        	    //String result = riskAnalysisForm.getModReason().split("\\[")[0];
        	    String result = riskAnalysisForm.getModReason();
        	    String filteredResult = riskAnalysisForm.getFilteredModReason();
        	    printBean.setModReason(result);
        	    printBean.setFilteredModReason( filteredResult );
        	    
        	}
        	else
        	    printBean.setModReason("NONE IDENTIFIED");
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
