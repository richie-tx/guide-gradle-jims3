package ui.juvenilecase.riskanalysis.action;

import messaging.riskanalysis.RemoveQuestionEvent;
import messaging.riskanalysis.reply.RemoveQuestionResponseEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import org.apache.struts.action.ActionForward;
import java.util.Map;
import org.apache.struts.action.ActionForm;
import ui.juvenilecase.riskanalysis.form.RiskQuestionDeleteForm;

public class SubmitRiskQuestionDeleteAction extends JIMSBaseAction
{
	/**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse 
     * @return ActionForward
     */
    public ActionForward removeQuestion(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse)
    {
    	RiskQuestionDeleteForm riskQuestionDeleteForm = (RiskQuestionDeleteForm) aForm;
    	
    	//Create Save Questions & Answers Event
    	RemoveQuestionEvent removeQuestionEvent = (RemoveQuestionEvent) EventFactory
                .getInstance(JuvenileRiskAnalysisControllerServiceNames.REMOVEQUESTION);
    	removeQuestionEvent.setRiskQuestionId(riskQuestionDeleteForm.getQuestion().getRiskQuestionId());
    	
    	//Run Save Questions Event
    	CompositeResponse questionsReponse = 
        	MessageUtil.postRequest(removeQuestionEvent); 
    	
    	//Extract Remove Question Response Event from Response
    	RemoveQuestionResponseEvent removeQuestionResponseEvent = (RemoveQuestionResponseEvent)
			MessageUtil.filterComposite( questionsReponse, RemoveQuestionResponseEvent.class );
    	
    	//Clear Form 
    	//riskQuestionDeleteForm.clearForm();
    	
    	
    	return aMapping.findForward("success");
    	
    }
	
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.finish", "removeQuestion");
		keyMap.put("button.back", "back");
	}
}
