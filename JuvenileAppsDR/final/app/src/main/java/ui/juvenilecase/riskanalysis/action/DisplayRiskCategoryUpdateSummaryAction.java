package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.codetable.criminal.reply.RiskResultGroupCodesResponseEvent;
import messaging.riskanalysis.GetCategoryDetailsEvent;
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
import ui.juvenilecase.riskanalysis.form.RiskCategoryDetailsForm;
import ui.juvenilecase.riskanalysis.form.RiskCategorySearchForm;
import ui.juvenilecase.riskanalysis.form.RiskCategoryUpdateForm;

public class DisplayRiskCategoryUpdateSummaryAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.next", "nextRequest");
		keyMap.put("button.addSelected", "addSelectedRequest");
		keyMap.put("button.remove", "removeRequest");
//		keyMap.put("button.view", "viewQuestionRequest");
		keyMap.put("button.back", "backRequest");
	}
	
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward nextRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {   
    	RiskCategoryUpdateForm rcUpdateForm = (RiskCategoryUpdateForm) aForm;
    	List wrkList = rcUpdateForm.getRiskResultGroups();
    	String rgId = rcUpdateForm.getCategory().getRiskResultGroupId();
    	for(int g=0; g<wrkList.size(); g++)
    	{
    		RiskResultGroupCodesResponseEvent cre = (RiskResultGroupCodesResponseEvent) wrkList.get(g);
    		if (cre.getCode().equalsIgnoreCase(rgId))
    		{
    			rcUpdateForm.getCategory().setRiskResultGroup(cre.getDescription());
    			break;
    		}
    	}
    	wrkList = null;
    	rgId = null;
    	rcUpdateForm.setPageType("summary");
    	return aMapping.findForward("success");
    } 
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward addSelectedRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {   
    	RiskCategoryUpdateForm rcUpdateForm = (RiskCategoryUpdateForm) aForm;
       	List wrkList1 = new ArrayList();
    	List wrkList2 = new ArrayList();
    	List wrkList3 = new ArrayList();
    	wrkList1 = rcUpdateForm.getQuestionList();
    	wrkList2 = rcUpdateForm.getNewQuestionList();
    	int len = rcUpdateForm.getSelectedValue().length;
    	int len2 = wrkList2.size();
    	String[] selectedId = rcUpdateForm.getSelectedValue();
    	for (int x=0; x<len; x++)
    	{
    		for (int y=0; y<len2; y++)
    		{
        		GetQuestionResponseEvent gre = (GetQuestionResponseEvent) wrkList2.get(y);
        		if (selectedId[x].equalsIgnoreCase(gre.getQuestionId()))
        		{
        			wrkList1.add(gre);
        			break;
        		} 
    		}
    	}
    	boolean matchFound = false;
    	len = rcUpdateForm.getNewQuestionList().size();
     	for (int x=0; x<len; x++)
    	{
    		GetQuestionResponseEvent gre = (GetQuestionResponseEvent) wrkList2.get(x);
    		matchFound = false;
    		for (int y=0; y<wrkList1.size(); y++)
    		{
    			GetQuestionResponseEvent gre2 = (GetQuestionResponseEvent) wrkList1.get(y);
    			if (gre.getQuestionId().equalsIgnoreCase(gre2.getQuestionId()))
    			{
    				matchFound = true;
    				break;
    			}
    		}
    		if (matchFound == false)
    		{
    			wrkList3.add(gre);
    		}	
     	}
    	Collections.sort(wrkList3);
    	Collections.sort(wrkList1);
    	rcUpdateForm.setNewQuestionList(wrkList3);
    	rcUpdateForm.setQuestionList(wrkList1);
    	wrkList1 = null;
    	wrkList2 = null;
    	wrkList3 = null;
    	return aMapping.findForward("addSuccess");
    } 
    
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward removeRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {   
    	RiskCategoryUpdateForm rcUpdateForm = (RiskCategoryUpdateForm) aForm;
    	List wrkList1 = new ArrayList();
    	List wrkList2 = new ArrayList();
    	List selectedList = rcUpdateForm.getQuestionList();
    	int len = selectedList.size();
    	String selectedId = aRequest.getParameter("questionID");
    	for (int x=0; x<len; x++)
    	{
       		GetQuestionResponseEvent gre = (GetQuestionResponseEvent) selectedList.get(x);
       		if (selectedId.equalsIgnoreCase(gre.getQuestionId()))
       		{
       			wrkList1.add(gre);
       		} else {
       			wrkList2.add(gre);       			
       		}
    	}
    	Collections.sort(wrkList2);
    	rcUpdateForm.setQuestionList(wrkList2);
    	wrkList2 = rcUpdateForm.getNewQuestionList();
    	wrkList2.add(wrkList1.get(0));
    	Collections.sort(wrkList2);
    	rcUpdateForm.setNewQuestionList(wrkList2);
    	wrkList1 = null;
    	wrkList2 = null;
    	selectedList = null;
    	return aMapping.findForward("removeSuccess");
    }     
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward backRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {
// Retrieve original questions
    	RiskCategorySearchForm rcSearchForm = (RiskCategorySearchForm) getSessionForm(aMapping, aRequest, "riskCategorySearchForm", true);
    	RiskCategoryDetailsForm rcDetailsForm = (RiskCategoryDetailsForm)getSessionForm(aMapping, aRequest, "riskCategoryDetailsForm", true); 
    	GetCategoryDetailsEvent catDetEvent = 
    		(GetCategoryDetailsEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.GETCATEGORYDETAILS);
    	catDetEvent.setCategoryId(rcSearchForm.getSelectedValue());
    	CompositeResponse catDetResponse = MessageUtil.postRequest(catDetEvent); 
    	List catQuestions = MessageUtil.compositeToList( catDetResponse, GetQuestionResponseEvent.class );
    	catQuestions = this.sortQuestions(catQuestions);
    	rcDetailsForm.setQuestionsList(catQuestions);  	
    	RiskCategoryUpdateForm rcUpdateForm = (RiskCategoryUpdateForm) aForm;
    	rcUpdateForm.clearForm();
    	return aMapping.findForward(UIConstants.BACK);
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
