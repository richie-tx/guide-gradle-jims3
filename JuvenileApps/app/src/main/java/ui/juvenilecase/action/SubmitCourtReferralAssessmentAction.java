package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.CheckRiskAnalysisOneHourResponseEvent;
import messaging.juvenilecase.reply.RiskRecommendationResponseEvent;
import messaging.juvenilecase.reply.RiskSuggestedCasePlanDomainResponseEvent;
import messaging.riskanalysis.CheckRiskAnalysisOneHourEvent;
import messaging.riskanalysis.SaveRiskAssessmentEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.RiskAnalysisConstants;
import naming.UIConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.ujac.util.BeanComparator;

import ui.juvenilecase.form.JuvenileCasefileForm;
import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;
import ui.juvenilecase.form.riskanalysis.RiskAssessmentCourtReferralForm;

public class SubmitCourtReferralAssessmentAction extends LookupDispatchAction
{
    protected Map getKeyMethodMap()
    {
        Map keyMap = new HashMap();
        keyMap.put("button.finish", "finish");
        keyMap.put("button.cancel", "cancel");
        keyMap.put("button.back", "back");
        return keyMap;
    }

    /**
     * @roseuid 4357DD670231
     */
    public SubmitCourtReferralAssessmentAction()
    {

    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 4357D9B000AD
     */
    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	
    	//RiskAssessmentCourtReferralForm refForm = (RiskAssessmentCourtReferralForm) aForm;
		//RiskAnalysisForm riskForm = (RiskAnalysisForm) aRequest.getSession().getAttribute("riskAnalysisForm");
    	RiskAnalysisForm riskForm = (RiskAnalysisForm) aForm;
    	RiskAssessmentCourtReferralForm refForm = (RiskAssessmentCourtReferralForm)aRequest.getSession().getAttribute("riskCourtReferralForm");
 /*   	SaveCourtReferralAssessmentEvent saveEvt = (SaveCourtReferralAssessmentEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVECOURTREFERRALASSESSMENT);
        
    	saveEvt.setAssessmentType(refForm.getAssessmentType());
    	saveEvt.setCasefileID(refForm.getCasefileID());
        saveEvt.setJuvenileNum(refForm.getJuvenileNum());
        saveEvt.setJpoUserID(UIUtil.getCurrentUserID());
        saveEvt.setAssessmentDate(riskForm.getRiskAssessmentDate());
        saveEvt.setUpdate(false);

*/  
    	String forwardStr = UIConstants.SUCCESS;
    	String assessmentType = RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_MALE;
    	JuvenileCasefileForm jhForm = (JuvenileCasefileForm)aRequest.getSession().getAttribute("juvenileCasefileForm");    	
    	if (jhForm.getSexId().equals("F")) {
    		assessmentType = RiskAnalysisConstants.RISK_TYPE_COURT_REFERRAL_FEMALE;
    	}
    	CheckRiskAnalysisOneHourEvent event =
        	(CheckRiskAnalysisOneHourEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.CHECKRISKANALYSISONEHOUR);
        event.setCasefileID(riskForm.getCasefileID());
        event.setAssessmentType(assessmentType);
        CheckRiskAnalysisOneHourResponseEvent resp = (CheckRiskAnalysisOneHourResponseEvent) MessageUtil.postRequest(event,CheckRiskAnalysisOneHourResponseEvent.class);

        //If one hour check fails, forward back to summary page
        if (resp != null && resp.isMessage() == true) {
        	ActionErrors errors = new ActionErrors();
        	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.assessmenttypecannotbedonewithinhour", 
        		assessmentType, riskForm.getCasefileID()));
            saveErrors(aRequest, errors); 
            forwardStr = "oneHourTestFailed";
        } else {	
	    	SaveRiskAssessmentEvent saveEvt = (SaveRiskAssessmentEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVERISKASSESSMENT);
	        
			saveEvt.setAssessmentDate(riskForm.getRiskAssessmentDate());
			saveEvt.setCasefileID(riskForm.getCasefileID());
			saveEvt.setJuvenileNum(riskForm.getJuvenileNum());
			saveEvt.setAssessmentType(assessmentType);
			saveEvt.setUpdate(false);
			saveEvt.setRiskFormulaId(riskForm.getRiskFormulaId());
	    	
	    	Iterator ite = riskForm.getProcessedQuestionAnswers().iterator();
	        while (ite.hasNext())
	        {
	            saveEvt.addRequest((IEvent) ite.next());
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
	
	        //Get New Risk Analysis by going through first recommendation response only
	        Iterator<RiskRecommendationResponseEvent> iterRiskRecommendation = answers.iterator();
	        while(iterRiskRecommendation.hasNext()) 
			{
	        	RiskRecommendationResponseEvent riskRecommendationResponseEvent = iterRiskRecommendation.next();
	        	riskForm.setAssessmentId(riskRecommendationResponseEvent.getRiskAnalysisId());
	        	break;
			}
	        
	        RiskSuggestedCasePlanDomainResponseEvent riskSuggestedCasePlanDomainResponseEvent 
	        	= (RiskSuggestedCasePlanDomainResponseEvent) MessageUtil.filterComposite(response, RiskSuggestedCasePlanDomainResponseEvent.class);
	        
	        if (riskSuggestedCasePlanDomainResponseEvent != null) {
	        	refForm.setSuggestedCasePlanDomains(riskSuggestedCasePlanDomainResponseEvent.getSuggestedCasePlanDomainNames());
	        }
        }	        
        return aMapping.findForward(forwardStr);
    }

    public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.CANCEL);
        return forward;
    }

    public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        ActionForward forward = aMapping.findForward(UIConstants.BACK);
        return forward;
    }
}
