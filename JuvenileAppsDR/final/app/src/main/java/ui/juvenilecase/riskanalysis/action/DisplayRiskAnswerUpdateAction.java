package ui.juvenilecase.riskanalysis.action;

import messaging.codetable.GetRiskAnalysisControlCodesEvent;
import messaging.codetable.riskanalysis.reply.RiskAnalysisControlCodeResponseEvent;
import messaging.riskanalysis.GetAnswersEvent;
import messaging.riskanalysis.GetQuestionsEvent;
import messaging.riskanalysis.reply.GetAnswerResponseEvent;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;
import ui.juvenilecase.riskanalysis.RiskAnalysisHelper;
import ui.juvenilecase.riskanalysis.RiskAnalysisObjectsAdapter;
import ui.juvenilecase.riskanalysis.RiskAnalysisUIHelper;
import ui.juvenilecase.riskanalysis.form.RiskAnswerUpdateForm;
import ui.juvenilecase.riskanalysis.form.objects.Answer;
import ui.juvenilecase.riskanalysis.form.objects.Question;

public class DisplayRiskAnswerUpdateAction extends JIMSBaseAction
{
	
	public DisplayRiskAnswerUpdateAction()
	{
		
	}
	
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
        //RiskAnalysisForm riskForm = (RiskAnalysisForm) aRequest.getSession().getAttribute("riskAnalysisForm");
    	RiskAnswerUpdateForm riskAnswerUpdateForm = (RiskAnswerUpdateForm) aForm;
    	
    	//Clear form for new incoming question detail
    	riskAnswerUpdateForm.clearForm();
    	
    	//Get Question ID from Request
    	String riskQuestionId = (String)aRequest.getSession().getAttribute("riskQuestionId");
    	
    	//Set Question ID inForm
    	riskAnswerUpdateForm.getQuestion().setRiskQuestionId(riskQuestionId);
    	
    	//Get Answer ID from Request
    	String riskAnswerId = (String)aRequest.getSession().getAttribute("selectedRiskAnswerId");
    	
    	//Set Answer ID in Form
    	riskAnswerUpdateForm.getCurrentAnswer().setRiskAnswerId(riskAnswerId);
    	
    	//Get Control Codes
    	GetRiskAnalysisControlCodesEvent getRiskAnalysisControlCodesEvent =
			(GetRiskAnalysisControlCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETRISKANALYSISCONTROLCODES);		
		CompositeResponse compositeResponse = 
        	MessageUtil.postRequest(getRiskAnalysisControlCodesEvent); 
		List controlCodes = MessageUtil.compositeToList(compositeResponse, RiskAnalysisControlCodeResponseEvent.class);
		
		//Set Question List in Form
    	riskAnswerUpdateForm.setControlCodes(RiskAnalysisUIHelper.sortControlCodes(controlCodes) );
    	
    	//Create Get Questions Event
    	GetQuestionsEvent getSingleQuestionEvent = (GetQuestionsEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
    	getSingleQuestionEvent.setReturnSingleQuestionBasedOnId(true);
    	getSingleQuestionEvent.setQuestionId(riskQuestionId);
    	
    	//Run Get Questions Event
    	CompositeResponse singleQuestionReponse = 
        	MessageUtil.postRequest(getSingleQuestionEvent); 
       
    	//Extract Single GetQuestionsResponseEvent 
    	List singleQuestion
			= MessageUtil.compositeToList( singleQuestionReponse, GetQuestionResponseEvent.class );
    	
    	GetQuestionResponseEvent getQuestionResponseEvent = new GetQuestionResponseEvent();
    	for (Object o : singleQuestion) 
    		getQuestionResponseEvent = (GetQuestionResponseEvent)o; //Only one question is expected
    	
    	//Set Question Detail on form
    	setRetrievedQuestionInForm( riskAnswerUpdateForm, getQuestionResponseEvent);
        
    	//Create Get Answers Event
    	GetAnswersEvent getSingleQuestionAnswersEvent = (GetAnswersEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETANSWERS);
    	getSingleQuestionAnswersEvent.setReturnAnswersBasedOnQuestionId(true);
    	getSingleQuestionAnswersEvent.setQuestionId(riskQuestionId);
    	
    	//Run Get Questions Event
    	CompositeResponse singleQuestionAnswersReponse = 
        	MessageUtil.postRequest(getSingleQuestionAnswersEvent); 
       
    	//Extract GetQuestionsResponse Events 
    	List singleQuestionAnswers = MessageUtil.compositeToList( singleQuestionAnswersReponse, GetAnswerResponseEvent.class );
    	//Set Answers on form
    	setRetrievedAnswersInForm(riskAnswerUpdateForm, singleQuestionAnswers, false);
    	
    	
    	// Create Get Answers Event for Selected Risk Answer ID
    	GetAnswersEvent getSelectedAnswerEvent = (GetAnswersEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETANSWERS);
    	getSelectedAnswerEvent.setReturnAnswerBasedOnAnswerId(true);    
    	getSelectedAnswerEvent.setAnswerId(riskAnswerId);
    	
    	//Run Get Questions Event
    	CompositeResponse selectedAnswerReponse = 
        	MessageUtil.postRequest(getSelectedAnswerEvent); 
       
    	//Extract GetQuestionsResponseEvent and place in Current Answer
    	List selectedAnswer = MessageUtil.compositeToList( selectedAnswerReponse, GetAnswerResponseEvent.class );
    	//Set Question Detail on form
    	setRetrievedAnswersInForm( riskAnswerUpdateForm, selectedAnswer, true);
    	
    	
    	//Create Get Questions Event
    	GetQuestionsEvent getQuestionsEvent = (GetQuestionsEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
    	getQuestionsEvent.setReturnQuestionsNotAttachedToCategory(true);
    	
    	//Run Get Questions Event
    	CompositeResponse questionsReponse = 
        	MessageUtil.postRequest(getQuestionsEvent); 
       
    	//Extract GetQuestionsResponseEvents and place in List
    	List questions = MessageUtil.compositeToList( questionsReponse, GetQuestionResponseEvent.class );
		//Collections.sort( questions );
    	
        
    	 //Create Get Answers Event
    	GetAnswersEvent getAnswersEvent = (GetAnswersEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETANSWERS);
    	getAnswersEvent.setReturnAnswersWithASubordinateQuestionAttached(true);    	
    	
    	//Run Get Questions Event
    	CompositeResponse answersReponse = 
        	MessageUtil.postRequest(getAnswersEvent); 
       
    	//Extract GetQuestionsResponseEvents and place in List
    	List answers = MessageUtil.compositeToList( answersReponse, GetAnswerResponseEvent.class );
		//Collections.sort( questions );
    	
    	//Remove questions from list that are already subordinate questions
    	List questionsFiltered = new ArrayList(questions); 
    	Iterator<GetQuestionResponseEvent> questionsIter = questions.iterator();
		while (questionsIter.hasNext())
		{
			GetQuestionResponseEvent questionResponseEvent = questionsIter.next();
			
			Iterator<GetAnswerResponseEvent> awsersIter = answers.iterator();
			while (awsersIter.hasNext())
			{
				GetAnswerResponseEvent answerResponseEvent = awsersIter.next();
				//First part of if checks to see Question is a subordinate question -
				//Second part of if will allow the question to be added to questionsFiltered if it matches the selectedAnswerResponseEvent SubordinateQuestionId
				if (questionResponseEvent.getQuestionId().equalsIgnoreCase(answerResponseEvent.getSubordinateQuestionId()) 
								&& !questionResponseEvent.getQuestionId().equalsIgnoreCase(riskAnswerUpdateForm.getCurrentAnswer().getSubordinateQuestionId()) )  
				{
					questionsFiltered.remove(questionResponseEvent);
				}
				
			}
		}
    	
    	//Set Question List in Form
		Collections.sort(questionsFiltered);
		riskAnswerUpdateForm.setQuestions(questionsFiltered);
    	
    	return aMapping.findForward("success");
    }
    
    private void setRetrievedQuestionInForm(
    		RiskAnswerUpdateForm riskAnswerUpdateForm,
			GetQuestionResponseEvent getQuestionResponseEvent) {
		
    	Question question = RiskAnalysisObjectsAdapter.createQuestionObject(getQuestionResponseEvent);
    	riskAnswerUpdateForm.setQuestion(question);    	
    	riskAnswerUpdateForm.getQuestion().setControlCodeName(
    			RiskAnalysisHelper.getControlCodeName(riskAnswerUpdateForm.getControlCodes(), question.getControlCode()));
	}
    
    private void setRetrievedAnswersInForm(
    		RiskAnswerUpdateForm riskAnswerUpdateForm,
			List answerResponseEventObjects, boolean singleAnswer) {
    	
    	List<Answer> answers = RiskAnalysisObjectsAdapter.createAnswerObjects(answerResponseEventObjects);
    	
    	if (singleAnswer) 
    	{
        	for (Answer answer : answers) 
        		riskAnswerUpdateForm.setCurrentAnswer(answer); //Only one answer is expected
    	}
    	else 
    	{
    		riskAnswerUpdateForm.setAnswerList(answers);
    	}
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