package ui.juvenilecase.riskanalysis.action;

import messaging.riskanalysis.SaveQuestionEvent;
import messaging.riskanalysis.UpdateCategoryQuestionEvent;
import messaging.riskanalysis.reply.CategoryResponseEvent;
import messaging.riskanalysis.reply.SaveQuestionResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.riskanalysis.RiskAnalysisHelper;
import ui.juvenilecase.riskanalysis.RiskAnalysisObjectsAdapter;
import ui.juvenilecase.riskanalysis.form.HandleRiskQuestionDetailsSelectionForm;
import ui.juvenilecase.riskanalysis.form.RiskCategoryDetailsForm;
import ui.juvenilecase.riskanalysis.form.RiskCategorySearchForm;
import ui.juvenilecase.riskanalysis.form.RiskQuestionUpdateForm;
import ui.juvenilecase.riskanalysis.form.objects.Question;

import org.apache.struts.action.ActionForward;
import java.util.Map;
import org.apache.struts.action.ActionForm;

public class SubmitUpdateCategoryQuestionAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "updateRequest");
		keyMap.put("button.categoryDetails", "backToDetails");
	}

    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @throws GeneralFeedbackMessageException 
     */
    public ActionForward updateRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {   
    	RiskQuestionUpdateForm rqForm = (RiskQuestionUpdateForm) aForm;
    	RiskCategorySearchForm rcSearchForm = (RiskCategorySearchForm) getSessionForm(aMapping, aRequest, "riskCategorySearchForm", true);
    	RiskCategoryDetailsForm rcDetailsForm = (RiskCategoryDetailsForm)getSessionForm(aMapping, aRequest, "riskCategoryDetailsForm", true);
       	HandleRiskQuestionDetailsSelectionForm hrQDetailsSelectionForm = (HandleRiskQuestionDetailsSelectionForm)getSessionForm(aMapping, aRequest, "handleRiskQuestionDetailsSelectionForm", true);

    	//Create Save Questions & Answers Event    	
    	UpdateCategoryQuestionEvent reqEvent = (UpdateCategoryQuestionEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.UPDATECATEGORYQUESTION);
    	createSaveQuestionEvent(rqForm,	reqEvent);
    	
    	
    	//Run Save Questions Event
    	CompositeResponse cr = MessageUtil.postRequest(reqEvent); 
    	
    	//Extract Newly Saved Question from Response
    	SaveQuestionResponseEvent saveQuestionResponseEvent = (SaveQuestionResponseEvent)
			MessageUtil.filterComposite( cr, SaveQuestionResponseEvent.class );
    	
    	//Category ID and Question ID may have been changed if a new version was created.
    	CategoryResponseEvent cre = (CategoryResponseEvent) MessageUtil.filterComposite(cr, CategoryResponseEvent.class);
    	
    	rcDetailsForm.getCategory().setCategoryId(cre.getCategoryId());
    	rcDetailsForm.getCategory().setFormulaStatusCd(cre.getFormulaStatusCd());
    	rcDetailsForm.getCategory().setUpdatable(cre.isUpdatable());
	    rcDetailsForm.getCategory().setVersion(cre.getVersion());
	    rcDetailsForm.getCategory().setEntryDate(cre.getEntryDate());
    	
    	hrQDetailsSelectionForm.getCategory().setCategoryId(cre.getCategoryId());
    	hrQDetailsSelectionForm.getCategory().setFormulaStatusCd(cre.getFormulaStatusCd());
    	hrQDetailsSelectionForm.getCategory().setUpdatable(cre.isUpdatable());
    	hrQDetailsSelectionForm.getQuestion().setRiskQuestionId(saveQuestionResponseEvent.getRiskQuestionId());
    	hrQDetailsSelectionForm.getQuestion().setRiskCategoryId(saveQuestionResponseEvent.getRiskCategoryId());
    	
    	rcSearchForm.setRiskCategoryId(cre.getCategoryId());
    	rcSearchForm.setSelectedValue(cre.getCategoryId());

    	
    	//Clear Form 
    	rqForm.clearForm();
    	
    	//Set Newly Saved Question in Form
    	setSavedQuestionInForm(rqForm, saveQuestionResponseEvent);
    	rqForm.setPageType("confirm");
    	return aMapping.findForward("updateSuccess");
    }
    
	private void setSavedQuestionInForm(
			RiskQuestionUpdateForm riskQuestionUpdateForm,
			SaveQuestionResponseEvent saveQuestionResponseEvent) {
		
		Question question = RiskAnalysisObjectsAdapter.createQuestionObject(saveQuestionResponseEvent);
		riskQuestionUpdateForm.setQuestion(question);  
		riskQuestionUpdateForm.getQuestion().setControlCodeName(
    			RiskAnalysisHelper.getControlCodeName(riskQuestionUpdateForm.getControlCodes(), question.getControlCode()));
	}

	private void createSaveQuestionEvent(
			RiskQuestionUpdateForm riskQuestionUpdateForm,
			SaveQuestionEvent saveQuestionsEvent) {
		
		saveQuestionsEvent.setACreate(false);
		RiskAnalysisObjectsAdapter.createQuestionEventObject(saveQuestionsEvent, riskQuestionUpdateForm.getQuestion(), null);
    	
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
       	RiskQuestionUpdateForm rqForm = (RiskQuestionUpdateForm) aForm;
       	rqForm.clearForm();
       	rqForm.setPageType("");
    	return aMapping.findForward("backToDetails");
    }  
}
