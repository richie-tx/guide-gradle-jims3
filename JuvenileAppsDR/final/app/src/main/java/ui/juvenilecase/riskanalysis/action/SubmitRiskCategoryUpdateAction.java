package ui.juvenilecase.riskanalysis.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.riskanalysis.SaveCategoryEvent;
import messaging.riskanalysis.SaveQuestionEvent;
import messaging.riskanalysis.reply.CategoryResponseEvent;
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
import ui.juvenilecase.riskanalysis.form.RiskCategoryDetailsForm;
import ui.juvenilecase.riskanalysis.form.RiskCategorySearchForm;
import ui.juvenilecase.riskanalysis.form.RiskCategoryUpdateForm;

public class SubmitRiskCategoryUpdateAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "updateCategoryRequest");
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
    public ActionForward updateCategoryRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {   
       	RiskCategoryUpdateForm rcUpdateForm = (RiskCategoryUpdateForm) aForm;
    	RiskCategorySearchForm rcSearchForm = (RiskCategorySearchForm) getSessionForm(aMapping, aRequest, "riskCategorySearchForm", true);
    	RiskCategoryDetailsForm rcDetailsForm = (RiskCategoryDetailsForm) getSessionForm(aMapping, aRequest, "riskCategoryDetailsForm", true);  	
       	
       	SaveCategoryEvent saveCtgEvt = 
       		(SaveCategoryEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVECATEGORY);
       	
       	saveCtgEvt.setCategoryId(rcUpdateForm.getCategory().getCategoryId());
       	saveCtgEvt.setCategoryName(rcUpdateForm.getCategory().getCategoryName());
       	saveCtgEvt.setDescription(rcUpdateForm.getCategory().getCategoryDescription());
       	saveCtgEvt.setRiskResultGroupId(rcUpdateForm.getCategory().getRiskResultGroupId());
       	
       	SaveQuestionEvent saveQuesEvt = null;
       	GetQuestionResponseEvent qre = null;
       	
       	for (int i = 0; i < rcUpdateForm.getQuestionList().size(); i++) {
			qre = (GetQuestionResponseEvent) rcUpdateForm.getQuestionList().get(i);
			saveQuesEvt = new SaveQuestionEvent();
			saveQuesEvt.setRiskQuestionId(qre.getQuestionId());
			saveCtgEvt.addRequest(saveQuesEvt);
		}
       	
       	CompositeResponse cr = MessageUtil.postRequest(saveCtgEvt);
       	CategoryResponseEvent cre = (CategoryResponseEvent) MessageUtil.filterComposite(cr, CategoryResponseEvent.class);
       	rcUpdateForm.getCategory().setCategoryId(cre.getCategoryId());
       	rcUpdateForm.getCategory().setVersion(cre.getVersion());
       	rcUpdateForm.getCategory().setEntryDate(cre.getEntryDate());
     	
       	List qList = MessageUtil.compositeToList(cr, GetQuestionResponseEvent.class);
       	rcUpdateForm.setQuestionList(qList);
       	
    	rcSearchForm.setRiskCategoryId(cre.getCategoryId());
    	rcSearchForm.setSelectedValue(cre.getCategoryId());
    	
       	rcDetailsForm.getCategory().setCategoryId(cre.getCategoryId());
       	rcDetailsForm.getCategory().setCategoryName(cre.getCategoryName());
       	rcDetailsForm.getCategory().setCategoryDescription(cre.getDescription());
    	rcDetailsForm.getCategory().setEntryDate(cre.getEntryDate());
    	rcDetailsForm.getCategory().setVersion(cre.getVersion());
    	rcDetailsForm.getCategory().setRiskResultGroupId(cre.getRiskResultGroupId());
    	rcDetailsForm.getCategory().setRiskResultGroup(cre.getRiskResultGroupDesc());
    	rcDetailsForm.getCategory().setUpdatable(cre.isUpdatable());
    	rcDetailsForm.getCategory().setTotalCategoriesTiedToFormulaGreaterThan1(cre.isTotalCategoriesTiedToFormulaGreaterThan1());
    	
    	rcUpdateForm.setPageType("confirm");
    	return aMapping.findForward("updateSuccess");
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
    	RiskCategoryUpdateForm rcUpdateForm = (RiskCategoryUpdateForm) aForm;
    	rcUpdateForm.setPageType("");
    	return aMapping.findForward("backToDetails");
    } 
}
