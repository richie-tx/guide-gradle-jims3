package pd.juvenilecase.riskanalysis.transactions;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import naming.RiskAnalysisConstants;

import pd.juvenilecase.riskanalysis.RiskFormula;

import messaging.riskanalysis.ActivateRiskFormulaEvent;
import messaging.riskanalysis.query.GetFormulasByAssessmentTypeAndStatusEvent;
import messaging.riskanalysis.reply.FormulaResponseEvent;
import mojo.km.messaging.IEvent;
import mojo.km.persistence.IHome;
import mojo.km.util.MessageUtil;
import mojo.km.context.ICommand;
import mojo.km.context.multidatasource.Home;

public class ActivateRiskFormulaCommand implements ICommand
{
	public void execute(IEvent anEvent)
	{
		ActivateRiskFormulaEvent reqEvent = (ActivateRiskFormulaEvent) anEvent;
		
		RiskFormula formulaToBeActivated = RiskFormula.find(reqEvent.getRiskFormulaId());
		
		GetFormulasByAssessmentTypeAndStatusEvent getFormulasEvt = new GetFormulasByAssessmentTypeAndStatusEvent();
	
		getFormulasEvt = new GetFormulasByAssessmentTypeAndStatusEvent();
		getFormulasEvt.setAssessmentType(formulaToBeActivated.getAssessmentType());
		getFormulasEvt.setStatusCd(RiskAnalysisConstants.JUV_RISK_FORMULA_STATUS_ACTIVE);
		IHome home = new Home();
		
		List <RiskFormula> activeFormulas = RiskFormula.findAll(getFormulasEvt);
	
		if (activeFormulas.size() > 0){
			RiskFormula activeFormula = activeFormulas.get(0);
			activeFormula.setStatusCd(RiskAnalysisConstants.JUV_RISK_FORMULA_STATUS_INACTIVE);
			//home.bind(activeFormula);
			//MessageUtil.postReply(activeFormula.getResponseEvent());
			activeFormula = null;
		}

		formulaToBeActivated.setActivateDate(new Date());
		formulaToBeActivated.setStatusCd(RiskAnalysisConstants.JUV_RISK_FORMULA_STATUS_ACTIVE);
		//home.bind(formulaToBeActivated);
	
		
		MessageUtil.postReply(formulaToBeActivated.getResponseEvent());

		reqEvent = null;
		formulaToBeActivated = null;
		getFormulasEvt = null;
		activeFormulas = null;
		home = null;
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
