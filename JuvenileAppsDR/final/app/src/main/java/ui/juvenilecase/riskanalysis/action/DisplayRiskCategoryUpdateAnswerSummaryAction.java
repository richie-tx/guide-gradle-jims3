package ui.juvenilecase.riskanalysis.action;

import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ui.action.JIMSBaseAction;
import ui.exception.GeneralFeedbackMessageException;
import ui.juvenilecase.riskanalysis.RiskAnalysisHelper;
import ui.juvenilecase.riskanalysis.form.RiskAnswerUpdateForm;
import ui.juvenilecase.riskanalysis.form.objects.Answer;

import org.apache.struts.action.ActionForward;
import java.util.Map;
import org.apache.struts.action.ActionForm;

public class DisplayRiskCategoryUpdateAnswerSummaryAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.next", "nextRequest");
	}
	
    /**
     * @param aMapping
     * @param aForm
     * @param aRequest
     * @param aResponse
     * @return ActionForward
     */
    public ActionForward nextRequest(ActionMapping aMapping, ActionForm aForm, HttpServletRequest aRequest,
            HttpServletResponse aResponse) throws GeneralFeedbackMessageException
    {   
    	RiskAnswerUpdateForm raUpdateForm = (RiskAnswerUpdateForm) aForm;
    	Answer answer = raUpdateForm.getCurrentAnswer();
    	RiskAnalysisHelper.setSubordinateQuestionName(raUpdateForm.getQuestions(), answer);
    	raUpdateForm.setPageType("summary");
    	return aMapping.findForward("nextSuccess");
    }

}
