package ui.juvenilecase.riskanalysis.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import messaging.juvenilecase.reply.RiskQuestionResponseEvent;
import messaging.riskanalysis.CheckProgressPreConditionsEvent;
import mojo.km.messaging.EventFactory;
import mojo.km.messaging.Composite.CompositeResponse;
import mojo.km.util.CollectionUtil;
import mojo.km.utilities.MessageUtil;
import naming.JuvenileRiskAnalysisControllerServiceNames;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import ui.action.JIMSBaseAction;
import ui.juvenilecase.UIRiskAnalysisHelper;
import ui.juvenilecase.form.riskanalysis.RiskAnalysisForm;

public class DisplayRiskFormulaPreviewAction extends JIMSBaseAction
{
	protected void addButtonMapping(Map keyMap)
	{
		keyMap.put("button.view", "displayPreview");
		keyMap.put("button.back", "back");
	}
	
    public ActionForward displayPreview(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
    	String assessmentId = aRequest.getAttribute("selectedAssessmentId").toString();
    	String assessmentType = aRequest.getAttribute("selectedAssessmentType").toString();
    	aRequest.setAttribute("selectedAssessmentId", null);
    	aRequest.setAttribute("selectedAssessmentType", null);

    	RiskAnalysisForm riskAnalysisForm = (RiskAnalysisForm) aForm;
    	riskAnalysisForm.clear();

		CheckProgressPreConditionsEvent event =
			(CheckProgressPreConditionsEvent) EventFactory.getInstance(JuvenileRiskAnalysisControllerServiceNames.CHECKPROGRESSPRECONDITIONS);
		event.setBypassPreconditionEdit(true);
		event.setFormulaId(assessmentId);
		event.setFormula(assessmentType);
		CompositeResponse response = MessageUtil.postRequest(event);

		try
		{
			List <RiskQuestionResponseEvent> qaGroupList = UIRiskAnalysisHelper.groupQuestionsAndAnswers(response);
			riskAnalysisForm.setRiskAssessmentType( qaGroupList.get(0).getAssessmentType());
	
			List <RiskQuestionResponseEvent> qaList = CollectionUtil.iteratorToList(qaGroupList.iterator());
			riskAnalysisForm.setQuestionAnswers(qaList);
			qaGroupList = null;
			qaList = null;
		} catch (Exception ex){
			riskAnalysisForm.setRiskAssessmentType(assessmentType);
			riskAnalysisForm.setQuestionAnswers(new ArrayList());
		}
		return aMapping.findForward("previewSuccess") ;
	}
    
    public ActionForward back(ActionMapping aMapping, ActionForm aForm,
			HttpServletRequest aRequest, HttpServletResponse aResponse) 
	{
    	RiskAnalysisForm riskAnalysisForm = (RiskAnalysisForm) aForm;
    	riskAnalysisForm.clear();
    	return aMapping.findForward("back") ;
	}	
}