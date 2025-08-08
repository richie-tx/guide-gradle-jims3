package pd.juvenilecase.riskanalysis.transactions;

import java.util.ArrayList;
import java.util.List;

import naming.RiskAnalysisConstants;

import pd.juvenilecase.riskanalysis.RiskCategory;
import pd.juvenilecase.riskanalysis.RiskFormula;
import pd.juvenilecase.riskanalysis.RiskQuestions;
import pd.juvenilecase.riskanalysis.RiskRecommendation;
import pd.juvenilecase.riskanalysis.RiskWeightedResponse;
import messaging.error.reply.ErrorResponseEvent;
import messaging.riskanalysis.GetFormulaDetailsforActivationEvent;
import messaging.riskanalysis.query.GetFormulasByAssessmentTypeAndStatusEvent;
import mojo.km.messaging.IEvent;
import mojo.km.util.CollectionUtil;
import mojo.km.util.MessageUtil;
import mojo.km.context.ICommand;

public class GetFormulaDetailsforActivationCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		GetFormulaDetailsforActivationEvent reqEvent = (GetFormulaDetailsforActivationEvent) anEvent;
		
		RiskFormula formulaToBeActivated = RiskFormula.find(reqEvent.getFormulaToBeActivatedId());
		
		GetFormulasByAssessmentTypeAndStatusEvent getFormulasEvt = new GetFormulasByAssessmentTypeAndStatusEvent();
		getFormulasEvt.setAssessmentType(formulaToBeActivated.getAssessmentType());
		getFormulasEvt.setStatusCd(RiskAnalysisConstants.JUV_RISK_FORMULA_STATUS_PENDING);
		
		List pendingFormulaList = RiskFormula.findAll(getFormulasEvt);
		
		ErrorResponseEvent re = null;
		List replies = new ArrayList();
		
		if (pendingFormulaList.size() > 1){
			re = new ErrorResponseEvent();
			re.setMessage("MULTIPLE PENDING VERSIONS OF FORMULA EXIST");
			replies.add(re);
		}

		getFormulasEvt = new GetFormulasByAssessmentTypeAndStatusEvent();
		getFormulasEvt.setAssessmentType(formulaToBeActivated.getAssessmentType());
		getFormulasEvt.setStatusCd(RiskAnalysisConstants.JUV_RISK_FORMULA_STATUS_ACTIVE);
		
		List <RiskFormula> activeFormulas = RiskFormula.findAll(getFormulasEvt);
	
		if (activeFormulas.size() > 1){
			re = new ErrorResponseEvent();
			re.setMessage("MULTIPLE ACTIVE VERSIONS OF FORMULA EXIST");
			replies.add(re);
		} 
		
		List recommendations = CollectionUtil.iteratorToList(RiskRecommendation.findAll("riskFormulaId", formulaToBeActivated.getOID()));
		if (recommendations.size() < 2){
			re = new ErrorResponseEvent();
			re.setMessage("THERE MUST BE AT LEAST 2 RECOMMENDATIONS ASSOCIATED TO FORMULA");
			replies.add(re);
		}
		
		List <RiskCategory> categories = RiskCategory.findAll("formulaId", formulaToBeActivated.getOID());
		if (categories.size() == 0){
			re = new ErrorResponseEvent();
			re.setMessage("THERE ARE NO CATEGORIES ASSOCIATED TO FORMULA");
			replies.add(re);
		} 
		
		RiskCategory category = null;
		RiskQuestions question = null;
		
		for (int i = 0; i < categories.size(); i++) {
			category = categories.get(i);
			List <RiskQuestions> questions = CollectionUtil.iteratorToList(RiskQuestions.findAll("riskCategoryId", category.getOID()));
			if (questions.size() == 0){
				re = new ErrorResponseEvent();
				re.setMessage("THERE ARE NO QUESTIONS ASSOCIATED TO CATEGORY " + category.getCategoryName());
				replies.add(re);
			}
			for (int j = 0; j < questions.size(); j++) {
				question = questions.get(j);
				List <RiskWeightedResponse> answers = CollectionUtil.iteratorToList(RiskWeightedResponse.findAll("riskQuestionId", question.getOID()));
				if (answers.size() == 0){
					re = new ErrorResponseEvent();
					re.setMessage("THERE ARE NO QUESTIONS ASSOCIATED TO QUESTION " + question.getQuestionName());
					replies.add(re);
				}
				answers = null;
			}
			questions = null;
		}
		
		if (replies.size() > 0){
			MessageUtil.postReplies(replies);
			return;
		}
		
		MessageUtil.postReply(formulaToBeActivated.getResponseEvent());
				
		RiskFormula formulaToBeDeactivated = null;
		
		if (activeFormulas.size() > 0){
			formulaToBeDeactivated = activeFormulas.get(0);
			MessageUtil.postReply(formulaToBeDeactivated.getResponseEvent());
		}
		
		reqEvent = null;
		formulaToBeActivated = null;
		getFormulasEvt = null;
		pendingFormulaList = null;
		re = null;
		replies = null;
		activeFormulas = null;
		recommendations = null;
		categories = null;
		category = null;
		question = null;
		formulaToBeDeactivated = null;
	}

	public void onRegister(IEvent anEvent)
	{
	}

	public void onUnregister(IEvent anEvent)
	{
	}

	public void update(Object anObject)
	{
	}
}
