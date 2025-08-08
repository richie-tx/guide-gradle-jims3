package ui.juvenilecase.riskanalysis.action;

import messaging.riskanalysis.GetAnswersEvent;
import messaging.riskanalysis.RemoveAnswerEvent;
import messaging.riskanalysis.reply.GetAnswerResponseEvent;
import messaging.riskanalysis.reply.RemoveAnswerResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
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
import ui.juvenilecase.riskanalysis.form.RiskAnswerDeleteForm;
import ui.juvenilecase.riskanalysis.form.objects.Answer;

public class SubmitRiskAnswerDeleteAction extends JIMSBaseAction
{
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward removeAnswer(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	//Get Form
    	RiskAnswerDeleteForm riskAnswerDeleteForm = (RiskAnswerDeleteForm) aForm;
    
    	//Create Save Questions & Answers Event
    	RemoveAnswerEvent removeAnswerEvent = (RemoveAnswerEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.REMOVEANSWER);
    	removeAnswerEvent.setRiskAnswerId(riskAnswerDeleteForm.getCurrentAnswer().getRiskAnswerId());
    	
    	//Run Save Questions Event
    	CompositeResponse questionsReponse = 
        	MessageUtil.postRequest(removeAnswerEvent); 
    	
    	//Extract Remove Question Response Event from Response
    	RemoveAnswerResponseEvent removeQuestionResponseEvent = (RemoveAnswerResponseEvent)
			MessageUtil.filterComposite( questionsReponse, RemoveAnswerResponseEvent.class );
    	
    	//Clear Answer List
    	riskAnswerDeleteForm.getAnswerList().clear();
        
        //Update Answer List from database
    	GetAnswersEvent getSingleQuestionAnswersEvent = (GetAnswersEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETANSWERS);
    	getSingleQuestionAnswersEvent.setReturnAnswersBasedOnQuestionId(true);
    	getSingleQuestionAnswersEvent.setQuestionId(riskAnswerDeleteForm.getQuestion().getRiskQuestionId());
    	
    	//Run Get Answer Event
    	CompositeResponse singleQuestionAnswersReponse = 
        	MessageUtil.postRequest(getSingleQuestionAnswersEvent); 
       
    	//Extract GetQuestionsResponse Events 
    	List singleQuestionAnswers = MessageUtil.compositeToList( singleQuestionAnswersReponse, GetAnswerResponseEvent.class );
    	//Set Answers on form
    	setRetrievedAnswersInForm(riskAnswerDeleteForm, singleQuestionAnswers, false);
    	
    	return aMapping.findForward("success");
    	
    }
    
    private void setRetrievedAnswersInForm(
    		RiskAnswerDeleteForm riskAnswerDeleteForm,
			List answerResponseEventObjects, boolean singleAnswer) {
    	
    	List<Answer> answers = RiskAnalysisObjectsAdapter.createAnswerObjects(answerResponseEventObjects);
    	
    	if (singleAnswer) 
    	{
        	for (Answer answer : answers) 
        		riskAnswerDeleteForm.setCurrentAnswer(answer); //Only one answer is expected
    	}
    	else 
    	{
    		riskAnswerDeleteForm.setAnswerList(answers);
    	}
    	
		
	}
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 433C3D3D01E1
     */
    public ActionForward goToQuestionDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	//Get Form(s) 
    	RiskAnswerDeleteForm riskAnswerDeleteForm = (RiskAnswerDeleteForm) aForm;
    	
    	//QuestionId to be used for the Question Details Selection page
    	String selectedRiskQuestionId = riskAnswerDeleteForm.getQuestion().getRiskQuestionId();
    	aRequest.setAttribute("selectedRiskQuestionId", selectedRiskQuestionId);
    	
    	return aMapping.findForward("goToQuestionDetails");
    }
	
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.questionDetails", "goToQuestionDetails");
		keyMap.put("button.finish", "removeAnswer");
		keyMap.put("button.back", "back");
	}
	
}
