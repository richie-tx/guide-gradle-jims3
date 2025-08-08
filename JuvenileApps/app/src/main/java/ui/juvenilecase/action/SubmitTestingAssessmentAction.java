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
import messaging.riskanalysis.SaveRiskAssessmentEvent;
import messaging.riskanalysis.SaveTestingAssessmentEvent;
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

public class SubmitTestingAssessmentAction extends LookupDispatchAction
{

    /**
     * @roseuid 433D8A34008D
     */
    public SubmitTestingAssessmentAction()
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
    public ActionForward finish(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
 
    	RiskAnalysisForm riskForm = (RiskAnalysisForm) aForm;
 
    	String forwardStr = UIConstants.SUCCESS;
    	CheckRiskAnalysisOneHourEvent event =
        	(CheckRiskAnalysisOneHourEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.CHECKRISKANALYSISONEHOUR);
        event.setCasefileID(riskForm.getCasefileID());
        event.setAssessmentType(RiskAnalysisConstants.RISK_TYPE_TESTING);
        CheckRiskAnalysisOneHourResponseEvent resp = (CheckRiskAnalysisOneHourResponseEvent) MessageUtil.postRequest(event,CheckRiskAnalysisOneHourResponseEvent.class);

        //If one hour check fails, forward back to summary page
        if (resp != null && resp.isMessage() == true) {
        	ActionErrors errors = new ActionErrors();
        	errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage("error.assessmenttypecannotbedonewithinhour", 
        			RiskAnalysisConstants.RISK_TYPE_TESTING, riskForm.getCasefileID()));
            saveErrors(aRequest, errors); 
            forwardStr = "oneHourTestFailed";
        } else {    	
	    	SaveRiskAssessmentEvent saveEvt = (SaveRiskAssessmentEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVERISKASSESSMENT);
			saveEvt.setAssessmentDate(riskForm.getRiskAssessmentDate());
			saveEvt.setCasefileID(riskForm.getCasefileID());
			saveEvt.setJuvenileNum(riskForm.getJuvenileNum());
			saveEvt.setAssessmentType(RiskAnalysisConstants.RISK_TYPE_TESTING);
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
	        Iterator <RiskRecommendationResponseEvent> iterRiskRecommendation = answers.iterator();
	        while(iterRiskRecommendation.hasNext()) 
			{
	        	RiskRecommendationResponseEvent riskRecommendationResponseEvent = iterRiskRecommendation.next();
	        	riskForm.setAssessmentId(riskRecommendationResponseEvent.getRiskAnalysisId());
	        	break;
			}
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
	public ActionForward updateTestingRiskAnalysisOverrideStatus(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		RiskAnalysisForm riskForm = (RiskAnalysisForm)aForm;
		
		SaveTestingAssessmentEvent saveEvt = (SaveTestingAssessmentEvent)
		EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVETESTINGASSESSMENT);
		
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

    /*
     * (non-Javadoc)
     * 
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
