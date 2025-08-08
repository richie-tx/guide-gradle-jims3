package ui.juvenilecase.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

import ui.juvenilecase.UIRiskAnalysisHelper;
import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;

public class DisplayTestingSummaryAction extends LookupDispatchAction 
{
   
   /**
    * @roseuid 4357DD4903BE
    */
   public DisplayTestingSummaryAction() 
   {
    
   }
   
   /**
	* @see LookupDispatchAction#getKeyMethodMap()
	* @return Map
	*/
   protected Map getKeyMethodMap()
   {
	   Map buttonMap = new HashMap();
	   buttonMap.put("button.next", "testingSummaryDetails");
	   buttonMap.put("button.nextQuestion", "nextQuestion");
	   buttonMap.put("button.back", "back");
	   return buttonMap;
   }
   
   
   /**
    * @param aMapping
    * @param aForm
    * @param aRequest
    * @param aResponse
    * @return ActionForward
    * @roseuid 4357D9AF0392
    */
   public ActionForward testingSummaryDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
	   RiskAnalysisForm riskForm = (RiskAnalysisForm) aForm;
       
	   String modReason = aRequest.getParameter("modReason");
       riskForm.setModReason(modReason);
	   
	   List qAns = riskForm.getQuestionAnswers(); 
		
		 // This list is for processing in DB - Seperated by WeightReponseId
        List questionRequestEvents = UIRiskAnalysisHelper.processIndividualQuestionAnswers(qAns);
        Collections.sort((ArrayList) questionRequestEvents);
        
        // This list if a view only for JSP - Consolidated by QuestionNumber
        List viewOnlyRequestEvents = UIRiskAnalysisHelper.viewOnlyMultiQuestionAnswers(questionRequestEvents);
        Collections.sort((ArrayList) viewOnlyRequestEvents);
        
        riskForm.setProcessedQuestionAnswers(questionRequestEvents); 
        riskForm.setProcessedViewQuestionAnswers(viewOnlyRequestEvents); 
		   		
		return aMapping.findForward("success");
   }
   
   /**
	* @param aMapping
	* @param aForm
	* @param aRequest
	* @param aResponse
	* @return ActionForward
	* @roseuid 4357D9AF0392
	*/
   public ActionForward nextQuestion(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest, HttpServletResponse aResponse) 
   {
   	//Need to implement the wizard like feature in the Testing Assessment
		//RiskAnalysisForm riskForm = (RiskAnalysisForm)aForm;

		//List traitDetailsList = (ArrayList) riskForm.getProcessedQuestionAnswers();

		
		//refForm.setDisplayJuvTraitsDetails(diplayJuvTraitsDetails);
				   		
		return aMapping.findForward("viewJuvTraitsDetails");
   }
   
   public ActionForward back(ActionMapping aMapping, ActionForm aForm, 
   HttpServletRequest aRequest, HttpServletResponse aResponse)
		   {
			   return aMapping.findForward(UIConstants.BACK);
   }

}
