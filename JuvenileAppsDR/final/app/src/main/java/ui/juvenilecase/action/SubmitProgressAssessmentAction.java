//Source file: C:\\views\\CommonSupervision\\app\\src\\ui\\juvenilecase\\action\\SubmitResidentialAssessmentAction.java

package ui.juvenilecase.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.ProgressPreConditionFailedResponseEvent;
import messaging.juvenilecase.reply.RiskRecommendationResponseEvent;
import messaging.riskanalysis.CheckProgressPreConditionsEvent;
import messaging.riskanalysis.SaveProgressAssessmentEvent;
import messaging.riskanalysis.SaveRiskAssessmentEvent;
import mojo.km.dispatch.EventManager;
import mojo.km.dispatch.IDispatch;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.RiskAnalysisConstants;
import naming.UIConstants;
import mojo.km.messaging.Composite.CompositeResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;

public class SubmitProgressAssessmentAction extends LookupDispatchAction
{
	/**
	 * @roseuid 4357DD670231
	 */
	public SubmitProgressAssessmentAction()
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

		boolean errorFound =this.checkForProgressWithinHour(aRequest, riskForm);
		if (errorFound){
			return aMapping.findForward( UIConstants.CANCEL ) ;
		}
		
		SaveRiskAssessmentEvent saveEvt = null;
		
		
		ActionForward forward = aMapping.findForward( UIConstants.SUCCESS ) ;
		
		saveEvt = (SaveRiskAssessmentEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVERISKASSESSMENT);
		
		saveEvt.setAssessmentDate(riskForm.getRiskAssessmentDate());
		saveEvt.setCasefileID(riskForm.getCasefileID());
		saveEvt.setJuvenileNum(riskForm.getJuvenileNum());
		if(riskForm.getRiskAssessmentType()!=null && riskForm.getRiskAssessmentType().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_RES_PROGRESS))
			saveEvt.setAssessmentType(RiskAnalysisConstants.RISK_TYPE_RES_PROGRESS);
		else
			saveEvt.setAssessmentType(RiskAnalysisConstants.RISK_TYPE_PROGRESS);
		saveEvt.setUpdate(false);
		saveEvt.setRiskFormulaId(riskForm.getRiskFormulaId());
		
		Iterator ite = riskForm.getProcessedQuestionAnswers().iterator();
		while (ite.hasNext())
		{
			saveEvt.addRequest((IEvent) ite.next());
		}
		
		List <RiskRecommendationResponseEvent> answers = MessageUtil.postRequestListFilter(saveEvt, RiskRecommendationResponseEvent.class);
		riskForm.setRecommendations(answers);
		    
		//Get New Risk Analysis by going through first recommendation response only
		if (answers.size() > 0){
			RiskRecommendationResponseEvent riskRecommendationResponseEvent = answers.get(0);
			riskForm.setAssessmentId(riskRecommendationResponseEvent.getRiskAnalysisId());
		}

		saveEvt = null;
		ite = null;
		answers = null;
		//riskForm.setRiskAssessmentType("");
		return( forward );
	}
	
	/**
	 * @param aMapping
	 * @param aForm
	 * @param aRequest
	 * @param aResponse
	 * @return ActionForward
	 * @roseuid 433C3D3D02F9
	 */
	public ActionForward updateProgressRiskAnalysisOverrideStatus(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		RiskAnalysisForm riskForm = (RiskAnalysisForm)aForm;
		
		SaveProgressAssessmentEvent saveEvt = (SaveProgressAssessmentEvent)
		EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVEPROGRESSASSESSMENT);
		
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
	
	private boolean checkForProgressWithinHour(HttpServletRequest aRequest,RiskAnalysisForm riskForm){
		
		boolean errorFound = false;
		
		CheckProgressPreConditionsEvent event = (CheckProgressPreConditionsEvent)
		EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.CHECKPROGRESSPRECONDITIONS);

		event.setCaseFileId(riskForm.getCasefileID());
		event.setJuvenileNumber(riskForm.getJuvenileNum());
		event.setAssessmentDate(riskForm.getRiskAssessmentDate());
		if(riskForm.getRiskAssessmentType()!=null && riskForm.getRiskAssessmentType().equalsIgnoreCase(RiskAnalysisConstants.RISK_TYPE_RES_PROGRESS))
			event.setFormula(RiskAnalysisConstants.RISK_TYPE_RES_PROGRESS);
		CompositeResponse response = MessageUtil.postRequest(event);
		
		ProgressPreConditionFailedResponseEvent errorEvt = (ProgressPreConditionFailedResponseEvent)
				MessageUtil.filterComposite(response, ProgressPreConditionFailedResponseEvent.class);
		
		if( errorEvt != null )
		{	// pre conditions not met
			ActionErrors errors = new ActionErrors();
			ActionMessage newErr = null;
			if( errorEvt.getMessage().equalsIgnoreCase(RiskAnalysisConstants.SAME_HOUR_SAME_CASEFILE)){
					newErr = new ActionMessage( "error.assessmenttypecannotbedonewithinhour", 
						riskForm.getRiskAssessmentType(), riskForm.getCasefileID() );
					errors.add(ActionErrors.GLOBAL_MESSAGE, newErr);
					saveErrors(aRequest, errors);
					errorFound = true;
			}
			newErr = null;
		}
		
		errorEvt = null;
		event = null;
		response = null;
		
		return errorFound;
	}
}
