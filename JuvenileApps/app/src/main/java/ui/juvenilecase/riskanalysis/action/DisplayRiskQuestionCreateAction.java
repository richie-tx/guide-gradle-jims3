package ui.juvenilecase.riskanalysis.action;

import java.util.Collections;
import java.util.Date;
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
import naming.RiskAnalysisConstants;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.common.SimpleCodeTableHelper;
import ui.juvenilecase.riskanalysis.RiskAnalysisHelper;
import ui.juvenilecase.riskanalysis.RiskAnalysisUIHelper;
import ui.juvenilecase.riskanalysis.form.RiskQuestionCreateForm;
import ui.juvenilecase.riskanalysis.form.objects.Answer;
import ui.juvenilecase.riskanalysis.form.objects.Question;

public class DisplayRiskQuestionCreateAction extends JIMSBaseAction
{
	public DisplayRiskQuestionCreateAction()
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
    	RiskQuestionCreateForm riskQuestionCreateForm = (RiskQuestionCreateForm) aForm;
        
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
		riskQuestionCreateForm.setQuestions(questionsFiltered);
    	
    	//Get Control Codes
    	GetRiskAnalysisControlCodesEvent getRiskAnalysisControlCodesEvent =
			(GetRiskAnalysisControlCodesEvent) EventFactory.getInstance(CodeTableControllerServiceNames.GETRISKANALYSISCONTROLCODES);		
		CompositeResponse compositeResponse = 
        	MessageUtil.postRequest(getRiskAnalysisControlCodesEvent); 
		List controlCodes = MessageUtil.compositeToList(compositeResponse, RiskAnalysisControlCodeResponseEvent.class);
		
		//Set Control Code List in Form
    	riskQuestionCreateForm.setControlCodes(RiskAnalysisUIHelper.sortControlCodes(controlCodes) );
    	
    	//Clear New Answer List
    	riskQuestionCreateForm.clearForm();
    	
    	riskQuestionCreateForm.getQuestion().setQuestonEntryDate(RiskAnalysisConstants.DATE_FORMAT.format(new Date()));
    	
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
    	RiskQuestionCreateForm riskQuestionCreateForm = (RiskQuestionCreateForm) aForm;
    	// weight value not always captured when field is disabled for UIControlType = QUESTIONHEADER
    	if ("QUESTIONHEADER".equalsIgnoreCase(riskQuestionCreateForm.getQuestion().getUiControlType() ) ){
    		if (riskQuestionCreateForm.getCurrentAnswer().getWeight() == null){
    			riskQuestionCreateForm.getCurrentAnswer().setWeight("0");
    		}
    	}
        Answer answer = riskQuestionCreateForm.getCurrentAnswer();
        
        if( answer != null )
        {
        	
        	//Validate Data
        	boolean validationFailed = validateData(aMapping, aRequest, riskQuestionCreateForm);
        	if (validationFailed)
        		return refreshPage(aMapping, aForm,  aRequest,aResponse);
        	
        	if( notNullNotEmptyString(answer.getAnswerText()) )
            {
        		//Check for subordinate question, use question name for display
        		RiskAnalysisHelper.setSubordinateQuestionName(riskQuestionCreateForm.getQuestions(), answer);
        		//Add Answer to New Answer List
        		riskQuestionCreateForm.getNewAnswerList().add(answer);
            }
        }

        //Possible Sort needed?
        //riskQuestionCreateForm.setNewAnswerList(UIJuvenileHelper.sortMemberAddressList((ArrayList)myForm.getNewAddressList()));
        riskQuestionCreateForm.clearCurrentAnswer();
        
        return( aMapping.findForward("updateAnswerList"));
    }

	
    
    private boolean validateData(ActionMapping aMapping,
			HttpServletRequest aRequest,
			RiskQuestionCreateForm riskQuestionCreateForm) {
    	
    	List<Answer> answers = riskQuestionCreateForm.getNewAnswerList();
    	Answer duplicateAnswerTextInNewAnswerList = null;
    	for (Answer answer : answers) 
    	{
    		if (riskQuestionCreateForm.getCurrentAnswer().getAnswerText().equalsIgnoreCase(answer.getAnswerText()))
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
	   RiskQuestionCreateForm riskQuestionCreateForm = (RiskQuestionCreateForm) aForm;
       String currentAddressPosition = riskQuestionCreateForm.getSelectedValue();
       
       if( riskQuestionCreateForm.getNewAnswerList() != null 
        		&& riskQuestionCreateForm.getNewAnswerList().size() > 0 )
       {
    	   RiskAnalysisHelper.removeAnswerFromList(riskQuestionCreateForm.getNewAnswerList(), currentAddressPosition);
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
	   RiskQuestionCreateForm createForm = (RiskQuestionCreateForm) aForm;
	   
	   Question question = createForm.getQuestion();
	   String uiControlType = question.getUiControlType();
	   question.setUiControlTypeDesc(SimpleCodeTableHelper.getDescrByCode(RiskAnalysisConstants.JUV_RISK_UI_CONTROL_TYPE, uiControlType));
	   createForm.setQuestion(question);
	   
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
}
