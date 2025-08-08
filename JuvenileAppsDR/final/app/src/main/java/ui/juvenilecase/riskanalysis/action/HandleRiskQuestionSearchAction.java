package ui.juvenilecase.riskanalysis.action;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import org.apache.struts.action.ActionForward;
import java.util.Map;
import org.apache.struts.action.ActionForm;
import ui.juvenilecase.riskanalysis.form.RiskQuestionSearchForm;
/**
 * @author PAlcocer
 *
 * Class which handles a request and redirects to the correct action
 *
 */
public class HandleRiskQuestionSearchAction extends JIMSBaseAction
{
	public HandleRiskQuestionSearchAction()
	{
		
	}
	
	
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     * @roseuid 433C3D3D01E1
     */
    public ActionForward searchQuestionRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	//Get Form(s) 
    	RiskQuestionSearchForm riskQuestionSearchForm = (RiskQuestionSearchForm) aForm;
    	
    	//QuestionId to be used for the Question Details Selection page
    	String selectedRiskQuestionId = riskQuestionSearchForm.getQuestionId();
    	aRequest.setAttribute("selectedRiskQuestionId", selectedRiskQuestionId);
    	
    	return aMapping.findForward("searchQuestionRequest");
    }
	
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward createQuestionRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {   
    	return aMapping.findForward("createQuestionRequest");
    }
    
    public ActionForward refresh(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse)
	{
		return aMapping.findForward("refreshRiskQuestionSearch") ;
	}
	
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.refresh", "refresh");
		keyMap.put("button.submit", "searchQuestionRequest");
		keyMap.put("button.createQuestion", "createQuestionRequest");
	}
}
