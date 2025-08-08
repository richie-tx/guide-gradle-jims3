package ui.juvenilecase.riskanalysis.action;

import messaging.riskanalysis.reply.GetQuestionResponseEvent;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import org.apache.struts.action.ActionForward;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;
import ui.juvenilecase.riskanalysis.form.RiskAnswerUpdateForm;
import ui.juvenilecase.riskanalysis.form.objects.Answer;

public class DisplayRiskAnswerUpdateSummaryAction extends JIMSBaseAction
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
    	RiskAnswerUpdateForm riskAnswerUpdateForm = (RiskAnswerUpdateForm) aForm;
    	
    	//Validate Data
    	boolean validationFailed = validateData(aMapping, aRequest, riskAnswerUpdateForm);
    	if (validationFailed)
    		return aMapping.findForward("validationFailed");
    	
    	if (riskAnswerUpdateForm.getCurrentAnswer().getSubordinateQuestionId() != null 
    			&& riskAnswerUpdateForm.getCurrentAnswer().getSubordinateQuestionId().length() > 0) 
    	{
    		Iterator<GetQuestionResponseEvent> ite = riskAnswerUpdateForm.getQuestions().iterator();
            while (ite.hasNext())
            {
            	GetQuestionResponseEvent getQuestionResponseEvent = ite.next();
            	if (riskAnswerUpdateForm.getCurrentAnswer().getSubordinateQuestionId().equalsIgnoreCase(getQuestionResponseEvent.getQuestionId())) 
            	{
            		riskAnswerUpdateForm.getCurrentAnswer().setSubordinateQuestionName(getQuestionResponseEvent.getQuestionName());
            	}
            	
            }
    	}
        
    	return aMapping.findForward("success");
		
    }
    
    private boolean validateData(ActionMapping aMapping,
			HttpServletRequest aRequest,
			RiskAnswerUpdateForm riskAnswerUpdateForm) {
    	
    	List<Answer> answers = riskAnswerUpdateForm.getAnswerList();
    	Answer duplicateAnswerTextInCurrentAnswerList = null;
    	for (Answer answer : answers) 
    	{
    		if (!riskAnswerUpdateForm.getCurrentAnswer().getRiskAnswerId().equalsIgnoreCase(answer.getRiskAnswerId()) &&
    				riskAnswerUpdateForm.getCurrentAnswer().getAnswerText().equalsIgnoreCase(answer.getAnswerText()))
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
	
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.link", "setFormBeansAndSendToDisplay");
	}
}
