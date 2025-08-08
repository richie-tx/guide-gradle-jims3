package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.riskanalysis.UpdateCategoryQuestionAnswerEvent;
import messaging.riskanalysis.reply.CategoryResponseEvent;
import messaging.riskanalysis.reply.GetAnswerResponseEvent;
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
import ui.juvenilecase.riskanalysis.form.RiskAnswerUpdateForm;
import ui.juvenilecase.riskanalysis.form.RiskCategoryDetailsForm;
import ui.juvenilecase.riskanalysis.form.RiskCategorySearchForm;

public class SubmitRiskCategoryUpdateAnswerAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "updateAnswerRequest");
		keyMap.put("button.questionDetails", "backToDetails");
	}
	   /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward updateAnswerRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {   
       	RiskAnswerUpdateForm raUpdateForm = (RiskAnswerUpdateForm) aForm;
    	HandleRiskQuestionDetailsSelectionForm hrqDetailsSelectionForm = 
    		(HandleRiskQuestionDetailsSelectionForm) getSessionForm(aMapping, aRequest, "handleRiskQuestionDetailsSelectionForm", true);
    	RiskCategoryDetailsForm rcDetailsForm = (RiskCategoryDetailsForm)getSessionForm(aMapping, aRequest, "riskCategoryDetailsForm", true);
    	
    	//Create Save Answer Event (One SaveAnswerEvent houses many saveAnswerEvents in it's requests attribute)
    	UpdateCategoryQuestionAnswerEvent saveAnswerEvent = 
    		(UpdateCategoryQuestionAnswerEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.UPDATECATEGORYQUESTIONANSWER);
    	saveAnswerEvent.setACreate(false);
    	saveAnswerEvent.setRiskQuestionId(raUpdateForm.getSelectedValue());
    	saveAnswerEvent.setRiskCategoryId(rcDetailsForm.getCategory().getCategoryId());
    	
     	List answerList = new ArrayList();
    	answerList.add(raUpdateForm.getCurrentAnswer());
    	RiskAnalysisObjectsAdapter.createAnswerEventObject(saveAnswerEvent, answerList);
    	saveAnswerEvent.addRequest(saveAnswerEvent);
    	
    	//Run Save Answer Event
    	CompositeResponse cr = MessageUtil.postRequest(saveAnswerEvent);
    	
       	CategoryResponseEvent cre = (CategoryResponseEvent) MessageUtil.filterComposite(cr, CategoryResponseEvent.class);
       	GetAnswerResponseEvent garEvent = (GetAnswerResponseEvent) MessageUtil.filterComposite(cr, GetAnswerResponseEvent.class);
       	raUpdateForm.getCurrentAnswer().setRiskAnswerId(garEvent.getRiskAnswerId());
       	raUpdateForm.getCurrentAnswer().setSubordinateQuestionId(garEvent.getSubordinateQuestionId());
       	raUpdateForm.getQuestion().setRiskCategoryId(cre.getCategoryId());
       	raUpdateForm.getQuestion().setRiskQuestionId(Integer.toString(garEvent.getRiskQuestionId()));
  
	    rcDetailsForm.getCategory().setCategoryId(cre.getCategoryId());
	    rcDetailsForm.getCategory().setFormulaStatusCd(cre.getFormulaStatusCd());
	    rcDetailsForm.getCategory().setUpdatable(cre.isUpdatable());
	    rcDetailsForm.getCategory().setModificationReason(cre.getModificationReason());
	    rcDetailsForm.getCategory().setVersion(cre.getVersion());
	    rcDetailsForm.getCategory().setEntryDate(cre.getEntryDate());

       	RiskCategorySearchForm rcSearchForm = (RiskCategorySearchForm) getSessionForm(aMapping, aRequest, "riskCategorySearchForm", true);
    	rcSearchForm.setRiskCategoryId(cre.getCategoryId());
    	rcSearchForm.setSelectedValue(cre.getCategoryId());

    	//Convert currentAnswer into GetAnswerResponseEvent to load Form answerList
    	garEvent = new GetAnswerResponseEvent();
		garEvent.setAction(raUpdateForm.getCurrentAnswer().getAction());
		garEvent.setRiskAnswerId(raUpdateForm.getCurrentAnswer().getRiskAnswerId());
		garEvent.setResponse(raUpdateForm.getCurrentAnswer().getAnswerText());
		garEvent.setSortOrder(Integer.parseInt(raUpdateForm.getCurrentAnswer().getSortOrder() ) );
		garEvent.setSubordinateQuestionId(raUpdateForm.getCurrentAnswer().getSubordinateQuestionId());
		garEvent.setSubordinateQuestionName(raUpdateForm.getCurrentAnswer().getSubordinateQuestionName());
		garEvent.setWeight(Integer.parseInt(raUpdateForm.getCurrentAnswer().getWeight() ) );
		garEvent.setAnswerEntryDate(raUpdateForm.getCurrentAnswer().getAnswerEntryDate());
        for (int x=0; x<hrqDetailsSelectionForm.getAnswerList().size(); x++)
        {
        	GetAnswerResponseEvent garEvent2 = (GetAnswerResponseEvent) hrqDetailsSelectionForm.getAnswerList().get(x);
        	if (garEvent2.getRiskAnswerId().equals(raUpdateForm.getCurrentAnswer().getRiskAnswerId()))
        	{
        		hrqDetailsSelectionForm.getAnswerList().set(x, garEvent);
        		break;
        	}
        }
       	raUpdateForm.setPageType("confirm");
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
    	RiskAnswerUpdateForm raUpdateForm = (RiskAnswerUpdateForm) aForm;
    	raUpdateForm.clearForm();
    	raUpdateForm.setPageType("");
    	raUpdateForm.clearCurrentAnswer();
    	return aMapping.findForward("backToDetails");
    }  	

}