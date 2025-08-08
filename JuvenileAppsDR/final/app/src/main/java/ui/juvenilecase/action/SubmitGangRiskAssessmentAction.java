//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\action\\SubmitResidentialAssessmentAction.java

package ui.juvenilecase.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.CheckRiskAnalysisOneHourResponseEvent;
import messaging.juvenilecase.reply.RiskRecommendationResponseEvent;
import messaging.riskanalysis.CheckRiskAnalysisOneHourEvent;
import messaging.riskanalysis.SaveReferralAssessmentEvent;
import messaging.riskanalysis.SaveRiskAssessmentEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.RiskAnalysisConstants;
import naming.UIConstants;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;

public class SubmitGangRiskAssessmentAction extends LookupDispatchAction
{
	/**
	 * @roseuid 4357DD670231
	 */
	public SubmitGangRiskAssessmentAction()
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
	public ActionForward finish(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		RiskAnalysisForm riskForm = (RiskAnalysisForm) aForm;
		String forwardStr = UIConstants.SUCCESS;
		
		CheckRiskAnalysisOneHourEvent event =
        	(CheckRiskAnalysisOneHourEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.CHECKRISKANALYSISONEHOUR);
        event.setCasefileID(riskForm.getCasefileID());
        if(riskForm.getRiskAssessmentType()!=null && riskForm.getRiskAssessmentType().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_MH_SCREEN)){
        	event.setAssessmentType(RiskAnalysisConstants.RISK_TYPE_MH_SCREEN);
		}else{
			event.setAssessmentType(RiskAnalysisConstants.RISK_TYPE_GANG);
		}
        CheckRiskAnalysisOneHourResponseEvent resp = (CheckRiskAnalysisOneHourResponseEvent) MessageUtil.postRequest(event,CheckRiskAnalysisOneHourResponseEvent.class);

        //If one hour check fails, forward back to summary page
        if (resp != null && resp.isMessage() == true) {
        	ActionErrors errors = new ActionErrors();
        	 if(riskForm.getRiskAssessmentType()!=null && riskForm.getRiskAssessmentType().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_MH_SCREEN)){
        		 errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.assessmenttypecannotbedonewithinhour", 
        	       			RiskAnalysisConstants.RISK_TYPE_MH_SCREEN, riskForm.getCasefileID()));
     		}else{
     			 errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.assessmenttypecannotbedonewithinhour", 
     	       			RiskAnalysisConstants.RISK_TYPE_GANG, riskForm.getCasefileID()));
     		}
            saveErrors(aRequest, errors); 
            forwardStr = "oneHourTestFailed";
        } else {   
		
			SaveRiskAssessmentEvent saveEvt = null;
			saveEvt = (SaveRiskAssessmentEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVERISKASSESSMENT);
			saveEvt.setAssessmentDate(riskForm.getRiskAssessmentDate());
			saveEvt.setCasefileID(riskForm.getCasefileID());
			saveEvt.setJuvenileNum(riskForm.getJuvenileNum());
			saveEvt.setRiskFormulaId(riskForm.getRiskFormulaId());
			if(riskForm.getRiskAssessmentType()!=null && riskForm.getRiskAssessmentType().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_MH_SCREEN)){
				saveEvt.setAssessmentType(RiskAnalysisConstants.RISK_TYPE_MH_SCREEN);
			}else{
				if(riskForm.getRiskAssessmentType()!=null && riskForm.getRiskAssessmentType().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_GANG)){
					saveEvt.setAssessmentType(RiskAnalysisConstants.RISK_TYPE_GANG);
				}
			}
			Iterator quesAnsItr = riskForm.getProcessedQuestionAnswers().iterator();
			while (quesAnsItr.hasNext())
			{
				saveEvt.addRequest((IEvent) quesAnsItr.next());
			}
			saveEvt.setUpdate(false);
			
			List <RiskRecommendationResponseEvent> answers = MessageUtil.postRequestListFilter(saveEvt, RiskRecommendationResponseEvent.class);
			riskForm.setRecommendations(answers);
			    
			//Get New Risk Analysis by going through first recommendation response only
			if (answers.size() > 0){
				RiskRecommendationResponseEvent riskRecommendationResponseEvent = answers.get(0);
				riskForm.setAssessmentId(riskRecommendationResponseEvent.getRiskAnalysisId());
			}
			
			saveEvt = null;
			quesAnsItr = null;
			answers = null;
			//riskForm.setRiskAssessmentType("");
        }
		return aMapping.findForward(forwardStr);
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 433C3D3D02F9
	 */
	public ActionForward updateGangRiskAnalysisOverrideStatus(ActionMapping aMapping, ActionForm aForm, 
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
	public ActionForward cancel(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return( aMapping.findForward(UIConstants.CANCEL) );
	}

	/*
   	 * 
   	 */
	public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return( aMapping.findForward(UIConstants.BACK) );
	}

	/*
	 * (non-Javadoc)
	 * @see org.apache.struts.actions.LookupDispatchAction#getKeyMethodMap()
	 */
	protected Map getKeyMethodMap()
	{
		Map keyMap = new HashMap();
		keyMap.put("button.finish", "finish");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.back", "back");
		return keyMap;
	}
	
	
}
