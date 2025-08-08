package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.riskanalysis.UpdateCategoryQuestionAnswerEvent;
import messaging.riskanalysis.reply.CategoryResponseEvent;
import messaging.riskanalysis.reply.GetAnswerResponseEvent;
import messaging.riskanalysis.reply.GetQuestionResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.riskanalysis.RiskAnalysisObjectsAdapter;
import ui.juvenilecase.riskanalysis.form.HandleRiskQuestionDetailsSelectionForm;
import ui.juvenilecase.riskanalysis.form.RiskAnswerCreateForm;
import ui.juvenilecase.riskanalysis.form.RiskCategoryDetailsForm;

public class SubmitRiskCategoryAddAnswersAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "addAnswersRequest");
		keyMap.put("button.questionDetails", "backToDetails");
	}
	   /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
	 * @throws GeneralFeedbackMessageException 
     */
    public ActionForward addAnswersRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {   
       	RiskAnswerCreateForm raCreateForm = (RiskAnswerCreateForm) aForm;
    	RiskCategoryDetailsForm rcDetailsForm = (RiskCategoryDetailsForm)getSessionForm(aMapping, aRequest, "riskCategoryDetailsForm", true);

     	UpdateCategoryQuestionAnswerEvent saveAnswerEvent = 
    		(UpdateCategoryQuestionAnswerEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.UPDATECATEGORYQUESTIONANSWER);
    	
    	saveAnswerEvent.setRiskQuestionId(raCreateForm.getRiskQuestionId());
    	saveAnswerEvent.setRiskCategoryId(rcDetailsForm.getCategory().getCategoryId());
	    saveAnswerEvent.setACreate(true);
	    RiskAnalysisObjectsAdapter.createAnswerEventObject(saveAnswerEvent, raCreateForm.getNewAnswerList());

	    CompositeResponse cr = MessageUtil.postRequest(saveAnswerEvent);
	    
	    CategoryResponseEvent cre = (CategoryResponseEvent) MessageUtil.filterComposite(cr, CategoryResponseEvent.class);
	    rcDetailsForm.getCategory().setCategoryId(cre.getCategoryId());
	    rcDetailsForm.getCategory().setFormulaStatusCd(cre.getFormulaStatusCd());
	    rcDetailsForm.getCategory().setUpdatable(cre.isUpdatable());
	    rcDetailsForm.getCategory().setModificationReason(cre.getModificationReason());
	    rcDetailsForm.getCategory().setVersion(cre.getVersion());
	    rcDetailsForm.getCategory().setEntryDate(cre.getEntryDate());
	    
	    // add new answer to answerList
	    HandleRiskQuestionDetailsSelectionForm hrQDetailsSelectionForm = (HandleRiskQuestionDetailsSelectionForm)getSessionForm(aMapping, aRequest, "handleRiskQuestionDetailsSelectionForm", true);
	   	
	   	GetQuestionResponseEvent qre = (GetQuestionResponseEvent) MessageUtil.filterComposite(cr, GetQuestionResponseEvent.class);
	   	raCreateForm.getQuestion().setRiskQuestionId(qre.getQuestionId());
	   	raCreateForm.getQuestion().setRiskCategoryId(Integer.toString(qre.getRiskCategoryId()));
	   	raCreateForm.getAnswerList().clear();
    	hrQDetailsSelectionForm.getQuestion().setRiskQuestionId(qre.getQuestionId());

	    List answers = MessageUtil.compositeToList(cr,GetAnswerResponseEvent.class);
    	if (answers != null) {
    		Collections.sort(answers);
    		hrQDetailsSelectionForm.setAnswerList(answers);
    	} else {
    		hrQDetailsSelectionForm.setAnswerList(new ArrayList());
    	}
    	
    	if (answers != null)
    	{
    		Collections.sort(answers);
    		hrQDetailsSelectionForm.setAnswerList(answers);
    	}
    	answers = null;

    	raCreateForm.setPageType("confirm");
    	return aMapping.findForward("addSuccess");
    }  
	
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward backToDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {   
       	RiskAnswerCreateForm racForm = (RiskAnswerCreateForm) aForm;
       	racForm.clearForm();
       	racForm.setPageType("");
    	return aMapping.findForward("backToDetails");
    }  	
}
