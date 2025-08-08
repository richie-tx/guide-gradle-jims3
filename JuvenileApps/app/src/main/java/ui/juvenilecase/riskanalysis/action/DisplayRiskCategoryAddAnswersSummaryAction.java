package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mojo.km.utilities.DateUtil;

import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.riskanalysis.form.HandleRiskQuestionDetailsSelectionForm;
import ui.juvenilecase.riskanalysis.form.RiskAnswerCreateForm;
import ui.juvenilecase.riskanalysis.form.objects.Answer;

public class DisplayRiskCategoryAddAnswersSummaryAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.next", "nextRequest");
		keyMap.put("button.removeSelected", "removeAnswerRequest");
		keyMap.put("button.addAnswer", "addAnswerRequest");
	}
	
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward nextRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {   
    	RiskAnswerCreateForm raCreateForm = (RiskAnswerCreateForm) aForm;
    	HandleRiskQuestionDetailsSelectionForm hrqDetailsSelectionForm = (HandleRiskQuestionDetailsSelectionForm)getSessionForm(aMapping, aRequest, "handleRiskQuestionDetailsSelectionForm", true);
    	raCreateForm.setRiskQuestionId(hrqDetailsSelectionForm.getQuestion().getRiskQuestionId());
    	raCreateForm.setPageType("summary");
    	return aMapping.findForward("nextSuccess");
    }

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward addAnswerRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {   
    	RiskAnswerCreateForm raCreateForm = (RiskAnswerCreateForm) aForm;
        Answer answer = raCreateForm.getCurrentAnswer();
    	boolean validationFailed = validateData(aMapping, aRequest, raCreateForm);
    	if (validationFailed)
    	{	
    		return refreshPage(aMapping, aForm,  aRequest,aResponse);
    	}
    	raCreateForm.getNewAnswerList().add(answer);
    	raCreateForm.setCurrentAnswer(new Answer());
    	raCreateForm.getCurrentAnswer().setAnswerEntryDate(DateUtil.getCurrentDateString(DateUtil.DATE_FMT_1));

    	return aMapping.findForward("addSuccess");
    }
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward removeAnswerRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {   
    	RiskAnswerCreateForm raCreateForm = (RiskAnswerCreateForm) aForm;
    	int len1 = raCreateForm.getSelectedValues().length;
    	int len2 = raCreateForm.getNewAnswerList().size();
    	List wrkList1 = new ArrayList();
    	String[] selVals = raCreateForm.getSelectedValues();
    	String xStr = "";
    	boolean matchFound = false;
    	for (int x=0; x<len2; x++)
    	{
    		matchFound = false;
    		xStr = Integer.toString(x);
    		for(int y=0; y<len1; y++)
    		{
    			if(selVals[y].equalsIgnoreCase(xStr))
    			{
    				matchFound = true;
    				break;
    			}
    		}
    		if (matchFound == false)
    		{
    			wrkList1.add(raCreateForm.getNewAnswerList().get(x));
    		}
    	}
    	Collections.sort(wrkList1);
    	raCreateForm.setNewAnswerList(wrkList1);
    	wrkList1 = null;
    	return aMapping.findForward("removeSuccess");
    } 

    /**
     * @param aMapping
     * @param aRequest
     * @param form
     * @return ActionForward
     */
    private boolean validateData(ActionMapping aMapping,
			HttpServletRequest aRequest,
			RiskAnswerCreateForm raCreateForm)
    {
    	
    	List<Answer> answers = raCreateForm.getNewAnswerList();
    	Answer duplicateAnswerTextInNewAnswerList = null;
    	for (Answer answer : answers) 
    	{
    		if (raCreateForm.getCurrentAnswer().getAnswerText().equalsIgnoreCase(answer.getAnswerText()))
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
    public ActionForward refreshPage(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
        return( aMapping.getInputForward());
    }
}
