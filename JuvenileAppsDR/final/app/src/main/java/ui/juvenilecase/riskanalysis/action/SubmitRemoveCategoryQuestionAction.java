package ui.juvenilecase.riskanalysis.action;

import messaging.riskanalysis.RemoveCategoryQuestionsEvent;
import messaging.riskanalysis.SaveQuestionEvent;
import messaging.riskanalysis.reply.CategoryResponseEvent;
import messaging.riskanalysis.reply.GetQuestionResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.riskanalysis.form.RiskCategoryDetailsForm;
import ui.juvenilecase.riskanalysis.form.RiskCategorySearchForm;
import ui.juvenilecase.riskanalysis.form.RiskQuestionDeleteForm;

import org.apache.struts.action.ActionForward;

import java.util.Map;
import org.apache.struts.action.ActionForm;

public class SubmitRemoveCategoryQuestionAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "removeRequest");
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
    public ActionForward removeRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {   
    	RiskQuestionDeleteForm rqdForm = (RiskQuestionDeleteForm) aForm;
    	RiskCategoryDetailsForm rcDetailsForm = (RiskCategoryDetailsForm) getSessionForm(aMapping, aRequest, "riskCategoryDetailsForm", true);
    	
       	RemoveCategoryQuestionsEvent saveCtgEvt = 
       		(RemoveCategoryQuestionsEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.REMOVECATEGORYQUESTIONS);
       	
       	saveCtgEvt.setCategoryId(rcDetailsForm.getCategory().getCategoryId());
       	
       	SaveQuestionEvent saveQuesEvt = null;
       	GetQuestionResponseEvent qre = null;
       	
       	for (int i = 0; i < rqdForm.getRemoveQuestions().size(); i++) {
			qre = (GetQuestionResponseEvent) rqdForm.getRemoveQuestions().get(i);
			saveQuesEvt = new SaveQuestionEvent();
			saveQuesEvt.setRiskQuestionId(qre.getQuestionId());
			saveCtgEvt.addRequest(saveQuesEvt);
		}
       	
		CompositeResponse cr = MessageUtil.postRequest(saveCtgEvt);
		CategoryResponseEvent cre = (CategoryResponseEvent) MessageUtil.filterComposite(cr, CategoryResponseEvent.class);
       
		RiskCategorySearchForm rcSearchForm = (RiskCategorySearchForm) getSessionForm(aMapping, aRequest, "riskCategorySearchForm", true);
		rcSearchForm.setRiskCategoryId(cre.getCategoryId());
		rcSearchForm.setSelectedValue(cre.getCategoryId());
		rcDetailsForm.getCategory().setEntryDate(cre.getEntryDate());
		rcDetailsForm.getCategory().setVersion(cre.getVersion());
       
       saveCtgEvt = null;
       saveQuesEvt = null;
       qre = null;
            	
       rqdForm.setPageType("confirm");
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
            HttpServletResponse aResponse)
    {   
    	RiskQuestionDeleteForm rqdForm = (RiskQuestionDeleteForm) aForm;
       	rqdForm.clearForm();
       	rqdForm.setPageType("");
    	return aMapping.findForward("backToDetails");
    }  
}
