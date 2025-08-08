//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\action\\SubmitReferralAssessmentAction.java

package ui.juvenilecase.action.riskanalysis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import messaging.casefile.UpdateJuvenileCasefileEvent;
import messaging.juvenilecase.reply.RiskQuestionResponseEvent;
import messaging.juvenilecase.reply.RiskRecommendationResponseEvent;
import messaging.juvenilecase.reply.RiskSuggestedCasePlanDomainResponseEvent;
import messaging.riskanalysis.SaveRiskAssessmentEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileCasefileControllerServiceNames;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.ujac.util.BeanComparator;

import ui.juvenilecase.casefile.form.JuvenileBriefingDetailsForm;
import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;
import ui.juvenilecase.form.riskanalysis.RiskAssessmentCourtReferralForm;

public class SubmitCourtReferralAssessmentUpdateAction extends LookupDispatchAction
{
	private static final String JUV_CASEFILE = "juvenileCasefileForm" ; //added by srtitwisha for changing the flag for referral assessment needed
	/**
	 * @roseuid 433D8A34008D
	 */
	public SubmitCourtReferralAssessmentUpdateAction()
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
		//RiskAssessmentCourtReferralForm refForm = (RiskAssessmentCourtReferralForm)aForm;
		//RiskAnalysisForm riskForm = (RiskAnalysisForm) aRequest.getSession().getAttribute("riskAnalysisForm");
		RiskAnalysisForm riskForm = (RiskAnalysisForm) aForm;
		RiskAssessmentCourtReferralForm refForm = (RiskAssessmentCourtReferralForm)aRequest.getSession().getAttribute( "riskCourtReferralForm" );
				
/*		SaveCourtReferralAssessmentEvent saveEvt = (SaveCourtReferralAssessmentEvent)
				EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVECOURTREFERRALASSESSMENT);
		
		saveEvt.setUpdate(true);
		saveEvt.setAssessmentType(refForm.getAssessmentType());
		saveEvt.setAssessmentID(riskForm.getAssessmentId());
		saveEvt.setRiskAnalysisId(riskForm.getAssessmentId());
*/		
		SaveRiskAssessmentEvent saveEvt = (SaveRiskAssessmentEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVERISKASSESSMENT);
		
		saveEvt.setUpdate(true);
		saveEvt.setAssessmentID(riskForm.getAssessmentId());
		saveEvt.setRiskAnalysisId(riskForm.getAssessmentId());
		saveEvt.setRiskFormulaId(riskForm.getRiskFormulaId());
		saveEvt.setAssessmentType(riskForm.getRiskAssessmentType());
		
		if ((riskForm.getModReason() != null) && 
				(riskForm.getModReason().length() > 0)) 
		{
			saveEvt.setModReason(riskForm.getModReason());
		}
		//setting the supervision number for TJJD Risk by Srutitwisha
		if((riskForm.getCasefileID() != null) && (riskForm.getCasefileID().length() > 0))
		{
			saveEvt.setCasefileID(riskForm.getCasefileID());
		}
		
		// to change the referralAssessmentNeededFlag to no if previously yes when the TJJD risk is transferred to another casefile	
		
		UpdateJuvenileCasefileEvent updateEvent = (UpdateJuvenileCasefileEvent)
		EventFactory.getInstance( JuvenileCasefileControllerServiceNames.UPDATEJUVENILECASEFILE );
		
		HttpSession session= aRequest.getSession();
		JuvenileCasefileForm caseFileForm = (JuvenileCasefileForm)
		session.getAttribute(JUV_CASEFILE);
		if(caseFileForm != null){
			boolean referralAssessmentNeededFlag = caseFileForm.getIsReferralAssessmentNeeded();
			if(referralAssessmentNeededFlag = true){
				referralAssessmentNeededFlag = false;
			}
			updateEvent.setReferralRiskNeeded(referralAssessmentNeededFlag);
			updateEvent.setSupervisionNumber(saveEvt.getCasefileID());
			IDispatch dispatch = EventManager.getSharedInstance( EventManager.REQUEST );
			dispatch.postEvent( updateEvent );
			
		}

		saveEvt.setAssessmentDate(riskForm.getRiskAssessmentDate());
		//saveEvt.setCasefileID(riskForm.getCasefileID());
		saveEvt.setJuvenileNum(riskForm.getJuvenileNum());
		//saveEvt.setJpoUserID(UIUtil.getCurrentUserID());

		Iterator<IEvent> ite = riskForm.getProcessedQuestionAnswers().iterator();
		while( ite.hasNext() )
		{
			saveEvt.addRequest( ite.next() );
		}

		CompositeResponse response = MessageUtil.postRequest(saveEvt);
		
        List <RiskRecommendationResponseEvent> answers = MessageUtil.compositeToList( response, RiskRecommendationResponseEvent.class );
        //riskForm.setRecommendations(answers);
		if (answers != null && answers.size() > 1){
			List sortedList = new ArrayList(answers);
			ArrayList sortFields = new ArrayList();
			sortFields.add(new ComparatorChain(new BeanComparator("resultGroupDisplayDesc")));
			ComparatorChain multiSort = new ComparatorChain(sortFields);
			Collections.sort(sortedList, multiSort);
			riskForm.setRecommendations(sortedList);
		} else {
			riskForm.setRecommendations(answers);
		}
       
        RiskSuggestedCasePlanDomainResponseEvent riskSuggestedCasePlanDomainResponseEvent 
    		= (RiskSuggestedCasePlanDomainResponseEvent) MessageUtil.filterComposite(response, RiskSuggestedCasePlanDomainResponseEvent.class);
    
	    if (riskSuggestedCasePlanDomainResponseEvent != null) {
	    	refForm.setSuggestedCasePlanDomains(riskSuggestedCasePlanDomainResponseEvent.getSuggestedCasePlanDomainNames());
	    }
		
		return aMapping.findForward("success");
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
		//RiskAssessmentCourtReferralForm refForm = (RiskAssessmentCourtReferralForm) aForm;
		RiskAnalysisForm refForm = (RiskAnalysisForm) aForm;
		
		 List myList = new ArrayList(); //Master List of Questions & Answers
		 Iterator<RiskQuestionResponseEvent> ite = refForm.getQuestionAnswers().iterator();
		 while( ite.hasNext() )
		 {
			 
			 RiskQuestionResponseEvent question = ite.next();
			 question.setSelectedAnswerID(null);
			 question.setSelectedAnswerIDs(null);
			 question.setSelectedAnswerWeight(null);
			 question.setSelectedChronicID(null);
			 question.setSelectedChronicIDs(null); 
			  
			 myList.add(question);
			 
		 }	     
	     refForm.setQuestionAnswers(myList);
	     
		return( aMapping.findForward(UIConstants.BACK) );
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
		
		return keyMap;
	}
}
