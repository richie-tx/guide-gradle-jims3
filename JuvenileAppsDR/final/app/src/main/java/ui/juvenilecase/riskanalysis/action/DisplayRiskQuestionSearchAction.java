package ui.juvenilecase.riskanalysis.action;

import messaging.riskanalysis.GetQuestionsEvent;
import messaging.riskanalysis.reply.GetQuestionResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import org.apache.struts.action.ActionForward;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.apache.struts.action.ActionForm;
import ui.juvenilecase.riskanalysis.form.RiskQuestionSearchForm;

public class DisplayRiskQuestionSearchAction extends JIMSBaseAction
{
	public DisplayRiskQuestionSearchAction()
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
    public ActionForward execute(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	//Get Form(s) 
        //RiskAnalysisForm riskForm = (RiskAnalysisForm) aRequest.getSession().getAttribute("riskAnalysisForm");
    	RiskQuestionSearchForm riskQuestionSearchForm = (RiskQuestionSearchForm) aForm;
    	riskQuestionSearchForm.setQuestionId(null);
        
    	 //Create Get Questions Event
    	GetQuestionsEvent getQuestionsEvent = (GetQuestionsEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.GETQUESTIONS);
    	getQuestionsEvent.setReturnQuestionsNotAttachedToCategory(true);
    	
    	//Run Get Questions Event
    	CompositeResponse questionsReponse = 
        	MessageUtil.postRequest(getQuestionsEvent); 
       
    	//Extract GetQuestionsResponseEvents and place in List
    	List questions = MessageUtil.compositeToList( questionsReponse, GetQuestionResponseEvent.class );
		Collections.sort( questions );
    	
    	//Set List in Form
    	riskQuestionSearchForm.setQuestions(questions);
    	
    	//return aMapping.getInputForward(); //uses input attribute in config mapping
    	return aMapping.findForward("success");
		
    }
	
	protected void addButtonMapping(Map keyMap)
	{
	}
}
