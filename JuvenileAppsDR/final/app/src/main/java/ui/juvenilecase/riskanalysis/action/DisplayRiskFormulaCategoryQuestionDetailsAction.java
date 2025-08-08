package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.riskanalysis.GetAnswersEvent;
import messaging.riskanalysis.GetQuestionsEvent;
import messaging.riskanalysis.reply.GetAnswerResponseEvent;
import messaging.riskanalysis.reply.GetQuestionResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.riskanalysis.form.RiskFormulaDetailsForm;

public class DisplayRiskFormulaCategoryQuestionDetailsAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.view", "viewQuestion");
		keyMap.put("button.back", "back");
	}
	
	   public ActionForward viewQuestion(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	            HttpServletResponse aResponse)
	    {
	    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
	    	String questionId = aRequest.getParameter("questionId");
	    	String subQuestion = aRequest.getParameter("subordinateQuestion");
	    	rfDetailsForm.setQuestionIsSubordinate(subQuestion);  //for back button flow
// may need to add code to find q/a info using file retrieval	
			GetQuestionsEvent questEvent =
				(GetQuestionsEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
//			questEvent.setReturnQuestionsNotAttachedToCategory(false);
			questEvent.setReturnSingleQuestionBasedOnId(true);
			questEvent.setQuestionId(questionId);
	    	CompositeResponse questResponse = MessageUtil.postRequest(questEvent);
	    	
	    	List wrkList1 = MessageUtil.compositeToList( questResponse, GetQuestionResponseEvent.class );
	    	if (wrkList1 != null && wrkList1.size() > 0)
	    	{	
	        	GetQuestionResponseEvent gre = (GetQuestionResponseEvent) wrkList1.get(0);
	   			rfDetailsForm.getQuestion().setRiskQuestionId(gre.getQuestionId());
	   			rfDetailsForm.getQuestion().setQuestionName(gre.getQuestionName());
	   			rfDetailsForm.getQuestion().setQuestonEntryDate(gre.getQuestonEntryDate());
	   			rfDetailsForm.getQuestion().setQuestionText(gre.getQuestionText());
	   			rfDetailsForm.getQuestion().setUiControlType(gre.getUiControlType());
	   			rfDetailsForm.getQuestion().setCollapsibleHeader(new Boolean (gre.isCollapsibleHeader()).toString());
	   			rfDetailsForm.getQuestion().setUiDisplayOrder(gre.getUiDisplayOrder());
	 			rfDetailsForm.getQuestion().setAllowFutureDates(new Boolean(gre.isAllowsFutureDates()).toString());
	 			rfDetailsForm.getQuestion().setNumericOnly(new Boolean(gre.isNumeric()).toString());
	 			rfDetailsForm.getQuestion().setHardcoded(new Boolean(gre.isHardcoded()).toString());
	   			rfDetailsForm.getQuestion().setQuestionInitialAction(gre.getInitialAction());
	 			rfDetailsForm.getQuestion().setRequired(new Boolean(gre.isRequired()).toString());
				rfDetailsForm.getQuestion().setRiskCategoryId(String.valueOf(gre.getRiskCategoryId()));
	 			rfDetailsForm.getQuestion().setControlCode(gre.getControlCode());
	 			rfDetailsForm.getQuestion().setControlCodeName(gre.getControlCodeDesc());
	 			rfDetailsForm.getQuestion().setAllowPrint(new Boolean(gre.isAllowPrint()).toString());
	 			rfDetailsForm.setAnswerList(new ArrayList());
	 			GetAnswersEvent ansEvent =
	 				(GetAnswersEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETANSWERS);
	 			ansEvent.setQuestionId(questionId);
	 			ansEvent.setReturnAnswersBasedOnQuestionId(true);
	 	    	CompositeResponse ansResponse = MessageUtil.postRequest(ansEvent);
	 	    	
	 	    	List wrkList2 = MessageUtil.compositeToList( ansResponse, GetAnswerResponseEvent.class );
	 	    	if (wrkList2 != null)
	 	    	{
	 	    		Collections.sort(wrkList2);
	 	    		rfDetailsForm.setAnswerList(wrkList2);
	 	    	}
	 	    	wrkList1 = null;
	 	    	wrkList2 = null;
	    	}
	    	questEvent = null;
	    	questionId = null;
	    	subQuestion = null;
	    	return aMapping.findForward("displayCategoryQuestionSuccess") ;
		}
	   
	   public ActionForward back(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
	            HttpServletResponse aResponse)
	    {
	    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
	    	String forwardStr = UIConstants.BACK;
	    	if ("Y".equalsIgnoreCase(rfDetailsForm.getQuestionIsSubordinate() ) ) {
    			forwardStr = "backToParentQuestion";
    			rfDetailsForm.setQuestionIsSubordinate(UIConstants.EMPTY_STRING);
	    	}
	    	return aMapping.findForward(forwardStr) ;
	    }	
}