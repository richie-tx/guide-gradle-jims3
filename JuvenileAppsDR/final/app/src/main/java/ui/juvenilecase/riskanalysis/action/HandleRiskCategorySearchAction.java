package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.RiskResultGroupCodesResponseEvent;
import messaging.riskanalysis.GetCategoryDetailsEvent;
import messaging.riskanalysis.GetQuestionsEvent;
import messaging.riskanalysis.reply.CategoryResponseEvent;
import messaging.riskanalysis.reply.GetQuestionResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import naming.UIConstants;

import org.apache.commons.collections.comparators.ComparatorChain;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.ujac.util.BeanComparator;

import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.riskanalysis.form.RiskCategoryCreateForm;
import ui.juvenilecase.riskanalysis.form.RiskCategoryDetailsForm;
import ui.juvenilecase.riskanalysis.form.RiskCategorySearchForm;

public class HandleRiskCategorySearchAction extends JIMSBaseAction
{
	public ActionForward createCategoryRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {
		
		//Get Form(s) 
        //RiskAnalysisForm riskForm = (RiskAnalysisForm) aRequest.getSession().getAttribute("riskAnalysisForm");
    	RiskCategorySearchForm riskCategorySearchForm = (RiskCategorySearchForm) aForm;    	
    	riskCategorySearchForm.setSelectedValue(null);
    	
    	 //Get Questions Event
    	GetQuestionsEvent questionsEvent = (GetQuestionsEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
    	questionsEvent.setReturnQuestionsNotAttachedToCategory(true);
    	
    	//Extract Questions Unattached to any Category and place in List
    	//Run Get Questions Event
    	CompositeResponse questionsResponse = 
        	MessageUtil.postRequest(questionsEvent); 
       
    	//Extract GetQuestionsResponseEvents and place in List
    	List<GetQuestionResponseEvent> questions = MessageUtil.compositeToList( questionsResponse, GetQuestionResponseEvent.class );
    	
    	//Set Question Collection in Search Form
    	Collections.sort(questions);
		riskCategorySearchForm.setQuestions(questions);

		RiskCategoryCreateForm rcCreateForm = (RiskCategoryCreateForm)getSessionForm(aMapping, aRequest, "riskCategoryCreateForm", true);		
		rcCreateForm.setNewQuestionList(new ArrayList());
		rcCreateForm.setQuestionList(new ArrayList());
		rcCreateForm.clearCategory();
		rcCreateForm.setNewQuestionList(riskCategorySearchForm.getQuestions());
		rcCreateForm.setRiskResultGroups(riskCategorySearchForm.getRiskResultGroups());

    	return aMapping.findForward("createCategoryRequest");
    }

    public ActionForward searchCategoryRequest(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
		RiskCategoryDetailsForm rcdForm = (RiskCategoryDetailsForm) getSessionForm(aMapping, aRequest, "riskCategoryDetailsForm", true);
    	rcdForm.clear();
    	rcdForm.setQuestionsList(new ArrayList());
    	RiskCategorySearchForm rcSearchForm = (RiskCategorySearchForm) getSessionForm(aMapping, aRequest, "riskCategorySearchForm", true);
    	String selectedCatId = rcSearchForm.getSelectedValue();
    	GetCategoryDetailsEvent catDetEvent = 
    		(GetCategoryDetailsEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETCATEGORYDETAILS);
    	catDetEvent.setCategoryId(selectedCatId);
    	CompositeResponse catDetResponse = MessageUtil.postRequest(catDetEvent); 

    	List categories = MessageUtil.compositeToList( catDetResponse, CategoryResponseEvent.class );
    	List catQuestions = MessageUtil.compositeToList( catDetResponse, GetQuestionResponseEvent.class );
    	CategoryResponseEvent cre = (CategoryResponseEvent) categories.get(0);
    	
		rcdForm.getCategory().setCategoryId(cre.getCategoryId());
    	rcdForm.getCategory().setCategoryName(cre.getCategoryName());
		rcdForm.getCategory().setEntryDate(cre.getEntryDate());
		rcdForm.getCategory().setRiskResultGroupId(cre.getRiskResultGroupId());
		rcdForm.getCategory().setRiskResultGroup(UIConstants.EMPTY_STRING);
		
		if (cre.getRiskResultGroupId() != null && !UIConstants.EMPTY_STRING.equalsIgnoreCase(cre.getRiskResultGroupId()))
		{
			int len = rcSearchForm.getRiskResultGroups().size();
			for (int y=0; y<len; y++)
			{
				RiskResultGroupCodesResponseEvent cdre = (RiskResultGroupCodesResponseEvent) rcSearchForm.getRiskResultGroups().get(y);
				if (cdre.getCode().equals(cre.getRiskResultGroupId() ) )
				{
					rcdForm.getCategory().setRiskResultGroup(cdre.getDescription());
					break;
				}
			}
		}
		rcdForm.getCategory().setVersion(cre.getVersion());
		rcdForm.getCategory().setCategoryDescription(cre.getDescription());
		rcdForm.getCategory().setModificationReason(cre.getModificationReason());
		rcdForm.getCategory().setModificatoinDate(cre.getModifyDate());
		rcdForm.getCategory().setFormulaStatusCd(cre.getFormulaStatusCd());
		rcdForm.setCategoryName(cre.getCategoryName());
		rcdForm.getCategory().setUpdatable(cre.isUpdatable());
		rcdForm.getCategory().setTotalCategoriesTiedToFormulaGreaterThan1(cre.isTotalCategoriesTiedToFormulaGreaterThan1());
		
		catQuestions = this.sortQuestions(catQuestions);
    	rcdForm.setQuestionsList(catQuestions);  	

		return aMapping.findForward("searchCategoryRequest") ;
	}
    
    private List sortQuestions(List catQuestions) {
		List sortedList = new ArrayList(catQuestions);
		ArrayList sortFields = new ArrayList();
		sortFields.add(new ComparatorChain(new BeanComparator("uiDisplayOrderAsInt")));
		ComparatorChain cc = new ComparatorChain(sortFields);
		Collections.sort(sortedList, cc);
		return sortedList;
	}

	public ActionForward refresh(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward("refreshRiskCategorySearch") ;
	}
    
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.submit", "searchCategoryRequest");
		keyMap.put("button.createCategory", "createCategoryRequest");
	}
}