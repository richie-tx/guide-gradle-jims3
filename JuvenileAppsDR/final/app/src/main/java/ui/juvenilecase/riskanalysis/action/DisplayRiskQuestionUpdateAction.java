package ui.juvenilecase.riskanalysis.action;

import messaging.codetable.GetRiskAnalysisControlCodesEvent;
import messaging.codetable.riskanalysis.reply.RiskAnalysisControlCodeResponseEvent;
import messaging.riskanalysis.GetQuestionsEvent;
import messaging.riskanalysis.reply.GetQuestionResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.CodeTableControllerServiceNames;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import org.apache.struts.action.ActionForward;
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;
import ui.juvenilecase.riskanalysis.RiskAnalysisObjectsAdapter;
import ui.juvenilecase.riskanalysis.RiskAnalysisUIHelper;
import ui.juvenilecase.riskanalysis.form.RiskQuestionUpdateForm;
import ui.juvenilecase.riskanalysis.form.objects.Question;

public class DisplayRiskQuestionUpdateAction extends JIMSBaseAction
{
	
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     * @roseuid 433C3D3D01E1
     */
    public ActionForward setFormBeansAndSendToDisplay(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	
    	//Get Form(s) 
    	RiskQuestionUpdateForm riskQuestionUpdateForm = (RiskQuestionUpdateForm) aForm;
    	
    	//Get Question ID from Request
    	String riskQuestionId = (String)aRequest.getSession().getAttribute("riskQuestionId");
    	
    	//Set in Question Form
    	riskQuestionUpdateForm.getQuestion().setRiskQuestionId(riskQuestionId);
    	
    	//Get Control Codes
    	GetRiskAnalysisControlCodesEvent getRiskAnalysisControlCodesEvent =
			(GetRiskAnalysisControlCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETRISKANALYSISCONTROLCODES);		
		CompositeResponse compositeResponse = 
        	MessageUtil.postRequest(getRiskAnalysisControlCodesEvent); 
		List controlCodes = MessageUtil.compositeToList(compositeResponse, RiskAnalysisControlCodeResponseEvent.class);
		
		//Set Question List in Form
		riskQuestionUpdateForm.setControlCodes(RiskAnalysisUIHelper.sortControlCodes(controlCodes) );
    	
		//Create Get Questions Event
    	GetQuestionsEvent getQuestionsEvent = (GetQuestionsEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
    	getQuestionsEvent.setReturnSingleQuestionBasedOnId(true);
    	getQuestionsEvent.setQuestionId(riskQuestionId);
    	
    	//Run Get Questions Event
    	CompositeResponse questionsReponse = 
        	MessageUtil.postRequest(getQuestionsEvent); 
       
    	//Extract Single GetQuestionsResponseEvent 
    	List questions 
			= MessageUtil.compositeToList( questionsReponse, GetQuestionResponseEvent.class );
    	
    	GetQuestionResponseEvent getQuestionResponseEvent = new GetQuestionResponseEvent();
    	for (Object o : questions) 
    		getQuestionResponseEvent = (GetQuestionResponseEvent)o; //Only one question is expected
    	
    	//Set Question Detail on form
    	setRetrievedQuestionInForm( riskQuestionUpdateForm, getQuestionResponseEvent);
    	
    	return aMapping.findForward("success");
		
    }
    
    private void setRetrievedQuestionInForm(
    		RiskQuestionUpdateForm riskQuestionUpdateForm,
			GetQuestionResponseEvent getQuestionResponseEvent) {
		
    	Question question = RiskAnalysisObjectsAdapter.createQuestionObject(getQuestionResponseEvent);
    	riskQuestionUpdateForm.setQuestion(question);
    	
    	
	}
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward goToSummary(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return( aMapping.findForward("goToSummary"));
    }
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward refreshPage(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return( aMapping.getInputForward());
    }
	
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.refresh", "refreshPage");
		keyMap.put("button.link", "setFormBeansAndSendToDisplay");
		keyMap.put("button.next", "goToSummary");
	}
	
}
