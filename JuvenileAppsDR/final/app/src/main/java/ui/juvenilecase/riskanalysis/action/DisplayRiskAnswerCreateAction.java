package ui.juvenilecase.riskanalysis.action;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.riskanalysis.RiskAnalysisHelper;
import ui.juvenilecase.riskanalysis.RiskAnalysisObjectsAdapter;
import ui.juvenilecase.riskanalysis.RiskAnalysisUIHelper;
import ui.juvenilecase.riskanalysis.form.RiskAnswerCreateForm;
import ui.juvenilecase.riskanalysis.form.objects.Answer;
import ui.juvenilecase.riskanalysis.form.objects.Question;

public class DisplayRiskAnswerCreateAction extends JIMSBaseAction
{
	public DisplayRiskAnswerCreateAction()
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
    	RiskAnswerCreateForm riskAnswerCreateForm = (RiskAnswerCreateForm) aForm;
    	
    	//Clear form for new incoming question detail
    	riskAnswerCreateForm.clearForm();
    	
    	//Get Question ID from Request
    	String riskQuestionId = (String)aRequest.getSession().getAttribute("riskQuestionId");
    	
    	//Set in Question Form
    	riskAnswerCreateForm.getQuestion().setRiskQuestionId(riskQuestionId);
    	
    	//Get Control Codes
    	GetRiskAnalysisControlCodesEvent getRiskAnalysisControlCodesEvent =
			(GetRiskAnalysisControlCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETRISKANALYSISCONTROLCODES);		
		CompositeResponse compositeResponse = 
        	MessageUtil.postRequest(getRiskAnalysisControlCodesEvent); 
		List controlCodes = MessageUtil.compositeToList(compositeResponse, RiskAnalysisControlCodeResponseEvent.class);
		
		//Set Question List in Form
    	riskAnswerCreateForm.setControlCodes(RiskAnalysisUIHelper.sortControlCodes(controlCodes) );
    	
    	//Create Get Questions Event
    	GetQuestionsEvent getSingleQuestionEvent = (GetQuestionsEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
    	getSingleQuestionEvent.setReturnQuestionsNotAttachedToCategory(false);
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
    	setRetrievedQuestionInForm( riskAnswerCreateForm, getQuestionResponseEvent);
        
    	 //Create Get Answers Event
    	GetAnswersEvent getSingleQuestionAnswersEvent = (GetAnswersEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETANSWERS);
    	getSingleQuestionAnswersEvent.setReturnAnswersWithASubordinateQuestionAttached(false);    
    	getSingleQuestionAnswersEvent.setReturnAnswersBasedOnQuestionId(true);
    	getSingleQuestionAnswersEvent.setQuestionId(riskQuestionId);
    	
    	//Run Get Questions Event
    	CompositeResponse singleQuestionAnswersReponse = 
        	MessageUtil.postRequest(getSingleQuestionAnswersEvent); 
       
    	//Extract GetQuestionsResponse Events 
    	List singleQuestionAnswers = MessageUtil.compositeToList( singleQuestionAnswersReponse, GetAnswerResponseEvent.class );
    	//Set Answers on form
    	setRetrievedAnswersInForm(riskAnswerCreateForm, singleQuestionAnswers, false);
    	
    	//Create Get Questions Event
    	GetQuestionsEvent getQuestionsEvent = (GetQuestionsEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
    	getQuestionsEvent.setReturnQuestionsNotAttachedToCategory(true);
    	
    	//Run Get Questions Event
    	CompositeResponse questionsReponse = 
        	MessageUtil.postRequest(getQuestionsEvent); 
       
    	//Extract GetQuestionsResponseEvents and place in List
    	List<GetQuestionResponseEvent> questions = MessageUtil.compositeToList( questionsReponse, GetQuestionResponseEvent.class );
    	
    	 //Create Get Answers Event
    	GetAnswersEvent getAnswersEvent = (GetAnswersEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETANSWERS);
    	getAnswersEvent.setReturnAnswersWithASubordinateQuestionAttached(true);    	
    	
    	//Run Get Questions Event
    	CompositeResponse answersReponse = 
        	MessageUtil.postRequest(getAnswersEvent); 
       
    	//Extract GetQuestionsResponseEvents and place in List
    	List<GetAnswerResponseEvent> answers = MessageUtil.compositeToList( answersReponse, GetAnswerResponseEvent.class );
    	
    	//Remove questions from list that are already subordinate questions
    	List questionsFiltered = RiskAnalysisHelper.removeSubordinateQuestions(questions, answers);
    	
    	//Set Question List in Form
    	Collections.sort(questionsFiltered);
		riskAnswerCreateForm.setQuestions(questionsFiltered);
    	
    	return aMapping.findForward("success");
		
    }

	
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward addAnswer(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	RiskAnswerCreateForm riskAnswerCreateForm = (RiskAnswerCreateForm) aForm;
        Answer answer = riskAnswerCreateForm.getCurrentAnswer();
        
        if( answer != null )
        {
        	
        	//Validate Data
        	boolean validationFailed = validateData(aMapping, aRequest, riskAnswerCreateForm);
        	if (validationFailed)
        		return refreshPage(aMapping, aForm,  aRequest,aResponse);
        	
        	if( notNullNotEmptyString(answer.getAnswerText()) )
            {
        		//Check for subordinate question, use question name for display
        		RiskAnalysisHelper.setSubordinateQuestionName(riskAnswerCreateForm.getQuestions(), answer);
        		//Add Answer to New Answer List
        		riskAnswerCreateForm.getNewAnswerList().add(answer);
            }
        }

        //Possible Sort needed?
        //riskAnswerCreateForm.setNewAnswerList(UIJuvenileHelper.sortMemberAddressList((ArrayList)myForm.getNewAddressList()));
        riskAnswerCreateForm.clearCurrentAnswer();
        
        return( aMapping.findForward("updateAnswerList"));
    }
    
    private boolean validateData(ActionMapping aMapping,
			HttpServletRequest aRequest,
			RiskAnswerCreateForm riskAnswerCreateForm) {
    	
    	List<Answer> answers = riskAnswerCreateForm.getNewAnswerList();
    	Answer duplicateAnswerTextInNewAnswerList = null;
    	for (Answer answer : answers) 
    	{
    		if (riskAnswerCreateForm.getCurrentAnswer().getAnswerText().equalsIgnoreCase(answer.getAnswerText()))
    		{
    			duplicateAnswerTextInNewAnswerList = answer; 
    			break;
    		}
    	}
    	
    	if (duplicateAnswerTextInNewAnswerList != null) 
		{
			//Answer text matches another within Question, do not allow create
			ActionErrors errors = new ActionErrors();
			ActionMessage newErr = null;
			newErr = new ActionMessage( "error.answerTextExistInDuplicateNewAnswerList", duplicateAnswerTextInNewAnswerList.getAnswerText() );
			errors.add( ActionErrors.GLOBAL_MESSAGE, newErr );
			saveErrors( aRequest, errors );
			
			return true;
		}
    	
    	
    	answers = riskAnswerCreateForm.getAnswerList();
    	Answer duplicateAnswerTextInCurrentAnswerList = null;
    	for (Answer answer : answers) 
    	{
    		if (riskAnswerCreateForm.getCurrentAnswer().getAnswerText().equalsIgnoreCase(answer.getAnswerText()))
    		{
    			duplicateAnswerTextInCurrentAnswerList = answer; 
    			break;
    		}
    	}
    	
    	if (duplicateAnswerTextInCurrentAnswerList != null) 
		{
			//Answer text matches another within Question, do not allow create
			ActionErrors errors = new ActionErrors();
			ActionMessage newErr = null;
			newErr = new ActionMessage( "error.answerTextExistInDuplicateCurrentAnswerList", duplicateAnswerTextInCurrentAnswerList.getAnswerText() );
			errors.add( ActionErrors.GLOBAL_MESSAGE, newErr );
			saveErrors( aRequest, errors );
			
			return true;
		}
    	
    	
    	return false;
    	
    }
    
    
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
	   RiskAnswerCreateForm riskAnswerCreateForm = (RiskAnswerCreateForm) aForm;
       String currentAddressPosition = riskAnswerCreateForm.getSelectedValue();
       
       if( riskAnswerCreateForm.getNewAnswerList() != null 
        		&& riskAnswerCreateForm.getNewAnswerList().size() > 0 )
       {
    	   RiskAnalysisHelper.removeAnswerFromList(riskAnswerCreateForm.getNewAnswerList(), currentAddressPosition);
       }
       
       return( aMapping.findForward("updateAnswerList"));
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
		keyMap.put("button.addToList", "addAnswer");
		keyMap.put("button.remove", "removeAnswer");
		keyMap.put("button.next", "goToSummary");
	}
	
	private boolean notNullNotEmptyString( String str )
	{
		return( str != null  &&  (str.length() > 0) ) ;
	}
	
	private void setRetrievedQuestionInForm(
			RiskAnswerCreateForm riskAnswerCreateForm,
			GetQuestionResponseEvent getQuestionResponseEvent) {
		
		Question question = RiskAnalysisObjectsAdapter.createQuestionObject(getQuestionResponseEvent);
		riskAnswerCreateForm.setQuestion(question);    	
		riskAnswerCreateForm.getQuestion().setControlCodeName(
    			RiskAnalysisHelper.getControlCodeName(riskAnswerCreateForm.getControlCodes(), question.getControlCode()));
		
	}
    
	private void setRetrievedAnswersInForm(
			RiskAnswerCreateForm riskAnswerCreateForm,
			List answerResponseEventObjects, boolean singleAnswer) {
    	
    	List<Answer> answers = RiskAnalysisObjectsAdapter.createAnswerObjects(answerResponseEventObjects);
    	
    	if (singleAnswer) 
    	{
        	for (Answer answer : answers) 
        		riskAnswerCreateForm.setCurrentAnswer(answer); //Only one answer is expected
    	}
    	else 
    	{
    		riskAnswerCreateForm.setAnswerList(answers);
    	}
    	
		
	}
	
}
