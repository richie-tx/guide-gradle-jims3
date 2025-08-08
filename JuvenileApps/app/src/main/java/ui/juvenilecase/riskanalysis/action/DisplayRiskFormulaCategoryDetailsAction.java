package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.RiskResultGroupCodesResponseEvent;
import messaging.riskanalysis.GetCategoryDetailsEvent;
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
import ui.juvenilecase.riskanalysis.form.RiskFormulaDetailsForm;

public class DisplayRiskFormulaCategoryDetailsAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.view", "viewCategory");
	}
    public ActionForward viewCategory(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) throws GeneralFeedbackMessageException
	{
    	RiskFormulaDetailsForm rfDetailsForm = (RiskFormulaDetailsForm) aForm;
    	String selectedCatId = aRequest.getParameter("categoryId");
//retrieve category details
    	GetCategoryDetailsEvent catDetEvent = 
    		(GetCategoryDetailsEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETCATEGORYDETAILS);
    	catDetEvent.setCategoryId(selectedCatId);
    	CompositeResponse catDetResponse = MessageUtil.postRequest(catDetEvent); 

    	List categories = MessageUtil.compositeToList( catDetResponse, CategoryResponseEvent.class );
    	List catQuestions = MessageUtil.compositeToList( catDetResponse, GetQuestionResponseEvent.class );
    	CategoryResponseEvent crEvent = (CategoryResponseEvent) categories.get(0);
 
    	rfDetailsForm.clearCategory();
    	rfDetailsForm.getCategory().setCategoryName(crEvent.getCategoryName());
    	rfDetailsForm.getCategory().setRiskResultGroup(crEvent.getRiskResultGroupDesc());
    	rfDetailsForm.getCategory().setVersion(crEvent.getVersion());
    	rfDetailsForm.getCategory().setCategoryDescription(crEvent.getDescription());
    	rfDetailsForm.getCategory().setEntryDate(crEvent.getEntryDate());
    	rfDetailsForm.setQuestionsList(catQuestions);    	
/** add risk result group description to category  */		
		if (crEvent.getRiskResultGroupId() != null && !UIConstants.EMPTY_STRING.equalsIgnoreCase(crEvent.getRiskResultGroupId()))
		{
			int len = rfDetailsForm.getRiskResultGroups().size();
			for (int y=0; y<len; y++)
			{
				RiskResultGroupCodesResponseEvent cdre = (RiskResultGroupCodesResponseEvent) rfDetailsForm.getRiskResultGroups().get(y);
				if (cdre.getCode().equals(crEvent.getRiskResultGroupId() ) )
				{
					rfDetailsForm.getCategory().setRiskResultGroup(cdre.getDescription());
					break;
				}
			}
		}
    	rfDetailsForm.getCategory().setVersion(crEvent.getVersion());
    	rfDetailsForm.getCategory().setCategoryDescription(crEvent.getDescription());
    	rfDetailsForm.getCategory().setModificationReason(crEvent.getModificationReason());
    	rfDetailsForm.getCategory().setModificatoinDate(crEvent.getModifyDate());
    	rfDetailsForm.getCategory().setFormulaStatusCd(crEvent.getFormulaStatusCd());
    	rfDetailsForm.setCategoryName(crEvent.getCategoryName());
    	rfDetailsForm.getCategory().setUpdatable(crEvent.isUpdatable());
    	rfDetailsForm.getCategory().setTotalCategoriesTiedToFormulaGreaterThan1(crEvent.isTotalCategoriesTiedToFormulaGreaterThan1());
		
		catQuestions = this.sortQuestions(catQuestions);
		rfDetailsForm.setQuestionsList(catQuestions);  	

    	return aMapping.findForward("success") ;
	}
    private List sortQuestions(List catQuestions) {
		List sortedList = new ArrayList(catQuestions);
		ArrayList sortFields = new ArrayList();
		sortFields.add(new ComparatorChain(new BeanComparator("uiDisplayOrderAsInt")));
		ComparatorChain cc = new ComparatorChain(sortFields);
		Collections.sort(sortedList, cc);
		return sortedList;
	}
}
