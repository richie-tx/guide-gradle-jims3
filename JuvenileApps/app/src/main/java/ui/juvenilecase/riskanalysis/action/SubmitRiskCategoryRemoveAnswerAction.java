package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.riskanalysis.RemoveAnswerEvent;
import messaging.riskanalysis.RemoveCategoryQuestionAnswerEvent;
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
import ui.juvenilecase.riskanalysis.form.HandleRiskQuestionDetailsSelectionForm;
import ui.juvenilecase.riskanalysis.form.RiskAnswerDeleteForm;
import ui.juvenilecase.riskanalysis.form.RiskCategoryDetailsForm;

public class SubmitRiskCategoryRemoveAnswerAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "removeAnswerRequest");
		keyMap.put("button.questionDetails", "backToDetails");
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
       	RiskAnswerDeleteForm raDeleteForm = (RiskAnswerDeleteForm) aForm;
    	RiskCategoryDetailsForm rcDetailsForm = (RiskCategoryDetailsForm)getSessionForm(aMapping, aRequest, "riskCategoryDetailsForm", true);
       	HandleRiskQuestionDetailsSelectionForm hrQDetailsSelectionForm = (HandleRiskQuestionDetailsSelectionForm)getSessionForm(aMapping, aRequest, "handleRiskQuestionDetailsSelectionForm", true);
       	
    	RemoveCategoryQuestionAnswerEvent removeQuestionAnswerEvent = 
    		(RemoveCategoryQuestionAnswerEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.REMOVECATEGORYQUESTIONANSWER);
    	
    	removeQuestionAnswerEvent.setRiskCategoryId(rcDetailsForm.getCategory().getCategoryId());
    	removeQuestionAnswerEvent.setRiskQuestionId(raDeleteForm.getQuestion().getRiskQuestionId()) ;
    	
       	List <GetAnswerResponseEvent> answersToBeRemovedList = raDeleteForm.getRemoveAnswersList();
       	GetAnswerResponseEvent gare = null;
       	RemoveAnswerEvent rae = null;
       	
       	for (int i = 0; i < answersToBeRemovedList.size(); i++) {
			gare = answersToBeRemovedList.get(i);
			rae = new RemoveAnswerEvent();
			rae.setRiskAnswerId(gare.getRiskAnswerId());
			removeQuestionAnswerEvent.addRequest(rae);
		}
       	
    	CompositeResponse cr = MessageUtil.postRequest(removeQuestionAnswerEvent);
    	CategoryResponseEvent cre = (CategoryResponseEvent) MessageUtil.filterComposite(cr, CategoryResponseEvent.class);
    	rcDetailsForm.getCategory().setCategoryId(cre.getCategoryId());
    	hrQDetailsSelectionForm.getCategory().setCategoryId(cre.getCategoryId());
    	hrQDetailsSelectionForm.getCategory().setFormulaStatusCd(cre.getFormulaStatusCd());
    	hrQDetailsSelectionForm.getCategory().setUpdatable(cre.isUpdatable());
    	hrQDetailsSelectionForm.getCategory().setVersion(cre.getVersion());
    	hrQDetailsSelectionForm.getCategory().setEntryDate(cre.getEntryDate());
    	hrQDetailsSelectionForm.getQuestion().setRiskCategoryId(cre.getCategoryId());
  
	    rcDetailsForm.getCategory().setCategoryId(cre.getCategoryId());
	    rcDetailsForm.getCategory().setFormulaStatusCd(cre.getFormulaStatusCd());
	    rcDetailsForm.getCategory().setUpdatable(cre.isUpdatable());
	    rcDetailsForm.getCategory().setModificationReason(cre.getModificationReason());
	    rcDetailsForm.getCategory().setVersion(cre.getVersion());
	    rcDetailsForm.getCategory().setEntryDate(cre.getEntryDate());
    	
    	GetQuestionResponseEvent qre = (GetQuestionResponseEvent) MessageUtil.filterComposite(cr, GetQuestionResponseEvent.class);
    	raDeleteForm.getQuestion().setRiskQuestionId(qre.getQuestionId());
    	raDeleteForm.getQuestion().setRiskCategoryId(Integer.toString(qre.getRiskCategoryId()));
    	raDeleteForm.getAnswerList().clear();
    	hrQDetailsSelectionForm.getQuestion().setRiskQuestionId(qre.getQuestionId());
         
        //Update Answer List from database
/*    	GetAnswersEvent gae = 
    		(GetAnswersEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETANSWERS);
    	gae.setReturnAnswersBasedOnQuestionId(true);
    	gae.setQuestionId(raDeleteForm.getQuestion().getRiskQuestionId());

    	cr = MessageUtil.postRequest(gae); 
*/    	
    	List answers = MessageUtil.compositeToList(cr,GetAnswerResponseEvent.class);
    	if (answers != null) {
    		Collections.sort(answers);
    		hrQDetailsSelectionForm.setAnswerList(answers);
    	} else {
    		hrQDetailsSelectionForm.setAnswerList(new ArrayList());
    	}

       	raDeleteForm.setPageType("confirm");
    	return aMapping.findForward("deleteSuccess");
    }  
	
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward backToDetails(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {   
    	RiskAnswerDeleteForm raDeleteForm = (RiskAnswerDeleteForm) aForm;
    	
    	HandleRiskQuestionDetailsSelectionForm hrQDetailsSelectionForm = (HandleRiskQuestionDetailsSelectionForm)getSessionForm(aMapping, aRequest, "handleRiskQuestionDetailsSelectionForm", true);
    	hrQDetailsSelectionForm.getQuestion().setRiskCategoryId(raDeleteForm.getQuestion().getRiskCategoryId());
    	hrQDetailsSelectionForm.getQuestion().setRiskQuestionId(raDeleteForm.getQuestion().getRiskQuestionId());
    	hrQDetailsSelectionForm.getCategory().setCategoryId(raDeleteForm.getQuestion().getRiskCategoryId());
    	
    	aRequest.getSession().setAttribute("selectedRiskQuestionId",raDeleteForm.getQuestion().getRiskQuestionId());
    	
    	raDeleteForm.clearForm();
    	raDeleteForm.setPageType("");
    	return aMapping.findForward("backToDetails");
    }  	
}