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
import naming.UIConstants;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.riskanalysis.form.RiskCategoryCreateForm;

public class SubmitRiskCategoryCreateAction extends JIMSBaseAction
{
	public ActionForward createCategory(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	RiskCategoryCreateForm riskCategoryCreateForm = (RiskCategoryCreateForm) aForm;
    	
       	SaveCategoryEvent saveCtgEvt = 
       		(SaveCategoryEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.SAVECATEGORY);
       	
       	saveCtgEvt.setCategoryId(null);
       	saveCtgEvt.setCategoryName(riskCategoryCreateForm.getCategory().getCategoryName());
       	saveCtgEvt.setDescription(riskCategoryCreateForm.getCategory().getCategoryDescription());
       	saveCtgEvt.setRiskResultGroupId(riskCategoryCreateForm.getCategory().getRiskResultGroupId());
       	
       	SaveQuestionEvent saveQuesEvt = null;
       	GetQuestionResponseEvent qre = null;
       	
       	for (int i = 0; i < riskCategoryCreateForm.getQuestionList().size(); i++) {
			qre = (GetQuestionResponseEvent) riskCategoryCreateForm.getQuestionList().get(i);
			saveQuesEvt = new SaveQuestionEvent();
			saveQuesEvt.setRiskQuestionId(qre.getQuestionId());
			saveCtgEvt.addRequest(saveQuesEvt);
		}
       	
       	CompositeResponse cr = MessageUtil.postRequest(saveCtgEvt);
       	CategoryResponseEvent cre = (CategoryResponseEvent) MessageUtil.filterComposite(cr, CategoryResponseEvent.class);
       	riskCategoryCreateForm.getCategory().setCategoryId(cre.getCategoryId());
       	riskCategoryCreateForm.getCategory().setVersion(cre.getVersion());
       	riskCategoryCreateForm.getCategory().setEntryDate(cre.getEntryDate());
       	
       	List qList = MessageUtil.compositeToList(cr, GetQuestionResponseEvent.class);
       	riskCategoryCreateForm.setQuestionList(qList);
    	riskCategoryCreateForm.setActionType("confirm");
    	
    	saveCtgEvt = null;
    	saveQuesEvt = null;
    	qre = null;
    	cr = null;
    	cre = null;
    	qList = null;
    	
    	return aMapping.findForward("success");
    }

	public ActionForward backToCategorySearch(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
		return aMapping.findForward("backToSearch");
    }
	
	public ActionForward cancel(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			return aMapping.findForward(UIConstants.CANCEL);
		}

	
	public ActionForward back(
			ActionMapping aMapping,
			ActionForm aForm,
			HttpServletRequest aRequest,
			HttpServletResponse aResponse)
		{
			return aMapping.findForward(UIConstants.BACK);
		}
	
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "createCategory");
		keyMap.put("button.back", "back");
		keyMap.put("button.cancel", "cancel");
		keyMap.put("button.categorySearch", "backToCategorySearch");
	}
}
