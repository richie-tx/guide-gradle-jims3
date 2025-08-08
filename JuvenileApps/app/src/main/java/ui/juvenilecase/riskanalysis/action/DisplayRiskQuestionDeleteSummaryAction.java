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
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import org.apache.struts.action.ActionForward;
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;
import ui.juvenilecase.riskanalysis.RiskAnalysisHelper;
import ui.juvenilecase.riskanalysis.RiskAnalysisObjectsAdapter;
import ui.juvenilecase.riskanalysis.RiskAnalysisUIHelper;
import ui.juvenilecase.riskanalysis.form.RiskQuestionDeleteForm;
import ui.juvenilecase.riskanalysis.form.objects.Answer;
import ui.juvenilecase.riskanalysis.form.objects.Question;

public class DisplayRiskQuestionDeleteSummaryAction extends JIMSBaseAction
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
    	RiskQuestionDeleteForm riskQuestionDeleteForm 
    		= (RiskQuestionDeleteForm) aForm;
    	
    	//Clear form for new incoming question detail
    	riskQuestionDeleteForm.clearForm();
    	
    	//Place questionId on form
    	//Get Question ID from Request
    	String riskQuestionId = (String)aRequest.getSession().getAttribute("riskQuestionId");
    	
    	//Set in Question Form
    	riskQuestionDeleteForm.getQuestion().setRiskQuestionId(riskQuestionId);
    	
    	//Validate Data
    	boolean validationFailed = validateData(aMapping, aRequest, riskQuestionDeleteForm);
    	if (validationFailed)
    		return aMapping.findForward("validationFailed");
    	
    	//Create Get Questions Event
    	GetQuestionsEvent getQuestionsEvent = (GetQuestionsEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
    	getQuestionsEvent.setReturnSingleQuestionBasedOnId(true);
    	getQuestionsEvent.setQuestionId(riskQuestionId);
    	
    	//Run Get Questions Event
    	CompositeResponse questionsReponse = 
        	MessageUtil.postRequest(getQuestionsEvent); 
       
    	//Extract Single GetQuestionsResponseEvent 
    	List<GetQuestionResponseEvent> questions 
			= MessageUtil.compositeToList( questionsReponse, GetQuestionResponseEvent.class );
    	
    	GetQuestionResponseEvent getQuestionResponseEvent = new GetQuestionResponseEvent();
    	for (GetQuestionResponseEvent question : questions) 
    		getQuestionResponseEvent = question; //Only one question is expected
    	
    	//Get Control Codes - has to run before question is saved in form
    	GetRiskAnalysisControlCodesEvent getRiskAnalysisControlCodesEvent =
			(GetRiskAnalysisControlCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETRISKANALYSISCONTROLCODES);		
		CompositeResponse compositeResponse = 
        	MessageUtil.postRequest(getRiskAnalysisControlCodesEvent); 
		List controlCodes = MessageUtil.compositeToList(compositeResponse, RiskAnalysisControlCodeResponseEvent.class);
		
		//Set Control Code List in Form
		riskQuestionDeleteForm.setControlCodes(RiskAnalysisUIHelper.sortControlCodes(controlCodes) );
		
    	//Set Question Detail on form
    	setRetrievedQuestionInForm( riskQuestionDeleteForm, getQuestionResponseEvent);
        
    	 //Create Get Answers Event
    	GetAnswersEvent getAnswersEvent = (GetAnswersEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETANSWERS);
    	getAnswersEvent.setReturnAnswersWithASubordinateQuestionAttached(false);    
    	getAnswersEvent.setReturnAnswersBasedOnQuestionId(true);
    	getAnswersEvent.setQuestionId(riskQuestionId);
    	
    	//Run Get Questions Event
    	CompositeResponse answersReponse = 
        	MessageUtil.postRequest(getAnswersEvent); 
       
    	//Extract GetQuestionsResponse Events 
    	List answers = MessageUtil.compositeToList( answersReponse, GetAnswerResponseEvent.class );    	
    	//Set Answers on form
    	setRetrievedAnswersInForm(riskQuestionDeleteForm, answers, false);

	
    	return aMapping.findForward("success");
    }

	public boolean  validateData(ActionMapping aMapping,
			HttpServletRequest aRequest,
			RiskQuestionDeleteForm riskQuestionDeleteForm) {
		
		//Create Get Answers Event
    	GetAnswersEvent getAnswerBasedOnSubordinateQuestionIdEvent = (GetAnswersEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETANSWERS);
    	getAnswerBasedOnSubordinateQuestionIdEvent.setReturnAnswerBasedOnSubordinateQuestionId(true);  
    	getAnswerBasedOnSubordinateQuestionIdEvent.setQuestionId(riskQuestionDeleteForm.getQuestion().getRiskQuestionId());
    	
    	//Run Get Answers Event
    	CompositeResponse answerBasedOnSubordinateQuestionIdReponse = 
        	MessageUtil.postRequest(getAnswerBasedOnSubordinateQuestionIdEvent); 
       
    	//Extract GetQuestionsResponseEvents and place in List
    	List<GetAnswerResponseEvent> answersBasedOnSubordinateQuestionIdReponseEvents 
    		= MessageUtil.compositeToList( answerBasedOnSubordinateQuestionIdReponse, GetAnswerResponseEvent.class );
    	
    	//Check to see if question is a subordinate question, if so, can't delete. notify user
    	GetAnswerResponseEvent answerResponseEvent = null;
		for (GetAnswerResponseEvent answers: answersBasedOnSubordinateQuestionIdReponseEvents )
			answerResponseEvent = answers; //Only one question is expected
		
		if (answerResponseEvent != null) 
		{
			//Question is a subordinate question, do not allow a delete
			ActionErrors errors = new ActionErrors();
			ActionMessage newErr = null;
			newErr = new ActionMessage( "error.questionCannotBeDeleted", answerResponseEvent.getSubordinateQuestionName()
					, answerResponseEvent.getResponse(), answerResponseEvent.getRiskQuestionName()  );
			errors.add( ActionErrors.GLOBAL_MESSAGE, newErr );
			saveErrors( aRequest, errors );
			
			//QuestionId to be used for the Question Details Selection page
	    	aRequest.setAttribute("selectedRiskQuestionId", riskQuestionDeleteForm.getQuestion().getRiskQuestionId());
			
	    	return true;
		}
		
		return false;
	}
    
    private void setRetrievedQuestionInForm(
    		RiskQuestionDeleteForm riskQuestionDeleteForm,
			GetQuestionResponseEvent getQuestionResponseEvent) {
		
    	Question question = RiskAnalysisObjectsAdapter.createQuestionObject(getQuestionResponseEvent);
    	riskQuestionDeleteForm.setQuestion(question);    	
    	riskQuestionDeleteForm.getQuestion().setControlCodeName(
    			RiskAnalysisHelper.getControlCodeName(riskQuestionDeleteForm.getControlCodes(), question.getControlCode()));
	}
    
    private void setRetrievedAnswersInForm(
    		RiskQuestionDeleteForm riskQuestionDeleteForm,
			List answerResponseEventObjects, boolean singleAnswer) {
    	
    	List<Answer> answers = RiskAnalysisObjectsAdapter.createAnswerObjects(answerResponseEventObjects);
    	riskQuestionDeleteForm.setAnswerList(answers);
    	
	}
	
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link", "setFormBeansAndSendToDisplay");
	}
}
