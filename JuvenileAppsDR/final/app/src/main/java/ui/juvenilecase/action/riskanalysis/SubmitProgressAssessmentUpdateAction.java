package ui.juvenilecase.action.riskanalysis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.RiskRecommendationResponseEvent;
import messaging.riskanalysis.SaveRiskAssessmentEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.IEvent;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;

public class SubmitProgressAssessmentUpdateAction extends LookupDispatchAction {

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
		RiskAnalysisForm riskAnalysisForm = (RiskAnalysisForm) aRequest.getSession().getAttribute("riskAnalysisForm");
		
		 if (!riskAnalysisForm.getMode().equalsIgnoreCase("update")) 
		 {
			 riskAnalysisForm.setMode("back");
		 }
	     
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
	
	public ActionForward submit(ActionMapping aMapping, ActionForm aForm, 
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		RiskAnalysisForm riskForm = (RiskAnalysisForm) aForm;
		 
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

		saveEvt.setAssessmentDate(riskForm.getRiskAssessmentDate());
		saveEvt.setCasefileID(riskForm.getCasefileID());
		saveEvt.setJuvenileNum(riskForm.getJuvenileNum());

		Iterator <IEvent> ite2 = riskForm.getProcessedQuestionAnswers().iterator();
		while( ite2.hasNext() )
		{
			saveEvt.addRequest( ite2.next() );
		}
		
	    CompositeResponse response = MessageUtil.postRequest(saveEvt);
	    MessageUtil.processReturnException(response);
	        
        List <RiskRecommendationResponseEvent> answers = MessageUtil.compositeToList( response, RiskRecommendationResponseEvent.class );
        riskForm.setRecommendations(answers);
 		
        return aMapping.findForward("success");
	}
}
